package noteBooks.mutilThreads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
	public static void main(String[] args) throws Exception{
		System.out.println("1.�̳�thread");
		new MyThreadExtendsThread("�߳�A").start();
		new MyThreadExtendsThread("�߳�B").start();
		new MyThreadExtendsThread("�߳�C").start();
		
		System.out.println("2.ʵ��runable");
		Thread aThread = new Thread(new MyThreadImplRunnable("runA"));
		Thread bThread = new Thread(new MyThreadImplRunnable("runB"));
		Thread cThread = new Thread(new MyThreadImplRunnable("runC"));
		aThread.start();
		bThread.start();
		cThread.start();
		
		/*
		 * //Futrue���Լ���Ŀ���̵߳���call��������������Future��get()�����Ի�ý��ʱ����ǰ�߳̾Ϳ�ʼ������ֱ��call�����������ؽ��
		 */
		System.out.println("3.ʵ��callable");
		FutureTask<String> futureTask = new FutureTask<>(new MyThreadImplCallable());
		new Thread(futureTask).start();
		System.out.println("result_get:"+futureTask.get());
		
		
		
		System.out.println("4.������Դ�ķ���");
		MyThreadEntity mt = new MyThreadEntity(); // ������Դ����
		new Thread(mt).start(); // ��һ���߳�����
		new Thread(mt).start(); // �ڶ����߳�����
		new Thread(mt).start(); // �������߳�����
	}
}
class MyThreadExtendsThread extends Thread{
	private String titile;

	public MyThreadExtendsThread(String titile) {
		this.titile = titile;
	}
	@Override
	public void run() { // ��������д���̷߳���
	for (int x = 0; x < 10; x++) {
		System.out.println(this.titile + "���У�x = " + x);
		}
	}
}


class MyThreadImplRunnable implements Runnable { // �̵߳�������
	private String title; // ��Ա����
	public MyThreadImplRunnable(String title) {  // ���Գ�ʼ��
		this.title = title;
		}
	@Override
	public void run() { // ��������д���̷߳���
	for (int x = 0; x < 10; x++) {
		System.out.println(this.title + "���У�x = " + x);
		}
	}
}


class MyThreadImplCallable implements Callable<String> {  // �����߳�������
	@Override
	public String call() throws Exception {  // �߳�ִ�з���
		for (int x = 0; x < 10; x++) {
			System.out.println("********* �߳�ִ�С�x = " + x);
		}
		return "www.mldn.cn"; // ���ؽ��
	}
}

class MyThreadEntity implements Runnable { // �̵߳�������
	private int ticket = 5;  // ������Ʊ��
	@Override
	public void run() { // �̵߳����巽��
		for (int x = 0; x < 100; x++) {  // ����100�ε���Ʊ����
			if (this.ticket > 0) {  // ��ʣ��Ʊ
				System.out.println("��Ʊ��ticket = " + this.ticket--);
			}
		}
	}
}