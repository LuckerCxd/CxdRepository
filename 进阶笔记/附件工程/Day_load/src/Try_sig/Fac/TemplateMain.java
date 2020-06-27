package Try_sig.Fac;

public class TemplateMain {
	public static void main(String[] args) {
		House house = new Hotel();
		house.pass();
	}
}
abstract class House{
	public abstract void enter();
	public abstract void quiz();
	public final void pass() {
		enter();
		quiz();
	}
}
class Hotel extends House{
	@Override
	public void enter() {
		System.out.println("���������Ǹ��������Ƶ���");
	}
	@Override
	public void quiz() {
		System.out.println("�������������ĵ��߳��Ƶ���");
	}
}
class Factory extends House{
	@Override
	public void enter() {
		System.out.println("����ȥ�����ϰ���");
	}
	@Override
	public void quiz() {
		System.out.println("�����ڴӹ����������");
	}
}
