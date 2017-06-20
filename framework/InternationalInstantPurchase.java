package framework;

import org.apache.log4j.Logger;

import utils.EnvironmentUtility;
import utils.XMLUtility;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 23, 2014
 */
public  class InternationalInstantPurchase extends CaliberService
{
	private static Logger logger = Logger.getLogger(InternationalInstantPurchase.class);
	private CustomerUserAccount custUserAcc = new CustomerUserAccount();
	private static IResponse response = null; 
	private String customerID = null;
	private String orderNumber = "";
	private String orderTimeStamp = "";
	public InternationalInstantPurchase(){
		
	}
	public String getIPXML(String ean, String quantity) {
		logger.info("getIPXML("+ean+"),("+quantity+"),("+ean+")");
		String xml = "<instantPurchaseRequest><cartItem><ean>"
			+ ean
			+ "</ean><quantity>"
			+ quantity
			+ "</quantity></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress></session></instantPurchaseRequest>";
		return xml;
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
}
