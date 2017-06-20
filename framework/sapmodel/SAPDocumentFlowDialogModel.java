package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlTreeTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 21, 2013
 */
public class SAPDocumentFlowDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	
	public SAPDocumentFlowDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Document Flow"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return dialog;
		
	}
	
	/*public SAPUserTableModel documentTable(){
		return new SAPUserTableModel(dialog(),"6,8,9,10,11,12,13,14","1,47,56,76,86,110","1,47,58,78,86,106");
	}*/
	public SAPGuiCtrlTreeTestObject documentTable(){
//		return (SAPGuiCtrlTreeTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/shell/shellcont[1]/shell[1]")))[0];
		return (SAPGuiCtrlTreeTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/shell/shellcont[1]/shell[1]")), true)[0];
	}
	
	public SAPCustomTreeTableModel documentTreeTable(){
		return new SAPCustomTreeTableModel(documentTable());
	}
	
	public SAPUserTableModel documentTableFromHeaderData(){
		return new SAPUserTableModel(dialog(),"5,7,8,9,10","1,49,58","1,49,58");
	}

	public SAPGuiTextTestObject deviceOrderConsmrNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[21,0]")), true)[0];
	}
	
	public SAPGuiTextTestObject devicDeliveryDCNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[69,0]")), true)[0];
	}
	
	public SAPGuiTextTestObject businessPartnerNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[21,1]")), true)[0];
	}
	
	public SAPGuiTextTestObject businessPartnerDescTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[42,1]")), true)[0];
	}
	
	public SAPGuiTextTestObject MaterialNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[21,2]")), true)[0];
	}
	
	public SAPGuiTextTestObject MaterialDescTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[42,2]")), true)[0];
	}
	public SAPGuiTextTestObject purchaseOrderCell(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,9]")), true)[0];
	}
	public SAPGuiTextTestObject deviceOrderWhSaleCell(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,8]")), true)[0];
	}

	public SAPGuiTextTestObject outbDelDeviceCell(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,10]")), true)[0];
	}

	public SAPGuiToggleTestObject displauDocumentButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[8]")), true)[0];
	}
}
