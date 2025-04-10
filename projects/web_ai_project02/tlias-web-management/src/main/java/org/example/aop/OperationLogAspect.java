package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(org.example.anno.Log)")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable {
        long startTime=System.currentTimeMillis();

        //执行目标方法
        Object result=pjp.proceed();

        //计算耗时
        long endTime=System.currentTimeMillis();
        long costTime=endTime-startTime;

        //构建日志实体
        OperateLog olog=new OperateLog();
        olog.setOperateEmpId(getCurrentUserId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(pjp.getTarget().getClass().getName());
        olog.setMethodName(pjp.getSignature().getName());
        olog.setMethodParams(Arrays.toString(pjp.getArgs()));
        olog.setReturnValue(result!=null?result.toString():"void");
        olog.setCostTime(costTime);

        //保存日志
        log.info("日志记录:{}",olog);
        operateLogMapper.insert(olog);

        return result;
    }

    private Integer getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }
}
