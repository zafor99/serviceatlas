package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class ListElementObject extends ElementObject implements IListElementObject 
{

	private Client client = new Client("localhost", 8889);
	private String zone = null;
	private String element = null;
	private int index;
	
	public ListElementObject(Client client, String zone, String element,int index) {
		
			super(client, zone, element, index);
			this.client = client;
			this.zone = zone;
			this.element = element;
			this.index = index;

	}
	
	public ListElementObject(String zone, String element,int index) {
		
		super(zone, element, index);
		this.client = client;
		this.zone = zone;
		this.element = element;
		this.index = index;

}


	/* (non-Javadoc)
	 * @see framework.alchemymodel.IListElementObject#elementListPick(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void elementListPick(String elementZone, String elementLocator, boolean click){
		client.elementListPick(zone, element, elementZone, elementLocator, index, click);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IListElementObject#elementListSelect(java.lang.String, int, boolean)
	 */
	@Override
	public void elementListSelect(String elementLocator, int index, boolean click){
		client.elementListSelect(element, elementLocator, index, click);
	}
	
	/* (non-Javadoc)
	 * @see framework.alchemymodel.IListElementObject#elementListVisible(java.lang.String, int)
	 */
	@Override
	public boolean elementListVisible(String elementLocator, int index){
		return client.elementListVisible(element, elementLocator, index);
	}
}
