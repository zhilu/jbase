package utiltools;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XML {

	/**
	 * 获取XML文档根节点
	 * 
	 * @param message
	 * @return
	 */
	public static Element getXmlRoot(String message) {
		Element root = null;
		try {
			SAXReader reader = new SAXReader();
			Document doc;
			doc = reader.read(new StringReader(message));
			root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return root;
	}

	/**
	 * 获取父元素的子元素名称为name的元素
	 * 
	 * @param parent
	 * @param name
	 * @return
	 */
	public static Element getElement(Element parent, String name) {
		if (null == parent || null == name || "".equals(name)) {
			return null;
		}
		return parent.element(name);
	}

	/**
	 * 获取元素的值
	 * 
	 * @param parent
	 * @param name
	 * @return
	 */
	public static String getEleValue(Element element) {
		if (null == element) {
			return null;
		}
		return element.getStringValue();
	}

	/**
	 * 获取父元素的子元素名称为name的值(无效值判断)
	 * 
	 * @param parent
	 * @param name
	 * @return
	 */
	public static String getEleValue(Element parent, String name) {
		if (null == parent || null == name || "".equals(name)) {
			return null;
		}

		Element child = parent.element(name);
		if (null == child){
			return null;
		}
		return child.getStringValue();

	}

	/**
	 * 获取元素属性名称为attr的值
	 * 
	 * @param parent
	 * @param name
	 * @return
	 */
	public static String getAttrValue(Element element, String attr) {
		if (null == element ||  null == attr ||"".equals(attr) ) {
			return null;
		}
		return element.attributeValue(attr);
	}

}
