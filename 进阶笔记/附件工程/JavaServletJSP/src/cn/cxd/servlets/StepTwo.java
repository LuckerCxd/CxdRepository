package cn.cxd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.cxd.beans.Student;

public class StepTwo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "text/html;charset=utf-8");
		String sno = (String)req.getServletContext().getAttribute("学号");
		String name = (String)req.getServletContext().getAttribute("姓名");
		PrintWriter writer = resp.getWriter();
		writer.write("学号："+sno+"<br/>");
		writer.write("姓名："+name);
		writer.close();
		
	}
}
