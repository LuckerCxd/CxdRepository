package JavaBeanClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String name;
	private String password;
	private int age;
	private Date loginTime;
	public User() {
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogTimeStr() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		return simpleDateFormat.format(loginTime);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", age=" + age + ", loginTime=" + loginTime + "]";
	}
	
}
