package Day_4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Try_clone {
	public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException, ClassNotFoundException  {
		//clone
		DateBy GoodDay = new DateBy(12,3);
		Dog_D4 d1 = new Dog_D4("abby", 1, 5.4);
		d1.setBirthday(GoodDay);
		Dog_D4 d2 = (Dog_D4) d1.clone();
		d2.getBirthday().setDate(9);
		d2.getBirthday().setMonth(3);
		System.out.println(d1.toString());	
		System.out.println(d2.toString());
		
		
		//序列化
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\Java\\testSerialize.txt"));
		objectOutputStream.writeObject(d1);
		
		//反序列化
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\Java\\testSerialize.txt"));
		d2 = (Dog_D4)objectInputStream.readObject();
		
		
		
		System.out.println(d1.toString());	
		System.out.println(d2.toString());
		
		
		
	}
	
}


class Dog_D4 implements Cloneable,Serializable{
	private String name;
	private int age;
	private double kg;
	private transient DateBy birthday;
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public DateBy getBirthday() {
		return birthday;
	}
	
	public void setBirthday(DateBy mbirth) {
		birthday = mbirth;
	}
	
	public double getKg() {
		return kg;
	}

	public Dog_D4(String name, int age, double kg) {
		super();
		this.name = name;
		this.age = age;
		this.kg = kg;
	}
	
	@Override
	public String toString() {
		return name+" is "+age+" kg: "+kg+" birthday : "+birthday;
	}
	
	/**
	 * 浅复制 
	 */
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}

	//深复制
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Dog_D4 temp  = (Dog_D4) super.clone();
		temp.birthday = (DateBy) getBirthday().clone();
		return temp;
	}
	
}


class DateBy implements Cloneable{
	private int month;
	private int date;
	
	public DateBy(int month, int date) {
		super();
		this.month = month;
		this.date = date;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DateBy [month=" + month + ", date=" + date + "]";
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}