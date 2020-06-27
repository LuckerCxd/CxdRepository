package noteBooks.reflect;

public class ReflectDemo {
	public static void main(String[] args) throws Exception {
		Member member = new Member();
		Class<? extends Member> clazz = member.getClass();
		System.out.println("1.使用实例化对象的Object.getClass()获取实例化对象: "+clazz);
		Class<? extends Member> clazz2 = Member.class;
		System.out.println("2.使用类.class 获取实例化对象: "+clazz2);
		Class<?> clazz3 = Class.forName("noteBooks.reflect.Member");
		System.out.println("3.使用Class类的静态方法forName() 参数：包名+类名获取实例化对象: "+clazz3);
	}
}
class Member{};