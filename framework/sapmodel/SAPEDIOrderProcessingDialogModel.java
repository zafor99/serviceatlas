package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 12, 2013
 */
public  class SAPEDIOrderProcessingDialogModel extends SAPModelBase
{
		private SAPApplicationModel appModel = null;
		
		public SAPEDIOrderProcessingDialogModel(){
			appModel = new SAPApplicationModel();		
		}
	
		public SAPTopLevelTestObject dialog(){
			SAPTopLevelTestObject dialog = null;
			TestObject[] tos = null;
			try {
				tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("EDI Order Processing.*")));
				if(tos.length>0){
					dialog = new SAPTopLevelTestObject(tos[0]);
				}
			} catch (NullPointerException e) {
				
			}
			return dialog;
			
		}
		public SAPGuiTextTestObject orderDateTextBox(){
			return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSWITCH"),atChild("Id","/tabpORDER"),atChild("Id","/ssubSUBSCREEN_HEADER:ZEDIWL01:9100/ctxtSEL_ADAT-LOW")), true)[0];
			//return (SAPGuiTextTestObject)dialog().find(atDescendant("Id","/ssubSUBSCREEN_HEADER:ZEDIWL01:9100/ctxtSEL_ADAT-LOW"))[0];
		}
		public SAPGuiTextTestObject orderStatusTextBox(){
			return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSWITCH"),atChild("Id","/tabpORDER"),atChild("Id","/ssubSUBSCREEN_HEADER:ZEDIWL01:9100/ctxtSEL_STA-LOW")), true)[0];

		}
		public SAPGuiTextTestObject customerOrderTextBox(){
			return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSWITCH"),atChild("Id","/tabpORDER"),atChild("Id","/ssubSUBSCREEN_HEADER:ZEDIWL01:9100/txtSEL_BSTN-LOW")), true)[0];

		}

		public SAPGuiToggleTestObject searchOrderButton(){
			return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[7]")), true)[0];
			
		}
		public SAPGuiCtrlGridViewTestObject orderDetailsGrid(){
			return (SAPGuiCtrlGridViewTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ssubSUBSCREEN_BODY:ZEDIWL01:3100/cntlLISTAREA/shellcont/shell")), true)[0];
		}
		
		public SAPGuiToggleTestObject followOnFunctionsButton(){
			 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[34]")), true)[0];
		}
		public SAPGuiToggleTestObject underReviewButton(){
			 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[14]")), true)[0];
		}
		public SAPGuiToggleTestObject createPOButton(){
			 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[23]")), true)[0];
		}
		
		public SAPGuiToggleTestObject sourceofSupplyButton(){
			 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[46]")), true)[0];
		}
}
