package cn.cxd.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import cn.cxd.bean.Student;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction5 extends ActionSupport{
	//2.set注入1
	private String username;
	private String password;

	
	public String showPara() throws Exception{
		//1.request获取
		Student student = new Student();
		HttpServletRequest request = ServletActionContext.getRequest();
		BeanUtils.populate(student, request.getParameterMap());
		System.out.println("firstMethod:"+student);
		
		//2.set注入2
		student = new Student();
		student.setUsername(username);
		student.setPassword(password);
		System.out.println("SecondMethod:"+student);
		return NONE;
	}

	//2.set注入3
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	
}
