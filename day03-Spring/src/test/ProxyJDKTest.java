package test;

import cn.cxd.JDKProxy.StuSeviceProxyFactory;
import cn.cxd.beans.StudentSeviceImpl;
import cn.cxd.impls.IStudentService;

public class ProxyJDKTest {
    public static void main(String[] args) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IStudentService studentService = StuSeviceProxyFactory.CreateStudentServiceImplProxy();
        studentService.addStudentUser();

        //StudentSeviceImpl studentService2 = (StudentSeviceImpl) studentService;
        //studentService2.checkStudentUser(2);

        System.out.println("proxy class :"+ studentService.getClass());
        //System.out.println("proxy object :"+ studentService);
        System.out.println("proxy super class :"+ studentService.getClass().getSuperclass());


    }
}
