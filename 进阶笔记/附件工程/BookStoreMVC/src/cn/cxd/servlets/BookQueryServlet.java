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

@WebServlet("/bookquery.do")
public class BookQueryServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookid = req.getParameter("bookid");
		BookDAO bookDAO = new BookDAO();
		BookBean book = bookDAO.searchBook(bookid);
		if(book !=null) {
			req.getSession().setAttribute("book", book);
			req.getRequestDispatcher("/display.jsp").forward(req, resp);
			
		}else {
			req.getRequestDispatcher("/errorPage.jsp").forward(req, resp);
		}
	}
	
}
