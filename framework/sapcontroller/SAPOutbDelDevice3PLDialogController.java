package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPOutbDelDevice3PLDialogModel;

public class SAPOutbDelDevice3PLDialogController extends SAPDialogController{
	private static Logger logger = Logger.getLogger(SAPOutbDelDevice3PLDialogController.class);
	private static SAPOutbDelDevice3PLDialogModel appModel = new SAPOutbDelDevice3PLDialogModel();
	public SAPOutbDelDevice3PLDialogController(){
		super(appModel.dialog());
	}
	
	public void clickDisplayChange(){
		logger.info("clickUnderReview()");
		appModel.displayChangeButton().click();
		delayFor(1);
	}
	public void clickProcessGoodIssue(){
		logger.info("clickUnderReview()");
		appModel.postGoodsIssueButton().click();
		delayFor(1);
	}
	public void select1stItem(){
		logger.info("select1stItem()");
		TestObject[] tos = null;
		SAPGuiTextTestObject to = null;
		//tos = appModel.allItemsTree().find(atChild("Id","/txtLIPS-POSNR[0,0]"));
		tos = appModel.allItemsTree().find(atChild("Id",xRegex("/.*\\[" + 0 + "," + 0 + "\\]")));
		if(tos.length>0){
			to = new SAPGuiTextTestObject(tos[0]);
		}
		
		to.click();
		delayFor(2);
		
	}
/*	TestObject[] tos = null;
	GuiTestObject to = null;
	tos = table().find(atChild("Id",xRegex("/.*\\[" + colIndex + "," + rowIndex + "\\]")));
	if(tos.length>0){
		to = new GuiTestObject(tos[0]);
	}
	return to;
	
*/	public boolean  verifyAllItemsTable(String vpName){
		logger.info("verifyConfirmationTable("+vpName+")");
		boolean result = false;
		ITestData testData = appModel.allItemsTree().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
}
