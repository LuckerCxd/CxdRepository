线程私有系列帧的帧栈 --- 方法调用，局部变量栈，操作数栈，常量池调用，结束后移除
    static : byte - int
			 boolean - int
			 Object - refence o
	runInstance :  first: refence this
	JVM会优先栈上分配，不必处理内存泄漏，线程私有对象直接栈上分配
	数据量大1M，>几十个bytes 的new才是堆
	自动回收，减轻GC回收压力
	决定了函数调用深度

全局共享的堆
main本身放入方法区 和 类中的方法都放入方法区

	xClass k = new xClass();
	k.getName();
	
--> 栈存了k这个对象, 而instance在堆上， getName在方法区， 所以是：
		栈指向堆，堆指向方法区

jvm启动流程： 1. 装载配置找到jvm.cfg
			  2.  根据配置找到jvm.dll
			  3.  初始化jvm，获得jvmEnv接口
			  4.  找到main方法，运行
			  
1. 要求perm区溢出。可以设置一个较小的MaxPermSize，但是必须要让jvm起来。
然后 载入大量类 即可。不一定要动态生成类。找一个大点的jar包，把类加载一下就可以了。
2. 需要让函数调用层次更深，可以从2个方面回答，第一增大栈空间，也就是设置xss。
第二，可以减小局部变量表，比如 少用double，long，减少参数个数，局部变量在使用的时候，注意作用域。
在作用域开外的，局部变量，是可以被重用的，以此减少局部变量表的大小。


最高边界 - 低边界  ==  整个大小  ==  整个的空间的和 ！= 可用大小 
新生代占堆 3  / 8 , 幸存占新生代 1 / 10  栈几百k 
对于物理上来说，新生代其实就是分配对象的内存+待复制对象的内存空间
	-XX:NetRatio1.67	设置老年代和新生代大小比例
	-XX:SurvivorRatio8  设置eden区和survivior区大小比例
-Xmn7m  //新生代大小
OOM：堆空间(新生代+老年代)用完 或是 永久区溢出
tenured generation 老年代
根据新生代和老年代的边界值计算Xmx   (新生总和 +老年总会)
根据Perm的边界值计算MaxPermsize，  ()

方法区和堆内存的永久代其实一个东西，一个是理论，一个是具体的实现，
为什么要分开说呢，因为方法区包含永久代，但是反过来就不能这么说， 
	所以这也正如作者所说，一个是接口，一个是具体实现，持久代对垃圾回收没有显著影响

lib精简方式：
这里面我给出一个精简rt.jar的程序，自己写的.（这里主要是给出了精简rt.jar的程序）
主要思想就是：
1、把程序运行所需要的class文件通过-XX:TraceClassLoading打印到文本文件
2、用自己写的程序把需要的class和rt路径，精简rt存放的路径设置好
3、然后将rt1里面的目录和文件打包成rt.zip,改名为rt.jar，然后替换原来的rt.jar
4、可以达到精简的作用，再将Java.exe和对应的dll copy到相应的目录，
5、写一个批处理命令，用于自带的Java去执行jar包。

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
//自己写的
public class CutJre {
	private String needClazz = "d:\\needClazz.txt";//需要的class
	private String rtPath = "D:\\Program Files\\Java\\jre6\\lib";//rt存放路径
	private String dstRtPath = "D:/cutJar/";//精简后的路径
	private JarFile rtJar;

	public static void main(String[] args) throws Exception {
		CutJre cutJre = new CutJre();
		cutJre.rtJar = new JarFile(cutJre.rtPath + "\\rt.jar");
		cutJre.copyClass("[Loaded sun.reflect.FieldAccessor from sda]");
//		 cutJre.execute();
	}

	private void execute() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(needClazz)));
		String string = br.readLine();
		while (string != null) {
			string = br.readLine();
		}
	}

	private boolean copyClass(String traceStr) throws IOException {
		if (traceStr.startsWith("[Loaded")) {
			String className = traceStr.split(" ")[1];
			//不是rt里面的Jar包，是自己有的
			if(className.contains("zh")){
				return true;
			}
			copyFile(className);
		}
		return false;
	}

	private void copyFile(String className) throws IOException {
		String classFile = className.replace(".", "/") + ".class";
		String classDir = classFile.substring(0,classFile.lastIndexOf("/"));

		File dir=new File(dstRtPath+classDir);
		System.out.println(dir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		JarEntry jarEntry = rtJar.getJarEntry(classFile);
		InputStream ins = rtJar.getInputStream(jarEntry);
		File file = new File(dstRtPath+ classFile);
		System.out.println(file);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		IOUtils.copy(ins, fos);
		ins.close();
		fos.close();

	}
}





GC对象是堆空间，永久区

算法：
1.引用计算法 引用数 = 0时清掉 (可达的有效对象为0时，即不可达时清空)
	难处理循环引用，加减操作影响性能
2.标记清除法 标记可达对象，清除无标记的对象
3.标记压缩法 标记可达对象，将可达对象压缩到边界去，清除边界外的对象
4.复制算法 复制正在使用的对象到另一块内存空间，
			随后清空正在使用的内存空间，之后再去使用它，内存一分为二
			eden : 对象产生的地方，新生代位置，[对象过大可能会直接去老年区/栈上分配]
			from ： 一分为二的位置，用于使用，
				eden的对象有幸存活就来到了from区或者说幸存区，
				一直幸存一定次数就进入了老年区
			to   ： 一分为二的位置，用于复制
分代思想：
少量对象存活：复制算法
大量对象存活：标记清理，标记压缩

短命对象归为新生代，长命对象归为老年代
对象从根结点可分为 ：可触及，可复活，不可触及的
	1. 从根结点可触及
	2. 所有引用被释放，但在finalize可能复活该对象
		system.gc 会调用对象的finalize方法, finalize可以使用 obj = this完成复活，
			但finalize只会被调用一次，因此可被复活一次，
			但finalize优先级低，不确定何时被调用，何时gc也不确定，不推荐使用
	3. finalize后，不可触及，也不可复活，可以回收



当一个对象到GC Roots不可达时，在下一个垃圾回收周期中尝试回收该对象，
如果该对象重写了finalize()方法，并在这个方法中成功自救(将自身赋予某个引用)，
那么这个对象不会被回收。但如果这个对象没有重写finalize()方法或者已经执行过这个方法，
也自救失败，该对象将会被回收


Exception对象会在下一个垃圾回收过程中被回收掉。

根：
	1.栈中引用的对象
	2.方法区静态成员或是常量引用的对象
	3.JNI方法栈引用对象

GC，Dump线程，死锁检查，堆Dump   ---  全局停顿

串行收集器：稳定效率高停顿时间长(一个线程回收)， 
	-XX：+UseSerialGC
	新生代，老年代都使用串行回收
	新生代：复制算法
	老年代：标记压缩
	
并行收集器：ParNew
	-XX:+UseParNewGC 新生代并行，老年代串行
	复制算法，多线程，需要多核支持，应用程序也要暂停
	Paraller收集器 ： 
		类似ParNew 串行收集器的并行化
		新生代复制算法 
		老年代标记压缩
		-XX：+UseParallelGC : 新生代使用并行回收收集器
		-XX：+UseParallelOldGC ：老年代使用并行回收收集器
		-XX：ParallelGCThreads : 垃圾回收线程数
	-XX:MaxGCPauseMills : 最大停顿时间，尽量满足
	-XX:GCTimeRatio : 0-100垃圾收集占用的时间，默认99，仅1%做GC
	这两参数矛盾，停顿时间和吞吐量不可能同时调优
	吞吐量 = 运行代码时间 / (运行代码时间 + 垃圾收集器工作时间)）
	
CMS收集器：
	Concurrent Mark Sweep 并发标记清除，并发会降低吞吐量，新生代使用ParNew
	-XX：+UseConcMarkSweepGC ：新生代使用并行收集器，老年代CMS+串行
	和程序(用户线程)一起进行，尽可能停顿减少了，但吞吐量降低了，清理不彻底，可能会同时产生新的垃圾
	是一个老年代收集器，不给新生代使用
	-XX：CMSInitiatingOccupancyFraction 设置触发GC的阈值%，老年代占比
	初始标记(根关联-快-停顿) -> 并发标记（标记过程，标记全部对象，和用户线程一起，不停顿）
	-> 重新标记（正式清理前做修正，停顿） -> 并发清除（清理掉对象） -> 并发重置
	当发生concurrent mode failure :堆空间不足时，停止进程使用，改用串行收集做后备
	-XX：+UseCMSCompactAtFullCollection Full GC ,停顿进行一次独占的整理碎片
	-XX：CMSFullGCBeforeCompaction ,进行几次Full GC后，进行一次碎片整理
	-XX: +CMSClassUnloadingEnabled ,允许对类元数据进行回收
	-XX：CMSInitiatingPermOccupancyFraction 设置触发GC的阈值%，永久区占比
	-XX：CMSInitiatingOccupancyOnly 只有到达阈值时，才进行CMS回收
	-XX：ParallelCMSThreads ,设置CMS线程数目
	
	
	
减轻GC回收压力：
	1.软件架构
	2.代码编写
	3.堆空间分配


	
	
类装载器：
	1.取得二进制流
	2.转为方法区数据结构
	3.在堆中生成对应的Java.lang.Class
	4.链接 -> 验证：保证Class流的格式是正确的
		文件格式验证： 0xCAFEBABE , 版本是否合理
		元数据验证： 是否有父类 继承了final类 非抽象类实现了所有抽象方法
		字节码验证： 运行检查，栈数据类型和操作码数据参数是否吻合，跳转指令到合理的位置
		符号引用验证: 常量池描述类是否存在，访问的方法或字段是否有足够的权限
	5.链接 -> 准备：
		分配内存，并为方法区中的类设置初始值，
			public static int v = 1
			在准备阶段设置变量为默认值0，在初始化<clinit>中才设置为值1
			对于static final 在准备阶段就被设置为正确的值1
	6.链接 -> 解析：
		符号引用 替换为 直接引用（内存位置，指针）
	7.初始化：
		执行类构造器<clinit> static变量，赋值语句，static{}
		子类的<clinit>调用前保证父类的<clinit>先调用，是线程安全的

ClassLoader:抽象类，ClassLoader实例读入Java字节码将类加载到JVM中
			可以定制，满足不同字节码流的获取方式
			负责类装载过程的加载阶段。
			loadClass(String name):载入并返回一个Class
			defindClass(byte[] b,int off,int len) : 定义一个类，不公开调用
			findClsss(String name) ：loadClass回调
			findLoadedClass(String name) ：寻找已加载的类

ClassLoader默认设计模式：BootStrap ClassLoader(启动ClassLoader)
						 Extension ClassLoader(拓展ClassLoader)
						 App ClassLoader(应用ClassLoader/系统ClassLoader)
						 Custom ClassLoader(自定义ClassLoader)
						 每个ClassLoader都有一个Parent做父亲
	从底向上检查是否已经加载，上面super-sub关系
	如果到了顶部都没有加载，就要从顶向下做加载
	findLoadedClass 不存在时， super.loadClass 有父类加载
	如果是同一个Class,在参数-Xbootclasspath/a:D:/tmp/clz 时，src下的class不会被app加载
	而在那个位置的同名Class会由bootStrap加载，
	但是如果在方法调用前，使用反射机制，使用ClassLoader将使用defindClass加载自己的类，
	那么就可以强制加载成自己的类，而非同名的由bootStrap加载
	
	Thread.setContextClassLoader():上下文加载器，解决顶层ClassLoader无法加载地城ClassLoader的类
	可以是在bootstrap启动的类，getContextClassLoader就能找到BootStrapClassLoader