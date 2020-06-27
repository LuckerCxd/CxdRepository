package Try_sig.Fac;

public class StateMain {
	public static void main(String[] args) {
		Product product = new Product();
		StartSell startSell = new StartSell();
		startSell.doAction(product);
	}
}
class StartSell implements State{
	@Override
	public void doAction(Product product) {
		System.out.println("it start sell");
		product.setState(this);
		System.out.println(product.getState());	
	}
}
class SellOut implements State{
	@Override
	public void doAction(Product product) {
		System.out.println("it Sell out");
		product.setState(this);
		System.out.println(product.getState());
	}
}
class Product{
	private State state;
	private String name;
	private float sale;
	public Product() {
		name = "±Ê¼Ç±¾";
		sale = 11;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
}