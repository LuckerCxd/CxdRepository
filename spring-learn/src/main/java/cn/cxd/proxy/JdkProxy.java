package cn.cxd.proxy;

import cn.cxd.service.Calculator;
import cn.cxd.service.CalculatorService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:30 2020/5/8
 * @Modified By:
 */
public class JdkProxy {
    private CalculatorService target;

    public JdkProxy(CalculatorService target) {
        this.target = target;
    }

    public Object getProxyObject(){
         return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(simpleDateFormat.format(new Date()));
                int result = (int)method.invoke(target,objects);
                System.out.println(method.getName()+" args: "+objects[0] + " , "+objects[1]+" has complete :"+result);
                return result;
            }
        });
    }
}
