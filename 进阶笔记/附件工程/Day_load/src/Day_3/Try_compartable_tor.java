package Day_3;

import java.util.Arrays;
import java.util.Comparator;

public class Try_compartable_tor {
	public static void main(String[] args) {
		Ddog[] lotOfDogs = new Ddog[10];
		for(int i = 0;i < 10 ;i++) {
			if(i % 2 == 0)
				lotOfDogs[i] = new Ddog(i, "HelloKitties"+String.valueOf(i), Double.parseDouble(String.valueOf(1.0+i)));
			else {
				lotOfDogs[i] = new Ddog(i, "HelloKitties"+String.valueOf(i*4), Double.parseDouble(String.valueOf(1.0+i*4)));
			}
		}
		Arrays.sort(lotOfDogs,new AgeCompator());
		for(int i = 0;i < 10 ;i++) {
			System.out.println(lotOfDogs[i].toString());
		}
	}
}

class Ddog implements Comparable<Ddog>{
	private int age;
	private String name;
	private double kilogram;
	
	public Ddog(int age, String name, double kilogram) {
		super();
		this.age = age;
		this.name = name;
		this.kilogram = kilogram;
	}
	
	@Override
	public int compareTo(Ddog o) {
		return this.name.compareTo(o.name);
	}
	
	@Override
	public String toString() {
		return "name : "+name + "      age : "+age +"     kg : "+kilogram;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public double getKilogram() {
		return kilogram;
	}
	

	
}

class AgeCompator implements Comparator<Ddog>{
	@Override
	public int compare(Ddog o1, Ddog o2) {
		Integer o1age = o1.getAge();
		Integer o2age = o2.getAge();
		return o1age.compareTo(o2age);
		
	}
}

class KgCompator implements Comparator<Ddog>{
	@Override
	public int compare(Ddog o1, Ddog o2) {
		Double o1kg = o1.getKilogram();
		Double o2kg = o2.getKilogram();
		return o1kg.compareTo(o2kg);
	}
}



