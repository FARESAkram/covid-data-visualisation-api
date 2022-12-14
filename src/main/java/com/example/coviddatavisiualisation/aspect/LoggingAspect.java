package com.example.coviddatavisiualisation.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.example.coviddatavisiualisation.service.*.*(..))")
    public void serviceLayer() {
    }
    @Pointcut("execution(* com.example.coviddatavisiualisation.dao.*.*(..))")
    public void daoLayer() {
    }
    @Pointcut("execution(* com.example.coviddatavisiualisation.repository.*.*(..))")
    public void repositoryLayer() {
    }

    @Pointcut("serviceLayer() || daoLayer() || repositoryLayer()")
    public void allLayers() {
    }

    @Around("allLayers()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable
    {
        try
        {
            logger.info("Entering " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "with arguments: " + Arrays.toString(joinPoint.getArgs()));
            Object result = joinPoint.proceed();
            logger.info("Exiting " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " with result: " + result);
            return result;
        }
        catch (Throwable e)
        {
            logger.error("Error in " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " : " + e.getMessage());
            throw e;
        }
        finally
        {
            logger.info("End of " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        }
    }
}
