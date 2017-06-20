package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;


import framework.crmmodel.IdentifyAccountPageAccountConfirmPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  December 02, 2013
 */
public  class IdentifyAccountPageAccountConfirmPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(IdentifyAccountPageAccountConfirmPanelController.class); 
	private IdentifyAccountPageAccountConfirmPanelModel identifyAccountPageAccountConfirmPanel  = null;
	public IdentifyAccountPageAccountConfirmPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		identifyAccountPageAccountConfirmPanel = new IdentifyAccountPageAccountConfirmPanelModel(driver);
	}
	public boolean isOrderExist(){
		boolean result = false;
		if(identifyAccountPageAccountConfirmPanel.confirmButton()!=null){
			result= true;
		}
		else{
			result = false; 
		}
			
		return result;
	}
	public void confirm(){
		logger.info("confirm()");
		identifyAccountPageAccountConfirmPanel.confirmButton().click();
		waitUntilProcessingEnds();
	}
	
}
