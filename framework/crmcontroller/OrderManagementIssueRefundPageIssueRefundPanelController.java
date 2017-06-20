package framework.crmcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementIssueRefundPageIssueRefundPanelModel;
import framework.webdrivermodel.WebDriverModelBase;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 06, 2014
 */
public  class OrderManagementIssueRefundPageIssueRefundPanelController extends PageController
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementIssueRefundPageIssueRefundPanelController.class);
	private OrderManagementIssueRefundPageIssueRefundPanelModel appModel  = null;
	public OrderManagementIssueRefundPageIssueRefundPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new OrderManagementIssueRefundPageIssueRefundPanelModel(driver);
	}
	public void selectReasonCodeComboBox(String item){
		int count;
		String tempItem=null;
		appModel.reasonCodeInputBox().click();
		getExecutingScript().delayFor(2);
		List<WebElement> list = null;
		try {
			list = appModel.reasonCodeComboList().findElements(By.xpath("ul/li"));//   by.xpath(By.xpath("ul/li[3]"));
			count = list.size();
			for(int i=1;i<count;i++){
				tempItem = list.get(i).getText();
				System.out.println("Item Text: "+tempItem);
				if(tempItem.contentEquals(item)){
					list.get(i).click();
					getExecutingScript().delayFor(5);
					break;
				}
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}catch (NullPointerException ex){
		}
	}
	//TODO: Remove this after bug is fixed related to update button
	public void updaterefundQty(){
		
		logger.info("updaterefundQty()");
		WebElement cell = null;
		appModel.issueRefundTableRefundQtyCell().click();
		appModel.issueRefundTableRefundQtyCell().sendKeys("1");
		appModel.issueRefundTableRefundQtyCell().sendKeys(Keys.RETURN);
		getExecutingScript().delayFor(3);
		appModel.updateButton().click();
		getExecutingScript().delayFor(5);
	}
}
