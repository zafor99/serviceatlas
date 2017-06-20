package framework.pfstoolsmodel;
import resources.framework.pfstoolsmodel.LoginDialogModelHelper;
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
public class LoginDialogModel extends LoginDialogModelHelper
{
	/**
	 * Script Name   : <b>LoginDialogModel</b>
	 * Generated     : <b>Sep 9, 2013 3:56:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/09
	 * @author zsadeque
	 */
	
	
	public TextGuiSubitemTestObject userIDTextBox(){
		return txtUserIDtext();
	}
	
	public TextGuiSubitemTestObject passwordTextBox(){
		return txtPasswordtext();
	}
	public GuiTestObject okButton(){
		return _OKbutton();
	}
	public void testMain(Object[] args) 
	{
		
	}
}

