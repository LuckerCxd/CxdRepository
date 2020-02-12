package cn.cxd.beans.anno;

import cn.cxd.impl.IAnnoAction;
import cn.cxd.impl.IAnnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Controller("annoActionName")
public class AnnoAction implements IAnnoAction {

    @Autowired
    public IAnnoService annoService;


    public AnnoAction() {
        System.out.println("constructor AnnoAction: "+this);
    }

    @Override
    public void add(){
        System.out.println("AnnoAction add: "+this+" "+this.annoService);
        annoService.add();
    }
}
