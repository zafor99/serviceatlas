package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDeviceInfoSerialNumberDialogModel;



/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 30, 2013
 */
public  class SAPDeviceInfoSerialNumberDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDeviceInfoSerialNumberDialogController.class);
	private static SAPDeviceInfoSerialNumberDialogModel appModel = new SAPDeviceInfoSerialNumberDialogModel();
	
	public SAPDeviceInfoSerialNumberDialogController(){
		super(appModel.dialog());		
	}
	public boolean  verifyDeviceHistoryTable(String vpName){
		logger.info("verifyPurchaseHistoryTable("+vpName+")");
		boolean result = false;
		ITestData testData = appModel.deviceHistoryGrid().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	public void clickRtrnForRefundButton(){
		logger.info("clickRtrnForRefundButton()");
		appModel.rtrnForRefundButton().click();
		
	}
}
