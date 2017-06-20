package framework;

import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;
import static com.bn.qa.webservice.restclient.BNRestful.given;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.EnvironmentUtility;
import utils.OracleDBConnection;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.xpath.XPathReader;
import com.bn.qa.xobject.script.XObjectTestScript;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.VpUtil;
import com.rational.test.ft.vp.impl.TestDataTreeNode;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 15, 2013
 */
public abstract class CaliberService extends XObjectTestScript
{
	public EnvironmentUtility envUtil = new EnvironmentUtility();
	
	public static IRequestSpecification caliber(){
		
		return given();
	}
	
	public static IRequestSpecification caliberNewInstance(){
		
		return getNewInstance();
	}
	
	public void logResponse(IResponse response)
	{
		
		boolean vp = false;
		String xmlDoc = "";
		boolean isResponseJSON = false;
		
		
		if(response.getStatusCode() == 200 )
		{
			
			String contentType = response.getHeader("Content-Type");

			System.out.println("Content-Type=" + contentType);
			if(contentType ==null){
				contentType = "";
			}

			if(contentType.toLowerCase().contains("application/json") && contentType!=null)
			{
				String json = convertToUTF8(response.getBody());
				JSONObject jsonObject = JSONObject.fromObject( json ); 
				xmlDoc = new XMLSerializer().write( jsonObject );
				isResponseJSON = true;
			}
			else 
			{
				xmlDoc = convertToUTF8(response.getBody());
				isResponseJSON = false;
			}
			
			
			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();
			
			ITestDataTreeNode[] td = getTestData(doc);
			ITestData testData = VpUtil.getTestData(td);
			logCriticalErrorOrException(xmlDoc);
			if(isResponseJSON)
			{
				this.vpManual("responseJSON" + getRandomNumber(),testData,testData ).performTest();
			}
			else
			{
				this.vpManual("responseXML" + getRandomNumber(),testData,testData ).performTest();
				
			}
		}
			
	}
	public String convertJSONtoXML(String json) {
		String xml = null;
		JSONObject jsonObject = JSONObject.fromObject(json);
		xml = new XMLSerializer().write(jsonObject);
		return xml;
	}

	public long getRandomNumber(){
		Random randomGenerator = new Random();
        long randomNum = randomGenerator.nextInt(1000);
        
        return randomNum;

	}
	private void logCriticalErrorOrException(String xmlDoc)
	{
		
		boolean err1 = xmlDoc.contains("SQLException");
		boolean err2 = xmlDoc.contains("JMSException");
		boolean err3 = xmlDoc.contains("CACHE_FAILURE");
		boolean err4 = xmlDoc.contains("RUNTIME");
		boolean err5 = xmlDoc.contains("REFRESH_FAILED");
		boolean err6 = xmlDoc.contains(".IOException");
		boolean err7 = xmlDoc.contains(".Exception");
		boolean err8 = xmlDoc.contains("General Database Exception");
		
		if(err1)
		{
			RationalTestScript.logError("SQLException");
			
		}
		else if(err2)
		{
			RationalTestScript.logError("JMSException");
			
		}
		else if(err3)
		{
			RationalTestScript.logError("CACHE_FAILURE");
			
		}
		else if(err4)
		{
			RationalTestScript.logError("RUNTIME");
			
		}
		else if(err5)
		{
			RationalTestScript.logError("REFRESH_FAILED");
			
		}
		else if(err6)
		{
			RationalTestScript.logError("IOException");
			
		}
		else if(err7)
		{
			RationalTestScript.logError("Exception");
			
		}
		else if(err8)
		{
			RationalTestScript.logError("General Database Exception");
		}
		
	}
	public String convertToUTF8(String xml){
		byte[] utf8bytes = xml.getBytes();
		Charset utf8charset = Charset.forName("UTF-8");
		return new String (utf8bytes, utf8charset);
	}
	public static Document getDocumentFromStream(String xmlString) {
		org.xml.sax.InputSource inStream = null;
		
		Document xmlDocument = null;

		inStream = new org.xml.sax.InputSource();
		inStream.setCharacterStream(new java.io.StringReader(xmlString));

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
	public ITestDataTreeNode[] getTestData(Document doc){
		
		ITestDataTreeNode[] treeRoot = new TestDataTreeNode[1];
		Element root = doc.getDocumentElement();
		if(root != null){
			StringBuilder sb = new StringBuilder(root.getNodeName());
	
			NamedNodeMap attributes = root.getAttributes();
			if(attributes != null){
				for(int i = 0; i < attributes.getLength(); i++){
					Node item = attributes.item(i);
					sb.append(" ");
					sb.append(item.getNodeName());
					sb.append("=");
					sb.append("\"" + item.getNodeValue() + "\""); 
					System.out.println(sb);
				}
			}
			
			treeRoot[0] = new TestDataTreeNode(sb.toString());
			parseDocument(root,treeRoot[0]);
		}
		 
		return treeRoot;
	}
	private void parseDocument(Node root,ITestDataTreeNode treeRoot){
		
		if(root.hasChildNodes()){
			  NodeList list = root.getChildNodes();
			  
			  if(treeRoot != null)
			  {
				  if(list != null && list.getLength() > 0){
					  ITestDataTreeNode[] treeNodes = new TestDataTreeNode[list.getLength()];
					  treeRoot.setChildren(treeNodes);
					  for(int i = 0; i < list.getLength(); i++){
						  Node item = list.item(i);
						  if(item.getNodeType() != 3)
						  {
							 // System.out.println(item.getNodeName() + " : " + item.getNodeType());
							  //treeNodes[i] = new TestDataTreeNode(item.getNodeName());
							  
							  StringBuilder sb = new StringBuilder(item.getNodeName());
							  NamedNodeMap attributes = item.getAttributes();
								if(attributes != null){
									for(int j = 0; j < attributes.getLength(); j++){
										Node attitem = attributes.item(j);
										if(attitem != null){
											sb.append(" ");
											if(attitem.getNodeName() != null){
												sb.append(attitem.getNodeName());
												sb.append("=");
												sb.append("\"" + attitem.getNodeValue() + "\""); 
											}
										}
										
									}
								}
								
								treeNodes[i] = new TestDataTreeNode(sb.toString());
						  }
						  else
						  {
							  //System.out.println(item.getNodeValue() + " : " + item.getNodeType());
							  treeNodes[i] = new TestDataTreeNode(item.getNodeValue());
						  }
						  parseDocument(item,treeNodes[i]);
					  }
				  }
			  }
		 }
	}
	public boolean verifyResponse(String vpName, IResponse response,
			String... nodes) {

		boolean vp = false;
		String xmlDoc = "";
		boolean isResponseJSON = false;

		if (response.getStatusCode() == 200) {

			String contentType = response.getHeader("Content-Type");

			System.out.println("Content-Type=" + contentType);
			if (contentType == null) {
				contentType = "";
			}

			if (contentType.toLowerCase().contains("application/json")
					&& contentType != null) {
				String json = response.getBody();

				// Convert to UTF-8 Charset added 6/11/2012
				JSONObject jsonObject = JSONObject
						.fromObject(convertToUTF8(json));
				xmlDoc = new XMLSerializer().write(jsonObject);
				isResponseJSON = true;

			} else {
				xmlDoc = convertToUTF8(response.getBody());
				isResponseJSON = false;
			}

			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();

			ITestDataTreeNode[] td = getTestData(doc);
			ITestData testData = VpUtil.getTestData(td);

			// This method logs an error if there is any
			logCriticalErrorOrException(xmlDoc);

			if (isResponseJSON) {

				AtlasScriptbase.getExecutingScript().vpManual("responseJSON" + getRandomNumber(), testData,
						testData).performTest();
			} else {
				AtlasScriptbase.getExecutingScript().vpManual("responseXML" + getRandomNumber(), testData,
						testData).performTest();

			}

			XPathReader xPath = new XPathReader(xmlDoc);

			String[][] ht = new String[nodes.length][2];
			for (int i = 0; i < nodes.length; i++) {
				Object result = null; // xPath.read(nodes[i],XPathConstants.STRING);
				Object node = null; // xPath.read(nodes[i],XPathConstants.NODE);
				String extractedXpath = "";

				if (nodes[i].contains("count")) {

					result = xPath.read(nodes[i], XPathConstants.STRING);
					ht[i][0] = nodes[i];
					ht[i][1] = result.toString();

				} else {
					node = xPath.read(nodes[i], XPathConstants.NODE);
					if (node != null) {
						result = xPath.read(nodes[i], XPathConstants.STRING);
						ht[i][0] = nodes[i];
						ht[i][1] = result.toString();
					} else {
						ht[i][0] = nodes[i];
						ht[i][1] = "ELEMENT DOES NOT EXIST";
					}
				}
			}

			vp = AtlasScriptbase.getExecutingScript().vpManual(vpName, VpUtil.getTestData(ht)).performTest();
			AtlasScriptbase.writeResultToExternalSources(vpName, response, vp);
			return vp;
		} else {
			System.out.println("Status Code: " + response.getStatusCode());
			System.out.println("Status Line: " + response.getStatusLine());
			RationalTestScript.logTestResult(
					"Response: " + response.getStatusLine(), false, xmlDoc);
			return vp;
		}

	}
	
	public boolean verifyXML(String vpName, String xml,
			String... nodes) {

		boolean vp = false;
		//String xmlDoc = "";
		boolean isResponseJSON = false;

	//	if (response.getStatusCode() == 200) {

/*			String contentType = response.getHeader("Content-Type");

			System.out.println("Content-Type=" + contentType);
			if (contentType == null) {
				contentType = "";
			}

			if (contentType.toLowerCase().contains("application/json")
					&& contentType != null) {
				String json = response.getBody();

				// Convert to UTF-8 Charset added 6/11/2012
				JSONObject jsonObject = JSONObject
						.fromObject(convertToUTF8(json));
				xmlDoc = new XMLSerializer().write(jsonObject);
				isResponseJSON = true;

			} else {
				xmlDoc = convertToUTF8(response.getBody());
				isResponseJSON = false;
			}*/

			Document doc = getDocumentFromStream(xml);
			doc.getDocumentElement().normalize();

			ITestDataTreeNode[] td = getTestData(doc);
			ITestData testData = VpUtil.getTestData(td);

			// This method logs an error if there is any
			logCriticalErrorOrException(xml);

			if (isResponseJSON) {

				AtlasScriptbase.getExecutingScript().vpManual("responseJSON" + getRandomNumber(), testData,
						testData).performTest();
			} else {
				AtlasScriptbase.getExecutingScript().vpManual("responseXML" + getRandomNumber(), testData,
						testData).performTest();

			}

			XPathReader xPath = new XPathReader(xml);

			String[][] ht = new String[nodes.length][2];
			for (int i = 0; i < nodes.length; i++) {
				Object result = null; // xPath.read(nodes[i],XPathConstants.STRING);
				Object node = null; // xPath.read(nodes[i],XPathConstants.NODE);
				String extractedXpath = "";

				if (nodes[i].contains("count")) {

					result = xPath.read(nodes[i], XPathConstants.STRING);
					ht[i][0] = nodes[i];
					ht[i][1] = result.toString();

				} else {
					node = xPath.read(nodes[i], XPathConstants.NODE);
					if (node != null) {
						result = xPath.read(nodes[i], XPathConstants.STRING);
						ht[i][0] = nodes[i];
						ht[i][1] = result.toString();
					} else {
						ht[i][0] = nodes[i];
						ht[i][1] = "ELEMENT DOES NOT EXIST";
					}
				}
			}

			vp = AtlasScriptbase.getExecutingScript().vpManual(vpName, VpUtil.getTestData(ht)).performTest();
			return vp;
		

	}
	
}
