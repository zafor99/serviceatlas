package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 01, 2014
 */
public  class SAPUpdateBillingAddressDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPUpdateBillingAddressDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text","Update billing address"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public SAPGuiTextTestObject nameTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtGS_BILLTO_ADDR_DIAL-FIRSTNAME")))[0];
	}
	public SAPGuiTextTestObject companyTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtGS_BILLTO_ADDR_DIAL-COMPANY")))[0];
	}
	public SAPGuiTextTestObject telephoneTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtGS_BILLTO_ADDR_DIAL-PHONE")))[0];
	}
	public SAPGuiTextTestObject street1TextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtGS_BILLTO_ADDR_DIAL-STREET1")))[0];
	}
	public SAPGuiTextTestObject street2TextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtGS_BILLTO_ADDR_DIAL-STREET2")))[0];
	}
	public SAPGuiTextTestObject street3TextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtGS_BILLTO_ADDR_DIAL-STREET3")))[0];
	}
	public SAPGuiTextTestObject cityTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtGS_BILLTO_ADDR_DIAL-CITY")))[0];
	}
	public SAPGuiTextTestObject zipTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtGS_BILLTO_ADDR_DIAL-ZIP")))[0];
	}
	public SAPGuiTextTestObject countryTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtZCSDISP_ADDRESS-COUNTRY")))[0];
	}
	public SAPGuiTextTestObject regionTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtZCSDISP_ADDRESS-STATE")))[0];
	}
	public SAPGuiToggleTestObject poBoxCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkGS_BILLTO_ADDR_DIAL-PO_BOX")))[0];
	}
	public SAPGuiToggleTestObject verificationAndSuggestionCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkG_NEED_SUGGEST")))[0];
	}

	public SAPGuiToggleTestObject updateButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[17]")))[0];
	}
	public SAPGuiToggleTestObject closeButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[6]")))[0];
	}
	public SAPGuiTableControlTestObject addressTable(){
		return (SAPGuiTableControlTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tblSAPLZCSDISPCTLSUGGESTION")))[0];
	}

}
