package framework.webdrivermodel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.GetExpression;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.rational.test.ft.script.RationalTestScript;

import framework.webdrivercontroller.WebDriverControllerBase;


/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 05, 2012
 */
public class WebDriverModelBase implements WebElement
{
	protected WebDriver driver = null;
	protected WebElement element = null;
	public WebDriverModelBase(WebDriver driver){
		this.driver = driver;
	}
	
	public WebDriverModelBase(WebDriver driver, By by){
		this.driver = driver;
		WebElement element = null;		
		try {
			element = driver.findElement(by);
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}
		this.element = element;
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
	
	public WebDriverModelBase(WebDriver driver, WebElement element){
		this.driver = driver;	
		try {
			this.element = element;
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}
		this.element = element;
	}
	
	public WebDriverModelBase(WebElement element, By by) {
		WebElement element2 = null;		
		try {
			element2 = element.findElement(by);
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}
		this.element = element2;
	}

	public WebDriver  getDriver(){
		return driver;
	}
	
	public WebElement findElement(By by){
		return getDriver().findElement(by);
	}
	
	public WebElement waitForElement(By locator,int timeOutInSeconds){
		WebDriverWait wdWait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return (WebElement) wdWait.until(WebDriverControllerBase.presenceOfElementLocated(locator));
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
	
	public WebElement activeBillingEditContainer(){
		return activeBillingEditContainer(element);
	}
	
	public WebElement activeBillingEditContainer(WebElement parent){
		WebElement container = null;
		List<WebElement> list = null;
		String style = "";
		try {
			list = driver.findElements(By.xpath("//article[@class='billing-edit']/article"));
			for(int i=0;i<list.size();i++){
				style = list.get(i).getAttribute("style");
				if(style.contains("block")){
					container = list.get(i);
				}
			}
			
			//container = getDriver().findElement(By.cssSelector("article[style='display: block;']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){

		}
		System.out.println(getElementXPath(container)+ "\t" + container.getAttribute("class"));
		return container;
	}
	
	public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator){
		return new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver){
				return driver.findElement(locator);
			}
		};
	}

	@Override
	public void clear() {
		element.clear();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void click() {
		element.click();
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return element.findElements(arg0);
	}

	@Override
	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return element.getAttribute(arg0);
	}

	@Override
	public String getCssValue(String arg0) {
		// TODO Auto-generated method stub
		return element.getCssValue(arg0);
	}

	@Override
	public Point getLocation() {
		
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return element.getSize();
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return element.getTagName();
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return element.getText();
	}

	@Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return element.isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return element.isEnabled();
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return element.isSelected();
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		element.sendKeys(arg0);
		
	}

	@Override
	public void submit() {
		element.submit();
		
	}
}
