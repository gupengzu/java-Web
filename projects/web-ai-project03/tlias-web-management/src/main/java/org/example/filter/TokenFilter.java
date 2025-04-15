package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;

import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = httpServletRequest.getRequestURI();
        //2.判断是否是登录请求，如果路径中包含/login，则放行
        if (requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.获取请求头中的token
        String token = httpServletRequest.getHeader("token");
        //4.判断token是否为空，如果为空，则提示用户登录
        if(token==null||token.isEmpty()){
            log.info("token为空，请登录");
            httpServletResponse.setStatus(401);
            return;
        }
        //5.如果不为空，则解析token，如果解析失败，则提示用户登录
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            httpServletResponse.setStatus(401);
            return;
        }
        //6.如果解析成功，则放行
        log.info("token解析成功，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
