package com.jnu.example.blog.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnu.example.db.pojo.dto.UserAddRequestDTO;
import com.jnu.example.db.pojo.dto.UserUpdateRequestDTO;
import com.jnu.example.blog.service.IUserService;
import com.jnu.example.core.constant.enums.UserTypeEnum;
import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.exception.BusinessException;
import com.jnu.example.core.pojo.PageData;
import com.jnu.example.core.util.JnuEncryptUtil;
import com.jnu.example.core.util.JnuMybatisPlusPageUtil;
import com.jnu.example.db.entity.BlogUser;
import com.jnu.example.db.service.IBlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  @Author: zy
 *  @Date: 2020/4/14 22:43
 *  @Description: 用户业务逻辑
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IBlogUserService blogUserService;

    /**
     * @author: zy
     * @description: 新增用户信息
     * @date: 2020/4/14 23:38
     * @param userAddRequestDTO: 待新增的用户信息
     * @return BlogUser:
     */
    @Override
    public BlogUser insertUser(UserAddRequestDTO userAddRequestDTO) {
        //判断用户名是否存在
        if(blogUserService.count(Wrappers.<BlogUser>lambdaQuery().eq(BlogUser::getUsername,userAddRequestDTO.getUsername()))>0){
            throw new BusinessException(ResponseCode.USER_ACCOUNT_ALREADY_EXIST);
        }

        //创建插入实体
        BlogUser user = new BlogUser();
        BeanUtil.copyProperties(userAddRequestDTO, user);

        String rawPassword = user.getPassword();
        user.setPassword(JnuEncryptUtil.encryptToBase64(rawPassword));

        //新增用户
        blogUserService.save(user);
        user.setPassword(null);
        return user;
    }

    /**
     * @Description: 根据用户id删除用户
     * @Author: zy
     * @Date: 2020/5/10 18:17
     * @param userId: 用户id
     * @Return Boolean:
     * @Exception :
     */
    @Override
    public Boolean deleteUser(Integer userId) {
        return blogUserService.removeById(userId);
    }

    /**
     * @Description: 更新用户信息
     * @Author: zy
     * @Date: 2020/5/10 17:47
     * @param userUpdateRequestDTO:待更新信息
     * @Return BlogUser:
     * @Exception :
     */
    @Override
    public BlogUser updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        //创建插入实体
        BlogUser user = new BlogUser();
        BeanUtil.copyProperties(userUpdateRequestDTO, user);

        String rawPassword = user.getPassword();
        if(StrUtil.isNotBlank(rawPassword)) {
            user.setPassword(JnuEncryptUtil.encryptToBase64(rawPassword));
        }

        //更新用户
        blogUserService.updateById(user);
        user.setPassword(null);
        return user;
    }

    /**
     * @author: zy
     * @description: 分页查询  获取用户列表
     * @date: 2020/4/15 0:30
     * @param current: 页码
     * @param pageSize: 每页记录数
     * @param all: 查询所有？ 默认查询全部 如果为true，pageNum和pageSize参数无效
     * @param username: 用户名
     * @param userType: 用户类型
     * @param createAtStart: 创建时间
     * @param createAtEnd: 创建时间
     * @return PageData<BlogUser>:
     */
    @Override
    public PageData<BlogUser> getUserList(Long current, Long pageSize, Boolean all, String username, UserTypeEnum userType, String createAtStart,String createAtEnd) {
        //条件
        LambdaQueryWrapper<BlogUser> queryWrapper = Wrappers.<BlogUser>lambdaQuery().orderByDesc(BlogUser::getId);
        //生成分页参数
        Page<BlogUser> page = JnuMybatisPlusPageUtil.getPage(current, pageSize, all);

        //用户名
        if(StrUtil.isNotBlank(username)){
            queryWrapper = queryWrapper.and(i ->i.like(BlogUser::getUsername,username));
        }

        //用户类型
        if(userType != null){
            if(userType == UserTypeEnum.GITHUB_USER) {
                queryWrapper = queryWrapper.isNotNull(BlogUser::getGithub);
            }
            if(userType == UserTypeEnum.SITE_USER){
                queryWrapper = queryWrapper.isNull(BlogUser::getGithub);
            }
        }

        //创建时间范围
        if(createAtStart != null && createAtEnd != null){
            //这里使用了between 左闭右开 所有+1天
            queryWrapper = queryWrapper.between(BlogUser::getCreatedAt,
                    LocalDate.parse(createAtStart,DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(createAtEnd,DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1));
        }

        //查询
        IPage<BlogUser> userPage = blogUserService.page(page, queryWrapper);

        //如果为空
        if(CollUtil.isEmpty(userPage.getRecords())){
            return new PageData<>(userPage);
        }

        //密码解密
        for(BlogUser user:userPage.getRecords()){
            user.setPassword(JnuEncryptUtil.decryptFromBase64(user.getPassword()));
        }

        return new PageData<>(userPage);
    }

}
