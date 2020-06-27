package Day_7;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		int len1 = o1.length();
		int len2 = o2.length();
		return Integer.compare(len1, len2);
	}
	
}
