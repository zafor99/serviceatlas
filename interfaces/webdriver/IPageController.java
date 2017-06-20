package interfaces.webdriver;

public interface IPageController{

	public abstract String getTitle();

	public abstract boolean verifyPageTitle(String vpName);

}