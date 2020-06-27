package Try_sig.Fac;

public class Strategyer {
	public static void main(String[] args) {
		Context context = new Context();
		context.setStrategy(new PowNum());
		context.executeStrategy(11);
		
	}
}
class PowNum implements Strategy{
	@Override
	public int doAction(int num) {
		return (int) Math.pow(num, 2);
	}
}
class AddOne implements Strategy{
	@Override
	public int doAction(int num) {
		return num+1;
	}
}
class Context{
	private Strategy strategy;
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public void executeStrategy(int num) {	
		System.out.println(strategy.doAction(num));
	}
}