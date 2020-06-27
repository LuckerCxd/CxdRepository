package cn.cxd.base.factoryBean;

import cn.cxd.base.model.Car;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 17:41 2020/5/7
 * @Modified By:
 */
public class CarFactoryBean implements FactoryBean<Car> {
    @Override
    public Car getObject() throws Exception {
        return new Car("AudiB1",400000,400);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
