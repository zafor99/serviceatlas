package framework.sapcontroller;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPRestrictValueRangeDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPRestrictValueRangeDialogController extends SAPDialogController
{
	private static SAPRestrictValueRangeDialogModel appModel = new SAPRestrictValueRangeDialogModel();
	
	public SAPRestrictValueRangeDialogController(){
		super(appModel.dialog());
	}
	
	public DeviceSearchTab deviceSearchTab(){
		return new DeviceSearchTab();
	}
	
	public void searchWebOrder(String webOrderNum){
		appModel.standardSearchTab().webOrderNumberTextBox().setText(webOrderNum);
		appModel.continueButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	
	public class DeviceSearchTab{
		public DeviceSearchTab(){
			
		}
		public void searchDeviceWebOrder(String webOrderNum){
			AtlasScriptbase.getExecutingScript().delayFor(2);
			appModel.deviceSearchTab().webDeviceOrderNumberTextBox().setText(webOrderNum);
			AtlasScriptbase.getExecutingScript().delayFor(2);
			appModel.continueButton().click();
			AtlasScriptbase.getExecutingScript().delayFor(2);
		}
	}
}
