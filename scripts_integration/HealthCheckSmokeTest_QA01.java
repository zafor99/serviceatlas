package scripts_integration;
import java.sql.SQLException;

import resources.scripts_integration.HealthCheckSmokeTest_QA01Helper;
import utils.ActiveMQUtil;
import utils.EmailUtil;
import utils.EnvironmentUtility;

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
import com.main.SerializeToDatabase;
import com.main.VpUtils;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class HealthCheckSmokeTest_QA01 extends HealthCheckSmokeTest_QA01Helper
{
	/**
	 * Script Name   : <b>HealthCheckShell</b>
	 * Generated     : <b>Aug 21, 2014 2:04:44 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/21
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		try {
			long runId=-1;
			runId = SerializeToDatabase.getLatestRunId();
			framework.HealthCheckSmokeTest.setRunId(runId);
			EnvironmentUtility.setEnvironment("QA01");
			framework.HealthCheckSmokeTest.setBuildNumber("Test_Build_1");
			framework.HealthCheckSmokeTest.setEnvId(4);
			framework.HealthCheckSmokeTest.setServerName( VpUtils.getServerName(EnvironmentUtility.caliber().serverName()));
			framework.HealthCheckSmokeTest.start();

			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			callScript("scripts_integration.Domestic.ebookPurchase.bn_comBNWallet.TC01_eBookPurchaseBN");
			callScript("scripts_integration.Domestic.ebookPurchase.bn_comBNWallet.TC02_eBookPurchaseUK");
			callScript("scripts_integration.Domestic.ebookPurchase.bn_comMSWallet.TC01_eBookPurchaseMS");
			callScript("scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet.TC01_eSubPurchaseUS");
			callScript("scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet.TC02_eSubPurchaseUK");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			framework.HealthCheckSmokeTest.end();
			//EmailUtil.send("Scripts integration Failed", e.getStackTrace().toString());
		}
		framework.HealthCheckSmokeTest.end();

	}
}

