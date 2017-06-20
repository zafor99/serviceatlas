package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPJobOverviewDialogModel;
import framework.sapmodel.SAPModelBase;
import framework.sapmodel.SAPStartTimeDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 12, 2013
 */
public  class SAPStartTimeDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPStartTimeDialogController.class);
	private static SAPStartTimeDialogModel appModel = new SAPStartTimeDialogModel();
	
	public SAPStartTimeDialogController(){
		super(appModel.dialog());
	}
	public void clickImmediate(){
		logger.info("clickImmediate()");
		appModel.immediateButton().click();
		delayFor(1);
	}
	public void save(){
		logger.info("save()");
		appModel.saveButton().click();
	}
}
