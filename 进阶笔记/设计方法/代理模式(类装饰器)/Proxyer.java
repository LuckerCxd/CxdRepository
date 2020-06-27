package Try_sig.Fac;

public class Proxyer {
	public static void main(String[] args) {
		Picture picture = new ProxyPicture("aaaa");
		picture.display();
	}
}
class RealPicture implements Picture{
	private String fileName;	
	public RealPicture(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public void display() {
		System.out.println("Display real picture");
	}
}
class ProxyPicture implements Picture{
	private RealPicture realPicture;
	public ProxyPicture(String fileName) {
		realPicture = new RealPicture(fileName);
	}
	@Override
	public void display() {
		System.out.println("at proxy");
		realPicture.display();
	}
}