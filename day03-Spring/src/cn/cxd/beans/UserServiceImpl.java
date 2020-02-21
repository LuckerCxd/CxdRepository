package cn.cxd.beans;


import cn.cxd.impls.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Override
    public int addUser() {
        System.out.println("UserServiceImpl addUser()");
        return 1235;
    }
    @Override
    public void delUser() {
        System.out.println("UserServiceImpl delUser()");
    }
}
