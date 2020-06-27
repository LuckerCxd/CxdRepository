package JavaBean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private String username;
	@JsonIgnore   //忽略password
	private String password;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //改Date的格式
	private Date loginTime;
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", loginTime=" + loginTime + "]";
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
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
	
}
