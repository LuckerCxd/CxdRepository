package cn.cxd.annotation.controller;

import cn.cxd.annotation.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:17 2020/5/8
 * @Modified By:
 */
@Controller
public class CustomController {
    private MyService myService;

    @Autowired
    public void setCustomService(@Qualifier("service") MyService myService) {
        this.myService = myService;
    }

    public MyService getCustomService() {
        return myService;
    }
}
