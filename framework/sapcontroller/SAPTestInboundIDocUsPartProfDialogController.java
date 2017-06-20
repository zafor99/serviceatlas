package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPInformationDialogModel;
import framework.sapmodel.SAPTestInboundIDocUsPartProfDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 15, 2014
 */
public  class SAPTestInboundIDocUsPartProfDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPTestInboundIDocUsPartProfDialogController.class);
	private static SAPTestInboundIDocUsPartProfDialogModel appModel = new SAPTestInboundIDocUsPartProfDialogModel();
	
	public SAPTestInboundIDocUsPartProfDialogController(){
		super(appModel.dialog());
	}
	
	public void clickOkButton(){
		logger.info("clickCancelButton()");
		appModel.okButton().click();
		delayFor(3);
		
		
	}
}
