package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPAutomaticCreationOfPurchaseDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 26, 2013
 */
public  class SAPAutomaticCreationOfPurchaseDialogController  extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPAutomaticCreationOfPurchaseDialogController.class);
	private static 	SAPAutomaticCreationOfPurchaseDialogModel appModel = new SAPAutomaticCreationOfPurchaseDialogModel();
	
	public SAPAutomaticCreationOfPurchaseDialogController(){
		super(appModel.dialog());		
	}
	public boolean  verifyPOCreationGrid(String vpName){
		logger.info("verifyOrderDetailsGrid("+vpName+")");
		boolean result = false;
		ITestData testData = appModel.poCreationGrid().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
}
