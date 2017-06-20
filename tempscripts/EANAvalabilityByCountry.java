package tempscripts;
import resources.tempscripts.EANAvalabilityByCountryHelper;
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
public class EANAvalabilityByCountry extends EANAvalabilityByCountryHelper
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
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\EANsAvailabilityByCountry.xls";
		String url = null;
		//String locale [] = {"AR","AT","BR","IN","IE","JP","MX","NZ","RU","ZA"};

		//update the list of Locale
		String locale [] = {"AR","AT","BR","IN","IE","JP","MX","NZ","NO","PL","RU","ZA","KR","SE"};
		int count=0;
		SpreadSheetUtil excelSheet = null;
		String ean = null;
		IResponse response = null; 
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		// update the number of EAN
		for(int i=1;i<61;i++){
			for(int j = 0;j<locale.length;j++){
				excelSheet.readRow(i);
				ean = excelSheet.getCellStringValue("Sample EANs");
				System.out.println("Locale is "+ locale[j]);
				url = "https://csqa2.barnesandnoble.com/bncloud/api/internal/pds/?ean="+ean+"&retailer=NOK&region="+locale[j];
				System.out.println(ean);
				System.out.println(url);
				response = caliber().get(url);
				System.out.println(response.getBody());
				count = BaseService.getNodeCount(response, "/response/products/product");
				System.out.println(count);
				if(count == 1){
					excelSheet.setCellStringValue(locale[j], "Available");
				}
				else{
					excelSheet.setCellStringValue(locale[j], "Not Available");
				}
				
			}
		}

	}
}

