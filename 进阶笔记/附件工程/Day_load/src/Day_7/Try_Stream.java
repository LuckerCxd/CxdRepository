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
			// 1. copy �ɸ����ı��ļ���ͼ���ļ�
//			Files.copy(Paths.get(".\\RWtext.txt"),fileOutputStream);	
//			��������ڣ���������Լ��½���Ŀ���ļ�
			
			
			//2. delete
//			Files.delete(Paths.get(".\\tt.txt"));
			//delete ������ɾ�������ڵ��ļ�..����.��������Ϊvoid   ---> ����ʹ�� deleteIfExists�ļ� ����ֵΪboolean
//			System.out.println(Files.deleteIfExists(Paths.get(".\\tt.txt")));
			
			//3.getFileStore �ļ������̷�  �������ͣ�FileStore
//			System.out.println(Files.getFileStore(Paths.get(".\\RWtext.txt")));
			
			//4.getLastModifiedTime
//			System.out.println(Files.getLastModifiedTime(Paths.get(".\\RWtext.txt")));
			
			//5.getOwner
//			System.out.println(Files.getOwner(Paths.get(".\\RWtext.txt")));
			
			//6.����path��equal
//			System.out.println(Paths.get("D:\\XDѧϰ\\MYcode\\������_j\\Collection_foreach���������ʼ�.txt").equals(Paths.get("D:\\XDѧϰ\\MYcode\\������_j\\Collection_foreach���������ʼ�.txt")));
			
			//7.�Ƿ��д isWritable
			Path path = Paths.get(".\\RWtext.txt");
//			Path path = Paths.get("D:\\XDѧϰ\\MYhowork\\ͳ��.xlsx");
//			System.out.println(Files.isWritable(path));
			
			//8.lines ���
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
			
			
			
			//9.path.getFileName() �����׺�� --move�ƶ���������Ƿ���ڣ����ڻᱨ�����ص�ַ
//			Path temp = Files.move(path,Paths.get(".\\"+path.getFileName().toString()));
//			System.out.println("temp:"+temp);
//			System.out.println("fileName:"+path.getFileName());
//			for(int i=0;i<temp.getNameCount();i++) {
//				System.out.println(temp.getName(i));
//			}
			
			//10.newBufferReader   �ȼ��������
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
			
			
			//11. readAllByte û�нض�..newInputStream ��..�Լ�����InputStream Ҳ��.
//			byte[] data = Files.readAllBytes(path);
//			System.out.println(new String(data));
			
			
			
			//12. size������� File_instance 's length ����
//			System.out.println(Files.size(path));
//			File file = new File(".\\RWtext.txt");
//			System.out.println(file.length());
			
			//13. probeContentType  ̽���ļ�����..��û���ϸ��պ�׺��..���õ�Ҫ�˽������ʹ��
//			System.out.println(Files.probeContentType(path));
//			System.out.println(Files.probeContentType(Paths.get(".\\th.jpg")));
			
			//14. readAllLines ��8 ���� �����ص��� list �������� �����Ƕ��߶�����iterator
			List list = Files.readAllLines(path,Charset.defaultCharset());
//			Iterator iterator = list.iterator();
//			while(iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
			
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i));
//			}
			//15. write �൱����copy�˰ɣ�����д���Path  1. bytes->readAllBytes  
//			  2. Iterable<? extends CharSequence> lines  -> readAllLines list ,������lines��steam..������ȥ����iterator()
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




