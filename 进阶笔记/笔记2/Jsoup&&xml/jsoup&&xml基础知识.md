jsoup&xml基础知识：(获取xml的存储信息)

使用jsoup获取xml的存储信息：

	0.xml的基础知识：（可拓展标记语言，语法严格，用于存储）
	1.导入jsoup-1.6.0.jar包
	2.将编写好的xml文件放入同级包(src下)
	3.使用类加载器将xml作为资源加载，并获取其资源路径
	4.获取Document对象，使用Jsoup工具类parse(),
		将File(资源路径)以及字符集编码作为参数传入
	5.通过Document,通过tag,id,attr...获取Element,Elements
	6.通过Element,获取子对象element,属性attr,文本内容text html


0.xml的基础知识：（可拓展标记语言，语法严格，用于存储）

	1.第一行得有文档声明: <?xml version="1.0" encoding="UTF-8"? >
	2.xml文档中，必须有而且只有一个根标签包裹其他内容
	3.标签名称区分大小写，属性要用引号，有开有闭
	4.字符串原形区：
		<code>
			<![CDATA[	
			if(true){
				print("hello world");
			}
			]]>
		</code>
	5.可以有dtd,schema约束文档，对xml的结构进行限定
		dtd约束文档：
					Person.dtd
			<?xml version="1.0" encoding="UTF-8"?>
			<!ELEMENT persons (person+)>
			<!ELEMENT person (id,name,sex)>
			<!ELEMENT id (#PCDATA) >
			<!ELEMENT name (#PCDATA)>
			<!ELEMENT sex (#PCDATA)>
			<!ATTLIST person number ID #REQUIRED>
				--设置属性,ID唯一，#REQUIRED:必须有的属性
				
		使用约束文档的xml：
					同目录的xml

			<?xml version="1.0" encoding="UTF-8"?>

			<!--  SYSTEM本地 PUBLIC在线 引入dtd约束文档-->
			<!DOCTYPE persons SYSTEM "Person.dtd">
			<persons>
				<person number  ="s0">
					<id>001</id>
					<name>liji</name>
					<sex>female</sex>
				</person>
				<person number  ="s1">
					<id>001</id>
					<name>liji</name>
					<sex>female</sex>
				</person>
			</persons>

1.导入jsoup的jar包
	
	jsoup-1.6.0.jar

2.将编写好的xml文件放入同级包(src下)
		
	-src
		-package0
			-xxxx.java
		-xxx.xml
xml
-
	<?xml version="1.0" encoding="UTF-8"?>
	<persons>
		<person number  ="s0">
			<id>001</id>
			<name>lixiao</name>
			<sex>female</sex>
		</person>
		<person number  ="s1">
			<id>001</id>
			<name>chenxiao</name>
			<sex>female</sex>
		</person>
	</persons>

3.使用类加载器将xml作为资源加载，并获取其资源路径
	
	String path = JsoupMain.class.getClassLoader().getResource("BaseDtdXML.xml").getPath();


4.获取Document对象，使用Jsoup工具类parse(),
	将File(资源路径)以及字符集编码作为参数传入

	Document document = Jsoup.parse(new File(path),"utf-8");


5.通过Document,通过tag标签名,id,attr...获取Element,Elements
	
	Elements elements = document.getElementsByTag("name");
	Element element = elements.get(0);

6.通过Element,获取子对象element,属性attr,文本内容text html
		
	System.out.println(element.text());

完整型：

	String path = JsoupMain.class.getClassLoader().getResource("BaseDtdXML.xml").getPath();
	Document document = Jsoup.parse(new File(path),"utf-8");
	Elements elements = document.getElementsByTag("name");
	Element element = elements.get(0);
	System.out.println(element.text());
	
