package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class ElementObject extends Client implements IElementObject
{
	private static  Client client = Device.client;
	private  String zone = null;
	private  String element = null;
	private  int index;
	
	public ElementObject(Client client, String zone, String element, int index ){
		this.client = client;
		this.zone = zone;
		this.element = element;
		this.index = index;
	}
	
	public ElementObject(String zone, String element, int index ){
		this.zone = zone;
		this.element = element;
		this.index = index;
	}
		
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#click()
	 */
	@Override
	public void click(){
		client.click(zone, element, 0, 1);
	}
		
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#click(int)
	 */
	@Override
	public void click(int count){
		client.click(zone, element, index, count);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#click(int, int, int)
	 */
	@Override
	public void click(int x, int y, int clickCount){
		client.click(zone, element, index, clickCount, x, y);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#clickIn(java.lang.String, java.lang.String, int, java.lang.String, int, int, int)
	 */
	@Override
	public void clickIn(String searchZone, String searchElement, int x, String direction, int width, int height, int clickCount){
		client.clickIn(searchZone, searchElement, x, direction, zone, element, index, width, height, clickCount);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#clickIn(java.lang.String, java.lang.String, int, java.lang.String, int, int)
	 */
	@Override
	public void clickIn(String searchZone, String searchElement, int index, String direction, int width, int height){
		client.clickIn(searchZone, searchElement, index, direction, zone, element, width, height);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#longClick(int, int, int)
	 */
	@Override
	public void longClick(int x, int y, int clickCount){
		client.longClick(zone, element, index, clickCount, x, y);
	}
		
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#sendText(java.lang.String)
	 */
	@Override
	public void sendText(String text){
		client.elementSendText(zone, element, index, text);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#sendProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendProperty(String property, String value){
		client.elementSetProperty(zone, element, index, property, value);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#swip(java.lang.String, int, int)
	 */
	@Override
	public void swip(String direction,int offset, int time){
		client.elementSwipe(zone, element, index, direction, offset, time);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getAllValues(java.lang.String)
	 */
	@Override
	public String[] getAllValues(String property){
		return client.getAllValues(zone, element, property);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getAllZonesWithElement()
	 */
	@Override
	public String getAllZonesWithElement(){
		return client.getAllZonesWithElement(element);
	}
	
	public ElementObject getElementObject(String zone, String element, int index){
		
		String xpath = this.element + element;
		ElementObject elementObj = new ElementObject(zone, xpath, index);
		
		return elementObj;
	}
	
	public ElementObject[] getElementObjects(String zone, String element, int index){
		

		ElementObject[] contents = null;
		int count = 0;
		String xpath = this.element + element;
		count = client.getElementCount("NATIVE", xpath);
		contents = new ElementObject[count];
		xpath = xpath.substring(xpath.indexOf("=")+1, xpath.length());
		xpath = "xpath=" + xpath;
		
		for(int i=0; i<count;i++){
			contents[i] = new ElementObject("NATIVE", xpath + "[" + (Integer.valueOf(i)+1) + "]" ,0);
		}
		return contents;
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getElementCount()
	 */
	@Override
	public int getElementCount(){
		return client.getElementCount(zone, element);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getProperty(java.lang.String)
	 */
	@Override
	public String getProperty(String property){
		return client.elementGetProperty(zone, element, index, property);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getText()
	 */
	@Override
	public String getText(){
		return client.elementGetText(zone, element, index);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getTextIn(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public String getTextIn(String textZone, String direction, int width, int height){
		return client.getTextIn(zone, element, index, textZone, direction, width, height);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#getTextIn(java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public String getTextIn(String textZone, String direction, int width, int height, int xOffset, int yOffset){
		return client.getTextIn(zone, element, index, textZone, direction, width, height, xOffset, yOffset);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#isElementBlank(int)
	 */
	@Override
	public boolean isElementBlank(int colorGroups){
		return client.isElementBlank(zone, element, index, colorGroups);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#isElementFound()
	 */
	@Override
	public boolean isElementFound(){
		return client.isElementFound(zone, element);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#isElementFound(int)
	 */
	@Override
	public boolean isElementFound(int index){
		return client.isElementFound(zone, element, index);		
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#rightClick(int, int, int)
	 */
	@Override
	public void rightClick(int clickCount, int x, int y ){
		client.rightClick(zone, element, index, clickCount, x, y);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#verifyElementFound()
	 */
	@Override
	public void verifyElementFound(){
		client.verifyElementFound(zone, element, index);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#verifyElementNotFound()
	 */
	@Override
	public void verifyElementNotFound(){
		client.verifyElementNotFound(zone, element, index);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#waitForElement(int)
	 */
	@Override
	public void waitForElement(int timeout){
		client.waitForElement(zone, element, index, timeout);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IElementObject#waitForElementToVanish(int)
	 */
	@Override
	public void waitForElementToVanish(int timeout){
		client.waitForElementToVanish(zone, element, timeout, timeout);
	}
	
	
}
