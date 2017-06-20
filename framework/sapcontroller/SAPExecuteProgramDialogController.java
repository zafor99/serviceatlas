package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPExecuteProgramDialogModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 22, 2013
 */
public  class SAPExecuteProgramDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPExecuteProgramDialogController.class);
	private static SAPExecuteProgramDialogModel appModel = new SAPExecuteProgramDialogModel();
	
	public SAPExecuteProgramDialogController(){
		super(appModel.dialog());
	}
	public void executeProgramWithVariant(String variantName){
		logger.info("clickCancelButton()");
		appModel.variantTextBox().setText(variantName);
		appModel.okButton().click();
		delayFor(3);
		
		
	}
}
