jvm = classLoader ,  RuntimeDataArea , ExecutionEngine , NativeInterface
classCloader将class文件加载到内存，交给ExecutionEngine解析到操作系统去执行
    loadClass(name) {refileToByteArray(name),return defineClass()} 


NativeInterface(融合不同开发语言的原生库，给Java使用)

getDeclaredMethod(name,xx.class) 
    // 获取到方法，不包含继承方法，private需要Method.setAccessible（true）

getMethod(name,xx.class) 
    // 只有PUBLIC,但包括继承方法

ClassLoader种类：
1.bootStrapClassLoader: c++,加载核心java.*
2.extClassLoader：java，加载javax.*
3.appClassLoader: java，加载java.class.path（程序）
4.自定义ClassLoader

从.class文件中获取到二进制数组，将其使用defineClass加载后交给JVM完成连接，初始化等操作。

双亲委派机制，从custom -> app -> ext -> bootstrap 询问，再从顶向下加载，避免重复加载。
loadClass中会不停的findLoadedClass，如果为null就getParent再去findLoadedClass，
即便是c++的bootstrap(会是null的情况下调用findBootstrapClassOrNull())getParent后，
这样就从当前层去findClass（）,即是一个递归操作。

加载:加载class文件字节码，生成class对象
链接：校验，准备(分配空间设置初始值)，解析(常量池引用转换为直接引用）
初始化：执行类变量赋值和静态代码块


loadClass和forName的区别：
loadClass:仅是加载，resolve为false，还未完成链接，getClassLoader即可
forname：已完成初始化操作，initial为true。forName方法

java内存：
1. 虚拟机栈：
    方法调用产生的栈帧(局部变量表，操作数栈，pc，指令)，是线程私有的，决定了函数深度。
    指令都是针对于操作数栈的，例如iconst0（0入操作数栈),istore2(局部变量表下标2的内容进栈)，iload1(将栈顶元素出栈到局部变量表)，ireturn(将栈顶返回出来)
2. 本地方法栈：作用于native方法，其余与虚拟机栈类似


栈(局部变量引用表，操作数栈，pc，指令)
堆（实例对象）
方法区(常量池(jdk1.7存入堆中)、静态变量、类信息、JIT编译后的代码)

栈和堆的区别：
1.栈是自动释放的，堆需要gc
2.栈空间较小，存储的是对象的引用
3.栈的空间碎片也较少
4.栈支持编译时确定大小的静态分配和运行时确定大小的栈式分配，堆仅支持编译或是运行时都不可确定大小的堆式分配
5.栈的效率较高

永久代是hotspot对方法区的实现。在jdk1.8后有元数据metaSpace元空间实现方法区。

metaSpace用来替代永久代的优势：
1.metaSpace使用的是本地内存，具有伸缩性，而永久代使用jvm内存
2.字符串常量池存在永久代，容易内存溢出，影响性能
3.类和方法的信息大小难以确定，因此难以指定永久代的大小
4.由于永久代空间不足引发的fullGC
5.方便hotspot和其他jvm的集成。

jvm3大性能调优参数
-Xss:每个线程虚拟机栈(即堆栈)的大小(256k)，影响并发线程数的大小
-Xms:堆的初始值，会扩容到-Xmx大小 -xx:PermSize 
-Xmx:堆能达到的最大值，通常与初始值一致，也就是避免扩容时的抖动 -xx:MaxPermSize


intern方法：
在jdk1.6和以前时，
    常量池存在永久代中，查询常量池有没有字符串实例，没有就添加堆中的副本(不是引用)到常量池里。是副本“aa”，返回值只会是常量池中的aa副本
在jdk1.7以后，
    常量池存在堆里面，查询常量池有没有字符串实例，没有就添加堆中的引用(不是副本)到常量池里。是变量aa的引用,返回值也因此可以是aa副本或是aa引用

垃圾回收机制

判定是否为垃圾：
1.引用计数法，难以处理循环的情况，环状情况(a->b,b->a)
2.可达性分析：从root开始分析可达性
            root: 1. 虚拟机栈的引用对象
                  2. 方法区中的常量引用对象
                  3. 方法区中的类静态属性引用对象
                  4. 本地方法栈的jni的引用对象
                  5. 活跃线程的引用对象

垃圾回收算法：
1.标记清理算法：从根集合扫描，先标记，后从头至尾线性清除无标记的对象内存 -> 碎片化
2.复制算法：使用空闲面和对象面，对象在对象面上创建，存活的对象复制到空闲面，清空对象面 -> 适用于存活率低，解决碎片化问题，新生代
3.标记整理算法：从根集合扫描，先标记，后将标记的对象按内存地址依次排列，清除末端空间 -> 解决碎片化问题,适用于存活率高，不需要浪费一大块空间，老年代
4.分代收集算法：
    minor GC：新生代1/3的垃圾回收,存活率低，复制算法
    (eden,surivivor = 8:1,新生对象大多数产生于eden,将eden和from区存活的对象到to区，该对象年龄+1)
    full GC: 老年代的垃圾回收会引起新生代的垃圾回收，标记清理/标记整理算法

新生代占比1/3,老年代占比2/3 (-XX:NewRadio 2, -XX:MaxTenuringThreshold=15)
eden占新生代的8/10，survivor占新生代的2/10，每个survivor就是1/10 （-XX:SurvivorRadio = 8）


对象如何晋升到老年代：
1.eden区放不下后，就会放到老年代，如果也放不下，就会触发fullGc
2.对象经历一次minorgc，如果存活，年龄+1，当年龄大于一定次数时，移入老年代。

fullGc的引发条件：
1.eden放不下的对象，在移入老年代时由于空间不足而引发的fullGc
2.老年代的空间占满了
3.永久代的空间占满了
4.minorGc移入老年的平均空间大小大于当前老年代的剩余空间
5.system.gc()被调用时，会提醒jvm进行一次fullgc
6.远程调用接口rmi进行rpc时，一小时进行一次fullgc


产生safepoint的位置(对象引用关系不再变化的点，只有线程在安全点了才进行gc)
1.循环跳转时
2.异常跳转时
3.方法调用时

年轻代常见的垃圾收集器:都是复制算法
1.Serial 单线程收集,client下默认年轻代垃圾收集器
2.ParNew 多线程收集, 可与cms配合使用
3.parallel Scanvenge 多线程收集，吞吐量(暂停时间占比小，适用于后台计算，不适合交互)，server下默认年轻代收集器

老年代常见的垃圾收集器
1.serial Old 单线程收集,标记整理
2.parallel old 多线程收集，吞吐量，标记整理
3.cms 多线程，仅是初始标记（扫第一层）和重置标记（扫描堆中剩余的对象）阶段需要stop-the-world，标记清除,jdk1.5发布

既用于年轻代和老年代的垃圾收集器：g1，分代收集，并发并行，空间整合，可预测停顿

c++的析构与java的finalize()作用是否一致：
1.java的finalize方法优先级低，容易被打断，具备不确定性
2.java的finalize方法会提供对象一个复活的机会，但只会调用一次


强引用：new出来的引用，不会被gc时回收, 可以赋值null将其变弱
弱引用：gc时会被回收，使用WeakHashMap可以在弱引用键为空时，移除条目
软引用：内存不足时会被gc回收，可以用作缓存，在内存不足时清掉
虚引用：随时会被回收，必须配合referenceQueue使用，当指向为空时，自动进入队列

联合使用ReferenceQueue时，当reference指向的对象变为null时，将该reference添加queue中。
reference.get() ： 获取reference指向的对象,必然为null


java进程与线程
start和run的区别，源码