package DemoRequset;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCLogCheck {
	
	
	
	
	public static boolean checkInfo(String username,String password) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SQLDruidHelp.getDataSource());
		String sql = "select * from userInfo where username = ? && password = ?";
		List<User> lists = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
		if(lists.size() > 0 ) {
			return true;
		}
			
		return false;
	}
	
	public static boolean checkInfo(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SQLDruidHelp.getDataSource());
		String sql = "select * from userInfo where username = ? && password = ?";
		List<User> lists = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
		if(lists.size() > 0 ) {
			return true;
		}
		return false;
	}
	
	
	@Test
	public void test1() {
		boolean flag = JDBCLogCheck.checkInfo("Àî°×","123456");
		if(flag) {
			System.out.println("yes");
		}else {
			System.out.println("nonono");
		}
		
	}
	
}



	
	
	