package com.dtdream.eureka.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RestAspect {

    @Pointcut("execution(public * com.dtdream.eureka.web.*.*(..))")
    public void point() {
    }


    @Before("point()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println( "方法前-------doBefore-------------------" );
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        System.out.println( "url:" + request.getRequestURI() );
        System.out.println( "ip:" + request.getRemoteHost() );
        System.out.println( "method:" + request.getMethod() );
        System.out.println( "class_method:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() );
        System.out.println( "args:" + joinPoint.getArgs() );
    }

    @After("point()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println( "方法执行后---------doAfter------------------" );
    }

    @AfterReturning(returning = "result", pointcut = "point()")
    public void doAfterReturning(Object result) {
        System.out.println( "执行返回值：" + result );
    }
}
