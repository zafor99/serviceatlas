package com.bn.qa.webservice.test;

import static com.bn.qa.webservice.restclient.BNRestful.given;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


import com.bn.qa.webservice.restclient.specification.IResponse;

public class JSonTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String json = "{\"request\" : {" ;
		json = json + "\"get\":{";
		json = json + "   \"eans\":[\"1234\",\"9780385533829\",\"2940000052327\"] , ";
		json = json + "    \"filterTemplate\":\"min\", " ;
		json = json + "    \"filterTemplateLinked\":\"min\"                     }";
		json = json + "}";
		json = json + "}";
		
		IResponse res = given().body(json).and().contentType("application/json").post("http://10.1.162.170/ProductDataServices/rest/ProductData");
		
		System.out.println(res.getValue());
		
		String jsonString = (String) res.getValue();
		
		JSONObject jsonObject = JSONObject.fromObject( jsonString );  
		Object bean = JSONObject.toBean( jsonObject );
		String xml = new XMLSerializer().write( jsonObject ); 
		
		System.out.println(xml);
		
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("requestType", "copurchasedeans");
		parameters.put("ean", "9780307450975");
		parameters.put("count", "3");
		
		//IResponse res = given().parameters(parameters).get("http://10.1.168.64/RecommendationService/getRecommendations?");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.getBody());
		
	}

}
