package cn.cxd.exceptionHandler;

import cn.cxd.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:33 2020/6/26
 * @Modified By:
 */
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView testExceptionHandle(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        System.out.println("ArithmeticException");
        modelAndView.addObject("excepteion",ex);
        return modelAndView;
    }


}
