package cn.cxd.beans;

import cn.cxd.impl.ICar;
import cn.cxd.impl.IManager;

import java.util.*;

public class ManagerImpl implements IManager {
    private String name;
    private int age;
    private double salary;
    private ICar car;


    private ICar[] carsArray;
    private List<ICar> carsList;
    private Set<ICar> carsSet;
    private Map<Integer,ICar> carsMap;
    private Properties carsProperties;
    private String[] girlFriendsNames;
    private String[] dogsNames;

    public String[] getGirlFriendsNames() {
        return girlFriendsNames;
    }

    public void setGirlFriendsNames(String[] girlFriendsNames) {
        this.girlFriendsNames = girlFriendsNames;
    }

    public String[] getDogsNames() {
        return dogsNames;
    }

    public void setDogsNames(String[] dogsNames) {
        this.dogsNames = dogsNames;
    }

    public ManagerImpl(String name, String[] girlFriendsNames) {
        this.name = name;
        this.girlFriendsNames = girlFriendsNames;
    }

    public ManagerImpl(String name, ICar[] carsArray) {
        this.name = name;
        this.carsArray = carsArray;
    }

    public ManagerImpl(String name, int age, double salary, ICar car) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.car = car;
    }

    public ManagerImpl(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public ManagerImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public ManagerImpl(String name, int age, ICar car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public ManagerImpl(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


    public ManagerImpl(String name) {
        this.name = name;
    }

    public Set<ICar> getCarsSet() {
        return carsSet;
    }

    public void setCarsSet(Set<ICar> carsSet) {
        this.carsSet = carsSet;
    }

    public Map<Integer, ICar> getCarMap() {
        return carsMap;
    }

    public void setCarMap(Map<Integer, ICar> carsMap) {
        this.carsMap = carsMap;
    }

    public Properties getCarsProperties() {
        return carsProperties;
    }

    public void setCarsProperties(Properties carsProperties) {
        this.carsProperties = carsProperties;
    }

    @Override
    public String toString() {
        String carString = "nullCar";
        if(car != null){
            carString = car.toString();
        }

        String carsArrayElements = "";
        String carsListElements = "";
        String carsSetElements = "";
        String carsPropertiesElements = "";
        String girlsNamesString = "";
        String dogsNamesString = "";
        String carsMapElement = "";

        if(carsArray != null){
            for (ICar car : carsArray) {
                carsArrayElements += car.toString();
            }
        }
        if(carsList != null){
            for (ICar car : carsList) {
                carsListElements += car.toString();
            }
        }
        if(carsSet != null){
            for (ICar car : carsSet) {
                carsSetElements += car.toString();
            }
        }

        if(carsMap != null){
            Set<Integer> integers = carsMap.keySet();
            for ( Integer integer: integers) {
                carsMapElement = carsMapElement +" "+ carsMap.get(integer).toString();
            }
        }

        if(carsProperties != null){
            Enumeration<Object> elements = carsProperties.elements();
            while(elements.hasMoreElements()){
                carsPropertiesElements = carsPropertiesElements + " "+elements.nextElement();
            }
        }
        if(girlFriendsNames != null){
            for (String girlName:girlFriendsNames
                 ) {
                girlsNamesString = girlsNamesString +" "+ girlName;
            }
        }

        if(dogsNames != null){
            for(String dogName:dogsNames){
                dogsNamesString =dogsNamesString+" "+ dogName;
            }
        }

        return "name :"+name+"\nage:"+age+"\nsalary:"+salary+"\ncar:"+carString+"\nArrayList: "+
                carsArrayElements+"\nList: "+carsListElements+"\nSet: "+carsSetElements+"\nMap:"+carsMapElement+"\nProperties: "
                +carsPropertiesElements+"\ngirlFriendsNames:"+girlsNamesString+"\ndogsNames:"+dogsNamesString;
    }

    public ManagerImpl(String name, List<ICar> carsList) {
        this.name = name;
        this.carsList = carsList;
    }

    public ManagerImpl(String name, ICar car) {
        this.name = name;
        this.car = car;
    }

    public List<ICar> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<ICar> carsList) {
        this.carsList = carsList;
    }

    @Override
    public void parseSituation() {
        System.out.println();
    }
}
