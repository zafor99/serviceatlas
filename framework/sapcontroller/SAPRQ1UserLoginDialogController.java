package framework.sapcontroller;

import org.apache.log4j.Logger;

import framework.sapmodel.SAPRQ1UserLoginDialogModel;

public class SAPRQ1UserLoginDialogController extends SAPControllerBase{
	
	private static Logger logger = Logger.getLogger(SAPRQ1UserLoginDialogController.class);
	private SAPRQ1UserLoginDialogModel model = null;
	
	public SAPRQ1UserLoginDialogController(){
		model = new SAPRQ1UserLoginDialogModel();
	}
	
	public boolean waitForExistence(){
		boolean result = false;

		model.dialog().waitForExistence(120, 5);
		if(model.dialog().exists()){
			result = true;
		}
		return result;
	}
	
	public boolean logIn(String userName, String password){
		boolean result = false;
		model.dialog().activate();
		if(userName!=null && !userName.contentEquals("!")){
			model.userTextBox().click();
			model.dialog().inputKeys(userName);
			model.dialog().inputKeys("{Tab}");
		}
		
		if(password!=null && !password.contentEquals("!")){
			model.passwordTextBox().click();
			model.dialog().inputKeys(password);
			model.dialog().inputKeys("{Tab}");
		}
		
		model.dialog().inputKeys("{Enter}");
		return result;
	}

}
