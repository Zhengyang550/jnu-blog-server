package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogPrivilege;
import com.jnu.example.db.mapper.BlogPrivilegeMapper;
import com.jnu.example.db.service.IBlogPrivilegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Service
public class BlogPrivilegeServiceImpl extends ServiceImpl<BlogPrivilegeMapper, BlogPrivilege> implements IBlogPrivilegeService {
    @Override
    public List<HashMap<String,Object>> getPrivilegeListByUserId(Integer userId) {
        return baseMapper.getPrivilegeListByUserId(userId);
    }

    @Override
    public List<HashMap<String, Object>> getPrivilegeListByPath(String path) {
        return baseMapper.getPrivilegeListByPath(path);
    }
}
