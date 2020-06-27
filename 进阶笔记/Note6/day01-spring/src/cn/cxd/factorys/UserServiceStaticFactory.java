package cn.cxd.factorys;

import cn.cxd.beans.UserServiceImpl;

public class UserServiceStaticFactory {
    public static UserServiceImpl createUserService(){
        return  new UserServiceImpl();
    }
}
