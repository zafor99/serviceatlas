package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapcontroller.SAPABAPEditorInitialScreenDialogController.ABAPEditorDialogToolbar;
import framework.sapmodel.SAPCreateIncomingInvoiceDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 22, 2013
 */
public class SAPCreateIncomingInvoiceDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCreateIncomingInvoiceDialogController.class);
	private static SAPCreateIncomingInvoiceDialogModel appModel = new SAPCreateIncomingInvoiceDialogModel();
	
	public SAPCreateIncomingInvoiceDialogController(){
		super(appModel.dialog());		
	}
	/*
	 * @param purchaseOrder purchase Order Number
	 * create incoming Invoice for that particular Purchase Order
	 */
	public void createIncomingInvoice(String purchaseOrder){
		logger.info("createIncomingInvoice("+purchaseOrder+")");
		appModel.purchaseOrderTextBox().setText(purchaseOrder);
		appModel.createIncomingInvoiceToolbar().executeButton().click();
		delayFor(3);
	}
	public void createIncomingInvoice(String purchaseOrder,String poDocumentType){
		logger.info("createIncomingInvoice("+purchaseOrder+")");
		appModel.purchaseOrderTextBox().setText(purchaseOrder);
		appModel.poDocumentTypeTextBox().setText(poDocumentType);
		appModel.createIncomingInvoiceToolbar().executeButton().click();
		
		delayFor(3);
	}

	public boolean verifyInvoiceCreated(String vpName){
		boolean result = false;
		String invoiceDocumentLabel  = null;
		if(appModel.invoiceDocumentLabel()!=null){
			invoiceDocumentLabel= appModel.invoiceDocumentLabel().getText();
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, invoiceDocumentLabel).performTest();
		}
		else{
			invoiceDocumentLabel = appModel.noPOsLabel().getText();
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, invoiceDocumentLabel).performTest();
		}
		AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
		return result;
	}
	public boolean isInvoiceCreated(){
		boolean result = false;
		if(appModel.invoiceDocumentLabel()!=null){
			result = true;
		}
		return result;
	}
}
