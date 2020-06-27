package test;

import cn.cxd.beans.anno.AnnoAction;
import cn.cxd.beans.anno.AnnoAction2;
import cn.cxd.beans.anno.AnnoDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");

       /*
       * 一、使用@Component注解
       *    @Component("id")  相当于<bean class id>: context.getBean("annoDaoName");
       *    @Component 相当于<bean class>: context.getBean(AnnoService.class);
       * */


/*
        测试@Component的，AnnoAction,AnnoService,AnnoDao都用这个注解

        AnnoService service = context.getBean(AnnoService.class);
        AnnoDao annoDao = (AnnoDao) context.getBean("annoDaoName");
        System.out.println(service);
        System.out.println(annoDao);
*/

       /*
       * 二、使用@Component 的拓展注解,实例化bean
       *    1.Controller : @Controller/@Controller("")
       *    2.dao : @Repository/@Repository("")
       *    3.Service : @Service/@Service("")
       *
       *        @Controller 注解的bean会被spring-mvc框架所使用。
                @Repository 会被作为持久层操作（数据库）的bean来使用

            4.@Autowired 根据类型自动注入到成员变量中，required 默认为true强行注入，false为尽力注入


       * */


/*
        AnnoAction annoAction = context.getBean(AnnoAction.class);
        annoAction.add();
*/

/*
        AnnoDao dao = context.getBean(AnnoDao.class);
        AnnoDao dao2 = context.getBean(AnnoDao.class);
        System.out.println(dao);
        System.out.println(dao2);
        try {
            context.getClass().getMethod("close").invoke(context);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
*/

/*
        AnnoDao2 dao = context.getBean(AnnoDao2.class);
        AnnoDao2 dao2 = context.getBean(AnnoDao2.class);
        System.out.println(dao);
        System.out.println(dao2);
        try {
            context.getClass().getMethod("close").invoke(context);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

*/


/*
        AnnoDao2 annoDao = (AnnoDao2) context.getBean("annoDaoName2");
        AnnoDao2 annoDao2 = (AnnoDao2) context.getBean("annoDaoName2");
        AnnoDao2 annoDao3 = (AnnoDao2) context.getBean("annoDaoName2");
        System.out.println(annoDao);
        System.out.println(annoDao2);
        System.out.println(annoDao3);
*/




/*
        AnnoDao annoDao = (AnnoDao) context.getBean("annoDaoName");
        AnnoDao annoDao2 = (AnnoDao) context.getBean("annoDaoName");
        AnnoDao annoDao3 = (AnnoDao) context.getBean("annoDaoName");
        AnnoDao annoDao4 = (AnnoDao) context.getBean("annoDaoName");
        System.out.println(annoDao);
        System.out.println(annoDao2);
        System.out.println(annoDao3);
        System.out.println(annoDao4);
        AnnoAction annoAction = (AnnoAction) context.getBean(AnnoAction.class);
        annoAction.add();
*/


        AnnoAction annoAction = (AnnoAction) context.getBean("annoActionName");
        AnnoAction2 annoAction2 = context.getBean(AnnoAction2.class);
        annoAction.add();
        annoAction2.add();
    }
}
