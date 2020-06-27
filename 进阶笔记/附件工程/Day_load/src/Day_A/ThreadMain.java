package Day_A;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import Day_8.TestAnnotation;

public class ThreadMain {
	public static void main(String[] args) throws Exception {
		
	}
	
	@Test
	public void test() throws Exception{
		Mediation mediation = new Mediation();
		ExtendThread myThread = new ExtendThread("Exended",mediation);
		myThread.start();
		Thread.sleep(1000);
		myThread.flag = false;
	}
	
	@Test
	public void test1() throws Exception{
		Mediation mediation = new Mediation();
		RunThread1 myThread1 = new RunThread1(mediation);
		Thread proxy = new Thread(myThread1,"tiger");
		Thread proxy1 = new Thread(myThread1,"rabbit");
		proxy1.start();
		proxy.start();
		Thread.sleep(1000);
		myThread1.flag = false;
	}
	
	
	@Test
	public void test2() throws Exception{
		Mediation mediation = new Mediation();
		CallThread callThread = new CallThread(mediation);
		ExecutorService eService = Executors.newCachedThreadPool();
		Future<Integer> future  = eService.submit(callThread);
		Future<Integer> future1  = eService.submit(callThread);
		Thread.sleep(1000);
		callThread.flag = false;
		System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
		System.out.println(future1.get(1000,TimeUnit.MILLISECONDS));
		eService.shutdown();
		if(!eService.awaitTermination(8*1000, TimeUnit.MILLISECONDS)){  
	         eService.shutdownNow();  
	     }  
	}
	
	@Test
	public void test3() throws Exception{
		Mediation mediation = new Mediation();
		CallThread callThread = new CallThread(mediation);			
		FutureTask<Integer> result  = new FutureTask<>(callThread);
		FutureTask<Integer> result1  = new FutureTask<>(callThread);
		Thread thread = new Thread(result,"tiger");
		Thread thread1 = new Thread(result1,"rabbit");
		thread.start();
		thread1.start();	
		Thread.sleep(1000);
		callThread.flag = false;
		System.out.println(result.get(1000,TimeUnit.MILLISECONDS));
		System.out.println(result1.get(1000,TimeUnit.MILLISECONDS));
		
	}
	
	@Test
	public void test4() throws Exception{
		System.out.println(Thread.currentThread().getName());
		CallThread callThread = new CallThread(new Mediation());
		ExecutorService eService = Executors.newCachedThreadPool();
		Future<Integer> future = eService.submit(callThread);
		
		for(int i=0;i<500;i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
		
//		callThread.flag = false;
		System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
		
		eService.shutdown();
		if(!eService.awaitTermination(8*1000, TimeUnit.MILLISECONDS)){  
	         eService.shutdownNow();  
	     }  
	}

		

	@Test
	public void test5() throws Exception{
		boolean flag = true;
		BaoziStore baoziStore = new BaoziStore();
		Productor productor = new Productor("老张菜包子铺","厦门", baoziStore);
		Consumer consumer = new Consumer("唐老板", "酒店", baoziStore);
		
		ExecutorService eService = Executors.newCachedThreadPool();
		Future<Integer> productResult  = eService.submit(productor);
		Future<Integer> consumerResult = eService.submit(consumer);
		
		Thread.sleep(1000);  
		//当前线程阻塞1秒后置flag为false，且要先于get执行，否则会阻塞。
		productor.energetic = false;
		consumer.hungry = false;

		while(flag) {
			if(productResult.isDone() && consumerResult.isDone())
				flag = false;
		}
		int result = productResult.get(1000,TimeUnit.MILLISECONDS);	
		System.out.println("共生产了： "+result);
		System.out.println("共消费了： "+consumerResult.get(1000,TimeUnit.MILLISECONDS));
		
		eService.shutdown();
		if(!eService.awaitTermination(8*1000, TimeUnit.MILLISECONDS)){  
	         eService.shutdownNow();  
	     }  

	
	}
	
	@Test
	public void test6() throws Exception{
		boolean flag = true;
		BaoziStore baoziStore = new BaoziStore();
		Productor productor = new Productor("老张菜包子铺","厦门", baoziStore);
		Productor productor1 = new Productor("小白菜包子铺","福建", baoziStore);
		Consumer consumer = new Consumer("唐老板", "酒店", baoziStore);
				
		ExecutorService eService = Executors.newCachedThreadPool();
		Future<Integer> productResult  = eService.submit(productor);
		Future<Integer> consumerResult = eService.submit(consumer);
		Future<Integer> productResult1  = eService.submit(productor1);
		
		Thread.sleep(1000);
		productor.energetic = false;
		productor1.energetic = false;
		consumer.hungry = false;
		
		
		while(flag) {
			if(productResult.isDone() && consumerResult.isDone()&& productResult1.isDone())
				flag = false;
		}
		
		int result = productResult.get(1000,TimeUnit.MILLISECONDS)+productResult1.get(1000,TimeUnit.MILLISECONDS);
		System.out.println("共生产了： "+result);
		System.out.println("共消费了： "+consumerResult.get(1000,TimeUnit.MILLISECONDS));
		
		eService.shutdown();
		if(!eService.awaitTermination(8*1000, TimeUnit.MILLISECONDS)){  
	         eService.shutdownNow();  
	     }  
	
	}
	
	
}

	
class Consumer implements Callable<Integer>{
	private BaoziStore baoziStore;
	private Baozi baozi; //用于减少produce方法中的引用
	public String name;
	public String local;
	public boolean hungry = true;
	public int eatenNum ;
	public Consumer(String name, String local,BaoziStore baoziStore) {
		this.name = name;
		this.local = local;
		this.baoziStore = baoziStore;
	}
	public void consume() throws Exception {
		baozi = baoziStore.outBaozis(this);
		eatenNum += 1;	
	}
	@Override
	public Integer call() throws Exception {
		while (hungry) {
			Thread.sleep(200);
			consume();
		}
		return Integer.valueOf(eatenNum);
	}
}

class Productor implements Callable<Integer>{
	private BaoziStore baoziStore;
	private Baozi baozi; //用于减少produce方法中的引用
	public String name;
	public String local;
	public int productedNum;
	public boolean energetic = true;
	public Productor(String name, String local,BaoziStore baoziStore) {
		this.name = name;
		this.local = local;
		this.baoziStore = baoziStore;
	}
	public void produce() {
		baozi = new Baozi("菜包", 1.5);
		baoziStore.inBaozis(baozi,this);
		productedNum += 1;
		
	}
	@Override
	public Integer call() throws Exception {
		while (energetic) {
			Thread.sleep(10);
			produce();
		}
		return Integer.valueOf(productedNum);
	}
}


class BaoziStore {
	private List<Baozi> baozis = new ArrayList<Baozi>();
	public boolean flag = true; 
	
	public synchronized void inBaozis(Baozi baozi,Productor productor) {
		if(baozis.size() >= 20) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		baozis.add(baozi);
		System.out.println(productor.local + " "+ 
		productor.name +" 生产了 "+baozi+" "+getBaozisSize());
		if(baozis.size() > 0) {
			this.notify();
		}
	}
	public synchronized Baozi outBaozis(Consumer consumer) {
		if(baozis.size() <= 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Baozi baozi =  baozis.remove(baozis.size()-1);
		System.out.println(consumer.local + " "+ 
		consumer.name +" 消费了 "+baozi+" "+getBaozisSize());
		if(baozis.size() < 20) {
			this.notify();
		}
		return baozi;
	}
	public int getBaozisSize() {
		return baozis.size();
	}
}


/*
class BaoziStore {
	private List<Baozi> baozis = new ArrayList<Baozi>();
	private boolean flag = true; 
	//先生产后等待，ture:生产者生产，消费者等待，
	//false：消费者消费，生产者等待
	public synchronized void inBaozis(Baozi baozi,Productor productor) {
		if(!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//生产
		baozis.add(baozi);
		System.out.println(productor.local + " "+ 
		productor.name +" 生产了 "+baozi+" "+getBaozisSize());
		
		//生产完了就flag ,唤醒消费者(唤醒线程),flag 取false
		this.notify();
		this.flag = false;
	}
	public synchronized Baozi outBaozis(Consumer consumer) {
		if(flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//消费
		Baozi baozi =  baozis.remove(baozis.size()-1);
		System.out.println(consumer.local + " "+ 
		consumer.name +" 消费了 "+baozi+" "+getBaozisSize());
		
		//消费完了就flag ,唤醒生产者(唤醒线程),flag取true	
		this.notify();
		this.flag = true;
		return baozi;
	}
	public int getBaozisSize() {
		return baozis.size();
	}
}


*/


class Baozi {
	private String name;
	private double price;
	public Baozi(String name,double price) {
		this.name = name;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Baozi [name=" + name + ", price=" + price + "]";
	}
}

class Mediation{
	public int num = 500;
}

class CallThread implements Callable<Integer>{
	private Mediation mediation;
	public boolean flag = true;
	public CallThread(Mediation mediation) {
		this.mediation = mediation;
	}
	
	@Override
	public Integer call() throws Exception {
		while(flag) {
			if(mediation.num > 0) {
				try {
					go1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			else {
				break;
			}
		}
		return Integer.valueOf(5);
	}
	
	public  synchronized void go() throws InterruptedException {
		String aString;
		if(mediation.num % 2 == 0) {
			aString = Thread.currentThread().getName()  + " has num"+": "+mediation.num--;
			System.out.println(aString);
			
		}
	}
	
	//
	public void go1() throws InterruptedException {
		synchronized (Integer.valueOf(mediation.num)) {
			String aString;
			if(mediation.num % 2 == 0) {
				aString = Thread.currentThread().getName()  + " has num"+": "+mediation.num--;
				System.out.println(aString);
			}
		}
		
	}	
}



class RunThread1 implements Runnable{
	private Mediation mediation;
	public boolean flag = true;
	public RunThread1 (Mediation mediation) {
		super();
		this.mediation = mediation;
	}
	@Override
	public void run() {
		while(flag) {
			if(mediation.num > 0) {
				try {
					go1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				break;
			}
		}
			
	}
	
	public  synchronized void go() throws InterruptedException {
		String aString;
		if(mediation.num % 2 == 0) {
			aString = Thread.currentThread().getName() + "has num"+": "+mediation.num --;
			System.out.println(aString);
		}
	}
	public  void go1() throws InterruptedException {
		String aString;
		if(mediation.num % 2 == 0) {
			aString = Thread.currentThread().getName() + "has num"+": "+mediation.num --;
			System.out.println(aString);
		}
	}
}


class ExtendThread extends Thread{
	private Mediation mediation;
	public boolean flag = true;
	public ExtendThread(String name,Mediation mediation) {
		super(name);
		this.mediation = mediation;
	}
	@Override
	public void run() {
		while(flag) {
			if(mediation.num > 0) {
				try {
					go();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				break;
			}
		}	
	}
	
	public  synchronized void go() throws InterruptedException {
		String aString;
		if(mediation.num % 2 == 0) {
			aString = Thread.currentThread().getName() + " has num"+": "+mediation.num --;
			System.out.println(aString);
		}
	}
	
}