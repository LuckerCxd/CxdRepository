package cn.cxd.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.cxd.beans.Student;

public class ConfigDemoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();
		Student student = new Student();
		try {
			BeanUtils.populate(student, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		req.getServletContext().setAttribute("ѧ��", student.getSno());
		req.getServletContext().setAttribute("����", student.getName());
		
		
//		String sno = (String)req.getServletContext().getAttribute("ѧ��");
//		String name = (String)req.getServletContext().getAttribute("����");
//		System.out.println("bs"+sno);
//		System.out.println("bn"+name);
		req.getRequestDispatcher("/output").forward(req, resp);
		
	}
}
