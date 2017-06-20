package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPHitList1EntryDialogModel;
import framework.sapmodel.SAPInformationDialogModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 12, 2013
 */
public  class SAPInformationDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPInformationDialogController.class);
	private static SAPInformationDialogModel appModel = new SAPInformationDialogModel();
	
	public SAPInformationDialogController(){
		super(appModel.dialog());
	}
	
	public void clickOkButton(){
		logger.info("clickCancelButton()");
		appModel.okButton().click();
		delayFor(3);
		
		
	}
	
	public String getShopConfirmIDOCiDocFromTextLabel(){
		String iDoc = null;
		iDoc = appModel.textLabel().getText();
		String[] words = iDoc.split(" ");  
		iDoc = words[1];
		System.out.println("iDoc Number "+ iDoc);
		return iDoc;
	}

	public String getShipConfirmiDocFromTextLabel(){
		String iDoc = null;
		iDoc = appModel.textLabel().getText();
		String[] words = iDoc.split(" ");  
		iDoc = words[2];
		System.out.println("iDoc Number "+ iDoc);
		return iDoc;
	}

}
