package framework.crmmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public class OrderManagementServReqPageServiceReqPanelModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public OrderManagementServReqPageServiceReqPanelModel(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	public WebElement container(){
		WebElement container = null;
		try {
			
			driver.switchTo().defaultContent();  
			System.out.print("");
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[1]")));
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame("FRAME_APPLICATION");
			driver.switchTo().frame("WorkAreaFrame1");
			container = driver.findElement(By.id("C19_W70_V71_V77_0002"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	public WebElement newButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C19_W70_V71_V77_V79_NEW']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement knowledgeBaseButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C19_W70_V71_V77_V79_KnowledgeBase']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement saveButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C19_W70_V71_V77_V79_SAVE']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
}
