package Day_1;

public class array_len {
	public static void main(String[] args) {
		try {
			Class.forName("Day_1.multi_arrays").getDeclaredMethod("main",args.getClass()).invoke(null,(Object)new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		array_len.test1();
		
	}
	public static void test1() {
		int[] array_int;
		int[] test_array = null;
		array_int = new int[0];
		System.out.println(array_int);
		System.out.println(test_array);
		
	}
	
}
