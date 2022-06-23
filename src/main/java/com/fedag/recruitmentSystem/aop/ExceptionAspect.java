package com.fedag.recruitmentSystem.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExceptionAspect {

    @Pointcut("execution(* com.fedag.recruitmentSystem.service..* (..))")
    private void allExceptionEntityService() {
    }

    @Pointcut("execution(* com.fedag.recruitmentSystem.security.SecurityService..* (..))")
    private void allExceptionSecurityService() {
    }

    @Pointcut("execution(* com.fedag.recruitmentSystem.security.jwt.JwtTokenProvider.validateToken (String))")
    private void allJWTException() {
    }

    @Pointcut("execution(* com.fedag.recruitmentSystem.email.MailSendlerService..* (..))")
    private void allEmailServiceMethods() {
    }

    @AfterThrowing(pointcut = "allExceptionSecurityService() || allJWTException() " +
            "|| allExceptionEntityService() || allEmailServiceMethods()",
            throwing = "exception")
    public void LoggingAllExceptionSecurityServiceAdvice(JoinPoint joinPoint, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.error("Было выброшено исключение " + exception.getClass().getSimpleName()
                + "(" + exception.getMessage() + ")"
                + " из метода " + methodSignature.getName()
                + " из пакета " + methodSignature.getDeclaringTypeName());
    }

}
