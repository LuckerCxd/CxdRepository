package Try_sig.Fac;

public class TemplateMain {
	public static void main(String[] args) {
		House house = new Hotel();
		house.pass();
	}
}
abstract class House{
	public abstract void enter();
	public abstract void quiz();
	public final void pass() {
		enter();
		quiz();
	}
}
class Hotel extends House{
	@Override
	public void enter() {
		System.out.println("他来啦，那个男人来酒店啦");
	}
	@Override
	public void quiz() {
		System.out.println("他走啦，静悄悄地走出酒店了");
	}
}
class Factory extends House{
	@Override
	public void enter() {
		System.out.println("他又去工厂上班了");
	}
	@Override
	public void quiz() {
		System.out.println("他终于从工厂里出来了");
	}
}
