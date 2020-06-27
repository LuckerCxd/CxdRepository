package cn.cxd.beans.anno;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("annoDaoName2")
public class AnnoDao2 extends AnnoDao{
    public AnnoDao2() {
        System.out.println("constructor AnnoDao2: "+this);
    }

    public void add() {
        System.out.println("annoDao2 add :"+this);
    }

    @Override
    public void selfInit() {
        System.out.println("are you ok init 2");
    }
}
