package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.获取请求路径
//        String requestURI = request.getRequestURI();
//        //2.判断是否是登录请求，如果路径中包含/login，则放行
//        if (requestURI.contains("/login")){
//            log.info("登录请求，放行");
//            return true;
//        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否为空，如果为空，则提示用户登录
        if(token==null||token.isEmpty()){
            log.info("token为空，请登录");
            response.setStatus(401);
            return false;
        }
        //5.如果不为空，则解析token，如果解析失败，则提示用户登录
        try {

            JwtUtils.parseToken(token);
            Integer empId=Integer.valueOf(JwtUtils.parseToken(token).get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工ID：{}，将其存入ThreadLocal",empId);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        //6.如果解析成功，则放行
        log.info("token解析成功，放行");
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();
    }
}
