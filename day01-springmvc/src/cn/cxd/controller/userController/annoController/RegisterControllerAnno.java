package cn.cxd.controller.userController.annoController;

import cn.cxd.model.User;

import cn.cxd.model.UserVoArray;
import cn.cxd.model.UserVoList;
import cn.cxd.model.UserVoPOJO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:35 2020/3/23
 * @Modified By:
 */

@Controller
@RequestMapping("/user")
public class RegisterControllerAnno {

    /*
        各个属性使用request获取，存起来
     */
    @RequestMapping("/registerAnno1.do")
    public String collectionParamAndReturnPage(HttpServletRequest request, String username, String password, String[] hobbies) throws InvocationTargetException, IllegalAccessException {
        System.out.println("username:" + username);
        System.out.println("password:"+ password);
        System.out.println("hobbies:"+ hobbies);

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("username",username);
        hashMap.put("password",password);
        hashMap.put("hobbies",hobbies);
        User user = new User();
        BeanUtils.populate(user,hashMap);
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("hobbiew",hobbies);
        request.setAttribute("user",user);
        return "user/userListAnnoJsp";
    }


    /*

        聚合成一个Object,para : 属性，名称上设置类似mybatis
     */
    @RequestMapping(value = "/registerAnno2.do" )
    public String collectionParamAndReturnPage(HttpServletRequest request, User o) {
        request.setAttribute("user",o);
        System.out.println("ooooo"+o.getClass());
        return "user/userListAnnoJsp";
    }


    /*

        再聚合成一个VOObject,para : 属性，名称上设置类似mybatis
     */
    @RequestMapping(value = "/registerAnno3.do" )
    public String collectionParamAndReturnPage(HttpServletRequest request, UserVoPOJO userVoPOJO) {
        request.setAttribute("userVoPOJO", userVoPOJO);
        return "user/userListAnnoJsp";
    }


    /*

        再聚合成一个VOArray,para : 使用索引[ ]
     */
    @RequestMapping(value = "/registerAnno4.do" )
    public String collectionParamAndReturnPage(HttpServletRequest request, UserVoArray userVoArray) {
        request.setAttribute("userVoArray", userVoArray);
        return "user/userListAnnoJsp";
    }

    @RequestMapping(value = "/registerAnno5.do" )
    public String collectionParamAndReturnPage(HttpServletRequest request, UserVoList userVoList) {
        request.setAttribute("userVoList", userVoList);
        return "user/userListAnnoJsp";
    }
}
