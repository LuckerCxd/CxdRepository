package cn.cxd.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 8:55 2020/6/26
 * @Modified By:
 */
public class FirstInterceptor implements HandlerInterceptor {

    // 在目标方法调用前使用，return true才会继续运行下一步，乃至下一个Interceptor
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("FirstInterceptor-preHandle startTime: "+new Date());
        return true;
    }

    // 在目标方法无异常执行后、视图渲染前使用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor-postHandle endTime: "+new Date());
    }

    // preHandle返回值为true，都要执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("FirstInterceptor-afterCompletion...");
    }
}
