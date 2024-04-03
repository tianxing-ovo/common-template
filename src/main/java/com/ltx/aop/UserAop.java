package com.ltx.aop;

import com.ltx.annotation.PreAuthorize;
import com.ltx.entity.User;
import com.ltx.exception.CustomException;
import com.ltx.util.ThreadLocalUtil;
import io.github.tianxingovo.enums.ErrorCodeEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面类
 * order值小的切面先执行
 */
@Aspect
@Component
@Order(1)
public class UserAop {

    /**
     * 切入点
     */
    @Pointcut("execution(public com.ltx.entity.User com.ltx.aop.UserService.main(String))")
    public void pointcut() {

    }

    /**
     * 前置通知: 在目标方法执行前执行
     */
    @Before("pointcut()")
    public void before() {
        System.out.println("Before Aop");
    }

    /**
     * 前置通知: 拦截带有指定注解的方法
     */
    @Before("@annotation(preAuthorize)")
    public void beforeAnnotation(JoinPoint joinPoint, PreAuthorize preAuthorize) {
        // 获取请求参数数组
        Object[] args = joinPoint.getArgs();
        String[] roles = preAuthorize.hasAnyRole();
        User user = ThreadLocalUtil.get();
        user.setId((Integer) args[0]);
        String role = user.getRole();
        boolean b = Arrays.asList(roles).contains(role);
        if (!b) {
            throw new CustomException(ErrorCodeEnum.UNAUTHORIZED.getCode(), ErrorCodeEnum.UNAUTHORIZED.getMessage());
        }
    }

    /**
     * 后置通知: 在目标方法执行后执行,方法没有返回值时使用
     */
    @After("pointcut()")
    public void after() {
        System.out.println("After Aop");
    }

    /**
     * 返回通知: 在目标方法返回结果后执行,方法有返回值时使用
     */
    @AfterReturning(value = "pointcut()", returning = "user")
    public void afterReturning(User user) {
        // 修改返回值
        user.setName("张三");
        System.out.println("AfterReturning Aop");
    }

    /**
     * 异常通知: 在目标方法抛出异常后执行
     */
    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println("AfterThrowing Aop");
    }

    /**
     * 环绕通知: 在目标方法执行前后都执行
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
