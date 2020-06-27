Jsoup工具类简介
	
	1.Jsoup:用于解析xml或是html文档 ->获取document
	2.Docuemnt:	public class Document extends Element 包含整个xml内容
	3.Element：获取子Element对象,属性,文本内容
	4.Elements:包含若干Element的ArrayList
	5.Node
	6.selector:选择器
	

	参考：
		https://jsoup.org/apidocs/org/jsoup/Jsoup.html
		https://jsoup.org/apidocs/org/jsoup/select/Selector.html
		https://jsoup.org/cookbook/extracting-data/selector-syntax

1.Jsoup:用于解析xml或是html文档 ->获取document
		
	静态parse返回Document对象:  Parse the contents of a file as HTML 
	1.parse​(File in, String charsetName)
		//解析本地文件
	2.parse​(URL url,int timeoutMillis) 
		//Only http & https protocols supported
		
2.Docuemnt:	public class Document extends Element 包含整个xml内容
	
	获取Element/Elements对象（来自父类Element）
		1.Element getElementById​(String id)
		2.Elements getElementsByTag​(String tagName)
		3.Elements getElementsByAttribute​(String key)
		4.Elements getElementsByAttributeValue​(String key, String value)
		5.Elements getElementsByAttributeValueContaining(String key,String with)
		
6.selector:选择器->快捷查询
		
		(同上也来源父类Element)
		6.Elements select​(String query)  
			1.document.select("person[number=s1]"); 
					//person标签中的属性number值为s1
			2.document.select("person[number=s1]>name");  
					//person标签中的属性number值为s1的name子标签
			3.document.select("person name[id = first]");
					//person的name子标签中的属性id且值为first的
		
3.Element：获取子Element对象,属性,文本内容

	获取Element对象：
		1.Element getElementById​(String id)
		2.Elements getElementsByTag​(String tagName)
		3.Elements getElementsByAttribute​(String key)
		4.Elements getElementsByAttributeValue​(String key, String value)
		5.Elements getElementsByAttributeValueContaining(String key,String with)
			
	获取属性值(来自Node)
		1.String	attr​(String attributeKey)
	
	获取文本内容：
		1.String	text​()  //所有文本信息
		2.String	html​()	//按含标签的文本信息

4.Elements :public class Elements extends ArrayList<Element>
	
5.Node：层次关系（父类->子类）
		
	Node -> Element -> Document



