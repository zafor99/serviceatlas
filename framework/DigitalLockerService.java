package framework;

import utils.EnvironmentUtility;
import utils.XMLUtility;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.xobject.vp.XTestDataVerificationPoint;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import org.jdom.Element;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 02, 2014
 */
public  class DigitalLockerService extends BaseService
{
	private String lockerItemURL = "https://csdev.barnesandnoble.com/bncloud/serviceRI/LockerView";
	private String limit = "1000";
	private String requestXml = "";
	private IResponse response = null;
	private String xml = "";
	private String deliveryId = "";
	private String customerId = null;
	
	public DigitalLockerService(){
		getLockerItem();
	}
	
	public DigitalLockerService(String customerId) {
		this.customerId = customerId;
		//requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request customerid=\""+ customerId + "\" offset=\"0\" limit=\"" + limit + "\" displayStatus=\"ALL\" sortType=\"TITLE_ASC\"/>";
		//getLockerItem();
	}

	public void setLimit(String limit)
	{
		this.limit = limit;
	}
	
	public void getLockerItem(){
		response = caliber().body(requestXml).contentType("application/xml").post(lockerItemURL);
		
		this.xml = response.getBody();
		System.out.println(this.xml);
	}
	
	public String getDeleveryId(String ean){
		
		String id = "";
		getLockerItem();
		System.out.println(this.xml);
		
		Element node = null;
		XMLUtility xmlUtil = new XMLUtility(this.xml);
		node = (Element) xmlUtil.getNodeBYAttribute(ean);
		if(node!=null){
			id = node.getAttributeValue("deliveryid");
		}
		
		return id;
	}
	
	public void deleteLockerItem(String... ean){
		
		String deliveryId = "";
		String url = "";
		
		for(int i=0;i<ean.length;i++){
			deliveryId = getDeleveryId(ean[i]);
			if(!deliveryId.contentEquals("")){
				IResponse response = null;
				url = "http://10.5.180.107/LockerServices/LockerItemStateService.svc/EdsUpdateDisplayStatus/" + deliveryId + "/deleted/"+this.customerId;
				System.out.println("URL : " + url);
				response = caliber().get(url);
				System.out.println("RESPONSE : " + response.getBody());
				deliveryId = "";
			}
			else
			{
				System.out.println("EAN " + ean + " not found to delete");
			}
		}
		
		getLockerItem();
	}
	
	public void deleteAllLockerItems(){
		String[] deliveryIdList = null;
		XMLUtility xmlUtil = new XMLUtility(this.xml);
		deliveryIdList = xmlUtil.getItemListWithAttribute("deliveryid");
		String url = "";
		
		for(int i=0;i<deliveryIdList.length;i++){
				IResponse response = null;
				url = "http://10.5.180.107/LockerServices/LockerItemStateService.svc/EdsUpdateDisplayStatus/" + deliveryIdList[i] + "/deleted/"+this.customerId;
				System.out.println("URL : " + url);
				response = caliber().get(url);
				System.out.println("RESPONSE : " + response.getBody());
				deliveryId = "";
		}
	}
	
	public String[] getLockerItemList(){
		

		getLockerItem();
		XMLUtility xmlUtil = new XMLUtility(this.xml);
		String[] eans = null;
		
		eans =  xmlUtil.getItemListWithAttribute("EAN");
				
		return eans;
	}
	
	public void showLockerItems(){
		getLockerItem();
		XMLUtility xmlUtil = new XMLUtility(this.xml);
		String[] eans = null;
		
		eans =  xmlUtil.getItemListWithAttribute("EAN");
		if(eans.length>0){
			for(int i=0;i<eans.length;i++){
				System.out.println(eans[i]);
			}
		}
		else
		{
			System.out.println("Locker is EMPTY");
		}
		//xmlUtil.showItemsAttribute("EAN");
	}
	
	/*
	 * Validate Locker item
	 */
	public boolean verifyLockerItem(String vpName){
		boolean result = true;
		int productCount = 0;
		String url =  EnvironmentUtility.catalog().serverName()+"/bncloud/api/internal/library/BN2/customer/"+customerId+"?limit=100";
		response =caliber().header("Accept", "application/xml").contentType("application/xml").get(url);
		this.xml = response.getBody();
		productCount = getNodeCount(response, "//libraryViewWrappers/libraryViewWrapper");
		System.out.println(xml);
		ITestData testData = null;
		int index = 1;
		String lockerItems[]= new String[productCount];;
		for(int i=0;i<productCount;i++){
			lockerItems[i] = getNodeValue(response, "//libraryViewWrappers/libraryViewWrapper["+index+"]/libraryEan");
			index++;
		}
		testData = VpUtil.getTestData(lockerItems);
		XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName,  AtlasScriptbase.getExecutingScript(),testData);
		result = vp.performTest();
		return result;
	}
	/*
	 * Validate Locker item
	 */
	public boolean verifyLockerItem(String vpName,String ean){
		boolean result = true;
		int productCount = 0;
		String cloudEAN = "";
		String url =  EnvironmentUtility.catalog().serverName()+"/bncloud/api/internal/library/BN2/customer/"+customerId+"?limit=100";
		response =caliber().header("Accept", "application/xml").contentType("application/xml").get(url);
		this.xml = response.getBody();
		productCount = getNodeCount(response, "//libraryViewWrappers/libraryViewWrapper");
		System.out.println(xml);
		ITestData testData = null;
		int index = 1;
		String lockerItems[]= new String[productCount];;
		for(int i=0;i<productCount;i++){
			lockerItems[i] = getNodeValue(response, "//libraryViewWrappers/libraryViewWrapper["+index+"]/libraryEan");
			if(lockerItems[i].contentEquals(ean)){
				cloudEAN = ean;
			}
			index++;
		}
		testData = VpUtil.getTestData(cloudEAN);
		XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName,  AtlasScriptbase.getExecutingScript(),testData);
		result = vp.performTest();
		return result;
	}

	public boolean verifyVideoLockerItem(String vpName){
		boolean result = true;
		int productCount = 0;
		String url =  EnvironmentUtility.catalog().serverName()+"/bncloud/api/internal/library/BN2/videos/customer/"+customerId+"?limit=100";
		System.out.print(url);
		response =caliber().header("Accept", "application/xml").contentType("application/xml").get(url);
		this.xml = response.getBody();
		productCount = getNodeCount(response, "//videoLibrary/video");
		System.out.println(xml);
		ITestData testData = null;
		int index = 1;
		String lockerItems[]= new String[productCount];;
		for(int i=0;i<productCount;i++){
			lockerItems[i] = getNodeValue(response, "//videoLibrary/video["+index+"]/ean");
			index++;
		}
		testData = VpUtil.getTestData(lockerItems);
		XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName,  AtlasScriptbase.getExecutingScript(),testData);
		result = vp.performTest();
		return result;
		
	}

	
}
