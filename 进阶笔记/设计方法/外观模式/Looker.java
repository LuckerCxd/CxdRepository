package Try_sig.Fac;

public class Looker {
	public static void main(String[] args) {
		AllComponent allComponent = new AllComponent();
		allComponent.StartAll();
	}
}
class KeyBoard implements Component{
	@Override
	public void start() {
		System.out.println("KeyBoard has started");
	}
}
class Mouse implements Component{
	@Override
	public void start() {
		System.out.println("Mouse has started");
	}
}
class Screen implements Component{
	@Override
	public void start() {
		System.out.println("Screen has started");
	}
}
class AllComponent {
	private Component mouse;
	private Component keyboard;
	private Component screen;
	public AllComponent() {
		this.mouse = new Mouse();
		this.keyboard = new KeyBoard();
		this.screen = new Screen();
	}
	public void StartAll(){
		mouse.start();
		keyboard.start();
		screen.start();
	}
	
}