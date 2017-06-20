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
public  class SAPTestToolForiDocProcessingDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPTestToolForiDocProcessingDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Test tool for IDoc processing"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public TestTooliDocProcessingDialogToolbar testToolDialogToolbar(){
		return new TestTooliDocProcessingDialogToolbar();
	}
	public class TestTooliDocProcessingDialogToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public TestTooliDocProcessingDialogToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject standardInboundButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
		public SAPGuiToggleTestObject inboundFunctionModuleButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[26]")), true)[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")))[0];
		}
	}
	public SAPGuiTextTestObject existingIDocTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtMSED7START-EXIDOCNUM")))[0];
	}
	public SAPGuiTextTestObject e1PORDCHExpandButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[5,3]")))[0];
	}
	public SAPGuiTextTestObject e1bPMEPOHEADER_POTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[40,5]")))[0];
	}
	public SAPGuiTextTestObject e1pordch_TextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[36,3]")))[0];
	}

	
	public SAPGuiTextTestObject e1EDL20ExpandButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[5,3]")))[0];
	}
	public SAPGuiTextTestObject e1EDL24ExpandButton(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[9,10]")))[0];
	}
	public SAPGuiTextTestObject e1EDL41_POTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[44,14]")))[0];
	}
	public SAPGuiTextTestObject z1EDL24_POTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[44,12]")))[0];
	}
	public SAPGuiTextTestObject e1EDL11_POTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[44,13]")))[0];
	}

	public SAPGuiTextTestObject z1EDL20_POTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[40,5]")))[0];
	}

}
