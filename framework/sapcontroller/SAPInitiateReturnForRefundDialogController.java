package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.impl.VPManager;


import framework.AtlasScriptbase;
import framework.sapmodel.SAPInitiateReturnForRefundDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 01, 2013
 */
public  class SAPInitiateReturnForRefundDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPInitiateReturnForRefundDialogController.class);
	private static SAPInitiateReturnForRefundDialogModel appModel = new SAPInitiateReturnForRefundDialogModel();
	
	public SAPInitiateReturnForRefundDialogController(){
		super(appModel.dialog());		
	}
	public void selectCustomerReturnReasonCode(String reasonCode){
		logger.info("selectCustomerReturnReasonCode()");
		appModel.customerReturnReasonCodeComboBox().setValue(reasonCode);
	}
	public void update(){
		logger.info("update()");
		appModel.updateButton().click();
		delayFor(10);
	}
	public void close(){
		logger.info("close()");
		appModel.closeButton().click();
		delayFor(3);
		
	}

	public boolean verifyCustomerReturnReasonCode(String vpName){
		logger.info("selectCustomerReturnReasonCode("+vpName+")");
		boolean result = false;
		String reasonCode  = appModel.customerReturnReasonCodeComboBox().getProperty("Text").toString();
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, reasonCode).performTest();
		return result;
		
	}
	
	
}
