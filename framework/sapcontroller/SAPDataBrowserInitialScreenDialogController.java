package framework.sapcontroller;

import org.apache.log4j.Logger;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapcontroller.SAPABAPEditorInitialScreenDialogController.ABAPEditorDialogToolbar;
import framework.sapmodel.SAPDataBrowserInitialScreenDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 03, 2014
 */
public  class SAPDataBrowserInitialScreenDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDataBrowserInitialScreenDialogController.class);
	private static SAPDataBrowserInitialScreenDialogModel appModel = new SAPDataBrowserInitialScreenDialogModel();
	
	public SAPDataBrowserInitialScreenDialogController(){
		super(appModel.dialog());		
	}
	public DataBrowserInitialScreenDialogToolbar toolbar(){
		return new DataBrowserInitialScreenDialogToolbar();
	}
	public class DataBrowserInitialScreenDialogToolbar{
		public DataBrowserInitialScreenDialogToolbar(){
			
		}
		/*
		 * Click execute  Button on the Tool Bar
		 * 
		*/
		public void clickTableContentButton(){
			logger.info("execute()");
			appModel.dataBrowserInitialScreenDialogToolbar().tableContentsButton().click();
		}
	}
	/*
	 * @param TableName Name of table to execute
	 * Execute the program 
	 */
	public void execute(String TableName){
		logger.info("execute("+TableName+")");
		appModel.tableNameTextBox().setText(TableName);
		toolbar().clickTableContentButton();
		delayFor(3);
	}
}
