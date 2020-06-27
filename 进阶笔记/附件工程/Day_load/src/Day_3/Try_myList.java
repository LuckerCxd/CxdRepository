package Day_3;

import java.util.Arrays;

public class Try_myList {
	private Object[] objects;
	private int size = 0;
	private int length = 0;
	public Try_myList() {
		size = 10;
		objects = new Object[size];
	}
	public Try_myList(int initsize) throws Exception {
		if(initsize <= 0) 
			throw new Exception("size can't less than 1");
		else {
			size = initsize;	
			objects = new Object[size];
		}
		
			
	}
	
	public void checkInitsize(int index) throws Exception {
		if(index >= size)
			throw new Exception("index out of range");	
	}
	
	public void add(int index,Object object) {
		try {
			checkInitsize(index);
			if(length == size-1) {
				size *= 3;
				Object[] myObjects = new Object[size];
				System.arraycopy(objects, 0, myObjects,0, index);
				myObjects[index] = object;
				System.arraycopy(objects,index,myObjects,index+1,objects.length-index);
				objects = myObjects;
			}
			else {
				System.arraycopy(objects,index,objects,index+1,length-index);
				objects[index] = object;
			}
			length++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void add(Object object) {
		if(length == size-1) {
			size *= 3;
			Object[] myObjects = new Object[size];
			System.arraycopy(objects,0,myObjects,0,objects.length);
			myObjects[objects.length] = object;
			objects = myObjects;
		}
		else {
			objects[length] = object;
		}
		length++;
	}
	
	public void remove(Object object) {
		int index = 0;
		for(index = 0;index < objects.length;index ++) {
			if (objects[index].equals(object)) {
				System.arraycopy(objects,index+1,objects,index,objects.length-index-1);
				length--;
				break;
			}
		}
	}
	
	public void remove(int index) {
		try {
			checkInitsize(index);
			System.arraycopy(objects,index+1,objects,index,objects.length-index-1);
			length--;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object get(int index) {
		try {
			checkInitsize(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects[index];
	}
	
	public Object set(int index,Object object) {
		try {
			checkInitsize(index);
			objects[index] = object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects[index];
	
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	public int getSize() {
		return size;
	}

	public int getLength() {
		return length;
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(objects, length);
	}
	
	@Override
	public String toString() {
		StringBuffer mBuffer = new StringBuffer();
		for (Object temp : objects) {
			mBuffer.append(temp.toString());
		}
		return mBuffer.toString();
	}

	public static void main(String[] args) {
		Try_myList testList = new Try_myList();
		System.out.println("length "+testList.getLength() +"  size: "+testList.getSize());
		testList.add("hello");
		testList.add("him");
		testList.add("he");
		testList.add("her");
		testList.add("she");
		testList.add(0,new Dog());
		testList.add(2,new Dog());
		testList.add(4,new Dog());
		testList.add(6,new Dog());
		testList.add(8,new Dog());
		System.out.println("length "+testList.getLength() +"  size: "+testList.getSize());
		System.out.println(testList.get(1));
		System.out.println(testList.get(3));
		testList.remove(9);
		System.out.println("length "+testList.getLength() +"  size: "+testList.getSize());
		for (Object temp : testList.toArray()) {
			System.out.println(temp.toString());
		}
		
		
	}
}


class Dog {
	
}








