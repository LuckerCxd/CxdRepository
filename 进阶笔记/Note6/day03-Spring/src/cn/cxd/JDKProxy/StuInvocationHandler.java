package cn.cxd.JDKProxy;

import cn.cxd.aspects.StuSeviceAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StuInvocationHandler implements InvocationHandler {
    private Object target;

    public StuInvocationHandler(Object target) {
        this.target = target;
    }


/*
    o : $proxy0,调用方法时会回到父类设置的invocationHandler，
        因为调用super.invocationHandler.invoke(this,args)，也就是调用当前的invoke(子类对象，参数)
        如果此时在其中再调用子类对象的相关方法就递归了，所以应该使用target目标类对象。
    method : $proxy0.method
    objects : $proxy0.实现了的接口方法的参数传入
*/

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("StuInvocationHandler完成拦截, " + " object : " + o.getClass());
        StuSeviceAspect.before();
        Object reObj = method.invoke(target,objects);
        StuSeviceAspect.after();
        return reObj;
    }
}
