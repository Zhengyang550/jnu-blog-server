package com.jnu.example.core.config.security.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.jnu.example.core.util.JnuSpringContextUtil;
import com.jnu.example.db.entity.BlogPrivilege;
import com.jnu.example.db.entity.BlogUser;
import com.jnu.example.db.service.IBlogPrivilegeService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

/**
 * Author: zy
 * Description: 定义满足Spirng Security规则的用户实体类
 * Date: 2020/4/15
 */
public class JwtUserDetails extends BlogUser implements UserDetails {
    private static IBlogPrivilegeService blogPrivilegeService;

    static{
        blogPrivilegeService = JnuSpringContextUtil.getBean(IBlogPrivilegeService.class);
    }

    public JwtUserDetails(BlogUser blogUser){
        BeanUtil.copyProperties(blogUser,this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //获取用户权限
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<HashMap<String,Object>> privilegeMaps = blogPrivilegeService.getPrivilegeListByUserId(id);
        if(CollUtil.isEmpty(privilegeMaps)) {
            return grantedAuthorities;
        }

        //遍历权限点
        List<BlogPrivilege> blogPrivileges = new ArrayList<>();
        for (Map<String, Object> item : privilegeMaps) {
            blogPrivileges.add(BeanUtil.mapToBean(item, BlogPrivilege.class,false));
        }

        //声明用户授权
        blogPrivileges.forEach(blogPrivilege -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(blogPrivilege.getPrivilegeCode());
            grantedAuthorities.add(grantedAuthority);
        });
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
