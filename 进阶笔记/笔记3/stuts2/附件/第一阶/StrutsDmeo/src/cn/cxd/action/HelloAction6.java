package cn.cxd.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cxd.bean.Student;

public class HelloAction6 extends ActionSupport implements ModelDriven<Student>{
	private Student student = new Student();
	public String showPara() {
		System.out.println(student);
		return NONE;
	}
	@Override
	public Student getModel() {
		return student;
	}
	
}
