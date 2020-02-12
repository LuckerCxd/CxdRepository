package test;

import cn.cxd.beans.ManagerImpl;
import cn.cxd.impl.IManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ManagerTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        IManager manager = (ManagerImpl)context.getBean("manager10");
        System.out.println(manager);
    }
}
