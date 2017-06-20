package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import utils.BNTimer;
import framework.AtlasScriptbase;
import framework.sapmodel.SAPBNEmailSystemDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 01, 2013
 */
public  class SAPBNEmailSystemDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPBNEmailSystemDialogController.class);
	private static SAPBNEmailSystemDialogModel appModel = new SAPBNEmailSystemDialogModel();
	
	public SAPBNEmailSystemDialogController(){
		super(appModel.dialog());		
	}
	
	public boolean verifyEmailhistoryTable(String vpName){
		logger.info("verifyPurchaseHistoryTable("+vpName+")");
		boolean result = false;
		appModel.emailHistoryGrid().getProperty("RowCount");
		ITestData testData = appModel.emailHistoryGrid().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	public boolean verifyEmailhistoryTable(String vpName,int expectedEmailCount){
		logger.info("verifyPurchaseHistoryTable("+vpName+")");
		BNTimer timer = new BNTimer();
		timer.start();
		ITestData testData  = null;
		boolean result = false;
		boolean result2 = false;
		int count = (Integer) appModel.emailHistoryGrid().getProperty("RowCount");
		if(count!=expectedEmailCount){
			do{
				retrieveEmailHistory();
				count = (Integer) appModel.emailHistoryGrid().getProperty("RowCount");
				if(count==expectedEmailCount){
					testData = appModel.emailHistoryGrid().getTestData("list");
					result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
					result2 = true;
				}
				if(timer.getElapsedTime()>=12000){
					RationalTestScript.logError("Unable to Find ZWARRANTY_UPDATE in 6 mins");
					break;
				}
			}while(result2 ==false);
		}
		else{
			testData = appModel.emailHistoryGrid().getTestData("list");
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		}
		
		return result;
	}
	public void retrieveEmailHistory(){
		logger.info("retrieveEmailHistory()");
		appModel.retrieveEmailHistoryButton().click();
		delayFor(3);
		
	}
}
