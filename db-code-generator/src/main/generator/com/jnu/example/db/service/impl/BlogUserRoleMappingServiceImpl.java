package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogUserRoleMapping;
import com.jnu.example.db.mapper.BlogUserRoleMappingMapper;
import com.jnu.example.db.service.IBlogUserRoleMappingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-18
 */
@Service
public class BlogUserRoleMappingServiceImpl extends ServiceImpl<BlogUserRoleMappingMapper, BlogUserRoleMapping> implements IBlogUserRoleMappingService {

}
