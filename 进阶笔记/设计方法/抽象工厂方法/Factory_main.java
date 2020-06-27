package Try_sig.Fac;

public class Factory_main {
	public static void main(String[] args) {
		AbstractFactory factory = FactoryProducer.getFactory("shape");
		Shape shape = factory.getShape("rectangle");
		shape.tell();
		factory = FactoryProducer.getFactory("clothes");
		Clothes clothes = factory.getClothes("shoes");
		clothes.tell();
	}
}

class Rectangle implements Shape{
	@Override
	public void tell() {
		System.out.println("rectangle");
		
	}
}

class Square implements Shape{
	@Override
	public void tell() {
		System.out.println("square");
		
	}
}


class ShapeFactory extends AbstractFactory{
	@Override
	public Shape getShape(String shapeName) {
		if(shapeName.equalsIgnoreCase("rectangle")) 
			return new Rectangle();
		if(shapeName.equalsIgnoreCase("square")) 
			return new Square();	
		return null;
	}
	
	@Override
	public Clothes getClothes(String clothesName) {
		return null;
	}
}

class Shirt implements Clothes{
	@Override
	public void tell() {
		System.out.println("shirt");
	}
}

class Shoes implements Clothes{
	@Override
	public void tell() {
		System.out.println("shoes");
	}
}


abstract class AbstractFactory{
	public abstract Shape getShape(String shapeName);
	public abstract Clothes getClothes(String clothesName);
}

class ClothesFactory extends AbstractFactory{
	@Override
	public Shape getShape(String shapeName) {
		return null;
	}
	
	@Override
	public Clothes getClothes(String clothesName) {
		if(clothesName.equalsIgnoreCase("shirt"))
			return new Shirt();
		if(clothesName.equalsIgnoreCase("shoes"))
			return new Shoes();
		return null;
	}
	
}

class FactoryProducer{
	public static AbstractFactory getFactory(String factoryName) {
		if(factoryName.equalsIgnoreCase("shape"))
			return new ShapeFactory();
		if(factoryName.equalsIgnoreCase("clothes"))
			return new ClothesFactory();
		return null;
	}
}
