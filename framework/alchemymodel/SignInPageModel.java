package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class SignInPageModel extends DeviceModelBase
{

	private Client client = null;
	public SignInPageModel(Client client) {
		super(client);
		this.client = client;
		
	}
	
	public ElementObject emailAddressTextBox(){
		ElementObject textBox = null;
		if(isIOS()){
			textBox = new ElementObject(client, "NATIVE",  "placeholder=Email Address", 0);
		}
		else if (isAndroid()){
			textBox = new ElementObject(client, "NATIVE", "xpath=//*[@hint='Email']", 0);
		}
		return textBox;
	}
	
	public ElementObject passwordTextBox(){
		ElementObject textBox = null;
		if(isIOS()){
			textBox = new ElementObject(client, "NATIVE", "placeholder=Password", 0);
		}
		else if (isAndroid()){
			textBox = new ElementObject(client, "NATIVE", "xpath=//*[@hint='Password']", 0);
		}		
		return textBox;
	}
	
	public ElementObject forgetPasswordLink(){
		ElementObject object = null;
		if(isIOS()){
			object = new ElementObject(client, "NATIVE", "xpath=//*[@text='Forgot Password?']", 0);
		}
		else if (isAndroid()){
			object = new ElementObject(client, "NATIVE", "xpath=//*[@text='Forgot Password?']", 0);
		}		
		return object;
	}
	
	public ElementObject nextButton(){
		ElementObject object = null;
		if(isIOS()){
			object = null;
		}
		else if (isAndroid()){
			object = new ElementObject(client, "NATIVE", "xpath=//*[@text='Next']", 0);
		}		
		return object;
	}
	
	public ElementObject signinButton(){
		ElementObject object = null;
		if(isIOS()){
			object = new ElementObject(client, "NATIVE", "text=Sign In", 0);
		}
		else if (isAndroid()){
			object = new ElementObject(client, "NATIVE", "xpath=//*[@text='Sign In']", 0);
		}		
		return object;
	}
}
//xpath=//*[@accessibilityLabel='Password' and @class='UITextFieldLabel']