package cn.cxd.beans;

public class ComparedUser implements Comparable<ComparedUser>{
	private String name;
	private int count;
	
	public ComparedUser(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	@Override
	public int compareTo(ComparedUser o) {
		if(this.getCount() < o.getCount())
			return 1;
		else if(this.getCount() > o.getCount())
			return -1;
		return 0;
	}
	@Override
	public String toString() {	
		return "Íæ¼ÒÃû : "+name +"\t\t\t³É¼¨: "+count;
	}
}
