package cn.cxd.base.getBean;

import cn.cxd.jdbc.DaoJdbcNameProperty;
import cn.cxd.jdbc.DaoJdbcTemplate;
import cn.cxd.jdbc.DaoJdbcdaoSupport;
import cn.cxd.jdbc.model.BookState;
import cn.cxd.proxy.CglibProxy;
import cn.cxd.proxy.JdkProxy;
import cn.cxd.service.Calculator;
import cn.cxd.service.CalculatorService;
import cn.cxd.xmlAop.XmlServiceAop;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:26 2020/5/6
 * @Modified By:
 */
public class Spring {

    public void jdkProxy(){
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        JdkProxy proxy = new JdkProxy(new CalculatorService());
        Calculator proxyObject = (Calculator)proxy.getProxyObject();
        proxyObject.add(1,3);
    }

    public void cglibProxy(){
        System.getProperties().put(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/cglib");
        CglibProxy cglibProxy = new CglibProxy(new CalculatorService());
        CalculatorService proxyObject = (CalculatorService) cglibProxy.getProxyObject();
        proxyObject.add(1,4);
    }
    public void xmlAopTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        //Calculator calculatorService = (Calculator) context.getBean("calculatorService");
        CalculatorService calculatorService =context.getBean(CalculatorService.class);
        int re = calculatorService.div(56,14);
        System.out.println("main result :"+ re);
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        DaoJdbcTemplate bean = (DaoJdbcTemplate) context.getBean("daoJdbcTemplate");
        System.out.println(bean.batchUpdate().length);
    }


    DaoJdbcTemplate daoJdbcTemplate;
    DaoJdbcdaoSupport daoJdbcdaoSupport;
    DaoJdbcNameProperty jdbcNameProperty;
    @Before
    public void loadConfig(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        daoJdbcTemplate = (DaoJdbcTemplate) context.getBean("daoJdbcTemplate");
        daoJdbcdaoSupport = (DaoJdbcdaoSupport) context.getBean("daoJdbcdaoSupport");
        jdbcNameProperty = (DaoJdbcNameProperty) context.getBean("jdbcNameProperty");
    }

    @Test //字段名与Bean一致，否则null
    public void testQuery1() {
        BookState bookState = (BookState) daoJdbcTemplate.queryForSingleObject("1");
        System.out.println(bookState);
    }

    @Test
    public void testQuery2() {
        System.out.println(daoJdbcTemplate.queryField("1"));
    }

    @Test
    public void testQuery3() {
        System.out.println(daoJdbcTemplate.queryAvg());
    }

    @Test
    public void testQuery4() {
        System.out.println(daoJdbcTemplate.queryMap("2"));
    }


    @Test
    public void testQuery5() {
        System.out.println(daoJdbcTemplate.queryMapList("1"));
    }

    @Test
    public void testQuery6() {
        System.out.println(daoJdbcTemplate.queryFieldList("1"));
    }

    @Test
    public void testQuery7() {
        System.out.println(daoJdbcTemplate.queryObjectList("1"));
    }

    @Test
    public void testQuery8() {
        System.out.println(daoJdbcTemplate.queryFieldMap("1"));
    }

    @Test
    public void testQuery9() {
        System.out.println(daoJdbcdaoSupport.queryForSingleObject("1"));
    }

    @Test
    public void testQuery10() {
        System.out.println(jdbcNameProperty.butchUpdate());
    }
}
