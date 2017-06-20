package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPCancelSubscriptionDialogModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  July 10, 2014
 */
public  class SAPCancelSubscriptionDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCancelSubscriptionDialogController.class);
	private static SAPCancelSubscriptionDialogModel appModel = new SAPCancelSubscriptionDialogModel();
	
	public SAPCancelSubscriptionDialogController(){
		super(appModel.dialog());
	}
	public void selectCancelReason(String cancelReason){
		logger.info("selectCancelReason("+cancelReason+")");
		//appModel.cancelReasonComboBox().click();
		appModel.cancelReasonComboBox().setValue(cancelReason);
	}
	public void update(){
		logger.info("update()");
		appModel.updateButton().click();
	}

}
