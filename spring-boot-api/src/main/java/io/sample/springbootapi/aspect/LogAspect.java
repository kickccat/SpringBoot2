package io.sample.springbootapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LogAspect {
    
    @Pointcut("execution(* io.sample.springbootapi.api.*.*(..))")
    public void logger() {
    
    }
    
    @Before("logger()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        RequestLog requestLog = new RequestLog(request.getRequestURL().toString(), request.getRemoteAddr(), classMethod, joinPoint.getArgs());
        
        log.info("Request: {}", requestLog);
    }
    
    @After("logger()")
    public void doAfter() {
//        log.info("----------------------------------- doAfter ----------------------------------");
    }
    
    @AfterReturning(pointcut = "logger()", returning = "result")
    public void doAfterReturning(Object result) {
        log.info("Return Content: {}", result);
    }
    
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    
        RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
    
        @Override
        public String toString() {
            return "RequestLog{" +
                           "url='" + url + '\'' +
                           ", ip='" + ip + '\'' +
                           ", classMethod='" + classMethod + '\'' +
                           ", args=" + Arrays.toString(args) +
                           '}';
        }
    }
}
