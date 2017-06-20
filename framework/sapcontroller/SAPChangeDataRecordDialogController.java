package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPChangeDataRecordDialogModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 15, 2014
 */
public class SAPChangeDataRecordDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPChangeDataRecordDialogController.class);
	private static SAPChangeDataRecordDialogModel appModel = new SAPChangeDataRecordDialogModel();
	
	public SAPChangeDataRecordDialogController(){
		super(appModel.dialog());
	}
	public void changeData(String poNumber,String vendor,String ean,String action){
		logger.info("poNumber("+poNumber+"),("+vendor+"),("+ean+")");
		if(poNumber!=null){
			appModel.poNumberTextBox().setText(poNumber);
		}
		if(vendor!=null){
			appModel.vendorTextBox().setText(vendor);
		}
		if(ean!=null){
			appModel.eanTextBox().setText(ean);
		}
		if(action!=null){
			if(action.contains("ok")){
				appModel.okButton().click();
			}
			if(action.contains("cancel")){
				appModel.cancelButton().click();
			}
			
		}
		
	}
	public void ChangeBSTNR(String poNumber,String action){
		logger.info("ChangeBSTNR("+poNumber+"),("+action+")");
		if(poNumber!=null){
			appModel.bSTNRTextBox().setText(poNumber);
		}
		if(action!=null){
			if(action.contains("ok")){
				appModel.okButton().click();
			}
			if(action.contains("cancel")){
				appModel.cancelButton().click();
			}
			
		}
		
	}
	public void ChangeZLIFNR(String vendorNumber,String action){
		logger.info("ChangeZLIFNR("+vendorNumber+"),("+action+")");
		if(vendorNumber!=null){
			appModel.bSTNRTextBox().setText(vendorNumber);
		}
		if(action!=null){
			if(action.contains("ok")){
				appModel.okButton().click();
			}
			if(action.contains("cancel")){
				appModel.cancelButton().click();
			}
			
		}
		
	}
	public void ChangeZVENDMAT(String eanNumber,String action){
		logger.info("ChangeZVENDMAT("+eanNumber+"),("+action+")");
		if(eanNumber!=null){
			appModel.zVENDMATTextBox().setText(eanNumber);
		}
		if(action!=null){
			if(action.contains("ok")){
				appModel.okButton().click();
			}
			if(action.contains("cancel")){
				appModel.cancelButton().click();
			}
			
		}
		
	}
	public void ChangeSerialNumber(String serialNumber,String action){
		logger.info("ChangeSerialNumber("+serialNumber+"),("+action+")");
		if(serialNumber!=null){
			appModel.serialNumberTextBox().setText(serialNumber);
		}
		if(action!=null){
			if(action.contains("ok")){
				appModel.okButton().click();
			}
			if(action.contains("cancel")){
				appModel.cancelButton().click();
			}
			
		}
		
	}

}
