package framework.pfstoolsmodel;
import resources.framework.pfstoolsmodel.PFSMainDialogModelHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class PFSMainDialogModel extends PFSMainDialogModelHelper
{
	/**
	 * Script Name   : <b>PFSMainDialogModel</b>
	 * Generated     : <b>Sep 9, 2013 4:29:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/09
	 * @author zsadeque
	 */
	
	public TopLevelSubitemTestObject pfsToolDialog(){
		return pfsMessageTestToolwindow();
	}
	public ShopConfirmTab shopConfirmTab(){
		return new ShopConfirmTab();
	}
	public ShipConfirmTab shipConfirmTab(){
		return new ShipConfirmTab();
	}
	public WebServiceTab webServiceTab(){
		return new WebServiceTab();
	}
	public class ShopConfirmTab{
		public ShopConfirmTab(){
			
		}
		
		public SelectGuiSubitemTestObject chkPOTypeListBox(){
			return chkPOTypeListBox();
		}
		public GuiTestObject getOpenPOButton(){
			return getOpenPObutton();
		}
	}
	public class ShipConfirmTab{
		public ShipConfirmTab(){
			
		}
		public GuiTestObject getShipPOButton(){
			return getShipPObutton();
		}
		public GuiTestObject shipPOButton(){
			return shipPObutton();
		}
		public ToggleGUITestObject xmlOnlyCheckBox(){
			return xmlOnlyNoUpdatescheckBox();
		}
	}
	public class WebServiceTab{
		public WebServiceTab(){
			
		}
		public TextGuiSubitemTestObject xmlTextBox(){
			return txtXMLtext();
		}
		
		public GuiTestObject callWebServiceButton(){
			return callWebServicebutton();
		}
		
	}
	public TextGuiSubitemTestObject orderNumberTextBox(){
		return txtOrdertext();
	}
	public SelectGuiSubitemTestObject poListBox(){
		return lstPOlist();
	}
	public GuiSubitemTestObject poListMenuBar(){
		return contextMenuStrip1toolBar();
	}
	public TextGuiSubitemTestObject statusTextMsgBox(){
		return txtMsgtext();
	}
	public TextSelectGuiSubitemTestObject endPointComboBox(){
		return cboEndPointcomboBox();
	}
	public GuiTestObject pingButton(){
		return pingButton();
	}
	public GuiSubitemTestObject tabList(){
		return tbcActionspageTabList();
	}

	public void testMain(Object[] args) 
	{
		
		// Window: PFSTool.exe: PFS Message Test Tool
/*		lstPOlist().click(RIGHT, atText("1100932218"));
		
		// 
		contextMenuStrip1toolBar().click(atPath("Confirm PO"));
		
		// Window: PFSTool.exe: PFS Message Test Tool
		lstPOlist().performTest(lstPO_listVP());
		String lstPO_SelectedValue = (String)lstPOlist().getProperty("SelectedValue");
		*/
		// Window: PFSTool.exe: PFS Message Test Tool
		// TabbedPage: 0: Shop Confirm
		
		// Window: PFSTool.exe: PFS Message Test Tool
		tbcActionspageTabList().click(atText("Ship Confirm"));
		//getOpenPObutton().click();
		
	
	}
	
}

