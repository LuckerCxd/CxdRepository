package cn.cxd.controller.userController.annoController;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:19 2020/3/23
 * @Modified By:
 */

@Controller
@RequestMapping("/user")
public class ToRegisterControllerAnno {

    @RequestMapping("/toRegisterAnno1.do")
    public String toRegisterAnno1(){
        return "user/registerAnnoJsp";
    }

}
