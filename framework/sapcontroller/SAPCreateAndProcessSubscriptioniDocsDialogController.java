package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPCreateAndProcessSubscriptioniDocsDialogModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 15, 2014
 */
public  class SAPCreateAndProcessSubscriptioniDocsDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCreateAndProcessSubscriptioniDocsDialogController.class);
	private static SAPCreateAndProcessSubscriptioniDocsDialogModel appModel = new SAPCreateAndProcessSubscriptioniDocsDialogModel();
	
	public SAPCreateAndProcessSubscriptioniDocsDialogController(){
		super(appModel.dialog());		
	}
	public void createSubscriptioniDocs(String billingDate,String contractNumber){
		logger.info("createIncomingInvoice("+billingDate+"),("+contractNumber+")");
		appModel.billingDateTextBox().setText(billingDate);
		appModel.contractTextBox().setText(contractNumber);
		appModel.testModeCheckBox().click();
		appModel.printSelectionCheckBox().click();
		appModel.createAndProcessSubcriptioniDocsToolbar().executeButton().click();
		delayFor(10);
	}
	public boolean  verifyContractsProcessed(String vpName){
		//logger.info("verifycontractsProcessed("+vpName+")");
		boolean result = false;
		ITestData testData = null;
			testData= appModel.contractsProcessedGrid().getTestData("list");
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
	public void waitForStatus53(){
		String status = null;
		int getTime = 0;
		BNTimer timer = new BNTimer();
		//status =appModel.contractsProcessedGrid().getColumnName("Status");
		
		status =appModel.contractsProcessedGrid().getCellValue(0, "STATUS");
		
		
		
		timer.start();
		delayFor(5);
		do{
			getTime = timer.getElapsedTime();
			appModel.createAndProcessSubcriptioniDocsToolbar().refreshStatusButton().click();
			status =appModel.contractsProcessedGrid().getCellValue(0, "STATUS");
			if(status.contains("53")){
				break;
			}
			else if(getTime>= 240){
				logger.info("status is stuck at 64 " + getTime );
				timer.stop();
				break;
			}

			delayFor(10);
		}
		while(status.contains("64"));
		
		
	}

}
