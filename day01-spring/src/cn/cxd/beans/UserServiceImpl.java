package cn.cxd.beans;

import cn.cxd.impl.IUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class UserServiceImpl implements IUserService, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private String username;

    @Override
    public void destroy() throws Exception {
        System.out.println("13.DisposableBean(destroy)空参void");
    }


    private String password;

    public UserServiceImpl() {
        System.out.println("3.Bean类的实例化：constructor here,username:"+this.username+",password:"+this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void add() {
        System.out.println("12.bean的使用添加用户: " + username + " " + password);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("6.BeanNameAware(setBeanName):s:" + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("7.BeanFactoryAware(setBeanFactory):beanFactory:" + beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("9.InitializingBean(afterPropertiesSet)空参void");
    }

    public void beanDestroy() {
        System.out.println("14.beanDestroy(beans.xml.destroy_method)void");
    }

    public void beanInit() {
        System.out.println("10.beanInit(beans.xml.init_method)空参void");
    }
}
