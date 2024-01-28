package com.library.management.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogServiceAspect {

    @Pointcut("within(com.library.management.system.controller..*)")
    public void validationPointcut() {
    }

    @Before("validationPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("**** Before LogServiceAspect ***** ");
    }

    @After("validationPointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("*** AfterAdvice LogServiceAspect **** ");
    }

    @AfterReturning("validationPointcut()")
    public void afterReturnAdvice(JoinPoint joinPoint) {
        log.info("*** AfterReturnAdvice LogServiceAspect *** ");
    }

    @Around("validationPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    @AfterThrowing(value = "validationPointcut()", throwing = "ex")
    public Object afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.println("After Throwing exception in method:" + joinPoint.getSignature());
        System.out.println("Exception is:" + ex.getMessage());
        return joinPoint.getTarget();
    }


}
