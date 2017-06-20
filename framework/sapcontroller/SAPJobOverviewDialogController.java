package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPDocumentFlowDialogModel;
import framework.sapmodel.SAPJobOverviewDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 12, 2013
 */
public  class SAPJobOverviewDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPJobOverviewDialogController.class);
	private static SAPJobOverviewDialogModel appModel = new SAPJobOverviewDialogModel();
	
	public SAPJobOverviewDialogController(){
		super(appModel.dialog());
	}
	public void checkFirstJobCheckBox(){
		logger.info("checkFirstJobCheckBox()");
		appModel.firstJobCheckbox().click();
	}

}
