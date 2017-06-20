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
 * @since  December 02, 2013
 */
public  class IdentifyAccountPageAccountConfirmPanelModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public IdentifyAccountPageAccountConfirmPanelModel(WebDriver driver){
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
			container = driver.findElement(By.id("C17_W60_V61_V62"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
			System.out.println("Container not Found");
		}
				
		return container;
	}
	public WebElement accountLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_3"));	
	}
	public WebElement accountNameTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_customer_struct.firstname']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement accountIDTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_customer_struct.lastname']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement addressLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_4"));	
	}
	public WebElement addressTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.street']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement aptNoTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.house_no']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement cityLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_5"));	
	}
	public WebElement cityTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.city']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement zipCodeLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_6"));	
	}
	public WebElement zipCodeTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.postl_cod1']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement stateTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.region']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement stateNameTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_region_text']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement countryLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_7"));	
	}
	public WebElement countryTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.country']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement countryNameTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_country_text']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement telephoneLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_8"));	
	}
	public WebElement faxLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_9"));	
	}
	public WebElement faxTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.fax_nofax']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement emailAddressLabel(){
		return container().findElement(By.id("C17_W60_V61_V62_thtmlb_label_10"));	
	}
	public WebElement emailAddressTexBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C17_W60_V61_V62_defaultaddress_struct.e_mailsmt']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement confirmButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C17_W60_V61_V62_Confirm']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
		//return container().findElement(By.id("C3_W18_V19_V20_Search"));	
	}
	public WebElement saveButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C17_W60_V61_V62_Save']/img"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
		//return container().findElement(By.id("C3_W18_V19_V20_Search"));	
	}
	public WebElement deleteButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C17_W60_V61_V62_reset']/img"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
		//return container().findElement(By.id("C3_W18_V19_V20_Search"));	
	}
	public WebElement editButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C17_W60_V61_V62_Change']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
		//return container().findElement(By.id("C3_W18_V19_V20_Search"));	
	}
	public WebElement clearButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C17_W60_V61_V62_clear']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
		//return container().findElement(By.id("C3_W18_V19_V20_Search"));	
	}
	
	
}
