package Day_5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Try_generator<T> implements generator<T>{
	private Class<T> type;
	@Override
	public T next() {
		try {
			Constructor<T> constructor = type.getDeclaredConstructor(new Class[] {int.class,String.class});
			try {
				return constructor.newInstance(new Object[]{2,"a"});
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public Try_generator(Class<T> type) {
		super();
		this.type = type;
	}
	public static <T>Try_generator<T> create_Generator(Class<T> type){
		return new Try_generator<>(type);
	}
	public static void main(String[] args) {
		Try_generator<D5_Dog> temp = Try_generator.create_Generator(D5_Dog.class);
		
		System.out.println(temp.next());
	}
}
