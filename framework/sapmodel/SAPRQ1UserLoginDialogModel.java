package framework.sapmodel;
import resources.framework.sapmodel.SAPRQ1UserLoginDialogModelHelper;
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
 * @author fahmed1
 */
public class SAPRQ1UserLoginDialogModel extends SAPRQ1UserLoginDialogModelHelper
{
	/**
	 * Script Name   : <b>SAPRQ1UserLoginDialogModel</b>
	 * Generated     : <b>May 3, 2013 3:11:49 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/03
	 * @author fahmed1
	 */
	public SAPTopLevelTestObject dialog(){
		return window_sap();
	}
	
	public SAPGuiTextTestObject userTextBox(){
		return text_rsystbname();
	}
	
	public SAPGuiTextTestObject passwordTextBox(){
		return text_rsystbcode();
	}
	
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		// Window: SAP
		//text_rsystbname().setText("FAHMED");
		//text_rsystbcode().setText("********");
		//text_rsystbcode().setCaretPosition(8);
		//window_sap().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		
		
		
		
	}
}

