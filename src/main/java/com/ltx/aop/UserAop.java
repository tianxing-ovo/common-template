package com.ltx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */
@Aspect
@Component
public class UserAop {

    /**
     * 切入点
     */
    @Pointcut("execution(public String com.ltx.aop.UserService.main(String))")
    public void pointcut() {

    }

    /**
     * 前置通知:在目标方法执行前执行
     */
    @Before("pointcut()")
    public void before() {
        System.out.println("Before Aop");
    }

    /**
     * 后置通知:在目标方法执行后执行，方法没有返回值时使用
     */
    @After("pointcut()")
    public void after() {
        System.out.println("After Aop");
    }

    /**
     * 返回通知:在目标方法返回结果后执行，方法有返回值时使用
     */
    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("AfterReturning Aop");
    }

    /**
     * 异常通知:在目标方法抛出异常后执行
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("AfterThrowing Aop");
    }

    /**
     * 环绕通知:在目标方法执行前后都执行
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行目标方法之前的操作
        System.out.println("Before Around AOP: " + joinPoint.getSignature().getName());
        // 执行目标方法并获取返回值
        Object o = joinPoint.proceed();
        // 执行目标方法之后的操作
        System.out.println("After Around AOP: " + joinPoint.getSignature().getName());
        // 返回结果
        return o;
    }
}
