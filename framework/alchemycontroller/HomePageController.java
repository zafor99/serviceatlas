package framework.alchemycontroller;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.alchemymodel.HomePageModel;
import framework.alchemymodel.SettingsPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 17, 2014
 */
public class HomePageController extends DeviceControllerBase
{
	private HomePageModel homePageModel = null;
	private SettingsPageModel settingsPageModel = null;
	public HomePageController(Client client) {
		super(client);	
		homePageModel = new HomePageModel(client);
		settingsPageModel = new SettingsPageModel(client);
	}
	
	public void gotoShopPage(){
		
		homePageModel.settingsSlideBarLink().click();
		homePageModel.settingSlideBar().myShopLink().click();
		AtlasScriptbase.getExecutingScript().delayFor(5);
	}
	
	public void gotoLibraryPage(){
		
		homePageModel.settingsSlideBarLink().click();
		homePageModel.settingSlideBar().libraryLink().click();
		AtlasScriptbase.getExecutingScript().delayFor(5);
	}
	
	public void signOut(){
		homePageModel.settingsSlideBarLink().click();
		homePageModel.settingSlideBar().settingsLink().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
		AtlasScriptbase.getExecutingScript().alchemy().settingsPage().logOut();
		
	}
	
	public void buyBook(){
		homePageModel.buyBook().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
}
