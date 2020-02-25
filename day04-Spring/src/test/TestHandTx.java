package test;

import cn.cxd.services.AccountService01;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHandTx {
    public static void main(String[] args) {
        ApplicationContext con = new ClassPathXmlApplicationContext("beans01.xml");
        AccountService01 service = con.getBean(AccountService01.class);

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
