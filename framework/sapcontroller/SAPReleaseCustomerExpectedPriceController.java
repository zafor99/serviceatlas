package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDocumentFlowDialogModel;
import framework.sapmodel.SAPReleaseCustomerExpectedPriceModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 20, 2013
 */
public  class SAPReleaseCustomerExpectedPriceController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPReleaseCustomerExpectedPriceController.class);
	private static SAPReleaseCustomerExpectedPriceModel appModel = new SAPReleaseCustomerExpectedPriceModel();
	
	public SAPReleaseCustomerExpectedPriceController(){
		super(appModel.dialog());
	}
	public boolean verifyDocumentTable(String vpName){
		logger.info("verifyDocumentTable("+vpName+")");
		boolean result = false;
		
		String[][] data = appModel.documentTable().getTableData();
		ITestData testData = VpUtil.getTestData(data);
		
		AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	public boolean verifyReleaseDocumentTable(String vpName){
		logger.info("verifyReleaseDocumentTable("+vpName+")");
		boolean result = false;
		
		String[][] data = appModel.releaseDocumentTable().getTableData();
		ITestData testData = VpUtil.getTestData(data);
		
		AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	public void checkDocument(){
		logger.info("checkDocument()");
		appModel.documentCheckbox().setSelected(true);
	}
	public void release(){
		logger.info("release()");
		appModel.releaseButton().click();
	}
}
