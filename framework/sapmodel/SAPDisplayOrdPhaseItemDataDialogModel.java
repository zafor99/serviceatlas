package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPApplicationModel;
import framework.sapmodel.SAPModelBase;
import framework.sapmodel.SAPDisplayDeviceOrderOverViewDialogModel.DisplayDeviceOrderToolBar;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 12, 2013
 */
public  class SAPDisplayOrdPhaseItemDataDialogModel extends SAPModelBase
{
	public SAPApplicationModel appModel = null;//new SAPApplicationModel();
	private ShippingTab shippingTab = null;
	private ScheduleLinesTab scheduleLinesTab = null;

	
	public SAPDisplayOrdPhaseItemDataDialogModel(){
		appModel = new SAPApplicationModel();
		//shippingTab = new ShippingTab();

	}
	public SAPTopLevelTestObject dialog(){
		
		SAPTopLevelTestObject dialog=null;
		try {
			TestObject[] tos = null;
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Display BN.*Item Data")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {

		}
		return dialog;
		
	}
	public SAPGuiTextTestObject firstArticleItem(){
		return (SAPGuiTextTestObject)dialog().find(atList(atDescendant("Id","/ctxtRV45A-MABNR[1,0]")),true)[0];
	}
	public ShippingTab shippingTab(){
		if(shippingTab==null){
			shippingTab = new ShippingTab();
		}
		return shippingTab;
	}
	public ScheduleLinesTab scheduleLinesTab(){
		if(scheduleLinesTab==null){
			scheduleLinesTab = new ScheduleLinesTab();
		}
		return scheduleLinesTab;
	}

	public class ShippingTab{
		private SAPGuiTabTestObject tab = null;
		public ShippingTab(){
			//tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_OVERVIEW"),atChild("Id","/tabpT\06")), true)[0];
			tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_ITEM"),atChild("Id",xRegex("/tabpT.*03"))), true)[0];
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTextTestObject specProcessingTextBox(){
			return (SAPGuiTextTestObject)RationalTestScript.find(atList(atDescendant("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4452/ctxtVBKD-SDABW"))),true)[0];
		}
	}
	public class ScheduleLinesTab{
		private SAPGuiTabTestObject tab = null;
		public ScheduleLinesTab(){
			tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_ITEM"),atChild("Id",xRegex("/tabpT.*07"))), true)[0];
		}
		public SAPGuiTabTestObject tab(){
			//return tab;
			return (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_ITEM"),atChild("Id",xRegex("/tabpT.*07"))), true)[0];
		}
		public SAPGuiTableControlTestObject quantitiesDatesTable(){
			return (SAPGuiTableControlTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPMV45A:4500/tblSAPMV45ATCTRL_PEIN")))[0];
		}
	}

	
}
