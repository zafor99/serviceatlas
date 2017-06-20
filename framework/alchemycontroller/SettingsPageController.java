package framework.alchemycontroller;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.alchemymodel.SettingsPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 17, 2014
 */
public class SettingsPageController extends DeviceControllerBase
{
	private SettingsPageModel appModel = null;
	public SettingsPageController(Client client) {
		super(client);		
		appModel = new SettingsPageModel(client);
	}
	
	public void logOut(){
		appModel.logOutButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
		appModel.singOutButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(5);
	}
	
}
