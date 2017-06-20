package framework.crmcontroller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import interfaces.crm.ICRMApplicationController;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentWindowHandle;

import utils.EnvironmentUtility;

import com.opera.core.systems.OperaDriver;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.webdrivermodel.ApplicationModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  November 18, 2013
 */
public class CRMApplicationController extends WebDriverControllerBase 
{
	private static  Logger logger = Logger.getLogger(CRMApplicationController.class); 
	private ApplicationModel appModel = null;
	protected static EnvironmentUtility envUtil = new EnvironmentUtility();
	private SignInDialogController signInDialog = null;
	private SelectABusinessRolePageController selectaBusinessRolePage = null;
	private IdentifyAccountPageController identifyAccountPage = null;
	private TopNavToolBarController topNavToolBar = null;
	private OrderManagementOrderPageController orderManagementOrderPage = null;
	private OrderManagementServReqPageController orderManagementServReqPage = null;
	private SelectSalesServiceTransactionPageController selectSalesServiceTransactionPage = null;
	private OrderManagementIssueRefundPageController orderManagementIssueRefundPage = null;
	private OrderManagementIssueRefundSummaryPageController issueRefundSummaryPage = null;
	private OrderManagementSummaryPageController orderManagementSummaryPage = null;
	private OrderManagementOptionsPageController optionsPage = null;
	public CRMApplicationController(WebDriver driver) {
		super(driver,envUtil.crm().serverName());
		
	}
	public SignInDialogController signInDialog(){
		if(signInDialog==null){
			signInDialog = new SignInDialogController(driver);
		}
		return signInDialog;
	}
	public SelectABusinessRolePageController selectABusinessRolePage(){
		if(selectaBusinessRolePage==null){
			selectaBusinessRolePage = new SelectABusinessRolePageController(driver);
		}
		return selectaBusinessRolePage;
	}
	public IdentifyAccountPageController identifyAccountPage(){
		if(identifyAccountPage==null){
			identifyAccountPage = new IdentifyAccountPageController(driver);
		}
		return identifyAccountPage;
	}
	public TopNavToolBarController topNavToolBar(){
		if(topNavToolBar==null){
			topNavToolBar = new TopNavToolBarController(driver);
		}
		return topNavToolBar;
	}
	public OrderManagementOrderPageController orderManagementOrderPage(){
		
		if(orderManagementOrderPage==null){
			orderManagementOrderPage = new OrderManagementOrderPageController(driver);
		}
		return orderManagementOrderPage;
	}
	public OrderManagementServReqPageController orderManagementServReqPage(){
		if(orderManagementServReqPage==null){
			orderManagementServReqPage = new OrderManagementServReqPageController(driver);
		}
		return orderManagementServReqPage;
	}
	public OrderManagementIssueRefundPageController orderManagementIssueRefundPage(){
		if(orderManagementIssueRefundPage==null){
			orderManagementIssueRefundPage = new OrderManagementIssueRefundPageController(driver);
		}
		return orderManagementIssueRefundPage;
	}
	
	public OrderManagementOptionsPageController orderManagementOptionsPage(){
		if(optionsPage==null){
			optionsPage = new OrderManagementOptionsPageController(driver);
		}
		return optionsPage;
	}
	public OrderManagementIssueRefundSummaryPageController orderManagementIssueRefundSummaryPage(){
		if(issueRefundSummaryPage==null){
			issueRefundSummaryPage = new OrderManagementIssueRefundSummaryPageController(driver);
		}
		return issueRefundSummaryPage;
	}
	public SelectSalesServiceTransactionPageController selectSalesServiceTransactionPage(){
		
		if(selectSalesServiceTransactionPage==null){
			selectSalesServiceTransactionPage = new SelectSalesServiceTransactionPageController(driver);
		}
		return selectSalesServiceTransactionPage;
	}
	public OrderManagementSummaryPageController orderManagementSummaryPage(){
		if(orderManagementSummaryPage==null){
			orderManagementSummaryPage = new OrderManagementSummaryPageController(driver);
		}
		return orderManagementSummaryPage;
	}
	
/*
 * Start the application
 */
	public void startApplication(){
		logger.info("startApplication");
		//driver.switchTo().activeElement(); 
		getDriver().navigate().to(envUtil.crm().serverName());
		waitUntillBrowserRenders();
		maximizeBrowser();
	}
	/*
	 * Log Off  the application
	 */
	public void logOff(){
		logger.info("logOff");
		WebElement logOffLink = null;
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame("CRMApplicationFrame");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[1]")));
		driver.switchTo().frame("CRMApplicationFrame");
		driver.switchTo().frame("FRAME_APPLICATION");
		driver.switchTo().frame("HeaderFrame");
		
	
		logOffLink = driver.findElement(By.xpath("//a[@id='LOGOFF']"));
		//logOffLink = driver.findElement(By.id("LOGOFF"));
		
		logOffLink.click();
		driver.switchTo().alert();
		driver.switchTo().alert().accept();
		waitUntillBrowserRenders();
		//maximizeBrowser();
	}
	
	public Alert getCurrentAlert(){
	    return driver.switchTo().alert();
	}
	
	
	/*
	 * Close the application
	 */
	public void closeApplication() {
		logger.info("closeApplication");
		if(!(getDriver() instanceof InternetExplorerDriver) && !(getDriver() instanceof FirefoxDriver && !(getDriver() instanceof OperaDriver))) {
			
			getDriver().quit();
			getExecutingScript().setCRMToNull();
			getExecutingScript().setDriverToNull();
			
			try {
				killProcess("chromedriver.exe");
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}else
		{
			getDriver().quit();
			getExecutingScript().setCRMToNull();
			getExecutingScript().setDriverToNull();
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
