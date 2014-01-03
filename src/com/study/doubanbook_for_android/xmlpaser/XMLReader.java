package com.study.doubanbook_for_android.xmlpaser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public static void parseXml(InputStream stream)
			throws ParserConfigurationException, SAXException, IOException {
		// 创建解析器工厂实例，并生成解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 创建需要解析的文档对象
		// File f = new File("books.xml");
		// 解析文档，并返回一个Document对象，此时xml文档已加载到内存中
		// 好吧，让解析来得更猛烈些吧，其余的事就是获取数据了
		Document doc = builder.parse(stream);
		// 获取文档根元素
		// 你问我为什么这么做?因为文档对象本身就是树形结构，这里就是树根
		// 当然，你也可以直接找到元素集合，省略此步骤
		Element root = doc.getDocumentElement();

		System.out.println("total title:" + getValue(root, "title"));
		System.out.println("start index:"
				+ getValue(root, "opensearch:startIndex"));
		System.out.println("total result:"
				+ getValue(root, "opensearch:totalResults"));
		System.out.println();
		// 上面找到了根节点，这里开始获取根节点下的元素集合
		NodeList list = root.getElementsByTagName("entry");
		for (int i = 0; i < list.getLength(); i++) {
			// 通过item()方法找到集合中的节点，并向下转型为Element对象
			Element n = (Element) list.item(i);
			System.out.println("i: " + i);
			// 打印元素内容，代码很纠结，差不多是个固定格式
			System.out.println("title: " + getValue(n, "title"));
			System.out.println("summary: " + getValue(n, "summary"));
			System.out.println("published: " + getValue(n, "published"));
			System.out.println("updated: " + getValue(n, "updated"));
			System.out.println("name: " + getValue(n, "name"));
			System.out.println("uri:" + getValue(n, "uri"));
			System.out.println();
		}
	}

	private static String getValue(Element n, String s) {
		try {
			return n.getElementsByTagName(s).item(0).getFirstChild()
					.getNodeValue();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}
}
