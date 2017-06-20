package framework.sapmodel;
import resources.framework.sapmodel.SAPLogon720PageModelHelper;
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
public class SAPLogon720PageModel extends SAPLogon720PageModelHelper
{
	/**
	 * Script Name   : <b>SAPLogon720PageModel</b>
	 * Generated     : <b>May 3, 2013 2:58:24 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/03
	 * @author fahmed1
	 */
	
	public TopLevelSubitemTestObject dialog(){
		return sapLogon730window();
	}
	
	
	public SelectGuiSubitemTestObject sytemListView(){
		return sysListView32table2();
	}
	
	public void testMain(Object[] args) 
	{
		
				
	}
}

