json基础知识:

    JavaScript Object natation 
        -js对象表示法 

json基本表示：

    1.表示对象:{}包裹键值对
    2.表示数组:[]一般包裹对象
    3.遍历： for...in..

举例：

    var user1 = {"username":"李白","password":"123456"};
    var user2 = {"username":"杜甫","password":"123456"};
    var users = [user1,user2];

xxx.html:
-
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    </head>
    <body>

    </body>
    
    <script type="text/javascript">
    
    //1.对象使用{},用逗号分隔各个键值对,
    //	遍历对象内容时使用for (var key in 对象){xxx对象[key]} 
    
    /* 
    var user = {"username":"李白","password":"123456"};
    alert(user);
    alert(user["username"]);
    for (var key in user) {
    	alert(key);
    }
    
    */
    
    
    //2.数组使用[],内部应放置对象，用逗号隔开
    var user1 = {"username":"李白","password":"123456"};
    var user2 = {"username":"杜甫","password":"123456"};
    var users = [user1,user2];
    var usObject = {"users":users};
    
    alert(users);
    
    var us = usObject["users"];
    
    
    //3.for_in循环：
    //		如果in的对象是数组,那么前方var的变量就是索引值
    //		如果in的对象是对象，那么前方var的变量就是对象的key(字符串)
    
    
    //1.
    /* 
    for (var user in us) {
    	for(var key in us[user]){
    		alert(us[user][key]);
    	}
    } 
    
     */
     
     
    //2. 
    
    for (var i = 0; i < us.length; i++) {
    	for(var key in us[i]){
    		alert(us[i][key]);
    	}
    } 
    	
     
    </script>
    
    </html>


java-->json:
    
    1.步骤
    2.注解
    3.其他类型
    
    
1.步骤
    1.导入jackson_jar包：
        jackson-annotations-2.9.8.jar
        jackson-core-2.9.8.jar
        jackson-databind-2.9.8.jar
    2.创建ObjectMapper对象并使用WriteValue(),writeValueAsString()：
        参数1：File，Writer,OutputStream
        参数2：对象

举例：

        User user = new User();
    	user.setUsername("李清照");
    	user.setPassword("123456");
    	user.setLoginTime(new Date());
    	
    	//对象->json
    	ObjectMapper objectMapper = new ObjectMapper();
    	System.out.println(objectMapper.writeValueAsString(user));
    	//objectMapper.writeValue(response.getWriter(), user);
    	response.getWriter().write(objectMapper.writeValueAsString(user));
    

2.注解：

    @JsonIgnore  @JsonFormat(pattern="xxxx"）

举例：

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

3.其他类型：

    list
    map

举例：

    package ServletDemo;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import com.fasterxml.jackson.databind.ObjectMapper;
    
    import JavaBean.User;
    
    
    @WebServlet("/servletDemo")
    public class servletDemo extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doPost(request, response);
    	}
    
    	
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		response.setContentType("text/html;charset=utf-8");
    		response.getWriter().write(request.getParameter("username")+" is you ?");
    		User user = new User();
    		user.setUsername("李清照");
    		user.setPassword("123456");
    		user.setLoginTime(new Date());
    		
    		//对象->json
    		ObjectMapper objectMapper = new ObjectMapper();
    		System.out.println(objectMapper.writeValueAsString(user));
    		//objectMapper.writeValue(response.getWriter(), user);
    		response.getWriter().write(objectMapper.writeValueAsString(user));
    	
    	
    		//list->json
    		User user2 = new User();
    		user2.setUsername("辛弃疾");
    		user2.setPassword("123456");
    		user2.setLoginTime(new Date());
    		List list = new ArrayList<>();
    		list.add(user);
    		list.add(user2);
    		System.out.println(objectMapper.writeValueAsString(list));
    		
    		//map->json
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("username", "苏轼");
    		System.out.println(objectMapper.writeValueAsString(map));
    	
    		//json->java
    		String json = "	{\"username\":\"王勃\",\"password\":123456}";
    		User user3 = objectMapper.readValue(json, User.class);
    		System.out.println(user3);
    	}
    
    }

        
json-->java
    
    注意：
        1.使用readValue(json语句String,对象.class)，会返回一个对象引用
        2.如果是被注解为@JsonIgnore的，那么也不会被解析，结果为null
        2.如果是被注解为@JsonFormat的，也不会被格式化
        
举例：
    
    String json = "	{\"username\":\"王勃\",\"password\":123456}";
    User user3 = objectMapper.readValue(json, User.class);
	System.out.println(user3);
    