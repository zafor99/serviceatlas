package framework.webdrivercontroller;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.bn.qa.xobject.exception.TimedOutException;
import com.opera.core.systems.OperaDriver;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

import interfaces.webdriver.IDashboardApplicationController;
import framework.webdrivermodel.ApplicationModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 05, 2012
 */
public class DashboardApplicationController extends WebDriverControllerBase implements IDashboardApplicationController
{
	private static  Logger logger = Logger.getLogger(DashboardApplicationController.class); 
	private ApplicationModel appModel = null;
	protected WebDriver driver = null;
	
	public DashboardApplicationController(WebDriver driver) {
		super(driver);		
		this.driver = driver;
		appModel = new ApplicationModel(driver);
	}

	public void startApplication(){
		logger.info("startApplication");
		getDriver().navigate().to(IDashboardApplicationController.APP_URL);
		waitUntillBrowserRenders();
		maximizeBrowser();
	}
	
	public void closeApplication(){
		logger.info("closeApplication");
		if(!(getDriver() instanceof InternetExplorerDriver) && !(getDriver() instanceof FirefoxDriver && !(getDriver() instanceof OperaDriver))) {
			getDriver().quit();
//			getDriver().close();
			try {
				killProcess("chromedriver.exe");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else
		{
			getDriver().quit();
			/*try {
				killProcess("chromedriver.exe");
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		}		
	}
	
	private void killProcess(String processName) throws Exception{
		 try {
			 if(isProcessRunging(processName)){
			Runtime.getRuntime().exec("taskkill /F /IM "+processName);
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean isProcessRunging(String serviceName) throws Exception {
		 Process p = Runtime.getRuntime().exec("tasklist");
		 BufferedReader reader = new BufferedReader(new InputStreamReader(
		   p.getInputStream()));
		 String line;
		 while ((line = reader.readLine()) != null) {

		  //System.out.println(line);
		  if (line.contains(serviceName)) {
		   return true;
		  }
		 }
		 return false;
		}

	
	public void closeBrowser(String documentName){
		logger.info("closeBrowser");
		appModel.externalBrowser(documentName).close();	
		driver = appModel.externalBrowser(APP_URL);
	}
	
	public void maximizeBrowser(){

		logger.info("windowMaximize");
		if(!(getDriver() instanceof InternetExplorerDriver) && !(getDriver() instanceof FirefoxDriver) && !(getDriver() instanceof OperaDriver))  {
			Toolkit toolkit = Toolkit.getDefaultToolkit(); 

			Dimension screenResolution = new Dimension((int) 
					 toolkit.getScreenSize().getWidth(), (int) 
					 toolkit.getScreenSize().getHeight()); 
			
			getDriver().manage().window().setSize(screenResolution); 


		}else {
			selenium.windowMaximize();
		}
	}
	
	public void navigateTo(String url){
		logger.info("navigateTo");
		getDriver().navigate().to(url);
		waitUntillBrowserRenders();
	}
	
	public void navigateBack(){
		logger.info("navigateBack");
		getDriver().navigate().back();
		waitUntillBrowserRenders();
	}
	
	public void navigateBack(String documentName){
		logger.info("navigateBack");
		appModel.externalBrowser(documentName).navigate().back();
	}
	
	public void navigateForward(){
		
		logger.info("navigateForward");
		getDriver().navigate().forward();
		waitUntillBrowserRenders();
	}
	
	public void refresh(){
		
		logger.info("refresh");
		getDriver().navigate().refresh();
		waitUntillBrowserRenders();
	}

	
	public void pressEnter(){
		logger.info("pressEnter");
		
	}

	public void waitUntillBrowserRenders(){

		logger.info("waitUntillBrowserRenders");
		selenium.waitForPageToLoad("60000");
	}
	
	public void waitUntillBrowserRenders(int second){

		logger.info("waitUntillBrowserRenders");
		selenium.waitForPageToLoad(String.valueOf(second));
	}
	
	public void waitUntillBrowserRenders(String documentName){

		logger.info("waitUntillBrowserRenders");
		WebDriverBackedSelenium extSelenium = new WebDriverBackedSelenium(appModel.externalBrowser(documentName), appModel.externalBrowser(documentName).getCurrentUrl());
		extSelenium.waitForPageToLoad("60000");
	}
	
	public boolean verifyBrowserURL(String vpName)
	{
		logger.info("verifyBrowserURL");
		boolean result=false;
		
		String URL = getDriver().getCurrentUrl();
		result = getExecutingScript().vpManual(appendRegionCode(vpName), URL).performTest();
		
		return result;
	}
	
	public boolean verifyBrowserURL(String documentName, String vpName)
	{
		logger.info("verifyBrowserURL");
		boolean result=false;
		
		String URL = appModel.externalBrowser(documentName).getCurrentUrl();
		result = getExecutingScript().vpManual(appendRegionCode(vpName), URL).performTest();
		
		return result;
	}
	
	public String getBrowserURL()
	{
		String URL = getDriver().getCurrentUrl();
		return  URL;
	}
	
	public boolean verifyPageTitle(String vpName)
	{
		boolean result=false;
		
		String URL = getBrowserTitle();
		result = getExecutingScript().vpManual(appendRegionCode(vpName), URL).performTest();
		
		return result;
	}
	
	public String getBrowserTitle(){
		logger.info("getBrowserTitle");
		return getDriver().getTitle();
	}
	
	public String getBrowserStatus(){
		// TODO Auto-generated method stub
		return null;
	}
	
	public void clearBrowserCookies() {
		logger.info("clearBrowserCookies");
		getDriver().manage().deleteAllCookies();
		refresh();
		System.out.println("Clear Cookies : " + getDriver().manage().getCookies().toString());
		
	}
}
