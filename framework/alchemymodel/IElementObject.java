package framework.alchemymodel;

public interface IElementObject {

	public abstract void click();

	public abstract void click(int count);

	public abstract void click(int x, int y, int clickCount);

	public abstract void clickIn(String searchZone, String searchElement,
			int x, String direction, int width, int height, int clickCount);

	public abstract void clickIn(String searchZone, String searchElement,
			int index, String direction, int width, int height);

	public abstract void longClick(int x, int y, int clickCount);

	public abstract void sendText(String text);

	public abstract void sendProperty(String property, String value);

	public abstract void swip(String direction, int offset, int time);

	public abstract String[] getAllValues(String property);

	public abstract String getAllZonesWithElement();

	public abstract int getElementCount();

	public abstract String getProperty(String property);

	public abstract String getText();

	public abstract String getTextIn(String textZone, String direction,
			int width, int height);

	public abstract String getTextIn(String textZone, String direction,
			int width, int height, int xOffset, int yOffset);

	public abstract boolean isElementBlank(int colorGroups);

	public abstract boolean isElementFound();

	public abstract boolean isElementFound(int index);

	public abstract void rightClick(int clickCount, int x, int y);

	public abstract void verifyElementFound();

	public abstract void verifyElementNotFound();

	public abstract void waitForElement(int timeout);

	public abstract void waitForElementToVanish(int timeout);

}