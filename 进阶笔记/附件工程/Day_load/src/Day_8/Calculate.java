package Day_8;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Properties;

@TestAnnotation(happyDegree = 5,className = "Day_8.Calculate",methodsName = {"add","sub"},happyAnnotation = @TestAnnotation1,happySeason=Season.spring)
@SuppressWarnings("all")
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
		Class class1 = Calculate.class;
		TestAnnotation testAnnotation = (TestAnnotation) class1.getAnnotation(TestAnnotation.class);
		Class class2 = Class.forName(testAnnotation.className());
		Object object = class2.getConstructor(String.class,int.class).newInstance("√¿Õ≈",11);
		Method method = class2.getMethod(testAnnotation.methodsName()[0],int.class,int.class);
		Method method1 = class2.getMethod(testAnnotation.methodsName()[1],int.class,int.class);
		System.out.println(method.invoke(object, 3,4));
		System.out.println(method1.invoke(object, 3,4));
	}
}
