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
 * @since  May 23, 2013
 */
public class SAPSelectIDocDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	public SAPSelectIDocDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Select IDocs|Test tool for IDoc processing")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	
	public SAPGuiToggleTestObject executeButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[8]")), true)[0];
		
	}
	
	public SAPGuiTextTestObject iDocNumberFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_DOCNU-LOW")), true)[0];
	}
	public SAPGuiTextTestObject existingiDocNumberFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtMSED7START-EXIDOCNUM")), true)[0];
	}
	
	public SAPGuiTextTestObject iDocNumberToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_DOCNU-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject multipleIDocNumberSelectionButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%_SX_DOCNU_%_APP_%-VALU_PUSH")), true)[0];
	}
	
	public SAPGuiTextTestObject createdOnFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_CREDA-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject createdOnToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_CREDA-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject createdOnPreviousButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%P009004_1100")), true)[0];
	}
	
	public SAPGuiTextTestObject createdOnNextButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%P011005_1100")), true)[0];
	}
	
	public SAPGuiTextTestObject timeCreatedFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_CRETI-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject timeCreatedToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_CRETI-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject changedOnFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDDA-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject changedOnToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDTI-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject changedOnPreviousButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%P009008_1100")), true)[0];
	}
	
	public SAPGuiTextTestObject changedOnNextButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%P011009_1100")), true)[0];
	}
	
	public SAPGuiTextTestObject lastChangedAtFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDTI-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject lastChangedAtToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDTI-HIGH")), true)[0];
	}
	
	
	public SAPGuiTextTestObject iDocStatusFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_STATU-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject iDocStatusToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDDA-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject multipleIDocStatusSelectionButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%_SX_STATU_%_APP_%-VALU_PUSH")), true)[0];
	}
	
	public SAPGuiTextTestObject partnerSystemFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_PRPRN-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject partnerSystemToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_PRPRN-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject multiplePartnerSystemSelectionButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%_SX_PRPRN_%_APP_%-VALU_PUSH")), true)[0];
	}
	
	public SAPGuiTextTestObject messageTypeFromTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDDA-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject messageTypeToTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_UPDDA-HIGH")), true)[0];
	}
	
	public SAPGuiTextTestObject multipleMessageTypeSelectionButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%_SX_MESTY_%_APP_%-VALU_PUSH")), true)[0];
	}
	
	public SAPGuiTextTestObject businessObjectTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_OBJTY-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject objectKeyTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtSX_OBJKY-LOW")), true)[0];
	}
	
	public SAPGuiTextTestObject multipleObjectKeySelectionButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btn%_SX_OBJKY_%_APP_%-VALU_PUSH")), true)[0];
	}
}
