package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 15, 2014
 */
public  class SAPChangeDataRecordDialogModel extends SAPModelBase
{
private SAPApplicationModel appModel = new SAPApplicationModel();
	
	public SAPChangeDataRecordDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text","Change Data Record"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public SAPGuiTextTestObject poNumberTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0400/txtSVALD-VALUE[0,21]")))[0];
	}
	public SAPGuiTextTestObject vendorTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0400/txtSVALD-VALUE[8,21]")))[0];
	}
	public SAPGuiTextTestObject eanTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0400/txtSVALD-VALUE[3,21]")))[0];
	}
	public SAPGuiToggleTestObject okButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[0]")))[0];
	}
	public SAPGuiToggleTestObject cancelButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[12]")))[0];
	}
	
	public SAPGuiTextTestObject bSTNRTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0300/txtSVALD-VALUE[1,21]")))[0];
	}
	public SAPGuiTextTestObject zVENDMATTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0300/txtSVALD-VALUE[2,21]")))[0];
	}
	public SAPGuiTextTestObject serialNumberTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0300/txtSVALD-VALUE[0,21]")))[0];
	}

	public SAPGuiTextTestObject zLIFNRTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/sub:SAPLSPO4:0300/txtSVALD-VALUE[0,21]")))[0];
	}
	

}
