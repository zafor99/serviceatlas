package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPTestToolForiDocProcessingDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 15, 2014
 */
public  class SAPTestToolForiDocProcessingDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPTestToolForiDocProcessingDialogController.class);
	private static SAPTestToolForiDocProcessingDialogModel appModel = new SAPTestToolForiDocProcessingDialogModel();
	
	public SAPTestToolForiDocProcessingDialogController(){
		super(appModel.dialog());
	}
	
	public void searchExistingiDoc(String iDocNumber){
		logger.info("searchExistingiDoc("+iDocNumber+")");
		appModel.existingIDocTextBox().setText(iDocNumber);
		appModel.testToolDialogToolbar().executeButton().click();
		delayFor(3);
	}
	public void expandE1PORDCH(){
		appModel.e1PORDCHExpandButton().click();
		delayFor(2);
	}
	public void expandE1EDL20(){
		appModel.e1EDL20ExpandButton().click();
		delayFor(2);
	}
	public void expandE1EDL24(){
		appModel.e1EDL24ExpandButton().click();
		delayFor(2);
	}
	public void openE1BPMEPOHEADER(){
		appModel.e1bPMEPOHEADER_POTextBox().click();
		delayFor(2);
	}
	public void openE1PORDCH(){
		appModel.e1pordch_TextBox().click();
		delayFor(2);
	}
	public void openE1EDL41(){
		appModel.e1EDL41_POTextBox().click();
		delayFor(2);
	}
	public void openZ1EDL24(){
		appModel.z1EDL24_POTextBox().click();
		delayFor(2);
	}
	public void openE1EDL11(){
		appModel.e1EDL11_POTextBox().click();
		delayFor(2);
	}
	
	public void openZ1EDL20(){
		appModel.z1EDL20_POTextBox().click();
		delayFor(2);
	}

	public void clickStandardInbound(){
		appModel.testToolDialogToolbar().standardInboundButton().click();
		delayFor(2);
		
	}
}
