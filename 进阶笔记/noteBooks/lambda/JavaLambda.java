package noteBooks.lambda;


public class JavaLambda {
	public static void main(String[] args) {
		
		Imessage imessage = (st)->{
			System.out.println(st);
		};
		
		
		// �ȼ���ʹ�������ڲ��� ʵ����һ������Ψһ�����ĳ���ӿڣ�
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