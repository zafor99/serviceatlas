package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPathConstants;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import utils.BNTimer;
import utils.XMLUtility;
import utils.SpreadSheetUtil;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.xpath.XPathReader;
import com.bn.qa.xobject.vp.XManualVerificationPoint;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 19, 2014
 */



public  class JCheckoutServices extends BaseService implements IJCheckoutConstants
{
	private static String connectionString = "";
	private static String username = "sanjum";
	private static String password = "Noble15";
	private static Statement statement = null;
	private static Connection connection = null; 
	public String jcheckoutRestrictionsCID = "";
	public String jcheckoutIntlAddItemCID  = "";
	public String[][] jIntlPhysicalCheckoutParams = new String [30][5];
	public String intlAddress1 = "<address>  " +
			"                    <city>Leighton Buzzard</city>  " +
			"                    <company>Barnes and Noble</company>  " +
			"                    <countryNum>826</countryNum>  " +
			"                    <firstName>John</firstName>  " +
			"                    <lastName>Smith</lastName>  " +
			"                    <line1>72 North Street</line1>   " +
			"                    <line2></line2>  " +
			"                    <line3></line3>  " +
			"                    <nickName>Nickname</nickName>  " +
			"                    <state>CBF</state>  " +
			"                    <zip>LU7 1EN</zip></address>";
	public String intlAddressAddPaymentXML = "<paymentMethod> " +
		    "                             <creditCard> " +
		    "                             <code>VI</code> " +
		    "                             <expiryMMYYYY>062015</expiryMMYYYY> " +
		    "                             <securityCode>562</securityCode> " +
		    "                             <numberPlain>4012888888881881</numberPlain> " +
		    "                             <isDefault>true</isDefault> " +
		    "                             <address> " +
		    "                             <checkSum>51201</checkSum>" + 
		    "                             <city>Kidderminster</city> " +
		    "                             <company>Barnes and Noble</company> " +
		    "                             <countryNum>826</countryNum> " +
		    "                             <firstName>John</firstName> " +
		    "                             <lastName>Smith</lastName> " +
		    "                             <line1>79-84 Worcester Street</line1> " +
		    "                             <line2></line2> " +
		    "                             <line3></line3> " +
		    "                             <nickName>Nickname</nickName> " +
		    "                             <state></state> " +
		    "                             <zip>DY10 1EH</zip> " +
		    "                             </address> " +
		    "                             </creditCard> " +
		    "                             </paymentMethod>";
	
	private static Logger logger = Logger.getLogger(JCheckoutServices.class);
	
	public final String BASE_JCHECKOUT_URL = envUtil.caliber().serverName();
	protected static String TEST_DATA_PATH = getCurrentProject().getLocation() + "/testData";
	protected final String ADDRESSBOOK_US_TESTDATA_PATH = TEST_DATA_PATH + "/AddressBook.xml";
	private static XMLUtility xmlUtility = null;
	private static CustomerUserAccount customerUserAccount = null;
	
	public static XMLUtility xmlUtility(){
		if(xmlUtility==null){
			xmlUtility =  new XMLUtility();
		}		
		return xmlUtility;
	}
	
public CustomerUserAccount customerUserAccount(){
		
		if(customerUserAccount==null){
			customerUserAccount = new CustomerUserAccount();
		}

		return customerUserAccount;
		
	}
	
	public JCheckoutServices()
	{
		
	}
	
	public static ResultSet getRecord_SQLServerUPDB(String sql) 
	{
		//enter this server into environment
		String URL = "jdbc:sqlserver://injupdb01;integratedSecurity=true";
		ResultSet rs = null;
        try
        {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	connection = java.sql.DriverManager.getConnection(URL,username,password);
        	if(connection!=null) System.out.println("DataBase Connection Successful!");
        	statement = connection.createStatement();
        	rs = statement.executeQuery(sql);
        	
        }
        catch (SQLException ex) 
        {
        	logException(ex);
		}
        catch(ClassNotFoundException ex)
        {
        	logException(ex);
        }
        return rs;
	}
	

	
	public  boolean verifyAddedItems_ProductDataCacheDB(String vpName, IResponse response,String ean)
	{
		boolean connected = false;
		ResultSet rs = null;
		boolean result = false;
		String[][] expectedTable = new String[2][9];
		String[][] actualTable = new String[2][9];
		final String productListingXPath = "/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing";
		
		expectedTable[0][0] = "EAN"; expectedTable[0][1] = "WebFlag"; expectedTable[0][2] = "FulfillmentCode"; expectedTable[0][3] = "DigitalFulfillmentCode"; expectedTable[0][4] = "BnPrice"; expectedTable[0][5] = "RetailPrice"; expectedTable[0][6] = "ProductCode";  expectedTable[0][7] = "ShippingCode"; expectedTable[0][8] = "ShippingCost";
		actualTable[0][0]   = "EAN"; actualTable[0][1] = "WebFlag"; actualTable[0][2] = "FulfillmentCode"; actualTable[0][3] = "DigitalFulfillmentCode"; actualTable[0][4] = "BnPrice"; actualTable[0][5] = "RetailPrice"; actualTable[0][6] = "ProductCode";  actualTable[0][7] = "ShippingCode"; actualTable[0][8] = "ShippingCost";
		
		expectedTable[1][0] = getNodeValue(response, productListingXPath + "/EAN"); 
		expectedTable[1][1] = getNodeValue(response, productListingXPath + "/availability/webFlag");  
		expectedTable[1][2] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/fulfillmentCode"); 
		expectedTable[1][3] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/digitalFulfillmentCode"); 
		expectedTable[1][4] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/bnPrice");  
		expectedTable[1][5] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/retailPrice");  
		expectedTable[1][6] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/productCode");  
		expectedTable[1][7] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/shippingCode");  
		expectedTable[1][8] = getNodeValue(response, productListingXPath + "/availability/orderAndFulfillment/shippingCost");  
		
		
		//String formattedExpectedFieldValue = queryFieldValue.replaceAll("'", "''");
		String query = "select p.EAN,vd.webFlag, p.fulfillmentCode, p.digitalFulfillmentCode,vd.bnPrice,vd.retailPrice, p.productCode,p.productSubTypeCode, p.shippingCode,p.shippingCost " +
		"From proddatacache.dbo.v_pdcgetproduct p Join proddatacache.dbo.v_pdcgetvolatiledata vd On  p.ean = vd.ean " +
		"where p.ean = " + ean;
		System.out.println("Database Query: " + query);
		
		connected = isDBConnected(query, null);
		if(connected){
			rs = getRecord_SQLServerUPDB(query);
			
			try
			{
				rs.next();
				for(int i=1;i<=8;i++)
				{
					actualTable[1][i] = rs.getString(i);
				}
				
				
			}
			catch (Exception ex) 
			{
				logException(ex);
			}
			finally
			{
				try 
				{ 
					rs.close();
					statement.close();
					connection.close();
				
				} 
				
				catch(SQLException ex)
				{
					logException(ex);
				}

			}
			
		}
		
		
		result = performTest(new XManualVerificationPoint(vpName, this, expectedTable, actualTable));
		return result;
	}
	
	public String getNodeValue(String xml, String path)
	{
		String value = "";
		
		XPathReader xPath = new XPathReader(xml);
		NodeList node = (NodeList)xPath.read(path, XPathConstants.NODE);
		if(node!=null)
		{
			value = node.item(0).getNodeValue();
			
		}
		
		return value;
	}
	
	public boolean verifyField_SQLServerUPDB(String vpName, String queryFieldName, String queryFieldValue, String expectedField, String expectedValue)
	{
		boolean connected = false;
		String actualFieldValue = null;
		ResultSet rs = null;
		boolean result = false;
		
		String formattedExpectedFieldValue = queryFieldValue.replaceAll("'", "''");
		String query = "Select " + expectedField + " from UPDB.dbo.UsedProduct where " + queryFieldName + "='" + formattedExpectedFieldValue + "'";
		System.out.println("Database Query: " + query);
		
		connected = isDBConnected(query, null);
		if(connected){
			rs = getRecord_SQLServerUPDB(query);
			
			try
			{
				rs.next();
				actualFieldValue = rs.getString(expectedField);
				System.out.println("Expected Result for filed " + expectedField + " is : " + expectedValue);
				System.out.println("Actual Result for filed " + expectedField + " is : " + actualFieldValue);
				System.out.println();
				
				
			}
			catch (Exception ex) 
			{
				logException(ex);
			}
			finally
			{
				try 
				{ 
					rs.close();
					statement.close();
					connection.close();
				
				} 
				
				catch(SQLException ex)
				{
					logException(ex);
				}

			}
			
		}
		
		
		result = performTest(new XManualVerificationPoint(vpName, this, expectedValue, actualFieldValue));
		return result;
	}
	
	private boolean isDBConnected(String query, String databaseName){
		boolean result = false;
		ResultSet rs = null;
		String newQuery = null;
		int getTime = 0;

		newQuery = query.substring(query.toLowerCase().indexOf("from"));
		newQuery = "Select COUNT(*) AS 'rowcount' " + newQuery;
		System.out.println(newQuery);
		
		int currentRow = 0;
		
		BNTimer timer = new BNTimer();
		timer.start();
		do{
			
			//rs = getRecord_SQLServerUPDB(newQuery,databaseName);
			rs = getRecord_SQLServerUPDB(newQuery);
			
			try {
				rs.next();
				currentRow = rs.getInt("rowcount");
				if(currentRow>0){
					result = true;
					timer.stop();
					break;
				}
				System.out.println("Current row: " + currentRow);
				getTime = timer.getElapsedTime();
				if(getTime>= 60){
					logInfo("Database get recordset timed out at " + getTime );
					timer.stop();
					break;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while (currentRow<1);
		System.out.println("Database connected in elapsed time : " + getTime);
		return result;
	}
	/*
	public void deleteEanFromLocker(String customerId, String ean)
	{
		DigitalLocker locker = new DigitalLocker(customerId);
		locker.deleteLockerItem(ean);
		
	}
	*/
	public IResponse addItemsToCart(String[] eans)
	{
		
		int size = eans.length;
		String xml = "";
		IResponse response = null;
		String url = BASE_JCHECKOUT_URL + BASE_ADD_ITEM_PATH;
		for(int i=0;i<size-1;i++)
		{
			xml = getCartXML(eans[i], 1);  //default quantity = 1
			response = caliber().body(xml).contentType("application/xml").post(url);
			
		}
		return response;
	}

	public String getCartXML(String ean, String quantity)
	{
		String xml = "<cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity></cartItem>";
		return xml;
	}
	public String getCartXML(String ean, int quantity)
	{
		String xml = "<cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity></cartItem>";
		return xml;
	}
	public String getIPXML(String ean, String quantity)
	{
		String xml = "<instantPurchaseRequest><cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress></session></instantPurchaseRequest>";
		return xml;
	}
	public String getIPXMLWithCoupon(String ean, String quantity,String coupon)
	{
		String xml = "<instantPurchaseRequest><cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity></cartItem><session><clientIPAddress>127.0.0.1</clientIPAddress></session><couponCode>" + coupon + "</couponCode></instantPurchaseRequest>";
		return xml;
	}
	public String getIPJSON(String ean, String quantity)
	{
		String json = "{\"instantPurchaseRequest\":{\"cartItem\":{\"ean\":\"" +ean+  "\",\"quantity\": \"" +quantity + "\"},\"session\":{\"clientIPAddress\": \"127.0.0.1\"} }}";
		return json;
	}
	public String getIPJSONWithCoupon(String ean, String quantity, String coupon)
	{
		String json = "{\"instantPurchaseRequest\":{\"cartItem\":{\"ean\":\"" +ean+  "\",\"quantity\": \"" +quantity + "\"},\"couponCode\":\"" + coupon + "\",\"session\":{\"clientIPAddress\": \"127.0.0.1\"} }}";
		return json;
	}
	public String getIPXML(String ean, String quantity, String ipAdress)
	{
		String xml = "<instantPurchaseRequest><cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity></cartItem><session><clientIPAddress>" + ipAdress + "</clientIPAddress></session></instantPurchaseRequest>";
		return xml;
	}
	public String getUpdateCartXML(String cartItemID, String quantity)
	{
		String xml = "<cartItem><cartItemID>" + cartItemID + "</cartItemID><quantity>" + quantity + "</quantity></cartItem>";
		return xml;
	}
	public String getUpdateCartXML(int cartItemID, int quantity)
	{
		String xml = "<cartItem><cartItemID>" + cartItemID + "</cartItemID><quantity>" + quantity + "</quantity></cartItem>";
		return xml;
	}
	public String getGiftCardCartXML(String ean, String quantity, String amount)
	{
		String xml = "<cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity><toName>Nancy</toName><fromName>John</fromName><email>qaautomation@book.com</email><message> All the BEST you</message><amount>" + amount + "</amount></cartItem>";
		return xml;
	}
	public String getGiftCardCartXML(String ean, int quantity, double amount)

	{
		String xml = "<cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity><toName>Nancy</toName><fromName>John</fromName><email>qaautomation@book.com</email><message> All the BEST you</message><amount>" + amount + "</amount></cartItem>";
		return xml;
	}
	
	public String getSetBillingXML(String countryNum, String addressLine1, String state, String zip, String country, String city)
	{
		String xml = "<address>	    " +
				"<city>" + city + "</city>	    " +   //Leighton Buzzard
				"<company>Barnes and Noble</company>	    " +
				"<countryNum>" + countryNum + "</countryNum>	    " +  //826
				"<firstName>John</firstName>	    " +
				"<lastName>Smith</lastName>	    " +
				"<line1>" + addressLine1 + "</line1>	    " +  //72 North Street
				"<line2></line2>	    " +
				"<line3></line3>	    " +
				"<nickName>Nickname</nickName>	    " +
				"<state>" + state + "</state>	    " +  //CBF
				"<zip>" + zip + "</zip>	  " +  //LU7 1EN
				"</address>";//
		return xml;
	}
	public String getSetShippingXML( String addressLine1,String city, String state, String zip, String countryNum)
	{
		String xml = "<address>	    " +
		        "<line1>" + addressLine1 + "</line1>	    " +  //72 North Street
				"<city>" + city + "</city>	    " +   //Leighton Buzzard
				"<state>" + state + "</state>	    " +  //CBF
				"<zip>" + zip + "</zip>	  " +  //LU7 1EN
				"<countryNum>" + countryNum + "</countryNum>	    " +  //826
				"</address>";//
		return xml;
	}
	public String getSetShippingIntlXML()
	{
		return getSetShippingXML("72 North Street","Leighton Buzzard","","LU7 1EN","826");
	}
	public String getIntlSetBillingXML()
	{
		return getSetBillingXML("826", "72 North Street", "","LU7 1EN", "", "Leighton Buzzard");
	}
	public String getIntlConvertCartXML(String id, String idType, String cartID, String country, String retailerID)
	{   
		String xml; 
		xml = "<convertFrom> <cartIdentifier> <id>" + id + "</id> <idType>" + idType + "</idType> <cartClientId>" + cartID + "</cartClientId><countryCode>" + country + "</countryCode><retailerId>" + retailerID + "</retailerId></cartIdentifier></convertFrom>" ;
				
		return xml;  
	}
	public String getIntlConvertCartXML()
	{   
		return getIntlConvertCartXML("abcd123","sessionId","40","GB","NOK");
	}
	public String getAggregateXML_AddItems(String customerId,String[] eans, int[] quantities)
	{
		String xml = "";
		String start = "<request>" + 
					"<cartID><cartClientId>40</cartClientId><id>" + customerId + "</id><idType>customerId</idType></cartID>" +
					"<productFilterTemplate>min</productFilterTemplate>" +
					"<taskList>" +
					"<task type=\"AddItems\"><cartItems>";
				
		String cartXML = "";
		for(int i=0;i<eans.length;i++)
		{
			cartXML =  cartXML + getCartXML(eans[i], quantities[i]);
		}
					
        String end = "</cartItems></task></taskList></request>";
        xml = start + cartXML + end;
		return xml;
	}
	public String getAggregateXML_AddItems(String customerId,String[] eans, String[] quantities)
	{
		String xml = "";
		String start = "<request>" + 
					"<cartID><cartClientId>40</cartClientId><id>" + customerId + "</id><idType>customerId</idType></cartID>" +
					"<productFilterTemplate>min</productFilterTemplate>" +
					"<taskList>" +
					"<task type=\"AddItems\"><cartItems>";
				
		String cartXML = "";
		for(int i=0;i<eans.length;i++)
		{
			cartXML =  cartXML + getCartXML(eans[i], quantities[i]);
		}
					
        String end = "</cartItems></task></taskList></request>";
        xml = start + cartXML + end;
		return xml;
	}
	public String getAggregateXML_UpdateQuantity(String customerId,String[] eans, int[] quantities)
	{
		String xml = "";
		String start = "<request>" + 
					"<cartID><cartClientId>40</cartClientId><id>" + customerId + "</id><idType>customerId</idType></cartID>" +
					"<productFilterTemplate>min</productFilterTemplate>" +
					"<taskList>" +
					"<task type=\"UpdateQuantity\"><cartItems>";
				
		String cartXML = "";
		for(int i=0;i<eans.length;i++)
		{
			cartXML =  cartXML + getCartXML(eans[i], quantities[i]);
		}
					
        String end = "</cartItems></task></taskList></request>";
        xml = start + cartXML + end;
		return xml;
	}
	public String getAggregateXML_UpdateQuantity(String customerId,String[] eans, String[] quantities)
	{
		String xml = "";
		String start = "<request>" + 
					"<cartID><cartClientId>40</cartClientId><id>" + customerId + "</id><idType>customerId</idType></cartID>" +
					"<productFilterTemplate>min</productFilterTemplate>" +
					"<taskList>" +
					"<task type=\"UpdateQuantity\"><cartItems>";
				
		String cartXML = "";
		for(int i=0;i<eans.length;i++)
		{
			cartXML =  cartXML + getCartXML(eans[i], quantities[i]);
		}
					
        String end = "</cartItems></task></taskList></request>";
        xml = start + cartXML + end;
		return xml;
	}
	public String getAggregateXML_SetShipServiceLevel(String customerId,String shipmentId,String shippingMethodCode,String shippingMethodId)
	{
		String xml = "";
		String start = "<request>" + 
					"<cartID><cartClientId>40</cartClientId><id>" + customerId + "</id><idType>customerId</idType></cartID>" +
					"<taskList>" +
					"<task type=\"SetShipServiceLevel\">" +
					"<shipmentID>" + shipmentId + "</shipmentID>" +
					"<shippingMethod><code>" + shippingMethodCode + "</code><shippingMethodId>" + shippingMethodId + "</shippingMethodId></shippingMethod>" ;
		
        String end = "</task></taskList></request>";
        xml = start + end;
		return xml;
	}
	
	public String getAddressXML(String nickName, String firstName, String lastName, String isPOBox, String line1, String city,
			   String state, String zip, String countryNum, String checkSum)
	{
		String xml;
		
		xml = "<address>" +
              "<nickName>" + nickName + "</nickName>"    +
              "<firstName>" + firstName + "</firstName>"    +
              "<lastName>" + lastName + "</lastName>"       +
              "<isPOBox>" + isPOBox + "</isPOBox>"          +
              "<line1>" + line1 + "</line1>"                +
              "<city>"  + city  + "</city>"                 +
              "<state>" + state + "</state>"                +
              "<zip>"   + zip + "</zip>"                    +
              "<countryNum>" + countryNum + "</countryNum>" +
              "<checkSum>" + checkSum + "</checkSum>"       +
              "</address>"                                  ;
		
		return xml;
	}
	
	public String getAddressXML(String line1, String city, String state, String zip, String countryNum, String checkSum)
	{
		return getAddressXML("","","","false",line1,city,state,zip,countryNum,checkSum);
	}
	
	public String getAddPaymentXML(String code, String expiry, String securityCode,String ccNum, String isDefault, String checkSum, String city,
			                       String company, String countryNum, String firstName, String lastName, String line1,
			                       String line2,String nickName, String state, String zip)
	{
		String xml;
		
		xml = "<paymentMethod>"                                 +     
              "<creditCard>"                                    + 
              "<code>" + code + "</code>"                               +       //VI
              "<expiryMMYYYY>" + expiry + "</expiryMMYYYY>"             +      //062015
              "<securityCode>" + securityCode + "</securityCode>"       +      //562
              "<numberPlain>" + ccNum + "</numberPlain>"                +      //4012888888881881
              "<isDefault>" + isDefault + "</isDefault>"                +      //true
              "<address>"                                       +
              "<checkSum>" + checkSum + "</checkSum>"                   +      //43842                           
              "<city>" + city + "</city>"                               +      //pago pago
              "<company>"  + company + "</company>"                      +      // Barnes and Noble
              "<countryNum>" + countryNum + "</countryNum>"             +      //16
              "<firstName>" + firstName + "</firstName>"                +      //John
              "<lastName>" + lastName + "</lastName>"                   +      //John  
              "<line1>" + line1 + "</line1>"                            +      //3222 Main highway                        
              "<line2>" + line2 + "</line2>"                            +      //3222 Main highway
              "<nickName>" + nickName + "</nickName>"                   +      //Nickname         
              "<state>" + state + "</state>"                            +      // State is not mendatory
              "<zip>" + zip + "</zip>"                                  +      //96799   
              "</address>"                                      +                             
              "</creditCard>"                                   +                             
              "</paymentMethod>";
		
		return xml;
	}

	public String getAddPaymentXMLLite(  String checkSum, String city,
             String countryNum,  String line1,
             String state, String zip)
    {
		  String xml;
    
		  xml = getAddPaymentXML("VI", "062015", "562","4012888888881881", "true", checkSum, city,
            "Barnes and Noble", countryNum, "FirstNameQA", "LastNameQA",line1,"","",state, zip);
    

		  return xml;
     }
	
	public String getShippingMethodXML(String code, String shippingMethodId)
	{
		String xml = "<shippingMethod><code>" + code + "</code><shippingMethodId>" + shippingMethodId + "</shippingMethodId></shippingMethod>";
		return xml;
	}
	
	public String getCOPAXML(String ean,String quantity, String licenseIndicator, String sourceID, String clientIP, 
			                 String id, String profileID1, String profileID2, String profileID3)
	{
		String xml = "<instantPurchaseRequest>"                   						+
                     "<cartItem>"								  						+
                     "<ean>" + ean +"</ean>"                      						+   //9781101142592
                     "<quantity>" + quantity + "</quantity>"             						+   //1                 
                     "</cartItem>"                                						+
                     "<licenseIndicator>" + licenseIndicator + "</licenseIndicator>"    +     //1
                     "<session>"                                  						+ 
                     "<sourceID>" +sourceID + "</sourceID>"                             +  //P001000007 
                     "<clientIPAddress>" + clientIP + "</clientIPAddress>"              +  //127.0.0.1
                     "<deviceID />"                               						+
                     "<deviceSerialNumber />"                     						+
                     "<userAgent />"                              						+
                     "</session>"                                 						+
                     "<nookProfile>"                              						+
                     "<id>" + id + "</id>"                                              +   //12345
                     "<name>John Smith Family</name>"             						+
                     "<profileType>PRIMARY</profileType>"         						+
                     "<profileEntitlement>"                       						+
                     "<profileId>" + profileID1 + "</profileId>"                        +  //2
                     "<profileId>"  + profileID2 + "</profileId>"                       +  //3
                     "<profileId>" + profileID3 + "</profileId>"                        +  //5
                     "</profileEntitlement>"                      						+
                     "</nookProfile>"                             						+
                     "</instantPurchaseRequest>"                    ;
		
		
		return xml; 
	}
	
	public String getCOPAXML(String ean,String quantity)
    {
      String xml = getCOPAXML(ean,quantity,"1","P001000007","127.0.0.1","12345","2","3","5");


      return xml; 
    }
	
	public String getCOPAXML(String ean,String quantity, String profileID1)
    {
      String xml = getCOPAXML(ean,quantity,"1","P001000007","127.0.0.1","12345",profileID1,"3","5");


      return xml; 
    }
	public String getCOPAJSON(String ean,String quantity, String licenseIndicator, String sourceID, String clientIP, 
            String id, String profileID1, String profileID2, String profileID3)
	{
			
		String json = "{" +
		              "\"instantPurchaseRequest\": {"  +
		              "\"cartItem\": {" + 
		              "\"ean\": \" "   + ean +  " \", " +    // 2940043350282
		              "\"quantity\": \" " + quantity + "\" " +     //1
                      "},"  +
                      "\"licenseIndicator\": \" " + licenseIndicator + "\"," + 
                      "\"session\": { " + 
                      "\"sourceID\": \" " + sourceID + "\",  " +   //P001000007
                      "\"clientIPAddress\": \" " + clientIP + "\" " + //127.0.0.1
                      "}, " +
                      "\"nookProfile\": { " +
                      "\"id\": \" " + id + "\", " +   //12345
                      "\"name\": \"John Smith Family\", " +
                      "\"profileType\": \"PRIMARY\", " +
                      "\"profileEntitlement\": { " +
                      "\"profileId\": [ " +
                      "\" " + profileID1 + "\", " +    //78901
                      "\" " + profileID2 + "\",   "   +  //3
                      "\" " + profileID3 + "\" "      +  //5
                      "]"   +
                      "}"   +
                      "}"   +
                      "}"   +
                      "}";
		
					return json; 
	}
	
	public String getCOPAJSON(String ean,String quantity, String profileID1)
    {
      String json = getCOPAJSON(ean,quantity,"1","P001000007","127.0.0.1","12345",profileID1,"3","5");


      return json; 
    }
	
	public int getGroupTypeIndex_BN(IResponse response)
	{
		int index = 0;
		String groupTypeCount = "";
		int count = 0;
		groupTypeCount = getNodeValue(response, "count(/response/checkoutDocument/order/totals/totalsGroup)");
		count = convertToInteger(groupTypeCount);
		String value = "";
		for(int i=1;i<=count;i++)
		{
			value = getNodeValue(response, "/response/checkoutDocument/order/totals/totalsGroup[" + i + "]/@groupType");
			if(value.equals("BN"))
			{
				index = i;
				break;
			}
		}
		return index;
	}
	/*
	 *  type = xml or json
	 */
	public int getGroupTypeIndex_BN(IResponse response,String type)
	{
		int index = 0;
		String groupTypeCount = "";
		int count = 0;
		if(type.equalsIgnoreCase("xml")){
			groupTypeCount = getNodeValue(response, "count(/response/checkoutDocument/order/totals/totalsGroup)");	
		}
		else {
			groupTypeCount = getNodeValue(response, "count(//checkoutDocument/order/totals/totalsGroup)");
		}
		count = convertToInteger(groupTypeCount);
		String value = "";
		for(int i=1;i<=count;i++)
		{
			if(type.equalsIgnoreCase("xml")){
				value = getNodeValue(response, "/response/checkoutDocument/order/totals/totalsGroup[" + i + "]/@groupType");	
			}
			else {
				value = getNodeValue(response, "//totals/totalsGroup[" + i + "]/groupType");
			}
			
			if(value.equals("BN"))
			{
				index = i;
				break;
			}
		}
		return index;
	}

	public int getGroupTypeIndex_MP(IResponse response)
	{
		int index = 0;
		String groupTypeCount = "";
		int count = 0;
		groupTypeCount = getNodeValue(response, "count(/response/checkoutDocument/order/totals/totalsGroup)");
		count = convertToInteger(groupTypeCount);
		String value = "";
		for(int i=1;i<=count;i++)
		{
			value = getNodeValue(response, "/response/checkoutDocument/order/totals/totalsGroup[" + i + "]/@groupType");
			if(value.equals("MarketPlace"))
			{
				index = i;
				break;
			}
		}
		return index;
	}
	public int getGroupTypeIndex_Aggregate(IResponse response)
	{
		int index = 0;
		String groupTypeCount = "";
		int count = 0;
		groupTypeCount = getNodeValue(response, "count(/response/checkoutDocument/order/totals/totalsGroup)");
		count = convertToInteger(groupTypeCount);
		String value = "";
		for(int i=1;i<=count;i++)
		{
			value = getNodeValue(response, "/response/checkoutDocument/order/totals/totalsGroup[" + i + "]/@groupType");
			if(value.equals("Aggregate"))
			{
				index = i;
				break;
			}
		}
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
	//*** GroupType = BN
	
	
	public int getItemCount_groupTypeBN(IResponse response)
	{
		int count;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_ITEM_COUNT_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		count = convertToInteger(value);
		return count;
	}
	
	public String getOrderNumber(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_ORDER_NUMBER_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getOrderNumber_Json(IResponse response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/orderNumber";
		value = getNodeValue(response, jpath);
		return value;
	}
	public String getOrderNumber(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_ORDER_NUMBER_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getOrderNumber_Json(String response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "@orderNumber";
		value = getNodeValue(response, jpath);
		return value;
	}
	
	public String getOrderDate(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_ORDER_DATE_XPATH;
		value = getNodeValue(response, xpath).substring(0, 10);;
		return value;
	}
	/*
	 * @Author: Shahzad Anjum
	 * Description: fixed the XPath & JPath 
	 */
	public String getOrderDate_Json(IResponse response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/orderDate";
		value = getNodeValue(response, jpath).substring(0, 10);;
		return value;
	}
	public String getOrderDate(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_ORDER_DATE_XPATH;
		value = getNodeValue(response, xpath).substring(0, 10);;
		return value;
	}
	public String getOrderDate_Json(String response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/orderDate";
		value = getNodeValue(response, jpath).substring(0, 10);;
		return value;
	}

	public String getSAPOrderNumber(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_SAP_ORDER_NUMBER_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getSAPOrderNumber_Json(IResponse response)
	{
		String value = "";
		String jpath = JCHECKOUT_SAP_ORDER_JPATH + "/msgIDs";
		value = getNodeValue(response, jpath);
		return value;
	}
	public String getWLPartnerID(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_WL_PARTNER_ID_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	
	public String getWLPartnerID_Json(IResponse response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/whiteLabelPartnerID";
		value = getNodeValue(response, jpath);
		return value;
	}
	public String getWLPartnerID(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_WL_PARTNER_ID_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getWLPartnerID_Json(String response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/whiteLabelPartnerID";
		value = getNodeValue(response, jpath);
		return value;
	}
	public String getChannelType(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_CHANNEL_TYPE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getChannelType_Json(IResponse response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/channelType";
		value = getNodeValue(response, jpath);
		return value;
	}
	public String getChannelType(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_CHANNEL_TYPE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getChannelType_Json(String response)
	{
		String value = "";
		String jpath = JCHECKOUT_ORDER_JPATH + "/channelType";
		value = getNodeValue(response, jpath);
		return value;
	}
	//
	public String getAllocationPaymentAmount(IResponse response)
	{
		String value = "";
		String xpath = "";
		xpath =JCHECKOUT_ORDER_ALLOCATION_XPATH + "/@paymentAmount";
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAllocationPaymentAmount_Json(IResponse response)
	{
		String value = "";
		String xpath = "";
		xpath =JCHECKOUT_ORDER_ALLOCATION_JPATH + "/paymentAmount";
		value = getNodeValue(response, xpath);
		return value;
	}
	
	public String getAllocationPaymentAmount(IResponse response,String type)
	{
		String value = "";
		String xpath = "";
		if(type.equalsIgnoreCase("xml")){
			xpath =JCHECKOUT_ALLOCATION_PAYMENTAMOUNT_XPATH;
		}
		else{
			xpath =JCHECKOUT_ALLOCATION_PAYMENTAMOUNT_JPATH;
		}

		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAllocationPaymentAmount(String response, String type)
	{
		String value = "";
		String xpath = "";
		
		if(type.equalsIgnoreCase("xml")){
			xpath =JCHECKOUT_ALLOCATION_XPATH + "/@paymentAmount";
		}
		else{
			xpath =JCHECKOUT_ALLOCATION_JPATH + "/paymentAmount";
		}
		
		value = getNodeValue(response, xpath);
		return value;
	}

	public String getAuthType(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_XPATH + "/authType";
		value = getNodeValue(response, xpath);
		if(value.contentEquals("F")){
			value = "A";
		}
		return value;
	}
	public String getAuthType_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authType";
		value = getNodeValue(response, xpath);
		if(value.contentEquals("F")){
			value = "A";
		}
		return value;
	}
	public String getAuthType(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_TYPE_XPATH;
		value = getNodeValue(response, xpath);
		if(value.contentEquals("F")){
			value = "A";
		}
		return value;
	}	
	public String getAuthType_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authType";
		value = getNodeValue(response, xpath);
		if(value.contentEquals("F")){
			value = "A";
		}
		return value;
	}
	public String getAuthCode(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_CODE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthCode_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authCode";
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthCode(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_CODE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthCode_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authCode";
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthResponse(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_RESPONSE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthResponse_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authResponse";
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthResponse(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_RESPONSE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthResponse_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authResponse";
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthResponseCode(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_RESPONSE_CODE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthResponseCode_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authResponseCode";
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthResponseCode(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_RESPONSE_CODE_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthResponseCode_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authResponseCode";
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthAmount(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_AMOUNT_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthAmount(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_AMOUNT_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	
	public String getAuthAmount_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authAmount";
		value = getNodeValue(response, xpath);
		return value;
	}
	
	public String getAuthAmount_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authAmount";
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthAmountFromGC(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_AMOUNT_FROM_GC_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthAmountFromGC_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_GIFTCARD_JPATH + "/authApprovedAmount";
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthAmountFromGC(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_AMOUNT_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}
	public String getAuthAmountFromGC_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_GIFTCARD_JPATH + "/authApprovedAmount";
		value = getNodeValue(response, xpath);
		return value;
	}
	
	public String getAuthApprovedAmount(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_APPROVED_AMOUNT_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthApprovedAmount_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authApprovedAmount";
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthApprovedAmount(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_APPROVED_AMOUNT_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthApprovedAmount_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_PAYMENT_CREDITCARD_JPATH + "/authApprovedAmount";
		value = getNodeValue(response, xpath);
		return value;
	}	
	
	public String getAuthApprovedAmountFromGC(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_APPROVED_AMOUNT_FROM_GC_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthApprovedAmountFromGC_Json(IResponse response)
	{
		String value = "";
		String xpath = JCHECKOUT_GIFTCARD_JPATH + "/authApprovedAmount";
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthApprovedAmountFromGC(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_AUTH_APPROVED_AMOUNT_FROM_GC_XPATH;
		value = getNodeValue(response, xpath);
		return value;
	}	
	public String getAuthApprovedAmountFromGC_Json(String response)
	{
		String value = "";
		String xpath = JCHECKOUT_GIFTCARD_JPATH + "/authApprovedAmount";
		value = getNodeValue(response, xpath);
		return value;
	}	

	public double getCartItemTotal_groupTypeBN(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_CARTITEMTOTAL_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getCartItemTotal_groupTypeBN(IResponse response,String type)
	{
		double doubleValue = 0.0;
		String xpath = "";
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		 if(type.equalsIgnoreCase("xml")){
			 xpath = searchAndReplace(JCHECKOUTDOC_BN_CARTITEMTOTAL_XPATH, "totalsGroup[1]", strToReplace);	 
		 }
		 else{
			 xpath = searchAndReplace(JCHECKOUTDOC_BN_CARTITEMTOTAL_JPATH, "totalsGroup[1]", strToReplace);
		 }
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	
	public double getMarchandisePrice_BN(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		value = getNodeValue(response, JCHECKOUTDOC_BNPRICE_XPATH);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getGiftWrap_groupTypeBN(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_GIFTWRAP_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderTaxTotal_groupTypeBN(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_ORDERTAXTOTAL_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
		
	}
	public double getOrderTaxTotal_groupTypeBN(IResponse response,String type)
	{
		
		double doubleValue = 0.0;
		String value = "";
		String xpath = "";
		
		int groupTypeIndex = getGroupTypeIndex_BN(response,type);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		if(type.equalsIgnoreCase("xml")){
			xpath = searchAndReplace(JCHECKOUTDOC_BN_ORDERTAXTOTAL_XPATH, "totalsGroup[1]", strToReplace);
		}
		else{
			xpath = searchAndReplace(JCHECKOUTDOC_BN_ORDERTAXTOTAL_JPATH, "totalsGroup[1]", strToReplace);
		}
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
		
	}

	public double getOrderTotal_groupTypeBN(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_ORDERTOTAL_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	
	public double getOrderTotal_groupTypeBN(IResponse response,String type)
	{
		
		double doubleValue = 0.0;
		String xpath = "";
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response,type);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		if(type.equalsIgnoreCase("xml")){
			xpath = searchAndReplace(JCHECKOUTDOC_BN_ORDERTOTAL_XPATH, "totalsGroup[1]", strToReplace);	
		}
		else{
			xpath = searchAndReplace(JCHECKOUTDOC_BN_ORDERTOTAL_JPATH, "totalsGroup[1]", strToReplace);
		}
		
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getShipping_groupTypeBN(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_SHIPPING_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getShippingChargeTotal(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		String xpath = JCHECKOUT_SHIPMENT_XPATH + "/shippingChargeTotal";
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getShipmentDiscount(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		String xpath = JCHECKOUT_SHIPMENT_XPATH + "/shipmentDiscount";
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getCartItemTotalDiscount_groupTypeBN(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_DISCOUNTS_CARTITEMTOTAL_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderDiscount_groupTypeBN(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_DISCOUNTS_ORDER_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getShippingDiscount_groupTypeBN(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_DISCOUNTS_SHIPPING_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getItemLevelDiscount_groupTypeBN(IResponse response)
	{
	
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_BN(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_BN_DISCOUNTS_ITEMLEVEL_XPATH, "totalsGroup[1]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	//*** GroupType = Aggregate
	public int getItemCount_groupTypeAggregate(IResponse response)
	{
		int count;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_ITEM_COUNT_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		count = convertToInteger(value);
		return count;
	}
	public double getCartItemTotal_groupTypeAggregate(IResponse response)
	{
			
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_CARTITEMTOTAL_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getGiftWrap_groupTypeAggregate(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_GIFTWRAP_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderTaxTotal_groupTypeAggregate(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_ORDERTAXTOTAL_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
		
	}
	public double getOrderTotal_groupTypeAggregate(IResponse response)
	{
		
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_ORDERTOTAL_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	
	public double getShipping_groupTypeAggregate(IResponse response)
	{
				
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_SHIPPING_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getCartItemTotalDiscount_groupTypeAggregate(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_DISCOUNTS_CARTITEMTOTAL_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderDiscount_groupTypeAggregate(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_DISCOUNTS_ORDER_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getShippingDiscount_groupTypeAggregate(IResponse response)
	{
				
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_DISCOUNTS_SHIPPING_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getItemLevelDiscount_groupTypeAggregate(IResponse response)
	{
			
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_Aggregate(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_DISCOUNTS_ITEMLEVEL_XPATH, "totalsGroup[2]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	
	//*** GroupType = Marketplace
	public int getItemCount_groupTypeMP(IResponse response)
	{
		
		int count;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_ITEM_COUNT_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		count = convertToInteger(value);
		return count;
	}
	public double getCartItemTotal_groupTypeMP(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_CARTITEMTOTAL_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getMarchandisePrice_MP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MARKETPLACEPRICE_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getGiftWrap_groupTypeMP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_GIFTWRAP_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderTaxTotal_groupTypeMP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_ORDERTAXTOTAL_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderTotal_groupTypeMP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_ORDERTOTAL_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	
	public double getShipping_groupTypeMP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_SHIPPING_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getCartItemTotalDiscount_groupTypeMP(IResponse response)
	{
				
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_DISCOUNTS_CARTITEMTOTAL_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getOrderDiscount_groupTypeMP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_DISCOUNTS_ORDER_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getShippingDiscount_groupTypeMP(IResponse response)
	{
		
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_DISCOUNTS_SHIPPING_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	public double getItemLevelDiscount_groupTypeMP(IResponse response)
	{
			
		double doubleValue = 0.0;
		String value = "";
		int groupTypeIndex = getGroupTypeIndex_MP(response);
		String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		String xpath = searchAndReplace(JCHECKOUTDOC_MP_DISCOUNTS_ITEMLEVEL_XPATH, "totalsGroup[3]", strToReplace);
		value = getNodeValue(response, xpath);
		doubleValue = convertToDouble(value);
		return doubleValue;
	}
	//**** Others
	public double getShippingDiscount(IResponse response)
	{
		double doubleValue = 0.0;
		String value = "";
		value = getNodeValue(response, JCHECKOUTDOC_SHIPMENTDISCOUNT_XPATH);
		doubleValue = convertToDouble(value);
		return doubleValue;
	
	}
	
	public int getQuantity(IResponse response)
	{
		int intValue = 0;
		String value = "";
		value = getNodeValue(response, JCHECKOUTDOC_QUANTITY_XPATH);
		intValue = convertToInteger(value);
		return intValue;
	
	}
	//******* DYNAMIC COMPUTATIONS *******
	
	public double calculateOrderTotal_groupTypeBN(IResponse response)
	{
		double orderTotal = 0.0;
		double marchandisePrice = getMarchandisePrice_BN(response);   //marchandise amount outside of totals node
		double giftWrap = getGiftWrap_groupTypeBN(response);
		double shipping = getShippingChargeTotal(response);   //this node is outside of totals node
		double orderTaxTotal = getOrderTaxTotal_groupTypeBN(response);
		
		double cartItemTotalDiscount = getCartItemTotalDiscount_groupTypeBN(response);
		double orderDiscount = getOrderDiscount_groupTypeBN(response);
		double shippingDiscount = getShippingDiscount_groupTypeBN(response);
		double itemLevelDiscount = getItemLevelDiscount_groupTypeBN(response);
		
		int itemCount = getItemCount_groupTypeBN(response);
		orderTotal =  ((marchandisePrice + giftWrap + shipping + orderTaxTotal) - (cartItemTotalDiscount + orderDiscount + shippingDiscount + itemLevelDiscount))*itemCount;
		
		return orderTotal;
	}
	public double calculateOrderTotal_groupTypeAggregate(IResponse response)  //review this method
	{
		double orderTotal = 0.0;
		double marchandisePrice = getMarchandisePrice_BN(response);   //marchandise amount of both BN + MP
		double giftWrap = getGiftWrap_groupTypeAggregate(response);
		double shipping = getShippingChargeTotal(response);   //this node is outside of totals node
		double orderTaxTotal = getOrderTaxTotal_groupTypeAggregate(response);
		double cartItemTotalDiscount = getCartItemTotalDiscount_groupTypeAggregate(response);
		double orderDiscount = getOrderDiscount_groupTypeAggregate(response);
		double shippingDiscount = getShippingDiscount_groupTypeAggregate(response);
		double itemLevelDiscount = getItemLevelDiscount_groupTypeAggregate(response);
		
		int itemCount = getItemCount_groupTypeAggregate(response);
		
		orderTotal =  ((marchandisePrice + giftWrap + shipping + orderTaxTotal) - (cartItemTotalDiscount + orderDiscount + shippingDiscount + itemLevelDiscount))*itemCount;
		
		return orderTotal;
	}
	
	public double calculateOrderTotal_groupTypeMP(IResponse response)
	{
		double orderTotal = 0.0;
		double marchandisePrice = getMarchandisePrice_MP(response);   //marchandise amount
		double giftWrap = getGiftWrap_groupTypeMP(response);
		double shipping = getShippingChargeTotal(response);   //this node is outside of totals node
		double orderTaxTotal = getOrderTaxTotal_groupTypeMP(response);
		double cartItemTotalDiscount = getCartItemTotalDiscount_groupTypeMP(response);
		double orderDiscount = getOrderDiscount_groupTypeMP(response);
		double shippingDiscount = getShippingDiscount_groupTypeMP(response);
		double itemLevelDiscount = getItemLevelDiscount_groupTypeMP(response);
		
		int itemCount = getItemCount_groupTypeMP(response);
		
		orderTotal =  ((marchandisePrice + giftWrap + shipping + orderTaxTotal) - (cartItemTotalDiscount + orderDiscount + shippingDiscount + itemLevelDiscount))*itemCount;
		
		return orderTotal;
	}
	
	
	public double calculateCartItemTotal_groupTypeBN(IResponse response)
	{
		double cartItemTotal = 0.0;
		//double marchandisePrice = getMarchandisePrice_BN(response);   //marchandise amount
		
		double cartItemTotalDiscount = getCartItemTotalDiscount_groupTypeBN(response);
		double orderDiscount = getOrderDiscount_groupTypeBN(response);
		double shippingDiscount = getShippingDiscount_groupTypeBN(response);
		double itemLevelDiscount = getItemLevelDiscount_groupTypeBN(response);
		
		int itemCount = getItemCount_groupTypeBN(response);
		double cartItemAmount = 0.0;   //cartItemAmount = marchandise amount
		
		
		for(int i=1;i<=itemCount;i++)
		{
			cartItemAmount = getCartItemAmount(response,i);
			cartItemTotal += ((cartItemAmount) - (cartItemTotalDiscount + orderDiscount + shippingDiscount + itemLevelDiscount));
		}
		
		
		cartItemTotal = roundTwoDecimals(cartItemTotal);
		
		return cartItemTotal;
	}
	
	public double getCartItemAmount(IResponse response, int index)
	{
		String xpath = "";
		String value = "";
		double cartItemAmount = 0.0;
		xpath = JCHECKOUT_CARTITEM_XPATH + "[" + index + "]/@amount";
		value = getNodeValue(response, xpath);
		if(value.equals("") || value == null)
		{
			cartItemAmount = 0.0;
		}
		else
		{
			cartItemAmount = Double.parseDouble(value);
		}
		return cartItemAmount;
	}
	
	public double calculateCartItemTotal_groupTypeAggregate(IResponse response)  //review this method
	{
		double cartItemTotal = 0.0;
		//double marchandisePrice = getMarchandisePrice_Aggregate(response);   //marchandise amount
		
		double cartItemTotalDiscount = getCartItemTotalDiscount_groupTypeAggregate(response);
		double orderDiscount = getOrderDiscount_groupTypeAggregate(response);
		double shippingDiscount = getShippingDiscount_groupTypeAggregate(response);
		double itemLevelDiscount = getItemLevelDiscount_groupTypeAggregate(response);
		
		int itemCount = getItemCount_groupTypeAggregate(response);
		double cartItemAmount = 0.0;   //cartItemAmount = marchandise amount
		
		
		for(int i=1;i<=itemCount;i++)
		{
			cartItemAmount = getCartItemAmount(response,i);
			cartItemTotal += ((cartItemAmount) - (cartItemTotalDiscount + orderDiscount + shippingDiscount + itemLevelDiscount));
		}
		
		cartItemTotal = roundTwoDecimals(cartItemTotal);
		return cartItemTotal;
	}
	public double calculateCartItemTotal_groupTypeMP(IResponse response)
	{
		double cartItemTotal = 0.0;
		//double marchandisePrice = getMarchandisePrice_MP(response);   //marchandise amount
		
		double cartItemTotalDiscount = getCartItemTotalDiscount_groupTypeMP(response);
		double orderDiscount = getOrderDiscount_groupTypeMP(response);
		double shippingDiscount = getShippingDiscount_groupTypeMP(response);
		double itemLevelDiscount = getItemLevelDiscount_groupTypeMP(response);
		
		int itemCount = getItemCount_groupTypeMP(response);
		double cartItemAmount = 0.0;   //cartItemAmount = marchandise amount
		
		
		for(int i=1;i<=itemCount;i++)
		{
			cartItemAmount = getCartItemAmount(response,i);
			cartItemTotal += ((cartItemAmount) - (cartItemTotalDiscount + orderDiscount + shippingDiscount + itemLevelDiscount));
		}
		
		cartItemTotal = roundTwoDecimals(cartItemTotal);
		return cartItemTotal;
	}
	
	public double calculateShipping(IResponse response)
	{
		double shippingAmount = 0.0;
		double shippingChargeTotal = getShippingChargeTotal(response);
		double shippingDiscount = getShipmentDiscount(response);
		
		shippingAmount = shippingChargeTotal - shippingDiscount;
		return shippingAmount;
		
	}
	
	//************* VERIFICATION METHODS *******************
	
	public boolean verifyCartItemTotal_BN(String vpName, IResponse response)
	{
		boolean result = false;
		String expectedCartItemTotal = calculateCartItemTotal_groupTypeBN(response) + "";
		String actualCartItemTotal = getCartItemTotal_groupTypeBN(response) + "";
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, expectedCartItemTotal, actualCartItemTotal));
				
		return result;
	}
	public boolean verifyCartItemTotal_Aggregate(String vpName, IResponse response)
	{
		boolean result = false;
		String expectedCartItemTotal = calculateCartItemTotal_groupTypeAggregate(response) + "";
		String actualCartItemTotal = getCartItemTotal_groupTypeAggregate(response) + "";
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, expectedCartItemTotal, actualCartItemTotal));
				
		return result;
	}
	public boolean verifyCartItemTotal_MP(String vpName, IResponse response)
	{
		boolean result = false;
		String expectedCartItemTotal = calculateCartItemTotal_groupTypeMP(response) + "";
		String actualCartItemTotal = getCartItemTotal_groupTypeMP(response) + "";
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, expectedCartItemTotal, actualCartItemTotal));
		
		return result;
	}
	public boolean verifyOrderTotal_BN(String vpName, IResponse response)
	{
		boolean result = false;
		String expectedOrderTotal = calculateOrderTotal_groupTypeBN(response) + "";
		String actualOrderTotal = getOrderTotal_groupTypeBN(response) + "";
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, expectedOrderTotal, actualOrderTotal));
		
		return result;
	}
	public boolean verifyOrderTotal_Aggregate(String vpName, IResponse response)
	{
		boolean result = false;
		String expectedOrderTotal = calculateOrderTotal_groupTypeAggregate(response) + "";
		String actualOrderTotal = getOrderTotal_groupTypeAggregate(response) + "";
		logResponse(response);
		
		result = performTest(new XManualVerificationPoint(vpName, this, expectedOrderTotal, actualOrderTotal));
		
		return result;
	}
	public boolean verifyOrderTotal_MP(String vpName, IResponse response)
	{
		boolean result = false;
		String expectedOrderTotal = calculateOrderTotal_groupTypeMP(response) + "";
		String actualOrderTotal = getOrderTotal_groupTypeMP(response) + "";
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, expectedOrderTotal, actualOrderTotal));
		
		
		return result;
	}
	
	public boolean verifyCartItemCount(String vpName, IResponse response, int expectedCartItemCount)
	{
		boolean result = false;
		int shipmentCount = 0;
		int cartItemCount = 0;
		String xpath = "";
		String value = "";
		shipmentCount = convertToInteger(getNodeValue(response, JCHECKOUT_SHIPMENT_COUNT_XPATH));
		
		//String strToReplace = "totalsGroup[" + groupTypeIndex + "]";
		//String xpath = searchAndReplace(JCHECKOUTDOC_AGGREGATE_SHIPPING_XPATH, "totalsGroup[2]", strToReplace);
		//fix the method
		for(int i=1;i<=shipmentCount;i++)
		{
			xpath = searchAndReplace(JCHECKOUT_CARTITEM_COUNT_XPATH,"shipment\\[1\\]","shipment["+i+"]");
			value = getNodeValue(response, xpath);
			cartItemCount += convertToInteger(value);
		}
		
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "CartItem_Count: " + expectedCartItemCount, "CartItem_Count: " + cartItemCount));
		
		return result;
	}
	
	public static double roundTwoDecimals(double d) 
	{
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        System.out.println(Double.valueOf(twoDForm.format(d)));
        return Double.valueOf(twoDForm.format(d));
	}
	
	public static boolean isOneDecimal(String value)
	{
		boolean result = false;
		//String [] digit = value.toString().split(".");
		String[] digit = value.split("\\.");
		if(value!=null){
			if(digit.length > 1)
			{
				if(digit[1].length()==1){
					result = true;
				}
			}
		}
		return result;
	}
	
	public boolean verifyShipmentType(String vpName,IResponse response, int shipmentId, String shipmentType)
	{
		boolean result = false;
		String actualShipmentType = getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "[" + shipmentId + "]/@shipmentType");
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "shipmentType: " + shipmentType, "shipmentType: " + actualShipmentType));
		
		return result;
	}
	public boolean verifyShipmentTotalItemQuantity(String vpName,IResponse response, int shipmentId, String shipmentTotalItemQuantity)
	{
		boolean result = false;
		String actualShipmentTotalItemQuantity = getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "[" + shipmentId + "]/@shipmentTotalItemQuantity");
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "shipmentType: " + shipmentTotalItemQuantity, "shipmentType: " + actualShipmentTotalItemQuantity));
		
		return result;
	}
	
	public boolean verifyQuantity(String vpName,IResponse response, int cartItemID, int quantity)
	{
		boolean result = false;
		String actualQuantity = getNodeValue(response, JCHECKOUT_SHIPMENTS_XPATH +  "//cartitems/cartItem[" + cartItemID + "]/@quantity");
		if(actualQuantity.equals(""))
		{
			actualQuantity = "0";
		}
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "Quantity: " + quantity, "Quantity: " + actualQuantity));
		
		return result;
	}
	public boolean verifyQuantity(String vpName,IResponse response, String cartItemID, String quantity)
	{
		boolean result = false;
		String actualQuantity = getNodeValue(response, JCHECKOUT_SHIPMENTS_XPATH +  "//cartitems/cartItem[" + cartItemID + "]/@quantity");
		if(actualQuantity.equals(""))
		{
			actualQuantity = "0";
		}
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "Quantity: " + quantity, "Quantity: " + actualQuantity));
		
		return result;
	}
	public boolean verifyQuantity(String vpName,IResponse response,int shipmentId, int cartItemID, int quantity)
	{
		boolean result = false;
		String xpath = JCHECKOUT_SHIPMENTS_XPATH + "/shipment[" + shipmentId + "]/cartitems/cartItem[" + cartItemID + "]/@quantity";
		String actualQuantity = getNodeValue(response, xpath);
		if(actualQuantity.equals(""))
		{
			actualQuantity = "0";
		}
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "Quantity: " + quantity, "Quantity: " + actualQuantity));
		
		return result;
	}
	public boolean verifyQuantity(String vpName,IResponse response,String shipmentId, String cartItemID, int quantity)
	{
		boolean result = false;
		String xpath = JCHECKOUT_SHIPMENTS_XPATH + "/shipment[" + shipmentId + "]/cartitems/cartItem[" + cartItemID + "]/@quantity";
		String actualQuantity = getNodeValue(response, xpath);
		if(actualQuantity.equals(""))
		{
			actualQuantity = "0";
		}
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "Quantity: " + quantity, "Quantity: " + actualQuantity));
		
		return result;
	}
	
	public boolean verifyAmount(String vpName,IResponse response, int cartItemID, double amount)
	{
		boolean result = false;
		String actualAmount = getNodeValue(response, JCHECKOUT_SHIPMENTS_XPATH +  "//cartitems/cartItem[" + cartItemID + "]/@amount");
		if(actualAmount.equals(""))
		{
			actualAmount = "0.0";
		}
		amount = roundTwoDecimals(amount);
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "Amount: " + amount, "Amount: " + actualAmount));
		return result;
	}
	public boolean verifyAmount(String vpName,IResponse response, int cartItemID, String amount)
	{
		boolean result = false;
		String actualAmount = getNodeValue(response, JCHECKOUT_SHIPMENTS_XPATH +  "//cartitems/cartItem[" + cartItemID + "]/@amount");
		if(actualAmount.equals(""))
		{
			actualAmount = "0.0";
		}
		
		logResponse(response);
		result = performTest(new XManualVerificationPoint(vpName, this, "Amount: " + amount, "Amount: " + actualAmount));
		
		return result;
	}
	
	public String getShipmentId(IResponse response)
	{
		String shipmentId = "";
		shipmentId = getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH +  "/@shipmentID");
		if(shipmentId==null)
		{
			shipmentId = "";
		}
		return shipmentId;
		
	}
	public String getShipmentIdByShipmentType(IResponse response, String shipmentType)
	{
		String shipmentId = "";
		
		String xpath;
		int shipmentNodeCount = 0;
		int index = 0;
		String nodeValue = getNodeValue(response, JCHECKOUT_SHIPMENT_COUNT_XPATH);
		shipmentNodeCount = convertToInteger(nodeValue);
		
		String sType;
		
		for(int i=1;i<=shipmentNodeCount;i++)
		{
			sType = getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "[" + i  + "]/@shipmentType");
			if(sType.equals(shipmentType))
			{
				index++;
				break;
			}
		}
		shipmentId = getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "[" + index  +  "]/@shipmentID");
		
		return shipmentId;
		
	}
	/*
	 * This method is supposed to be called after setShipServiceLevel call. The method compares the shippingChargeTotal from response against the values calculated
	 *  from the spreadsheet. Call this method for books only.
	 *  
	 */
	
	public boolean verifyShippingChargeTotal_Books(String vpName, IResponse response,String region,String serviceLevelCode)
	{
		int quantity = getQuantity(response);
		double perOrderShipping = ShipMethodsService.bookPricingPerOrder(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		double perItemShipping = ShipMethodsService.bookPricingPerItem(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		boolean vp = false;
		
		double expectedShippingChargeTotal = (perOrderShipping + (perItemShipping*quantity));
		double actualShippingChargeTotal = convertToDouble(getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "/@shippingChargeTotal"));
		
		vp = performTest(new XManualVerificationPoint(vpName, this, "ShippingChargeTotal = " + expectedShippingChargeTotal , "ShippingChargeTotal = " + actualShippingChargeTotal));
		
		
		return vp;
	}
	/*
	 *  This method is supposed to be called after setShipServiceLevel call. The method compares the shippingChargeTotal from response against the values calculated
	 *  from the spreadsheet. Call this method for music/DVD only.
	 *  
	 */
	public boolean verifyShippingChargeTotal_MusicDVD(String vpName, IResponse response,String region,String serviceLevelCode)
	{
		int quantity = getQuantity(response);
		double perOrderShipping = ShipMethodsService.bookPricingPerOrder(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		double perItemShipping = ShipMethodsService.bookPricingPerItem(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		boolean vp = false;
		
		double expectedShippingChargeTotal = (perOrderShipping + (perItemShipping*quantity));
		double actualShippingChargeTotal = convertToDouble(getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "/@shippingChargeTotal"));
		
		vp = performTest(new XManualVerificationPoint(vpName, this, "ShippingChargeTotal = " + expectedShippingChargeTotal , "ShippingChargeTotal = " + actualShippingChargeTotal));
		
		
		
		return vp;
	}
	public double getWeight(IResponse response)
	{
		double weight = convertToDouble(getNodeValue(response, JCHECKOUTDOC_PRODUCT_DIMENSIONS_XPATH1 + "/weight"));;
		return weight;
	}
	/*
	 *  This method is supposed to be called after setShipServiceLevel call. The method compares the shippingChargeTotal from response against the values calculated
	 *  from the spreadsheet. Call this method when you have both books and Music/DVDs in the cart.
	 *  
	 */
	public boolean verifyShippingChargeTotal_BKandDVD(String vpName, IResponse response,String region,String serviceLevelCode)
	{
		//INCOMPLETE METHOD
		boolean vp = false;
		int quantity = getQuantity(response);
		double perOrderShipping_book = ShipMethodsService.bookPricingPerOrder(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		double perItemShipping_book = ShipMethodsService.bookPricingPerItem(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		double perOrderShipping_dvd = ShipMethodsService.musicDVDPricingPerItem(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
		double perItemShipping_dvd = ShipMethodsService.musicDVDPricingPerItem(region,serviceLevelCode);  //Reading Mercury_Ship_Methods.xls spreadsheet
			
		double[] perOrderArray = {perOrderShipping_book,perOrderShipping_dvd};
		double maxPerOrder = getMaxValue(perOrderArray);
		
		double expectedShippingChargeTotal = (maxPerOrder + (perItemShipping_book*quantity)) + ((maxPerOrder + (perItemShipping_dvd*quantity)));
		double actualShippingChargeTotal = convertToDouble(getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "/@shippingChargeTotal"));
		
		vp = performTest(new XManualVerificationPoint(vpName, this, "ShippingChargeTotal = " + expectedShippingChargeTotal , "ShippingChargeTotal = " + actualShippingChargeTotal));
		
		return vp;
	}
	/*
	 *  This method is supposed to be called after setShipServiceLevel call. The method compares the shippingChargeTotal from response against the values calculated
	 *  from the spreadsheet. Call this method when for heavy items .
	 *  
	 */
	public boolean verifyShippingChargeTotal_HeavyItem(String vpName, IResponse response,String region,String serviceLevelCode,double weight)
	{
		//INCOMPLETE METHOD
	
		//There is no PerOrderShipping for the heavy items
		int quantity = getQuantity(response);
		boolean vp = false;
		double rate = ShippingRatesService.getShippingRate(weight, "Domestic Shipments", "Standard Delivery");
		double expectedShippingChargeTotal = rate*quantity;
		double actualShippingChargeTotal = convertToDouble(getNodeValue(response, JCHECKOUT_SHIPMENT_XPATH + "/@shippingChargeTotal"));
		vp = performTest(new XManualVerificationPoint(vpName, this, "ShippingChargeTotal = " + expectedShippingChargeTotal , "ShippingChargeTotal = " + actualShippingChargeTotal));
		return vp;
	}
	
	public static ResultSet getResultSet_OraclePayAuthDB(String sql) 
	{
		
		//String URL = "jdbc:oracle:thin:@//injpaymntdb:1521/injpaymntdb01.barnesandnoble.com";
		String URL = "jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb";
		String userName = "qa_user";
		String password = "qa_user";
			
		ResultSet rs = null;
        	
        try
        {
        	 
        	Class.forName("oracle.jdbc.OracleDriver");
        	connection = DriverManager.getConnection(URL,userName,password);
        	statement = connection.createStatement();
        	rs = statement.executeQuery(sql);
          
        	
        }
        catch (SQLException ex) 
        {
        	logException(ex);
		}
        catch(ClassNotFoundException ex)
        {
        	logException(ex);
        }
       
        return rs;

	}
	public String getApproveAmount_OraclePayAuthDB(String orderNumber) 
	{
		
		
		String URL = "jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb";
		String userName = "qa_user";
		String password = "qa_user";
		String query = "select * from PAYAUTH.ACTIVITY_LOG where ORDER_NUMBER='"+orderNumber+"'";
		System.out.println(query);
		ResultSet rs = null;
		String sellerId="";
        	
        try
        {
        	 
        	Class.forName("oracle.jdbc.OracleDriver");
        	
        	
        	connection = DriverManager.getConnection(URL,userName,password);
        	statement = connection.createStatement();
        	rs = statement.executeQuery(query);
        	
        	if(rs.next())
        	{
        		
        		sellerId = rs.getString("APPROVE_AMOUNT");// getString(1);
        	}
           	System.out.println(rs.toString());
        	
        	
        	
        }
        catch (SQLException ex) 
        {
        	logException(ex);
		}
        catch(ClassNotFoundException ex)
        {
        	logException(ex);
        }
       
        return sellerId;

	}
	public boolean verifyOrderTotalwithApproveAmount_OraclePayAuthDB(String vpName,String orderTotal,String orderNumber)
	{
	    boolean result = false;
	    String l_orderTotal;
	    if(isOneDecimal(orderTotal)){
	    	l_orderTotal = orderTotal+ "0";
	    }
	    else{
	    	l_orderTotal = orderTotal;
	    }
	    	
	    result =	vpManual(vpName, l_orderTotal,getApproveAmount_OraclePayAuthDB(orderNumber)).performTest(); 
	    return result;
	}
	public boolean verifyOrderDetails_OraclePayAuthDB(String vpName, String[][] expected,String orderNumber)
	{
	      String[][] actualTableData = new String[2][10];
	      String temp=null;
	      boolean result = false;
	      String query ="select ORDER_NUMBER,TRANSACTION_DATE,TRANSACTION_TYPE,AUTH_TYPE,AUTH_CODE,RESPONSE_CODE,SUCCESS,AUTH_AMOUNT,APPROVE_AMOUNT,RESPONSE_TEXT,ADAPTER_TYPE from PAYAUTH.ACTIVITY_LOG where order_number='" + orderNumber + "'";
	      System.out.println(query);
	      ResultSet rs = getResultSet_OraclePayAuthDB(query);
	      actualTableData [0][0] =  "ORDER NUMBER";			
	      actualTableData [0][1] =  "TRANSACTION DATE";
	      actualTableData [0][2] =  "TRANSACTION TYPE";				
	      actualTableData [0][3] =  "AUTH TYPE";
	      actualTableData [0][4] =  "AUTH CODE";
	      actualTableData [0][5] =  "RESPONSE_CODE"; 
	      actualTableData [0][6] =  "SUCCESS";					
	      actualTableData [0][7] =  "AUTH AMOUNT";	
	      actualTableData [0][8] =  "APPROVE AMOUNT";	
	      actualTableData [0][9] =  "RESPONSE TEXT";
	      try
	      {
	            rs.next();
	            //String actualFieldValue = rs.getString(fieldName);
	            for(int c=1;c<11;c++)
	            {
	                  if(c==2){
	                        temp= rs.getString(c);
	                        actualTableData[0][c-1] = temp.substring(0, 10);
	                        
	                  }
	                  else{
	                        actualTableData[0][c-1] = rs.getString(c);
	                  }
	            }
	            result = performTest(new XManualVerificationPoint(vpName, this, expected, actualTableData));
	            
	      }
	      catch (Exception ex) 
	      {
	            logException(ex);
	      }
	      finally
	      {
	            try 
	            { 
	                  rs.close();
	                  statement.close();
	                  connection.close();
	            
	            } 
	            
	            catch(SQLException ex)
	            {
	                  logException(ex);
	            }

	      }
	      return result;
	}
	public boolean verifyOrderDetails_OraclePayAuthDB(String vpName, String orderNumber,IResponse response)
	{
		  String[][] expected = new String[2][10];    
		  String[][] actualTableData = new String[2][10];
	      String temp=null;
	      boolean result = false;
	      	expected [0][0] =  "ORDER NUMBER";			
			expected [0][1] =  "TRANSACTION DATE";
			expected [0][2] =  "AUTH TYPE";
			expected [0][3] =  "AUTH CODE";
			expected [0][4] =  "RESPONSE_CODE"; 
			expected [0][5] =  "SUCCESS";					
			expected [0][6] =  "AUTH AMOUNT";	
			expected [0][7] =  "APPROVE AMOUNT";	
			expected [0][8] =  "RESPONSE TEXT";
			expected [0][9] =  "ADAPTER TYPE";
			
			expected [1][0] =  orderNumber;			//order_number,
			expected [1][1] =  getOrderDate(response); //TRANSACTION_DATE,
			expected [1][2] =  getAuthType(response);	//AUTH_TYPE,
			expected [1][3] =  getAuthCode(response);//AUTH_CODE,
			expected [1][4] =  getAuthResponseCode(response); //RESPONSE_CODE,
			expected [1][5] =  "Y";					//SUCCESS,
			String authAmount = getAuthAmount(response);
				if(isOneDecimal(authAmount)){
					authAmount = authAmount+"0";
				}
			expected [1][6] =  authAmount;	//AUTH_AMOUNT,
			String approveAmount = getAuthApprovedAmount(response);
				if(isOneDecimal(approveAmount)){
					approveAmount = approveAmount+"0";
				}
			expected [1][7] =  approveAmount;	//APPROVE_AMOUNT,2940000952740  qaautomation46683@book.com
			expected [1][8] =  getAuthResponse(response);//RESPONSE_TEXT
			if (getNodeValue(response, "//order/@retailerID").equals("NOK"))
			   expected [1][9] = "payteck";
			else
				expected [1][9] = "payware";
			
	      String query ="select ORDER_NUMBER,TRANSACTION_DATE,TRANSACTION_TYPE,AUTH_CODE,RESPONSE_CODE,SUCCESS,AUTH_AMOUNT,APPROVE_AMOUNT,RESPONSE_TEXT,ADAPTER_TYPE from PAYAUTH.ACTIVITY_LOG where order_number='" + orderNumber + "'";
	      System.out.println(query);
	      ResultSet rs = getResultSet_OraclePayAuthDB(query);
	      actualTableData [0][0] =  "ORDER NUMBER";			
	      actualTableData [0][1] =  "TRANSACTION DATE";
//	      actualTableData [0][2] =  "TRANSACTION TYPE";				
	      actualTableData [0][2] =  "AUTH TYPE";
	      actualTableData [0][3] =  "AUTH CODE";
	      actualTableData [0][4] =  "RESPONSE_CODE"; 
	      actualTableData [0][5] =  "SUCCESS";					
	      actualTableData [0][6] =  "AUTH AMOUNT";	
	      actualTableData [0][7] =  "APPROVE AMOUNT";	
	      actualTableData [0][8] =  "RESPONSE TEXT";
	      actualTableData [0][9] =  "ADAPTER TYPE";
	      try
	      {     
	    	    boolean b = false;
	    	    b = rs.next();
	    	    if (b == true)  
	    	    {
//	            rs.next();
	            //String actualFieldValue = rs.getString(fieldName);
	            for(int c=1;c<11;c++)
	            {
	                  if(c==2){
	                        temp= rs.getString(c);
	                        actualTableData[1][c-1] = getSubstring(temp, 0, 10);
	                        
	                  }
	                  else{
	                        actualTableData[1][c-1] = rs.getString(c);
	                  }
	            }
	    	    } // if b
	            result = performTest(new XManualVerificationPoint(vpName, this, expected, actualTableData));
	            
	      }
	      catch (Exception ex) 
	      {
	            logException(ex);
	      }
	      finally
	      {
	            try 
	            { 
	                  rs.close();
	                  statement.close();
	                  connection.close();
	            
	            } 
	            
	            catch(SQLException ex)
	            {
	                  logException(ex);
	            }

	      }
	      return result;
	}
	public boolean isResultSetGenerated_OracleOraclePayAuthDB(String query){
		boolean result = false;
		
		ResultSet rs = null;
		String newQuery = null;
		int getTime = 0;

		newQuery = query.substring(query.toLowerCase().indexOf("from"));
		newQuery = "Select COUNT(*) AS rowcount " + newQuery;
		System.out.println(newQuery);
		
		int currentRow = 0;
		BNTimer timer = new BNTimer();
		
		timer.start();
		do{

			rs = getResultSet_OraclePayAuthDB(query);
			
			try {
				rs.next();
				currentRow = rs.getInt(1);
				System.out.println(currentRow);
				if(currentRow>0){
					result = true;
					timer.stop();
					break;
				}
				System.out.println("Current row: " + currentRow);
				getTime = timer.getElapsedTime();
				if(getTime>= 60){
					logInfo("Database get RecordSet timed out at " + getTime );
					timer.stop();
					break;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while (currentRow<1);
		System.out.println("Database connected in elapsed time : " + getTime);
		return result;
	}
	/**
	 *  
	 * 
	 * @param vpName
	 * @param response = requires a JCHECKOUTDOC schema ( example: Instant Purchase response )
	 * @return
	 */
	public boolean verifyOrderDetails_OraclePayAuthDB_IP(String vpName,IResponse response)
	{
		  boolean isGettingResultSet = false;
		  String[][] expected = new String[2][9];    
		  String[][] actualTableData = new String[2][9];
	      String orderNumber = getNodeValue(response, JCHECKOUTDOC_ORDER_XPATH + "/@orderNumber"); //jcheckoutServices.getOrderNumber(response);
	      String temp=null;
	      boolean result = false;
	      boolean next = false;
	      	expected [0][0] =  "ORDER NUMBER";			
			expected [0][1] =  "TRANSACTION DATE";
			expected [0][2] =  "AUTH TYPE";
			expected [0][3] =  "AUTH CODE";
			expected [0][4] =  "RESPONSE_CODE"; 
			expected [0][5] =  "SUCCESS";					
			expected [0][6] =  "AUTH AMOUNT";	
			expected [0][7] =  "APPROVE AMOUNT";	
			expected [0][8] =  "RESPONSE TEXT";
			
			expected [1][0] =  orderNumber;			//order_number,
			expected [1][1] =  getOrderDate(response); //TRANSACTION_DATE,
			expected [1][2] =  getAuthType(response);	//AUTH_TYPE,
			expected [1][3] =  getAuthCode(response);//AUTH_CODE,
			expected [1][4] =  getAuthResponseCode(response); //RESPONSE_CODE,
			expected [1][5] =  "Y";					//SUCCESS,
			String authAmount = getAuthAmount(response);
			if(isOneDecimal(authAmount)){
				authAmount = authAmount+"0";
			}
			expected [1][6] =  authAmount;	//AUTH_AMOUNT,
			String approveAmount = getAuthApprovedAmount(response);
			if(isOneDecimal(approveAmount)){
				approveAmount = approveAmount+"0";
			}
			expected [1][7] =  approveAmount;	//APPROVE_AMOUNT,2940000952740  qaautomation46683@book.com
			expected [1][8] =  getAuthResponse(response);//RESPONSE_TEXT
			
	      String query ="select ORDER_NUMBER,TRANSACTION_DATE,TRANSACTION_TYPE,AUTH_CODE,RESPONSE_CODE,SUCCESS,AUTH_AMOUNT,APPROVE_AMOUNT,RESPONSE_TEXT from PAYAUTH.ACTIVITY_LOG where order_number='" + orderNumber + "'";
	      System.out.println(query);
	      isGettingResultSet = isResultSetGenerated_OracleOraclePayAuthDB(query);
	      if(isGettingResultSet){
	    	  ResultSet rs = getResultSet_OraclePayAuthDB(query);
		      actualTableData [0][0] =  "ORDER NUMBER";			
		      actualTableData [0][1] =  "TRANSACTION DATE";
//		      actualTableData [0][2] =  "TRANSACTION TYPE";				
		      actualTableData [0][2] =  "AUTH TYPE";
		      actualTableData [0][3] =  "AUTH CODE";
		      actualTableData [0][4] =  "RESPONSE_CODE"; 
		      actualTableData [0][5] =  "SUCCESS";					
		      actualTableData [0][6] =  "AUTH AMOUNT";	
		      actualTableData [0][7] =  "APPROVE AMOUNT";	
		      actualTableData [0][8] =  "RESPONSE TEXT";
		      delayFor(20);
		      try
		      {
		            next = rs.next();
		            if (next = true)
		            {
		            //String actualFieldValue = rs.getString(fieldName);
		            for(int c=1;c<10;c++)
		            {
		                  if(c==2){
		                        temp= rs.getString(c);
		                        actualTableData[1][c-1] = getSubstring(temp, 0, 10);
		                        
		                  }
		                  else if(c==7){
		                        temp= rs.getString(c);
		                        if(temp!=null){
		                        	actualTableData[1][c-1] = rs.getString(c);	
		                        }
		                        else{
		                        	temp = "0.00";
		                        	actualTableData[1][c-1] =temp;
		                        }
		                        
		                        
		                        
		                  }
		                  else{
		                        actualTableData[1][c-1] = rs.getString(c);
		                  }
		            }
		            
		            } // If
		            result = performTest(new XManualVerificationPoint(vpName, this, expected, actualTableData));
		            
		      }
		      catch (Exception ex) 
		      {
		            logException(ex);
		      }
		      finally
		      {
		            try 
		            { 
		                  rs.close();
		                  statement.close();
		                  connection.close();
		            
		            } 
		            
		            catch(SQLException ex)
		            {
		                  logException(ex);
		            }

		      }
	      }
	      else{
	    	  logError("Database data not retrieved");
	      }
	      return result;
	}
	/**
	 *  
	 * 
	 * @param vpName
	 * @param response = requires a JCHECKOUTDOC schema ( example: Instant Purchase response )
	 * @return
	 */

	public boolean verifyOrderDetails_OraclePayAuthDB_IP(String vpName,IResponse response,boolean isGiftCard)
	{
		  String[][] expected = new String[2][6];    
		  String[][] actualTableData = new String[2][6];
	      String orderNumber = getNodeValue(response, JCHECKOUTDOC_ORDER_XPATH + "/@orderNumber"); //jcheckoutServices.getOrderNumber(response);
	      String temp=null;
	      boolean result = false;
	      	expected [0][0] =  "ORDER NUMBER";			
			expected [0][1] =  "TRANSACTION DATE";
			expected [0][2] =  "AUTH TYPE";
			expected [0][3] =  "SUCCESS";					
			expected [0][4] =  "AUTH AMOUNT";	
			expected [0][5] =  "APPROVE AMOUNT";	

			expected [1][0] =  orderNumber;			//order_number,
			expected [1][1] =  getOrderDate(response); //TRANSACTION_DATE,
			expected [1][2] =  getAuthType(response);	//AUTH_TYPE,
			expected [1][3] =  "Y";					//SUCCESS,
			String authAmount = getAuthAmountFromGC(response);
			if(!authAmount.equals("")){
				if(isOneDecimal(authAmount)){
					authAmount = authAmount+"0";
				}
			}
			expected [1][4] =  authAmount;	//AUTH_AMOUNT,
			String approveAmount = getAuthApprovedAmountFromGC(response);
			if(!authAmount.equals("")){
				if(isOneDecimal(approveAmount)){
					approveAmount = approveAmount+"0";
				}
			}
			expected [1][5] =  approveAmount;	//APPROVE_AMOUNT,2940000952740  qaautomation46683@book.com
			
	      String query ="select ORDER_NUMBER,TRANSACTION_DATE,TRANSACTION_TYPE,SUCCESS,AUTH_AMOUNT,APPROVE_AMOUNT from PAYAUTH.ACTIVITY_LOG where order_number='" + orderNumber + "'";
	      System.out.println(query);
	      ResultSet rs = getResultSet_OraclePayAuthDB(query);
	      actualTableData [0][0] =  "ORDER NUMBER";			
	      actualTableData [0][1] =  "TRANSACTION DATE";
	      actualTableData [0][2] =  "AUTH TYPE";
	      actualTableData [0][3] =  "SUCCESS";					
	      actualTableData [0][4] =  "AUTH AMOUNT";	
	      actualTableData [0][5] =  "APPROVE AMOUNT";	
	      delayFor(20);
	      try
	      {
	            rs.next();
	            //String actualFieldValue = rs.getString(fieldName);
	            for(int c=1;c<7;c++)
	            {
	                  if(c==2){
	                        temp= rs.getString(c);
	                        actualTableData[1][c-1] = getSubstring(temp, 0, 10);
	                        
	                  }
	                  else if(c==5){
	                        temp= rs.getString(c);
	                        if(temp!=null){
	                        	actualTableData[1][c-1] = rs.getString(c);	
	                        }
	                        else{
	                        	temp = "0.00";
	                        	actualTableData[1][c-1] =temp;
	                        }
	                        
	                        
	                        
	                  }
	                  else{
	                        actualTableData[1][c-1] = rs.getString(c);
	                  }
	            }
	            result = performTest(new XManualVerificationPoint(vpName, this, expected, actualTableData));
	            
	      }
	      catch (Exception ex) 
	      {
	            logException(ex);
	      }
	      finally
	      {
	            try 
	            { 
	                  rs.close();
	                  statement.close();
	                  connection.close();
	            
	            } 
	            
	            catch(SQLException ex)
	            {
	                  logException(ex);
	            }

	      }
	      return result;
	}
	/**
	 *  use this when you convert json file to XML
	 * 
	 * @param vpName
	 * @param response = requires a JCHECKOUTDOC schema ( example: Instant Purchase response )
	 * @return
	 */
	public boolean verifyOrderDetails_OraclePayAuthDB_IP(String vpName,String response,boolean isGiftCard)
	{
		  String[][] expected = new String[2][6];    
		  String[][] actualTableData = new String[2][6];
	      String orderNumber = getNodeValue(response, JCHECKOUTDOC_ORDER_XPATH + "/@orderNumber"); //jcheckoutServices.getOrderNumber(response);
	      String temp=null;
	      boolean result = false;
	      	expected [0][0] =  "ORDER NUMBER";			
			expected [0][1] =  "TRANSACTION DATE";
			expected [0][2] =  "AUTH TYPE";
			expected [0][3] =  "SUCCESS";					
			expected [0][4] =  "AUTH AMOUNT";	
			expected [0][5] =  "APPROVE AMOUNT";	

			expected [1][0] =  orderNumber;			//order_number,
			expected [1][1] =  getOrderDate(response); //TRANSACTION_DATE,
			expected [1][2] =  getAuthType(response);	//AUTH_TYPE,
			expected [1][3] =  "Y";					//SUCCESS,
			String authAmount = getAuthAmountFromGC(response);
			if(isOneDecimal(authAmount)){
				authAmount = authAmount+"0";
			}
			expected [1][4] =  authAmount;	//AUTH_AMOUNT,
			String approveAmount = getAuthApprovedAmountFromGC(response);
			if(isOneDecimal(approveAmount)){
				approveAmount = approveAmount+"0";
			}
			expected [1][5] =  approveAmount;	//APPROVE_AMOUNT,2940000952740  qaautomation46683@book.com
			
	      String query ="select ORDER_NUMBER,TRANSACTION_DATE,TRANSACTION_TYPE,SUCCESS,AUTH_AMOUNT,APPROVE_AMOUNT from PAYAUTH.ACTIVITY_LOG where order_number='" + orderNumber + "'";
	      System.out.println(query);
	      ResultSet rs = getResultSet_OraclePayAuthDB(query);
	      actualTableData [0][0] =  "ORDER NUMBER";			
	      actualTableData [0][1] =  "TRANSACTION DATE";
	      actualTableData [0][2] =  "AUTH TYPE";
	      actualTableData [0][3] =  "SUCCESS";					
	      actualTableData [0][4] =  "AUTH AMOUNT";	
	      actualTableData [0][5] =  "APPROVE AMOUNT";	
	      delayFor(20);
	      try
	      {
	            rs.next();
	            //String actualFieldValue = rs.getString(fieldName);
	            for(int c=1;c<7;c++)
	            {
	                  if(c==2){
	                        temp= rs.getString(c);
	                        actualTableData[1][c-1] = getSubstring(temp, 0, 10);
	                        
	                  }
	                  else if(c==5){
	                        temp= rs.getString(c);
	                        if(temp!=null){
	                        	actualTableData[1][c-1] = rs.getString(c);	
	                        }
	                        else{
	                        	temp = "0.00";
	                        	actualTableData[1][c-1] =temp;
	                        }
	                        
	                        
	                        
	                  }
	                  else{
	                        actualTableData[1][c-1] = rs.getString(c);
	                  }
	            }
	            result = performTest(new XManualVerificationPoint(vpName, this, expected, actualTableData));
	            
	      }
	      catch (Exception ex) 
	      {
	            logException(ex);
	      }
	      finally
	      {
	            try 
	            { 
	                  rs.close();
	                  statement.close();
	                  connection.close();
	            
	            } 
	            
	            catch(SQLException ex)
	            {
	                  logException(ex);
	            }

	      }
	      return result;
	}
	/**
	 *Use this method when you convert your json file to XML
	 * 
	 * @param vpName
	 * @param response = requires a JCHECKOUTDOC schema ( example: Instant Purchase response )
	 * @return
	 */
	public boolean verifyOrderDetails_OraclePayAuthDB_IP(String vpName,String response)
	{
		  String[][] expected = new String[2][9];    
		  String[][] actualTableData = new String[2][9];
	      String orderNumber = getNodeValue(response, "//orderNumber"); //jcheckoutServices.getOrderNumber(response);
	      String temp=null;
	      boolean result = false;
	      	expected [0][0] =  "ORDER NUMBER";			
			expected [0][1] =  "TRANSACTION DATE";
			expected [0][2] =  "AUTH TYPE";
			expected [0][3] =  "AUTH CODE";
			expected [0][4] =  "RESPONSE_CODE"; 
			expected [0][5] =  "SUCCESS";					
			expected [0][6] =  "AUTH AMOUNT";	
			expected [0][7] =  "APPROVE AMOUNT";	
			expected [0][8] =  "RESPONSE TEXT";
			
			expected [1][0] =  orderNumber;			//order_number,
			expected [1][1] = getNodeValue(response, "//orderDate").substring(0, 10); //TRANSACTION_DATE,  
			expected [1][2] =  getNodeValue(response, "//authType");	//AUTH_TYPE,
			expected [1][3] =  getNodeValue(response, "//authCode");//AUTH_CODE,  
			expected [1][4] =  getNodeValue(response, "//authResponseCode"); //RESPONSE_CODE,  
			expected [1][5] =  "Y";					//SUCCESS,   authResponse
			String authAmount = getNodeValue(response, "//authAmount");
			if(isOneDecimal(authAmount)){
				authAmount = authAmount+"0";
			}
			expected [1][6] =  authAmount;	//AUTH_AMOUNT, 
			String approveAmount = getNodeValue(response, "//authApprovedAmount");
			if(isOneDecimal(approveAmount)){
				approveAmount = approveAmount+"0";
			}
			expected [1][7] =  approveAmount;	//APPROVE_AMOUNT,2940000952740  qaautomation46683@book.com
			expected [1][8] =  getNodeValue(response, "//authResponse");//RESPONSE_TEXT
			
	      String query ="select ORDER_NUMBER,TRANSACTION_DATE,TRANSACTION_TYPE,AUTH_CODE,RESPONSE_CODE,SUCCESS,AUTH_AMOUNT,APPROVE_AMOUNT,RESPONSE_TEXT from PAYAUTH.ACTIVITY_LOG where order_number='" + orderNumber + "'";
	      System.out.println(query);
	      ResultSet rs = getResultSet_OraclePayAuthDB(query);
	      actualTableData [0][0] =  "ORDER NUMBER";			
	      actualTableData [0][1] =  "TRANSACTION DATE";
//	      actualTableData [0][2] =  "TRANSACTION TYPE";				
	      actualTableData [0][2] =  "AUTH TYPE";
	      actualTableData [0][3] =  "AUTH CODE";
	      actualTableData [0][4] =  "RESPONSE_CODE"; 
	      actualTableData [0][5] =  "SUCCESS";					
	      actualTableData [0][6] =  "AUTH AMOUNT";	
	      actualTableData [0][7] =  "APPROVE AMOUNT";	
	      actualTableData [0][8] =  "RESPONSE TEXT";
	      delayFor(20);
	      try
	      {
//	            rs.next();
	            if (rs.next())
	            {
	            	//String actualFieldValue = rs.getString(fieldName);
	            	for(int c=1;c<10;c++)
	            	{
	                  	if(c==2){
	                        temp= rs.getString(c);
	                        actualTableData[1][c-1] = getSubstring(temp, 0, 10);
	                        
	                  	}
	                  	else if(c==7){
	                        temp= rs.getString(c);
	                        if(temp!=null){
	                        	actualTableData[1][c-1] = rs.getString(c);	
	                        }
	                        else{
	                        	temp = "0.00";
	                        	actualTableData[1][c-1] =temp;
	                        }
	                        
	                        
	                        
	                  	}
	                  	else{
	                        actualTableData[1][c-1] = rs.getString(c);
	                  	}
	                 }
	                 
	            }
	            result = performTest(new XManualVerificationPoint(vpName, this, expected, actualTableData)); 
	            
	            
	      }
	      catch (Exception ex) 
	      {
	            logException(ex);
	      }
	      finally
	      {
	            try 
	            { 
	                  rs.close();
	                  statement.close();
	                  connection.close();
	            
	            } 
	            
	            catch(SQLException ex)
	            {
	                  logException(ex);
	            }

	      }
	      return result;
	}
	/*
	public boolean verifyItemsInDigitalLocker(String vpName,String customerID,String [] expectedItems){
		
		boolean result = false;
		DigitalLocker locker = new DigitalLocker(customerID); 
		result=performTest(new XManualVerificationPoint(vpName, this, expectedItems, locker.getLockerItemList()));
		
		return result;
	}
	public boolean verifyItemsInDigitalLocker(String vpName,String customerID){
		
		boolean result = false;
		DigitalLocker locker = new DigitalLocker(customerID); 
		result=performTest(new XManualVerificationPoint(vpName, this, locker.getLockerItemList()));
		
		return result;
	}
	*/
	/**
	 * 
	 * 
	 */
	public String generateCouponFromBaseCode(String baseCode){
		
        String coupon = null;
        String xmlBody =  "<request><couponType>accountless</couponType><baseCode>" + baseCode + "</baseCode><count>1</count></request>";
		String url =  envUtil.caliber().serverName() +"/CouponService/generateCoupons";
		// Generate Cooupon
		IResponse response = caliber().body(xmlBody).contentType("application/xml")
		                              .post(url);
		
		coupon = getNodeValue(response, "/response/coupons/coupon/displayCode");
		
		return coupon;
	}
	/**
	 * @param baseCode  : base code 
	 * @param quantity  : how many coupons needs to be generated
	 * @return  getNodeCount is not working...This method is !!!!! OOS  !!!!!
	 */
    public String[] generateCouponFromBaseCode(String baseCode, String quantity){ 
		
    	int cCount;
    	String[] coupon = null;
        String xmlBody =  "<request><couponType>accountless</couponType><baseCode>" + baseCode + "</baseCode><count>" + quantity + "</count></request>";
		String url =  envUtil.caliber().serverName() +"/CouponService/generateCoupons";
		// Generate Cooupon
		IResponse response = caliber().body(xmlBody).contentType("application/xml")
		                              .post(url);
		cCount =  getNodeCount(response, "/response/coupons/coupon");
		
		for (int i = 1;  i <= cCount;i++)
		{
			coupon = new String[cCount];
			coupon[i - 1] = getNodeValue(response, "/response/coupons/coupon"+ "[" + i + "]" + "/displayCode" );
		}
		//coupon = getNodeValue(response, "/response/coupons/coupon/displayCode");
		
		return coupon;
	}
  //make sure you send the node as "count(/response)"
	public static int getNodeCount(IResponse response, String node){
		
		boolean vp = false;
		String xmlDoc = "";
		Object value="";
		int count = 0;
		
		if(response.getStatusCode() == 200 ){
			
			String contentType = response.getHeader("Content-Type");
			System.out.println("Content-Type=" + contentType);
			if(contentType == null)
			{
				contentType = "";
			}
			//if(contentType.equalsIgnoreCase("application/json"))
			if(contentType.toLowerCase().contains("application/json"))
			{
				String json = response.getBody();
				JSONObject jsonObject = JSONObject.fromObject( json ); 
				xmlDoc = new XMLSerializer().write( jsonObject ); 
			}
			else {
				xmlDoc = response.getBody();
			}
			
			
			Document doc = getDocumentFromStream(xmlDoc);
			doc.getDocumentElement().normalize();
			XPathReader xPath = new XPathReader(xmlDoc);
			
			value = xPath.read(node,XPathConstants.NUMBER);
			if(value != null)
			{
				System.out.println(value.toString());
				count = Integer.parseInt(value.toString());
			}
			
		}
		return count;
	}
	public static SpreadSheetUtil orderList = null;
	public static String orderListSpreadSheet = "\\\\bnyfsfile05\\QADept\\QA Automation\\OrderNumberList.xls";  //"C:\\Users\\srahman\\Desktop\\OrderNumberList.xls"; //
	
	public static SpreadSheetUtil orderList(){
        if (orderList == null){
		   orderList = new SpreadSheetUtil(orderListSpreadSheet, 0);
        }
		return orderList;
	}
	public void writeOrderNumToOrderList(IResponse response)
	{
		
    	String responseType = "xml";
    	String orderXPath = "";
		String response_body = "";
		String orderNo = "";
		
    	responseType = response.getHeader("Content-Type");
    	
    	if (responseType.contains("xml"))
    	{
    		orderXPath = JCHECKOUT_ORDER_NUMBER_XPATH;
    		orderNo = getNodeValue(response, orderXPath);
    		
    	}
    	else if (responseType.contains("json"))
    	{
    		orderXPath = JCHECKOUT_ORDER_NUMBER_JPATH;
    		response_body = convertJSONtoXML(response.getBody());
    		orderNo = getNodeValue(response_body, orderXPath);
    	}
    	else
    	   System.out.println("Response type can not be determined");
		
    	
		if (orderNo != null && orderNo.length() != 0)
		{
			int rowCount = orderList().getRowCount()+1;
			orderList().readRow(rowCount-1);
			orderList().setCellStringValue("Order NO", orderNo);
			orderList().setCellStringValue("Date", getCurrentDate());
		}
		
		
	}
	public String getCurrentDate(){
	    String currentDate;
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	   Date date = new Date();
	   currentDate = dateFormat.format(date);
	   System.out.println(dateFormat.format(date));
	   return currentDate;
	   
	  }
	public String convertJSONtoXML(String json)
	{
		String xml = null;
		JSONObject jsonObject = JSONObject.fromObject( json ); 
		xml = new XMLSerializer().write( jsonObject );
		return xml;
	}
    public void clearCartIntl(String customerID)
    {   
    	String urlClearCart;
    	String cartClientId = "41";
    	String id = customerID; 
    	String idType = "customerId";
    	String country = "GB";
    	String ratailerID = "NOK";
    	
    	
    	urlClearCart = BASE_JCHECKOUT_URL + BASE_CLEAR_CART_PATH  + "cartClientId=" + cartClientId + "&idType=" + idType + "&id=" + id + "&country=" + country + "&retailerID=" + ratailerID;
		IResponse response3 = caliber().body("").contentType("application/xml").post(urlClearCart);
    }
    
    public String addItemXML(String ean, int quantity)
	{
		String xml = "<cartItem><ean>" + ean + "</ean><quantity>" + quantity + "</quantity></cartItem>";
		return xml;
	}
    
    public String getAddCCXML(String ccNumber, String type, String expiration, String securityCode)
	{
		String xml = "<creditCard><code>" + type + "</code><expiry>" + expiration + "</expiry><securityCode>" + securityCode + "</securityCode><numberPlain>" + ccNumber + "</numberPlain></creditCard>";
		return xml;
	}

    public String getSetEmailXML(String email)
	{
		String xml = "<profile><email>" + email + "</email></profile>";  //checkout__b5egag6p449@book.com
		return xml;
	}
    
    public String setShiServiceLevelXML(String code, String shippingMethodId)
	{
		String xml = "<shippingMethod><code>" + code + "</code><shippingMethodId>" + shippingMethodId + "</shippingMethodId></shippingMethod>";
		return xml;
	}
    /**
     * 
     * @return
     */
    public String generateUSAccountWithPayment(){
    	
    	// Add Payment Method 
		String xml_CreateAccount = "";
		String id; // = "";
		
		xmlUtility().readXMLFile(ADDRESSBOOK_US_TESTDATA_PATH);
		xml_CreateAccount = xmlUtility().getXMLString();
		//logRequestXml(xml_CreateAccount);
		//Creating user with Visa Credit card
		customerUserAccount().generateUserAccountWithPayment("VI","4313081835209051",xml_CreateAccount);
        //addressID = customerUserAccount().getAddressId();
		id = customerUserAccount().getCustomerId();   //"i007X65RNYJYO810" ; //
		
		
    	return id;
    }
    /*
    
    public String createIntlAccountWithPayment() throws IOException
    {
    	
    	
    	
    	return createAccountWithAddPayment(intlAddressAddPaymentXML); 
    	

    }
    public String createAccountWithAddPayment(String addPaymentXML) throws IOException
    {
    	String xml = "";
    	String urlUserAcc = "";
    	String urlAddPayment = "";
    	String id = getNewCustomerId(); //getNewCloudCustomerId();
    	
    	String responseCode = "500";
    	
    	// read XML
    	xml = addPaymentXML;
    	urlAddPayment = BASE_ADDRESSVALIDATION_URL + ADD_PAYMENT_METHOD + "customerid=" + id + "&validateAddress=true";
    	IResponse response = caliber().body(xml).contentType("application/xml").post(urlAddPayment);
    	responseCode = getNodeValue(response, "//response/@code");
    	
    	if (responseCode.equals("200")|| responseCode.equals("400"))
    	{
    		return id;
    	}
    	else return responseCode;
    }
    */
}
