package Try_sig.Fac;

public class Briger {
	public static void main(String[] args) {
		Shape redSquare = new Square(10, new RedFill());
		redSquare.printColor();
		Shape blueRectangle = new Rectangle(10, new BlueFill());
		blueRectangle.printColor();
	}
}
class RedFill implements FillColor{
	@Override
	public void fill(float degree) {
		System.out.println("Red,choose color degree:"+degree);
	}
}

class BlueFill implements FillColor{
	@Override
	public void fill(float degree) {
		System.out.println("Blue,choose color degree:"+degree);
	}
	
}
abstract class Shape{
	public FillColor fillColor;
	public abstract void printColor();
}
class Rectangle extends Shape{
	private float tdegree;
	public Rectangle(float degree,FillColor color) {
		fillColor = color;
		this.tdegree = degree;
	}
	@Override
	public void printColor() {
		System.out.print("Rectangle:");
		fillColor.fill(tdegree);
	}
}
class Square extends Shape{
	private float tdegree;
	public Square(float degree,FillColor color) {
		fillColor = color;
		this.tdegree = degree;
	}
	@Override
	public void printColor() {
		
		System.out.print("Square:");
		fillColor.fill(tdegree);
	}
}

