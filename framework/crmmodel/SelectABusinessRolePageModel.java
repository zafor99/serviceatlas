package framework.crmmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  November 18, 2013
 */
public class SelectABusinessRolePageModel extends WebDriverModelBase
{

	private WebElement selectABusinessRolePage = null;
	public SelectABusinessRolePageModel(WebDriver driver){
		super(driver,By.id("crmUIHostBusinessRoles"));		
	}
	public WebElement ZBNT1CSRLink(){
		//driver.switchTo().activeElement(); 
		return this.findElement(By.id("ZBNT1CSR"));		
	}
	
	
}
