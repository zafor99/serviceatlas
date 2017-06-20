package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapcontroller.SAPDeliveryRequestPTNDialogController.Toolbar;
import framework.sapmodel.SAPABAPEditorInitialScreenDialogModel;
import framework.sapmodel.SAPDeliveryRequestPTNDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 18, 2013
 */
public  class SAPABAPEditorInitialScreenDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDeliveryRequestPTNDialogController.class);
	private static SAPABAPEditorInitialScreenDialogModel appModel = new SAPABAPEditorInitialScreenDialogModel();
	
	public SAPABAPEditorInitialScreenDialogController(){
		super(appModel.dialog());		
	}
	
	public ABAPEditorDialogToolbar toolbar(){
		return new ABAPEditorDialogToolbar();
	}
	public class ABAPEditorDialogToolbar{
		public ABAPEditorDialogToolbar(){
			
		}
		/*
		 * Click execute  Button on the Tool Bar
		 * 
		*/
		public void execute(){
			logger.info("execute()");
			appModel.aBAPEditorDialogToolbar().executeButton().click();
		}
	}
	/*
	 * @param programName Name of program to execute
	 * Execute the program 
	 */
	public void execute(String programName){
		logger.info("execute("+programName+")");
		appModel.programTextBox().setText(programName);
		appModel.aBAPEditorDialogToolbar().executeButton().click();
		delayFor(3);
	}
	public void executeWithVariant(String programName,String variantName){
		logger.info("executeWithVariant("+programName+","+variantName+")");
		appModel.programTextBox().setText(programName);
		appModel.aBAPEditorDialogToolbar().withVariantButton().click();
		AtlasScriptbase.getExecutingScript().sap().executeProgramdialog().executeProgramWithVariant(variantName);
		delayFor(3);
	}

}
