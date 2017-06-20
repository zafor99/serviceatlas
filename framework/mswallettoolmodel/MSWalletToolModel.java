package framework.mswallettoolmodel;
import resources.framework.mswallettoolmodel.MSWalletToolModelHelper;
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
public class MSWalletToolModel extends MSWalletToolModelHelper
{
	/**
	 * Script Name   : <b>MSWalletToolModel</b>
	 * Generated     : <b>Mar 19, 2014 10:46:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/19
	 * @author fahmed1
	 */
	public TopLevelTestObject mswalletMainWindow(){
		return jFrame();
	}
	public GuiTestObject accessTokenButton(){
		return accessToken();
	}
	public TextSelectGuiSubitemTestObject environmentComboBox(){
		return jComboBox();
	}
	
	public TextSelectGuiSubitemTestObject clientTypeComboBox(){
		return jComboBox2();
	}
	public TextSelectGuiSubitemTestObject usernameComboBox(){
		return jComboBox3();
	}
	
	public TextGuiSubitemTestObject requestCodeTextBox(){
		return jTextField3();
	}
	public TextGuiSubitemTestObject passwordTextBox(){
		return jTextField();
	}
	public TextGuiSubitemTestObject countryTextBox(){
		return jTextField2();
	}
	public GuiTestObject manageDefaultPaymentInstrumentButton(){
		return manageDefaultPaymentInstrument();
	}
	public GuiTestObject offlineTokenButton(){
		return offlineToken();
	}
	public GuiTestObject requestCodeButton(){
		return requestCode();
	}
	
	public TextGuiSubitemTestObject accessCodeTextBox(){
		return textPlain();
	}
	public TextGuiSubitemTestObject offlineTokenTextBox(){
		return textPlain2();
	}
	
	public TextGuiSubitemTestObject localeTextBox(){
		return username();
	}
	
	public void testMain(Object[] args) 
	{
		
	}
}

