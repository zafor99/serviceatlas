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
 * @since  February 06, 2014
 */
public  class OrderManagementOptionPageAvailOrderPanelModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public OrderManagementOptionPageAvailOrderPanelModel(WebDriver driver){
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
			container = driver.findElement(By.id("C19_W70_V71_V114_0001"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
		return container;
	}
	public WebElement addPaymentRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_1']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement modifyOrderRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_2']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement replacementRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_3']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement returnDebitRefundRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_4']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	
	public WebElement addPaymentTypeRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_5']"));
																    
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement taxExemptionRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_6']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement promotionsRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_6']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement addUpdateGiftMessageRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_7']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement issueRefundRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_5']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement refundRadioButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//input[@id='C19_W70_V71_V114_V115_thtmlb_radioButton_10']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
}
