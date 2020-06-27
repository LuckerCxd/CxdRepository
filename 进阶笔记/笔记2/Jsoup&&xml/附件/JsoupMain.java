import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;



public class JsoupMain {
	public static void main(String[] args) throws Exception {
		String path = JsoupMain.class.getClassLoader().getResource("BaseDtdXML.xml").getPath();
		Document document = Jsoup.parse(new File(path),"utf-8");
		Elements elements = document.select("person  name[id = first]");
		System.out.println(elements);
		
		
	}
}

/*
//		
	String path = JsoupMain.class.getClassLoader().getResource("BaseDtdXML.xml").getPath();
		System.out.println(path);
		Document document = Jsoup.parse(new File(path),"utf-8");
		Elements elements = document.getElementsByTag("name");
		Element element = elements.get(0);
		System.out.println(element.text());





*/