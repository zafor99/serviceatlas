package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPDataBrowserTableEDIDCDialogModel;
import framework.sapmodel.SAPDataBrowserTableSelectionScreenDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 08, 2014
 */
public  class SAPDataBrowserTableEDIDCDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDataBrowserTableEDIDCDialogController.class);
	private static SAPDataBrowserTableEDIDCDialogModel appModel = new SAPDataBrowserTableEDIDCDialogModel();
	
	public SAPDataBrowserTableEDIDCDialogController(){
		super(appModel.dialog());		
	}
	public String getStatus(){
		return appModel.statusTextBox().getText();
	}
	public String getiDocNumber(){
		return appModel.iDocTextBox().getText();
	}
}
