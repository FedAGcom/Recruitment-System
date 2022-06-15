package com.fedag.recruitmentSystem.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.fedag.recruitmentSystem.service.impl..* (..))")
    private void allServiceMethods() {
    }

    @Before("allServiceMethods()")
    public void beforeLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Начинается выполнение метода " +
                methodSignature.getName() + " из пакета "
                + methodSignature.getDeclaringTypeName());
    }

    @After("allServiceMethods()")
    public void afterLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Метод " + methodSignature.getName() +
                " из пакета "
                + methodSignature.getDeclaringTypeName() + " выполнен");
    }
}
