package framework;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.openqa.selenium.internal.seleniumemulation.GetExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import utils.MSWalletIPEnvUtil;
import utils.XMLUtility;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 19, 2014
 */
public class MSWalletService extends BaseService
{
	private static Logger logger = Logger.getLogger(MSWalletService.class);
	public final int MAX_RETRIES = 7;
	public final int RETRY_INTERVAL = 3;
	private CustomerUserAccount customerUserAccount = null;
	private MSWalletIPEnvUtil msWalletIPEnv = null;
	protected String TEST_DATA_PATH = getCurrentProject().getLocation() + "/testData/";
	protected final String INSTANTPURCHASE_MSWALLET_XML = TEST_DATA_PATH + "/jCheckoutServices/mswallet_intl/xml/";
	protected final String INSTANTPURCHASE_MSWALLET_JSON = TEST_DATA_PATH + "/jCheckoutServices/mswallet_intl/json/";
	IResponse response = null;
	private String orderNumber = "";
	private String orderTimeStamp = "";
	
	public CustomerUserAccount customerUserAccount(){
		
		if(customerUserAccount==null){
			customerUserAccount = new CustomerUserAccount();
		}

		return customerUserAccount;
		
		
	}
	
	private MSWalletIPEnvUtil msWalletIPEnv(){
		if(msWalletIPEnv==null){
			msWalletIPEnv = new MSWalletIPEnvUtil();
		}
		
		return msWalletIPEnv;
	}

	public String getXMLRequestForMSWallet(String filePath, String ean, String accessToken)
	{
		String xml = "";
		try {
			xml = Util.readFile(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logError("Can't read the file");
		}
		xml = setNodeValueByTagName(xml, "ean", ean);
		xml = setNodeValueByTagName(xml, "microsoftToken", accessToken);
		
		return xml;
		
	}
	public String getOrderNumber(){
		logInfo("getOrderNumber()");
		return orderNumber;
	}
	public String getOrderTimeStamp(){
		logInfo("getOrderTimeStamp()");
		return orderTimeStamp;
	}
	public IResponse responseWithRetries(String xml, String contentType, String url)
	{
		int retries = MAX_RETRIES;  //5
		IResponse response = null;
		
		while(retries > 0)
		{
			response = caliber().body(xml).contentType(contentType).post(url);
			if(response.getBody().contains("ERROR_SOCKET_TIMEOUT"))
			{
				retries--;
				delayFor(RETRY_INTERVAL);
				System.out.println("Retrying Instant Purchase after " + RETRY_INTERVAL + " seconds" );
				
				
			}
			else
			{
				System.out.println("Retrying works after " + ((MAX_RETRIES+1) - retries) + " attempts");
				break;
			}
			
		}
		
		return response;
	}
	
	public IResponse createInstantPurchase(String accessToken){
		logInfo("createInstantPurchase("+accessToken+")");
		String customerId = null;
		String url = null;
		String xmlFile = null;
		String xml_InstantPurchase = null;
		IResponse response = null;
		String orderNumber = null;
		String id = null;
		
		if( msWalletIPEnv().getCcountryCode().contentEquals("US")){
			customerUserAccount().createAccount();
			id = customerUserAccount().getCustomerId();
			msWalletIPEnv().setCustomerId(id);
		}
		else{
			customerId  = customerUserAccount().createInternationalAccount(msWalletIPEnv().getRetailer(), msWalletIPEnv().getCcountryCode(), msWalletIPEnv().getLocale());
			msWalletIPEnv().setCustomerId(customerId);
			msWalletIPEnv().setCustomerId(id);
		}
		
		url = msWalletIPEnv().getInstantPurchaseURL();
		logInfo("URL: " + url);
		
		// Instant Purchase using MS Wallet
		xmlFile = TEST_DATA_PATH +  "MSWallet_IntantPurchaseRequest.xml";
		xml_InstantPurchase = getXMLRequestForMSWallet(xmlFile, msWalletIPEnv().getEan(), accessToken);
		System.out.println(xml_InstantPurchase);
		//logRequestXml(xml_InstantPurchase);
		
		/**** Retrying 5 times in case if SOCKET_TIMEOUT Exception occurs ****/
		response = responseWithRetries(xml_InstantPurchase, "application/xml", url);			
		System.out.println(response.getBody());
		return response;
	}
	public IResponse getMSWalletInstantPurchaseResponse(){
		return response;
	}
	public IResponse createInstantPurchase(String accessToken,String customerID,String ean){
		logInfo("createInstantPurchase("+accessToken+"),("+customerID+"),("+ean+")");
		String url = null;
		String xmlFile = null;
		String xml_InstantPurchase = null;
		
		msWalletIPEnv().setCustomerId(customerID);
		
		url = msWalletIPEnv().getInstantPurchaseURL();
		logInfo("URL: " + url);
		
		// Instant Purchase using MS Wallet
		xmlFile = TEST_DATA_PATH +  "MSWallet_IntantPurchaseRequest.xml";
		xml_InstantPurchase = getXMLRequestForMSWallet(xmlFile, ean, accessToken);
		System.out.println(xml_InstantPurchase);
		//logRequestXml(xml_InstantPurchase);
		
		/**** Retrying 5 times in case if SOCKET_TIMEOUT Exception occurs ****/
		response = responseWithRetries(xml_InstantPurchase, "application/xml", url);			
		System.out.println(response.getBody());
		orderNumber = getNodeValue(response, "//response/checkoutDocument/order/@orderNumber");//xmlUtil.getNodeValueByXPath("//response/checkoutDocument/order/@orderNumber");
		orderTimeStamp = getNodeValue(response, "//response/checkoutDocument/order/@orderDate");//xmlUtil.getNodeValueByXPath("//response/checkoutDocument/order/@orderDate");
		return response;
	}
	/*
	 * This is for VAT Change
	 * Hard coded the url(Temp )
	 */
	public IResponse createInstantPurchase(String accessToken,String customerID,String ean,String cartClientId,String country,String retailerID){
		logInfo("createInstantPurchase("+accessToken+"),("+customerID+"),("+ean+"),("+country+"),("+retailerID+")");
		String url = null;
		String xmlFile = null;
		String xml_InstantPurchase = null;
		
		msWalletIPEnv().setCustomerId(customerID);
		
//		url = msWalletIPEnv().getInstantPurchaseURL();
		url= envUtil.caliber().serverName() + "/CheckoutService/cartItem/instantPurchase?" +  "cartClientId=" + cartClientId + "&idType=" + "customerId" + "&id=" + customerID + "&retailerID=" + retailerID + "&country=" + country;
		logInfo("URL: " + url);
		
		// Instant Purchase using MS Wallet
		xmlFile = TEST_DATA_PATH +  "MSWallet_IntantPurchaseRequest.xml";
		xml_InstantPurchase = getXMLRequestForMSWallet(xmlFile, ean, accessToken);
		System.out.println(xml_InstantPurchase);
		//logRequestXml(xml_InstantPurchase);
		
		/**** Retrying 5 times in case if SOCKET_TIMEOUT Exception occurs ****/
		response = responseWithRetries(xml_InstantPurchase, "application/xml", url);			
		System.out.println(response.getBody());
		orderNumber = getNodeValue(response, "//response/checkoutDocument/order/@orderNumber");//xmlUtil.getNodeValueByXPath("//response/checkoutDocument/order/@orderNumber");
		orderTimeStamp = getNodeValue(response, "//response/checkoutDocument/order/@orderDate");//xmlUtil.getNodeValueByXPath("//response/checkoutDocument/order/@orderDate");
		return response;
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
	public void verifyInstantPurchase(String vpName){
		logger.info("verifyInstantPurchase("+vpName+")");
		logInfo("verifyInstantPurchase("+vpName+")");
		verifyResponse(vpName,response,
				"//response/checkoutDocument/order/serviceCallInfo/acertifyStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/authStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/authType",
				 "//response/checkoutDocument/order/serviceCallInfo/edsInsertStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/edsStatus",
				 "//response/checkoutDocument/order/serviceCallInfo/vertexStatus"
		);

	}
}
