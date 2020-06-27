package Day_6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Try_ThreadCreate {
	private File file = null;
	public static void main(String[] args) {
		File file = new File(".//RWtext.txt");
		Try_ThreadCreate test_Thread = new Try_ThreadCreate(file);
		Readthread tReadthread = new Readthread(test_Thread);
		Writethread writethread = new Writethread(test_Thread);
		writethread.start();
		tReadthread.start();

	}
	public File getFile() {
		return file;
	}
	
	public Try_ThreadCreate(File file) {
		super();
		this.file = file;
	}	
	
	public void write_content11() {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\neRWtext.txt",true))){ 
			if(!this.file.exists()){
				this.file.createNewFile();	
			}
			//fileWrite

			String eof = bufferedReader.readLine();
			while(eof != null) {
				bufferedWriter.write(eof);
				bufferedWriter.newLine();
				eof = bufferedReader.readLine();
			}
			System.out.println("已完成写入");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void read_content1() {
		try (BufferedReader bufferedReader= new BufferedReader(new FileReader(new File(".\\neRWtext.txt")))){
			if(!this.file.exists()){
				this.file.createNewFile();	
			}
			String eof = bufferedReader.readLine();
			while (eof != null) {
				System.out.println(eof);
				eof = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class Readthread extends Thread{
	private Try_ThreadCreate t;
	@Override
	public void run() {
		synchronized (t.getFile()) {
			t.read_content1();
		}
	}
	public Readthread(Try_ThreadCreate t) {
		this.t = t;
	}
	
}

class Writethread extends Thread{
	private Try_ThreadCreate t;
	@Override
	public void run() {
		synchronized (t.getFile()) {
			t.write_content11();
		}
	}
	public Writethread(Try_ThreadCreate t) {
		super();
		this.t = t;
	}
	
}