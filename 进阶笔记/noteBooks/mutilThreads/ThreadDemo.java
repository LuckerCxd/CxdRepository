package noteBooks.mutilThreads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
	public static void main(String[] args) throws Exception{
		System.out.println("1.继承thread");
		new MyThreadExtendsThread("线程A").start();
		new MyThreadExtendsThread("线程B").start();
		new MyThreadExtendsThread("线程C").start();
		
		System.out.println("2.实现runable");
		Thread aThread = new Thread(new MyThreadImplRunnable("runA"));
		Thread bThread = new Thread(new MyThreadImplRunnable("runB"));
		Thread cThread = new Thread(new MyThreadImplRunnable("runC"));
		aThread.start();
		bThread.start();
		cThread.start();
		
		/*
		 * //Futrue可以监视目标线程调用call的情况，当你调用Future的get()方法以获得结果时，当前线程就开始阻塞，直接call方法结束返回结果
		 */
		System.out.println("3.实现callable");
		FutureTask<String> futureTask = new FutureTask<>(new MyThreadImplCallable());
		new Thread(futureTask).start();
		System.out.println("result_get:"+futureTask.get());
		
		
		
		System.out.println("4.并发资源的访问");
		MyThreadEntity mt = new MyThreadEntity(); // 定义资源对象
		new Thread(mt).start(); // 第一个线程启动
		new Thread(mt).start(); // 第二个线程启动
		new Thread(mt).start(); // 第三个线程启动
	}
}
class MyThreadExtendsThread extends Thread{
	private String titile;

	public MyThreadExtendsThread(String titile) {
		this.titile = titile;
	}
	@Override
	public void run() { // 【方法覆写】线程方法
	for (int x = 0; x < 10; x++) {
		System.out.println(this.titile + "运行，x = " + x);
		}
	}
}


class MyThreadImplRunnable implements Runnable { // 线程的主体类
	private String title; // 成员属性
	public MyThreadImplRunnable(String title) {  // 属性初始化
		this.title = title;
		}
	@Override
	public void run() { // 【方法覆写】线程方法
	for (int x = 0; x < 10; x++) {
		System.out.println(this.title + "运行，x = " + x);
		}
	}
}


class MyThreadImplCallable implements Callable<String> {  // 定义线程主体类
	@Override
	public String call() throws Exception {  // 线程执行方法
		for (int x = 0; x < 10; x++) {
			System.out.println("********* 线程执行、x = " + x);
		}
		return "www.mldn.cn"; // 返回结果
	}
}

class MyThreadEntity implements Runnable { // 线程的主体类
	private int ticket = 5;  // 定义总票数
	@Override
	public void run() { // 线程的主体方法
		for (int x = 0; x < 100; x++) {  // 进行100次的卖票处理
			if (this.ticket > 0) {  // 有剩余票
				System.out.println("卖票，ticket = " + this.ticket--);
			}
		}
	}
}