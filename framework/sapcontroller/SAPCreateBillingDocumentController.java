package framework.sapcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.internal.selenesedriver.SendKeys;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPCreateBillingDocumentModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 19, 2013
 */
public  class SAPCreateBillingDocumentController extends SAPDialogController
{
	public static Logger logger =Logger.getLogger(SAPCreateBillingDocumentController.class);
	public static SAPCreateBillingDocumentModel appModel = new SAPCreateBillingDocumentModel();
	
	public SAPCreateBillingDocumentController(){
		super(appModel.dialog());
	}
	public boolean verifyBillingDocStatus(String vpName){
		logger.info("verifyDeviceDeliveryStatus("+vpName+")");
		boolean result = false;
		String actual = getStatusBarText();
		String BillingDocNumber = actual.split(" ")[1];
		
		String baseline = "Document " + BillingDocNumber + " has been saved";
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, baseline,actual).performTest();
		
		
		return result;
	}
	public boolean verifyDocsToBeProcessedTable(String vpName){
		logger.info("verifyDocsToBeProcessedTable("+vpName+")");
		boolean result = false;
		String[][] data = appModel.docsToBeProcessedTable().getTableTextData();
		ITestData testData = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
	
	public void enterDocumentNumber(String documentNumber){
		logger.info("enterDocumentNumber("+documentNumber+")");
		appModel.docsToBeProcessedTable().getCellTestObject("Document", 0).click();
		appModel.docsToBeProcessedTable().getCellTestObject("Document", 0).setProperty("Text", documentNumber);
		
	//	appModel.docsToBeProcessedTable().getCellTestObject("Document", 0).;
		TestObject tes = new GuiTestObject(appModel.docsToBeProcessedTable().getCellTestObject("Document", 0));
		appModel.dialog().inputKeys("{Enter}");
		delayFor(2);
		
	}
}

