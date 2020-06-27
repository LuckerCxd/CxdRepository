线程的共享(synchronized)

	synchronized :修饰方法，或是作用于块，但是无论如何，我们都应该视其为一个禁入条件，
	也就是说，synchronized应该使用的场景是：当一个方法有可能被并行执行时，
	而其内部有对数据进行增删改的操作时，需要被保证其进入条件，
	此时可选择使用，synchronized代码块 或是 synchronized 方法。
	形象生动地：
			我们可以视其synchronized为一个锁，
			其包裹内容视作房间，整体看做是外面带有锁的房间
	
	“当一个方法有可能被并行执行时”:
		这意味着：
			如果方法为一个实例方法，那么当我们使用该类同一个对象，
			并且有可能并行地执行该实例方法时，我们才可能使用synchronized
			如果我们是串行操作，又或者对于每一个对象，使用其自身的这个实例方法，
			那么我们是不需要添加synchronize。
			同理，当方法为类方法时，也作同样考虑，但此时主语为该类本身，而非其对象行为
	
	“作用于块”：	
		一般而言将对象this当资源：
		synchronized(this){
			...
		}
	“修饰方法”：
		public  synchronized void go()  {
			...
		}
	
举个例子说明：
	当同一对象通过自身实例方法对数值做出加减时，要保证仅有这个对象同时仅进入一个。


	
1.创建Callable接口实现类，重写call，定义synchronized方法，和普通方法

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

go():	
-	
	public  synchronized void go() throws InterruptedException {
		String aString;
		if(mediation.num % 2 == 0) {
			aString = Thread.currentThread().getName()  + " has num"+": "+mediation.num--;
			System.out.println(aString);
		}
	}

go1():	
-			
	public void go1() throws InterruptedException {
		String aString;
		if(mediation.num % 2 == 0) {
			aString = Thread.currentThread().getName()  + " has num"+": "+mediation.num--;
			System.out.println(aString);
		}
	}

go2():	
-	
	public void go2() throws InterruptedException {
		synchronized (Integer.valueOf(mediation.num)) {
			String aString;
			if(mediation.num % 2 == 0) {
				aString = Thread.currentThread().getName()  + " has num"+": "+mediation.num--;
				System.out.println(aString);
			}
		}
	}		
	

go3():
-
	public void go3() throws InterruptedException {
		synchronized (Integer.valueOf(mediation.num)) {
			String aString;
			if(mediation.num % 2 == 0) {
				aString = Thread.currentThread().getName()  + " has num"+": "+mediation.num--;
				System.out.println(aString);
			}
		}
		
	}	
	} //这个括号是整个CallThread类的	

2.通过使用Executors.newCachedThreadPool()创建ExecutorService类对象，
	再由ExecutorService类的对象，submit()同一个callable实现类的对象，
	内部调用非synchronized方法，可以观察到结果显示

	class Mediation{
		public int num = 500;
	}
		
主进程：
-
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
	
3.我们可以观察到输出结果是：

	go1():且发生了get()引发的TimeoutExcepiton
		pool-1-thread-1 has num: 499
		pool-1-thread-1 has num: 498
		pool-1-thread-2 has num: 500
	可以推测出来：thread2，thread1同时到达判断%2的位置，并且同时判断成功
	但是thread2先完成了减一操作，thread1紧追其后，但此时1获得的已经是减了1的499，
	而499是不满足于先前的%2判断的，这里是一个错误。另一个错误是，
	thread1完成了499的减一操作后，再一次使数值%2判断成功，此时，
	不管是thread1,thread2,都有机会在间隙中进入运行，并成功，
	但是如果再往后，并非都能继续借助间隙来继续执行，
	而应该是会存在时间片的长度限制。
	
4.尝试使用go(),即使用了synchronized包裹的go1函数，观察结果：

		pool-1-thread-1 has num: 500
		5
		5
	后面的5,5,为返回值，第一条非常符合，应该threa1,thread2,仅有1个进去运行，
	得到了500，减为499，thread1,或是thread2没有办法在499的情况下进入运行，
	因此仅仅为一个500。

5.尝试使用synchronized（this）代码块：--go2()

		pool-1-thread-1 has num: 500
		5
		5
	输出结果和4一致，是正确的。

6.尝试使用:锁定其他资源的go3(),观察其输出结果
	synchronized（Integer.valueOf(mediation.num)
	
		pool-1-thread-2 has num: 500
		pool-1-thread-2 has num: 498
		pool-1-thread-1 has num: 499
	输出结果和3一致，是错误的。

			
总工程：
-
	ThreadMain.java		
		