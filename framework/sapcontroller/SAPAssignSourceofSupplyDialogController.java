package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPAssignSourceofSupplyDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 20, 2013
 */
public  class SAPAssignSourceofSupplyDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPAssignSourceofSupplyDialogController.class);
	private static SAPAssignSourceofSupplyDialogModel appModel = new SAPAssignSourceofSupplyDialogModel();
	
	public SAPAssignSourceofSupplyDialogController(){
		super(appModel.dialog());
	}
	public void selectEAN(){
		logger.info("selectEAN()");
		appModel.eanCheckBox().setFocus();
	}
	public void clickAssignAutomatically(){
		logger.info("clickAssignAutomatically()");
		appModel.assignAutomaticallyButton().click();
		delayFor(3);
	}
	
	
	public boolean verifySourceOverviewTable(String vpName){
		logger.info("verifyItemSummaryTable("+vpName+")");
		boolean result = false;
		String[][] data = appModel.sourceOverviewTable().getTableTextData();
		ITestData testData = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
	public void selectInfobyVendor(String vendorID){
		logger.info("selectInfobyVendor()");
		int rowcount = appModel.sourceOverviewTable().getRowCount();
		for(int i=0;i<rowcount;i++){
			System.out.println(appModel.sourceOverviewTable().getCellText(0, i).toString());
			if(appModel.sourceOverviewTable().getCellText(0, i).contentEquals(vendorID)){
				appModel.sourceOverviewTable().getCellTestObject(2, i).doubleClick();
				delayFor(2);
			}
		}	
	}
	public void selectInfobyInfoID(String infoID){
		logger.info("selectInfobyVendor()");
		int rowcount = appModel.sourceOverviewTable().getRowCount();
		for(int i=0;i<rowcount;i++){
			System.out.println(appModel.sourceOverviewTable().getCellText(2, i).toString());
			if(appModel.sourceOverviewTable().getCellText(2, i).contentEquals(infoID)){
				appModel.sourceOverviewTable().getCellTestObject(2, i).doubleClick();
				delayFor(2);
				break;
			}
		}	
	}

	public boolean verifyInfoRecLabel(String vpName){
		logger.info("verifyInfoRecLabel("+vpName+")");
		boolean result = false;
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, appModel.inforecLabel().getProperty("Text").toString()).performTest();
		
		
		return result;
	}
	
}
