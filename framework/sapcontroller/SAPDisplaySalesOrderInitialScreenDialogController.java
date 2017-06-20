package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDisplaySalesOrderInitialScreenDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 15, 2013
 */
public class SAPDisplaySalesOrderInitialScreenDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDisplaySalesOrderInitialScreenDialogController.class);
	private static SAPDisplaySalesOrderInitialScreenDialogModel appModel = new SAPDisplaySalesOrderInitialScreenDialogModel();

	
	public SAPDisplaySalesOrderInitialScreenDialogController(){
		super(appModel.dialog());
	}
	
	public void search(String purchaseOrderNo){
		logger.info("search("+purchaseOrderNo+")");
		boolean result = false;
		BNTimer timer = new BNTimer();
		
		timer.start();
		if(purchaseOrderNo!=null){
			appModel.orderNoTextBox().setText(" ");
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
	public void search(String purchaseOrderNo,boolean repeat){
		logger.info("search("+purchaseOrderNo+")");
		boolean result = false;
		BNTimer timer = new BNTimer();
		
		timer.start();
		if(repeat){
			if(purchaseOrderNo!=null){
				appModel.purchaseOrderNoTextBox().setText(purchaseOrderNo);
				do{			
					appModel.searchButton().click();
					result = AtlasScriptbase.getExecutingScript().sap().hitList1EntryDialog().waitForExistence(10); 
					if(timer.getElapsedTime()>=600){
						RationalTestScript.logError("Search timeout in 6 mins");
						break;
					}
				}while(result==false);
			}
			else{
				appModel.searchButton().click();
			}
		}
		else{
			appModel.purchaseOrderNoTextBox().setText(purchaseOrderNo);
			appModel.searchButton().click();
		}
		
	delayFor(2);
		
	}


}
