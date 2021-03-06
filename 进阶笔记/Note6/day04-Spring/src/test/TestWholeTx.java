package test;

import cn.cxd.Impls.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestWholeTx {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans03.xml");
        IAccountService service = (IAccountService)context.getBean("service");
        System.out.println(service.getClass().getSuperclass());
        double accountZ3 = service.checkAccount("张三");
        double accountL4 = service.checkAccount("李四");

        System.out.println("张三金额： "+accountZ3);
        System.out.println("李四金额： "+accountL4);
        service.transferAccount("张三","李四",100);

        accountZ3 = service.checkAccount("张三");
        accountL4 = service.checkAccount("李四");
        System.out.println("张三金额： "+accountZ3);
        System.out.println("李四金额： "+accountL4);
    }
}
