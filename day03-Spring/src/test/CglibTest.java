package test;

import cn.cxd.CGLIBProxy.StuSeviceCglibFactory;
import cn.cxd.beans.StudentSeviceImpl;
import cn.cxd.impls.IStudentService;
import org.springframework.cglib.core.DebuggingClassWriter;

public class CglibTest {
    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/cglib");

        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IStudentService studentService = StuSeviceCglibFactory.createCglibObject();
        studentService.addStudentUser();
        System.out.println("object class: "+ studentService.getClass());
        System.out.println("cglib super class :"+ studentService.getClass().getSuperclass());

    }
}
