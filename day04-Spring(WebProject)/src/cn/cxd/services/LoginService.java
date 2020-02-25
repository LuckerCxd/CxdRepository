package cn.cxd.services;

public class LoginService {
    public String login(String username,String password){
        return username + ","+password+" 成功登陆";
    }
}
