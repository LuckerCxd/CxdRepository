package Day_1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class System_copy {
	public static void main(String[] args) {
//		int[] a = {1,2,3,4,5};
//		int[] b = null;
		String[] a = {"a","bb","ccc"};
		String[] b = null;
		ara_syscopy(a,b);
		ara_copyof(a,b);
		ara_copyofRange(a,b);
		ara_clone(a,b );
		System.out.println(Arrays.toString(args));
		System.out.println("---------end of progmer----------");
	}
	public static void ara_syscopy(String[]a,String[]b) {
		b = new String[10];
		System.arraycopy(a, 0, b, 2, a.length);
		System.out.println( Arrays.toString(b) );
		
	}
	public static void ara_copyof(String[]a,String[]b) {
		b = Arrays.copyOf(a, 6);
		System.out.println(Arrays.toString(b));
	}
	
	public static void ara_copyofRange(String[]a,String[]b) {
		b = Arrays.copyOfRange(a, 1, 7);
		System.out.println(Arrays.toString(b));
	}
	public static void ara_clone(String[]a,String[]b){
		b = a.clone();
		System.out.println(Arrays.toString(b));
	}
}
