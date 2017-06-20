package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.PageModel;
import framework.crmmodel.SignInDialogModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 21, 2013
 */
public  class PageController extends WebDriverControllerBase
{
	private static  Logger logger = Logger.getLogger(PageController.class); 
	private PageModel pageModel = null;
	public PageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pageModel = new PageModel(driver);
	}

	public Header topPanel(){
		return new Header();
	}
	public class Header{
		
		public Header(){
			
		}
		public void clickEnd(){
			pageModel.topPanel().endButton().click();
			waitUntilProcessingEnds();
		}
	}
	
}
