package framework;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 12, 2013
 */
public  class PurchaseOrderAcknowledgementService extends BaseService
{
	private String fileName = "AdvanceShipNotice.xml";
	private String internalPath = null;
	private String externalPath = null;
	
	public PurchaseOrderAcknowledgementService(){
		internalPath = "/atlas-web/Logistics/services/rs/PurchaseOrderAcknowledgement";
		externalPath = "/Logistics/services/rs/PurchaseOrderAcknowledgement?client_id=gxs";
		loadXmlFile(fileName);
	}
	public void postInternal(){
		post(envUtil.eaiInternal().serverName()+ internalPath);
		
	}
	public void postExternal(){
		post(envUtil.eaiExternal().serverName() + externalPath);
	}
}
