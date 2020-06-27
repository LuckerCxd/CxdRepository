package Try_sig.Fac;

import java.util.ArrayList;
import java.util.List;

public class ObserverMain {
	public static void main(String[] args) {
		Num num = new Num();
		HexObserver hexObserver = new HexObserver(num);
		BinObserver binObserver = new BinObserver(num);
		num.addObserver(hexObserver);
		num.addObserver(binObserver);
		num.setNum(55660);
	}
}
abstract class Observer{
	protected Num num;
	public abstract void update();
}
class Num{
	private List<Observer>observers = new ArrayList();
	private int contentNum;
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void setNum(int contentNum) {
		this.contentNum = contentNum;
		System.out.println("ÊýÖµÎª: "+contentNum);
		for(Observer o:observers) {
			o.update();
		}
	}
	public int getContentNum() {
		return contentNum;
	}
}
class HexObserver extends Observer{
	public HexObserver(Num num) {
		this.num = num;
	}
	@Override
	public void update() {
		System.out.println("Hex: "+Integer.toHexString(num.getContentNum()));
	}
}
class BinObserver extends Observer{
	public BinObserver(Num num) {
		this.num = num;
	}
	@Override
	public void update() {
		System.out.println("Hex: "+Integer.toBinaryString(num.getContentNum()));
	}
}