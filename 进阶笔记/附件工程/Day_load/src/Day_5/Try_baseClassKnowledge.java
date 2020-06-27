package Day_5;

public class Try_baseClassKnowledge {
	public static void main(String[] args) {
		String abc = "ahhapy";
		shoes tShoes = new shoes(100, abc, 42);
		System.out.println(tShoes.toString());
		abc  = "newby";
		shoes ttShoes = new shoes(tShoes);
		System.out.println(ttShoes.toString());
		System.out.println(tShoes.equals(ttShoes));
		
	}
}
class shoes{
	private int price;
	private String shape;
	private int size;
	
	public shoes(int price, String shape, int size) {
		super();
		this.price = price;
		this.shape = shape;
		this.size = size;
	}
	
	public shoes(shoes tempshoes) {
		this.price = tempshoes.price;
		this.shape = tempshoes.shape;
		this.size = tempshoes.size;
	}
	
	@Override
	public String toString() {
		return "shoes [price=" + price + ", shape=" + shape + ", size=" + size + "]";
	}

	
	
}