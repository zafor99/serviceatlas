package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import framework.AtlasScriptbase;
import framework.sapmodel.SAPRQ1UserLoginDialogModel;
import framework.sapmodel.SAPSession;
import framework.sapmodel.SAPUserLoginDialogModel;
import utils.EnvironmentUtility;


/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  August 01, 2012
 */
public class SAPUserLoginDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPUserLoginDialogController.class);
	private static SAPRQ1UserLoginDialogModel appModel = new SAPRQ1UserLoginDialogModel();
	
	public SAPUserLoginDialogController(){		
		super(appModel.dialog());
	}
	
	public void logIn(){
		logger.info("logIn");
		boolean result = false;
		//if(userName!=null && !userName.contentEquals("!")){
		appModel.userTextBox().setText(EnvironmentUtility.sap().userName());
		//}

		//if(password!=null && !password.contentEquals("!")){
		appModel.passwordTextBox().setText(EnvironmentUtility.sap().passWord());
		//}

		AtlasScriptbase.getExecutingScript().delayFor(3);
		SAPSession.getActiveWindow().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		//return result;

		AtlasScriptbase.getExecutingScript().sap().easyAccessDialog().waitForExistence();
	}
	
	public boolean logIn(String userName, String password){
		logger.info("logIn("+userName+","+password+")");
		boolean result = false;
		if(userName!=null && !userName.contentEquals("!")){
			appModel.userTextBox().setText(userName);
		}
		
		if(password!=null && !password.contentEquals("!")){
			appModel.passwordTextBox().setText(password);
		}
		
		AtlasScriptbase.getExecutingScript().delayFor(3);
		SAPSession.getActiveWindow().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		AtlasScriptbase.getExecutingScript().sap().easyAccessDialog().waitForExistence();
		return result;
	}
}
