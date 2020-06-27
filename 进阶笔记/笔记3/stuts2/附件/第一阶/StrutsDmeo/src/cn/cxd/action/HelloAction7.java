package cn.cxd.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.cxd.bean.Student;

public class HelloAction7 extends ActionSupport{
	private Student student;
	public String showPara() {
		System.out.println(student);
		return NONE;
	}
	public Student getStudent() {
		System.out.println("get it");
		return student;
	}
	public void setStudent(Student student) {
		System.out.println("set it");
		this.student = student;
	}
}
