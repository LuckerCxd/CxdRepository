package cn.cxd.springAopXmlConfig.service;

public class UserService implements IUserService{
    private String name;

    @Override
    public int addUser() {
        System.out.println("UserService addUser 任务执行");
        return 10086;
    }

    @Override
    public void delUser() {
        System.out.println("UserService delUser 任务执行");
    }

    public void updateUser() {
        System.out.println("UserService updateUser 任务执行");
    }
}
