package Day_3;

public class try_wrapper {
	
	
	public static void main(String[] args) {
		
		char []tt = {'c','e','t','+','e'};
		
		System.out.println(String.valueOf(15));
		
		System.out.println(String.format("itsde %d %d is nii", 100,200));
		
		String cope = String.copyValueOf(tt);
	
		
		for(String temp : cope.split("[+]")) {
			System.out.println(temp);
		}
		
		System.out.println(cope.endsWith("e"));
		
		
		
		
		
	}
}


