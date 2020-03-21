package test;

import cn.cxd.impl.UserDaoImpl;
import cn.cxd.mapper.UserMapper;
import cn.cxd.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:45 2020/3/20
 * @Modified By:
 */
public class CombinedSSM {

    /* 直接使用1 selectOne*/
    @Test
    public void TestCombined0(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        SqlSessionFactory sessionFactory =(SqlSessionFactory) context.getBean("factoryBean");
        SqlSession sqlSession = sessionFactory.openSession();
        User user = sqlSession.selectOne("findUserById",2);
        System.out.println(user);
    }

    /*使用继承了SqlSessionDaoSupport的daoImpl 使用getSqlSession()*/
    @Test
    public void TestCombined(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        UserDaoImpl dao = (UserDaoImpl) context.getBean("dao");
        User user = dao.findUserById(1);
        System.out.println(user);
    }

    /* 直接使用2 mapper 使用openSession()*/
    @Test
    public void TestCombined2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        SqlSessionFactory sessionFactory =(SqlSessionFactory) context.getBean("factoryBean");
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(2);
        System.out.println(user);
    }

    /*使用Spring的MapperFactoryBean 使用getSqlSession()*/
    @Test
    public void TestCombined3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        MapperFactoryBean mapperFactoryBean = context.getBean(MapperFactoryBean.class);
        SqlSession sqlSession = mapperFactoryBean.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(2);
        System.out.println(user);
    }

    /*使用Spring的MapperScannerConfigurer 默认配置好mapper*/
    @Test
    public void TestCombined4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        UserMapper mapper = (UserMapper)context.getBean("userMapper");
        User user = mapper.findUserById(2);
        System.out.println(user);
    }

    /*Spring的MapperFactoryBean id getBean*/
    @Test
    public void TestCombined5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        UserMapper mapper = (UserMapper)context.getBean("userMapper");
        User user = mapper.findUserById(2);
        System.out.println(user);
    }
}
