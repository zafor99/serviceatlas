package framework.sapcontroller;

import org.apache.log4j.Logger;
import framework.sapmodel.SAPCallingiDocTestToolDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 15, 2014
 */
public  class SAPCallingiDocTestToolDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCallingiDocTestToolDialogController.class);
	private static SAPCallingiDocTestToolDialogModel appModel = new SAPCallingiDocTestToolDialogModel();

	public SAPCallingiDocTestToolDialogController(){
		super(appModel.dialog());
	}

	public void clickOkButton(){
		logger.info("clickCancelButton()");
		appModel.okButton().click();
		delayFor(3);


	}
}
