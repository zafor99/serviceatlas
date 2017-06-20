package framework;

import utils.EnvironmentUtility;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 11, 2013
 */
public class AdvanceShipmentNoticeService extends BaseService
{
	private String fileName = "AdvanceShipNotice.xml";
	private String internalPath = null;
	private String externalPath = null;
	
	public AdvanceShipmentNoticeService(){
		internalPath = "/atlas-web/Logistics/services/rs/AdvanceShipNotice";
		externalPath = "/Logistics/services/rs/AdvanceShipNotice?client_id=gxs";
		loadXmlFile(fileName);
	}
	
	public void postInternal(){
		post(envUtil.eaiInternal().serverName()+ internalPath);
		
	}
	public void postExternal(){
		post(envUtil.eaiExternal().serverName() + externalPath);
	}
}
