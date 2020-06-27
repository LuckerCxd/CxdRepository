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
@TestAnnotation(happyAdj = "", happyAnnotation = @TestAnnotation1, happyDegree = 0, happyName = { "" }, happySeason = Season.fall)
@SuppressWarnings("all")
public class Calculate {
	private int a=5,b=3;
	private String name="helloWorld";
	public String ya = "a";
	public String ua = "a";
	public String ia = "a";
	public static int age = 10;
	
	/**
	 * @param a 整数
	 * @param b 整数
	 * @return a+b的和
	 */
	
//	@TestAnnotation(happyDegree = 5,happyAdj = "nice",happyName = {"lihua","gutianle"},happyAnnotation = @TestAnnotation1,happySeason=Season.spring)
	public int add(int a,int b) {
		return a+b;
	}
	
	
	
	
	/**
	 * @param a 整数
	 * @param b 整数
	 * @return a-b的差
	 */
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
		return "Calculate [a=" + a + ", b=" + b + ", name=" + name + ", ya=" + ya + ", ua=" + ua + ", ia=" + ia + "]";
	}
	
	public static void main(String[] args) throws Exception {	
		Class class1 = Class.forName("Day_8.Calculate");
		Class class2 = Day_8.Calculate.class;

		Calculate calculate = new Calculate();
		Class class3 = calculate.getClass();
		System.out.println(class1 == class2 && class2 == class3);
		
	}
}
