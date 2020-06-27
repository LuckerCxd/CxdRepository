package application;


import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class GenerateConsumerModel {
	public static BlockingQueue<Integer> buffer = new LinkedBlockingQueue<Integer>(); 
	public static AtomicInteger generateSum = new AtomicInteger(0);
	public static AtomicInteger consumeSum = new AtomicInteger(0);
	public static Boolean hasFinishConsume = false;
	public static final int MAX_DATE_SUM = 1000000;
	public static void main(String[] args) {
		System.out.println("Start "+new SimpleDateFormat("HH:mm:ss.SSS").format(System.currentTimeMillis()));
		ExecutorService fService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		int generateNums = 2;
		int consumeNums = 4;
		for(int i=0;i<generateNums;i++) {
			fService.execute(new GenerateThreadPool(MAX_DATE_SUM/generateNums));			
		}
		for(int i=0;i<consumeNums;i++) {
			fService.execute(new ConsumerThreadPool(MAX_DATE_SUM/consumeNums));			
		}
		fService.shutdown();
		
	}
}
class GenerateThreadPool implements Runnable{	
	private int dealLength = 0;
	public GenerateThreadPool(int dealLength) {
		this.dealLength = dealLength;
	}
	@Override
	public void run() {
		boolean stop = false;
		int singleLength = 0;
		while(!stop) {
			int randomValue = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
			if(GenerateConsumerModel.buffer.add(randomValue)) {
				GenerateConsumerModel.generateSum.incrementAndGet();
				singleLength += 1;
			}
			if(singleLength == dealLength) {
				stop = true;
			}
				
		}		
		
	}
}


class ConsumerThreadPool implements Runnable{	
	private int dealLength = 0;
	public ConsumerThreadPool(int dealLength) {
		this.dealLength = dealLength;
	}
	@Override
	public void run() {
		boolean stop = false;
		Integer takeRandomValue = new Integer(0);
		int singleLength = 0;
		while(!stop) {
			boolean notNull = false;
			try {
//				takeRandomValue = GenerateConsumerModel.buffer.poll(1000, TimeUnit.MILLISECONDS);
				takeRandomValue = GenerateConsumerModel.buffer.poll(1000, TimeUnit.MILLISECONDS);
				if(takeRandomValue != null) {
					GenerateConsumerModel.consumeSum.incrementAndGet();
					singleLength += 1;
					notNull = true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("GenerateThreadPool_run_put 方法异常");
			}
			
			// 仅做判断未输出
			if(notNull) {
				judgeRandomPrimed(takeRandomValue);
			}
			
			if(singleLength == dealLength) {
				stop = true;
				if(GenerateConsumerModel.consumeSum.get() == GenerateConsumerModel.MAX_DATE_SUM) {
					synchronized (GenerateConsumerModel.hasFinishConsume) {
						if(! GenerateConsumerModel.hasFinishConsume ) {
							GenerateConsumerModel.hasFinishConsume = true;
							System.out.println("end "+new SimpleDateFormat("HH:mm:ss.SSS").format(System.currentTimeMillis()));
							System.out.println("G_length:"+GenerateConsumerModel.generateSum);
							System.out.println("C_length:"+GenerateConsumerModel.consumeSum);
							System.out.println("buffer_length:"+GenerateConsumerModel.buffer.size());
					
						}
					}
				}
			}
		}		
		
	}
	
	public boolean judgeRandomPrimed(int randomValue) {
		int max = (int)Math.sqrt(randomValue);
		boolean flag = false;
		for(int i = 2;i< max ;i++) {
			if(randomValue % i == 0) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
}


