package Try_sig.Fac;


public class Responsibler {
	public static void main(String[] args) {
		MilitaryPosition militaryPosition = new Soldier();
		MilitaryPosition lieutenant = new Lieutenant();
		MilitaryPosition major = new Major();
		militaryPosition.setNextPosition(lieutenant);
		lieutenant.setNextPosition(major);
		
//		System.out.println(lieutenant.affairLevel);
		
		militaryPosition.receiveMessage(0, "做饭");
		militaryPosition.receiveMessage(2, "买武器");
		militaryPosition.receiveMessage(1, "训练士兵");
	}
}
abstract class MilitaryPosition{
	protected MilitaryPosition nextMilitaryPosition;
	protected int affairLevel;
	public void setNextPosition(MilitaryPosition nextMilitaryPosition) {
		this.nextMilitaryPosition = nextMilitaryPosition;
	};
	public void receiveMessage(int level,String message) {
		if(affairLevel >= level) {
			dealAffair(message);
		}
		else if(nextMilitaryPosition != null) {
			nextMilitaryPosition.receiveMessage(level, message);
		}
	}
	public abstract void dealAffair(String message);
}
class Soldier extends MilitaryPosition{
	@Override
	public void dealAffair(String message) {
		System.out.println("士兵可以解决:"+message);
	}
}
class Lieutenant extends MilitaryPosition{
	public Lieutenant() {
		affairLevel = 1;
	}
	@Override
	public void dealAffair(String message) {
		System.out.println("少尉可以解决:"+message);
	}
	
}
class Major extends MilitaryPosition{
	public Major() {
		affairLevel = 2;
	}
	@Override
	public void dealAffair(String message) {
		System.out.println("将军可以解决:"+message);
	}
}
