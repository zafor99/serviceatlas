package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.IdentifyAccountPageOrdersTabPanelModel;
import framework.crmmodel.TopNavToolBarModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  December 18, 2013
 */
public  class TopNavToolBarController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(TopNavToolBarController.class); 
	private TopNavToolBarModel appModel  = null;
	public TopNavToolBarController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new TopNavToolBarModel(driver);
	}
	public void end(){
		logger.info("end");
		appModel.endButton().click();
		waitUntilProcessingEnds();
	}
}
