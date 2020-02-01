package cn.cxd.beanLives;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("7-1.BeanPostProcessor(postProcessBeforeInitialization)object:"+o+",s:"+s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("10-1.BeanPostProcessor(postProcessAfterInitialization):object:"+o+",s:"+s);
        return o;
    }
}
