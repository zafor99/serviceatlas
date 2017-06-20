package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.impl.VPManager;

import framework.AtlasScriptbase;
import framework.sapcontroller.SAPDataBrowserInitialScreenDialogController.DataBrowserInitialScreenDialogToolbar;
import framework.sapmodel.SAPAutomaticCreationOfPODialogModel;
import framework.sapmodel.SAPAutomaticCreationOfPODialogModel.AutomaticCreationOfPODialogToolbar;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 04, 2014
 */
public  class SAPAutomaticCreationOfPODialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPAutomaticCreationOfPODialogController.class);
	private static SAPAutomaticCreationOfPODialogModel appModel = new SAPAutomaticCreationOfPODialogModel();
	
	public SAPAutomaticCreationOfPODialogController(){
		super(appModel.dialog());		
	}
	public AutomaticCreationOfPODialogToolbar toolbar(){
		return new AutomaticCreationOfPODialogToolbar();
	}
	public class AutomaticCreationOfPODialogToolbar{
		public AutomaticCreationOfPODialogToolbar(){
			
		}
		/*
		 * Click execute  Button on the Tool Bar
		 * 
		*/
		public void execute(){
			logger.info("execute()");
			appModel.automaticCreationOfPODialogToolbar().executeButton().click();
		}
		public void clickGetVariant(){
			logger.info("withVariant()");
			appModel.automaticCreationOfPODialogToolbar().getVariantButton().click();
		}
	}
	public void createPOfromPurchaseRequisition(String purchaseRequisition,String variant){
		logger.info("execute("+purchaseRequisition+")");
		if(purchaseRequisition.length()>1){
			toolbar().clickGetVariant();
			AtlasScriptbase.getExecutingScript().sap().findVariantDialog().findVariant(variant);
			appModel.purchaseRequisitionTextBox().setText(purchaseRequisition);
			toolbar().execute();
			delayFor(3);
		}
		else{
			RationalTestScript.logError("purchaseRequisition is not generated");
		}
	}
	public boolean verifyPurchaseOrderCreation(String vpName){
		boolean result= false;
		String purchaseOrderLabel ="";
		if(appModel.purchaseOrderLabel().exists()){
			purchaseOrderLabel = appModel.purchaseOrderLabel().getText();
			if(appModel.alreadyprocessingSalesDocLabel().getText().contains("already")){
				purchaseOrderLabel = appModel.alreadyprocessingSalesDocLabel().getText();
			}
			else{
				purchaseOrderLabel = " "+purchaseOrderLabel +appModel.purchaseOrderNoLabel().getText()+ " ";
				purchaseOrderLabel = purchaseOrderLabel +appModel.purchaseOrderCreateLabel().getText();
				
			}
		}
		else{
			purchaseOrderLabel = " Purchase Order Already got created";
		}
		
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, purchaseOrderLabel).performTest();
		return result;
	}
}
