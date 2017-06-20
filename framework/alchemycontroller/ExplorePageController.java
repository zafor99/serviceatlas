package framework.alchemycontroller;

import org.openqa.selenium.internal.seleniumemulation.GetExpression;

import com.bn.qa.xobject.script.XObjectTestScript;
import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.alchemymodel.Device;
import framework.alchemymodel.ExplorePageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class ExplorePageController extends DeviceControllerBase
{
	private ExplorePageModel appModel = null;
	public ExplorePageController(Client client){
		super(client);
		appModel = new ExplorePageModel(client);
	}
	
	public void clickSignInButton(){
		
		appModel.signInButton().waitForElement(60);
		appModel.signInButton().click();
		
		AtlasScriptbase.getExecutingScript().delayFor(5);
		
	}
	
}
