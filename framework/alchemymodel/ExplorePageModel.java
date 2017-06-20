package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class ExplorePageModel extends DeviceModelBase
{
	private Client client = null;
	public ExplorePageModel(Client client) {
		super(client);
		this.client = client;
		System.out.println(client.getDevicesInformation());
		// TODO Auto-generated constructor stub
	}
	//TODO Insert shared functionality here
	
	public ElementObject signInButton(){
		ElementObject button = null;
		if(isIOS()){
			button = new ElementObject(client, "NATIVE", "xpath=//*[@accessibilityLabel='SIGN IN']", 0);
		}
		else if (isAndroid()) {
			button = new ElementObject(client, "NATIVE", "text=LOG IN", 0);
		}	
		return button;
	}
	
	public ElementObject exploreTheAppButton(){
		return new ElementObject(client, "NATIVE", "xpath=//*[@text='Explore the app']", 0);
	}
	
	
}
