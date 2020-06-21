package com.jnu.example.core.config.security.handler.authorization;

import cn.hutool.core.collection.CollUtil;
import com.jnu.example.core.constant.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Collection;
import java.util.Iterator;

/**
 *  @Author: zy
 *  @Date: 2020/4/15 23:18
 *  @Description: 访问决策管理器 将访问的url所需权限和用户的权限进行比较，如果用户具有该url所需所有权限，授权通过
 */
@Configuration
@Slf4j
public class JwtAccessDecisionManager implements AccessDecisionManager {

    /**
     * @author: zy
     * @description: 判断用户有无权限  必须具有资源的所有权限才可以通过
     * @date: 2020/4/17 17:23
     * @param authentication: 用户及用户权限信息
     * @param o
     * @param attributes: 访问资源需要的权限
     * @return void:
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> attributes){
        //如果访问资源不需要任何权限直接通过
        if(CollUtil.isEmpty(attributes)){
            return;
        }

        //判断用户是不是匿名访问
        if(authentication instanceof AnonymousAuthenticationToken) {
            log.info(ResponseCode.USER_NOT_LOGIN.toString());
            throw new UsernameNotFoundException(ResponseCode.USER_NOT_LOGIN.getMessage());
        }

        //当前用户所具有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //-1 表示忽略"authenticated"权限
        if(authorities.size() < (attributes.size()-1)){
            log.info(ResponseCode.NO_PERMISSION.toString());
            throw new AccessDeniedException(ResponseCode.NO_PERMISSION.getMessage());
        }

        Iterator<ConfigAttribute> iterator = attributes.iterator();

        //循环遍历url资源需要的权限
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.toString();
            //如果所需权限不是配置类中配置的authenticated  anyRequest().authenticated()
            if(!"authenticated".equals(needRole)) {
                //标志位，判断用户是否存在needRole权限
                Boolean hasRole = false;
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(needRole)) {
                        hasRole = true;
                    }
                }
                if (!hasRole) {
                    log.info(ResponseCode.NO_PERMISSION.toString());
                    throw new AccessDeniedException(ResponseCode.NO_PERMISSION.getMessage());
                }
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
