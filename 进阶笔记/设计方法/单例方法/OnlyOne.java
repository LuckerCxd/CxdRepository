package Try_sig.Fac;

public class OnlyOne {
	private static  OnlyOne instance = new OnlyOne();
	private OnlyOne() {};
	public static OnlyOne getInstance() {
		return instance;
	}
	
	
	public static void main(String[] args) {
		System.out.println(OnlyOne.getInstance());
		System.out.println(OnlyOne.getInstance());
		System.out.println(OnlyOne.getInstance());
		System.out.println(OnlyOne.getInstance());
	}
}
