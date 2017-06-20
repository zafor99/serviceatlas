package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 17, 2014
 */
public class SettingsPageModel extends DeviceModelBase
{

	public SettingsPageModel(Client client) {
		super(client);
		// TODO Auto-generated constructor stub
	}
	
	
	public ElementObject logOutButton(){
		return new ElementObject("NATIVE", "xpath=//*[@text='Log out']", 0);
	}
	
	public ElementObject cancelButton(){
		return new ElementObject("NATIVE", "xpath=//*[@text='Cancel']", 0);
	}
	
	public ElementObject singOutButton(){
		return new ElementObject("NATIVE", "xpath=//*[@text='Sign Out']", 0);
	}
	
	
}
