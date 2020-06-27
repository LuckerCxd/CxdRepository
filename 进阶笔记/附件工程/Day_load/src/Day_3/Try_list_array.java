package Day_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class Try_list_array {
	public static void main(String[] args) {
		List mlist = new ArrayList(2);
		mlist.add("you");
		mlist.add("he");
		mlist.add("him");
		mlist.add("you");
		mlist.add(1230);
		mlist.add(new foo_0());
		mlist.add(new foo_0());
		for(Object temp : mlist) {
			System.out.println(temp.toString()+" is "+temp.hashCode());
		}
		System.out.println();
		
		Collection mCollection = new ArrayList();
		mCollection.add("it");
		mCollection.addAll(mlist);
		mCollection.remove(mlist.get(2));
		for(Object temp : mCollection) {
			System.out.println(temp.toString()+" is "+temp.hashCode());
		}
		System.out.println();
		
		Object[] mObjects = new Object[100];
		mObjects = mCollection.toArray();
		
		for(Object temp : mObjects) {
			System.out.println(temp.toString()+" is "+temp.hashCode());
		}
		System.out.println();
		
		
		mlist = Arrays.asList(mObjects);
		for(Object temp : mlist) {
			System.out.println(temp.toString()+" is "+temp.hashCode());
		}
	}
}
class foo_0{
	@Override
	public String toString() {
		super.toString();
		return "foo_0  " + this.hashCode();
	}
}

