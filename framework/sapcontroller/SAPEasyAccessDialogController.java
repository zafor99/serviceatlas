package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPEasyAccessDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 31, 2012
 */
public class SAPEasyAccessDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPEasyAccessDialogController.class);
	private static SAPEasyAccessDialogModel appModel = new SAPEasyAccessDialogModel();
	
	public SAPEasyAccessDialogController(){
		//appModel = new SAPEasyAccessDialogModel();
		super(appModel.dialog());
	}
	
	
	public void openDisplayArticleDialog(){
		logger.info("openDisplayArticleDialog");
		appModel.easyAccessOptionTree().doubleClickNode(atPath("Favorites->MM43 - Display Article &"));
	}
	
	public void openCustomerInteractionCenterDialog(){
		logger.info("openCustomerInteractionCenterDialog");
		appModel.easyAccessOptionTree().doubleClickNode(atPath("Favorites->CIC0 - Customer Interaction Center"));
	}
	
	public void openSelectIDocDialog(){
		logger.info("openSelectIDocDialog");
		appModel.easyAccessOptionTree().doubleClickNode(atPath("Favorites->BD87 - Process IDocs manually"));
	}
	
	public void openDisplaySalesOrderInitialScreenDialog(){
		logger.info("openDisplaySalesOrderInitialScreenDialog");
		appModel.easyAccessOptionTree().doubleClickNode(atPath("Favorites->VA03 - Display Sales Order"));
		AtlasScriptbase.getExecutingScript().sap().displaySalesOrderInitailScreenDialog().waitForExistence();
	}
	
}
