package framework.sapmodel;

import javax.swing.plaf.ToolBarUI;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiComboBoxTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiUserAreaTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sun.java.swing.plaf.windows.WindowsBorders.ToolBarBorder;

import framework.sapcontroller.SAPDisplaySalesOrderInitialScreenDialogController;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 17, 2013
 */
public class SAPDisplayDeviceOrderOverViewDialogModel extends SAPModelBase
{
	private DisplayDeviceOrderToolBar toolBar = null;
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPDisplayDeviceOrderOverViewDialogModel(){
		appModel = new SAPApplicationModel();
	//	toolBar = new DisplayDeviceOrderToolBar();
	
	}

	public SAPTopLevelTestObject dialog(){
		
		SAPTopLevelTestObject dialog=null;
		try {
			TestObject[] tos = null;
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Display.*: Overview|Change.*: Overview")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {

		}
		return dialog;
		
	}
	
	public DisplayDeviceOrderToolBar toolBar(){
		//return toolBar;
		return new DisplayDeviceOrderToolBar();
	}
	
	public SalesTab salesTab(){
		return new SalesTab();
	}
	public SAPGuiUserAreaTestObject userArea(){
		return (SAPGuiUserAreaTestObject)dialog().find(atList(atChild("Id","/usr")))[0];
	}
	
	public SAPGuiTextTestObject deviceOrderConsumerNumberTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Name","VBAK-VBELN")))[0];
	}
	public SAPGuiToggleTestObject displayDocHeaderDetailsButton()
	{
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/subSUBSCREEN_HEADER:SAPMV45A:4021/btnBT_HEAD")))[0];
	}

	public SAPGuiToggleTestObject scheduleLinesForItemButton()
	{
		//return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_OVERVIEW"),atChild("Id",xRegex("/tabpT.*01"),atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4400/subSUBSCREEN_TC:SAPMV45A:4900/subSUBSCREEN_BUTTONS:SAPMV45A:4050/btnBT_PEIN")))))[0];
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_OVERVIEW"),atChild("Id",xRegex("/tabpT.*01")),atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4400/subSUBSCREEN_TC:SAPMV45A:4900/subSUBSCREEN_BUTTONS:SAPMV45A:4050/btnBT_PEIN"))))[0];
	}

	public class SalesTab{
		private SAPGuiTabTestObject tab = null;
		public SalesTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_OVERVIEW"),atChild("Id",xRegex("/tabpT.*01"))))[0];
				//tab = (SAPGuiTabTestObject)RationalTestScript.find(atList(atDescendant("Id",xRegex("/tabpT\\01"))))[0];
				//tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_OVERVIEW"),atChild("Id","/tabpT\01")))[0];
			}
		}
		
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTableControlTestObject allItemsTable(){
			return (SAPGuiTableControlTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPMV45A:4400/subSUBSCREEN_TC:SAPMV45A:4900/tblSAPMV45ATCTRL_U_ERF_AUFTRAG")))[0];
		}

	}
	public class DisplayDeviceOrderToolBar{
		
		private SAPGuiToolbarTestObject toolbar = null;
		
		public DisplayDeviceOrderToolBar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		
		public SAPGuiToggleTestObject displayDocumentFlowButton()
		{
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id",xRegex("/btn.*"),"Tooltip",xRegex("Display document flow   \\(F5\\)|Display document flow   \\(F7\\)"))))[0];
		}
	}
	public SAPGuiTextTestObject firstArticleItem(){
		return (SAPGuiTextTestObject)RationalTestScript.find(atList(atDescendant("Name","RV45A-MABNR")),true)[0];
	}
	
	public SAPGuiComboBoxTestObject billingBlockComboBox(){
		return (SAPGuiComboBoxTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPMV45A:4400/ssubHEADER_FRAME:SAPMV45A:4440/cmbVBAK-FAKSK")),true)[0];
	}
	public SAPGuiTextTestObject bnSubscriptionTextBox(){
		return (SAPGuiTextTestObject)RationalTestScript.find(atList(atDescendant("Id","/subSUBSCREEN_HEADER:SAPMV45A:4021/ctxtVBAK-VBELN")),true)[0];
	}
	
}
