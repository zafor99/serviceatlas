package tempscripts;
import resources.tempscripts.EANAvalabilityHelper;
import utils.GiftCardUtil;
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

import framework.BaseService;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class EANAvalability extends EANAvalabilityHelper
{
	/**
	 * Script Name   : <b>GiftCardGeneration</b>
	 * Generated     : <b>Dec 11, 2013 11:31:27 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/12/11
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\EANs Availability.xls";
		String url,locale = null;
		int count=0;
		SpreadSheetUtil excelSheet = null;
		String ean = null;
		IResponse response = null; 
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		for(int i=1;i<51;i++){
			excelSheet.readRow(i);
			ean = excelSheet.getCellStringValue("Sample EANs");
			locale = excelSheet.getCellStringValue("Geo");
			url = "https://csqa2.barnesandnoble.com/bncloud/api/internal/pds/?ean="+ean+"&retailer=NOK&region="+locale;
			System.out.println(ean);
			System.out.println(url);
			response = caliber().get(url);
			System.out.println(response.getBody());
			count = BaseService.getNodeCount(response, "/response/products/product");
			System.out.println(count);
			if(count == 1){
				excelSheet.setCellStringValue("Availability", "Available");
			}
			else{
				excelSheet.setCellStringValue("Availability", "Not Available");
			}
		}

	}
}

