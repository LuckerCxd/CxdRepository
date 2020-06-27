package Day_8Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public class Problem {
	@Check
	public static void add() {
		int result = 1+0;
		System.out.println("1 + 0 = "+result);
	}
	@Check
	public static void sub() {
		int result = 1-0;
		System.out.println("1 - 0 = "+result);
	}
	@Check
	public static void mul() {
		int result = 1*0;
		System.out.println("1 * 0 = "+result);
	}
	@Check
	public static void div() {
		int result = 1/0;
		System.out.println("1 / 0 = "+result);
	}
	public static void main(String[] args) throws Exception {
		Class class1 = Problem.class;
		Method[] methods = class1.getMethods();
		BufferedWriter bw = new BufferedWriter(new FileWriter(".\\bugs.txt"));
		for(Method m:methods) {
			if(m.isAnnotationPresent(Check.class)) {
				try{
					m.invoke(class1);
				} catch (Exception e) {	
					bw.append(m.getName() + "() 出现异常");
					bw.newLine();
					bw.append("异常名称:  "+ e.getCause().getClass().getSimpleName());
					bw.newLine();
					bw.append("异常原因：  "+e.getCause().getMessage());
					bw.newLine();
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
