package cn.cxd.beans;

import cn.cxd.impl.ICar;

public class NiceCarImpl implements ICar {
    private String carSimbol;
    private String numberID;
    public static int beanAge = 21;

    public String getCarSimbol() {
        return carSimbol;
    }

    public void setCarSimbol(String carSimbol) {
        this.carSimbol = carSimbol;
    }

    public String getNumberID() {
        return numberID;
    }

    public void setNumberID(String numberID) {
        this.numberID = numberID;
    }

    @Override
    public void runCar() {
    }

    @Override
    public String toString() {
        return "NiceCarImpl{" +
                "carSimbol='" + carSimbol + '\'' +
                ", numberID='" + numberID + '\'' +
                '}';
    }
}
