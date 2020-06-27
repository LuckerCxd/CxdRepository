package cn.cxd.beans.anno;

import cn.cxd.impl.IAnnoAction;
import cn.cxd.impl.IAnnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Controller
public class AnnoAction2 extends AnnoAction {


    public AnnoAction2() {
        System.out.println("constructor AnnoAction2: "+this);
    }

    @Override
    public void add(){
        System.out.println("AnnoAction2 add: "+this+" "+super.annoService);
        super.annoService.add();
    }
}
