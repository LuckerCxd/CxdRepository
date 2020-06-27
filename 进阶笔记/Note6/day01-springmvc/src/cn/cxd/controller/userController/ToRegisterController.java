package cn.cxd.controller.userController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:19 2020/3/23
 * @Modified By:
 */
public class ToRegisterController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        /*
            尝试获取参数的方式：
            第一种是单取元素：username
            modelAndView.setViewName("user/register");
        */

        modelAndView.setViewName("user/register");
        System.out.println("ToRegister _ model: "+ modelAndView.getModel());
        return modelAndView;
    }
}
