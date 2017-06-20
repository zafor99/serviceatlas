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
public  class OrderManagementTopHeaderPanelModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public OrderManagementTopHeaderPanelModel(WebDriver driver){
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
			//driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[@title='Main Client              ']")));
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame("FRAME_APPLICATION");
			driver.switchTo().frame("WorkAreaFrame1");
			container = driver.findElement(By.id("C1_W1_V2_V3"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	public WebElement nextButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C19_W70_V71_V118_FrwdButton']/span/b|//a[@id='C19_W70_V71_FrwdButton']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
		
	}
	public WebElement previousButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C19_W70_V71_BackButton']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement notificationLink(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//div[@id='th-mes-inf-cont']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement notificationText(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//div[@id='th-mes-cont2']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
}
