package noteBooks.generic;

public class GenericDemo {
	public static void main(String[] args) {
		Message<String> message = new Message<String>();
		message.setContent("are you ok");
		System.out.println("1.ʹ��ʵ�����������ȡ���ݣ�"+message.getContent());
		
		IMessage<String> message2 = new MessageImpl<String>();
		System.out.println("2.���巺�ͽӿ����ಢʵ��,ʹ��ʵ�������������ȡ���ݣ�"+message2.echo("are you clear"));
		
	}
	
}
class Message<T>{
	private T content;
	public void setContent(T content) {
		this.content = content;
	}
	public T getContent() {
		return content;
	}
}
interface IMessage<T>{
	public String echo(T msg);
}
class MessageImpl<S> implements IMessage<S>{

	@Override
	public String echo(S msg) {	
		return "[ECHO]"+msg;
	}

	

	
	
}