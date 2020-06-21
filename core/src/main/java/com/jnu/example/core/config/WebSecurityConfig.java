package com.jnu.example.core.config;

import com.jnu.example.core.config.security.CustomizedPasswordEncoder;
import com.jnu.example.core.config.security.handler.authentication.JwtAuthenticationEntryPoint;
import com.jnu.example.core.config.security.handler.authentication.JwtAuthenticationTokenFilter;
import com.jnu.example.core.config.security.handler.authorization.JwtAccessDecisionManager;
import com.jnu.example.core.config.security.handler.authorization.JwtAccessDeniedHandler;
import com.jnu.example.core.config.security.handler.authorization.JwtFilterInvocationSecurityMetadataSource;
import org.jooq.tools.reflect.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Author: zy
 * Description: 配置Spring Security
 * Date: 2020/4/15
 * 1、SpringBoot+SpringSecurity+jwt整合及初体验：https://www.cnblogs.com/pjjlt/p/10960690.html
 * 2、Spring Security基本原理：https://www.cnblogs.com/zyly/p/12286285.html#_label2
 * 3、Spring Security 实现 antMatchers 配置路径的动态获取：https://baijiahao.baidu.com/s?id=1619832433443852458&wfr=spider&for=pc 视频不错
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //处理用户认证的时候出现错误时抛出的异常
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    //处理用户在访问受保护资源时被拒绝而抛出的异常
    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    //使用token进行身份认证
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //权限资源元数据
    @Autowired
    JwtFilterInvocationSecurityMetadataSource jwtFilterInvocationSecurityMetadataSource;

    //访问决策管理器
    @Autowired
    JwtAccessDecisionManager jwtAccessDecisionManager;

    /**
     *  @Author: zy
     *  @Date: 2020/4/15 21:42
     *  @Description: 将加密对象注入到Spring IOC容器中
     */
    @Bean
    public CustomizedPasswordEncoder passwordEncoder() {
        return new CustomizedPasswordEncoder();
    }

    /**
     *  @Author: zy
     *  @Date: 2020/4/15 21:42
     *  @Description: 将认证管理器注入到Spring IOC容器中
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @author: zy
     * @description: 配置不同类型的安全配置器，每个安全过滤器对应一个或者若干个过滤器
     * @date: 2020/4/15 10:56
     * @param http: HTTP请求安全处理
     * @return void:
     *
     *   1、如果访问/login,首先被jwtAuthenticationTokenFilter过滤器拦截，判断是否携带token，如果没有token，放行
     *      执行到jwtFilterSecurityInterceptor进行权限校验，首先查询用户访问的url需要哪些资源，然后获取用户的权限
     *      由于用户没有登录，所以用户权限为null，但是当Url需要权限也为null时，就会放行  因此为了避免url被匿名用户
     *      访问，最好给每个url分配权限
     *
     *      这里采用了一种特殊的手段，获取配置类中配置的每个url对应的权限，然后和数据库中配置的叠加，最终为url所需的权限
     *      如果时permitAll直接放行；如果是authenticated，校验用户是否为匿名访问，如果是拒绝，如果不是权限对比
     *
     *  2、当一个非/login请求过来，首先被jwtAuthenticationTokenFilter过滤器拦截，判断是否携带token，如果没有token，放行
     *     执行到jwtFilterSecurityInterceptor进行权限校验，同1...
     *
     *  3、如果携带token，首先校验token，校验通过，保存用户信息及权限信息，然后放行
     *     执行到jwtFilterSecurityInterceptor进行权限校验，同1...
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭CSRF保护
        http.csrf().disable();

        //禁用缓存
        http.headers().cacheControl();

        //在身份鉴定的实现中，传统的方法是在服务端存储一个 session，给客户端返回一个 cookie，而使用JWT之后，当用户使用它的认证信息登录系统之后，会返回给用户一个JWT， 用户只需要本地保存该 token（通常使用localStorage，也可以使用cookie）即可。
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //对Spring Security认证授权相关的异常进行统一的自定义处理  Spring Security内部异常不会被全局异常捕获
        http.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        /*
          配置用于权限校验的安全配置器 对应FilterSecurityInterceptor过滤器
          FilterSecurityInterceptor过滤器是Spring Security过滤器链条中的最后一个过滤器
          它的任务是来最终决定一个请求是否可以被允许访问
          权限校验步骤：
          * 1、调用jwtFilterInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
          * 2、调用jwtAccessDecisionManager的decide方法来校验用户的权限是否足够
         */
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/github").permitAll()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/null/swagger-resources/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/favicon.ico","/css/**","/js/**").permitAll()
                .antMatchers("/article/**").permitAll()
                .antMatchers("/tag/**").permitAll()
                .anyRequest().authenticated()
                //配置后置处理器 所有请求都需要经过这个后置处理器处理
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    //参数o就是原始的FilterSecurityInterceptor 对象，拿到它然后对它进行操作，替换掉原有的 SecurityMetadataSource 对象
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        //获取默认的metadataSource
                        SecurityMetadataSource metadataSource = o.obtainSecurityMetadataSource();
                        //利用反射获取私有字段值
                        Reflect reflect = Reflect.on(metadataSource);
                        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap =  reflect.field("requestMap").get();
                        //保存原原有的权限配置
                        jwtFilterInvocationSecurityMetadataSource.setRequestMap(requestMap);
                        o.setAccessDecisionManager(jwtAccessDecisionManager);
                        o.setSecurityMetadataSource(jwtFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                });

        /*
         添加token身份认证过滤器  配置jwtAuthenticationTokenFilter过滤器在UsernamePasswordAuthenticationFilter之前，
         如果没有配置http.formLogin()最终生成的过滤器链并不会有该该过滤器
         */
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
