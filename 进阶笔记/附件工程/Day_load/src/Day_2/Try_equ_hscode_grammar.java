package Day_2;

import java.util.zip.Inflater;

import javax.print.attribute.ResolutionSyntax;

class D2_Employee{
	private String name;
	private String ID;
	private int salary;
	public D2_Employee() {
		System.out.println("hello");
	}
	public D2_Employee(String name, String ID, int salary) {
		super();
		this.name = name;
		this.ID = ID;
		this.salary = salary;
	}
	
	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(!(obj instanceof D2_Employee))
			return false;
		D2_Employee tEmployee = (D2_Employee)obj;
		return ID.equals(tEmployee.ID) && name.equals(tEmployee.name) && salary ==tEmployee.salary;
	}
	@Override
	public int hashCode() {
		return ID.hashCode();
	}
	
	public int getSalary() {
		return salary;
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}
	
}

class D2_Manager extends D2_Employee{
	private double bonus;
	public D2_Manager(String name, String ID, int salary,double bonus) {
		super(name, ID, salary);
		this.bonus = bonus;
	}
	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof D2_Manager))
			return false;
		D2_Manager temp = (D2_Manager)obj;
		return bonus == temp.bonus;
		
	}
	@Override
	public int hashCode() {
		return String.valueOf(bonus).hashCode();
	}
}


public class Try_equ_hscode_grammar {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		D2_Employee p1 = new D2_Employee("XiaoMing", "201325", 8000);
		D2_Employee p2 = new D2_Employee("XiaoLILI", "201325", 8000);
		System.out.println(p1.equals(p2));
		
		D2_Manager m1 = new D2_Manager("XiaoMing", "201325", 8000,1.2);
		D2_Manager m2 = new D2_Manager("XiaoMing", "201325", 8000,1.2);
		System.out.println(m1.equals(m2));
		
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		
		Object object = Class.forName("Day_2.D2_Employee").newInstance();
		System.out.println(((D2_Employee)object).getName());
		
		
	}
}
