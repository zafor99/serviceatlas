package framework;

import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;
import static com.bn.qa.webservice.restclient.BNRestful.given;

import java.io.IOException;
import java.nio.charset.Charset;

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

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.xpath.XPathReader;
import com.bn.qa.xobject.script.XObjectTestScript;
import com.bn.qa.xobject.vp.XManualVerificationPoint;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.VpUtil;
import com.rational.test.ft.vp.impl.TestDataTreeNode;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  April 02, 2013
 */
public abstract class ControllerScriptBase extends XObjectTestScript
{
	private static AtlasScriptbase executingScript = null;
	
	public IRequestSpecification httpClient(){

		return given();
	}

	public IRequestSpecification httpClientInstance(){

		return getNewInstance();
	}
	
	public static AtlasScriptbase getExecutingScript(){
		return executingScript;
	}
	
	public String convertToUTF8(String xml){
		byte[] utf8bytes = xml.getBytes();
		Charset utf8charset = Charset.forName("UTF-8");
		return new String (utf8bytes, utf8charset);
	}
	
	public Object getNode(IResponse response, String xpath)
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
				String json = response.getBody();
				
				// Convert to UTF-8 Charset added 6/11/2012				
				JSONObject jsonObject = JSONObject.fromObject( convertToUTF8(json)); 
				xmlDoc = new XMLSerializer().write( jsonObject );
				//logResponseXml(xmlDoc);
				isResponseJSON = true;
			}
			else 
			{
				// Convert to UTF-8 Charset added 6/11/2012
				xmlDoc = convertToUTF8(response.getBody());				
				isResponseJSON = false;
			}
			
			
			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();
			
			/*ITestDataTreeNode[] td = getTestData(doc);
			ITestData testData = VpUtil.getTestData(td);
			
			if(isResponseJSON)
			{
				
				this.vpManual("responseJSON" + getRandomNumber(),testData,testData ).performTest();
			}
			else
			{
				this.vpManual("responseXML" + getRandomNumber(),testData,testData ).performTest();
				
			}*/
			
			
			XPathReader xPath = new XPathReader(xmlDoc);
			
			Object node = null;  //xPath.read(nodes[i],XPathConstants.NODE);
			Node l_node = null;

			node = xPath.read(xpath,XPathConstants.NODE);
			
			return node;
			
		}
		else
		{
			RationalTestScript.logTestResult("Response: " + response.getStatusLine(), false, xmlDoc) ;
			return null; 
		}
			
	}
	
	public Document getDocumentFromStream(String xmlString) {
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
	
	private void parseDocument(Node root,ITestDataTreeNode treeRoot,Boolean children, Integer level, Boolean data, Boolean regularizeData){
		String nodeValue = "";
		Integer index = null;
		ITestDataTreeNode[] treeNodes = null;
		
		if(root.hasChildNodes()){
			
			  NodeList list = root.getChildNodes();
			  
			  if(treeRoot != null)
			  {				  
				  if(list != null && list.getLength() > 0){
					  
					  if(list.item(0).getNodeType()==3 && data == false){
						 						  
					  }
					  else
					  {
						  treeNodes = new TestDataTreeNode[list.getLength()];
						  treeRoot.setChildren(treeNodes);
						  System.out.println(level + " : " + list.getLength() );
					  }
					  
					  
					  for(int i = 0; i < list.getLength(); i++){
						  
						  index = level;
						  Node item = list.item(i);
						  System.out.println( " Node Type : " + item.getNodeType());
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
								
								if(children!=null && children == true){
									  if(index !=null && index > 0){
										  //index--;
										  parseDocument(item,treeNodes[i],children,index, data, regularizeData); 
									  }	
								 }
								else
								{
									if(data!=null && data==true){
										parseDocument(item,treeNodes[i],false,0, data, regularizeData);
									}
								}
						  }
						  else
						  {
							  //System.out.println(item.getNodeValue() + " : " + item.getNodeType());
							  
							  if(data!=null && data == true){
								  if(regularizeData!=null && regularizeData == true){
									  String nodeData = item.getNodeValue();
									  if(nodeData!=null & !nodeData.contentEquals("")){
										  treeNodes[i] = new TestDataTreeNode("DATA");
									  }
									  else
									  {
										  treeNodes[i] = new TestDataTreeNode("EMPTY");
									  }
								  }else
								  {
									  treeNodes[i] = new TestDataTreeNode(item.getNodeValue());
								  }
								  
							  } 
							 /* else
							  {
								  treeNodes[i] = new TestDataTreeNode(" ");
							  }*/
							  index--;
						  }
						  /*if(children!=null && children == true){
							  if(index !=null && index > 0){
								  index--;
								  parseDocument(item,treeNodes[i],children,index, data); 
							  }								 
						  }*/
						  
					  }
				  } 
				  
			  }
		 }
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
	
public ITestDataTreeNode[] getTestData(Document doc, Element node, Boolean children, Integer level, Boolean data, Boolean regularData){
		
		ITestDataTreeNode[] treeRoot = new TestDataTreeNode[1];
		Element root = null;
		if(node!=null){
			root = node;
		} else {
			root = doc.getDocumentElement();
		}
		
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
			parseDocument(root,treeRoot[0],children,level, data, regularData);
		}
		 
		return treeRoot;
	}
	
	public boolean verifyResponse(String vpName, IResponse response)
	{
			
			boolean vp = false;
			
			
			String xmlDoc = "";
			boolean isResponseJSON = false;
			if(response.getStatusCode() == 200 ){
				
				String contentType = response.getHeader("Content-Type");
				System.out.println("Content-Type=" + contentType);
				
				if(contentType.toLowerCase().contains("application/json")){
					String json = response.getBody();
					System.out.println(json);
					JSONObject jsonObject = JSONObject.fromObject( json.trim() );
					/*JSONObject jsonObject = JSONObject.fromObject( json.trim() ); 
					xmlDoc = new XMLSerializer().write( jsonObject );*/ 
					
					
					
					isResponseJSON = true;
				}
				else {
					xmlDoc = response.getBody();
				}
				
				Document doc = getDocumentFromStream(xmlDoc);
				doc.getDocumentElement().normalize();
				ITestDataTreeNode[] td = getTestData(doc);
				ITestData testData = VpUtil.getTestData(td);
				vp = performTest(new XManualVerificationPoint(vpName, this, testData));
				return vp;
			}
			else
			{
				RationalTestScript.logTestResult("Response: " + response.getStatusLine(), false, xmlDoc) ;
				return vp; 
			}

			
		}

	public boolean verifyResponse(String vpName, IResponse response, String xPath, Boolean children, Integer level, Boolean data, Boolean regularizeData)
	{

		boolean vp = false;
		String xmlDoc = "";
		boolean isResponseJSON = false;
		if(response.getStatusCode() == 200 ){

			String contentType = response.getHeader("Content-Type");
			System.out.println("Content-Type=" + contentType);

			if(contentType.toLowerCase().contains("application/json")){
				String json = response.getBody();
				JSONObject jsonObject = JSONObject.fromObject( json ); 
				xmlDoc = new XMLSerializer().write( jsonObject ); 
				isResponseJSON = true;
			}
			else {
				xmlDoc = response.getBody();
			}

			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();
			Element id_node = null;
			if(xPath!=null){
				
				id_node = (Element) getNode(response, xPath);
				
			}
			
			ITestDataTreeNode[] td = getTestData(doc,id_node,children,level, data, regularizeData);
			ITestData testData = VpUtil.getTestData(td);
			vp = performTest(new XManualVerificationPoint(vpName, this, testData));
			return vp;
		}
		else
		{
			RationalTestScript.logTestResult("Response: " + response.getStatusLine(), false, xmlDoc) ;
			return vp; 
		}

	}
}
