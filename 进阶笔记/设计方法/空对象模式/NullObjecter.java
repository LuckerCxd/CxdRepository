package Try_sig.Fac;

public class NullObjecter {
	public static void main(String[] args) {
		ProductFactory productFactory = new ProductFactory();
		Product product = productFactory.getProduct("С˵");
		System.out.println(product.getName());
		product = productFactory.getProduct("�ľߺ�");
		System.out.println(product.getName());
	}
}
abstract class Product{
	protected String productName;
	public String getName() {
		return "��ӵ����Ʒ�� "+productName;
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
	private String[] productNames = {"�ʼǱ�","������","�ľߺ�"};
	public Product getProduct(String productName) {
		for(String s:productNames) {
			if(s.equals(productName)) {
				return new MeaningProduct(productName);
			}	
		}
		return new NullProduct();
	}
}