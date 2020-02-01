package cn.cxd.beanLives;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> aClass, String s) throws BeansException {
        System.out.println("2.InstantiationAwareBeanPostProcessor(postProcessBeforeInstantiation):aClass:"+aClass.toString()+",s:"+s);
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, PropertyDescriptor[] propertyDescriptors, Object o, String s) throws BeansException {
        System.out.println("5.InstantiationAwareBeanPostProcessor(postProcessPropertyValues):propertyValues:"+propertyValues.toString()+",object:"+o+",s:"+s);
        System.out.print("propertyDescriptors:");
        for (PropertyDescriptor p:propertyDescriptors) {
            System.out.print(p.toString());
        }
        System.out.println();
        return propertyValues;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
        System.out.println("4.InstantiationAwareBeanPostProcessor(postProcessAfterInstantiation):object:"+o+",s:"+s);
        return true;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("11.InstantiationAwareBeanPostProcessor(postProcessAfterInitialization):object:"+o+",s:"+s);
        return o;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("8.InstantiationAwareBeanPostProcessor(postProcessBeforeInitialization)object:"+o+",s:"+s);
        return o;
    }

}
