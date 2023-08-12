package com.jirengu.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("generalPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String message = String.format("%s#%s被执行，参数为%s", className, methodName, Arrays.toString(args));
        logger.info(message);
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long interval = end - start;
        message = String.format("执行时间 %d 毫秒", interval);
        logger.debug(message);
        return object;
    }

    @Pointcut("@annotation(com.jirengu.spring.aop.annotation.MyLogAnnotation)")
    public void generalPointCut() {

    }
}
