package framework.alchemycontroller;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.alchemymodel.Device;
import framework.alchemymodel.SignInPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class SignInPageController extends DeviceControllerBase
{
	private SignInPageModel appModel = null;
	
	public SignInPageController(Client client) {
		super(client);
		appModel = new SignInPageModel(client);
	}


	public void signIn(String userName, String password){
		if(Device.isIOS()){
			appModel.emailAddressTextBox().sendText(userName);
			appModel.passwordTextBox().sendText(password);
			//client.click("NATIVE", "class=UIKBDimmingView", 0, 1);
			client.click("NATIVE", "xpath=(//*/*[@class='UIKBKeyView'])[4]", 0, 1);
			client.waitForElement("NATIVE", "class=UIKBDimmingView", 0, 10000);
			client.click("NATIVE", "class=UIKBDimmingView", 0, 1);		
			AtlasScriptbase.getExecutingScript().delayFor(2);
			appModel.signinButton().click();
			AtlasScriptbase.getExecutingScript().delayFor(5);
			
			client.waitForElement("NATIVE", "class=UIImageView", 0, 30000);
	        client.click("NATIVE", "class=UIImageView", 0, 1);
	        client.click("NATIVE", "text=Settings", 0, 1);
	        client.elementListSelect("", "accessibilityLabel=Logout", 0, false);
	        client.click("NATIVE", "accessibilityLabel=Logout", 0, 1);
	        client.click("NATIVE", "text=OK", 0, 1);
		}
		else if(Device.isAndroid()){
			appModel.emailAddressTextBox().sendText(userName);
			appModel.nextButton().click();
			appModel.passwordTextBox().waitForElement(5);
			
			appModel.passwordTextBox().sendText(password);
			appModel.signinButton().click();
			AtlasScriptbase.getExecutingScript().delayFor(5);
			client.click("NATIVE", "xpath=//*[@text='Allow']", 0, 1);
			
		}
	}
}
