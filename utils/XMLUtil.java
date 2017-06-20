package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 04, 2014
 */
public  class XMLUtil 
{
	public  String m_fileName = null;
	public  Document m_Doc = null;
	public  Element m_RootNode = null;
	
	public XMLUtil(){
		
	}
	
	public XMLUtil(String xml){
		readFromString(xml);
		
	}
	
	
	public void readXMLFile(String xmlFile){
		SAXBuilder builder = new SAXBuilder();
		m_fileName = xmlFile;
		try {
			m_Doc = builder.build(xmlFile);
			m_RootNode = m_Doc.getRootElement();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFromString(String xml){
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(xml);
		
		try {
			m_Doc = builder.build(in);
			m_RootNode = m_Doc.getRootElement();
			m_fileName = "";
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	 public void modifyPaymentRequestAttributes(String orderNum, String orderTotal, String customerId){
		 	m_RootNode.setAttribute("customerid",customerId);
			m_RootNode.setAttribute("ordertotal",orderTotal);			
			m_RootNode.setAttribute("ordernumber",orderNum);
			
		}
	 
	 public void modifyPaymentRequestAttributes(String orderNum, String orderTotal, String customerId,String currencyCode, String countryCode){
		 	m_RootNode.setAttribute("customerid",customerId);
			m_RootNode.setAttribute("ordertotal",orderTotal);			
			m_RootNode.setAttribute("ordernumber",orderNum);
			m_RootNode.setAttribute("currencyCode",currencyCode);
			m_RootNode.setAttribute("countryCode",countryCode);
			
		}
		
		public void modifyPaymentAttribute(String accountNum, String EncryptScheme, String expDate){
			m_RootNode.getChild("detail").getChild("payment").setAttribute("account",accountNum);
			m_RootNode.getChild("detail").getChild("payment").setAttribute("encryptionscheme",EncryptScheme);
			m_RootNode.getChild("detail").getChild("payment").setAttribute("exp",expDate);
		}
	public Element getNodeBYAttribute(String ean){
		
		//System.out.println(m_RootNode.getNamespace());
		//System.out.println(m_RootNode.getChild("items").getName());
		List children = m_RootNode.getChild("items").getChildren();
		
	    Iterator iterator = children.iterator();
	    
	    Element node=null;
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      System.out.println(child.getName());
	      System.out.println(child.getAttributeValue("EAN").toString());
	      if(child.getAttributeValue("EAN").toString().contentEquals(ean)){
	    	  System.out.println(child.getAttributeValue("EAN").toString());
	    	  node= child;
	    	  break;
	      }
	      //listChildren(child, depth+1);
	    }
	    
	    return node;
	}
/*	
	public String[] getNodeWithAttribute(String String attribute){
		String[] list = null;
		List children = m_RootNode.getChild("items").getChildren();
		
	    Iterator iterator = children.iterator();
	    list = new String[children.size()];
	    int index=0;
	    
	    while (iterator.hasNext()) {
	    	Element child = (Element) iterator.next();
	    	list[index]=child.getAttributeValue(attribute).toString();
	    	index++;
	    }
		
		return list;
	}
	*/
	public String[] getItemListWithAttribute(String attribute){
		String[] list = null;
		List children = m_RootNode.getChild("items").getChildren();
		
	    Iterator iterator = children.iterator();
	    list = new String[children.size()];
	    int index=0;
	    while (iterator.hasNext()) {
	    	Element child = (Element) iterator.next();
	    	list[index]=child.getAttributeValue(attribute).toString();
	    	index++;
	    }
		
		return list;
	}
	
	public Element getRootNode(){
		return m_RootNode;
	}
	
	public String getNodeAttributeStringValue(String node, String attribute){
		return getRootNode().getChild(node).getAttribute(attribute).getValue();
	}
	
	public int getNodeAttributeIntegerValue(String node, String attribute) throws DataConversionException{
		return getRootNode().getChild(node).getAttribute(attribute).getIntValue();
	}
	
	
	
	public int getSizeByRouteName(String routeName){
		List children = m_RootNode.getChildren();
		int count = 0;
		String route = null;
		 Iterator iterator = children.iterator();
		 Element node=null;
		  while (iterator.hasNext()) {
			  Element child = (Element) iterator.next();
			  System.out.println(child.getAttributeValue("name").toString());
			  route = child.getAttributeValue("name").toString();
			  if(route.contentEquals(routeName)){
				  count = Integer.parseInt(child.getChild("stats").getAttributeValue("size").toString());
				  break;
			  }
			  
			  /*			  if(count==1){
				  System.out.println(child.getAttributeValue("stats").toString());  
			  }
			  */
		    	//System.out.println(child.getAttributeValue(routeName).toString());
		    	
		  }
		return count;
	}
	public void showItemsAttribute(String attribute){
		List children = m_RootNode.getChild("items").getChildren();
		
	    Iterator iterator = children.iterator();
	    
	    Element node=null;
	    while (iterator.hasNext()) {
	    	Element child = (Element) iterator.next();
	    	System.out.println(child.getAttributeValue(attribute).toString());
	    }
	}
	
	public void saveToFile(){
		try {
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(m_Doc, new FileWriter(m_fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getXMLString(){
		String xml = "";
		try {
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xml = xmlOutput.outputString(m_Doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return xml;
	}
}

