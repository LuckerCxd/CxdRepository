package test;

import cn.cxd.mapper.UserMapper;
import cn.cxd.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 17:09 2020/3/19
 * @Modified By:
 */
public class CachedTest {

    /**
     * @Function: testFirstCache
     * @Return:  void
     * @Describe:存在缓存，同一个方法的同内容仅查一次，返回的结果是一致的，在多次查询中
     * @Time:  2020/3/19-17:37
     */
    @Test
    public void testFirstCache() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = mapper.findUserById(1);
        System.out.println(user1);

        User user2 = mapper.findUserById(2);
        System.out.println(user2);

        User user3 = mapper.findUserById(1);
        System.out.println(user3);
        sqlSession.close();
    }

    /**
     * @Function: testFirstCache2
     * @Return:  void
     * @Describe:查不同结构的方法，指向同一个查询语句是否还有缓存
     *           不同参指向同一个语句的没有缓存
     *           或者是不同的方法？
     * @Time:  2020/3/19-17:37
     */
    @Test
    public void testFirstCache2() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = mapper.findUserById(1);
        System.out.println(user1);

        User user2 = mapper.findUserById(2);
        System.out.println(user2);

        User user3 = mapper.findUserByUser(new User(1));
        System.out.println(user3);
        sqlSession.close();
    }


    /**
     * @Function: testFirstCache3
     * @Return:  void
     * @Describe: 确定了是不同的方法带不同的缓存，就是说即便参数，返回值一致只要不是不同的
     * 方法，那么即便是内容指向同一个查询语句的不同方法有着不同的缓存空间
     * @Time:  2020/3/19-17:37
     */
    @Test
    public void testFirstCache3() throws IOException {

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = mapper.findUserById(1);
        System.out.println(user1);

        User user2 = mapper.findUserById(2);
        System.out.println(user2);

        User user3 = mapper.findUserByInt(1);
        System.out.println(user3);
        sqlSession.close();
    }

    /**
     * @Function: testFirstCache4
     * @Return:
     * @Describe: 在insert,delete,update等操作后，一级缓存清空，不需要commit(),
     *           也就是说，执行这些操作但实际上并未写入到数据库中时，一级缓存就清空了。
     * @Time:  2020/3/19-17:47
     */
    @Test
    public void testFirstCache4() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = mapper.findUserById(1);
        System.out.println(user1);

        int affectedRow = mapper.insertUser(new User(10, "miqi", "123456"));
        System.out.println(affectedRow);

        User user3 = mapper.findUserById(1);
        System.out.println(user3);
        sqlSession.close();
    }

    /*
        close()后才可放入二级。一级不用close(),也不用commit
     */
    @Test
    public void testSecondCache() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.findUserById(1);
        System.out.println(user1);
        sqlSession.commit();


        SqlSession sqlSession2 = sessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.findUserById(1);
        System.out.println(user2);
        sqlSession2.close();

        SqlSession sqlSession3 = sessionFactory.openSession();
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        User user3 = mapper3.findUserById(1);
        System.out.println(user3);
        sqlSession3.close();
    }

    /*commit - update*/
    @Test
    public void testSecondCache2() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.findUserById(1);
        System.out.println(user1);
        sqlSession.commit();


        SqlSession sqlSession2 = sessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        int affectedRow = mapper2.insertUser(new User(10, "miqi", "123456"));
        System.out.println(affectedRow);
        sqlSession2.close();


        SqlSession sqlSession3 = sessionFactory.openSession();
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        User user3 = mapper3.findUserById(1);
        System.out.println(user3);
        sqlSession3.close();
    }

    @Test
    public void testSecondCache3() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.findUserById(1);
        System.out.println(user1);
        sqlSession.commit();

        SqlSession sqlSession2 = sessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        int affectedRow = mapper2.insertUser(new User(10, "miqi", "123456"));
        System.out.println(affectedRow);
        sqlSession2.commit();

        SqlSession sqlSession3 = sessionFactory.openSession();
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        User user3 = mapper3.findUserById(1);
        System.out.println(user3);
        sqlSession3.close();
    }

    @Test
    public void testEhCache() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig_ehcache.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.findUserById(1);
        System.out.println(user1);
        sqlSession.close();

        SqlSession sqlSession2 = sessionFactory.openSession(true);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        int affectedRow = mapper2.insertUser(new User(10, "miqi", "123456"));
        System.out.println(affectedRow);


        SqlSession sqlSession3 = sessionFactory.openSession();
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        User user3 = mapper3.findUserById(1);
        System.out.println(user3);
        sqlSession3.close();
    }

    @Test
    public void testEhCache2() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig_ehcache.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.findUserById(1);
        System.out.println(user1);
        sqlSession.commit();


        SqlSession sqlSession2 = sessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.findUserById(1);
        System.out.println(user2);
        sqlSession2.close();

        SqlSession sqlSession3 = sessionFactory.openSession();
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        User user3 = mapper3.findUserById(1);
        System.out.println(user3);
        sqlSession3.close();
    }
}
