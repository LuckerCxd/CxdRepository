package cn.cxd.controller.userController;

import cn.cxd.model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.Map;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:35 2020/3/23
 * @Modified By:
 */
public class RegisterController implements Controller {
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView("user/userList");
        modelAndView.addObject("username",httpServletRequest.getParameter("username"));
        modelAndView.addObject("password",httpServletRequest.getParameter("password"));
        modelAndView.addObject("hobbies",httpServletRequest.getParameterValues("hobbies"));

        User user = new User();
        BeanUtils.populate(user,httpServletRequest.getParameterMap());
        modelAndView.addObject("user",user);


        String[] hobbies = httpServletRequest.getParameterValues("hobbies");
        System.out.println("string[] : " + hobbies);
        System.out.println("foreach string[]: ");
        for (String s:hobbies
             ) {
            System.out.println(s);
        }
        System.out.println("Register _ model: "+ modelAndView.getModel());
        return modelAndView;
    }
}
