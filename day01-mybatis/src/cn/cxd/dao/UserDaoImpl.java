package cn.cxd.dao;

import cn.cxd.impls.IUserDao;
import cn.cxd.models.User;
import cn.cxd.models.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory sqlSessionFactory;
    private static UserDaoImpl instance;


    private UserDaoImpl() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try{
            sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static UserDaoImpl getInstance(){
        if(instance == null){
            synchronized (UserDaoImpl.class){
                if(instance == null){
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }



    @Override
    public int addUserReturnRow(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int row = sqlSession.insert("insert", user);
        sqlSession.close();
        return row;
    }





    @Override
    public int selectLikeReturnCount(String name) {
        return 0;
    }

    @Override
    public User findUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("selectById", id);
        sqlSession.close();
        return user;
    }


    @Override
    public List<User> findUserByUsernameTag(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users =  sqlSession.selectList("selectByName", username);
        sqlSession.close();
        return users;
    }

    @Override
    public User findUserByUserId(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User target = sqlSession.selectOne("selectByUserId", user);
        sqlSession.close();
        return target;
    }

    @Override
    public int delUserReturnRow(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int row = sqlSession.delete("delete", id);
        sqlSession.close();
        return row;
    }


    @Override
    public int updateUserInforByMap(HashMap<String, Object> hashMap) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int row = sqlSession.update("update2", hashMap);
        sqlSession.close();
        return row;
    }

    @Override
    public int updateUserInforByUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int row = sqlSession.update("update", user);
        sqlSession.close();
        return row;
    }


    @Override
    public User findUserByUserVo(UserQueryVo userQueryVo) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User target = sqlSession.selectOne("selectByUserVo", userQueryVo);
        sqlSession.close();
        return target;
    }
}
