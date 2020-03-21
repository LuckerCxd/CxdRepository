package cn.cxd.mapper;

import cn.cxd.model.User;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:42 2020/3/19
 * @Modified By:
 */
public interface UserMapper {
    public User findUserById(int id);
    public User findUserByInt(int id);
    public User findUserByUser(User user);
    public int insertUser(User user);
}
