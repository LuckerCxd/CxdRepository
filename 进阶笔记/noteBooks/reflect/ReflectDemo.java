package noteBooks.reflect;

public class ReflectDemo {
	public static void main(String[] args) throws Exception {
		Member member = new Member();
		Class<? extends Member> clazz = member.getClass();
		System.out.println("1.ʹ��ʵ���������Object.getClass()��ȡʵ��������: "+clazz);
		Class<? extends Member> clazz2 = Member.class;
		System.out.println("2.ʹ����.class ��ȡʵ��������: "+clazz2);
		Class<?> clazz3 = Class.forName("noteBooks.reflect.Member");
		System.out.println("3.ʹ��Class��ľ�̬����forName() ����������+������ȡʵ��������: "+clazz3);
	}
}
class Member{};