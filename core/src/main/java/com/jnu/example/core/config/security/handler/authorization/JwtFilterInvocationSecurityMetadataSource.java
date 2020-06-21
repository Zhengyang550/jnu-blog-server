package com.jnu.example.core.config.security.handler.authorization;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.jnu.example.db.entity.BlogPrivilege;
import com.jnu.example.db.service.IBlogPrivilegeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 *  @Author: zy
 *  @Date: 2020/4/15 23:09
 *  @Description: 权限资源管理器，查询数据库获取请求的url需要的权限 为权限决策管理器提供支持
 *  1、拦截到当前的请求，并根据请求路径从数据库中查出当前资源路径需要哪些权限
 *  2、然后将查出的需要的权限列表交给AccessDecisionManager去处理后续逻辑
 */
@Configuration
@Data
public class JwtFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    IBlogPrivilegeService blogPrivilegeService;

    /*
     * 保存url和权限配置信息  如{"/login":"permitAll",...}
     */
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    /*
     * 获取当前请求路径在spring security配置文件中配置的权限
     */
    public Collection<ConfigAttribute> getSpringSecurityConfigAttributes(HttpServletRequest request) {
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            if (entry.getKey().matches(request)) {
                //如果存在"permitAll" 放行
                Collection<ConfigAttribute> attributes = entry.getValue();
                for(ConfigAttribute attribute:attributes){
                    if("permitAll".equals(attribute.toString())){
                        return new ArrayList<>();
                    }
                }
                //返回一个新副本 不然后面会修改原引用
                return new ArrayList(entry.getValue());
            }
        }
        return new ArrayList<>();
    }

    /*
     * 1、获取当前路径url，然后查询数据库获取所需要权限
     * 2、获取spring security中配置类的权限
     * 3、进行叠加，就是当前url需要的权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o)  {
        HttpServletRequest request = ((FilterInvocation) o).getRequest();
        return getAttributes(request);
    }

    /*
     * 同上 只是参数不同
     */
    public Collection<ConfigAttribute> getAttributes(HttpServletRequest request){
        //获取spring security配置类中配置的权限
        Collection<ConfigAttribute> configAttributes = getSpringSecurityConfigAttributes(request);

        //获取请求地址
        String requestUrl =request.getRequestURI();

        //查询具体某个接口的权限
        List<HashMap<String,Object>> privilegeMaps =  blogPrivilegeService.getPrivilegeListByPath(requestUrl);

        //请求路径没有配置权限，表明该请求接口可以任意访问
        if(CollUtil.isEmpty(privilegeMaps )) {
            return configAttributes;
        }

        //遍历权限点
        List<BlogPrivilege> blogPrivileges = new ArrayList<>();
        for (Map<String, Object> item : privilegeMaps) {
            blogPrivileges.add(BeanUtil.mapToBean(item, BlogPrivilege.class,false));
        }

        List<String> attributes = new ArrayList<>(blogPrivileges.size());
        for(BlogPrivilege privilege:blogPrivileges){
            attributes.add(privilege.getPrivilegeCode());
        }

        configAttributes.addAll(SecurityConfig.createList(attributes.toArray(new String[0])));
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
