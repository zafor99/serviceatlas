package framework;

import java.net.URL;
import java.sql.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import utils.XMLUtility;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.rational.test.ft.script.RationalTestScript;


/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 15, 2013
 */
public class CustomerInformationService extends CaliberService
{
	private static Logger logger = Logger.getLogger(CustomerInformationService.class);
	private String cartClientId = "";
	private String customerId = "";
	private String retailerId = "";
	private String country = "";
	private String emailAddress = "";
	private String orderNumber = "";
	private String orderTimeStamp = "";

	private XMLUtility xmlCheckout = null;
	private XMLUtility xmlEmail = null;
	private String xmlFileCheckout = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\checkout.xml";
	private String xmlFileEmail = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\email.xml";
	public CustomerUserAccount user = new CustomerUserAccount();

	public CustomerInformationService(){
		xmlCheckout = new XMLUtility(xmlFileCheckout);
		xmlEmail = new XMLUtility(xmlFileEmail);
		setClientInformation();
		setEmailAddress();
	}

	public CustomerInformationService(String xmlFile){
		xmlEmail = new XMLUtility(xmlFileEmail);
		xmlCheckout = new XMLUtility(xmlFile);		
		setClientInformation();
		setEmailAddress();
	}
	public CustomerInformationService(String xmlFile,String xmlEmailFile){
		xmlEmail = new XMLUtility(xmlEmailFile);
		xmlCheckout = new XMLUtility(xmlFile);		
		setClientInformation();
		setEmailAddress();
	}

	public String getCustomerId(){
		return customerId;
	}
	public void setCustomerId(String customerID){
		customerId = customerID;
	}

	public String getRetailerId(){
		return retailerId;
	}

	public String getCountryCode(){
		return country;
	}

	public String getOrderNumber(){
		logInfo("getOrderNumber");
		return orderNumber;
	}

	public String getOrderTimeStamp(){
		logInfo("getOrderTimeStamp");
		return orderTimeStamp;
	}


	private void setEmailAddress(){
		emailAddress = xmlEmail.getNodeValueByXPath("//email");
	}

	private void setClientInformation(){
		logger.info("setClientInformation");
		cartClientId = xmlCheckout.getNodeValueByXPath("//cartClientId");
		customerId = xmlCheckout.getNodeValueByXPath("//id");
		retailerId = xmlCheckout.getNodeValueByXPath("//retailerID");
		country = xmlCheckout.getNodeValueByXPath("//country");
	}	

	public void setXMLFile(String xmlFile){
		logger.info("setXMLFile("+xmlFile+")");
		this.xmlFileCheckout = xmlFile;
		xmlCheckout = new XMLUtility(xmlFile);
	}

	public void modifyEmailAddress(String email){
		logger.info("modifyEmailAddress("+email+")");
		xmlEmail.setNodeValueByXPath("//email", email);
	}
	public void modifyShippingMethod(String code,String shippingMethodId){
		logger.info("modifyShippingMethod("+code+","+shippingMethodId+")");
		xmlCheckout.setNodeValueByXPath("//shippingMethod/code", code);
		xmlCheckout.setNodeValueByXPath("//shippingMethod/shippingMethodId", shippingMethodId);
	}
	public void modifyCustomerID(String customerID){
		logger.info("modifyCustomerID("+customerID+")");
		xmlCheckout.setNodeValueByXPath("//cartID/id", customerID);
	}

	public void modifyAddItem(String ean, String quantity){
		logger.info("modifyAddItem("+ean+","+quantity+")");
		if(ean!=null && !ean.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='AddItems']/cartItems/cartItem/ean", ean);
		}

		if(quantity!=null && !quantity.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='AddItems']/cartItems/cartItem/quantity", quantity);
		}

	}
	public String getNewCustomerId()
	{
		String cid = "";
		//CustomerUserAccount ua = new CustomerUserAccount();
		if(user!=null){
			this.customerId = user.getNewCustomerId("runtimeuser");
		}
		return this.customerId;

	}
	public String getNewAccountEmailAddress(){
		return this.emailAddress = user.getEmailAddress();

	}
	public String getNewAccountCustomerID(){
		CustomerUserAccount user = new CustomerUserAccount();
		return user.getCustomerId();

	}

	public void modifyBillingAddress(String firstName, String lastName, String nickName, String company,
			String addLine1, String addLine2, String addLine3,
			String city, String state, String zip, String countryNumber){
		logger.info("modifyShippingAddress("+firstName+","+lastName+","+nickName+","+company+","+addLine1+","+addLine2+","+addLine3+","+city+","+state+","+zip+","+countryNumber+")");
		if(firstName!=null && !firstName.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/firstName", firstName);
		}

		if(lastName!=null && !lastName.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/lastName", lastName);
		}

		if(nickName!=null && !nickName.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/nickName", nickName);
		}

		if(company!=null && !company.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/company", company);
		}

		if(addLine1!=null && !addLine1.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/line1", addLine1);
		}

		if(addLine2!=null && !addLine2.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/line2", addLine2);
		}

		if(addLine3!=null && !addLine3.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/line3", addLine3);
		}

		if(city!=null && !city.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/city", city);
		}

		if(state!=null && !state.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/state", state);
		}

		if(zip!=null && !zip.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/zip", zip);
		}

		if(countryNumber!=null && !countryNumber.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetBillingAddress']/address/countryNum", countryNumber);
		}
	}

	public void modifyShippingAddress(String shipmentId, String firstName, String lastName, String nickName, String company,
			String addLine1, String addLine2, String addLine3,
			String city, String state, String zip, String countryNumber){
		logger.info("modifyShippingAddress("+shipmentId+","+firstName+","+lastName+","+nickName+","+company+","+addLine1+","+addLine2+","+addLine3+","+city+","+state+","+zip+","+countryNumber+")");

		if(shipmentId!=null && !shipmentId.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/shipmentId", shipmentId);
		}

		if(firstName!=null && !firstName.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/firstName", firstName);
		}

		if(lastName!=null && !lastName.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/lastName", lastName);
		}

		if(nickName!=null && !nickName.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/nickName", nickName);
		}

		if(company!=null && !company.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/company", company);
		}

		if(addLine1!=null && !addLine1.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/line1", addLine1);
		}

		if(addLine2!=null && !addLine2.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/line2", addLine2);
		}

		if(addLine3!=null && !addLine3.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/line3", addLine3);
		}

		if(city!=null && !city.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/city", city);
		}

		if(state!=null && !state.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/state", state);
		}

		if(zip!=null && !zip.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/zip", zip);
		}

		if(countryNumber!=null && !countryNumber.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShippingAddress']/address/countryNum", countryNumber);
		}
	}

	public void modifyShippingServiceLevel(String shipmentId, String shippingMethodCode, String shippingMethodId){
		logger.info("modifyShippingServiceLevel("+shipmentId+","+shippingMethodCode+","+shippingMethodId+")");
		if(shipmentId!=null && !shipmentId.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShipServiceLevel']/shipmentId", shipmentId);
		}

		if(shippingMethodCode!=null && !shippingMethodCode.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShipServiceLevel']/shippingMethod/code", shippingMethodCode);
		}

		if(shippingMethodId!=null && !shippingMethodId.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='SetShipServiceLevel']/shippingMethod/shippingMethodId", shippingMethodId);
		}

	}

	public void modifyCreditCardInfo(String creditCode, String creditCardNumber, String expiry, String securityCode){
		logger.info("submitCustomerIfo("+creditCode+","+creditCardNumber+","+expiry+","+securityCode+")");

		if(creditCode!=null && !creditCode.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='AddCreditCard']/creditCard/code", creditCode);
		}

		if(creditCardNumber!=null && !creditCardNumber.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='AddCreditCard']/creditCard/numberPlain", creditCardNumber);
		}

		if(expiry!=null && !expiry.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='AddCreditCard']/creditCard/expiry", expiry);
		}

		if(securityCode!=null && !securityCode.contentEquals("!")){
			xmlCheckout.setNodeValueByXPath("//task[@type='AddCreditCard']/creditCard/securityCode", securityCode);
		}

	}



	public void submitCustomerIfo(){
		logger.info("submitCustomerIfo");
		String url = envUtil.caliber().serverName() + "/CheckoutService/aggregation";
		String xml = xmlCheckout.getXMLString();
		System.out.println(xml);
		IResponse response = null;
		response = caliber().content("application/xml").body(xml).post(url);
		System.out.println(response.getStatusCode());
	}

	public void submitEmailAddress(){
		logger.info("submitEmailAddress");
		String url = envUtil.caliber().serverName() + "/CheckoutService/setEmail?cartClientId=" + cartClientId + "&idType=customerId&id=" + customerId + "&country=" + country + "&retailerID=" + retailerId;
		String xml = xmlEmail.getXMLString();
		System.out.println(xml);
		IResponse response = null;
		response = caliber().content("application/xml").body(xml).post(url);
		System.out.println(response.getStatusCode());
	}

	public void submitOrder(){

		logger.info("submitOrder");
		submitCustomerIfo();
		submitEmailAddress();

		String url = envUtil.caliber().serverName() + "/CheckoutService/submitOrder?cartClientId=" + cartClientId + "&idType=customerId&id=" + customerId + "&country=" + country + "&retailerID=" + retailerId;
		IResponse response = null;
		response = caliber().content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		String status = String.valueOf( response.getStatusCode());
		if(status.contentEquals("200")){
			RationalTestScript.logInfo("GB PHysical Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");


			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}

	}

	public void submitOrder(boolean newAccount,String shippingMethod,String ean){

		logger.info("submitOrder("+newAccount + "),("+shippingMethod+"),("+ean+")");

		if(newAccount){
			String customerID=getNewCustomerId(); 
			String emailAddress =getNewAccountEmailAddress(); 
			modifyCustomerID(customerID);
			modifyEmailAddress(emailAddress);
			setCustomerId(customerID);
		}
		if(shippingMethod!=null){
			if(shippingMethod.contains("STD")){
				modifyShippingMethod("STD","84");
			}else if (shippingMethod.contains("EDO")){
				modifyShippingMethod("EDO","27");
			}else if(shippingMethod.contains("IRM")){
				modifyShippingMethod("IRM", "29");
			}else if(shippingMethod.contains("OPS")){
				modifyShippingMethod("OPS", "11");
			}

		}
		if(ean!=null){
			modifyAddItem(ean, null);		
		}
		submitCustomerIfo();
		submitEmailAddress();

		String url = envUtil.caliber().serverName() + "/CheckoutService/submitOrder?cartClientId=" + cartClientId + "&idType=customerId&id=" + customerId + "&country=" + country + "&retailerID=" + retailerId;
		IResponse response = null;
		response = caliber().content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		String status = String.valueOf( response.getStatusCode());
		if(status.contentEquals("200")){
			RationalTestScript.logInfo("GB PHysical Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");


			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}
	}
	public void submitOrder(boolean newAccount,String shippingMethod,String ean,String payType,String cardNumber){

		logger.info("submitOrder("+newAccount + "),("+shippingMethod+"),("+ean+")");

		if(newAccount){
			String customerID=getNewCustomerId(); 
			String emailAddress =getNewAccountEmailAddress(); 
			modifyCustomerID(customerID);
			modifyEmailAddress(emailAddress);
			modifyCreditCardInfo(payType, cardNumber, "2017-06-01T00:00:00-04:00", "123");
			setCustomerId(customerID);
		}
		if(shippingMethod!=null){
			if(shippingMethod.contains("STD")){
				modifyShippingMethod("STD","84");
			}else if (shippingMethod.contains("EDO")){
				modifyShippingMethod("EDO","27");
			}else if(shippingMethod.contains("IRM")){
				modifyShippingMethod("IRM", "29");
			}else if(shippingMethod.contains("OPS")){
				modifyShippingMethod("OPS", "11");
			}

		}
		if(ean!=null){
			modifyAddItem(ean, null);		
		}
		submitCustomerIfo();
		submitEmailAddress();

		String url = envUtil.caliber().serverName() + "/CheckoutService/submitOrder?cartClientId=" + cartClientId + "&idType=customerId&id=" + customerId + "&country=" + country + "&retailerID=" + retailerId;
		IResponse response = null;
		response = caliber().content("application/xml").post(url);
		System.out.println(response.getStatusCode());
		String status = String.valueOf( response.getStatusCode());
		if(status.contentEquals("200")){
			RationalTestScript.logInfo("GB PHysical Order submitted successfully");
			XMLUtility xmlUtil = new XMLUtility();
			xmlUtil.readFromString(response.getBody());

			orderNumber = xmlUtil.getNodeValueByXPath("//feMessage/msgID");
			orderTimeStamp = xmlUtil.getNodeValueByXPath("//feMessage/timeStamp");


			System.out.println("Order Num: " + orderNumber);
			System.out.println("Order Date Time: " + orderTimeStamp);
			RationalTestScript.logInfo("Order Num: " + orderNumber + " Order Date & Time : " + orderTimeStamp);
		}
	}
}
