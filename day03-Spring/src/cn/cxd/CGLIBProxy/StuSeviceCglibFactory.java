package cn.cxd.CGLIBProxy;

import cn.cxd.beans.StudentSeviceImpl;
import cn.cxd.impls.IStudentService;
import org.springframework.cglib.proxy.Enhancer;

public class StuSeviceCglibFactory {
    public static IStudentService createCglibObject(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentSeviceImpl.class);
        enhancer.setCallback(new StuMethodInterceptor());
        IStudentService reObj = (IStudentService)enhancer.create();
        return reObj;
    }
}
