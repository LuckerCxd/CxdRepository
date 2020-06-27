package DemoRequset;

import java.sql.Timestamp;

public class User{
	private String username;
	private String password;
	private Timestamp loggingTime;
	public User(String username, String password,Timestamp loggingTime) {
		this.username = username;
		this.password = password;
		this.loggingTime = loggingTime;
	}
	public User() {
		super();
	}
	public Timestamp getLoggingTime() {
		return loggingTime;
	}
	public void setLoggingTime(Timestamp loggingTime) {
		this.loggingTime = loggingTime;
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
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", loggingTime=" + loggingTime + "]";
	}
}