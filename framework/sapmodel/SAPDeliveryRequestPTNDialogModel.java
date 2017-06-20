package framework.sapmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 27, 2013
 */
public  class SAPDeliveryRequestPTNDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	
	public SAPDeliveryRequestPTNDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public ConfirmationTab confirmationTab(){
		return new ConfirmationTab();
	}
	public PurchaseOrderHistoryTab purchaseOrderHistoryTab(){
		return new PurchaseOrderHistoryTab();
	}
	public ToolBar toolBar(){
		return new ToolBar();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Delivery Request PTN.*|BN.com Dropship PO.*|APPs Dwnload PO .* Created by .*|Agency Model PO.*Created by .*|Ebooks Dwnload PO .* Created by .*|Agency Sub/Siss PO.*")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;

	}
	public SAPTopLevelTestObject dialog(String idXpath,String textXpath){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = appModel.getSAPSession().find(atDescendant("Id",idXpath,"Text",textXpath));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;

	}
	public SAPGuiTextTestObject bnDropShipPONumberTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subSUB0:SAPLMEGUI:0019/subSUB0:SAPLMEGUI:0030/subSUB1:SAPLMEGUI:1105/txtMEPO_TOPLINE-EBELN")), true)[0];
	}
	public class ToolBar{
		
		private SAPGuiToolbarTestObject toolbar = null;
		
		public ToolBar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject otherPurchaseOrderButton(){
//			return (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subSUB0:SAPLMEGUI:0019/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL"),atChild("Id","/tabpTABIDT16")), true)[0];
			return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[17]")), true)[0];
		}
		public SAPGuiToggleTestObject otherDocumentButton(){
			return (SAPGuiToggleTestObject)dialog("/wnd[1]","Select Document").find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[0]")), true)[0];
		}
		
	}
	public class ConfirmationTab{
	
		private SAPGuiTabTestObject tab = null;
		public ConfirmationTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/subSUB0:SAPLMEGUI:.*/subSUB3:SAPLMEVIEWS:.*/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:.*/subSUB2:SAPLMEGUI:.*/tabsITEM_DETAIL")),atChild("Id",xRegex("/tabpTABIDT16"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTableControlTestObject confirmationTable(){
			return (SAPGuiTableControlTestObject)tab().find(atList(atChild("Id","/ssubTABSTRIPCONTROL1SUB:SAPLMEVIEWS:1101/subSUB2:SAPLMEGUI:1332/subSUB0:SAPLEINB:0300/tblSAPLEINBTC_0300")))[0];
		}
	}
	public class PurchaseOrderHistoryTab{
		private SAPGuiTabTestObject tab = null;
		public PurchaseOrderHistoryTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/subSUB0:SAPLMEGUI:.*/subSUB3:SAPLMEVIEWS:.*/subSUB2:SAPLMEVIEWS:.*/subSUB1:SAPLMEGUI:.*/subSUB2:SAPLMEGUI:.*/tabsITEM_DETAIL")),atChild("Id",xRegex("/tabpTABIDT13"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiCtrlGridViewTestObject purchaseOrderHistoryGrid(){
			return (SAPGuiCtrlGridViewTestObject)tab().find(atList(atChild("Id","/ssubTABSTRIPCONTROL1SUB:SAPLMEGUI:1316/ssubPO_HISTORY:SAPLMMHIPO:0100/cntlMEALV_GRID_CONTROL_MMHIPO/shellcont/shell")))[0];
		}
	}
	
	@Deprecated
	public SAPGuiTableControlTestObject confirmationTable(){
		return (SAPGuiTableControlTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subSUB0:SAPLMEGUI:0019/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL"),atChild("Id","/tabpTABIDT16"),atChild("Id","/ssubTABSTRIPCONTROL1SUB:SAPLMEVIEWS:1101/subSUB2:SAPLMEGUI:1332/subSUB0:SAPLEINB:0300/tblSAPLEINBTC_0300")), true)[0];		
	}
	@Deprecated
	public SAPGuiCtrlGridViewTestObject purchaseOrderHistoryGrid(){
		return (SAPGuiCtrlGridViewTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subSUB0:SAPLMEGUI:0019/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL"),atChild("Id","/tabpTABIDT13"),atChild("Id","/ssubTABSTRIPCONTROL1SUB:SAPLMEGUI:1316/ssubPO_HISTORY:SAPLMMHIPO:0100/cntlMEALV_GRID_CONTROL_MMHIPO/shellcont/shell")), true)[0];		
	}

	

}
