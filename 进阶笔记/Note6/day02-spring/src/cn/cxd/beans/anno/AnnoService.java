package cn.cxd.beans.anno;

import cn.cxd.impl.IAnnoDao;
import cn.cxd.impl.IAnnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AnnoService implements IAnnoService {

    @Autowired
    private IAnnoService iAnnoService;

    @Autowired
    @Qualifier("annoDaoName")
    private IAnnoDao annoDao;

    @Autowired
    @Qualifier("annoDaoName")
    private IAnnoDao annoDao3;

    @Autowired
    @Qualifier("annoDaoName")
    private IAnnoDao annoDao2;

    public AnnoService() {
        System.out.println("constructor AnnoService: "+ this+" "+this.iAnnoService);
    }

    @Override
    public void add(){
        System.out.println("Service add: "+this+" "+this.iAnnoService);
        annoDao.add();
        annoDao2.add();
        annoDao3.add();
    }
}
