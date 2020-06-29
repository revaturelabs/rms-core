package com.revature.rms.core.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Manages the logging aspect of microservices. Currently set up to log method invocations,
 * successful completion of methods, and the throwing of exceptions during method execution.
 *
 * @author Wezley Singleton (Github: wsingleton)
 */
@Aspect
public abstract class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This pointcut matching to all joinpoints in the com.revature.rms package and all of its
     * sub-packages.
     */
    @Pointcut("within(com.revature.rms..*)")
    public void logAll() {}

    /**
     * Logs basic information when a method is invoked but before its execution is allowed.
     * Information logged includes the qualified name of the method invoked and the local time of its
     * invocation. Values of the input arguments are also present for debugging purposes.
     *
     * @param jp - A point during the execution of the program
     */
    @Before("logAll()")
    public void logMethodStart(JoinPoint jp) {
        String methodSig = jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
        String argStr = Arrays.toString(jp.getArgs());
        logger.info("{} invoked at {}", methodSig, LocalTime.now());
        logger.info("Input arguments: {}", argStr);
    }

    /**
     * Logs basic information after a method successfully returns. Information logged includes the
     * qualified name of the method invoked and the local time of its successful completion. The value
     * of the returned object is present as well for debugging purposes.
     *
     * @param jp - A point during the execution of the program
     * @param returned - The object that will be returned by the method being advised
     */
    @AfterReturning(pointcut = "logAll()", returning = "returned")
    public void logMethodReturn(JoinPoint jp, Object returned) {
        String methodSig = jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
        logger.info("{} successfully returned at {}", methodSig, LocalTime.now());
        logger.info("Object returned: {}", returned);
    }

    /**
     * Logs basic information when a method throws any exception. Information logged includes the
     * qualified name of the method invoked and the local time when the exception was thrown.
     *
     * @param jp - A point during the execution of the program
     * @param e - The exception that was thrown by the method being advised
     */
    @AfterThrowing(pointcut = "logAll()", throwing = "e")
    public void errorOccurrence(JoinPoint jp, Exception e) {
        String methodSig = jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
        logger.info("{} thrown in method: {}", e.getMessage(), methodSig);
    }

}

