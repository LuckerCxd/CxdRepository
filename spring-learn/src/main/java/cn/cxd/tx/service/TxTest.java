package cn.cxd.tx.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 20:22 2020/5/14
 * @Modified By:
 */
public class TxTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        BookTranscationService service = (BookTranscationService) context.getBean("bookTranscationService");
        service.someoneBuyBook("张三","1");

    }
}
