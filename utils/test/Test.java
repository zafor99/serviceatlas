package com.bn.qa.webservice.test;

import static com.bn.qa.webservice.restclient.BNRestful.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.specification.IResponseSpecification;
import com.bn.qa.webservice.restclient.utils.Util;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Get testing: ......");
		doGet();
		
		//System.out.println("Post testing: ......");
		//doPost();
		
	}

	public static void doGet(){
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("customerid", "000rTpcO80AO8og0");
		//parameters.put("ean", "9780307450975");
		//parameters.put("count", "3");
		
		
		given().parameters(parameters).header("Accept", "application/json").get("http://injsvcjapp01:8080/sellerService/getSellerByCustomerId?");
	}
	
	public static void doPost() throws IOException{
		
		/*String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml = xml + "<Request>";
		xml = xml + "<Get filterTemplate=\"min\" filterTemplateLinked=\"min\">";
		xml = xml + "<EANS>"  ;
		xml = xml + "<EAN>1234</EAN>";
		xml = xml + "<EAN>9780385533829</EAN>";
		xml = xml + "<EAN>2940000052327</EAN>";
		xml = xml + "</EANS>";
		xml = xml + "</Get>";
		xml = xml + "</Request>";*/
		
		String xml = Util.readFile("C:/MyDevelopment/Workspaces/RFTWorkspace/BNWebserviceClient/src/com/bn/qa/webservice/test/PostFile.xml");
		
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("requestType", "copurchasedeans");
		parameters.put("ean", "9780307450975");
		parameters.put("count", "5");
		
		IResponse res = given().body(xml).and().contentType("application/xml").post("http://10.1.162.118/ProductDataServices/rest/ProductData");
		//given().parameters(parameters).post("http://10.1.162.118/ProductDataServices/rest/ProductData");
		
		System.out.println(res.getValue());
		
		
		IRequestSpecification x = given();
	}
	

}
