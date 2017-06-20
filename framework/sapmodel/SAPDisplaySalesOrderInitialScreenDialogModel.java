package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 15, 2013
 */
public class SAPDisplaySalesOrderInitialScreenDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel =null;
	private String dialogTitle = "";

	public SAPDisplaySalesOrderInitialScreenDialogModel() {
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Display Sales Order: Initial Screen|Change Sales Order: Initial Screen")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (Exception e) {
			
		}
		return dialog;
	}
		
	public SAPGuiTextTestObject purchaseOrderNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtRV45S-BSTNK")))[0];
	}

	public SAPGuiTextTestObject orderNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtVBAK-VBELN")))[0];
	}

	public SAPGuiTextTestObject soldToPartyTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtRV45S-KUNNR")))[0];
	}
	
	public SAPGuiTextTestObject deliveryTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtRV45Z-LFNKD")))[0];
	}
	
	public SAPGuiTextTestObject billingDeliveryTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtRV45S-FAKKD")))[0];
	}
	
	public SAPGuiTextTestObject WBSElementTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtRV45S-PSPID")))[0];
	}
	
	public SAPGuiToggleTestObject searchButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btnBT_SUCH")))[0];
	}
	
}
