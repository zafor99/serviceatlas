package framework.pfstoolscontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.pfstoolsmodel.LoginDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 09, 2013
 */
public  class LoginDialogController extends PFSControllerBase
{
	private static  Logger logger = Logger.getLogger(LoginDialogController.class); 
	public LoginDialogModel appModel = new LoginDialogModel();
	public LoginDialogController(){
		
	}
	
	public void login(){
		logger.info("login()");
		appModel.userIDTextBox().setText("bntestmonroe");
		appModel.passwordTextBox().setText("password");
		appModel.okButton().click();
		delayFor(3);
		
	}

}
