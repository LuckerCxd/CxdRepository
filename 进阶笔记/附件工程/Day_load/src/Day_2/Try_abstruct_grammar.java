package Day_2;

abstract class animal{
	private static int tea ;
	private static String like;
	static {
		tea = 5;
		like = "i like rea tea";
		System.out.println("func : initial class");
	}
	
	public abstract void sayHello();
	
	public animal() {
		System.out.println("abstract constructor");
	}
	public void eat() {
		System.out.println("eat");
	}
	@Override
	public String toString() {

		return getClass().getName()+"   tea num = "+tea+"  "+like;
	}
}
class people extends animal{
	public people() {
		System.out.println("people constructor");
	}

	@Override
	public void sayHello() {
		System.out.println("Call me back to drink tea");
	}
	
	
}
public class Try_abstruct_grammar {
	public static void main(String[] args) {
		people xiaoMing = new people();
		System.out.println(xiaoMing);
		xiaoMing.sayHello();
	}
}
