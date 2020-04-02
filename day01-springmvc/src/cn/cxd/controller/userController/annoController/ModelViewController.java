package cn.cxd.controller.userController.annoController;

import cn.cxd.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:42 2020/3/30
 * @Modified By:
 */

@Controller
@RequestMapping("/user")
public class ModelViewController {

    @RequestMapping("/forward.do")
    public String forward(@RequestParam(required = true,value = "uid",defaultValue = "20") int uid){
        return "forward:/user/userList.do";
    }

    @RequestMapping("/redirect.do")
    public String redirect(){
        return "redirect:/user/userList.do";
    }

    @RequestMapping("/userList.do")
    public String showUserList(Model model, HttpServletRequest request){
        String[] hobbies = {"1","2","3"};
        User user1 = new User(1,"LiMei","123",hobbies);
        User user2 = new User(2,"liuXIn","123",hobbies);
        User user3 = new User(3,"caoJIn","123",hobbies);
        User user4 = new User(4,"JiUI","123",hobbies);
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        model.addAttribute("userList",userList);
        return "/user/modelViewUserListJSP";
    }

}
