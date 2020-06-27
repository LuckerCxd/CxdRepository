package Try_sig.Fac;

import java.util.ArrayList;
import java.util.List;

public class OrderMain {
	public static void main(String[] args) {
		Product product = new Product();
		Invoker invoker = new Invoker();
		invoker.addOrder(new SellOrder(product));
		invoker.addOrder(new BuyOrder(product));
		invoker.executeOrderList();
	}
}
class Product{
	private String name = "p30";
	private String quality = "S";
	public void buy(){
		System.out.println(name +"("+" "+quality+"级别"+")"+" buy it");
	}
	public void sell() {
		System.out.println(name +"("+" "+quality+"级别"+")"+" sell it");
	}
}
class SellOrder implements Order{
	private Product product;
	public SellOrder(Product product) {
		this.product = product;
	}
	@Override
	public void execute() {
		product.sell();
	}
}
class BuyOrder implements Order{
	private Product product;
	public BuyOrder(Product product) {
		this.product = product;
	}
	@Override
	public void execute() {
		product.buy();
	}
}
class Invoker{
	private List<Order> orderList = new ArrayList<>();
	public void addOrder(Order order) {
		orderList.add(order);
	}
	public void executeOrderList() {
		for(Order o:orderList) {
			o.execute();
		}
		orderList.clear();
	}
}