package cn.cxd.web.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cxd.bean.User;

public class I18NActionDemo extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	
	public String doShow() {
		System.out.println(getText("login.username"));
		System.out.println(getText("login.password"));
		System.out.println(user);
		return NONE;
	}

	@Override
	public User getModel() {
		return user;
	}
}
