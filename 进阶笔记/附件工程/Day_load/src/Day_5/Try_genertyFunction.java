package Day_5;

public class Try_genertyFunction {
	public static void main(String[] args) {
		Try_genertyFunction try_genertyFunction = new Try_genertyFunction();
		System.out.println(try_genertyFunction.foo("abc"));
	}
	public <T> T foo(T t) {
		return t;
	}
}


