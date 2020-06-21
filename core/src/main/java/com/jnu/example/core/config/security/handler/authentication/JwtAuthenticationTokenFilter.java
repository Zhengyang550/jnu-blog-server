package com.jnu.example.core.config.security.handler.authentication;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.jnu.example.core.config.security.JwtTokenUtil;
import com.jnu.example.core.config.security.handler.authorization.JwtFilterInvocationSecurityMetadataSource;
import com.jnu.example.core.config.security.service.JwtUserDetailsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 *  @Author: zy
 *  @Date: 2020/4/16 22:46
 *  @Description: token身份认证过滤器
 */
@Configuration
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    //权限资源元数据
    @Autowired
    JwtFilterInvocationSecurityMetadataSource jwtFilterInvocationSecurityMetadataSource;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)  {
        //获取请求头信息authorization信息
        String authHeader = httpServletRequest.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);

        //获取当前url需要的权限 如果为空，表示访问该url不需要任何权限
        Collection<ConfigAttribute> configAttributes = jwtFilterInvocationSecurityMetadataSource.getAttributes(httpServletRequest);

        /*
          如果不需要任何权限，就不验证token，直接放行
          如果需要权限，并且有token，进行认证，失败抛出异常、认证成功，在上下文中保存authentication并放行
          如果需要权限，但是没有token，也不进行验证，直接放行，由于未认证，在权限校验中判断为匿名登录，并会抛出异常
         */
        if(CollUtil.isNotEmpty(configAttributes)  && StrUtil.isNotBlank(authHeader)) {
            String username = null;
            try {
                //获取用户名
                username = JwtTokenUtil.getUsernameFromToken(authHeader);
            }
            catch(Exception e) {
                log.info(e.getMessage(),e);
                throw new BadCredentialsException(e.getMessage());
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //加载用户信息
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

                //验证这个authToken是否正确，是否在有效期
                if (JwtTokenUtil.validateToken(authHeader, username)) {
                    //加载用户、角色、权限信息，Spring Security根据这些信息进行权限校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    //将主体信息authentication，存入上下文环境，供后面使用
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
