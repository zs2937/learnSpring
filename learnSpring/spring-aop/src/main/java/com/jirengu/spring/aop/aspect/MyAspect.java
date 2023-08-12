package com.jirengu.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Before("generalPointCut()")
    public void before() {
        System.out.println("前置通知");
    }

    @AfterReturning("generalPointCut()")
    public void afterReturning() {
        System.out.println("返回通知");
    }


    @Around("generalPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前环绕通知");
        // 执行目标方法
        Object object = joinPoint.proceed();
        System.out.println("后环绕通知");
        return object;
    }

    @AfterThrowing("generalPointCut()")
    public void exception() {
        System.out.println("异常通知");
    }


    @After("generalPointCut()")
    public void after() {
        System.out.println("后置通知");
    }


    @Pointcut("@annotation(com.jirengu.spring.aop.annotation.MyAnnotation)")
    public void generalPointCut() {

    }


}
