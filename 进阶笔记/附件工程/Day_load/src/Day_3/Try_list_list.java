package Day_3;

import java.util.ArrayList;
import java.util.Arrays;

public class Try_list_list {
	private int size;
	private ArrayList<ArrayList> arrayLists;
	public Try_list_list() {
		size = 10;
		arrayLists = new ArrayList<ArrayList>(size);
		for(int i=0;i<size;i++) {
			ArrayList< TestMapD3> temp = new ArrayList<TestMapD3>(10);
			arrayLists.add(temp);
		}
	}
	public Try_list_list(int initSize) throws Exception {
		if(initSize < 0)
			throw new Exception("illegel parament size");
		else {
			size = initSize;
			arrayLists = new ArrayList<ArrayList>(size);
			for(ArrayList temp : arrayLists) {
				temp = new ArrayList<TestMapD3>(10);
				arrayLists.add(temp);
			}
		}
	}
	
	/**
	 * 根据key来hashcode找下标，并判断是否存在，即不可重名key
	 * @param key
	 * @param value
	 * @throws Exception
	 */
//	public void  add(Object key,Object value) throws Exception {
//		TestMapD3 testMapD3 = new TestMapD3(key,value);
//		int arrayIndex = key.hashCode() % 9;
//		ArrayList<TestMapD3> tempArrayList = arrayLists.get(arrayIndex);
//		for(TestMapD3 tempTestMapD3:tempArrayList) {
//			if (testMapD3.key.equals(tempTestMapD3.key)) {
//				throw new Exception("key had exist,can't add again");
//			}
//		}
//		tempArrayList.add(testMapD3);
// 	}
	
	/**
	 * 根据key来hashcode找下标，但使用整体判断是否存在，即可重名key，但不能重复Node
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void  add(Object key,Object value) throws Exception {
		TestMapD3 testMapD3 = new TestMapD3(key,value);
		int arrayIndex = testMapD3.key.hashCode() % 9;
		ArrayList<TestMapD3> tempArrayList = arrayLists.get(arrayIndex);
		for(TestMapD3 tempTestMapD3:tempArrayList) {
			if (testMapD3.equals(tempTestMapD3)) {
				throw new Exception("key had exist,can't add again");
			}
		}
		tempArrayList.add(testMapD3);
 	}
	
	public Object remove(Object key) throws Exception{
		int arrayIndex = key.hashCode() % 9;
		ArrayList<TestMapD3> tempArrayList = arrayLists.get(arrayIndex);
		for(int i = 0;i < tempArrayList.size();i++) {
			if (key.equals(tempArrayList.get(i).key)) {
				return tempArrayList.remove(i); 
			}
		}
		throw new Exception("can't remove no exsit key_value");
	}
	
	
	public Object get(Object key) throws Exception{
		int arrayIndex = key.hashCode() % 9;
		ArrayList<TestMapD3> tempArrayList = arrayLists.get(arrayIndex);
		for(int i = 0;i < tempArrayList.size();i++) {
			if (key.equals(tempArrayList.get(i).key)) {
				return tempArrayList.get(i).value;
			}
		}
		throw new Exception("can't get no exsit key_value");
	}
	
	public Object set(Object key,Object value) throws Exception {
		TestMapD3 testMapD3 = new TestMapD3(key,value);
		int arrayIndex = key.hashCode() % 9;
		ArrayList<TestMapD3> tempArrayList = arrayLists.get(arrayIndex);
		for(TestMapD3 tempTestMapD3:tempArrayList) {
			if (testMapD3.key.equals(tempTestMapD3.key)) {
				tempTestMapD3.value = value;
				return tempTestMapD3;
			}
		}
		throw new Exception("can't set no exsit key_value");
	}
	
	
	public String find_key(Object key) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		int arrayIndex = key.hashCode() % 9;
		ArrayList<TestMapD3> tempArrayList = arrayLists.get(arrayIndex);
		for(int i = 0;i < tempArrayList.size();i++) {
			if (key.equals(tempArrayList.get(i).key)) {
				stringBuilder.append(tempArrayList.get(i).value+" ");
			}
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) throws Exception {
		Try_list_list myList = new Try_list_list();
		myList.add("张三", "自然科学");
		myList.add("李四", "国际政治");
		System.out.println(myList.get("张三"));
		System.out.println(myList.get("李四"));
		myList.add("张三", "北京大学");
		System.out.println(myList.find_key("张三"));
//		myList.remove("aaa");
//		System.out.println(myList.get("aaa"));
	}
}



class TestMapD3{
	Object key;
	Object value;
	
	public TestMapD3(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		TestMapD3 temp = (TestMapD3)obj;
		return this.key.equals(temp.key) && this.value.equals(temp.value);
	}
	
	@Override
	public int hashCode() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(key.toString());
		stringBuilder.append(value.toString());
		return stringBuilder.hashCode();
	}
	
	

	
}
