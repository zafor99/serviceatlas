package framework;

import utils.SpreadSheetUtil;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 19, 2014
 */
public class ShipMethodsService
{
	private static String filePath = "W:\\QA Automation\\Mercury_Ship_Methods_updated_ 3-5-12.xls";
	private static String timeToDeliver = "";
	private static String serviceLevel = "";
	private static String shipMethod = "";
	private static String product = "";
	private static String perOrder = "";
	private static String perItem = "";
	
	public ShipMethodsService()
	{
		
	}
	
	/*
	 * Returns the index of the column. If colHeader is not found in neither first row nor second row then it returns -1
	 * @param String colHeader
	 * @return the column index
	 */
	
	public static int getCoulmnIndex(String colHeader)
	{
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		String current1 = "-";
		String current2 = "-";
		org.apache.poi.ss.usermodel.Cell cell1 = null;
		org.apache.poi.ss.usermodel.Cell cell2 = null;
		boolean found = false;
		
		sheet.readRow(0);
		int c=0;
		int index=0;
		int d=0;
		
		while(!current1.equals(colHeader) )
		{
			sheet.readRow(0);  	
			cell1 = sheet.getCell(c);   
			if(cell1 != null)
			{
				current1 = cell1.toString().trim();
				if(current1.equals(colHeader))
				{
					found = true;
				}
								
			}
			else
			{
				break;
			}
			
			c++;
			
			
		}
		
		if(!found)  //if text is not found in the 1st row then search it in 2nd row
		{
			sheet.readRow(1);
			while(!current2.equals(colHeader))
			{
				sheet.readRow(1);  	
				cell2 = sheet.getCell(d);   
				if(cell2 != null)
				{
					current2 = cell2.toString().trim(); 
					
				}
								
				d++;
				if(d == 20)
				{
					return -1;
				}
				
			}
			index = d-1;
			
		}
		else
		{
			index = c-1;
		}
		 
		
		return index;
	}
	
	
	public static int getRowIndex(String region, String serviceLevel)
	{
		
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		String current = "-"; 
		sheet.readRow(0);
		int i=0;
		int region_index = 0;
		int index = 0;
		org.apache.poi.ss.usermodel.Cell cell = null;
		
		while(!current.equals(region))
		{
			sheet.readRow(i);  	
			cell = sheet.getCell(0);   
			if(cell != null)
			{
				current = cell.toString().trim(); 
				
			}
			
			i++;
			if(i==150)
			{
				return -1;
				
			}
			//System.out.println("i = " + i);
			
		}
		
		region_index = i-1;  //Index of Domestic, Domestic Extensions, Canada, International and so on ...
		return region_index;
	}
	
	
	
	//This method needs to be fixed
	public static int getRowIndex(String region, String serviceLevel, String extension)
	{
		
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		String current = "-"; 
		sheet.readRow(0);
		int i=0;
		int region_index = 0;
		int index = 0;
		org.apache.poi.ss.usermodel.Cell cell = null;
		
		while(!current.equals(region))
		{
			sheet.readRow(i);  	
			cell = sheet.getCell(0);   
			if(cell != null)
			{
				current = cell.toString().trim(); 
				
			}
			
			i++;
			if(i==150)
			{
				return -1;
				
			}
			//System.out.println("i = " + i);
			
		}
		 
		region_index = i-1;  //Index of Domestic, Domestic Extensions, Canada, International and so on ...
		
		//Search at least 30 rows below region_index to find out Service Level Index
		boolean found = false;
		String current2 = "-"; 
		sheet.readRow(region_index + 1);
		org.apache.poi.ss.usermodel.Cell cell2 = null;
		boolean extensionFlag = !extension.equals("") && extension != null;
		int k = 0;
		for(k=region_index + 1; k<=30; k++)
		{
			if(!extensionFlag)
			{
				if(!current2.equals(serviceLevel))
				{
					sheet.readRow(k);  	
					cell2 = sheet.getCell(0);   
					if(cell2 != null)
					{
						current2 = cell2.toString().trim();  
						
					}
								
					
				}
				else
				{
					break;
				}
			}
			else
			{
				// Code for region extensions
			}
		}
		
		index = k - 1;
		return index;
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
	
	//*** PRICING PER ORDER
	public static double bookPricingPerOrder(String region, String serviceLevel)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Order");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel));
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	public static double bookPricingPerOrder(String region, String serviceLevel,String extension)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Order");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel),extension);
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	
	public static double musicDVDPricingPerOrder(String region, String serviceLevel)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Order");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel),null) + 1;
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	
	public static double musicDVDPricingPerOrder(String region, String serviceLevel,String extension)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Order");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel),extension)+1;
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	//*** PRICING PER ITEM
	public static double bookPricingPerItem(String region, String serviceLevel)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Item");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel));
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	public static double bookPricingPerItem(String region, String serviceLevel, String extension)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Item");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel),extension);
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	public static double musicDVDPricingPerItem(String region, String serviceLevel)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Item");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel))+1;
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
	public static double musicDVDPricingPerItem(String region, String serviceLevel, String extension)
	{
		double price = 0.0;
		int colIndex = getCoulmnIndex("Per Item");
		int rowIndex = getRowIndex(region,AbbrevAndConstants.DOMESTIC_STANDARD_DELIVERY(serviceLevel),extension)+1;
		SpreadSheetUtil sheet = new SpreadSheetUtil(filePath, 0);
		org.apache.poi.ss.usermodel.Cell cell = null;
		sheet.readRow(rowIndex);
		String priceString = sheet.getCell(colIndex).toString().trim(); 
		price = convertToDouble(priceString);
		return price;
	}
}
