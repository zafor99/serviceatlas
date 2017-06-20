package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapcontroller.SAPBNOrderDataRefreshDialogController.paymentCardsTab;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 05, 2014
 */
public  class SAPAddPaymentDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPAddPaymentDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text","Add payment"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public SAPGuiTableControlTestObject paymentTable(){
		return (SAPGuiTableControlTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tblSAPLZCSDISPTCTLCARDS")))[0];
	}
	public SAPGuiTextTestObject  typeFirstCellTextBox(){
		return (SAPGuiTextTestObject)paymentTable().find(atList(atChild("Id","/ctxtGT_CARD-CCINS[0,0]")))[0];
	}
	public SAPGuiTextTestObject  cardNumber1stCellTextBox(){
		return (SAPGuiTextTestObject)paymentTable().find(atList(atChild("Id","/txtGT_CARD-CCNUM[1,0]")))[0];
	}
	public SAPGuiTextTestObject  validTo1stCellTextBox(){
		return (SAPGuiTextTestObject)paymentTable().find(atList(atChild("Id","/txtGT_CARD-YYYYMM[2,0]")))[0];
	}
	public SAPGuiToggleTestObject updateButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[5]")))[0];
	}
	
}
