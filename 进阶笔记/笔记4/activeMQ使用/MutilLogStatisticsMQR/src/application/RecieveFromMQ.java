package application;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class RecieveFromMQ {
	public static final int MAX_DATE_SUM = 100;
	public static boolean finishConsumer = false;
	public static ExecutorService fService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start "+new SimpleDateFormat("HH:mm:ss.SSS").format(System.currentTimeMillis()));
		int consumeNums = 4;
		
		// 光消费，不能两个一起启动run
		for(int i=0;i<consumeNums;i++) {
			fService.execute(new receiveThread(new Receiver(MAX_DATE_SUM/consumeNums)));			
		}
		fService.shutdown();


	}
	
	
	
}


class receiveThread extends Thread{
	private Receiver receiver = null;


	public receiveThread(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void run() {
		receiver.ConnectMQAndReceiveMessage();
	}
}