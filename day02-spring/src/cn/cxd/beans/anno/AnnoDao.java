package cn.cxd.beans.anno;

import cn.cxd.impl.IAnnoDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.annotation.Inherited;

@Repository("annoDaoName")
@Scope("prototype")
public class AnnoDao implements IAnnoDao {

    @PostConstruct
    public void selfInit(){
        System.out.println("init-stand for xml-init-Method");
    }

    @PreDestroy
    public void selfDestroy(){
        System.out.println("destroy-stand for xml-destroy-Method");
    }

    public AnnoDao() {
        System.out.println("constructor AnnoDao: "+this);
    }


    @Override
    public void add(){
        System.out.println("AnnoDao add,real end add: "+this);
    }
}
