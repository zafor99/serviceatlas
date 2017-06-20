package framework;

import utils.XMLUtility;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 15, 2013
 */
public class CheckOutService extends CaliberService
{
	private String customerId = "";
	private String emailAddress = "";
	private CustomerInformationService gbPhysicalPurchase = null;
	private CustomerInformationService usPhysicalPurchase = null;
	private String xmlFileCheckout_US = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\checkout_US.xml";
	private String xmlFilePath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\";
	private String xmlFileEmail_US = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\email_US.xml";
	public CheckOutService(){
		
	}
	public CustomerInformationService GBPhysicalPurchase(){
		if(gbPhysicalPurchase==null)
		{
			gbPhysicalPurchase = new CustomerInformationService();
		}
	
		return gbPhysicalPurchase;
	}
	public CustomerInformationService USPhysicalPurchase(){
		if(usPhysicalPurchase==null)
		{
			usPhysicalPurchase = new CustomerInformationService(xmlFileCheckout_US,xmlFileEmail_US);
		}
	
		return usPhysicalPurchase;
	}
	
	public CustomerInformationService USPhysicalPurchase(String fileName){
		//String path = xmlFileEmail_US+fileName;
		if(usPhysicalPurchase==null)
		{
			usPhysicalPurchase = new CustomerInformationService(fileName,xmlFileEmail_US);
		}
	
		return usPhysicalPurchase;
	}
	
	
}
