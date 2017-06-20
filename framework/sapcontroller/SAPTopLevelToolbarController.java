package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPSession;
import framework.sapmodel.SAPTopLevelToolbarModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public  class SAPTopLevelToolbarController extends SAPControllerBase
{
	private static Logger logger = Logger.getLogger(SAPTopLevelToolbarController.class);
	private SAPTopLevelToolbarModel appModel = null;
	private SAPTopLevelTestObject dialog = null;
	
	public SAPTopLevelToolbarController(){
		this.dialog = dialog;
		appModel = new SAPTopLevelToolbarModel();
	}
	
	public void refresh(){
		logger.info("refresh");
		appModel.refreshButton().click();
		delayFor(5);
	}
	
	private boolean isCommandFieldOpen(){
		logger.info("isCommandFieldOpen");
		String commandTooTip = appModel.commandFieldButton().getProperty("Tooltip").toString();
		String toolTip = "commandTooTip";
		System.out.println("Tooltop Text : "+ toolTip);
		boolean open = false;
		if(commandTooTip.contentEquals("Close Command Field")){
			open = true;
		}
		return open;
	}
	
	public void enterCommand(String command){
		logger.info("enterCommand("+command+")");
		if( !isCommandFieldOpen())
		{
			appModel.commandFieldButton().click();
		}
		appModel.commandFieldComboTextBox().setFocus();
		appModel.commandFieldComboTextBox().setText(command);
		//dialog.inputKeys("{Enter}");
		SAPSession.getActiveWindow().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
	
	}
	
	public void openCommandField(){
		logger.info("openCommandField");
		if( isCommandFieldOpen()){
			logInfo("Command Field is already Open");
		}
		else{
			appModel.commandFieldButton().click();
		}
	}
	
	public void closeCommandField(){
		logger.info("closeCommandField");
		if( !isCommandFieldOpen()){
			logInfo("Command Field is already Close");
		}
		else{
			appModel.commandFieldButton().click();
		}
	}
	
	public void exit(){
		logger.info("exit");
		appModel.exitButton().click();
	}
	
	public void navigateBack(){
		logger.info("navigateBack");
		appModel.backButton().click();
		delayFor(5);
	}
	public void save(){
		logger.info("save");
		appModel.saveButton().click();
		delayFor(5);
	}
	
	
}
