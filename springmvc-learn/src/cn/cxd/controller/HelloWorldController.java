package cn.cxd.controller;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 13:21 2020/6/2
 * @Modified By:
 */



import cn.cxd.exception.CustomException;
import cn.cxd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;


@Controller
public class HelloWorldController {
    private final String SUCCESS = "success";

    @RequestMapping("/makeCustomException")
    public void makeCustomException() {
        throw new CustomException();
    }

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @ExceptionHandler({ClassNotFoundException.class})
    public ModelAndView testExceptionHandle2(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        System.out.println("RuntimeException");
        modelAndView.addObject("excepteion",ex);
        return modelAndView;
    }



    @RequestMapping("/testExceptionHandle")
    public String makeException() {
        int i = 10/0;
        return "success";
    }

    @RequestMapping("/testUpload")
    public String testUpload(@RequestParam("upload-file")MultipartFile multipartFile,
                             @RequestParam("upload-desc")String uploadDesc) {
        System.out.println("originalFilename: "+ multipartFile.getOriginalFilename());
        System.out.println("name: "+ multipartFile.getName());
        System.out.println("size: "+ multipartFile.getSize());
        return "success";
    }

    @RequestMapping("/i18n1")
    public String testI18nInBeanAndConvertLocale(Locale locale) {
        String val = messageSource.getMessage("username", null, locale);
        System.out.println(locale.getCountry()+"  "+locale.getLanguage()+" "+val);
        return "i18n1";
    }

    @RequestMapping("/i18n2")
    public String testI18n2InBeanAndConvertLocale(Locale locale) {
        String val = messageSource.getMessage("password", null, locale);
        System.out.println(locale.getCountry()+"  "+locale.getLanguage()+" "+val);
        return "i18n2";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect() {
        return "redirect:/myCustomView";
    }
    @RequestMapping("/testForward")
    public String testForward() {
        return "forward:/straightMapping";
    }

    @RequestMapping("/myCustomView")
    public String customView() {
        return "customView";
    }
    @ModelAttribute
    public void testModelAttribute(@RequestParam(value = "id",required = false) Integer id
            ,ModelMap map) {
        if(id != null){
            User user = new User("kk", "123456", "kk@123456");
            map.addAttribute("maUser",user);
        }
    }

    @RequestMapping(value = "/test/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("maUser") User user) {
        System.out.println("user : "+user);
        return SUCCESS;
    }

    @RequestMapping(value = "/test/ParamModel")
    public String param(@ModelAttribute("kuser") User kuser) {
        System.out.println("user : "+kuser);
        return SUCCESS;
    }

    @RequestMapping(value = "/test/testMap")
    public String testMap(String name,
                       String password,
                        String email) {
        System.out.println("name: "+name);
        System.out.println("password: "+password);
        System.out.println("email: "+email);
        return SUCCESS;
    }

    @RequestMapping("/helloWorld")
    public String hello() {
        System.out.println("hello test");
        return SUCCESS;
    }
}
