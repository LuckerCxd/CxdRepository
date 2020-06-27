package DemoFilter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.commons.beanutils.BeanUtils;

import JavaBeanClass.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		System.out.println("username:"+map.get("username")[0]);
		System.out.println("password:"+map.get("password")[0]);
		
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			System.out.println("BUusername:"+user.getUsername());
			System.out.println("BUpassword:"+user.getPassword());
//			手动设置
//			user.setName(map.get("username")[0]);
//			user.setPassword(map.get("password")[0]);;
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//相当于直接当做数据库中有此数据
		if(true) {
			System.out.println("name"+request.getAttribute("name"));
			request.getSession().setAttribute("user",user);
			request.getRequestDispatcher("/ServletDemo1").forward(request,response);
		}
	}

}
