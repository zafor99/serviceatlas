package framework.mswallettoolcontroller;

import com.rational.test.ft.script.RationalTestScript;

import framework.mswallettoolmodel.MSWalletToolModel;


/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 19, 2014
 */
public class MSWalletToolController extends MSWalletToolControllerBase
{
	//TODO Insert shared functionality herepublic MSWalletToolModel msWalletToolModel = null;
	public MSWalletToolModel msWalletToolModel = null;
	public MSWalletToolController()
	{
		msWalletToolModel = new MSWalletToolModel();
	}
	
	public void startApp()
	{
		//startApp("ms-wallet-tool-1.14.59-executable");
		startApp("ms-wallet-tool-1.14.86-executable");
		getExecutingScript().delayFor(10);
	}
	public void closeApp(){
		msWalletToolModel.mswalletMainWindow().close();
		getExecutingScript().delayFor(5);
	}
	public void waitForExistence()
	{
		msWalletToolModel.mswalletMainWindow().waitForExistence(30, 2);
	}
	public void selectEnvironment(String env)
	{
		msWalletToolModel.environmentComboBox().select(env.toLowerCase());
		getExecutingScript().delayFor(10);
	}
	
	public void selectClientType(String clientType)
	{
		msWalletToolModel.clientTypeComboBox().select(clientType);
		getExecutingScript().delayFor(2);
	}
	public void selectUsername(String username)
	{
		msWalletToolModel.usernameComboBox().setText("");
		msWalletToolModel.usernameComboBox().select(username);
		getExecutingScript().delayFor(2);
	}
	public void setUsername(String username)
	{
		msWalletToolModel.usernameComboBox().setText("");
		msWalletToolModel.usernameComboBox().setText(username);
		getExecutingScript().delayFor(1);
	}
	public void setPassword(String password)
	{
		msWalletToolModel.passwordTextBox().setText(password);
		getExecutingScript().delayFor(1);
	}
	public void setCountry(String country)
	{
		msWalletToolModel.countryTextBox().setText(country);
		getExecutingScript().delayFor(1);
	}
	public void setLocale(String locale)
	{
		msWalletToolModel.localeTextBox().setText(locale);
		getExecutingScript().delayFor(1);
	}
	
	public void clickRequestCodeButton()
	{
		msWalletToolModel.requestCodeButton().click();
		getExecutingScript().delayFor(8);
	}
	public void clickAccessTokenButton()
	{
		msWalletToolModel.accessTokenButton().click();
		getExecutingScript().delayFor(8);
	}
	public void clickOfflineTokenButton()
	{
		msWalletToolModel.offlineTokenButton().click();
		getExecutingScript().delayFor(5);
	}
	public void clickManageDefaultPaymentButton()
	{
		msWalletToolModel.manageDefaultPaymentInstrumentButton().click();
		getExecutingScript().delayFor(5);
	}
	
	public String getRequestCode()
	{
		String requestCode = "";
		requestCode = msWalletToolModel.requestCodeTextBox().getProperty("text").toString();
		return requestCode;
	}
	
	
	public String getAccessToken()
	{
		String accessToken = "";
		accessToken = msWalletToolModel.accessCodeTextBox().getProperty("text").toString();
		return accessToken;
	}
	public String getOfflineToken()
	{
		String offlineToken = "";
		offlineToken = msWalletToolModel.offlineTokenTextBox().getProperty("text").toString();
		return offlineToken;
	}
	
	public void enterCredentials(String env, String clientType, String username, String password, String country, String locale)
	{
		selectEnvironment(env.toLowerCase());
		selectClientType(clientType);
		setUsername(username);
		setPassword(password);
		setCountry(country);
		setLocale(locale);
	}
	public void enterCredentials(String env, String clientType, String username, String password)
	{
		selectEnvironment(env);
		selectClientType(clientType);
		setUsername(username);
		setPassword(password);
		
	}

}
