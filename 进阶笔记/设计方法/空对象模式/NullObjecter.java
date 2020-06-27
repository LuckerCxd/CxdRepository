package Try_sig.Fac;

public class NullObjecter {
	public static void main(String[] args) {
		ProductFactory productFactory = new ProductFactory();
		Product product = productFactory.getProduct("小说");
		System.out.println(product.getName());
		product = productFactory.getProduct("文具盒");
		System.out.println(product.getName());
	}
}
abstract class Product{
	protected String productName;
	public String getName() {
		return "已拥有商品： "+productName;
	}
}
class MeaningProduct extends Product{
	public MeaningProduct(String productName) {
		this.productName = productName;
	}
}
class NullProduct extends Product{
	@Override
	public String getName() {
		return "Invalid Product Name";
	}
}
class ProductFactory{
	private String[] productNames = {"笔记本","计算器","文具盒"};
	public Product getProduct(String productName) {
		for(String s:productNames) {
			if(s.equals(productName)) {
				return new MeaningProduct(productName);
			}	
		}
		return new NullProduct();
	}
}