package cn.cxd.factorys;

import cn.cxd.beans.UserServiceImpl;

public class UserServiceInstanceFactory {
    public UserServiceInstanceFactory() { };
    public UserServiceImpl createUserService(){
        return new UserServiceImpl();
    }
}
