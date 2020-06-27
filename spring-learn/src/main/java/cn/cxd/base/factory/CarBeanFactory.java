package cn.cxd.base.factory;

import cn.cxd.base.model.Car;

import java.util.HashMap;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 15:09 2020/5/7
 * @Modified By:
 */
public class CarBeanFactory {
    public CarBeanFactory() {
        System.out.println("carBeanFactory 's constructor..");
    }

    public static HashMap<String , Car> carHashMap;
    static {
        carHashMap = new HashMap<>();
        carHashMap.put("AudiA8",new Car("AudiA8",280000,280));
        carHashMap.put("AudiA9",new Car("AudiA9",290000,290));
        System.out.println("static block has done");
    }
    public static Car getCarFromMapS(String brand){
        return carHashMap.get(brand);
    }

    public Car getCarFromMapI(String brand){
        return carHashMap.get(brand);
    }
}
