package framework;

import utils.SpreadSheetUtil;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 19, 2014
 */
public class ShippingRatesService 
{
	private static String filePath = "\\\\bnyfsfile05\\qadept\\QA Automation\\ShippingRatesForHeavyItems.xls";
	//private static String filePath = "W:\\QA Automation\\H1-H2-H3ShippingRatesinOPM3-updated3-5-12.xls";
	
	
	
	public ShippingRatesService()
	{
		
	}
	
	
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
	
	public static double getShippingRate(double weightInLbs, String region,String serviceLevel)
	{
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		sheet.readRow(getRowIndex(weightInLbs));
		int c= getColumnIndex(region,serviceLevel);
		org.apache.poi.ss.usermodel.Cell cell = null;
		
		double shippingRate = 0.0;
		
		
		cell = sheet.getCell(c);
		if(cell != null)
		{
			shippingRate = convertToDouble(cell.toString());
		}
		else
		{
			shippingRate = 0.0;
			
		}
		//shippingRate = convertToDouble(current);		
		return shippingRate;
			
	}
	
	private static int getRowIndex(double weightInLbs)
	{
		int excelRowIndex = 0;
		if(weightInLbs>=5.01 && weightInLbs <= 10.00)
		{
			excelRowIndex = 5;   
		}
		else if (weightInLbs>=10.01 && weightInLbs <= 20.00)
		{
			excelRowIndex = 6;
		}
		else if(weightInLbs>=20.01)
		{
			excelRowIndex = 7;
		}
		return excelRowIndex;
	}
	
	private static int getColumnIndex(String region, String serviceLevel)
	{
		int excelColumnIndex = 0;
		if(serviceLevel.equalsIgnoreCase("Weight (lbs)"))
		{
			excelColumnIndex = 0;
			
		}
		else if(serviceLevel.equalsIgnoreCase("Shipping Code"))
		{
			excelColumnIndex = 1;
		}
		else if(region.equalsIgnoreCase("Domestic Shipments") && serviceLevel.equalsIgnoreCase("Standard Delivery"))
		{
			excelColumnIndex = 2;
		}
		else if(region.equalsIgnoreCase("Domestic Shipments") && serviceLevel.equalsIgnoreCase("Express"))
		{
			excelColumnIndex = 3;
		}
		else if(region.equalsIgnoreCase("Domestic Shipments") && serviceLevel.equalsIgnoreCase("Air Service"))
		{
			excelColumnIndex = 4;
		}
		 
		 else if(region.equalsIgnoreCase("Domestic Shipments") && serviceLevel.equalsIgnoreCase("Expedited Air Service"))
		{
				excelColumnIndex = 5;
		}
		else if(region.equalsIgnoreCase("Domestic Extensions") && serviceLevel.equalsIgnoreCase("Standard Delivery"))
		{
			excelColumnIndex = 6;
		}
		else if(region.equalsIgnoreCase("Domestic Extensions") && serviceLevel.equalsIgnoreCase("Express"))
		{
			excelColumnIndex = 7;
		}
		else if(region.equalsIgnoreCase("Canada") && serviceLevel.equalsIgnoreCase("Standard Delivery"))
		{
			excelColumnIndex = 8;
		}
		else if(region.equalsIgnoreCase("Canada") && serviceLevel.equalsIgnoreCase("Express"))
		{
			excelColumnIndex = 9;
		}
		else if(region.equalsIgnoreCase("International") && serviceLevel.equalsIgnoreCase("Int'l Airmail"))
		{
			excelColumnIndex = 10;
		}
		
		else if(region.equalsIgnoreCase("International") && serviceLevel.equalsIgnoreCase("Int'l Priority Airmail"))
		{
			excelColumnIndex = 11;
		}

		else if(region.equalsIgnoreCase("International") && serviceLevel.equalsIgnoreCase("Int'l Express"))
		{
			excelColumnIndex = 12;
		}

		
			
		
				
		return excelColumnIndex;
	}
}
