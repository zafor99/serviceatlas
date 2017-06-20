package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPEDIOrderManagementMainMenuDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 12, 2013
 */
public  class SAPEDIOrderManagementMainMenuDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPEDIOrderManagementMainMenuDialogController.class);
	private static SAPEDIOrderManagementMainMenuDialogModel appModel = new SAPEDIOrderManagementMainMenuDialogModel();
	
	public SAPEDIOrderManagementMainMenuDialogController(){
		super(appModel.dialog());
	}
	public void process(){
		logger.info("process");
		appModel.processButton().click();
		delayFor(5);
	}
	
	public void selectOrderWorkList(){
		logger.info("selectOrderWorkList");
		appModel.orderWorkListButton().click();
		delayFor(1);
	}
}
