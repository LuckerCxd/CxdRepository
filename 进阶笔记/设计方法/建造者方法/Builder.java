package Try_sig.Fac;

import java.util.LinkedList;

public class Builder {
	public static void main(String[] args) {
		MenuBuilder menuBuilder = new MenuBuilder();
		Menu menu = menuBuilder.preBigHigh();
		menu.show();
		System.out.println(menu.cost());
		
	}
}

class GaoTong implements Cpu{
	@Override
	public String modelName() {
		return "高通865";
	}
	
}

class XiaoLong implements Cpu{
	@Override
	public String modelName() {
		return "骁龙975";
	}
}

class HuaWei implements Brand{
	@Override
	public String BrandName() {
		return "华为";
	}
}

class Apple implements Brand{
	@Override
	public String BrandName() {
		return "Iphone";
	}
}

abstract class Phone implements EletronicProduct{
	@Override
	public Brand brandInfo() {
		return new HuaWei();
	}
}

abstract class PC implements EletronicProduct{
	@Override
	public Brand brandInfo() {
		return new HuaWei();
	}
}

class BigPhone extends Phone{
	@Override
	public Cpu cpuInfo() {
		return new GaoTong();
	}
	@Override
	public float priceInfo() {
		return 1000;
	}
}


class FastPC extends PC{
	@Override
	public Cpu cpuInfo() {
		return new XiaoLong();
	}
	@Override
	public float priceInfo() {
		return 6000;
	}
}

class Menu{
	private LinkedList<EletronicProduct> linkedList = new LinkedList<>();
	public void add(EletronicProduct temp) {
		linkedList.add(temp);
	}
	public float cost() {
		float re = 0;
		for(EletronicProduct i:linkedList) {
			re += i.priceInfo();
		}
		return re;
	}
	public void show() {
		StringBuilder stringBuilder = new StringBuilder();
		for(EletronicProduct i:linkedList) {
			stringBuilder.append(i.cpuInfo().modelName());
			stringBuilder.append(i.brandInfo().BrandName());
			stringBuilder.append("  ");
		}
		System.out.println(stringBuilder);
	}
}

class MenuBuilder{
	public Menu preBigHigh() {
		Menu menu = new Menu();
		menu.add(new FastPC());
		menu.add(new FastPC());
		return menu;
	}
}
