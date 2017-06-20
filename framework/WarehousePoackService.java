package framework;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 12, 2013
 */
public  class WarehousePoackService extends BaseService
{
	private String fileName = "AdvanceShipNotice.xml";
	private String path = null;
	private String clientId = "cstCIwT9TAyMHw9uuvy3Wg";

	public WarehousePoackService(){
		path = "/Logistics/services/rs/WarehousePOAck?client_id="+ clientId;
		loadXmlFile(fileName);
	}
	public void updateClientId(String newClientId){
		clientId = newClientId;
	}
	public void postExternal(){
		post(envUtil.eaiExternal().serverName()+ path);
		
	}
}
