package Try_sig.Fac;

import java.util.ArrayList;
import java.util.List;

public class Mediationer {
	public static void main(String[] args) {
		Market market = new Market("天题交易行", 10);
		Trader atrader = new Trader("A", 3500);
		Trader btrader = new Trader("B", 2000);
		Product product = new Product("米其林轮胎", 1000);
		atrader.sell(market, product, btrader);
	}
}
class Product{
	public String productName;
	public float sale;
	public Product(String productName, float sale) {
		this.productName = productName;
		this.sale = sale;
	}
	
}

class Trader{
	protected List<Product> products = new ArrayList<>();
	protected float money;
	protected String name;
	public Trader(String name,float money) {
		this.money = money;
		this.name = name;
	}
	public void sell(Market market,Product product,Trader trader) {
		market.sell(this, trader, product);
	}
	
}
class Market{
	private String marketName ;
	private int credibility;
	public Market(String marketName, int credibility) {
		this.marketName = marketName;
		this.credibility = credibility;
	}
	public void sell(Trader seller,Trader buyer,Product product){
		System.out.println(buyer.name +" buy "+product.productName + " from "+seller.name+" in "+marketName);
		buyer.money -= product.sale;
		seller.money += product.sale;
		buyer.products.add(product);
		seller.products.remove(product);
	}
}
