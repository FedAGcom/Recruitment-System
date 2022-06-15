package com.fedag.recruitmentSystem.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExceptionAspect {

    @Pointcut("execution(* com.fedag.recruitmentSystem.service..* (..))")
    private void AllExceptionEntityService() {
    }

    @Pointcut("execution(* com.fedag.recruitmentSystem.security.SecurityService..* (..))")
    private void AllExceptionSecurityService() {
    }

    @Pointcut("execution(* com.fedag.recruitmentSystem.security.jwt.JwtTokenProvider.validateToken (String))")
    private void AllJWTException() {
    }

    @Around("execution(* com.fedag.recruitmentSystem.service..* (..))")
    public void LoggingAllExceptionEntityServiceAdvice(ProceedingJoinPoint joinPoint)
            throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        try {
            joinPoint.proceed();
        } catch (Throwable exception) {
            log.error("Было выброшено исключение " + exception.getClass().getSimpleName()
                    + "(" + exception.getMessage() + ")"
                    + " из метода " + methodSignature.getName()
                    + " из пакета " + methodSignature.getDeclaringTypeName());
        }
    }

    @AfterThrowing(pointcut = "AllExceptionSecurityService() || AllJWTException()",
            throwing = "exception")
    public void LoggingAllExceptionSecurityServiceAdvice(JoinPoint joinPoint, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.error("Было выброшено исключение " + exception.getClass().getSimpleName()
                + "(" + exception.getMessage() + ")"
                + " из метода " + methodSignature.getName()
                + " из пакета " + methodSignature.getDeclaringTypeName());
    }

//    @AfterThrowing(pointcut = "execution(* com.fedag.recruitmentSystem.security.jwt.JwtTokenProvider.validateToken (String))",
//            throwing = "exception")
//    public void LoggingAllJWTExceptionAdvice(JoinPoint joinPoint, Throwable exception){
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        log.error("Было выброшено исключение " + exception.getClass().getSimpleName()
//                + "(" + exception.getMessage() + ")"
//                + " из метода " + methodSignature.getName()
//                + " из пакета " + methodSignature.getDeclaringTypeName());
//    }
}
