package test;

import cn.cxd.springAopXmlConfig.aspect.MyAspect;
import cn.cxd.springAopXmlConfig.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopWholeTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");

/*
        IUserService userProxy = (IUserService) context.getBean("proxyOrCglib");
        userProxy.addUser();
        System.out.println(userProxy.getClass().getSuperclass());
*/


        MyAspect myAspect = (MyAspect)context.getBean("myAspect");
        myAspect.after();
        System.out.println();
        BookService bookService = (BookService)context.getBean("myBookService");
        bookService.addBook("bbbbb");
    }
}
