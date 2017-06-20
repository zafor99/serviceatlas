package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utils.EnvironmentUtility;

import com.rational.test.ft.script.RationalTestScript;


import framework.AtlasScriptExecution;
import framework.AtlasScriptbase;
import framework.crmmodel.SignInDialogModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 21, 2013
 */
public  class SignInDialogController extends WebDriverControllerBase
{

	private static  Logger logger = Logger.getLogger(SignInDialogController.class); 
	private SignInDialogModel signInPageModel = null;
	protected static EnvironmentUtility envUtil = new EnvironmentUtility();
	public SignInDialogController(WebDriver driver) {
		super(driver);
		signInPageModel = new SignInDialogModel(driver);
	}
	
	public boolean waitForExistence(){
		
		boolean result = false;
		
		result = waitForElementPresent(signInPageModel, 60);
		
		return result;
		
	}
	public void signIn(String userName, String password,String action){
		logger.info("signIn("+userName+","+password+","+action+")");
		if(userName!=null && !userName.contentEquals("!")){
			signInPageModel.userTextBox().sendKeys(userName);
		}
		
		if(password!=null && !password.contentEquals("!")){
			signInPageModel.passwordTextBox().sendKeys(password);
		}

		if(action!=null && !action.contentEquals("!")){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", signInPageModel.logOnButton());
			//signInPageModel.logOnButton().click();
			waitUntillBrowserRenders();
			
		}
	}
	
		public void signIn(){
			logger.info("signIn()");
			signInPageModel.userTextBox().sendKeys(envUtil.crm().userName());
			signInPageModel.passwordTextBox().sendKeys(envUtil.crm().passWord());
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", signInPageModel.logOnButton());
			//signInPageModel.logOnButton().click();
			getExecutingScript().delayFor(10);
			if(signInPageModel.continueButton()!=null){
				signInPageModel.continueButton().click();	
			}
			waitUntillBrowserRenders();
				
/*		if(userName!=null && !userName.contentEquals("!") && password!=null && !password.contentEquals("!")){
			waitUntillBrowserRenders();
		}*/
		
	}

	
}
