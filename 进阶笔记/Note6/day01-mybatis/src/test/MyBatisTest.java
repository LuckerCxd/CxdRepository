package test;

import cn.cxd.dao.UserDaoImpl;
import cn.cxd.models.User;
import cn.cxd.models.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test1() {
        /**
         * @Function: test1
         * @Return:  void
         * @Describe:测试select语句
         * @Time:  2020/2/29-17:49
         */
        UserDaoImpl instance = UserDaoImpl.getInstance();
        User user = instance.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void test2() {
        /**
         * @Function: test2
         * @Return:  void
         * @Describe:测试模糊查询like:${abc}
         * @Time:  2020/2/29-17:50
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        List<User> users = intance.findUserByUsernameTag("i");
        System.out.println(users);
    }
    
    

    @Test
    public void test3() {
        /**
         * @Function: test3
         * @Return:  void
         * @Describe:insert语句在插入的是指定的对象的成员变量
         * @Time:  2020/2/29-17:52
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        User user = new User(8,"amiya","123",3,5);
        int row = intance.addUserReturnRow(user);
        System.out.println(row);
    }

    @Test
    public void test4() {
        /**
         * @Function: test4
         * @Return:  void
         * @Describe:测试select，如果传入的是对象，那#{}也得是成员变量
         * @Time:  2020/2/29-17:53
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        User user = new User(1, "dd", "d", 1, 3);
        User target = intance.findUserByUserId(user);
        System.out.println(target);
    }

    @Test
    public void test5() {
        /**
         * @Function: test5
         * @Return:  void
         * @Describe:delete
         * @Time:  2020/2/29-18:03
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        int row = intance.delUserReturnRow(8);
        System.out.println(row);
    }

    @Test
    public void test6() {
        /**
         * @Function: test6
         * @Return:  void
         * @Describe:update by user
         * @Time:  2020/2/29-18:03
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        User user = new User(1, "tuti", "123", 5, 6);
        int row = intance.updateUserInforByUser(user);
        System.out.println(row);
    }

    @Test
    public void test7() {
        /**
         * @Function: test7
         * @Return:  void
         * @Describe: update by map
         * @Time:  2020/2/29-18:03
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username","yotu");
        hashMap.put("id",1);
        hashMap.put("password","123");
        int row = intance.updateUserInforByMap(hashMap);
        System.out.println(row);
    }

    @Test
    public void test8() {
        /**
         * @Function: test8
         * @Return:  void
         * @Describe: select by vo
         * @Time:  2020/2/29-18:03
         */
        UserDaoImpl intance = UserDaoImpl.getInstance();
        User user = new User(1, "dd", "d", 1, 3);
        UserQueryVo userQueryVo = new UserQueryVo(user);
        User userRe = intance.findUserByUserVo(userQueryVo);
        System.out.println(userRe);
    }
}
