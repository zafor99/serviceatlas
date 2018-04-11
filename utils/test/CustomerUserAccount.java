package com.atg_sync_services.framework;

import static com.bn.qa.webservice.restclient.BNRestful.given;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;
import com.bn.qa.webservice.xpath.XPathReader;
//import com.bn.qa.xobject.script.XObjectTestScript;
//import com.rational.test.ft.script.RationalTestScript;


public class CustomerUserAccount {
	
	private File directory = new File(".");
	private String emailAddress = "";
	private String firstName = "";
	private String lastName = "";
	private String hintsQuestionId = "";
	private String hintAnswer = "";
	
	private String customerId = "";
	private String addressId = "";
	private IResponse response = null;
	private String server = "http://injsvcjapp08";
	private String port = ":8080";
	
	private String createAccountURL = server + port + "/AccountService/createBuyerAccount";
	private String updateCreditCardUrl = server + port + "/AccountService/addToWallet?";
	private String defaultCreditCardUrl = server + port + "/AccountService/setDefaultPaymentMethod?";
	private String addressBookUrl = server + port + "/AccountService/addToAddressBook?";
	private String defaultAddressAndShippingUrl = server + port + "/AccountService/setDefaultShipping?";
	
	private String createAccountXmlFile = directory.getAbsolutePath() + "/src/test/resources/testdata/CreateAccount.xml";
	
	private String updateCreditCardXmlFile = directory.getAbsolutePath()  + "/src/test/resources/testdata/UpdateCreditCard.xml";
	private String AddressBookXmlFile = directory.getAbsolutePath()  + "/src/test/resources/testdata/AddressBook.xml";
	
/*	private String createAccountXmlFile = getCurrentProject().getLocation() + "/testData/CreateAccount.xml";
	private String updateCreditCardXmlFile = getCurrentProject().getLocation() + "/testData/UpdateCreditCard.xml";
	private String AddressBookXmlFile = getCurrentProject().getLocation() + "/testData/AddressBook.xml";*/
	private String xmlCreateAccountContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n<profile>\r\n" + 
											 "    <email>qaautomation@book.com</email> \r\n" + 
											 "    <password>qatester</password>\r\n" + 
											 "    <lastName>Test</lastName>\r\n" + 
											 "    <firstName>Johnny</firstName>\r\n" + 
											 "    <hintQuestionID>1</hintQuestionID>\r\n" + 
											 "    <hintAnswer>New York</hintAnswer>\r\n</profile>\r\n";
	
	private String xmlUpdateCreditCardContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
												"<creditCard><billingAddressID>63764776</billingAddressID><code>MC</code>\r\n" + 
												"<expiry>2015-10-01T00:00:00-04:00</expiry>\r\n<numberPlain>5454545454545454</numberPlain>\r\n" + 
												"</creditCard>\r\n";
	private Document docCreateAccountXml = null;
	private String creditCardId = "";
	
	//private static Logger logger = Logger.getLogger(CustomerUserAccount.class);
	public static IRequestSpecification caliber() {
		
		return given();
	}
	
	public CustomerUserAccount(){

	}
	
	public void generateUserAccount(){
		String randomNum = generateRandom(7);
		String emailAddress = "qaautomation" + randomNum + "@book.com";
		this.emailAddress = emailAddress;
		
		try {
			readFile();
			createAccount();
			Thread.sleep(2000);
			setAddressBook();
			Thread.sleep(2000);
			setDefaultAdressAndShipping();
			Thread.sleep(2000);
			updateCreditCard();
			Thread.sleep(2000);
			setDefaultCreditCard();
			displayProperties();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void generateUserAccount(boolean setAddressBook,boolean setDefaultAdressAndShipping,boolean updateCreditCard, boolean setDefaultCreditCard){
		String randomNum = generateRandom(7);
		String emailAddress = "qaautomation" + randomNum + "@book.com";
		this.emailAddress = emailAddress;
		try {
			readFile();
			createAccount();
			Thread.sleep(2000);
			if (setAddressBook) setAddressBook();
			Thread.sleep(2000);
			if (setDefaultAdressAndShipping) setDefaultAdressAndShipping();
			Thread.sleep(2000);
			if (updateCreditCard) updateCreditCard();
			Thread.sleep(2000);
			if (setDefaultCreditCard) setDefaultCreditCard();
			Thread.sleep(2000);
			displayProperties();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void generateUserAccount(String emailAddress){
		this.emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard();
		setDefaultCreditCard();
		displayProperties();
	}
		
	public void readFile(){
		try {
			
			String xml = Util.readFile(createAccountXmlFile);
			xmlCreateAccountContent = xml;
			System.out.println(xmlCreateAccountContent);
			//docCreateAccountXml = getDocumentFromStream(xmlCreateAccountContent);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readFile( String fileName){
		String xml = "";
		try {			
			xml = Util.readFile(fileName);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}
	
	public void setCreateAccountUrl(String url){
		createAccountURL = url;
	}

	public String getCreateAccountUrl(String url){
		return createAccountURL;
	}
	
	public String getEmailAddress(){
		return emailAddress;
	}
	
	public String getCustomerId(){
		return customerId;
	}
	
	public String getAddressId(){
		return addressId;
	}
	
	public void setEmailAddress(String addressId){
		
		this.emailAddress = addressId;
		
	}
	
	public void setAddressXMLFile(String xmlFile){
		this.AddressBookXmlFile = directory.getAbsolutePath() + "/testData/" + xmlFile;
	}
	
	public void setAddressId(String addressId){
		
		this.addressId = addressId;
		
	}
	
	public String getCreditId(){
		return creditCardId;
	}
	
	public void setUpdateCreditCardXmlFile(String xmlFile){
		this.updateCreditCardXmlFile = directory.getAbsolutePath() + "/testData/" + xmlFile;
	}
	
	private Document getDocumentFromStream(String xmlString) {
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
	
	private IResponse doGetRequest(String url, Map<String,String> parameters){
		
		//logger.info("doGetRequest");
		
		IResponse response = null;
		try{
			System.out.println("RestCall: " + Util.queryString(url, parameters).replaceAll("%7C", "|").replaceAll("%40","@").replaceAll("%20", ",").replace("%26", "&").replace("%3D", "="));
			response = caliber().parameters(parameters).get(url);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return response;
	}
	
	public String getNodeValue(IResponse response, String node){
		
		boolean vp = false;
		Object value="";
		String xmlDoc = "";
		
		xmlDoc = response.getBody();
		Document doc = getDocumentFromStream(xmlDoc);
		if(response.getStatusCode() == 200 ){
			doc.getDocumentElement().normalize();
			XPathReader xPath = new XPathReader(xmlDoc);

			value = xPath.read(node,XPathConstants.STRING);

		}
		return value.toString();
	}
	
	public String setNodeValueByTagName(String xmlDoc, String node, String value){
		
		String xmlString = "";

		Document doc = getDocumentFromStream(xmlDoc);
		if(doc!=null){
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
	
	public String generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return new String(digits);
	}
	
	public String generateRandomEmailAddress(){
		
		String randomNum = generateRandom(7);
		String emailAddress = "qaautomation" + randomNum + "@book.com";
		this.emailAddress = emailAddress;
		System.out.println("Email Address : " + this.emailAddress);
		return this.emailAddress;
	}
		
	public boolean createRandomAccount(){
		
		boolean result = false;
		String randomNum = generateRandom(7);
		String emailAddress = "qaautomation" + randomNum + "@book.com";
		this.emailAddress = emailAddress;
		
		String xml = readFile(createAccountXmlFile);
		xml = setNodeValueByTagName(xml, "email", emailAddress);
		System.out.println("Create Account URL : " + createAccountURL);
		System.out.println(xml);
		
		response = caliber().body(xml).contentType("application/xml").post(createAccountURL);
		
		if(response.getStatusCode() == 200 ){
			customerId = getNodeValue(response, "/response/customerID");
			result = true;
		}
		displayProperties();
		return result;
	}
	
	public boolean createAccount(){
		
		boolean result = false;

		String xml = readFile(createAccountXmlFile);
		xml = setNodeValueByTagName(xml, "email", emailAddress);
		System.out.println("Create Account URL : " + createAccountURL);
		System.out.println(xml);
		
		response = caliber().body(xml).contentType("application/xml").post(createAccountURL);
		
		if(response.getStatusCode() == 200 ){
			customerId = getNodeValue(response, "/response/customerID");
			result = true;
		}
		
		return result;
	}
	
	public void setAddressBook(){
		boolean result = false;
		String xml;
		String url = "";
		try {
			
			xml = readFile(AddressBookXmlFile);
			url = addressBookUrl + "customerid=" + customerId;  
			System.out.println("Set Address Book URL : " + url);
			IResponse response = caliber().body(xml).contentType("application/xml").post(url);
			if(response.getStatusCode() == 200 ){
				
				addressId = getNodeValue(response,"/response/address/addressID");
				System.out.println("Adress Id: " + addressId);
				
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateCreditCard(){
		
		boolean result = false;
		String xml;
		String url = "";
		
		try {
			url = updateCreditCardUrl + "customerid=" + customerId;
			//System.out.println("Update Credit Card URL : " + url);
			
			xml = readFile(updateCreditCardXmlFile);
			
			xml = setNodeValueByTagName(xml, "billingAddressID", addressId);
			System.out.println("Update Credit Card URL : " + url);
			System.out.println(xml);
			IResponse response = caliber().body(xml).contentType("application/xml").post(url);
			
			if(response.getStatusCode() == 200 ){
				creditCardId = getNodeValue(response,"/response/creditCard/pid");
				System.out.println("Credit card successfully updated");
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public boolean setDefaultAdressAndShipping(){
		
		boolean result = false;
		String url = "";
		
		Map<String,String> parameters = new HashMap<String,String>();
		
		parameters.put("customerid", customerId);
		parameters.put("shippingaddressid", addressId);	
		parameters.put("shipmethod", "27");	
		System.out.println("Set Default Address URL : " + defaultAddressAndShippingUrl);
		IResponse response = doGetRequest(defaultAddressAndShippingUrl, parameters);
		if(response.getStatusCode() == 200 ){						
			result = true;
			System.out.println("Default Address and shipping method successfully implemented");
		}
		
		return result;
	}
	
	public boolean setDefaultCreditCard(){
		boolean result = false;
		String url = "";		
		Map<String,String> parameters = new HashMap<String,String>();
		
		parameters.put("customerid", customerId);
		parameters.put("cardid", creditCardId);		

		IResponse response = doGetRequest(defaultCreditCardUrl, parameters);
		if(response.getStatusCode() == 200 ){						
			result = true;
			System.out.println("Default Credit card successfully implemented");
		}
		
		return result;
	}
	
	
	
	public void displayProperties()
	{
		System.out.println("Email Address : " + emailAddress);
		System.out.println("Cusromer Id : " + customerId);
		System.out.println("Credit Id : " + creditCardId);
		System.out.println("Address Id : " + addressId);
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CustomerUserAccount cust = new CustomerUserAccount();
		cust.generateUserAccount();
		cust.displayProperties();

		
	}


}
