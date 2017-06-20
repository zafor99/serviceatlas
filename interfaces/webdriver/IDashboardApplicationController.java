package interfaces.webdriver;



public interface IDashboardApplicationController {
	//public String APP_URL = "https://qa.nook.com";
	public String APP_URL = "http://injeaijapp01:8080/atlas-web/dashboard/view/camel";
	//public String APP_URL = "http://www.qa.gooseherd.com/";

	public abstract void startApplication();

	public abstract void closeApplication();

	public abstract void closeBrowser(String documentName);

	public abstract void maximizeBrowser();

	public abstract void navigateTo(String url);

	public abstract void navigateBack();

	public abstract void navigateBack(String documentName);

	public abstract void navigateForward();

	public abstract void refresh();

	public abstract void pressEnter();

	public abstract void waitUntillBrowserRenders();

	public abstract void waitUntillBrowserRenders(int second);

	public abstract void waitUntillBrowserRenders(String documentName);

	public abstract boolean verifyBrowserURL(String vpName);

	public abstract boolean verifyBrowserURL(String documentName, String vpName);

	public abstract String getBrowserURL();

	public abstract boolean verifyPageTitle(String vpName);

	public abstract String getBrowserTitle();

	public abstract String getBrowserStatus();
	
	public abstract void clearBrowserCookies();

}