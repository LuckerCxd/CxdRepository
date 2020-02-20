package cn.cxd.springAopXmlConfig.aspect;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MyMethodAfterReturning implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("AfterReturningMethod o : "+o+" method : "+method + " o1: "+o1);
    }
}
