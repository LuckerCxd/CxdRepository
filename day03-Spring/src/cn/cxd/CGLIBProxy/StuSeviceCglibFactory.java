package cn.cxd.CGLIBProxy;

import cn.cxd.aspects.StuSeviceAspect;
import cn.cxd.beans.StudentSeviceImpl;
import cn.cxd.impls.IStudentService;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class StuSeviceCglibFactory {
    public static IStudentService createCglibObject(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentSeviceImpl.class);
        enhancer.setCallback(new StuMethodInterceptor());
        IStudentService reObj = (IStudentService)enhancer.create();
        return reObj;
    }
}
