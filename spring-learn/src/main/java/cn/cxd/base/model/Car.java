package cn.cxd.base.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:21 2020/5/6
 * @Modified By:
 */
public class Car {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private double price;
    private double maxSpeed;

    public Car(){
        System.out.println("car constructor.."+super.toString());
    }



    public double getPrice() {
        return price;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public Car(String brand, double price, double maxSpeed) {
        this.brand = brand;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", maxSpeed=" + maxSpeed +
                " "+super.toString() + '}';
    }
}
