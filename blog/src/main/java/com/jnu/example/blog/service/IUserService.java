package com.jnu.example.blog.service;


import com.jnu.example.core.constant.enums.UserTypeEnum;
import com.jnu.example.db.pojo.dto.UserAddRequestDTO;
import com.jnu.example.db.pojo.dto.UserUpdateRequestDTO;
import com.jnu.example.core.pojo.PageData;
import com.jnu.example.db.entity.BlogUser;


/**
 *  @Author: zy
 *  @Date: 2020/4/14 22:43
 *  @Description: 用户接口
 */
public interface IUserService {
    BlogUser insertUser(UserAddRequestDTO userAddRequestDTO);
    Boolean deleteUser(Integer userId);
    BlogUser updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    PageData<BlogUser> getUserList(Long current, Long pageSize, Boolean all, String username, UserTypeEnum userType, String createAtStart, String createAtEnd);
}
