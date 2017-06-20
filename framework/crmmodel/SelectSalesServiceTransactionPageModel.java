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
public  class SelectSalesServiceTransactionPageModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	String winHandle = null;
	public SelectSalesServiceTransactionPageModel(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	public WebElement container(){
		WebElement container = null;
		try {
			//winHandle= driver.getWindowHandle();
			saveCurrentHandle();
			driver.switchTo().defaultContent();  
			System.out.print("");

			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getWindowHandle());
			
			System.out.println(driver.getWindowHandles());
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getWindowHandle());
			driver.switchTo().frame("WorkAreaFrame1");
			container = driver.findElement(By.id("rootAreaDiv"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	public void saveCurrentHandle(){
		winHandle= driver.getWindowHandle();;
	}
	
	public void resetHandle(){
		driver.switchTo().window(winHandle);
	}
	
	public CRMCustomTableModel serviceRequestTable(){		
		return new CRMCustomTableModel(driver, container(), By.xpath("//table[@id='C30_W111_V112_Table_TableHeader']"));

	}
	public WebElement firstCRMServiceRequestCheckbox(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//td[@id='C30_W111_V112_Table_sel_1']/div/a"));
			//button = container().findElement(By.id("C30_W111_V112_Table_sel_1-rowsel"));	
			
			//button = container().findElement(By.xpath("//td[@id='C30_W111_V112_Table_sel_1']/div/a[@id='C30_W111_V112_Table_sel_1-rowsel']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement secondCRMServiceRequestCheckbox(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C30_W111_V112_Table_sel_2-rowsel']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
}
