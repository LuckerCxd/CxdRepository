package Try_sig.Fac;

public class IteratorMain {
	public static void main(String[] args) {
		String[] names= {"Lucy","Habby","Kathy"};
		Container nameContainer = new NameContainer(names);
		Iterator nameIterator = nameContainer.getIterator();
		while(nameIterator.hasNext()) {
			System.out.println(nameIterator.next());
		}
	}
}
class NameContainer implements Container{
	private String[] names ;
	public NameContainer(String[] names) {
		this.names = names;
	}
	@Override
	public Iterator getIterator() {
		return new NameIterator(names);
	}
}
class NameIterator implements Iterator{
	private String[] names;
	private int index ;
	public NameIterator(String []names) {
		this.names = names;
	}
	@Override
	public boolean hasNext() {
		if(index < names.length)
			return true;
		return false;
	}
	@Override
	public Object next() {
		if(hasNext())
			return names[index++];
		return null;
	}
}