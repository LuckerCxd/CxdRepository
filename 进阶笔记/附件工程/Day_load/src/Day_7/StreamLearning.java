package Day_7;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamLearning {
	public static <T> void show(String title,Stream<T> stream) {
		final int size = 10;
		List<T> firstElements = stream.limit(size+1).collect(Collectors.toList());
		System.out.println(title + ": ");
		for(int i = 0;i < firstElements.size();i++) {
			if(i > 0) System.out.print(",");
			if(i < size) System.out.print(firstElements.get(i));
			else	System.out.println("...");
		}
		System.out.println();
	}
	
	public static <T> void concatMap(String title,Stream<T>stream) {
		final int size = 10;
		
		@SuppressWarnings("rawtypes")
		List<Stream> firstElements = (List<Stream>) stream.limit(size+1).collect(Collectors.toList());
		
		System.out.println("title" +": ");
		for(int i = 0; i < firstElements.size(); i++) {
			if (i > 0)	System.out.print(",");
			if(i < size ) {
				
				@SuppressWarnings("unchecked")
				List<T> list = (List<T>) firstElements.get(i).collect(Collectors.toList());
				int tempsize = list.size();
				for (int j = 0; j < tempsize ; j++) {
					if (j > 0)	System.out.print(",");
					System.out.print(list.get(j));
				}
			}
			else	System.out.print("...");
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("C:\\Users\\23516\\Desktop\\temppp.txt");
		String contents = new String(Files.readAllBytes(path),Charset.defaultCharset());
		
		
		String[] seasons = {"spring","summer","fall","winter"};
//		Stream<String> stream = Stream.of(seasons);
//		List<String> list = stream.collect(Collectors.toList());
//		for(String string : list) {
//			System.out.println(string);
//		}
//		Stream<String> stream = Arrays.stream(seasons,0,4);
//		Object[] temp =  stream.toArray();
//		for(Object obj :temp) {
//			System.out.println(obj);
//		}
//		String[] strings = stream.toArray(String[]::new);
//		for(String string:strings) {
//			System.out.println(string);
//		}
//		
//		List<String> list = Arrays.asList(seasons);
//		Stream<String> stream = list.stream();
//		Iterator<String>iterator = stream.iterator();
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		Stream<String> stream = Stream.empty();
//		List<String> slist =  stream.collect(Collectors.toList());
//		Set<String> set = stream.collect(Collectors.toSet());
//		for(String string : slist) {
//			System.out.println(string);
//		}
		
//		Stream<String> stream = Stream.of(contents.split("\\PL+"));
//		show("words",stream);		
//		show("word", Arrays.stream(contents.split("\\PL+")));
		
//		show("empty", Stream.empty());
		
//		List<String> list = Arrays.asList(contents.split("\\PL+"));
//		show("word", list.stream());
		
		
//		Stream<Integer> stream = Stream.iterate(randomNum(), n-> randomNum());
//		List<Integer> list = stream.limit(10).collect(Collectors.toList());
//		for(int i = 0; i < list.size();i++) {
//			if(i > 0) System.out.print(",");
//			if(i < list.size()) System.out.print(list.get(i));
//			else	System.out.print("...");
//		}
		
//		show("generator",Stream.generate(()->randomNum()));
//		show("generator",Stream.generate(StreamLearning::randomNum));
//		show("generator", Stream.generate(Math::random));
		
//		show("iterator", Stream.iterate(BigInteger.ONE,n -> n.add(BigInteger.TEN)));
//		show("iterator", Stream.iterate(randomNum(), n-> n.valueOf(randomNum())));
		
//		Stream<String> stream = Stream.of(seasons).filter(t -> t.length() > 2).map(t -> t.toUpperCase());
//		List<String> list = stream.limit(1).collect(Collectors.toList());
//		for(int i = 0; i < list.size();i++) {
//			if(i > 0) System.out.print(",");
//			if(i < list.size()) System.out.print(list.get(i));
//			else	System.out.print("...");
//		}
		
//		show("word",Stream.of(contents.split("\\PL+")).filter(t -> t.length() > 5));
//		show("word",Stream.of(contents.split("\\PL+")).filter(t -> t.length() > 5).map(t ->t.toUpperCase()));
//		show("word",Stream.of(contents.split("\\PL+")).filter(t -> t.length() > 5).flatMap(t ->letters(t)));
//		concatMap("word",Stream.of(contents.split("\\PL+")).filter(t -> t.length() > 5).map(t ->letters(t)));
//		show("temp", letters("hello"));
		
//		show("limit", Stream.generate(() -> randomNum()).limit(3));
		
		
		
//		Stream<String> stream = Stream.concat(letters(seasons[0]), letters(seasons[1]));
//		List<String> list = stream.collect(Collectors.toList());
//		for(int i = 0; i < list.size();i++) {
//			if(i > 0) System.out.print(",");
//			if(i < list.size()) System.out.print(list.get(i));
//			else	System.out.print("...");
//		}
		
//		show("word",Stream.concat(letters("hello"),letters("world")) );
		
//		show("word",Stream.concat(letters("hello"),letters("world")).distinct() );
		
//		Stream<String> stream = Stream.concat(letters(seasons[0]), letters(seasons[1])).distinct().sorted();
//		List<String> list = stream.collect(Collectors.toList());
//		for(int i = 0; i < list.size();i++) {
//			if(i > 0) System.out.print(",");
//			if(i < list.size()) System.out.print(list.get(i));
//			else	System.out.print("...");
//		}
		
//		show("word", Stream.of(contents.split("\\PL+")).sorted());
//		show("word", Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).map(t -> t.toUpperCase()).distinct().sorted(new LengthComparator()));
//		show("word", Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).map(t -> t.toUpperCase()).distinct().sorted(new LengthComparator().reversed()));
//		show("word", Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).map(t -> t.toUpperCase()).distinct().sorted(Comparator.comparing(String::length).reversed()));
		
//		Stream<String> stream = Stream.of(seasons).distinct().sorted(Comparator.comparing(String::length).reversed());
//		List<String> list = stream.collect(Collectors.toList());
//		for(int i = 0; i < list.size();i++) {
//			if(i > 0) System.out.print(",");
//			if(i < list.size()) System.out.print(list.get(i));
//			else	System.out.print("...");
//		}
		
//		Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).map(t -> t.toUpperCase()).distinct().sorted(new LengthComparator()).peek(t -> System.out.println(t.toString()));
//		Stream stream = Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).peek(t -> System.out.println(t.toString()));
//		List list = (List) stream.collect(Collectors.toList());
		
		
//		Stream<String> stream = Stream.of(seasons).filter(t -> t.length() > 4).peek(t -> System.out.println(t));
//		List<String> list = stream.collect(Collectors.toList());
//		for(int i = 0; i < list.size();i++) {
//			if(i > 0) System.out.print(",");
//			if(i < list.size()) System.out.print(list.get(i));
//			else	System.out.print("...");
//		}
		
		 //没有collect(Collectors.toList())、迭代   如果没有获取元素..peek不使用
//		List list = Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).peek(t -> System.out.println(t.toString())).collect(Collectors.toList());
//		Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).map(t -> t.toUpperCase()).sorted(new LengthComparator()).distinct().peek(t -> System.out.println(t.toString())).collect(Collectors.toList());
		
		
//		List<String> list = Stream.of(seasons).filter(t -> t.length() > 4).peek(t -> System.out.println(t)).collect(Collectors.toList());
//		Stream<String> stream = Stream.of(seasons).filter(t -> t.length() > 4).peek(t -> System.out.println(t));
//		Iterator<String>iterator = stream.iterator();
//		while(iterator.hasNext()) {
//			iterator.next();
//		}
		
		
//		Stream stream = Stream.of(contents.split("\\PL+")).filter(t -> t.length()>5).peek(t -> System.out.println(t.toString()));
//		Iterator iterator = stream.iterator();
//		while(iterator.hasNext()) {
//			iterator.next();
//		}
		
		
		
		
		
	}
	public static int randomNum(){
		try {
			return Random.class.newInstance().nextInt(20);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
	public static Stream<String> letters(String s) {
		 List<String> result = new ArrayList<>();
		 for(int i=0;i < s.length();i++) {
			 result.add(s.substring(i, i+1));
		 }
		 return result.stream();
	}
}
