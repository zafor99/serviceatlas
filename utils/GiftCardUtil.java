package utils;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.rational.test.ft.script.RationalTestScript;

import framework.CaliberService;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 29, 2013
 */
public class GiftCardUtil extends CaliberService
{
	private String giftCardNo = null;
	private String giftCardPin = null;
	String url = "http://inyugc01:8301/service_request.asp";
	XMLUtility xmlUtil = new XMLUtility();
	public GiftCardUtil(){
		
	}
	
	public void setGiftCardNo(String newGiftCardNo){
		giftCardNo = newGiftCardNo;
	}
	public String getGiftCardNo(){
		return giftCardNo;
	}
	public void setGiftCardPin(String newGiftCardPin){
		giftCardPin = newGiftCardPin;
	}
	public String getGiftCardPin(){
		return giftCardPin;
	}
	public String getGiftCardXML(String value)
	{
		String xml ="<bnServiceRequest><UGCmessage type=\"0300\" procCode=\"920000\" amt=\"" + value + "\" logLevel=\"1\">" +
        "<cardAcceptorTerminalID>1224</cardAcceptorTerminalID></UGCmessage></bnServiceRequest>";
		System.out.println(xml);
		return xml;
	}

	public void generateGiftCard(String value){
		IResponse response = caliber().body(getGiftCardXML(value)).contentType("application/xml").post(url);
		xmlUtil.readFromString(response.getBody());
		String xml = xmlUtil.getXMLString();
		String giftCard =xmlUtil.getNodeValue(response, "//UGCmessage/@account");

		setGiftCardNo(giftCard);
		String giftCardPin =xmlUtil.getNodeValue(response, "//UGCmessage/@PIN"); 
		giftCardPin = giftCardPin.substring(4);
		setGiftCardPin(giftCardPin);
		
	}
	
}
