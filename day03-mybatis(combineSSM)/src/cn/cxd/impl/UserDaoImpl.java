package cn.cxd.impl;

import cn.cxd.mapper.UserMapper;
import cn.cxd.model.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 15:15 2020/3/21
 * @Modified By:
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public User findUserById(int id) {
        return this.getSqlSession().selectOne("findUserById",1);
    }
}
