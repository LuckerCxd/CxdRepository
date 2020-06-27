package application;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SenderToMQ {
	public static final int MAX_DATE_SUM = 100;
	public static boolean finishGenerate = false;
	public static ExecutorService fService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start "+new SimpleDateFormat("HH:mm:ss.SSS").format(System.currentTimeMillis()));
		
		int generateNums = 2;
		// ¹âÉú²ú
		for(int i=0;i<generateNums;i++) {
			fService.execute(new sendThread(new Sender(MAX_DATE_SUM/generateNums)));			
		}
		fService.shutdown();
	}
	
	
	
}

class sendThread extends Thread{
	private Sender sender = null;
	public sendThread(Sender sender) {
		this.sender = sender;
	}
	@Override
	public void run() {
		sender.ConnectMQAndSendMessage();
	}
}

