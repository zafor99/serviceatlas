package framework.crmcontroller;

import java.util.List;

import org.apache.log4j.Logger;
//import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;

import framework.crmmodel.OrderManagementServReqPageServiceReqPanelModel;
import framework.crmmodel.OrderMgntServReqPageServiceReqOverviewPanelModel;
import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 29, 2014
 */
public class OrderManagementServReqPageServiceRequestOverviewPanelController extends PageController
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementServReqPageServiceRequestOverviewPanelController.class);
	private OrderMgntServReqPageServiceReqOverviewPanelModel appModel  = null;
	public OrderManagementServReqPageServiceRequestOverviewPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new OrderMgntServReqPageServiceReqOverviewPanelModel(driver);
	}
	
	public void selectCategory1ComboBox(String item){
		int count;
		String tempItem=null;
		appModel.category1InputBox().click();
		getExecutingScript().delayFor(2);
		List<WebElement> list = null;
		try {
			list = appModel.category1ComboList().findElements(By.xpath("ul/li"));//   by.xpath(By.xpath("ul/li[3]"));
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
	public void selectCategory2ComboBox(String item){
		int count;
		String tempItem=null;
		appModel.category2InputBox().click();
		getExecutingScript().delayFor(2);
		List<WebElement> list = null;
		try {
			list = appModel.category2ComboList().findElements(By.xpath("ul/li"));//   by.xpath(By.xpath("ul/li[3]"));
			count = list.size();
			for(int i=1;i<count;i++){
				tempItem = list.get(i).getText();
				System.out.println("Item Text: "+tempItem);
				if(tempItem.contentEquals(item)){
					list.get(i).click();
					//list.get(i).click();
					getExecutingScript().delayFor(5);
					break;
				}
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}catch (NullPointerException ex){
		}
	}
	public void selectCategory3ComboBox(String item){
		int count;
		String tempItem=null;
		appModel.category3InputBox().click();
		getExecutingScript().delayFor(2);
		List<WebElement> list = null;
		try {
			list = appModel.category3ComboList().findElements(By.xpath("ul/li"));//   by.xpath(By.xpath("ul/li[3]"));
			count = list.size();
			for(int i=1;i<count;i++){
				tempItem = list.get(i).getText();
				System.out.println("Item Text: "+tempItem);
				if(tempItem.contentEquals(item)){
					list.get(i).click();
					//list.get(i).click();
					getExecutingScript().delayFor(5);
					break;
				}
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}catch (NullPointerException ex){
		}
	}
	public void selectResolutionComboBox(String item){
		int count;
		String tempItem=null;
		appModel.resolutionInputBox().click();
		getExecutingScript().delayFor(2);
		List<WebElement> list = null;
		try {
			list = appModel.resolutionComboList().findElements(By.xpath("ul/li"));//   by.xpath(By.xpath("ul/li[3]"));
			count = list.size();
			for(int i=1;i<count;i++){
				tempItem = list.get(i).getText();
				System.out.println("Item Text: "+tempItem);
				if(tempItem.contentEquals(item)){
					list.get(i).click();
					//list.get(i).click();
					getExecutingScript().delayFor(5);
					break;
				}
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}catch (NullPointerException ex){
		}
	}
	
	
	public void updateCallReason(String category1,String category2,String category3,String resolution){
		getExecutingScript().delayFor(3);
		logger.info("updateCallReason("+category1+"),("+category2+"),("+category3+"),("+resolution+")");
		if(category1!=null){
			selectCategory1ComboBox(category1);
		}
		if(category2!=null){
			selectCategory2ComboBox(category2);
		}
		if(category3!=null){
			selectCategory3ComboBox(category3);
		}
		if(resolution!=null){
			selectResolutionComboBox(resolution);
		}
	}
}
