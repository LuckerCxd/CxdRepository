package cn.cxd.JDKProxy;

import cn.cxd.aspects.StuSeviceAspect;
import cn.cxd.beans.StudentSeviceImpl;
import cn.cxd.impls.IStudentService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class StuSeviceProxyFactory {

    public static IStudentService CreateStudentServiceImplProxy(){
        // 创建target目标对象
        IStudentService studentService = new StudentSeviceImpl();
        //newInstance Proxy对象并返回
        IStudentService stuServiceImplProxy = (IStudentService)Proxy.newProxyInstance(studentService.getClass().getClassLoader(),
                studentService.getClass().getInterfaces(),new StuInvocationHandler(studentService));
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        return stuServiceImplProxy;
    }

}
