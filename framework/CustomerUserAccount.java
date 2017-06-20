package framework;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import org.apache.log4j.Logger;
//import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.EnvironmentUtility;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;
import com.bn.qa.webservice.xpath.XPathReader;
import com.bn.qa.xobject.script.XObjectTestScript;
import com.rational.test.ft.script.RationalTestScript;

import static com.bn.qa.webservice.restclient.BNRestful.given;
import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;

/**
 * Description   : Super class for script helper
 * 
 * @author sanjum
 * @since  March 28, 2012
 */
public class CustomerUserAccount extends BaseService {
	
	 
	protected static String emailAddress = "";
	protected String firstName = "";
	protected String lastName = "";
	protected String hintsQuestionId = "";
	protected String hintAnswer = "";
	protected static String customerId = "";
	protected String addressId = "";
	protected static IResponse response = null;
	protected static String securityQuestionId = "";
	protected static	String securityAnswer = "";
	public static String[] alphaNumeric = {"A","B","1","C","2","D","E","3","4","F","5","G","H","I","6","J","K","7","L","M","N","O","8","P","Q","9","R","S","0","T","U","V","W","X","Y","Z"};	
	private static String createAccountURL = envUtil.caliber().serverName() + "AccountService/createBuyerAccount";
	private String updateCreditCardUrl =  envUtil.caliber().serverName() + "/AccountService/addToWallet?";
	private String addPaymentMethodUrl = "http://injsvcjapp08:8080/AccountService/addPaymentMethod?";
		//"http://injsvcjapp06:8080/AccountService/addPaymentMethod?";
		//envUtil.caliber().serverName() + "/AccountService/addPaymentMethod?";
	private String addGCtoWalletUrl = envUtil.caliber().serverName() + "/AccountService/addGCToWallet?customerid=";
	private String defaultCreditCardUrl =  envUtil.caliber().serverName() + "/AccountService/setDefaultPaymentMethod?";
	private String addressBookUrl =  envUtil.caliber().serverName() + "/AccountService/addToAddressBook?";
	private String defaultAddressAndShippingUrl =  envUtil.caliber().serverName() + "/AccountService/setDefaultShipping?";
	private static String createAccountXmlFile = getCurrentProject().getLocation() + "/testData/CreateAccount.xml";
	private String updateCreditCardXmlFile = getCurrentProject().getLocation() + "/testData/UpdateCreditCard.xml";
	private String addPaymentXMLFile = getCurrentProject().getLocation() + "/testData/addPayment.xml";
	private String updateCreditCardXmlFile_ExpiredCC = getCurrentProject().getLocation() + "/testData/UpdateCreditCard_Expired.xml";
	private String AddressBookXmlFile = getCurrentProject().getLocation() + "/testData/AddressBook.xml";
	private String AddressBookXmlFile_Canada = getCurrentProject().getLocation() + "/testData/AddressBook_Canada.xml";
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
	private String xmlGiftCard = "<giftCard><plainAccountNum>627715099904597</plainAccountNum><plainPin>7628</plainPin><retailerId>BN2</retailerId></giftCard>";
	private Document docCreateAccountXml = null;
	private String creditCardId = "";
	
	private static String URL = "jdbc:oracle:thin:@//10.1.161.175:1521/INYHG.barnesandnoble.com";
	private static String userName_userProfile = "si_user";
	private static String password_userProfile = "si_user";
	private static Statement statement = null;
	private static Connection connection = null; 
	private static String contentType = "";
	private static String header = "";
	private static String body = "";

	private static Logger logger = Logger.getLogger(CustomerUserAccount.class);
	public static IRequestSpecification caliber() {
		
		return given();
	}
	public CustomerUserAccount(){
		createAccountURL = envUtil.caliber().serverName()+"/AccountService/createBuyerAccount";
	}
	public CustomerUserAccount(String serverPath){
		createAccountURL = serverPath;
	}
	


	private CustomerUserAccount(String countryCode, String retailer, String locale)
	{
		if(countryCode.equalsIgnoreCase("US"))
		{
			this.createAccountURL =  EnvironmentUtility.customerAccDom().serverName()+ "/AccountService/createBuyerAccount";
			this.contentType = "application/xml";
			this.header = "application/xml";
			String randomNum = generateRandom(6);
			String emailAddress = "instant_purchase2" + randomNum + "@book.com";
			this.emailAddress = emailAddress;
			
			this.body = readFile(createAccountXmlFile);
			this.body = setNodeValueByTagName(this.body, "email", emailAddress);
			System.out.println("Create Account URL : " + createAccountURL);
			System.out.println(this.body); 
			
		}
		else
		{
			
			createAccountURL = EnvironmentUtility.customerAccIntl().serverName()+ "/bncloud/api/internal/customer/account";
			this.header = "application/json";
			this.contentType = "application/json";
			this.body = getInternationalJsonRequest(retailer, countryCode, locale);
		}
		
	}
	
	 public static String createInternationalAccount(String retailer, String countryCode, String locale){
			
			boolean result = false;
			//setCreateAccXMLPath(intlXMLPath);
			createAccountURL = EnvironmentUtility.customerAccIntl().serverName()+ "/bncloud/api/internal/customer/account";
			String jsonRequest = getInternationalJsonRequest(retailer, countryCode, locale);
			
			System.out.println("Create Account URL : " + createAccountURL);
			System.out.println(jsonRequest);
			
			response = caliber().body(jsonRequest).contentType("application/json").post(createAccountURL);
			
			if(response.getStatusCode() == 200 ){
				customerId = getNodeValue(response, "//custId");
				System.out.println("Customer Id: " + customerId);
				result = true;
			}
			
			return customerId;
	}
	 public  String createUKAccountWithAddPayment(String payType,String cardNumber){
			
		  	String text=null;
		 	createInternationalAccount("NOK","GB","en_GB");
			addPayment(payType,cardNumber,getUKAddressAddPaymentXML());
			displayProperties();
			text = this.customerId + "\t" + this.emailAddress + "\t" + this.creditCardId + "\t" + this.addressId;
			return customerId;
	}
	
	public String getUKAddressAddPaymentXML(){
		String ukAddressAddPaymentXML = "<paymentMethod> " +
	    "                             <creditCard> " +
	    "                             <code>VI</code> " +
	    "                             <expiryMMYYYY>062017</expiryMMYYYY> " +
	    "                             <securityCode>562</securityCode> " +//securiy code MC and others 562 and AX 1234 
	    "                             <numberPlain>4313081835209051</numberPlain> " +
	    "                             <isDefault>true</isDefault> " +
	    "                             <address> " +
	    "                             <checkSum>51201</checkSum>" + 
	    "                             <city>Kidderminster</city> " +
	    "                             <company>Barnes and Noble</company> " +
	    "                             <countryNum>826</countryNum> " +
	    "                             <firstName>John</firstName> " +
	    "                             <lastName>Smith</lastName> " +
	    "                             <line1>79-84 Worcester Street</line1> " +
	    "                             <line2></line2> " +
	    "                             <line3></line3> " +
	    "                             <nickName>Nickname</nickName> " +
	    "                             <state></state> " +
	    "                             <zip>DY10 1EH</zip> " +
	    "                             </address> " +
	    "                             </creditCard> " +
	    "                             </paymentMethod>";  //
		
		return ukAddressAddPaymentXML;

	}
	public long getRandomNumber(){
		Random randomGenerator = new Random();
        long randomNum = randomGenerator.nextInt(1000);
        
        return randomNum;

	}
	public static long getRandomNumber(int range){
		Random randomGenerator = new Random();
        long randomNum = randomGenerator.nextInt(range);
        
        return randomNum;

	}
	public int getRandomNumber_Int(int range){
		Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(range);
        
        return randomNum;

	}
	
	public String getRandomEmailAddress(){
		return "qaautomation" + getRandomString() + "@book.com";
	}
	
	public void setEmailaddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
	public static void setCreateAccXMLPath(String createAccXMLPath){
		createAccountXmlFile = createAccXMLPath;
	}
	public static String getRandomString()
	{
		String sessionId = "";
		int randChar = 0;
		
		for(int i=0;i<8;i++)
		{
			randChar = Integer.parseInt(getRandomNumber(alphaNumeric.length)+"");
			sessionId = sessionId + alphaNumeric[randChar].toLowerCase();
		}
		return sessionId;
	}
	
	public static String getSecurityQuestionId() {
		return securityQuestionId;
	}
	/*public void setSecurityQuestionId(String securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}*/
	public static String getSecurityAnswer() {
		return securityAnswer;
	}
	
	 public static String getInternationalJsonRequest(String retailer, String countryCode, String locale)
	    {
	    	//String email = countryCode.toLowerCase() + "mswalletemail_" + getRandomString() + "@book.com";
		 	String randomNum = generateRandom(10);
		 	emailAddress = EnvironmentUtility.atlas().serverName().substring(7, 17)+"_"+ randomNum + "@book.com";
	    	setSecurityQuestionIdAndAnswer(locale);
	    	String securityQuesId = getSecurityQuestionId();
	    	String securityAnswer = getSecurityAnswer();
	    	
	    	String json = "{\"email\":\"" + emailAddress + "\"," +
	    					"\"password\":\"welcome\"," + 
	    					"\"firstName\":\"accountfn\"," +
	    					"\"lastName\":\"testln\"," +
	    					"\"securityQuestionId\":\"" + securityQuesId + "\"," +
	    					"\"securityAnswer\":\"" + securityAnswer + "\"," +
	    					"\"userLocale\":\""+ locale + "\"," +
	    					"\"retailerCode\":\""+ retailer + "\"," +
	    					"\"retailerActiveCountry\":\""+ countryCode + "\"" +
	    				
	    					"}";
	    	
	    	return json;
	    }
	    
	    private static void setSecurityQuestionIdAndAnswer(String locale)
	    {
	    	if(locale.equals("en_US"))
	    	{
	    		securityQuestionId = "21";
	    		securityAnswer = "Erica";
	    	}
	    	else if(locale.equals("en_GB"))
	    	{
	    		securityQuestionId = "49";
	    		securityAnswer = "Honda";
	    	}
	    	else if(locale.equals("cs_CZ"))
	    	{
	    		securityQuestionId = "408";
	    		securityAnswer = "CzechRepublic";
	    	}
	    	else if(locale.equals("da_DK"))
	    	{
	    		securityQuestionId = "416";
	    		securityAnswer = "COPENHAGEN";
	    	}
	    	else if(locale.equals("de_DE"))
	    	{
	    		securityQuestionId = "86";
	    		securityAnswer = "Chaffee";
	    	}
	    	else if(locale.equals("el_GR"))
	    	{
	    		securityQuestionId = "440";
	    		securityAnswer = "Athens";
	    	}
	    	else if(locale.equals("et_EE"))
	    	{
	    		securityQuestionId = "424";
	    		securityAnswer = "Tallinn";
	    	}
	    	else if(locale.equals("fr_FR"))
	    	{
	    		securityQuestionId = "53";
	    		securityAnswer = "Paris";
	    	}
	    	else if(locale.equals("hu_HU"))
	    	{
	    		securityQuestionId = "448";
	    		securityAnswer = "Budapest";
	    	}
	    	else if(locale.equals("it_IT"))
	    	{
	    		securityQuestionId = "77";
	    		securityAnswer = "Milan";
	    	}
	    	else if(locale.equals("lt_LT"))
	    	{
	    		securityQuestionId = "536";
	    		securityAnswer = "Vilnius";
	    	}
	    	else if(locale.equals("lv_LV"))
	    	{
	    		securityQuestionId = "457";
	    		securityAnswer = "Allen";
	    	}
	    	else if(locale.equals("mt_MT"))
	    	{
	    		securityQuestionId = "464";
	    		securityAnswer = "Floriana";
	    	}
	    	else if(locale.equals("nl_NL"))
	    	{
	    		securityQuestionId = "472";
	    		securityAnswer = "Holand";
	    	}
	    	else if(locale.equals("pl_PL"))
	    	{
	    		securityQuestionId = "480";
	    		securityAnswer = "Warsaw";
	    	}
	    	else if(locale.equals("pt_PT"))
	    	{
	    		securityQuestionId = "488";
	    		securityAnswer = "Lisbon";
	    	}
	    	else if(locale.equals("ro_RO"))
	    	{
	    		securityQuestionId = "496";
	    		securityAnswer = "Bucharest";
	    	}
	    	else if(locale.equals("sk_SK"))
	    	{
	    		securityQuestionId = "510";
	    		securityAnswer = "Avatar";
	    	}
	    	else if(locale.equals("sl_SI"))
	    	{
	    		securityQuestionId = "518";
	    		securityAnswer = "Avatar";
	    	}
	    	else if(locale.equals("sv_FI"))
	    	{
	    		securityQuestionId = "437";
	    		securityAnswer = "Blinky";
	    	}
	    	else if(locale.equals("sv_SE"))
	    	{
	    		securityQuestionId = "527";
	    		securityAnswer = "Sweden";
	    	}
	    	else if(locale.equals("tr_CY"))
	    	{
	    		securityQuestionId = "400";
	    		securityAnswer = "Nicosia";
	    	}
	    	
	    	
	    }
	    
	    public void setSecurityAnswer(String securityAnswer) {
			securityAnswer = securityAnswer;
		}
	public String getNewCustomerId(String id){
		long randomNum = getRandomNumber();
		String randomString = getRandomString(); 
		String emailAddress = id + "_" + randomString + randomNum + "@book.com";
		emailAddress = emailAddress;
		readFile();
		createAccount();
		displayProperties();
		return customerId;
	}
	/*public String getNewCustomerId(String id,String intlCreateAccXMLPath){
		long randomNum = getRandomNumber();
		String randomString = getRandomString(); 
		String emailAddress = id + "_" + randomString + randomNum + "@book.com";
		emailAddress = emailAddress;
		readFile();
		createAccount(intlCreateAccXMLPath);
		displayProperties();
		return customerId;
	}*/
	public String getNewCustomerId_DefaultAddressAndShippingAndCC(String id){
		//long randomNum = getRandomNumber();
		//String randomString = getRandomString(); 
		//String emailAddress = id + "_" + randomString + randomNum + "@book.com";
		
		String text;
	//	emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard();
		setDefaultCreditCard();
		displayProperties();
		text = customerId;
		return text;
	}
	public String getNewCustomerId_NoDefaultAddressAndShippingAndCC(String id){
		long randomNum = getRandomNumber();
		String randomString = getRandomString(); 
		String emailAddress = id + "_" + randomString + randomNum + "@book.com";
		
		String text;
		emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		updateCreditCard();
		displayProperties();
		text = customerId;
		return text;
		
	}
	public String getNewCustomerId_DefaultAddressAndShippingAndCC_Foreign(String id){
		long randomNum = getRandomNumber();
		String randomString = getRandomString(); 
		String emailAddress = id + "_" + randomString + randomNum + "@book.com";

		String text;
		emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard();
		setDefaultCreditCard();
		displayProperties();
		text = customerId;
		return text;
		
	}
	
	public String getNewCustomerId_ExpiredCC(String id){
		long randomNum = getRandomNumber();
		String randomString = getRandomString(); 
		String emailAddress = id + "_" + randomString + randomNum + "@book.com";
		String text;
		emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard_ExpiredCC();
		setDefaultCreditCard();
		displayProperties();
		text = customerId + "\t" + emailAddress;
		return text;
	}
	public String getNewCustomerIdAndEmail(String id){
		long randomNum = getRandomNumber();
		String randomString = getRandomString(); 
		//String emailAddress = id + "_" + randomString + randomNum + "@book.com";
		String emailAddress = id + "_" + randomNum + "@book.com";
		
		emailAddress = emailAddress;
		String text = "";
		readFile();
		createAccount();
		/*setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard();
		setDefaultCreditCard();*/
		displayProperties();
		text = customerId + "\t" + emailAddress;
		return text;
	}
	
	public void generateUserAccount()
	{
		String randomNum = generateRandom(6);
		String emailAddress = "instant_purchase" + randomNum + "@book.com";
		emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard();
		setDefaultCreditCard();
		displayProperties();
	}
	
	public void generateUserAccount(String emailAddress){
		this.emailAddress = emailAddress;
		readFile();
		createAccount(emailAddress);
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard();
		setDefaultCreditCard();
		displayProperties();
	}
	
	public void generateUserAccount(String payType, String carNumber)
	{
		String randomNum = generateRandom(6);
		String emailAddress = "instant_purchase" + randomNum + "@book.com";
		this.emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook();
		setDefaultAdressAndShipping();
		updateCreditCard(payType,carNumber);
		setDefaultCreditCard();
		displayProperties();
	}
	public String generateUserAccount(String payType, String carNumber, String xmlFileNameForAddress)
	{
		String randomNum = generateRandom(6);
		String emailAddress = "instant_purchase2" + randomNum + "@book.com";
		String text="";
		this.emailAddress = emailAddress;
		readFile();
		createAccount();
		setAddressBook(xmlFileNameForAddress);
		setDefaultAdressAndShipping();
		updateCreditCard(payType,carNumber);
		setDefaultCreditCard();
		displayProperties();
		text = this.customerId + "\t" + this.emailAddress + "\t" + this.creditCardId + "\t" + this.addressId;
		return text;
	}
	public String generateUserAccountWithPayment(String payType, String cardNumber, String xml)
	{
		String randomNum = generateRandom(10);
		String emailAddress = EnvironmentUtility.atlas().serverName().substring(7, 17)+"_" + randomNum + "@book.com";
		String text="";
		this.emailAddress = emailAddress;
		readFile();
		createAccount();
		addPayment(payType,cardNumber);
		displayProperties();
		text = this.customerId + "\t" + this.emailAddress + "\t" + this.creditCardId + "\t" + this.addressId;
		return text;
	}
	public String generateUserAccountWithPayment(String emailContains,String payType, String cardNumber, String xml)
	{
		String randomNum = generateRandom(10);
		String emailAddress = emailContains + randomNum + "@book.com";
		String text="";
		this.emailAddress = emailAddress;
		readFile();
		createAccount(emailAddress);
		addPayment(payType,cardNumber);
		displayProperties();
		text = this.customerId + "\t" + this.emailAddress + "\t" + this.creditCardId + "\t" + this.addressId;
		return text;
	}
	public String generateUserAccountWithPayment(String payType, String cardNumber, String xml,boolean isTokenize)
	{
		String text="";
		readFile();
		createAccount();
		addPayment(payType,cardNumber,isTokenize);
		displayProperties();
		text = this.customerId + "\t" + this.emailAddress + "\t" + this.creditCardId + "\t" + this.addressId;
		return text;
	}
	public boolean addPayment(String payType,String cardNumber){
		boolean result = false;
		String xml;
		String url = "";
		
		try {
//				url = addPaymentMethodUrl + "customerid=" + customerId + "&validateaddress=true&tokenize=true";
				url = addPaymentMethodUrl + "customerid=" + customerId + "&validateaddress=true";
			System.out.println("Add Payment Method URL : " + url);
			
			xml = readFile(addPaymentXMLFile);
			xml = setNodeValueByTagName(xml, "numberPlain", cardNumber);
			xml = setNodeValueByTagName(xml, "code", payType);
			System.out.println(xml);
			IResponse response = caliber().body(xml).contentType("application/xml").post(url);
			
			if(response.getStatusCode() == 200 ){
				System.out.println("Add Payment is  successfull");
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean addPayment(String payType,String cardNumber,String addPaymentXML){
		boolean result = false;
		//String xml=addPaymentXML;
		String url = "";
		
		try {
//				url = addPaymentMethodUrl + "customerid=" + customerId + "&validateaddress=true&tokenize=true";
				url = addPaymentMethodUrl + "customerid=" + customerId + "&validateaddress=true";
			System.out.println("Add Payment Method URL : " + url);
			
		//	xml = readFile(addPaymentXML);
			addPaymentXML = setNodeValueByTagName(addPaymentXML, "numberPlain", cardNumber);
			addPaymentXML = setNodeValueByTagName(addPaymentXML, "code", payType);
			System.out.println(addPaymentXML);
			IResponse response = caliber().body(addPaymentXML).contentType("application/xml").post(url);
			
			if(response.getStatusCode() == 200 ){
				System.out.println("Add Payment is  successfull");
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean addPayment(String payType,String cardNumber,boolean isTokenized){
		boolean result = false;
		String xml;
		String url = "";
		
		try {
			if(isTokenized){
				url = addPaymentMethodUrl + "customerid=" + customerId + "&validateaddress=true&tokenize=true";
			}
			else{
				url = addPaymentMethodUrl + "customerid=" + customerId + "&validateaddress=true";	
			}
			System.out.println("Add Payment Method URL : " + url);
			
			xml = readFile(addPaymentXMLFile);
			xml = setNodeValueByTagName(xml, "numberPlain", cardNumber);
			xml = setNodeValueByTagName(xml, "code", payType);
			System.out.println(xml);
			IResponse response = caliber().body(xml).contentType("application/xml").post(url);
			
			if(response.getStatusCode() == 200 ){
				System.out.println("Add Payment is  successfull");
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
	
	public static String readFile( String fileName){
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
	
	public void setAddressId(String addressId){
		
		this.addressId = addressId;
		
	}
	
	public String getCreditId(){
		return creditCardId;
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
	
	private IResponse doGetRequest(String url, Map<String,String> parameters){
		
		logger.info("doGetRequest");
		
		IResponse response = null;
		try{
			logInfo("RestCall: " + Util.queryString(url, parameters).replaceAll("%7C", "|").replaceAll("%40","@").replaceAll("%20", ",").replace("%26", "&").replace("%3D", "="));
			response = caliber().parameters(parameters).get(url);
			
		}
		catch(Exception ex)
		{
			logException(ex);
		}
		
		return response;
	}
	
	/*public String getNodeValue(IResponse response, String node){
		
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
	
	private String generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return new String(digits);
	}*/
		
	public boolean createRandomAccount(){
		
		boolean result = false;
		String randomNum = generateRandom(10);
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
	
	public static boolean createAccount(){
		
		boolean result = false;
		createAccountURL = envUtil.caliber().serverName() + "/AccountService/createBuyerAccount";
		String randomNum = generateRandom(10);
		emailAddress = EnvironmentUtility.atlas().serverName().substring(7, 17)+"_"+ randomNum + "@book.com";

		
		String xml = readFile(createAccountXmlFile);
		xml = setNodeValueByTagName(xml, "email", emailAddress);
		System.out.println("Create Account URL : " + createAccountURL);
		System.out.println(xml);
		
		response = caliber().body(xml).contentType("application/xml").post(createAccountURL);
		
		if(response.getStatusCode() == 200 ){
			customerId = getNodeValue(response, "/response/customerID");
			System.out.println("Customer Id: " + customerId);
			result = true;
		}
		else{
			logError("unable to create customer account. Response code :"+response.getStatusCode());
		}
		
		return result;
	}
	public static boolean createAccount(String nodeName1,String nodeValue1,String nodeName2,String nodeValue2){
		
		boolean result = false;
		createAccountURL = envUtil.caliber().serverName() + "/AccountService/createBuyerAccount";
		String randomNum = generateRandom(10);
		emailAddress = EnvironmentUtility.atlas().serverName().substring(7, 17)+"_"+ randomNum + "@book.com";

		
		String xml = readFile(createAccountXmlFile);
		xml = setNodeValueByTagName(xml, "email", emailAddress);
		xml = setNodeValueByTagName(xml, nodeName1, nodeValue1);
		xml = setNodeValueByTagName(xml, nodeName2, nodeValue2);
		System.out.println("Create Account URL : " + createAccountURL);
		System.out.println(xml);
		
		response = caliber().body(xml).contentType("application/xml").post(createAccountURL);
		
		if(response.getStatusCode() == 200 ){
			customerId = getNodeValue(response, "/response/customerID");
			System.out.println("Customer Id: " + customerId);
			result = true;
		}
		else{
			logError("unable to create customer account. Response code :"+response.getStatusCode());
		}
		
		return result;
	}
	public static boolean createAccount(String emailAddress){
		
		boolean result = false;
		String email = emailAddress;
		createAccountURL = envUtil.caliber().serverName() + "/AccountService/createBuyerAccount";
/*		String randomNum = generateRandom(10);
		//emailAddress = emailContains + randomNum + "@book.com";
*/		
		
		String xml = readFile(createAccountXmlFile);
		xml = setNodeValueByTagName(xml, "email", emailAddress);
		System.out.println("Create Account URL : " + createAccountURL);
		System.out.println(xml);
		
		response = caliber().body(xml).contentType("application/xml").post(createAccountURL);
		
		if(response.getStatusCode() == 200 ){
			customerId = getNodeValue(response, "/response/customerID");
			System.out.println("Customer Id: " + customerId);
			result = true;
		}
		else{
			logError("unable to create customer account. Response code :"+response.getStatusCode());
		}
		
		return result;
	}
/*   public static boolean createAccount(String intlXMLPath){
		
		boolean result = false;
		setCreateAccXMLPath(intlXMLPath);
		String xml = readFile(createAccountXmlFile);
		xml = setNodeValueByTagName(xml, "email", emailAddress);
		System.out.println("Create Account URL : " + createAccountURL);
		System.out.println(xml);
		
		response = caliber().body(xml).contentType("application/xml").post(createAccountURL);
		
		if(response.getStatusCode() == 200 ){
			customerId = getNodeValue(response, "/response/customerID");
			System.out.println("Customer Id: " + customerId);
			result = true;
		}
		else{
			logError("unable to create customer account. Response code :"+response.getStatusCode());
		}
		
		return result;
	}*/
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
	
	public void setAddress(String xml){
		boolean result = false;
		String url = "";
		try {
			
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
	public void setAddressBook(String xmlFileNameForAddress){
		boolean result = false;
		String xml;
		String url = "";
		try {
			
			xml = readFile(getCurrentProject().getLocation() + "/testData/" + xmlFileNameForAddress);
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
			System.out.println("Update Credit Card URL : " + url);
			
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
	public boolean updateCreditCard_ExpiredCC(){
		
		boolean result = false;
		String xml;
		String url = "";
		
		try {
			url = updateCreditCardUrl + "customerid=" + customerId;
			System.out.println("Update Credit Card URL : " + url);
			
			xml = readFile(updateCreditCardXmlFile_ExpiredCC);
			
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
	public boolean updateCreditCard(String payType,String cardNumber)
	{
		
		boolean result = false;
		String xml;
		String url = "";
		
		try {
			url = updateCreditCardUrl + "customerid=" + customerId;
			System.out.println("Update Credit Card URL : " + url);
			
			xml = readFile(updateCreditCardXmlFile);
			
			xml = setNodeValueByTagName(xml, "billingAddressID", addressId);
			xml = setNodeValueByTagName(xml, "code", payType);
			xml = setNodeValueByTagName(xml, "numberPlain", cardNumber);
			
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
    public boolean setDefaultAdressAndShipping_Foreign(String custID,String addId){
		
		boolean result = false;
		String url = "";
		
		Map<String,String> parameters = new HashMap<String,String>();
		
		parameters.put("customerid", custID);
		parameters.put("shippingaddressid", addId);	
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
	
	public boolean setDefaultCreditCard(String customerID, String creditCardNum){
		boolean result = false;
		String url = "";		
		Map<String,String> parameters = new HashMap<String,String>();
		
		parameters.put("customerid", customerID);
		parameters.put("cardid", creditCardNum);		

		IResponse response = doGetRequest(defaultCreditCardUrl, parameters);
		if(response.getStatusCode() == 200 ){						
			result = true;
			System.out.println("Default Credit card successfully implemented");
		}
		
		return result;
	}
	
	public void addGCtoWallet(String gcNumber,String pin){
		String url = addGCtoWalletUrl + getCustomerId();
		String newGCxml;
		newGCxml = setNodeValueByTagName(xmlGiftCard, "plainAccountNum", gcNumber);
		newGCxml =setNodeValueByTagName(newGCxml, "plainPin",pin);
		System.out.println(url);
		System.out.println(newGCxml);
		IResponse response = caliberNewInstance().body(newGCxml).contentType("application/xml").post(url);
		//logResponse(response);
		System.out.println(response.getBody());
	}
	
	public void displayProperties()
	{
		System.out.println("************************************");
		System.out.println("Email Address : " + emailAddress);
		System.out.println("Cusromer Id : " + customerId);
		System.out.println("Credit Id : " + creditCardId);
		System.out.println("Address Id : " + addressId);
		System.out.println("************************************");
		
		RationalTestScript.logInfo("Email Address : " + emailAddress);
		RationalTestScript.logInfo("Cusromer Id : " + customerId);
		RationalTestScript.logInfo("Credit Id : " + creditCardId);
		RationalTestScript.logInfo("Address Id : " + addressId);
	}

}

