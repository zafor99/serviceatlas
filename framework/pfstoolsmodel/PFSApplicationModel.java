package framework.pfstoolsmodel;
import resources.framework.pfstoolsmodel.PFSApplicationModelHelper;
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
public class PFSApplicationModel extends PFSApplicationModelHelper
{
	/**
	 * Script Name   : <b>PFSApplicationModel</b>
	 * Generated     : <b>Sep 12, 2013 2:56:51 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/12
	 * @author zsadeque
	 */
	public TopLevelSubitemTestObject mdiDocument(){
		return pfsMessageTestToolwindow();
	}
	public void testMain(Object[] args) 
	{
		
	}
}

