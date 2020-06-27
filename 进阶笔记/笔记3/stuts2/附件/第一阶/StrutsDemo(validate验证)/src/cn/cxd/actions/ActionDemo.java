package cn.cxd.actions;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cxd.bean.User;

public class ActionDemo extends ActionSupport implements ModelDriven<User>{
	
	private User user = new User();
	
	public String register() {
		System.out.println(user);
		return NONE;
	}
	
//	@SkipValidation
	public String showUsers() {
		return "users";
	}
	
	//	全局
	/*
	@Override
	public void validate() {
		if(StringUtils.isEmpty(user.getUsername())) {
			addFieldError("username", "用户名不能为空");
		}
	}
	*/
	
	/*
	public void validateRegister(){
		if(StringUtils.isEmpty(user.getUsername())) {
			addFieldError("username", "用户名不能为空");
		}
	}
	*/
	
	
	/*
	 * com.opensymphony.xwork2.validator.validators.StringLengthFieldValidator
	 * com.opensymphony.xwork2.validator.validators.RequiredStringValidator
	 * com.opensymphony.xwork2.validator.validators.ExpressionValidator
	 * com.opensymphony.xwork2.validator.validators.EmailValidator
	 * com.opensymphony.xwork2.validator.validators.IntRangeFieldValidator
	 * com.opensymphony.xwork2.validator.validators.URLValidator
	 * com.opensymphony.xwork2.validator.validators.RegexFieldValidator
	 * 
	 */
	
	
	@Override
	public User getModel() {
		return user;
	}
}
