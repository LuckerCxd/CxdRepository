package cn.cxd.annotation;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:46 2020/5/8
 * @Modified By:
 */
public class BaseTService<T> {

    @Autowired
    private BaseTRepository<T> baseTRepository;

    public T save(T t){
        System.out.println(baseTRepository.getClass() +" saved data");
        return t;
    }
}
