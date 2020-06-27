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
