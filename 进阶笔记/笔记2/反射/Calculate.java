package Day_8;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.DayOfWeek;
import java.util.Properties;


/**
 * @since 1.5
 * @author 23516
 * @version 1.0
 */
public class Calculate {
	private int a=5,b=3;
	private String name="helloWorld";
	public String ya = "a";
	public String ua = "a";
	public String ia = "a";
	public static int age = 10;
	
	public int add(int a,int b) {
		return a+b;
	}
	public int sub(int a,int b) {
		return a-b;
	}
	private String info() {
		return "it private func";
	}
	public Calculate(String name,int priority) {
		this.name = name;
		System.out.println(name+" is a priority "+priority+" calculator");
	}
	public Calculate(String name,String priority) {
		this(name,Integer.parseInt(priority));
	}
	
	public Calculate() {
		super();
	}
	@Override
	public String toString() {
		return "Calculate [a=" + a + ", b=" + b + ", name=" 
			+ name + ", ya=" + ya + ", ua=" + ua + ", ia=" + ia + "]";
	}
	public static void main(String[] args) throws Exception {	
		Class class1 = Class.forName("Day_8.Calculate");
		Class class2 = Day_8.Calculate.class;

		Calculate calculate = new Calculate();
		Class class3 = calculate.getClass();
		System.out.println(class1 == class2 && class2 == class3);
		
	}
}
	
	

