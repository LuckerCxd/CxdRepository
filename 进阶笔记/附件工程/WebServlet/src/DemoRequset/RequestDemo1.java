package DemoRequset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class RequestDemo1
 */
@WebServlet("/RequestDemo1")
public class RequestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = new User();
		Map<String, String[]> map = request.getParameterMap();
		
			try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
		
		boolean flag = JDBCLogCheck.checkInfo(user);
		if(flag) {
			request.setAttribute("username",user.getUsername());
			request.getRequestDispatcher("/successdemo").forward(request, response);
			
		}else {
			request.getRequestDispatcher("/faildemo").forward(request, response);
		}
	}

}
