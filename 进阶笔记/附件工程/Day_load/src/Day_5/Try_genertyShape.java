package Day_5;

import java.util.ArrayList;

public class Try_genertyShape {
	public static void main(String[] args) {
		String aString = "hello";
		Box<String> sBox = new Box<String>(aString);	
		sBox.getContent();
		
		aString  = "world";
		sBox.getContent();
		
		Integer integer = 5;
		Box<Integer> iBox  = new Box<Integer>(integer);
		iBox.getContent();
		integer  =  new Integer(6);
		iBox.getContent();
		
		D5_Dog tempDog =  new D5_Dog(1, "abby");
		Box<D5_Dog>dBox = new Box(tempDog);
		DDDofDog te =  new DDDofDog(tempDog);
		
		System.out.println("dbox: "+dBox.getContent());
		System.out.println("dddofdog: "+te);
		tempDog = new D5_Dog(2, "awway");
		System.out.println("dbox: "+dBox.getContent());
		System.out.println("dddofdog: "+te);
		ArrayList<D5_Dog> tempList = new ArrayList<>();
	}
}


class Box<T>{
	private T t;
	public Box(T t) {
		this.t = t;
	}
	public T getContent() {
		System.out.println(t);
		return t;
	}
	public void cat() {
		if(this.t instanceof Integer) {
			System.out.println("it num");
		}
	}
}

class DDDofDog{
	private D5_Dog d5_Dog ;

	public DDDofDog(D5_Dog d5_Dog) {
		super();
		this.d5_Dog = d5_Dog;
	}

	@Override
	public String toString() {
		return "DDDofDog [d5_Dog=" + d5_Dog + "]";
	}
	
}

class D5_Dog{
	private int age;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public D5_Dog(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString() {
		return "D5_Dog [age=" + age + ", name=" + this.name + "]";
	}
	
}
