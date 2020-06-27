package Try_sig.Fac;

import java.util.ArrayList;
import java.util.List;

public class Memenoer {
	public static void main(String[] args) {
		Product product = new Product("笔记本", (float)11.2);
		Operator operator = new Operator(product);
		System.out.println(operator.getCurrentState().toString());
		operator.saveCurrentState();
		operator.setProductState("高级笔记本", 10);
		System.out.println(operator.getCurrentState().toString());
		operator.getFormalState();
		System.out.println(operator.getCurrentState().toString());
		
	}
}
class Product{
	public String productName;
	public float sale;
	public Product(String productName, float sale) {
		this.productName = productName;
		this.sale = sale;
	}
	@Override
	public String toString() {
		return productName+" 售价："+sale;
	}
}
class StockProductState{
	private String productName;
	private float sale;
	public StockProductState(Product product) {
		productName = product.productName;
		sale = product.sale;
	}
	public Product getProduct(Product product){
		product.productName = productName;
		product.sale = sale;
		return product;
	}
}
class Operator{
	private Product product;
	private Helper helper = new Helper();
	public Operator(Product product) {
		this.product = product;
	}
	public void setProductState(String productName,float sale) {
		product.productName = productName;
		product.sale = sale;
	}
	public Product getCurrentState() {
		return product;
	}
	public void saveCurrentState() {
		helper.addStateToStates(new StockProductState(product));
	}
	public void getFormalState() {
		product = helper.getStateFromStates().getProduct(product);
	}
}
class Helper{
	private List<StockProductState> states = new ArrayList<>();
	public void addStateToStates(StockProductState state) {
		states.add(state);
	}
	public StockProductState getStateFromStates() {
		int lastOne = states.size() - 1;
		if(lastOne >= 0)
			return states.get(lastOne);
		return null;
	}
}