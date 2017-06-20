package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPAddPaymentDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 07, 2014
 */
public  class SAPAddPaymentDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPAddPaymentDialogController.class);
	private static SAPAddPaymentDialogModel appModel = new SAPAddPaymentDialogModel();
	
	public SAPAddPaymentDialogController(){
		super(appModel.dialog());
	}
	public void addPayment(String type,String cardNumber,String validTo,String maximumAmt,String memberReference,String tracking,String action){
		logger.info("addPayment("+type+"),("+cardNumber+"),("+validTo+"),("+maximumAmt+"),("+memberReference+"),("+tracking+"),("+action+")");
		
		if(type!=null){
/*			appModel.paymentTable().setColumnWidth(
                    atColumnHeader(atText("Type")), 
                    5);
*/			appModel.typeFirstCellTextBox().setText(type);
		}
		if(cardNumber!=null){
			appModel.cardNumber1stCellTextBox().setText(cardNumber);
		}
		if(validTo!=null){
			appModel.validTo1stCellTextBox().setText(validTo);
		}
		if(maximumAmt!=null){
			//TODO appModel.validTo1stCellTextBox().setText(maximumAmt);
		}
		if(memberReference!=null){
			//TODO appModel.validTo1stCellTextBox().setText(memberReference);
		}
		if(tracking!=null){
			//TODO appModel.validTo1stCellTextBox().setText(tracking);
		}
		if(action!=null){
			appModel.updateButton().click();
			delayFor(3);
			
		}
		
	}
}
