package com.jnu.example.blog.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: zy
 * @Description: 过滤器 用于打印请求信息
 * @Date: 2020/2/24
 */
@Order(1)
@Component
@WebFilter(urlPatterns = "/**/**", filterName = "logFilter")
@Slf4j
public class ConfigLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig)  {
        //不做任何事
    }

    /**
     * @author: zy
     * @description: 过滤器
     * @date: 2020/3/3 8:56
     * @param servletRequest
     * @param servletResponse
     * @param filterChain:
     * @return void:
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        //打印请求信息
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // IP地址
        String ipAddr = getRemoteHost(request);
        String url = request.getRequestURL().toString();
        String reqParam = reqParam(servletRequest);
        log.info("user request information ip:{} url:{} param:{}",ipAddr, url, reqParam);
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("execute cost " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * @author: zy
     * @description: 获取http请求参数
     * @date: 2020/2/28 16:34
     * @param request: 请求信息
     * @return java.lang.String:
    */
    private String reqParam(ServletRequest request) {
        return JSON.toJSONString(request.getParameterMap());
    }

    /**
     * @author: zy
     * @description: 获取访问用户ip地址
     * @date: 2020/2/28 16:36
     * @param request:请求信息
     * @return java.lang.String:
     */
    private String getRemoteHost(HttpServletRequest request) {
        String unkown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unkown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unkown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unkown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    @Override
    public void destroy() {
        //不做任何事
    }
}