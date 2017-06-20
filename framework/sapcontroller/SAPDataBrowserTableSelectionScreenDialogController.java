package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapcontroller.SAPDataBrowserInitialScreenDialogController.DataBrowserInitialScreenDialogToolbar;
import framework.sapmodel.SAPDataBrowserTableSelectionScreenDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 03, 2014
 */
public  class SAPDataBrowserTableSelectionScreenDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDataBrowserTableSelectionScreenDialogController.class);
	private static SAPDataBrowserTableSelectionScreenDialogModel appModel = new SAPDataBrowserTableSelectionScreenDialogModel();
	
	public SAPDataBrowserTableSelectionScreenDialogController(){
		super(appModel.dialog());		
	}
	public DataBrowserTableSelectionScreenDialogToolbar toolbar(){
		return new DataBrowserTableSelectionScreenDialogToolbar();
	}
	public class DataBrowserTableSelectionScreenDialogToolbar{
		public DataBrowserTableSelectionScreenDialogToolbar(){
			
		}
		/*
		 * Click execute  Button on the Tool Bar
		 * 
		*/
		public void clickTableContentButton(){
			logger.info("execute()");
			appModel.dataBrowserTableSelectionScreenDialogToolbar().executeButton().click();
		}
	}
	/*
	 * @param TableName Name of table to execute
	 * Execute the program 
	 */
	public void execute(String orderNumber){
		logger.info("execute("+orderNumber+")");
		appModel.sndladTextBox().setText(orderNumber);
		toolbar().clickTableContentButton();
		delayFor(3);
	}
}
