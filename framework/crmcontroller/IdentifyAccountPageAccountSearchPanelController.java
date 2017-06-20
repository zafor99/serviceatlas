package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.crmmodel.IdentifyAccountPageAccountSearchPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;
import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 21, 2013
 */
public  class IdentifyAccountPageAccountSearchPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(IdentifyAccountPageAccountSearchPanelController.class); 
	private IdentifyAccountPageAccountSearchPanelModel identifyAccountPageAccountSearchPanel  = null;
	public IdentifyAccountPageAccountSearchPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		identifyAccountPageAccountSearchPanel = new IdentifyAccountPageAccountSearchPanelModel(driver);
	}

	public void search(String orderNumber,String emailAddress, String phoneNo,String firstAndLastName, String membershipStoreAccountID, String action){
		logger.info("search("+orderNumber+"),("+emailAddress+"),("+phoneNo+"),("+firstAndLastName+"),("+membershipStoreAccountID+"),("+action+")");
		if(orderNumber!=null){
			//identifyAccountPageAccountSearchPanel.orderNoTextBox().
			identifyAccountPageAccountSearchPanel.orderNoTextBox().sendKeys(orderNumber);
		}
		if(emailAddress!=null){
			identifyAccountPageAccountSearchPanel.emailAddressTextBox().sendKeys(emailAddress);
		}
		if(phoneNo!=null){
			identifyAccountPageAccountSearchPanel.phoneNoTextBox().sendKeys(phoneNo);
		}
		if(firstAndLastName!=null){
			identifyAccountPageAccountSearchPanel.firstAndLastNameTextBox().sendKeys(firstAndLastName);
		}
		if(membershipStoreAccountID!=null){
			identifyAccountPageAccountSearchPanel.membershipStoreAccountIDTextBox().sendKeys(membershipStoreAccountID);
		}
		if(action!=null){
			if(action.contains("search")){
				//identifyAccountPageAccountSearchPanel.searchAccountButton().sendKeys(Keys.RETURN);
				identifyAccountPageAccountSearchPanel.searchAccountButton().click();
				waitUntilProcessingEnds(); 
			}
			if(action.contains("clear")){
				identifyAccountPageAccountSearchPanel.clearButton().click();	
			}
			if(action.contains("create")){
				identifyAccountPageAccountSearchPanel.createButton().click();	
			}
		}

	}
	public void printOrderLabel(){
		System.out.println(identifyAccountPageAccountSearchPanel.orderNoLabel().getText());
		
		
	}
}
