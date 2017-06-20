package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPCustomerInteractionCenterDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPCustomerInteractionCenterDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCustomerInteractionCenterDialogController.class);
	private static SAPCustomerInteractionCenterDialogModel appModel = new SAPCustomerInteractionCenterDialogModel();
	
	
	public SAPCustomerInteractionCenterDialogController(){
		super(appModel.dialog());
	}	
	
	public void selectOrderSearchTab(){
		logger.info("selectOrderSearchTab()");
		appModel.orderSearchTab().tab().select();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	public void selectDeviceSearchTab(){
		logger.info("selectDeviceSearchTab()");
		appModel.deviceSearchTab().tab().select();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}

	
	public void clickOrderSearchFindButton(){
		logger.info("clickOrderSearchFindButton");
		appModel.orderSearchTab().findButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}

	public void clickDeviceSearchFindButton(){
		logger.info("clickDeviceSearchFindButton");
		appModel.deviceSearchTab().findButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}

	public void goToDisplayChangeOrderDialog(){
		logger.info("goToDisplayChangeOrderDialog");
		appModel.mainTab().tab().select();
		appModel.mainTab().dispLayButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}

	public void goToiDocReportingToolDialog(){
		logger.info("goToDisplayChangeOrderDialog");
		appModel.mainTab().tab().select();
		appModel.mainTab().aloc1Button().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	public void goToMercuryAccountProfileDialog(){
		logger.info("goToDisplayChangeOrderDialog");
		appModel.mainTab().tab().select();
		appModel.mainTab().mapButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	public void goToDispSAPSalesOrderDialog(){
		logger.info("goToDisplayChangeOrderDialog");
		appModel.mainTab().tab().select();
		appModel.mainTab().va03Button().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}

}
