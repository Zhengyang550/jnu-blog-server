package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogRole;
import com.jnu.example.db.mapper.BlogRoleMapper;
import com.jnu.example.db.service.IBlogRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-18
 */
@Service
public class BlogRoleServiceImpl extends ServiceImpl<BlogRoleMapper, BlogRole> implements IBlogRoleService {

}
