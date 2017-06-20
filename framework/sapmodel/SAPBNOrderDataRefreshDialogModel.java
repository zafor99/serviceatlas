package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
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
 * @since  September 23, 2013
 */
public  class SAPBNOrderDataRefreshDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel =null;
	private SAPTopLevelTestObject dialog = null;
	private bnOrderDataRefreshToolBar toolBar = null;
	
	public SAPBNOrderDataRefreshDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		//if(this.dialog==null){
			try {
				TestObject[] tos = null;
				tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("BN\\.com order .* - data refresh.*")));
				if(tos.length>0){
					dialog = new SAPTopLevelTestObject(tos[0]);
				}
			} catch (Exception e) {
				
			}
		//}
		
		return dialog;
		
	}
	public bnOrderDataRefreshToolBar toolBar(){
		return new bnOrderDataRefreshToolBar();
	}
	public SchedulesNPOsTab schedulesNPOsTab(){
		return new SchedulesNPOsTab();
	}
	public ItemsTab itemsTab(){
		return new ItemsTab();
	}
	public PaymentCardsTab paymentCardsTab(){
		return new PaymentCardsTab();
	}
	public class SchedulesNPOsTab {
		private SAPGuiTabTestObject tab = null;
		
		public SchedulesNPOsTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpSCHD")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}

		public SAPGuiTableControlTestObject scheduleSummaryTable(){
			return (SAPGuiTableControlTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8820/tblSAPLZCSDISPTCTLSCHEDULE")), true)[0];		
		}
		public SAPGuiTableControlTestObject warehousePOStatusTable(){
			return (SAPGuiTableControlTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8820/tblSAPLZCSDISPTCTRLPO")), true)[0];		
		}

	}
	public class ItemsTab{
		private SAPGuiTabTestObject tab = null;
		public ItemsTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpITEM")))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTableControlTestObject summaryTable(){
			return (SAPGuiTableControlTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8810/tblSAPLZCSDISPTCTLITEM")), true)[0];		
		}
		public SAPGuiToggleTestObject cancelButton()
		{
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8810/subTOOLBAR:SAPLZCSDISP:8010/btnBT_CANCELI")), true)[0];
		}
		/*public SAPGuiTextTestObject item1stItem(){
			return (SAPGuiTextTestObject)summaryTable().find(atList(atDescendant("Id","/txtZRCS_CSDISP_ITEM-LINE_NO[0,0]")), true)[0];
		}*/

	}
	public class PaymentCardsTab{
		private SAPGuiTabTestObject tab = null;
		public PaymentCardsTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpCARD")))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiToggleTestObject billToAdressButton()
		{
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8850/subTOOLBAR:SAPLZCSDISP:8050/btnBT_ADDRESS")), true)[0];
		}
		public SAPGuiToggleTestObject detailsButton()
		{
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8850/subTOOLBAR:SAPLZCSDISP:8050/btnBT_DETAIL")), true)[0];
		}
		public SAPGuiToggleTestObject addPaymentButton()
		{
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8850/subTOOLBAR:SAPLZCSDISP:8050/btnBT_ADD")), true)[0];
		}
		public SAPGuiTableControlTestObject paymentCardsTable(){
			return (SAPGuiTableControlTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPLZCSDISP:8850/tblSAPLZCSDISPTCTRL_ZAHLUNGSKARTEN")), true)[0];		
		}


	}
	public class bnOrderDataRefreshToolBar{
		
		private SAPGuiToolbarTestObject toolbar = null;
		
		public bnOrderDataRefreshToolBar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		
		public SAPGuiToggleTestObject emailButton()
		{
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[6]")))[0];
		}
	}
	
	
}
