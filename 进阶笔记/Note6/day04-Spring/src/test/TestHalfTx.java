package test;

import cn.cxd.Impls.IAccountService;
import cn.cxd.services.AccountService02;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

public class TestHalfTx {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans02.xml");
        IAccountService service = (IAccountService)context.getBean("proxyService");
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
