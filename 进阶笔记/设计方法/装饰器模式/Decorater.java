package Try_sig.Fac;

public class Decorater {
	public static void main(String[] args) {
		Picture blacker = new BlackenDecorate(new CartoonPicture());
		blacker.display();
	}
}
class ScenePicture implements Picture{
	@Override
	public void display() {
		System.out.println("beautiful scenery picture");
	}
}
class CartoonPicture implements Picture{
	@Override
	public void display() {
		System.out.println("cute cartoon picture");
	}
}
abstract class EffectedDecorate implements Picture{
	protected Picture originalPicture;
	public EffectedDecorate(Picture originalPicture) {
		this.originalPicture = originalPicture;
	}
}
class WhiteningDecorate extends EffectedDecorate{
	public WhiteningDecorate(Picture originalPicture) {
		super(originalPicture);
	}
	@Override
	public void display() {
		originalPicture.display();
		System.out.println("whiten it");
	}
}
class BlackenDecorate extends EffectedDecorate{
	public BlackenDecorate(Picture originalPicture) {
		super(originalPicture);
	}
	@Override
	public void display() {
		originalPicture.display();
		System.out.println("blacken it");
	}
}