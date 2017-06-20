package tempscripts;
import resources.tempscripts.MSWalletDomHelper;
import utils.BNTimer;
import utils.MSWalletIPEnvUtil;
import utils.SpreadSheetUtil;

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
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

import framework.CustomerUserAccount;
/**
 * Description   : Functional Test Script
 * @author fahmed1
 */
public class MSWalletDom extends MSWalletDomHelper
{
	/**
	 * Script Name   : <b>MSWallet</b>
	 * Generated     : <b>Mar 21, 2014 12:39:32 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/21
	 * @author fahmed1
	 */
	public void testMain(Object[] args) 
	{
		String customerID,requestCode,accessToken,msEmailAddress = "";
		BNTimer time = new BNTimer();

		msWalletIPEnv().readClientInfo("US", "WEB");
		mswalletTool().startApp();
		mswalletTool().enterCredentials("QA06", msWalletIPEnv().getClientType(), msWalletIPEnv().getUserName(), msWalletIPEnv().getPassword());
		msEmailAddress = msWalletIPEnv().getUserName();
		mswalletTool().clickRequestCodeButton();
		requestCode = mswalletTool().getRequestCode();
		mswalletTool().clickAccessTokenButton();
		accessToken = mswalletTool().getAccessToken();
		if(accessToken.length()>2){
			
			//Creating Domestic user ID
			customerUserAccount().createAccount();
			customerID = customerUserAccount().getCustomerId();
			String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAccountBinding.xls";
			SpreadSheetUtil excelSheet = null;
			excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);

			excelSheet.readRow(1);
			excelSheet.setCellStringValue("Microsoft UserID", msEmailAddress);
			excelSheet.setCellStringValue("Access Token", accessToken);
			excelSheet.setCellStringValue("Date",time.getCurrentDate());
			excelSheet.setCellStringValue("Caliber UserID", customerID);
			
			
			
		}
		else{
			logError("Access Token is not generated");
		}
		mswalletTool().closeApp();
	}
}

