package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPApplicationModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPHitList1EntryDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPHitList1EntryDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text"," Hit List 1 Entry"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
		
	}
	
	public SAPGuiToggleTestObject okButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[0]")))[0];
	}
	
	public SAPGuiToggleTestObject cancelButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[12]")))[0];
	}
	
	public SAPGuiTextTestObject orderStatusHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,1]")))[0];
	}
	
	private String getColumnNumber(String columnName){
		TestObject[] testObj = null;
		String label = null;
		String column = null;
		dialog().click();
		testObj = dialog().find(atList(atChild("Id","/usr"),atChild("Text",columnName)));
		if(testObj.length>0){
			label = (String)testObj[0].getProperty("Id");
			column = label.split("\\[")[1].split(",")[0];
			System.out.println("Column is : " + column);
		}
		//return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,3]")))[0];
		return column;
	}
	
	public SAPGuiTextTestObject getRowGuiTextTestObject(String columnName){
		//SAPGuiTextTestObject label = null;
		String columnNum = getColumnNumber(columnName);
		String id = "/lbl[" + columnNum + ",3]";
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",id)))[0]; 
	}
	
	public SAPGuiTextTestObject orderStatusLabel(){
		TestObject[] testObj = null;
		SAPGuiTextTestObject label = null;
		dialog().click();
		testObj = dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,3]")));
		if(testObj.length>0){
			label = (SAPGuiTextTestObject)testObj[0];
		}
		//return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[1,3]")))[0];
		return label;
	}
	
	public SAPGuiTextTestObject webOrderNumberHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[22,1]")))[0];
	}
	
	public SAPGuiTextTestObject webOrderNumberLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[22,3]")))[0];
	}
	
	public SAPGuiTextTestObject orderDtHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[43,1]")))[0];
	}
	
	public SAPGuiTextTestObject orderDtLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[43,3]")))[0];
	}
	
	public SAPGuiTextTestObject typeHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[54,1]")))[0];
	}
	
	public SAPGuiTextTestObject typeLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[54,3]")))[0];
	}
	
	public SAPGuiTextTestObject soldToEmailAddressHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[60,1]")))[0];
	}
	
	public SAPGuiTextTestObject soldToEmailAddressLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[60,3]")))[0];
	}
	
	public SAPGuiTextTestObject clientHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[106,1]")))[0];
	}
	
	public SAPGuiTextTestObject clientLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[106,3]")))[0];
	}
	
	public SAPGuiTextTestObject feCustomerIdHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[127,1]")))[0];
	}
	
	public SAPGuiTextTestObject feCustomerIdLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[127,3]")))[0];
	}
	
	public SAPGuiTextTestObject ordTypHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[52,1]")))[0];
	}
	
	public SAPGuiTextTestObject ordTypLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[52,3]")))[0];
	}
	
	public SAPGuiTextTestObject documentHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[59,1]")))[0];
	}
	
	public SAPGuiTextTestObject documentLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[59,3]")))[0];
	}
	
	public SAPGuiTextTestObject customerHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[70,1]")))[0];
	}
	
	public SAPGuiTextTestObject customerLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[70,3]")))[0];
	}
	
	public SAPGuiTextTestObject streetHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[81,1]")))[0];
	}
	
	public SAPGuiTextTestObject streetLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[81,3]")))[0];
	}
	
	public SAPGuiTextTestObject cityHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[107,1]")))[0];
	}
	
	public SAPGuiTextTestObject cityLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[107,3]")))[0];
	}
	
	public SAPGuiTextTestObject stateHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[133,1]")))[0];
	}
	
	public SAPGuiTextTestObject stateLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[133,3]")))[0];
	}
	
	public SAPGuiTextTestObject zipCodeHdrLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[139,3]")))[0];
	}
	
	public SAPGuiTextTestObject zipCodeLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[139,3]")))[0];
	}
}
