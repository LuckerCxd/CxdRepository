package cn.cxd.base.model;

import java.util.*;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:28 2020/5/6
 * @Modified By:
 */
public class CarStore {
    private List<Car> carsList ;
    private Map<String,Car> carHashMap;
    private Properties carProperties;

    public CarStore() {
        System.out.println("carStore Constructor..");
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }

    public void setCarHashMap(Map<String, Car> carHashMap) {
        this.carHashMap = carHashMap;
    }

    public Properties getCarProperties() {
        return carProperties;
    }

    public void setCarProperties(Properties carProperties) {
        this.carProperties = carProperties;
    }

    @Override
    public String toString() {
        return "CarStore{" +
                "carsList=" + carsList +
                ", carHashMap=" + carHashMap +
                ", carProperties=" + carProperties +
                '}';
    }
}
