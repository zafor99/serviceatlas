package framework.alchemycontroller;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public abstract class DeviceControllerBase extends RationalTestScript
{
	protected Client client = null;
	
	public DeviceControllerBase(Client client){
		this.client = client;
	}
	
	public Client getClient(){
		return client;
	}
	
}
