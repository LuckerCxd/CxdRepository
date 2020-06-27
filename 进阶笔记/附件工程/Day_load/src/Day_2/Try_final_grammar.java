package Day_2;

final class  Employee{
	public final int salary ; 
	private int superAbility;
	public Employee() {
		salary = 0;
	}
	public  void sayHello() {
		System.out.println("hello, good morning,i am employee");
	}
	public static void playGame() {
		System.out.println("it is for employee Games");
	}
	public Employee(int x) {
		salary = x;
		superAbility = 8;
		superAbility = 9;
	}
	@Override
	public String toString() {
		return getClass().getName()+"  salary :"+String.valueOf(salary)+"  superAbility :"+String.valueOf(superAbility) ;
	}
}


public class Try_final_grammar {
	public static void main(String[] args) {
		Employee t1 =  new Employee(8000);
		System.out.println(t1);
	}
}
