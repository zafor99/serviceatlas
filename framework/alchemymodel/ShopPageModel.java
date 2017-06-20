package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  October 17, 2014
 */
public class ShopPageModel extends DeviceModelBase
{

	public ShopPageModel(Client client) {
		super(client);
		
	}
	
	public ElementObject buyBook(){
		//return new ElementObject("WEB", "xpath=//html/body/div/div/div[3]/div[1]/div/div[2]/div[1]/input[1]", 0);
		return new ElementObject("WEB", "xpath=//*[@id='rows']/div[1]/div/div[2]/div/input[@value='$9.99']", 0);
	}
	
	public ElementObject purchaseOkButton(){
		return new ElementObject("NATIVE", "xpath=//*[@text='OK']", 0);
	}
	
}
