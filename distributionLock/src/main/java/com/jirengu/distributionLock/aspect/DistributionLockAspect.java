package com.jirengu.distributionLock.aspect;

import com.jirengu.distributionLock.annotation.DistributionLockAnnotation;
import com.jirengu.distributionLock.exception.CannotAcquireLockException;
import com.jirengu.distributionLock.lock.MyDistributionLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.UUID;


@Aspect
@Component
public class DistributionLockAspect {

    @Resource
    private MyDistributionLock myDistributionLock;

    @Around("generalPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取锁相关参数
        LockParam lockParam = getLockKey(joinPoint);
        String lockKey = lockParam.getLockKey();
        long expireTime = lockParam.getExpireTime();
        long waitTime = lockParam.getWaitTime();
        String value = UUID.randomUUID().toString();
        if (myDistributionLock.tryLock(lockKey, value, expireTime, waitTime)) {
            // 获取到锁，执行业务逻辑
            try {
                return joinPoint.proceed();
            } finally {
                // 解锁
                myDistributionLock.unlock(lockKey, value);
            }
        } else {
            throw new CannotAcquireLockException("未获取到分布式锁");
        }
    }

    private LockParam getLockKey(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入方法的对象
        Method method = signature.getMethod();
        //获取方法上的Aop注解
        DistributionLockAnnotation annotation = method.getAnnotation(DistributionLockAnnotation.class);
        //获取注解上的值
        String keyEl = annotation.key();
        //将注解的值中的El表达式部分进行替换
        //创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        //获取表达式
        Expression expression = parser.parseExpression(keyEl);
        //设置解析上下文(有哪些占位符，以及每种占位符的值)
        EvaluationContext context = new StandardEvaluationContext();
        //获取参数值
        //获取运行时参数的名称
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i].toString());
        }
        //解析, 获取替换后的结果
        String lockKey = expression.getValue(context).toString();
        long expireTime = annotation.expireTime();
        long waitTime = annotation.waitTime();
        return new LockParam(lockKey, expireTime, waitTime);
    }

    private class LockParam {

        private String lockKey;

        private long expireTime;

        private long waitTime;

        public LockParam(String lockKey, long expireTime, long waitTime) {
            this.lockKey = lockKey;
            this.expireTime = expireTime;
            this.waitTime = waitTime;
        }

        public String getLockKey() {
            return lockKey;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public long getWaitTime() {
            return waitTime;
        }
    }

    @Pointcut(value = "@annotation(com.jirengu.distributionLock.annotation.DistributionLockAnnotation)")
    public void generalPointCut() {

    }

}
