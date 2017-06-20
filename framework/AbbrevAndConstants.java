package framework;

import java.util.HashMap;
import java.util.Map;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  April 11, 2014
 */
public class AbbrevAndConstants 
{
	private static Map stateAbbrev = new HashMap();
	private static Map countryAbbrev = new HashMap();
	private static Map productTypeAbbrev = new HashMap();
	
	
	private static final String DOMESTIC_STANDARD = "";
	
	
	public AbbrevAndConstants()
	{
		setStateAbbrev();
		setCountryAbbrev();
	}
	
	public static String DOMESTIC_STANDARD_DELIVERY(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("Standard Delivery") || value.equalsIgnoreCase("StandardDelivery") || value.equalsIgnoreCase("STD") || value.equalsIgnoreCase("Standard"))
		{
			str = "Standard Delivery";
		}
		return str;
		
	}
	public static String DOMESTIC_EXPRESS_DELIVERY(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("Express Delivery") || value.equalsIgnoreCase("ExpressDelivery") || value.equalsIgnoreCase("EDO") || value.equalsIgnoreCase("Express"))
		{
			str = "Express Delivery";
		}
		return str;
		
	}
	public static String DOMESTIC_AIRSERVICE_DELIVERY(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("Air Service") || value.equalsIgnoreCase("AirService") || value.equalsIgnoreCase("2ND") || value.equalsIgnoreCase("Air"))
		{
			str = "Air Service";
		}
		return str;
		
	}
	public static String DOMESTIC_EXPEDITED_DELIVERY(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("Expedited Delivery") || value.equalsIgnoreCase("ExpeditedDelivery") || value.equalsIgnoreCase("NXD") || value.equalsIgnoreCase("Expedited"))
		{
			str = "Expedited Delivery";
		}
		return str;
		
	}
	
	
	public static String CANADA_STANDARD_DELIVERY(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("Standard Delivery") || value.equalsIgnoreCase("StandardDelivery") || value.equalsIgnoreCase("CAN") || value.equalsIgnoreCase("Standard"))
		{
			str = "Standard";
		}
		return str;
		
	}
	public static String CANADA_EXPRESS_DELIVERY(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("Express Delivery") || value.equalsIgnoreCase("ExpressDelivery") || value.equalsIgnoreCase("CEX") || value.equalsIgnoreCase("Express"))
		{
			str = "Express";
		}
		return str;
		
	}
	public static String INTERNATIONAL_AIRMAIL(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("International Airmail") || value.equalsIgnoreCase("InternationalAirmail") || value.equalsIgnoreCase("IRM") || value.equalsIgnoreCase("Airmail"))
		{
			str = "International Airmail";
		}
		return str;
		
	}
	
	public static String INTERNATIONAL_PRIORITY_AIRMAIL(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("International Priority Airmail") || value.equalsIgnoreCase("InternationalPriorityAirmail") || value.equalsIgnoreCase("IPA") || value.equalsIgnoreCase("Priority Airmail") ||  value.equalsIgnoreCase("PriorityAirmail"))
		{
			str = "International Priority Airmail";
		}
		return str;
		
	}
	
	public static String INTERNATIONAL_EXPRESS(String value)
	{
		String str = "";
		if(value.equalsIgnoreCase("International Express") || value.equalsIgnoreCase("InternationalExpress") || value.equalsIgnoreCase("IPR") || value.equalsIgnoreCase("Express"))
		{
			str = "International Express";
		}
		return str;
		
	}
	//// *******************
	
	public static String getStateAbbrev(String stateFullName)
	{
		return stateAbbrev.get(stateFullName).toString();
	}
	public static void  setStateAbbrev()
	{
		stateAbbrev.put("ALABAMA","AL");
	    stateAbbrev.put("ALASKA","AK");
	    stateAbbrev.put("AMERICAN SAMOA","AS");
	    stateAbbrev.put("ARIZONA","AZ");
	    stateAbbrev.put("ARKANSAS","AR");
	    stateAbbrev.put("CALIFORNIA","CA");
	    stateAbbrev.put("COLORADO","CO");
	    stateAbbrev.put("CONNECTICUTT","CT");
	  
	    stateAbbrev.put("DELAWARE","DE");
	    stateAbbrev.put("DISTRICT OF COLUMBIA","DC");
	    stateAbbrev.put("FEDERATED STATES OF MICRONESIA","FM");
	    stateAbbrev.put("FLORIDA","FL");
	    stateAbbrev.put("GEORGIA","GA");
	    stateAbbrev.put("GUAM","GU");
	    stateAbbrev.put("HAWAII","HI");
	    stateAbbrev.put("IDAHO","ID");
	  
	    stateAbbrev.put("ILLINOIS","IL");
	    stateAbbrev.put("INDIANA","IN");
	    stateAbbrev.put("IOWA","IA");
	    stateAbbrev.put("KANSAS","KS");
	    stateAbbrev.put("KENTUCKY","KY");
	    stateAbbrev.put("LOUISIANA","LA");
	
	    stateAbbrev.put("MAINE","ME");
	    stateAbbrev.put("MARSHALL ISLANDS","MH");
	    stateAbbrev.put("MARYLAND","MD");
	    stateAbbrev.put("MASSACHUSETTS","MA");
	    stateAbbrev.put("MICHIGAN","MI");
	    stateAbbrev.put("MINNESOTA","MN");
	  
	    stateAbbrev.put("MISSISSIPPI","MS");
	    stateAbbrev.put("MISSOURI","MO");
	    stateAbbrev.put("MONTANA","MT");
	    stateAbbrev.put("NEBRASKA","NE");
	    stateAbbrev.put("NEVADA","NV");
	    stateAbbrev.put("NEW HAMPSHIRE","NH");
	  
	    stateAbbrev.put("NEW JERSEY","NJ");
	    stateAbbrev.put("NEW MEXICO","NM");
	    stateAbbrev.put("NEW YORK","NY");
	    stateAbbrev.put("NORTH CAROLINA","NC");
	    stateAbbrev.put("NORTH DAKOTA","ND");
	    stateAbbrev.put("NORTHERN MARIANA ISLANDS","MP");
	  
	    stateAbbrev.put("OKLAHOMA","OK");
	    stateAbbrev.put("OREGON","OR");
	    stateAbbrev.put("PALAU","PW");
	    stateAbbrev.put("PENNSYLVANIA","PA");
	    stateAbbrev.put("PUERTO RICO","PR");
	    stateAbbrev.put("RHODE ISLAND","RI");
	    stateAbbrev.put("SOUTH CAROLINA","SC");
	    stateAbbrev.put("SOUTH DAKOTA","SD");
	  
	    stateAbbrev.put("TENNESSEE","TN");
	    stateAbbrev.put("TEXAS","TX");
	    stateAbbrev.put("UTAH","UT");
	    stateAbbrev.put("VERMONT","VT");
	    stateAbbrev.put("VIRGIN ISLANDS","VI");
	    stateAbbrev.put("VIRGINIA","VA");
	    stateAbbrev.put("WASHINGTON","WA");
	    stateAbbrev.put("WEST VIRGINIA","WV");
	    stateAbbrev.put("WISCONSIN","WI");
	    stateAbbrev.put("WYOMING","WY");
    
	}
	
	public static String getCountryAbbrev(String country)
	{
		return countryAbbrev.get(country).toString();
	}
	public static void  setCountryAbbrev()
	{
		countryAbbrev.put("United States","USA");
		countryAbbrev.put("Canada","CA");
		//..... Add more countries here
	}
	public static void  setProductTypeAbbrev()
	{
		productTypeAbbrev.put("Books","AL");
	   
	}
}
