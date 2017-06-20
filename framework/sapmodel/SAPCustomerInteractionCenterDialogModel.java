package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPApplicationModel;
import framework.sapmodel.SAPCustomerInteractionCenterDialogModel.MainTab;
import framework.sapmodel.SAPCustomerInteractionCenterDialogModel.OrderSearchTab;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public  class SAPCustomerInteractionCenterDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel =null;
	private SAPTopLevelTestObject dialog = null;
	
	public SAPCustomerInteractionCenterDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		//if(this.dialog==null){
			try {
				TestObject[] tos = null;
				tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Customer Interaction Center"));
				if(tos.length>0){
					dialog = new SAPTopLevelTestObject(tos[0]);
				}
			} catch (Exception e) {
				
			}
		//}
		
		return dialog;
		
	}
	
	public OrderSearchTab orderSearchTab(){
		return new OrderSearchTab();
	}

	public DeviceSearchTab deviceSearchTab(){
		return new DeviceSearchTab();
	}

	public MainTab mainTab(){
		return new MainTab();
	}
	
	public class OrderSearchTab {
		private SAPGuiTabTestObject tab = null;
		
		public OrderSearchTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subAREA01:SAPLCCMA:0100/tabsCCONT_CONTROL"),atChild("Id","/tabpCCONT_TAB01")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		
		public SAPGuiToggleTestObject findButton(){
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id","/ssubCCONT_SUBSC01:SAPLZCCM1:0100/btnSEARCH_BUTTON")))[0];
		}
	}
	public class DeviceSearchTab {
		private SAPGuiTabTestObject tab = null;
		
		public DeviceSearchTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subAREA01:SAPLCCMA:0100/tabsCCONT_CONTROL"),atChild("Id","/tabpCCONT_TAB02")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		
		public SAPGuiToggleTestObject findButton(){
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id","/ssubCCONT_SUBSC02:SAPLZCCM2:0100/btnSEARCH_BUTTON")))[0];
		}
	}
	
	
	
	public class MainTab {
		
		private SAPGuiTabTestObject tab = null;
		public MainTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subAREA04:SAPLCCM2:0100/tabsPROCESS_TABSTRIP"),atChild("Id","/tabpFCTAB01")))[0];
			}
			
		}
		
		private TestObject buttonContainer(){
			return tab().find(atList(atChild("Name","SAPLCCM2TABCTRL201")))[0];
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		
		public SAPGuiToggleTestObject dispLayButton(){			
			return (SAPGuiToggleTestObject)buttonContainer().find(atList(atChild("Id","/btnTAB01-CTRL-LINE-COL1-VALUE[0,0]")))[0];
		}
		
		public SAPGuiToggleTestObject aloc1Button(){			
			return (SAPGuiToggleTestObject)buttonContainer().find(atList(atChild("Id","/btnTAB01-CTRL-LINE-COL1-VALUE[0,1]")))[0];
		}
		public SAPGuiToggleTestObject mapButton(){			
			return (SAPGuiToggleTestObject)buttonContainer().find(atList(atChild("Id","/btnTAB01-CTRL-LINE-COL1-VALUE[0,2]")))[0];
		}

		public SAPGuiToggleTestObject va03Button(){			
			return (SAPGuiToggleTestObject)buttonContainer().find(atList(atChild("Id","/btnTAB01-CTRL-LINE-COL1-VALUE[0,3]")))[0];
		}
	}
}
