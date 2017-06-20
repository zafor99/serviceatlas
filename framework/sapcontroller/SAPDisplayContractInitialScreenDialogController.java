package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDisplayContractInitialScreenDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 14, 2014
 */
public  class SAPDisplayContractInitialScreenDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDisplaySalesOrderInitialScreenDialogController.class);
	private static SAPDisplayContractInitialScreenDialogModel appModel = new SAPDisplayContractInitialScreenDialogModel();

	
	public SAPDisplayContractInitialScreenDialogController(){
		super(appModel.dialog());
	}
	
	public void search(String purchaseOrderNo){
		logger.info("search("+purchaseOrderNo+")");
		boolean result = false;
		BNTimer timer = new BNTimer();
		
		timer.start();
		if(purchaseOrderNo!=null){
			appModel.purchaseOrderNoTextBox().setText(purchaseOrderNo);
			do{			
				appModel.searchButton().click();
				result =AtlasScriptbase.getExecutingScript().sap().hitList1EntryDialog().waitForExistence(10); 
				if(timer.getElapsedTime()>=600){
					RationalTestScript.logError("Search timeout in 6 mins");
					break;
				}
			}while(result==false);
		}
		else{
			appModel.searchButton().click();
		}
		
	delayFor(2);
		
	}
}
