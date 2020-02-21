package test;


import cn.cxd.impls.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoAopTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans03.xml");
        IUserService userService = context.getBean(IUserService.class);
        userService.addUser();
    }
}
