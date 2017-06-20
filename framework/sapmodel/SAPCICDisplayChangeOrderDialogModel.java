package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPApplicationModel;
import framework.sapmodel.SAPCICDisplayChangeOrderDialogModel.InvoiceTabModel;
import framework.sapmodel.SAPCICDisplayChangeOrderDialogModel.ItemsTabModel;
import framework.sapmodel.SAPCICDisplayChangeOrderDialogModel.OrdersTabModel;
import framework.sapmodel.SAPGuiTableControl;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPCICDisplayChangeOrderDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	
	public SAPCICDisplayChangeOrderDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		//if(this.dialog==null){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = appModel.getSAPSession().find(atDescendant("Id","/wnd[0]","Text","SAP"));
			//tos = appModel.getSAPSession().find(atDescendant("Id","/usr"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block				
		}
		//}

		return dialog;
		
	}

	
	public OrdersTabModel ordersTab(){
		return new OrdersTabModel();
	}
	
	public ItemsTabModel itemsTab(){
		return new ItemsTabModel();
	}
	
	public InvoiceTabModel invoiceTab(){
		return new InvoiceTabModel();
	}
	
	
	
	public class OrdersTabModel {
		private SAPGuiTabTestObject tab = null;
		public OrdersTabModel(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpORDER")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		
		public SAPGuiTableControl orderHistoryTable(){
			return new SAPGuiTableControl(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8880/tblSAPLZCSDISPTCTLORDER"));
		}
	}
	
	public class ItemsTabModel {
		
		private SAPGuiTabTestObject tab = null;
		public ItemsTabModel(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpITEM")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		
		public SAPGuiTableControl itemSummaryTable(){
			return new SAPGuiTableControl(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8810/tblSAPLZCSDISPTCTLITEM"));
		}
	}
	
	public class InvoiceTabModel {
		
		private SAPGuiTabTestObject tab = null;
		public InvoiceTabModel(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpBILL")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		
		public SAPGuiTableControl invoiceDetailsTable(){
			return new SAPGuiTableControl(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8840/tblSAPLZCSDISPTCTLBILLING"));
		}
	}
}
