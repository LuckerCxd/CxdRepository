package cn.cxd.springAopXmlConfig.aspect;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyMethodBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("MethodBeforeAdvice o : "+o+" method : "+method);
    }
}
