package cn.cxd.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:22 2020/6/14
 * @Modified By:
 */

@Controller
public class CustomView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }
    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.getWriter().write("CustomView hello");
    }
}
