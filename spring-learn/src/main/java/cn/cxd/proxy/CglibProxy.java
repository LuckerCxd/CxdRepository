package cn.cxd.proxy;

import cn.cxd.service.CalculatorService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 17:47 2020/5/9
 * @Modified By:
 */
public class CglibProxy {

    public CglibProxy(CalculatorService target) {
        this.target = target;
    }

    private CalculatorService target;

    public Object getProxyObject(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(simpleDateFormat.format(new Date()));
                int result = (int)methodProxy.invokeSuper(o,objects);
                System.out.println(method.getName()+" args: "+objects[0] + " , "+objects[1]+" cglib has complete :"+result);
                return result;
            }
        });
        return enhancer.create();
    }
}
