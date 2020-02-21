package test;

import cn.cxd.springAopXmlConfig.service.IUserService;
import cn.cxd.springAopXmlConfig.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopHalfTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        IUserService userProxy = (IUserService) context.getBean("proxyOrCglib");
        userProxy.addUser();
        System.out.println(userProxy.getClass().getSuperclass());
    }
}
