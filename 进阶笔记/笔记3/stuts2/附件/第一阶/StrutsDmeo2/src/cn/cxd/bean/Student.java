package cn.cxd.bean;

import java.util.Date;

public class Student {
	private String username;
	private String password;
	private Date birthday;
	private boolean adult;
	
	
	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", password=" + password + ", birthday=" + birthday + ", adult="
				+ adult + "]";
	}
	
	
}
