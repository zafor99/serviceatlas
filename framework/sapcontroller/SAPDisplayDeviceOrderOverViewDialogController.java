package framework.sapcontroller;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Isnumber;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDisplayDeviceOrderOverViewDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * 
 * @author fahmed1
 * @since  May 17, 2013
 */
public class SAPDisplayDeviceOrderOverViewDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDisplayDeviceOrderOverViewDialogController.class);
	private static SAPDisplayDeviceOrderOverViewDialogModel appModel = new SAPDisplayDeviceOrderOverViewDialogModel();

	public SAPDisplayDeviceOrderOverViewDialogController(){
		super(appModel.dialog());
	}
	public SalesTab salesTab(){
		return new SalesTab();
	}
	public void Save(){
		logger.info("Save()");
		selectMenuItem(atPath("Sales document->Save"));
		delayFor(3);
	}
	
	
	public void deliver(){
		logger.info("deliver");
		selectMenuItem(atPath("Sales document->Deliver"));
		delayFor(3);
	}
	
	public void openDisplayDocumentFlowDialog(){
		logger.info("openDisplayDocumentFlowDialog()");
		appModel.toolBar().displayDocumentFlowButton().click();
		delayFor(3);
	}

	public void openDisplayDocHeaderDeatailsDialog(){
		logger.info("openDisplayDocumentFlowDialog()");
		appModel.displayDocHeaderDetailsButton().click();
		delayFor(3);
	}
	public String getBNSubscription(){
		logger.info("getBNSubscription()");
		String contract = null;
		contract = appModel.bnSubscriptionTextBox().getText();
		return contract;
	}
	public String getSAPOrderNumber(){
		logger.info("getSAPOrderNumber()");
		return getBNSubscription();
	}
	public void openFirstArticle(){
		logger.info("openDisplayDocumentFlowDialog()");
		appModel.firstArticleItem().doubleClick();
		delayFor(2);
	}

	public void selectBillingBlock(String billingBlockText){
		logger.info("updateBillingBlock("+billingBlockText+")");
		appModel.billingBlockComboBox().setKey(billingBlockText);
		delayFor(2);
	}
	public void clickScheduleLinesForItem(){
		logger.info("clickScheduleLinesForItem()");
		appModel.scheduleLinesForItemButton().click();
		delayFor(3);
	}
	public class SalesTab{
		public SalesTab(){
			
		}
		public boolean  verifyAllItemsTable(String vpName){
			logger.info("verifyAllItemsTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			
			boolean isActive = (Boolean) appModel.salesTab().tab().getProperty("Changeable");

			
			
			if(isActive){
				testData = appModel.salesTab().allItemsTable().getTestData("list");
				
			}
			else{
				appModel.salesTab().tab().click();
				testData = appModel.salesTab().allItemsTable().getTestData("list");	
			}
		
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
		public void openFirstMaterial(){
			logger.info("openFirstMaterial()");
			TestObject [] tos = null;
			GuiTestObject cell = null;
			tos =  appModel.salesTab().allItemsTable().findAllByName("RV45A-MABNR", "GuiCTextField");
			if(tos!=null){
				cell = ((GuiTestObject) tos[0]);
				cell.doubleClick();
			}

		}
	}
	
}
