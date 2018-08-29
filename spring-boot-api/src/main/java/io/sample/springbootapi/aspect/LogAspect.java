package io.sample.springbootapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    
    @Pointcut("execution(* io.sample.springbootapi.api.LogTestAPI.log(..))")
    public void logger() {
    
    }
    
    @Before("logger()")
    public void doBefore() {
        log.info("--------------------------------- doBefore -----------------------------------");
    }
    
    @After("logger()")
    public void doAfter() {
        log.info("----------------------------------- doAfter ----------------------------------");
    }
    
    @AfterReturning(pointcut = "logger()", returning = "result")
    public void doAfterReturning(Object result) {
        log.info("--------------- doAfterReturning ---------------- Content: {}", result);
    }
}
