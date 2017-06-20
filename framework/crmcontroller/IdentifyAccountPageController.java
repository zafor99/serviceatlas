package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.IdentifyAccountPageAccountSearchPanelModel;
import framework.crmmodel.IdentifyAccountPageModel;
import framework.crmmodel.SignInDialogModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 21, 2013
 */
public  class IdentifyAccountPageController extends PageController
{
	private WebDriver driver = null;
	private static  Logger logger = Logger.getLogger(IdentifyAccountPageController.class); 
	private IdentifyAccountPageModel identifyAccountPageModel = null;
	private IdentifyAccountPageAccountSearchPanelController accountSearchPanelController = null;
	private IdentifyAccountPageAccountConfirmPanelController accountConfirmPanelController = null;
	private IdentifyAccountPageOrdersTabPanelController ordersTabPanelController = null;
	public IdentifyAccountPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		identifyAccountPageModel = new IdentifyAccountPageModel(driver);
	}
	public IdentifyAccountPageAccountSearchPanelController accountSearchPanel(){
		if(accountSearchPanelController ==null){
			accountSearchPanelController = new IdentifyAccountPageAccountSearchPanelController(driver);
		}
		return accountSearchPanelController;
	}
	public IdentifyAccountPageAccountConfirmPanelController accountConfirmPanel(){
		if(accountConfirmPanelController ==null){
			accountConfirmPanelController = new IdentifyAccountPageAccountConfirmPanelController(driver);
		}
		return accountConfirmPanelController;
	}
	public IdentifyAccountPageOrdersTabPanelController ordersTabPanel(){
		if(ordersTabPanelController ==null){
			ordersTabPanelController = new IdentifyAccountPageOrdersTabPanelController(driver);
		}
		return ordersTabPanelController;
	}
}
