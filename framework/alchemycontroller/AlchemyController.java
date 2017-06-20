package framework.alchemycontroller;

import org.openqa.selenium.internal.seleniumemulation.GetExpression;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.alchemymodel.Device;
import framework.alchemymodel.SettingsPageModel;


/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class AlchemyController 
{
	private Client client = null;
	private ExplorePageController explorePage = null;
	private SignInPageController signInPage = null;
	private HomePageController homePage = null;
	private ShopPageController shopPage = null;
	private LibraryPageController libraryPage = null;
	private SettingsPageController settingsPage = null;
	private String app = null;
	
	public AlchemyController(){
		client = Device.getClient();
	
	//	client.setProjectBaseDirectory("C:\\Users\\zsadeque\\workspace\\project2");
		//client.setProjectBaseDirectory("C:\\Users\\fahmed\\workspace\\project3");
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public ExplorePageController explorePage(){
		if(explorePage==null){
			explorePage = new ExplorePageController(client);
		}
		return explorePage;
	}
	
	public HomePageController homePage(){
		if(homePage==null){
			homePage = new HomePageController(client);
		}
		return homePage;
	}
	
	public SettingsPageController settingsPage(){
		if(settingsPage==null){
			settingsPage = new SettingsPageController(client);
		}
		return settingsPage;
	}
	
	public SignInPageController singInPage(){
		if(signInPage==null){
			signInPage = new SignInPageController(client);
		}
		return signInPage;
	}
	
	public ShopPageController shopPage(){
		if(shopPage==null){
			shopPage = new ShopPageController(client);
		}
		return shopPage;
	}
	
	public LibraryPageController libraryPage(){
		if(libraryPage==null){
			libraryPage = new LibraryPageController(client);
		}
		return libraryPage;
	}
	
	public void setAndroidDevice(){
		client.setDevice("adb:Galaxy Tab 4");
		app = "bn.ereader/.MainActivity";
	}
	
	public void setiOSDevice(){
		client.setDevice("ios_app:iPhone 4S");
		app = "com.barnesandnoble.B-N-eReader";
	}
	
	public void launchApp(){
		client.launch(app, true, false);
		AtlasScriptbase.getExecutingScript().delayFor(30);
	}
	
}
