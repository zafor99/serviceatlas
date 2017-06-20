package framework;

import utils.XMLUtility;
import utils.SpreadSheetUtil;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 25, 2014
 */
public  class TaxRulesAndRatesInstantPurchase extends JCheckoutServices
{
	private static String filePath = "W:\\QA Automation\\TaxRulesAndRates_InstantPurchase.xls";
	public static SpreadSheetUtil m_SpreadSheet = null;
	
	public static SpreadSheetUtil taxAndRateSpreadSheet(){
		if(m_SpreadSheet==null){
			m_SpreadSheet = new SpreadSheetUtil(filePath, 0);
		}
		return m_SpreadSheet;		
	}

	public TaxRulesAndRatesInstantPurchase()
	{		
			
	}
	/*
	 * Returns tax amount of price
	 * @param the price and taxRate
	 * @return (price * taxRate)
	 */
	public double tax(double price, double taxRate)
	{
		double taxAmount = 0.0;
		taxAmount = price * taxRate;
		return roundTwoDecimals(taxAmount);
	}
/*	
	 * Returns tax_rate/100 if City-Zip is found in the excel sheet, else returns -1.0
	 * @param the concatenated string of city and zip as shown in the excel sheet
	 * @return tax_rat/100
	 
	public static double convertToDouble(String value)
	{
		if(value.equals("") || value == null)
		{
			return 0.0;
		}
		else
		{
			return Double.parseDouble(value);
		}
	}
	
	public static int convertToInteger(String value)
	{
		if(value.equals("") || value == null)
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(value);
		}
	}
	*/
	
	public static double getTaxRateByCounty(String county)
	{
		double taxRate = 0.0;
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		String current = "-"; 
		sheet.readRow(0);
		int i=2;
		String rate = "";
		org.apache.poi.ss.usermodel.Cell cell = null;
		while(!current.equals(county))
		{
			sheet.readRow(i);  	
			cell = sheet.getCell(2);   //assuming this is the County column
			if(cell != null)
			{
				current = cell.toString(); 
				rate = sheet.getCell(3).toString(); //reading rate
			}
			else
			{
				rate = "-1.0";
				break;
				
			}
			i++;
			
		}
		 
		taxRate = convertToDouble(rate);
		return taxRate;
	}

	public boolean verifyTaxCalculationFromResponseXML(String vpName, IResponse response, boolean isTaxable){
		boolean result = false;
		//Tax Rate is Column 8.
		System.out.print(taxAndRateSpreadSheet().getCellStringValue(8));
		result = verifyTaxCalculationFromResponseXML(vpName, response, taxAndRateSpreadSheet().getCellStringValue(8),isTaxable);
		
		return result;
	}
	/**
	 * Verify the Tax Calculation from the Response XML
	 * @param vpName
	 * @param response
	 * @param taxRate
	 * @param isTaxable
	 * @return
	 */
	public boolean verifyTaxCalculationFromResponseXML(String vpName,IResponse response,String taxRate,boolean isTaxable){
		boolean result = false;
		String xmlDoc = "";
		String[][] actual = new String [2][2];
		String[][] baseLine = new String [2][2];
		
		xmlDoc = response.getBody();

		logResponseXml(xmlDoc);
		
		XMLUtility responseXML = new XMLUtility();
		responseXML.readFromString(xmlDoc);
		
		actual[0][0] = "Tax Rate";
		actual[1][0] = "Payment Allocation";
		
		System.out.println("Root : " + responseXML.m_RootNode.getLocalName());
		
		//Tax Rate from the response
		actual[0][1] =	roundTwoDecimals(convertToDouble(getNodeValue(response, JCHECKOUT_CARTITEM_XPATH +"/@taxRate")))+"";
		//paymentAmount from allocations 
		actual[1][1]= roundTwoDecimals(convertToDouble(getNodeValue(response, JCHECKOUTDOC_IP_ALLOCATION+"/@paymentAmount")))+"";

		baseLine[0][0] = "Tax Rate";
		baseLine[1][0] = "Payment Allocation";
		
		//Getting tasRate from the Excel file
		if(isTaxable){
			baseLine[0][1] =roundTwoDecimals(convertToDouble(taxRate)) + "";	
		}else{
			taxRate = "0";
			baseLine[0][1] =roundTwoDecimals(convertToDouble(taxRate)) + "";
		}
			
		
		//paymentAmount= Sum of cartItemTotal + orderTaxTotal
		baseLine[1][1] = roundTwoDecimals(getOrderTotal_groupTypeBN(response))+"";
		
		ITestData actualData = VpUtil.getTestData(actual);
		ITestData expectedData = VpUtil.getTestData(baseLine);
		
		result = vpManual(vpName, expectedData,actualData).performTest();
		
		return result;
		
	}
	/**
	 * Verify the Tax Calculation from the Response XML
	 * @param vpName
	 * @param response
	 * @param taxRate
	 * @param isTaxable
	 * @return
	 */
	public boolean verifyTaxCalculationFromResponseJSON(String vpName,IResponse response,String taxRate,boolean isTaxable,String type){
		boolean result = false;
		String xmlDoc = "";
		String[][] actual = new String [2][2];
		String[][] baseLine = new String [2][2];
		
		/*xmlDoc = response.getBody();
		getExecutingScript().logResponseXml(xmlDoc);
		
		CaliberXMLUtility responseXML = new CaliberXMLUtility();
		responseXML.readFromString(xmlDoc);*/
		
		actual[0][0] = "Tax Rate";
		actual[1][0] = "Payment Allocation";
		
	//	System.out.println("Root : " + responseXML.m_RootNode.getName());
		
		if(type.equalsIgnoreCase("xml")){
			//Tax Rate from the response
			actual[0][1] =	roundTwoDecimals(convertToDouble(getNodeValue(response, JCHECKOUT_CARTITEM_XPATH +"/@taxRate")))+"";
			//paymentAmount from allocations 
			actual[1][1]= roundTwoDecimals(convertToDouble(getNodeValue(response, JCHECKOUTDOC_IP_ALLOCATION+"/@paymentAmount")))+"";
		}
		else{
		//	String xml_response = convertJSONtoXML(response.getBody());

			//Tax Rate from the response
			actual[0][1] =	roundTwoDecimals(convertToDouble(getNodeValue(response, "//shipments/e/cartitems/e/taxRate")))+"";
			//paymentAmount from allocations 
			actual[1][1]= roundTwoDecimals(convertToDouble(getNodeValue(response, JCHECKOUT_ALLOCATION_PAYMENTAMOUNT_JPATH)))+"";
			System.out.print(actual[1][1]);
		}
		

		baseLine[0][0] = "Tax Rate";
		baseLine[1][0] = "Payment Allocation";
		
		//Getting tasRate from the Excel file
		if(isTaxable){
			baseLine[0][1] =roundTwoDecimals(convertToDouble(taxRate)) + "";	
		}else{
			taxRate = "0";
			baseLine[0][1] =roundTwoDecimals(convertToDouble(taxRate)) + "";
		}
			
		
		//paymentAmount= Sum of cartItemTotal + orderTaxTotal
		baseLine[1][1] = roundTwoDecimals(getOrderTotal_groupTypeBN(response,type))+"";
		
		ITestData actualData = VpUtil.getTestData(actual);
		ITestData expectedData = VpUtil.getTestData(baseLine);
		
		result = vpManual(vpName, expectedData,actualData).performTest();
		
		return result;
		
	}

	public boolean verifyTotalTax(String vpName,IResponse response,double taxRate){
		boolean result = false;
		double totalPrice = getCartItemTotal_groupTypeBN(response);
		double totalTax = tax(totalPrice, taxRate);
		double actualTotalTax = getOrderTaxTotal_groupTypeBN(response);
		
		result = vpManual(vpName, totalTax, actualTotalTax).performTest(); 
		
		return result;
	}
	public boolean verifyTotalTax(String vpName,IResponse response,double taxRate,String type){
		boolean result = false;
		double totalPrice = getCartItemTotal_groupTypeBN(response,type);
		double totalTax = tax(totalPrice, taxRate);
		double actualTotalTax = getOrderTaxTotal_groupTypeBN(response);
		
		result = vpManual(vpName, totalTax, actualTotalTax).performTest(); 
		
		return result;
	}

	public String modifyAddressXML(String xml){
		xml = setNodeValueByTagName(xml, "line1", taxAndRateSpreadSheet().getCellStringValue("line1"));
		xml = setNodeValueByTagName(xml, "city", taxAndRateSpreadSheet().getCellStringValue("city"));
		xml = setNodeValueByTagName(xml, "state", taxAndRateSpreadSheet().getCellStringValue("state"));
		xml = setNodeValueByTagName(xml, "zip", taxAndRateSpreadSheet().getCellStringValue("zipCode"));
		xml = setNodeValueByTagName(xml, "checkSum", taxAndRateSpreadSheet().getCellStringValue("checkSum"));
		return xml;
	}
}
