package cn.cxd.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.cd.beans.BookBean;
import cn.cd.beans.BookDAO;

@WebServlet("/bookinsert.do")
public class BookInsertServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("gb2312");
		Map<String, String[]> parameterMap = req.getParameterMap();
		BookBean bean = new BookBean();
		String message = null;
		
		try {
			BeanUtils.populate(bean, parameterMap);
			BookDAO bookdao = new BookDAO();
			boolean success = bookdao.insertBook(bean);
			if(success){
				message = "成功插入一条记录！";
			}else{
				message = "插入记录错误！";
			}
			req.setAttribute("result",message);
			req.getRequestDispatcher("/bookInsert.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
