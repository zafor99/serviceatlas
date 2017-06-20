package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/*import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;*/
import org.hsqldb.Server;
//import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.xpath.XPathReader;
import com.rational.test.ft.script.RationalTestScript;
import com.sun.jersey.core.impl.provider.entity.XMLListElementProvider.Text;
import com.sun.xml.internal.txw2.annotation.XmlElement;

import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  April 30, 2013
 */
public class XMLUtility {
	private  String m_fileName = null;
	private  Document m_Doc = null;
	public  Element m_RootNode = null;
	private  String m_Xml = null;
    private File m_file = null;
	public XMLUtility(){

	}

	public XMLUtility(String xmlFile){
		readXMLFile(xmlFile);

	}

	public void readXMLFile(String xmlFile){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;

		m_file = new File(xmlFile);
		m_fileName = xmlFile;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			m_Doc = docBuilder.parse(m_file);
			m_RootNode = m_Doc.getDocumentElement();
			m_Xml =  getXMLString(m_Doc);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFromString(String xml){
		XMLOutputter xmlOut=new XMLOutputter(); 
		m_Doc = convertDocumentFromString(xml);
		m_RootNode = m_Doc.getDocumentElement();
		m_fileName = "";
		//m_Xml = xmlOut.outputString(m_Doc);

	}
	
	public Element getRootNode(){
		return m_RootNode;
	}
	
	public String getXMLString(Document doc){
		
		String xmlDoc = null;
		try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       xmlDoc = writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
		
		return xmlDoc;
	}

	public Document convertDocumentFromString(String xml){

		org.xml.sax.InputSource inStream = null;
		
		Document xmlDocument = null;

		inStream = new org.xml.sax.InputSource();
		inStream.setCharacterStream(new java.io.StringReader(xml));

		try {
			xmlDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(inStream);

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}

		return xmlDocument;
	}


	public Element getNodeBYAttribute(String ean){
		Element node=null;
		/*List children = m_RootNode.getChild("items").getChildren();
	    Iterator iterator = children.iterator();
	    
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
	    }*/
		return node;
	}

	public String[] getItemListWithAttribute(String attribute){
		String[] list = null;
		/*Attr children = m_RootNode.getAttributeNode(attribute);

		Iterator iterator = ((List) children).iterator();
		list = new String[((JSON) children).size()];
		int index=0;
		while (iterator.hasNext()) {
			Element child = (Element) iterator.next();
			list[index]=child.getAttribute(attribute).toString();
			index++;
		}*/

		return list;
	}

	public void showItemsAttribute(String attribute){
		/*List children = m_RootNode.getChild("items").getChildren();

		Iterator iterator = children.iterator();

		Element node=null;
		while (iterator.hasNext()) {
			Element child = (Element) iterator.next();
			System.out.println(child.getAttribute(attribute).toString());
		}*/
	}

	public String convertToUTF8(String xml){
		byte[] utf8bytes = xml.getBytes();
		Charset utf8charset = Charset.forName("UTF-8");
		return new String (utf8bytes, utf8charset);
	}

	public Document getDocumentFromStream(String xmlString) {
		org.xml.sax.InputSource inStream = null;

		Document xmlDocument = null;

		inStream = new org.xml.sax.InputSource();
		inStream.setCharacterStream(new java.io.StringReader(xmlString));

		try {
			xmlDocument = (Document) DocumentBuilderFactory.newInstance()
			.newDocumentBuilder().parse(inStream);

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}

		return xmlDocument;
	}

	public Element getNode(String xpath)
	{

		String xmlString = "";
		Element element = null;
		XMLOutputter xmlOut=new XMLOutputter(); 
		String xmlDoc = getXMLString(m_Doc);
		XPathReader xPath = new XPathReader(xmlDoc);

		if(m_Doc!=null)
		{
			m_Doc.getDocumentElement().normalize();
			
			Object node = xPath.read(xpath, XPathConstants.NODE);
			if(node!=null)
			{
				element = (Element)node;			
				
			}

		} 
		
		return element;
		
	}
	
	


	public String getNodeValueByXPath(String xpath)
	{
		Element element = null;
		element = getNode(xpath);
		String value = "";
		if(element!=null){
			value = element.getTextContent();
		}
		else
		{
			System.out.println(xpath + " : Node not found");
		}
		
		return value;
		
	}
	public String getNodeValue(IResponse response, String node){
		
		boolean vp = false;
		String xmlDoc = "";
		Object value="";
		
		if(response.getStatusCode() == 200 ){
			
			String contentType = response.getHeader("Content-Type");
			//System.out.println("Content-Type=" + contentType);
			if(contentType == null)
			{
				contentType = "";
			}
			//if(contentType.equalsIgnoreCase("application/json"))
			if(contentType.toLowerCase().contains("application/json"))
			{
				String json = response.getBody();
				JSONObject jsonObject = JSONObject.fromObject( json ); 
				xmlDoc = new XMLSerializer().write( jsonObject ); 
			}
			else {
				xmlDoc = response.getBody();
			}
			
			
			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();
			XPathReader xPath = new XPathReader(xmlDoc);
			
			value = xPath.read(node,XPathConstants.STRING);
			
			
		}
		return value.toString();
	}
	public void setNodeValueByXPath(String xpath,String value)
	{
		Element element = null;
		element = getNode(xpath);
		if(element!=null){
			element.setTextContent(value);
			m_Doc = element.getOwnerDocument();
			System.out.println(getXMLString());
			//saveToFile();
		}
		else
		{
			System.out.println(xpath + " : Node not found");
		}
	}
	public void addXmlNode (Node currentNode,String newNodeName)  {
		String newXML=null;
		System.out.println(currentNode.getNodeName());
		Element newEle = m_Doc.createElement(newNodeName);
		Node element = m_Doc.getElementsByTagName(currentNode.getNodeName()).item(0);
		element.appendChild(newEle);

	}
	
	public void addXmlNode (Node currentNode,String newNodeName, String value)  {
		String newXML=null;
		System.out.println(currentNode.getNodeName());
		Element newEle = m_Doc.createElement(newNodeName);
		Node element = m_Doc.getElementsByTagName(currentNode.getNodeName()).item(0);
		element.appendChild(newEle);
		addTextNode (newEle,value);
		System.out.println(getXMLString());
	}

	public void addTextNode (Node currentNode,String value) {
		String newXML=null;
		System.out.println(currentNode.getNodeName());
		org.w3c.dom.Text newEle = m_Doc.createTextNode(value);
		Node element = m_Doc.getElementsByTagName(currentNode.getNodeName()).item(0);
		element.appendChild(newEle);
		System.out.println(getXMLString());
	}

	public void setNodeAttributeByXPath(String xpath,String attribute, String value)
	{
		Element element = null;
		element = getNode(xpath);
		if(element!=null){
			element.setAttribute(attribute, value);
			m_Doc = element.getOwnerDocument();
			System.out.println(getXMLString());
			//saveToFile();
		}
		else
		{
			System.out.println("Node not found");
		}
	}
	
	public void setRootAttribute(String attribute, String value)
	{
		Element element = null;
		element = getRootNode();
		if(element!=null){
			element.setAttribute(attribute, value);
			m_Doc = element.getOwnerDocument();
			System.out.println(getXMLString());
			//saveToFile();
		}		
	}

	public void saveToFile(){
		try {
			Source src= new DOMSource(m_Doc);

			File file = new File(m_fileName);
			StreamResult  rs = new StreamResult(new File(m_fileName));

			TransformerFactory tmf = TransformerFactory.newInstance();
			Transformer trnsfrmr = tmf.newTransformer();
			trnsfrmr.setOutputProperty(OutputKeys.INDENT, "yes");
			trnsfrmr.transform(src, rs);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getXMLString(){
		
		return getXMLString(m_Doc);
	}
	/*
	 * Convert Json to XML
	 * @param Takes json as a input
	 */
	public String convertJSONtoXML(String json)
	{
		String xml = null;
		JSONObject jsonObject = JSONObject.fromObject( json ); 
		xml = new XMLSerializer().write( jsonObject );
		return xml;
	}

	public String convertXMLToJson(String xmlText)
	{
		String jsonText = "";
		 
		 XMLSerializer xmlSerializer = new XMLSerializer(); 
         JSON json = xmlSerializer.read( xmlText ); 
         //System.out.println( json.toString(2) );
         jsonText = json.toString();
		 return jsonText;
	}
}
