package cn.cxd.base.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:24 2020/5/6
 * @Modified By:
 */
public class Person {
    private String name;
    private Car car;
    private String city;
    private String address;

    public Person(String name, Car car, String city, String address) {
        this.name = name;
        this.car = car;
        this.city = city;
        this.address = address;
    }

    public Person() {
        System.out.println("Person constructors..:"+super.toString());
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", car=" + car + " "+super.toString()+
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
