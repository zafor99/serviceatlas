package framework.crmmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rational.test.ft.script.RationalTestScript;

import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 20, 2013
 */
public  class SignInDialogModel extends WebDriverModelBase
{

	//private WebElement signInDialog = null;
	public SignInDialogModel(WebDriver driver){
//		super(driver,By.xpath("//td[@id='SLB_contentcell']"));
		super(driver,By.id("SLB_contentcell"));
	}
	
	public WebElement systemLabel(){
		return this.findElement(By.xpath("//label[@id='sysid_l']"));	
	}
	public WebElement systemTextBox(){
		return this.findElement(By.xpath("//input[@id='sysid']"));	
	}
	public WebElement clientLabel(){
		return this.findElement(By.xpath("//label[@id='12']"));	
	}
	public WebElement clientTextBox(){
		return this.findElement(By.xpath("//input[@id='sap-client']"));	
	}
	public WebElement userLabel(){
		return this.findElement(By.xpath("//label[@id='13']"));	
	}
	public WebElement userTextBox(){
		return this.findElement(By.xpath("//input[@id='sap-user']"));	
	}
	public WebElement passwordLabel(){
		return this.findElement(By.xpath("//label[@id='14']"));	
	}
	public WebElement passwordTextBox(){
		return this.findElement(By.xpath("//input[@id='sap-password']"));	
	}
	public WebElement languageLabel(){
		return this.findElement(By.xpath("//label[@id='15']"));	
	}
	public Select languageComboBox(){
		return new Select(this.findElement(By.id("sap-language-dropdown")));
	}
	public WebElement logOnButton(){
		//return this.findElement(By.id("LOGON_BUTTON"));
		return this.findElement(By.xpath("//a[@id='LOGON_BUTTON']"));		
	}
	public WebElement changePasswordLink(){
		return this.findElement(By.id("CHANGE_PASSWORD_LINK"));		
	}
	public WebElement continueButton(){
		WebElement label = null;		
		try {
			label = this.findElement(By.xpath("//td[@id='cb1']/a"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}
		return label;
	
	}
	
}
