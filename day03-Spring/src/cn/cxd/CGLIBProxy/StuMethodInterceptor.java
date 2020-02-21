package cn.cxd.CGLIBProxy;

import cn.cxd.annoaop.aspects.StuSeviceAspect;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class StuMethodInterceptor implements MethodInterceptor {
    /*

    Object为由CGLib动态生成的代理类实例
    Method为上文中实体类所调用的被代理的方法引用
    Object[]为参数值列表
    MethodProxy为生成的代理类对方法的代理引用。

    o : 修改了字节码的业务子类
    method : method引用
    objects : 子类的method.重写了的方法的传入参数
    MethodProxy为生成的代理类对方法的代理引用。

     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("StuMethodInterceptor完成拦截, " + " object : " + o.getClass());
        StuSeviceAspect.before();
        Object reObj = methodProxy.invokeSuper(o,objects);
        System.out.println("method is : "+method);
        System.out.println("methodProxy is : "+methodProxy);
        //Object reObj = methodProxy.invoke(o, objects);
        StuSeviceAspect.after();
        return reObj;
    }
}
