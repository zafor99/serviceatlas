package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiComboBoxTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlTreeTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 06, 2014
 */
public  class SAPiDocReportingToolDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel =null;
	public SAPiDocReportingToolDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","IDoc Reporting Tool"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
	}
	public SAPGuiTextTestObject sapSalesOrderTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtVBAK-VBELN")), true)[0];
	}
	public SAPGuiTextTestObject feOrderNumberTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtVBKD-BSTKD")), true)[0];
	}
	public SAPGuiComboBoxTestObject salesDocTypeComboBox(){
		return (SAPGuiComboBoxTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cmbVBAK-AUART")), true)[0];
	}
	public SAPGuiToggleTestObject executeButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btnBUTTON1")), true)[0];
	}
	public SAPCustomTreeTableModel iDocReportingToolTreeTable(){
		return new SAPCustomTreeTableModel(iDocReportingToolTree());
	}
	
	public SAPGuiCtrlTreeTestObject iDocReportingToolTree(){
		return (SAPGuiCtrlTreeTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlTREE_CONTAINER/shellcont/shell")), true)[0];
	}
	
}
