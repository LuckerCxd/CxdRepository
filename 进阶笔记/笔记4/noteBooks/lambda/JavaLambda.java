package noteBooks.lambda;


public class JavaLambda {
	public static void main(String[] args) {
		
		Imessage imessage = (st)->{
			System.out.println(st);
		};
		
		
		// 等价于使用匿名内部类 实现了一个仅有唯一方法的抽象接口，
		Imessage imessage2 = new Imessage() {
			
			@Override
			public void send(String string) {
				System.out.println(string);
			}
		};
		
		imessage2.send("hloo ");
	}
}

interface Imessage{
	void send(String string);
}