package test;


import cn.cxd.model.User;
import cn.cxd.modelMapper.UserMapper;
import cn.cxd.modelVo.UserVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:50 2020/3/9
 * @Modified By:
 */
public class MyBatisTest {
    @Test
    public void test1() throws IOException {
        /**
         * @Function: test1
         * @Return:  void
         * @Describe: testMapper
         * @Time:  2020/3/9-16:51
         */

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());
        User user = mapper.findUserByid(1);
        System.out.println(user);

    }

    @Test
    public void test2() throws IOException {
        /**
         * @Function: test2
         * @Return:  void
         * @Describe: testMapper
         * @Time:  2020/3/9-16:51
         */

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());
        List<User> users = mapper.findUserByName("y");
        System.out.println(users);


    }


    @Test
    public void test3() throws IOException {
        /**
         * @Function: test3
         * @Return:  void
         * @Describe: testMapper _resultMap
         * @Time:  2020/3/9-16:51
         */

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());
        User user = mapper.findUserByidUseResultMap(1);
        System.out.println(user);


    }

    @Test
    public void test4() throws IOException {
        /**
         * @Function: test4
         * @Return:  void
         * @Describe: testMapper
         *      <if><association><collection>
         * @Time:  2020/3/9-16:51
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());

        User user1 = new User(1, "yotu", "123456", 1, 5);
        Integer[] user1Array = {1,2,3};
        List<Integer> user1List = new ArrayList<>();
        user1List.add(3);
        user1List.add(4);
        user1List.add(5);
        UserVo userVo = new UserVo(user1, user1Array, user1List);
        System.out.println("userVo prev: "+ userVo);
        List<UserVo> userByArrayUnionList = mapper.findUserByArrayUnionList(userVo);
        System.out.println("result after: "+userByArrayUnionList);


    }


    @Test
    public void test5() throws IOException {
        /**
         * @Function: test5
         * @Return:  void
         * @Describe: testMapper
         * <sql> <where><include</where>
         * @Time:  2020/3/9-16:51
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());

        User user1 = new User(1, "yotu", "123456", 1, 5);
        Integer[] user1Array = {1,2,3};
        List<Integer> user1List = new ArrayList<>();
        user1List.add(3);
        user1List.add(4);
        user1List.add(5);
        UserVo userVo = new UserVo(user1, user1Array, user1List);
        System.out.println("userVo prev: "+ userVo);
        List<User> userByArrayUnionList = mapper.findUserByArrayInList(userVo);
        System.out.println("result after: "+userByArrayUnionList);


    }



    @Test
    public void test6() throws IOException {
        /**
         * @Function: test6
         * @Return:  void
         * @Describe: testMapper
         *  <collection></collection>
         * @Time:  2020/3/9-16:51
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());

        User user1 = new User(1, "yotu", "123456", 1, 5);
        Integer[] user1Array = {1,2,3};
        List<Integer> user1List = new ArrayList<>();
        user1List.add(3);
        user1List.add(4);
        user1List.add(5);
        UserVo userVo = new UserVo(user1, user1Array, user1List);
        System.out.println("userVo prev: "+ userVo);
        List<UserVo> userByArrayUnionList = mapper.findUserByArrayInListRecordIdsToList(userVo);
        System.out.println("result after: "+userByArrayUnionList);


    }


    @Test
    public void test7() throws IOException {
        /**
         * @Function: test7
         * @Return:  void
         * @Describe: testMapper
         *  <collection> list 外键 </collection>
         * @Time:  2020/3/9-16:51
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());

        User user1 = new User(1, "yotu", "123456", 1, 5);
        UserVo userVo = new UserVo(3);

        System.out.println("userVo prev: "+ userVo);
        List<UserVo> userByArrayUnionList = mapper.findUserByIdRecordOrders(userVo);
        System.out.println("result after: "+userByArrayUnionList);


    }

    @Test
    public void test8() throws IOException {
        /**
         * @Function: test8
         * @Return:  void
         * @Describe: testMapper
         *  <collection> list 外键 </collection>
         * @Time:  2020/3/9-16:51
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("class " + mapper.getClass());

        User user1 = new User(1, "yotu", "123456", 1, 5);
        UserVo userVo = new UserVo(3);

        System.out.println("userVo prev: "+ userVo);
        List<Integer> integers = mapper.findUserIntegers(userVo);
        System.out.println("result after: "+integers);


    }
}
