package com.itheima.apo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecordTimeAspect {
    @Around("execution(* com.itheima.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.记录方法运行的开始时间
        long begin=System.currentTimeMillis();
        //2.执行原始的方法
        Object result = pjp.proceed();
        //3.记录方法运行的结束时间
        long end=System.currentTimeMillis();
        System.out.println("方法"+pjp.getSignature()+"运行时间："+(end-begin)+"ms");
        return result;
    }
}
