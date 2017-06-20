package framework.sapcontroller;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPLogOffDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  August 07, 2012
 */
public class SAPLogOffDialogController extends SAPControllerBase
{
	
	private SAPLogOffDialogModel sapLogOffDialogModel = null;
	
	public SAPLogOffDialogController(){
		sapLogOffDialogModel = new SAPLogOffDialogModel();
	}
	
	public boolean waitForExistence(){
		boolean result = false;

		sapLogOffDialogModel.dialog().waitForExistence(120, 5);
		if(sapLogOffDialogModel.dialog().exists()){
			result = true;
		}
		return result;
	}
	
	public void clickYesButton(){
		sapLogOffDialogModel.yesButton().click();
		getExecutingScript().delayFor(3);
	}
	
	public void clickNoButton(){
		sapLogOffDialogModel.noButton().click();
		getExecutingScript().delayFor(3);
	}
}
