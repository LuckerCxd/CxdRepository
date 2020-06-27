package Day_7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Try_Stream {
	public static void main(String[] args) {
		try(FileOutputStream fileOutputStream = new FileOutputStream(new File(".\\neRWtext.txt"))) {
//			System.out.println(Files.exists(Paths.get(".\\neRWtext.txt")));
			// 1. copy 可复制文本文件、图像文件
//			Files.copy(Paths.get(".\\RWtext.txt"),fileOutputStream);	
//			如果不存在，输出流会自己新建出目标文件
			
			
			//2. delete
//			Files.delete(Paths.get(".\\tt.txt"));
			//delete 不可以删除不存在的文件..报错.返回类型为void   ---> 可以使用 deleteIfExists文件 返回值为boolean
//			System.out.println(Files.deleteIfExists(Paths.get(".\\tt.txt")));
			
			//3.getFileStore 文件所在盘符  返回类型：FileStore
//			System.out.println(Files.getFileStore(Paths.get(".\\RWtext.txt")));
			
			//4.getLastModifiedTime
//			System.out.println(Files.getLastModifiedTime(Paths.get(".\\RWtext.txt")));
			
			//5.getOwner
//			System.out.println(Files.getOwner(Paths.get(".\\RWtext.txt")));
			
			//6.存在path的equal
//			System.out.println(Paths.get("D:\\XD学习\\MYcode\\工具箱_j\\Collection_foreach迭代遍历笔记.txt").equals(Paths.get("D:\\XD学习\\MYcode\\工具箱_j\\Collection_foreach迭代遍历笔记.txt")));
			
			//7.是否可写 isWritable
			Path path = Paths.get(".\\RWtext.txt");
//			Path path = Paths.get("D:\\XD学习\\MYhowork\\统计.xlsx");
//			System.out.println(Files.isWritable(path));
			
			//8.lines 输出
//			Stream<String> tStream = Files.lines(path,Charset.defaultCharset());
//			Iterator<String> iterator = tStream.iterator(); 
//			while(iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
//			Stream<String> tStream = Files.lines(path,Charset.defaultCharset());
//			List list = tStream.collect(Collectors.toList());
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i));
//			}
			
			
			
			//9.path.getFileName() 会带后缀名 --move移动，不检查是否存在，不在会报错，返回地址
//			Path temp = Files.move(path,Paths.get(".\\"+path.getFileName().toString()));
//			System.out.println("temp:"+temp);
//			System.out.println("fileName:"+path.getFileName());
//			for(int i=0;i<temp.getNameCount();i++) {
//				System.out.println(temp.getName(i));
//			}
			
			//10.newBufferReader   等价于下面的
//			BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.defaultCharset());
//			String string = null;
//			while((string = bufferedReader.readLine()) != null) {
//				System.out.println(string);
//			}
			
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(".\\RWtext.txt")), Charset.defaultCharset()));
//			String string = null;
//			while((string = bufferedReader.readLine()) != null) {
//				System.out.println(string);
//			}
			
			
			//11. readAllByte 没有截断..newInputStream 会..自己建立InputStream 也会.
//			byte[] data = Files.readAllBytes(path);
//			System.out.println(new String(data));
			
			
			
			//12. size可以替代 File_instance 's length 如下
//			System.out.println(Files.size(path));
//			File file = new File(".\\RWtext.txt");
//			System.out.println(file.length());
			
			//13. probeContentType  探测文件类型..但没有严格按照后缀名..可用但要了解过后再使用
//			System.out.println(Files.probeContentType(path));
//			System.out.println(Files.probeContentType(Paths.get(".\\th.jpg")));
			
			//14. readAllLines 跟8 类似 但返回的是 list 而不是流 ，但是二者都能用iterator
			List list = Files.readAllLines(path,Charset.defaultCharset());
//			Iterator iterator = list.iterator();
//			while(iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
			
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i));
//			}
			//15. write 相当于是copy了吧，返回写入的Path  1. bytes->readAllBytes  
//			  2. Iterable<? extends CharSequence> lines  -> readAllLines list ,而不是lines的steam..即便再去调用iterator()
//			System.out.println(Files.write(Paths.get(".\\neRWtext.txt"), data));
//			System.out.println(Files.write(Paths.get(".\\neRWtext.txt"), list, Charset.defaultCharset()));
			
			
//			String contents = new String(Files.readAllBytes(path),Charset.defaultCharset());
//			List<String> words = Arrays.asList(contents.split("\\PL+"));
//			
//			long count = words.stream().filter(t -> t.length() > 5).count();
//			System.out.println(count);
//			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}




