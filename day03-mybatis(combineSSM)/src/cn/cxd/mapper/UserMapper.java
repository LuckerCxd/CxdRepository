package cn.cxd.mapper;

import cn.cxd.model.User;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:11 2020/3/20
 * @Modified By:
 */
public interface UserMapper{
    User findUserById(int id);
}
