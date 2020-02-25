package cn.cxd.servlets;

import cn.cxd.services.LoginService;
import com.mysql.cj.log.Log;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html; charset=UTF-8");

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        LoginService service = (LoginService) context.getBean("service");
        resp.getWriter().write(service.login(username,password));
        System.out.println(service.hashCode());
        System.out.println(service.hashCode());
        System.out.println(service.hashCode());
        System.out.println(service.hashCode());
    }
}
