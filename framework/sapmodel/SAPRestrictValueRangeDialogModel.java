package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPApplicationModel;
import framework.sapmodel.SAPRestrictValueRangeDialogModel.StandardSearchTab;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPRestrictValueRangeDialogModel extends SAPModelBase
{
	private SAPTopLevelTestObject dialog = null;
	private SAPApplicationModel appModel = null;
	public SAPRestrictValueRangeDialogModel(){
		 appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text","Restrict Value Range"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	
	public SAPGuiToggleTestObject continueButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[0]")))[0];
	}
	
	public StandardSearchTab standardSearchTab(){
		return new StandardSearchTab();
	}
	public DeviceSearchTab deviceSearchTab(){
		return new DeviceSearchTab();
	}
	
	public class StandardSearchTab{
		
		public StandardSearchTab(){
			
		}
		
		public SAPGuiTextTestObject webOrderNumberTextBox(){
			return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsG_SELONETABSTRIP"),atChild("Id","/tabpTAB001"),atChild("Id","/ssubSUBSCR_PRESEL:SAPLSDH4:0220/sub:SAPLSDH4:0220/txtG_SELFLD_TAB-LOW[0,24]")))[0];
		}
	}
	public class DeviceSearchTab{
		public DeviceSearchTab(){
			
		}
		public SAPGuiTextTestObject webDeviceOrderNumberTextBox(){
			return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsG_SELONETABSTRIP"),atChild("Id","/tabpTAB001"),atChild("Id","/ssubSUBSCR_PRESEL:SAPLSDH4:0220/sub:SAPLSDH4:0220/txtG_SELFLD_TAB-LOW[4,24]")))[0];
		}
		
		
	}
	
	public class AdvancedSearchTab{
		
		public AdvancedSearchTab(){
			
		}
		
		
	}
}
