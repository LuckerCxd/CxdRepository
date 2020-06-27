package cn.cxd.web.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cxd.bean.User;

public class InterceptorAction extends ActionSupport implements ModelDriven<User>{
	
	private User user = new User();
	
	public String execute() {
		System.out.println(user);
		return SUCCESS;
	}
	
	
	@Override
	public User getModel() {
		return user;
	}
	
}
