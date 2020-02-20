package cn.cxd.springAopXmlConfig.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class MyAdvice implements MethodInterceptor  , ThrowsAdvice {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("methodInterceptor before");
        Object reObj = methodInvocation.proceed();
        System.out.println("methodInterceptor after");
        return reObj;
    }





    public void afterThrowing(Exception ex){
        System.out.println("ThrowsAdvice : afterThrowing : "+ex.getMessage());

    }
}

