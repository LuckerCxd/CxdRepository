package Day_1;

import java.util.Arrays;

public class multi_arrays {
	public static void main(String[] args) {	
		int[][] a = set_multi_array();
		for(int[]t : a) {
			System.out.println(Arrays.toString(t));
		}
		
	}
	
	public static int[][] set_multi_array() {
		int[][] a = new int[3][4];
		int count = 0;
		for(int[]i:a) {
			for (int j = 0;j<i.length;j++) {
				i[j] = count;
				count ++;
			}
		}
		return a;
	}
}
