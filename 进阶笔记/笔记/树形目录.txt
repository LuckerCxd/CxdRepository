package Try_sig.Fac;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Combiner {
	public static void createTree(Node node) {
		File file = new File(node.nodeName);
		File[] files = file.listFiles();
		for(File f:files) {
			if(f.isFile()) {
				Filer filer = new Filer(f.getAbsolutePath());
				node.addNode(filer);
			}
			if(f.isDirectory()) {
				Dictory dictory = new Dictory(f.getAbsolutePath());
				node.addNode(dictory);
				createTree(dictory);
			}
		}
	}
	public static void main(String[] args) {
		Node node = new Dictory("D:\\7-zip");
		createTree(node);
		node.display();
	}
}

abstract class Node{
	protected String nodeName;
	public abstract void display();
	public void addNode(Node node) {};
	public Node(String nodeName) {
		this.nodeName = nodeName;
	}
}

class Filer extends Node{
	public Filer(String nodeName) {
		super(nodeName);
	}
	@Override
	public void display() {
		System.out.println(nodeName);
	}
}

class Dictory extends Node{
	public Dictory(String nodeName) {
		super(nodeName);
	}
	private List<Node> nodeList = new ArrayList<>();
	
	@Override
	public void addNode(Node node) {
		nodeList.add(node);
	}
	
	@Override
	public void display() {
		System.out.println(nodeName);
		for(Node node: nodeList) {
			node.display();
		}
	}
}


