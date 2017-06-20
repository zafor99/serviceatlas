package framework.webdrivermodel;

import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 05, 2012
 */
public class ApplicationModel extends WebDriverModelBase{

	public ApplicationModel(WebDriver driver) {
		super(driver);
	}
	

	public WebDriver externalBrowser(String documentName){
		
		WebDriver newDriver = null;
		String targetHandle = "";
		
		for (String handle : driver.getWindowHandles()) {
			
			System.out.println(driver.switchTo().window(handle).getCurrentUrl());
			if(driver.switchTo().window(handle).getCurrentUrl().contains(documentName))
			{
				targetHandle = handle;
				break;
			}
			  
	    }
		if(!targetHandle.contentEquals("")){
			newDriver = driver.switchTo().window(targetHandle);
		}
		
		return newDriver;
		
	}
}
