package scripts.outboudsapactions.withoutingestion.mswallet;
import resources.scripts.outboudsapactions.withoutingestion.mswallet.ExcelDataResetHelper;
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
public class ExcelDataReset extends ExcelDataResetHelper
{
	/**
	 * Script Name   : <b>ExcelDataReset</b>
	 * Generated     : <b>Jul 24, 2014 11:33:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/24
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		resetOutboundMSWalletOrderExcelData();
	}
}

