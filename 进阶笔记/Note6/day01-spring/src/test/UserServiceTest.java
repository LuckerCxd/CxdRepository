package test;

import cn.cxd.impl.IUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import java.lang.reflect.InvocationTargetException;

public class UserServiceTest {
    public static void main(String[] args) {

//一、加载Bean.xml

        // 1. ClassPathXmlApplicationContext  即时加载xml中的所有bean  使用src的相对路径

        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
/*
        IUserService userService1 = (IUserService) context.getBean("UserService");
        userService1.add();
*/


        // 2. FileSystemXmlApplicationContext 即时加载bean  使用绝对路径
/*
        ApplicationContext context1 = new FileSystemXmlApplicationContext("D:\\IDEAProjects\\day01-spring\\src\\beans.xml");
        IUserService userService1 = (IUserService) context1.getBean("UserService");
        userService1.add();
*/


        // 3. BeanFactory 延迟加载，在getBean()才开始加载bean
/*
        BeanFactory  beanFactory = new XmlBeanFactory(new FileSystemResource("D:\\IDEAProjects\\day01-spring\\src\\beans.xml"));
        IUserService userService1 = (IUserService) beanFactory.getBean("UserService");
*/

//二、配置<bean>

        // 1. <bean beanID beanClass> ...getBean(beanID)

/*
        IUserService userService1 = (IUserService) context.getBean("UserService1");
        userService2.add();
*/

        // 2. <bean beanID factoryClass factoryMethod>  getBean(beanID)
/*
        IUserService userService2 = (IUserService) context.getBean("UserService2");
        userService2.add();
*/


        // 3. <bean factoryID factoryClass> <bean beanId .factoryBean factoryMethod>

/*
        IUserService userService3 = (IUserService) context.getBean("UserService3");
        userService3.add();
*/


//三、<bean>的常见scope:  singleton(单例每次获取一致)  prototype(多例每次获取的对象都不一致)
/*
        IUserService userService4 = (IUserService) context.getBean("UserService4");
        IUserService userService5 = (IUserService) context.getBean("UserService4");
        System.out.println(userService4);
        System.out.println(userService5);

        IUserService userService6 = (IUserService) context.getBean("UserService5");
        IUserService userService7 = (IUserService) context.getBean("UserService5");
        System.out.println(userService6);
        System.out.println(userService7);
*/

//四、<bean>的生命周期

        IUserService userService = (IUserService) context.getBean("UserService8");
        userService.add();
        try {
            context.getClass().getMethod("close").invoke(context);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("end");



    }
}
