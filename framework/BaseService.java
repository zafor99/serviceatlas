package framework;

import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;
import static com.bn.qa.webservice.restclient.BNRestful.given;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.xml.serialize.OutputFormat;
import org.openqa.selenium.internal.seleniumemulation.GetExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.BNTimer;
import utils.EnvironmentUtility;
import utils.XMLUtility;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.xpath.XPathReader;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 31, 2013
 */
public abstract class BaseService extends CaliberService
{
	
	protected String fileName = "AdvanceShipNotice.xml";
	
	protected String filePath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\";
	protected XMLUtility xmlUtil = null;
	protected String url = "";
	protected String urlPath = "";
	protected static EnvironmentUtility envUtil = null;
	protected String clientId = "cstCIwT9TAyMHw9uuvy3Wg";
	protected IResponse response = null;
	protected static Map<String,String> headers = new HashMap<String,String>();

	public BaseService(){
		setHeader();
		envUtil = new EnvironmentUtility();
		loadXmlFile(fileName);
	}

	public BaseService(String path){
		urlPath = path;
		setHeader();
		envUtil = new EnvironmentUtility();
		loadXmlFile(fileName);
	}
	
	public void setHeader(){
		headers.put("Authorization", "Basic Z3hzOkNyNzZ3ZQ==");
		headers.put("bn_client_id", "gxs");
		headers.put("Content-Type", "application/xml");
	}
	public void setHeader(String authorization,String clientId, String contentType){
		if(authorization!=null && !authorization.contentEquals("!")){
			headers.put("Authorization", authorization);
			
		}
		if(clientId!=null && !clientId.contentEquals("!")){
			headers.put("bn_client_id", clientId);
			
		}
		if(contentType!=null && !contentType.contentEquals("!")){
			headers.put("Content-Type", contentType);
			
		}

	}
	public void setHeader(String header,String value){
		headers.put(header, value);
	}	
	
	public void loadXmlFile(String fileName){
		xmlUtil = new XMLUtility(filePath +fileName);
	}
	
	public static String setNodeValueByTagName(String xmlDoc, String node, String value){
		
		String xmlString = "";

		Document doc = getDocumentFromStream(xmlDoc);
		if(doc!=null)
		{
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName(node);
			Element element = (Element)nodeList.item(0);
			element.setTextContent(value);

			try
			{
				DOMSource domSource = new DOMSource(doc);
				StringWriter writer = new StringWriter();
				StreamResult result = new StreamResult(writer);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.transform(domSource, result);
				writer.flush();
				xmlString =  writer.toString();
			}
			catch(TransformerException ex)
			{
				ex.printStackTrace();
				return null;
			}

		} 
		return xmlString;
	}
	public void updateDatesForPurchaseOrderXML(){
		BNTimer timer = new BNTimer();
		String currentDate = null;
		String currentTime = null;
		String dateTime = null;
		currentDate = timer.getCurrentDate("yyyy-MM-dd").toString();
		currentTime = timer.getCurrentTime();
		dateTime = currentDate+"T"+currentTime;
		System.out.println(currentDate);
		System.out.println("currentTime  :"+currentTime);
		System.out.println(currentDate+"T"+currentTime);
		xmlUtil.setNodeValueByXPath("//ApplicationArea/CreationDateTime", dateTime);
		xmlUtil.setNodeValueByXPath("//DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentDateTime", dateTime);
		xmlUtil.setNodeValueByXPath("//DataArea/PurchaseOrder/PurchaseOrderHeader/RequestedShipDateTime", dateTime);
	}
	public void updateDatesForAdvanceShipNoticeXML(){
		BNTimer timer = new BNTimer();
		String currentDate = null;
		String currentTime = null;
		String dateTime = null;
		currentDate = timer.getCurrentDate("yyyy-MM-dd").toString();
		currentTime = timer.getCurrentTime();
		dateTime = currentDate+"T"+currentTime;
		System.out.println(currentDate);
		System.out.println("currentTime  :"+currentTime);
		System.out.println(currentDate+"T"+currentTime);
		xmlUtil.setNodeValueByXPath("//ApplicationArea/CreationDateTime", dateTime);
		xmlUtil.setNodeValueByXPath("//DocumentDateTime", dateTime);
		xmlUtil.setNodeValueByXPath("//ActualShipDateTime", dateTime);
		xmlUtil.setNodeValueByXPath("//ScheduledDeliveryDateTime", dateTime);
	}
	public void updateDatesForInvoiceXML(){
		BNTimer timer = new BNTimer();
		String currentDate = null;
		String currentTime = null;
		String dateTime = null;
		currentDate = timer.getCurrentDate("yyyy-MM-dd").toString();
		currentTime = timer.getCurrentTime();
		dateTime = currentDate+"T"+currentTime;
		System.out.println(currentDate);
		System.out.println("currentTime  :"+currentTime);
		System.out.println(currentDate+"T"+currentTime);
		xmlUtil.setNodeValueByXPath("//ApplicationArea/CreationDateTime", dateTime);
		xmlUtil.setNodeValueByXPath("//DocumentDateTime", dateTime);
	}

	public void setRandomDocumentID(){
		xmlUtil.setNodeValueByXPath("//DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentID/ID", "88"+ generateRandom(4));
	}
	public void setReferenceID(String referenceID){
		xmlUtil.setNodeValueByXPath("//ApplicationArea/Sender/ReferenceID", referenceID);
	}
	public void setRandomReferenceID(){
		xmlUtil.setNodeValueByXPath("//ApplicationArea/Sender/ReferenceID", "QA-TEAT2_"+ new Date().getTime());
	}
	public void setRandomShipmentDocumentID(){
		xmlUtil.setNodeValueByXPath("//DataArea/Shipment/ShipmentHeader/DocumentID/ID", generateRandom(13));
	}

	public void setShipmentDocumentID(String shipmentHeaderDocumentID){
		xmlUtil.setNodeValueByXPath("//DataArea/Shipment/ShipmentHeader/DocumentID/ID", shipmentHeaderDocumentID);
	}

	public void setDocumentID(String documentID){
		xmlUtil.setNodeValueByXPath("//DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentID/ID", documentID);
	}

	public void setPONumber(String documentID){
		xmlUtil.setNodeValueByXPath("//DataArea/Shipment/ShipmentUnit/ShipmentUnitItem/PurchaseOrderReference/DocumentID/ID", documentID);
	}

	public void setAlternateDocumentID(String alternateDocumentID){
		xmlUtil.setNodeValueByXPath("//NotifyShipment/ApplicationArea/Sender/ReferenceID", alternateDocumentID);
	}
	public void setRandomBatchNumber(){
		xmlUtil.setNodeAttributeByXPath("DeliveryConfirmationBatch", "BatchNumber", "QA-TEAT2_"+ new Date().getTime());
	}
	public void post(){
		String url = envUtil.eaiInternal().serverName() + urlPath;
		System.out.println(xmlUtil.getXMLString());
		response = caliber().content("application/xml").headers(headers).body(xmlUtil.getXMLString()).post(url);
		System.out.println(response.getStatusCode());
		
	}

	public void post(String url){
		
//		String url = envUtil.eai().serverName() + path;
		
		System.out.println(xmlUtil.getXMLString());
		response = caliber().content("application/xml").headers(headers).body(xmlUtil.getXMLString()).post(url);
		System.out.println(response.getStatusCode());
		
	}

	public boolean verifyResponse(String vpName,String expectedResponse){
		boolean result = false;
		String status = response.getStatusCode()+"";
		
		System.out.println("status code " + status);
		//System.out.println("status text " + response.getBody());
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName,expectedResponse,status).performTest();
		
		return result;
	}
	public boolean verifyResponseBody(String vpName,String expectedResponseBody){
		
		boolean result = false;
		System.out.println("Response Body :" + response.getBody());
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName,expectedResponseBody, response.getBody().trim() ).performTest();
		
		return result;
	}
	protected static String generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return new String(digits);
	}
	
	
	public static String getNodeValue(IResponse response, String node){
		
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
	
	public String searchAndReplace(String xml, String textToSearch, String textToReplace)
	{
		Random randomGenerator = new Random();
		Pattern pattern = Pattern.compile(textToSearch);
		 // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(xml);
        String outXML = matcher.replaceAll(textToReplace);
        //System.out.println(textToSearch+randomNum);
        return outXML;
	}
	
	public  double getMaxValue(double[] numbers)
	{  
		double maxValue = numbers[0];  
		  for(int i=1;i < numbers.length;i++){  
		    if(numbers[i] > maxValue){  
		      maxValue = numbers[i];  
		    }  
		  }  
		  return maxValue;  
	}  
	
	public String getSubstring(String str, int beginIndex, int endIndex)
	{
		String substring = "";
		if(str.equals("") || str == null)
		{
			return "";
		}
		else
		{
			substring = str.substring(beginIndex, endIndex);
		}
		return substring;
	}
	
	public String xmlPrityPrinting(Document document) {
		try {
			@SuppressWarnings("deprecation")
			OutputFormat format = new OutputFormat(document);
			format.setLineWidth(65);
			format.setIndenting(true);
			format.setIndent(4);
			Writer out = new StringWriter();
			
			//com.sun.org.apache.xml.internal.serialize.XMLSerializer serializer = new com.sun.org.apache.xml.internal.serialize.XMLSerializer(
			//		out, format);
			@SuppressWarnings("deprecation")
			org.apache.xml.serialize.XMLSerializer serializer = new org.apache.xml.serialize.XMLSerializer(
					out, format);
			
			//XMLSe
			serializer.serialize(document);

			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public  void logRequestXml(String xmlString){
		System.out.println(xmlString);
		Document doc = getDocumentFromStream(xmlString);
		doc.getDocumentElement().normalize();
		String xmlResponse = xmlPrityPrinting(doc);
		ITestData testData = VpUtil.getTestData(xmlResponse);
		this.vpManual("requestXML",testData ,testData).performTest();
	}
	
	public  void logResponseXml(String xmlString){
		System.out.println(xmlString);
		Document doc = getDocumentFromStream(xmlString);
		doc.getDocumentElement().normalize();
		String xmlResponse = xmlPrityPrinting(doc);
		ITestData testData = VpUtil.getTestData(xmlResponse);
		this.vpManual("responseXML",testData ,testData).performTest();
	}
	public static int getNodeCount(IResponse response, String xpath){

		boolean vp = false;
		String xmlDoc = "";
		Object value="";
		xpath = "count(" + xpath + ")";

		if(response.getStatusCode() == 200 ){

			String contentType = response.getHeader("Content-Type");
			System.out.println("Content-Type=" + contentType);
			if(contentType == null)
			{
				contentType = "";
			}
			if(contentType.toLowerCase().contains("application/json")){
				String json = response.getBody();
				JSONObject jsonObject = JSONObject.fromObject( json ); 
				xmlDoc = new XMLSerializer().write( jsonObject ); 
			}
			else 
			{
				xmlDoc = response.getBody();
			}


			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();
			XPathReader xPath = new XPathReader(xmlDoc);

			value = xPath.read(xpath,XPathConstants.STRING);


		}
		return Integer.parseInt(value.toString());
	}

}
