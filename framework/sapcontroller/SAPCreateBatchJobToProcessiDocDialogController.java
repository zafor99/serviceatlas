package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPCreateBatchJobToProcessiDocDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  July 01, 2014
 */
public  class SAPCreateBatchJobToProcessiDocDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCreateBatchJobToProcessiDocDialogController.class);
	private static SAPCreateBatchJobToProcessiDocDialogModel appModel = new SAPCreateBatchJobToProcessiDocDialogModel();
	
	public SAPCreateBatchJobToProcessiDocDialogController(){
		super(appModel.dialog());		
	}
	public void createBatchtoProcessiDoc(String iDocNumber){
		logger.info("createBatchtoProcessiDoc("+iDocNumber +")");
		appModel.iDocNumberTextBox().setText(iDocNumber);
		if(isTestModeCheck()){
			appModel.testModeCheckBox().click();	
		}
		appModel.createBatchJobDialogToolbar().executeButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(3);
		if(AtlasScriptbase.getExecutingScript().sap().informationDialog().waitForExistence(5)){
			AtlasScriptbase.getExecutingScript().sap().informationDialog().clickOkButton();
			delayFor(3);
			logger.info(iDocNumber + "is already processed.");
		}
		else{
			waitFor53Status();
		}
			
	}
	public boolean isTestModeCheck(){
		boolean isChecked = false;
		isChecked = (Boolean) appModel.testModeCheckBox().getProperty("Selected");
		return isChecked;
	}
	public boolean  verifyBatchJobGrid(String vpName){
		logger.info("verifyBatchJobGrid("+vpName+")");
		boolean result = false;
		ITestData testData = appModel.batchJobGridView().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	public String getiDocStatus(){
		logger.info("getiDocStatus()");
		String iDocStatus = null;
		iDocStatus = appModel.batchJobGridView().getCellValue(0, "STATUS");
		
		return iDocStatus;
	}
	
	public void refreshStatus(){
		logger.info("refreshStatus()");
		appModel.createBatchJobDialogToolbar().refreshStatusButton().click();
		
	}
	public void waitFor53Status(){
		logger.info("waitFor53Status()");
		String iDocStatus = null;
		int getTime = 0;
		BNTimer timer = new BNTimer();
		iDocStatus = getiDocStatus();
		timer.start();
		delayFor(5);
		do{
			getTime = timer.getElapsedTime();
			refreshStatus();
			iDocStatus = getiDocStatus();
			if(iDocStatus.contains("53")){
				break;
			}
			if(iDocStatus.contains("51")){
				RationalTestScript.logWarning("Status is 51");
			}
			else if(getTime>= 240){
				logger.info("status is stuck at 64 " + getTime );
				timer.stop();
				break;
			}
			
			
		}
		while(iDocStatus.contains("64"));
	}
}

