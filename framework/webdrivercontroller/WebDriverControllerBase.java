package framework.webdrivercontroller;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BNTimer;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;
import com.sun.corba.se.impl.orb.ParserTable.TestBadServerIdHandler;

import framework.AtlasScriptbase;


/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 05, 2012
 */
public class WebDriverControllerBase 
{
	protected WebDriver driver = null;
	protected WebDriverBackedSelenium selenium = null;
	protected WebDriverWait wait = null;
	private static  Logger logger = Logger.getLogger(WebDriverControllerBase.class); 
	
	public AtlasScriptbase getExecutingScript()
	{
		return AtlasScriptbase.getExecutingScript();
	}
	
	public WebDriverControllerBase(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		selenium = new WebDriverBackedSelenium(driver, DashboardApplicationController.APP_URL);
	}
	
	public WebDriverControllerBase(WebDriver driver, String url){
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		selenium = new WebDriverBackedSelenium(driver, url);
	}
	
	public WebDriver  getDriver(){
		return driver;
	}
	
	public WebDriverBackedSelenium  getSelenium(){
		return selenium;
	}
	
	public WebDriverWait getWait(){
		return wait;
	}
	
	public String getRegionCode(){
		return null;
		
		/*String regionCode = "";
		String[] urlList = null;
		
		urlList = getExecutingScript().nook().APP_URL.split("\\.");
		if(urlList.length>3){
			regionCode = (urlList[0].substring(urlList[0].indexOf(":")+3)).toUpperCase();
		}
		else
		{
			regionCode = "US";
		}
		return regionCode;*/
		
	}
	
	public String appendRegionCode(String vpName){
		return getRegionCode() + "_" + vpName;
	}
	
	public boolean waitForElementPresent(WebElement element, int waitInSeconds)
	{		
		int wait = waitInSeconds * 1000;
	    int y  = (wait/250);
	    Stopwatch sw = new Stopwatch();
	    sw.start();

	    for (int x = 0; x < y; x++)
	    {
	        if (sw.elapsedTime(TimeUnit.MILLISECONDS) > wait) 
	            return false;

	        if (element != null)
	            return true;
	        try {
				Thread.sleep(250);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
	    }
	    return false;
	}
	
	
	public WebElement waitForElement(By locator,int timeOutInSeconds){
		WebDriverWait wdWait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return (WebElement) wdWait.until(WebDriverControllerBase.presenceOfElementLocated(locator));
	}
	
	public void waitUntillBrowserRenders() {
		logger.info("waitUntillBrowserRenders");
		selenium.waitForPageToLoad("60000");
		
	}
	
	public void waitUntilProcessingEnds(int sec){
		WebElement button = null;	
		int height=-1;
		BNTimer time = new BNTimer();
		time.start();
		try {
			do{
				driver.switchTo().defaultContent();  
				button = driver.findElement(By.xpath("//div[@id='th_freezeShield']/img"));
				height = Integer.valueOf(button.getAttribute("offsetHeight"));
				System.out.println("Waiting for Process to End...");
				if(time.getElapsedTime()>=sec){
					break;
				}
			}while(height>0);
			
			
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		
	}
	public void waitUntilProcessingEnds(){
		waitUntilProcessingEnds(60);
		
	}
	public void waitUntillBrowserRenders(int sec) {
		logger.info("waitUntillBrowserRenders");
		selenium.waitForPageToLoad(String.valueOf(sec*1000));
		
	}
	
	public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator){
		return new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver){
				return driver.findElement(locator);
			}
		};
	}
	
	public void inputKeys(WebElement textBox, String value){
		
		try {
			Robot robot = new Robot();
			char[] charList = value.toCharArray();
			
			textBox.clear();
			textBox.click();
			for(int i=0; i<charList.length;i++){
				robot.keyPress(charList[i]);
				robot.keyRelease(charList[i]);
			}
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String[] convertArrayListToArray(ArrayList<String> list){
		
		String[] data = null;
		data = new String[list.size()];
		
		for(int i=0; i<data.length;i++){
			data[i] = list.get(i);
		}
		
		return data;
	}
	
	public String getElementXPath(WebElement element) {
	    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!=='')" +
	    		"{return'id(\"'+c.id+'\")'}" +
	    		"if(c===document.body){return c.tagName}" +
	    		"var a=0;var e=c.parentNode.childNodes;" +
	    		"for(var b=0;b<e.length;b++)" +
	    		"{var d=e[b];if(d===c)" +
	    		"{return gPt(c.parentNode)+'/'" +
	    		"+c.tagName+'['+(a+1)+']'}" +
	    		"if(d.nodeType===1&&d.tagName===c.tagName){a++}}};" +
	    		"return gPt(arguments[0]).toLowerCase();", element);
	}
			
	public boolean verifyContainerInformationList(WebElement container, String vpName){
		boolean result = false;
		
		//Capture all the fieldset from the container.
		List<WebElement> fieldSetList = container.findElements(By.xpath("article//fieldset|form/article|*/..//fieldset"));
		ArrayList<String> dataList = new ArrayList<String>();
		
		String[][] data = null;
		WebElement label = null;
		WebElement element = null;		
		
		for(int i=0;i<fieldSetList.size();i++){
			String info = "";
			System.out.println(getElementXPath(fieldSetList.get(i)) + " :: " + fieldSetList.get(i).getAttribute("class"));
			
			//for Save change capture the name only
			if(fieldSetList.get(i).getAttribute("class").contains("save-changes")){
				List<WebElement> elementList = fieldSetList.get(i).findElements(By.xpath("input|button"));
				{
					
					info = info + "Button" + "!";
					for(int j=0;j<elementList.size();j++){
						if(elementList.get(j).getTagName().contains("button")){
							info  = info + elementList.get(j).getText() + " | ";
						} else if(elementList.get(j).getTagName().contains("input")){
							info  = info + elementList.get(j).getAttribute("value");
						}
					}
					dataList.add(info);
				}
			} 
			//for existing email label split and if length > 2 then display
			else if(fieldSetList.get(i).getAttribute("class").contains("existing-email")){
				String[] emailList = fieldSetList.get(i).getText().split(":");
				System.out.println("existing-email " + emailList.length);
				if(emailList.length>2){
					dataList.add(emailList[0] + "!" + emailList[1]);
				}
				
			} 
			// if expiration date capture one labe and both combobox for Month and Year
			else if(fieldSetList.get(i).getAttribute("class").contains("expiration-date")){
				List<WebElement> elementList = fieldSetList.get(i).findElements(By.xpath("div/span/select"));
				{
					info = "";
					label = fieldSetList.get(i).findElement(By.xpath("label"));
					
					for(int j=0;j<elementList.size();j++){
						info = "";
						if(j==0){
							label = fieldSetList.get(i).findElement(By.xpath("label"));
							info = label.getAttribute("class").contains("required")?"*"+label.getText():label.getText();
							info+="!";
						} else {
							info = " " + "!";
						}
						Select combo = new Select(elementList.get(j));
						info = info + combo.getFirstSelectedOption().getText();
						dataList.add(info);
					}
					
				}
			}
			else {
				element = fieldSetList.get(i).findElement(By.xpath("input|span/select"));				
				if(fieldSetList.get(i).findElement(By.xpath("input|span/select|button")).getTagName().contains("select")){
					info = "";
					Select combo = new Select(element);
					label = fieldSetList.get(i).findElement(By.xpath("label"));
					info = label.getAttribute("class").contains("required")?"*"+label.getText():label.getText();
					info+= "!";
					info = info + combo.getFirstSelectedOption().getText();
					dataList.add(info);
				} else if(element.getTagName().contains("input")){
					info = "";
					label = fieldSetList.get(i).findElement(By.xpath("label"));
					info = label.getAttribute("class").contains("required")?"*"+label.getText():label.getText();
					info += "!";
					info += element.getAttribute("value").contentEquals("")?" ":element.getAttribute("value");
					dataList.add(info);
				} 
			}			

		}
		data = new String[dataList.size()][2];
		for(int i=0; i<data.length;i++){
			String[] info = dataList.get(i).split("!");
			data[i][0] = info[0];
			data[i][1] = info[1];
		}
		
		ITestData testData = VpUtil.getTestData(data);
		result = getExecutingScript().vpManual(appendRegionCode(vpName), testData).performTest();
		
		return result;
	}
	
	public boolean verifyContainerErrorList(WebElement container, String vpName){
		boolean result = false;
		List<WebElement> errorList = container.findElements(By.xpath("fieldset/label/strong[@class='error-copy error']|div[@class='error-copy error']"));
		String[] data = new String[errorList.size()];	
		
		for(int i=0;i<data.length;i++){
			data[i] = errorList.get(i).getText();
		}
		
		ITestData testData = VpUtil.getTestData(data);
		result = getExecutingScript().vpManual(appendRegionCode(vpName), testData).performTest();
		
		return result;
	}
	
}
