package framework.alchemycontroller;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.alchemymodel.SettingsPageModel;
import framework.alchemymodel.ShopPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  October 17, 2014
 */
public class ShopPageController extends DeviceControllerBase
{
	private ShopPageModel shopPageModel = null;
	private SettingsPageModel settingsPageModel = null;
	public ShopPageController(Client client) {
		super(client);	
		shopPageModel = new ShopPageModel(client);
	}
	
	public void buyBook(){
		shopPageModel.buyBook().click();
		AtlasScriptbase.getExecutingScript().delayFor(5);
		shopPageModel.purchaseOkButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(5);
	}

}
