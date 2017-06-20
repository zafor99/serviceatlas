package framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapcontroller.SAPAssignSourceofSupplyDialogController;

import utils.BNTimer;
import utils.EnvironmentUtility;
import utils.GiftCardUtil;
import utils.XMLUtility;

/**
 * Description : Super class for script helper
 * 
 * @author fahmed1
 * @since October 07, 2013
 */
public class InstantPurchase extends CaliberService {
	
	private static Logger logger = Logger.getLogger(InstantPurchase.class);
	private CustomerUserAccount custUserAcc = new CustomerUserAccount();
	String xml_AddressBookUS_Path = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AddressBook_US.xml";
	String clientLogin_Path = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\clientLogin.json";
	private XMLUtility xmlUtil = new XMLUtility();
	// CustomerUserAccount newCustomer = new CustomerUserAccount();
	private String serialNo = null;
	private String orderNumber = "";
	private String orderTimeStamp = "";
	private static IResponse response = null; // 02/21/2014
	private String customerID = null;

	public InstantPurchase() {

	}

	public String getOrderNumber() {
		logger.info("getOrderNumber()");
		return orderNumber;
	}

	public String getOrderTimeStamp() {
		logger.info("getOrderTimeStamp()");
		return orderTimeStamp;
	}

	public IResponse getResponse() {
		return response;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void pushEANtoSAP(String ean){
		logger.info("pushEANtoSAP("+ean+")");
		String url = null;
		url =  "http://injsvcjapp0:8080/article/invalidate?ean=" + ean + "&country=GB&retailer=NOK";
		response = caliber().post(url);
		System.out.println(response.getStatusCode());
	}
	/*
	 * This method post Instant Purchase using payType, cardNumber and EAN
	 *  This method generates a user account using the payment method
	 *  @param payType Payment Type
	 *  @param cardNumber Credit Card number
	 *  @param ean EAN to purchase 
	 */
	public void submitIPOrder(String payType, String cardNumber, String ean) {
		logger.info("submitIPOrder("+payType+"),("+cardNumber+"),("+ean+")");
		String xml_CreateAccount = "";
		String xml_InstantPurchase = "";
		xmlUtil.readXMLFile(xml_AddressBookUS_Path);
		xml_CreateAccount = xmlUtil.getXMLString();
		String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
		custUserAcc.generateUserAccountWithPayment(payType, cardNumber,xml_CreateAccount);
		customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + 11 + "&idType=customerId&id="	+ customerID;
		xml_InstantPurchase = getIPXML(ean, "1");
		// IResponse response = null; 02/21/2014
		System.out.println(xml_InstantPurchase);
		response = caliber().body(xml_InstantPurchase).content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());
			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");
			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
		
	}
	public void submitIPOrderFromSamsung(String payType, String cardNumber, String ean,String sourceID,String deviceSerialNumber) {
		logger.info("submitIPOrderFromSamsung("+payType+"),("+cardNumber+"),("+ean+"),("+sourceID+"),("+deviceSerialNumber+")");
		String xml_CreateAccount = "";
		String xml_InstantPurchase = "";
		xmlUtil.readXMLFile(xml_AddressBookUS_Path);
		xml_CreateAccount = xmlUtil.getXMLString();
		String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
		custUserAcc.generateUserAccountWithPayment(payType, cardNumber,xml_CreateAccount);
		customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + 11 + "&idType=customerId&id="	+ customerID+"&retailerID=BN2&country=US";
		xml_InstantPurchase = getIPXMLForSamsungDevice(ean, sourceID, deviceSerialNumber);
		// IResponse response = null; 02/21/2014
		System.out.println(xml_InstantPurchase);
		response = caliber().body(xml_InstantPurchase).content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());
			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");
			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
		
	}
	/*
	 * This method post Instant Purchase using payType, cardNumber and EAN
	 *  This method generates a user account using the payment method
	 *  @param payType Payment Type
	 *  @param cardNumber Credit Card number
	 *  @param ean EAN to purchase 
	 */
	public void submitIPOrder(String emailContains,String payType, String cardNumber, String ean) {
		logger.info("submitIPOrder("+payType+"),("+cardNumber+"),("+ean+")");
		String xml_CreateAccount = "";
		String xml_InstantPurchase = "";
		xmlUtil.readXMLFile(xml_AddressBookUS_Path);
		xml_CreateAccount = xmlUtil.getXMLString();
		String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
		custUserAcc.generateUserAccountWithPayment(emailContains,payType, cardNumber,xml_CreateAccount);
		customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + 11 + "&idType=customerId&id="	+ customerID;
		xml_InstantPurchase = getIPXML(ean, "1");
		// IResponse response = null; 02/21/2014
		System.out.println(xml_InstantPurchase);
		response = caliber().body(xml_InstantPurchase).content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());
			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");
			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
		
	}
	public void verifyAcertifyServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyAcertifyServiceStatusInInstantPurchase("+vpName+")");
		
		verifyResponse(vpName,response,
				"//response/checkoutDocument/order/serviceCallInfo/acertifyStatus"
		);
	}
	public void verifyVertexServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyVertexServiceStatusInInstantPurchase("+vpName+")");
		
		verifyResponse(vpName,response,
				"//response/checkoutDocument/order/serviceCallInfo/vertexStatus");
	}

	public void verifyEDSServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyEDSServiceStatusInInstantPurchase("+vpName+")");
		
		verifyResponse(vpName,response,
				 "//response/checkoutDocument/order/serviceCallInfo/edsInsertStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/edsStatus"

		);
	}
	public void verifyCCAuthorizationServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyEDSServiceStatusInInstantPurchase("+vpName+")");
		
		verifyResponse(vpName,response,
				 "//response/checkoutDocument/order/serviceCallInfo/authStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/authType"

		);
	}

	public void verifyInstantPurchase(String vpName){
		logger.info("verifyInstantPurchase("+vpName+")");
		
		verifyResponse(vpName,response,
				"//response/checkoutDocument/order/serviceCallInfo/acertifyStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/authStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/authType",
				 "//response/checkoutDocument/order/serviceCallInfo/edsInsertStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/edsStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/vertexStatus"
		);

	}
	/*
	 * This method post Instant Purchase using payType, cardNumber and EAN
	 *  This method generates a user account using the payment method
	 *  @param payType Payment Type
	 *  @param cardNumber Credit Card number
	 *  @param ean EAN to purchase
	 *  @param isTokenize  Tokenization true or false
	 */

	public void submitIPOrder(String payType, String cardNumber, String ean,boolean isTokenize) {
		logger.info("submitIPOrder("+payType+"),("+cardNumber+"),("+ean+"),("+isTokenize+")");
		if(isTokenize){
			String xml_CreateAccount = "";
			String xml_InstantPurchase = "";
			xmlUtil.readXMLFile(xml_AddressBookUS_Path);
			xml_CreateAccount = xmlUtil.getXMLString();
			String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
			custUserAcc.generateUserAccountWithPayment(payType, cardNumber,xml_CreateAccount, isTokenize);
			customerID = custUserAcc.getCustomerId();
			url = url + "cartClientId=" + 11 + "&idType=customerId&id="+ customerID;
			xml_InstantPurchase = getIPXML(ean, "1");
			// IResponse response = null; 02/21/2014
			System.out.println(xml_InstantPurchase);
			response = caliber().body(xml_InstantPurchase).content("application/xml").post(url);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			String status = String.valueOf(response.getStatusCode());
			if (status.contentEquals("200")) {
				RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
				XMLUtility xmlUtil = new XMLUtility();
				xmlUtil.readFromString(response.getBody());
				orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
				orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");
				System.out.println("Order Num: " + orderNumber);
				System.out.println("Order Date Time: " + orderTimeStamp);
				RationalTestScript.logInfo("Order Num: " + orderNumber	+ " Order Date & Time : " + orderTimeStamp);
			}
			else{
				RationalTestScript.logError("Service is broken with Status :"+ status);
			}
		}else{
			submitIPOrder(payType, cardNumber, ean);
		}
	}
	/*
	 * This method post Instant Purchase Rental using payType, cardNumber and EAN
	 *  This method generates a user account using the payment method
	 *  @param payType Payment Type
	 *  @param cardNumber Credit Card number
	 *  @param ean EAN to purchase
	 *  @param rentalDays Total Number of Rental Days
	 *  @param isTokenize  Tokenization true or false
	 */
	public void submitIPRentalOrder(String payType, String cardNumber, String ean,String rentalDays,boolean isTokenize) {
		logger.info("submitIPRentalOrder("+payType+"),("+cardNumber+"),("+ean+"),("+rentalDays+"),("+isTokenize+")");
		String xml_CreateAccount = "";
		String xml_InstantPurchase = "";
		xmlUtil.readXMLFile(xml_AddressBookUS_Path);
		xml_CreateAccount = xmlUtil.getXMLString();
		String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
		custUserAcc.generateUserAccountWithPayment(payType, cardNumber,xml_CreateAccount, isTokenize);
		customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + 11 + "&idType=customerId&id="+ customerID;
		xml_InstantPurchase = getIPRentalXML(ean, "1", rentalDays);
		System.out.println(xml_InstantPurchase);
		response = caliber().body(xml_InstantPurchase).content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");

			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber	+ " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
	}

	/*
	 * This method post Instant Purchase  using 2 Gift Cards and EAN
	 *  This method generates a user account using the payment method
	 *  @param ean EAN to purchase
	 *  @param gcAmount1 Gift Card 1 Amount
	 *  @param gcAmount2 Gift Card 2 Amount
	 */
	public void purchaseDigitalItemWith2GC(String ean, String gcAmount1,String gcAmount2) {
		logger.info("purchaseDigitalItemWith2GC("+ean+"),("+gcAmount1+"),("+gcAmount2+")");
		String xml_InstantPurchase,customerId,emailAddress= "";
		xml_InstantPurchase = "";
		customerId = custUserAcc.getNewCustomerId_DefaultAddressAndShippingAndCC("nookVideo");
		emailAddress = custUserAcc.getEmailAddress();
		GiftCardUtil giftCard = new GiftCardUtil();
		giftCard.generateGiftCard(gcAmount1);
		System.out.println("Gift Card1 : " + giftCard.getGiftCardNo()+ " and Pin :" + giftCard.getGiftCardPin());
		if (giftCard.getGiftCardNo() == null) {
			RationalTestScript.logError("unable to generate Gift Card");
		} else {
			custUserAcc.addGCtoWallet(giftCard.getGiftCardNo(),giftCard.getGiftCardPin());
		}
		giftCard.generateGiftCard(gcAmount2);
		System.out.println("Gift Card2 :" + giftCard.getGiftCardNo() + " and Pin :" + giftCard.getGiftCardPin());
		custUserAcc.addGCtoWallet(giftCard.getGiftCardNo(),	giftCard.getGiftCardPin());
		String url = EnvironmentUtility.caliber().serverName()	+ "/CheckoutService/cartItem/instantPurchase?";
		customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + 11 + "&idType=customerId&id=" + customerID;
		xml_InstantPurchase = getIPXML(ean, "1");
//		IResponse response = null;
		System.out.println(url);
		System.out.println(xml_InstantPurchase);
		response = caliber().body(xml_InstantPurchase)
		.content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());
			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");
			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber	+ " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
	}
	

	public void purchaseDigitalItemWith3GC(String ean, String gcAmount1,String gcAmount2, String gcAmount3) {
		logger.info("purchaseDigitalItemWith3GC("+ean+"),("+gcAmount1+"),("+gcAmount2+"),("+gcAmount3+")");
		String id,xml_InstantPurchase,customerId,emailAddress= "";
		xml_InstantPurchase = "";
		customerId = custUserAcc.getNewCustomerId_DefaultAddressAndShippingAndCC("nookVideo");
		emailAddress = custUserAcc.getEmailAddress();
		GiftCardUtil giftCard = new GiftCardUtil();
		// Adding 1st Gift Card
		giftCard.generateGiftCard(gcAmount1);
		System.out.println("Gift Card1 : " + giftCard.getGiftCardNo()+ " and Pin :" + giftCard.getGiftCardPin());
		custUserAcc.addGCtoWallet(giftCard.getGiftCardNo(),	giftCard.getGiftCardPin());
		// Adding 2nd gift Card
		giftCard.generateGiftCard(gcAmount2);
		System.out.println("Gift Card2 :" + giftCard.getGiftCardNo()+ " and Pin :" + giftCard.getGiftCardPin());
		custUserAcc.addGCtoWallet(giftCard.getGiftCardNo(),
				giftCard.getGiftCardPin());
		// Adding 3rd Gift Card
		giftCard.generateGiftCard(gcAmount3);
		System.out.println("Gift Card3 :" + giftCard.getGiftCardNo()+ " and Pin :" + giftCard.getGiftCardPin());
		custUserAcc.addGCtoWallet(giftCard.getGiftCardNo(),
				giftCard.getGiftCardPin());

		String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
		id = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + 11 + "&idType=customerId&id=" + id;
		xml_InstantPurchase = getIPXML(ean, "1");
		IResponse response = null;
		System.out.println(url);
		System.out.println(xml_InstantPurchase);
		response = caliber().body(xml_InstantPurchase)
		.content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript
			.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil
			.getNodeValueByXPath("//feMessage/timeStamp");

			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber
					+ " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
	}

	public String getIPXML(String ean, String quantity) {
		logger.info("getIPXML("+ean+"),("+quantity+")");
		String xml = "<instantPurchaseRequest><cartItem><ean>"
			+ ean
			+ "</ean><quantity>"
			+ quantity
			+ "</quantity></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress></session></instantPurchaseRequest>";
		return xml;
	}

	public String getIPXMLForSamsungDevice(String ean,String sourceID,String deviceSerialNumber) {
		logger.info("getIPXMLForSamsungDevice("+ean+"),("+sourceID+"),("+deviceSerialNumber+")");
		String xml = "<instantPurchaseRequest>"+
					"<nookProfile><id>10205</id><profileEntitlement><profileId>10205</profileId></profileEntitlement><profileType>PRIMARY</profileType><name>Jhonny Test</name></nookProfile>"+
			"<cartItem><ean>"
			+ ean
			+ "</ean><quantity>1</quantity></cartItem>"+
			"<session><clientIPAddress>192.168.101.8</clientIPAddress> <userAgent>BN ClientAPI Java/1.0.0.0 (SC;androidtablethd;3.4;P001000036)</userAgent><sourceID>"
			+sourceID+"</sourceID><deviceSerialNumber>"
			+deviceSerialNumber
			+"</deviceSerialNumber></session></instantPurchaseRequest>";
		return xml;
	}

	public String getIPRentalXML(String ean, String quantity,String rentalDays) {
		logger.info("getIPRentalXML("+ean+"),("+quantity+"),("+rentalDays+")");
		String xml = "<instantPurchaseRequest><cartItem><ean>"
			+ ean
			+ "</ean><quantity>"
			+ quantity
			+ "</quantity><rentalDays>"
			+rentalDays
			+"</rentalDays></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress></session></instantPurchaseRequest>";
		return xml;
	}
	public String getVideoIPXML(String ean, String quantity, String serialNumber) {
		logger.info("getVideoIPXML("+ean+"),("+quantity+"),("+serialNumber+")");
		String xml = "<instantPurchaseRequest><cartItem><ean>"
			+ ean
			+ "</ean><quantity>"
			+ quantity
			+ "</quantity></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress><deviceSerialNumber>"
			+ serialNumber
			+ "</deviceSerialNumber></session></instantPurchaseRequest>";
		return xml;
	}
	public String getVideoRentalIPXML(String ean, String quantity, String serialNumber,String rentalDays) {
		logger.info("getVideoIPXML("+ean+"),("+quantity+"),("+serialNumber+")");
		String xml = "<instantPurchaseRequest><cartItem><ean>"
			+ ean
			+ "</ean><quantity>"
			+ quantity
			+ "</quantity><rentalDays>"
			+rentalDays+"</rentalDays></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress><deviceSerialNumber>"
			+ serialNumber
			+ "</deviceSerialNumber></session></instantPurchaseRequest>";
		return xml;
	}

	/*
	 * Post client login json request creates a new client id update json file
	 * with new customer id and unique serial no url :
	 * http://csqa2.barnesandnoble.com/bncloud/api/internal/customer/clientLogin
	 */
	public void clientLogin() throws IOException {
		logger.info("clientLogin()");
		long randomNum = custUserAcc.getRandomNumber();
		String serialNo = "External-XBOX-AUTO" + randomNum;
		String convertedJson = null;
		setSerialNo(serialNo);
		String customerId = custUserAcc.getNewCustomerId_DefaultAddressAndShippingAndCC("nookVideo");
		String emailAddress = custUserAcc.getEmailAddress();
		String url = EnvironmentUtility.catalog().serverName()	+ "/bncloud/api/internal/customer/clientLogin";
		String json = Util.readFile(clientLogin_Path);
		String xml = xmlUtil.convertJSONtoXML(json);
		xmlUtil.readFromString(xml);
		xmlUtil.setNodeValueByXPath("//email", emailAddress);
		xmlUtil.setNodeValueByXPath("//serialNumber", serialNo);
		convertedJson = xmlUtil.convertXMLToJson(xmlUtil.getXMLString());
		IResponse response = null;
		System.out.println(url);
		response = caliber().body(convertedJson).contentType("application/json").header("Accept", "application/json").post(url);
		System.out.println(convertedJson);
		String status = String.valueOf(response.getStatusCode());
		System.out.println(status);

	}

	public void setSerialNo(String newSerialNo) {
		logger.info("setSerialNo("+newSerialNo+")");
		serialNo = newSerialNo;
	}

	public String getSerialNo() {
		logger.info("getSerialNo()");
		return serialNo;
	}
	public String getRandomSerialNo(){
		logger.info("getSerialNo()");
		long randomNum = custUserAcc.getRandomNumber();
		serialNo = "External-XBOX-AUTO" + randomNum;
		return serialNo;
	}

	public void authenticatingClient() {
		logger.info("authenticatingClient()");
		String url;
		String customerID = custUserAcc.getCustomerId();
		url = EnvironmentUtility.catalog().serverName()+ "/bncloud/api/internal/partner/user/" + customerID + "/device/" + getSerialNo() + "?partnerId=DELUXE&authType=0";
		IResponse response = null;
		response = caliber().body("").contentType("application/json").header("Accept", "application/json").put(url);
		String status = String.valueOf(response.getStatusCode());
		System.out.println(status);
	}

	public void submitVideoIPOrder(String ean, String cartClientID,String customerID) {
		logger.info("submitVideoIPOrder("+ean+"),("+cartClientID+"),("+customerID+")");
		String id;
		String xml_VideoIP = "";
		String url = EnvironmentUtility.caliber().serverName()	+ "/CheckoutService/cartItem/instantPurchase?";
		this.customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + cartClientID + "&idType=customerId&id="+ this.customerID + "&country=US&retailerID=BN2";
		xml_VideoIP = getVideoIPXML(ean, "1", getSerialNo());
		System.out.println(xml_VideoIP);
		//IResponse response = null;
		response = caliber().body(xml_VideoIP).contentType("application/xml").post(url);
		System.out.println(response.getStatusCode());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript
			.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil
			.getNodeValueByXPath("//feMessage/timeStamp");

			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber
					+ " Order Date & Time : " + orderTimeStamp);
		} else {
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}

	}
	public void submitVideoRentalIPOrder(String ean, String cartClientID) {
		logger.info("submitVideoRentalIPOrder("+ean+"),("+cartClientID+")");
		
		String xml_VideoIPRental = "";
		String url = EnvironmentUtility.caliber().serverName()	+ "/CheckoutService/cartItem/instantPurchase?";
		customerID = custUserAcc.getCustomerId();
		url = url + "cartClientId=" + cartClientID + "&idType=customerId&id="+ customerID + "&country=US&retailerID=BN2";
		xml_VideoIPRental = getVideoRentalIPXML(ean, "1", getSerialNo(),"1");
		
		System.out.println(xml_VideoIPRental);
		//IResponse response = null;
		response = caliber().body(xml_VideoIPRental).contentType("application/xml").post(url);
		System.out.println(response.getStatusCode());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript
			.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil
			.getNodeValueByXPath("//feMessage/timeStamp");

			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber
					+ " Order Date & Time : " + orderTimeStamp);
		} else {
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}

	}
	public void submitVideoRentalIPOrder(String ean) throws IOException{
		logger.info("submitIPVideoPurchase("+ean+")");
		clientLogin();
		authenticatingClient();
		String customerID = custUserAcc.getCustomerId();
		submitVideoRentalIPOrder(ean, "41");
	}
	public void submitIPVideoPurchase(String ean) throws IOException {
		logger.info("submitIPVideoPurchase("+ean+")");
		clientLogin();
		authenticatingClient();
		String customerID = custUserAcc.getCustomerId();
		submitVideoIPOrder(ean, "41", customerID);
	}
	public void submitUKIPOrder(String payType, String cardNumber, String ean) {
		String xml_InstantPurchase = "";
		custUserAcc.createUKAccountWithAddPayment(payType, cardNumber);
		customerID = custUserAcc.getCustomerId();
		xml_InstantPurchase = getIPXML(ean, "1");
		System.out.println(xml_InstantPurchase);
		String url = EnvironmentUtility.caliber().serverName()+ "/CheckoutService/cartItem/instantPurchase?";
		url = url + "cartClientId=" + 11 + "&idType=customerId&id="	+ customerID+"&country=GB&retailerID=NOK";
		response = caliber().body(xml_InstantPurchase).content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		String status = String.valueOf(response.getStatusCode());
		if (status.contentEquals("200")) {
			RationalTestScript.logInfo("Instant Purchase Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());
			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");
			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
	}
	public String geteGiftsJsonFileWithATGOrderNumber(String ean,String date,String customerID){
		String eGiftsJsonFile= null;
		eGiftsJsonFile="{\"meta\":{ \"atgOrderNumber\": \"12345\"  }, "+
						"\"product\": {\"ean\": \""+ean+"\", \"price\": {\"amount\": 9.99 ,\"tax\": 2.43,\"currency\": \"USD\"}},"+
						"\"gift\":{\"senderName\": \"John Q. Gifter\", \"eGiftingRecipientName\": \"Caliber tester\",\"recipientEmail\": \"qaautomation@book.com\", \"eGiftingMessage\": \"hello\",\"deliveryDate\": \""+date+"T09:00:00.000+0000\"},"+
						"\"account\": {\"id\": \""+customerID+"\",\"firstName\": \"Darren\",\"lastName\": \"Tag\",\"billingAddress\":{\"street1\": \"60 Sip Avenue\",\"city\": \"Jersey City\",\"state\": \"NJ\",\"postalCode\": \"07306\",\"country\": \"US\" }}}";
		
		return eGiftsJsonFile;
	}
	public void submitEgiftOrder(String emailContains,String payType, String cardNumber,String ean){
		BNTimer time = new BNTimer();
		String date = time.getCurrentDate("yyyy-MM-dd");
		System.out.println(date);
		custUserAcc.generateUserAccountWithPayment(emailContains,payType, cardNumber,null);	
		customerID = custUserAcc.getCustomerId();

		String json = geteGiftsJsonFileWithATGOrderNumber(ean, date, customerID);
		System.out.println(json);
		String url = EnvironmentUtility.caliber().serverName()+ "/v2/CheckoutService/orders/egifts";
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		response = caliber().contentType("application/json").headers(headers).body(json).post(url);
		String status = String.valueOf(response.getStatusCode());
	//	logResponse(response);
		if (status.contentEquals("200")) {
			orderNumber = xmlUtil.getNodeValue(response, "//id");			
		}
		else{
			RationalTestScript.logError("Service is broken with Status :"+ status);
		}
	}
	
}
