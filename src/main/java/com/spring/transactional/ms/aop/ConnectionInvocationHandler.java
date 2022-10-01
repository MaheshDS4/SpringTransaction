package com.spring.transactional.ms.aop;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

@Slf4j
public class ConnectionInvocationHandler implements InvocationHandler {

    private final Connection connection;

    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().contains("commit") || method.getName().contains("rollback") || method.getName().contains("close")) {
            log.info("Connection Trace : {} ", method.toGenericString());
        }
        Object returnValue = method.invoke(connection, args);
        return returnValue;
    }
}
