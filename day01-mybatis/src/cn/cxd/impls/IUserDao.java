package cn.cxd.impls;

import cn.cxd.models.User;
import cn.cxd.models.UserQueryVo;

import java.util.HashMap;
import java.util.List;

public interface IUserDao {
    int addUserReturnRow(User user);
    int selectLikeReturnCount(String name);
    User findUserById(int id);
    User findUserByUserVo(UserQueryVo userQueryVo);
    User findUserByUserId(User user);
    List<User> findUserByUsernameTag(String username);
    int delUserReturnRow(int id);
    int updateUserInforByUser(User user);
    int updateUserInforByMap(HashMap<String,Object> hashMap);
}
