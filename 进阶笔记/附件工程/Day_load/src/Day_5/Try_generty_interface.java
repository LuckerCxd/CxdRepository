package Day_5;

import java.util.Random;

public class Try_generty_interface {
	public static void main(String[] args) {
		NoDefineInterface<Integer> interface1 =  new NoDefineInterface(5);
		NoDefineInterface<D5_Dog> interface2 = new NoDefineInterface(new D5_Dog(2, "niuniu"));
		System.out.println(interface2.toString());
		System.out.println(interface1.next().toString());
		
		DefineInterface interface3 =  new DefineInterface("ae");
		System.out.println(interface3.next());
	}
}

class NoDefineInterface<T> implements generator<T>{
	private T t;
	@Override
	public T next() {
		if(t instanceof Integer)	
			return (T) new Integer(new Random().nextInt(100));
		return null;
	}
	public NoDefineInterface(T t) {
		this.t  = t;
		if (t instanceof Integer) {
			System.out.println("Integer type");
		}
	}
	@Override
	public String toString() {
		return "NoDefineInterface [t=" + t + "]";
	}
	
}


class DefineInterface implements generator<String>{
	private String string = null;
	@Override
	public String next() {
		return "abc";
	}
	public DefineInterface(String string) {
		super();
		this.string = string;
	}

}




