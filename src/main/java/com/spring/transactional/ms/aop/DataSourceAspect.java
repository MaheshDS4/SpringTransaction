package com.spring.transactional.ms.aop;

import com.mysql.cj.jdbc.ConnectionImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ;
        log.info("Datasource tracker : " + proceedingJoinPoint.getSignature());
        Object returnValue= proceedingJoinPoint.proceed();
        log.info("Return Value : " + returnValue);
        if(returnValue instanceof Connection){
            Connection con = (Connection) Proxy.newProxyInstance(ConnectionImpl.class.getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionInvocationHandler((Connection) returnValue));
            return con;
        }
        return returnValue;
    }
}
