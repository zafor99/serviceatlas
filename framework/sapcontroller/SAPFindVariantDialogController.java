package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPFindVariantDialogModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 04, 2014
 */
public  class SAPFindVariantDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPFindVariantDialogController.class);
	private static SAPFindVariantDialogModel appModel = new SAPFindVariantDialogModel();
	
	public SAPFindVariantDialogController(){
		super(appModel.dialog());		
	}
	public void findVariant(String variant){
		logger.info("findVariant("+variant+")");		
		appModel.variantTextBox().setText(variant);
		appModel.createByTextBox().setText("");
		appModel.executeButton().click();
		delayFor(2);
		
	}

}
