package framework;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;
import com.bn.qa.xobject.vp.XManualVerificationPoint;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 30, 2014
 */
public  class ActivityMonitorService extends BaseService
{
	private static Logger logger = Logger.getLogger(ActivityMonitorService.class);
	private String url = "http://dnjesdb03:9200/*atlas-ac*/_search";
	private String url_logstash_atlas_activitymonitor = "http://dnjesdb03.barnesandnoble.com:9200/logstash-atlas-activitymonitor-*/_search";
	private String url_logstash_atlas_messagearchive = "http://dnjesdb03.barnesandnoble.com:9200/logstash-atlas-messagearchive-*/_search";
	private String jsonFileSearchActivityMonitor = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\activityMonitor\\SearchActivityMonitor.json";
	private String jsonFileGetWIDnTID = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\activityMonitor\\getWIDnTID.json";
	private String jsonFileGetOrderXML = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\activityMonitor\\getOrderXML.json";
	IResponse response = null;
	private String jsonSearchActivityMonitorContent = null;

	
	public String getJsonSearchActivityMonitorContent(String orderNumber){
		jsonSearchActivityMonitorContent = "{query:{\"bool\" : {\"must\" " +
				": [ {\"match\" : {\"PID\" : {\"query\" : \"" + orderNumber + 
				"\",\"type\" : \"boolean\"}} }, {\"match\" : {\"loglevel\" : {\"query\" : \"ERROR\",\"type\" : \"boolean\"}}}]}}}";
		System.out.println(jsonSearchActivityMonitorContent);
		return jsonSearchActivityMonitorContent;
		
	}
	public String getJsonSearchActivityMonitorContent(String orderNumber,String endPointName){
		jsonSearchActivityMonitorContent = "{query:{\"bool\" : {\"must\" " +
				": [ {\"match\" : {\"PID\" : {\"query\" : \"" + orderNumber + 
				"\",\"type\" : \"boolean\"}} }, {\"match\" : {\"ENDPOINT\" : {\"query\" : \""+endPointName+"\",\"type\" : \"boolean\"}}}]}}}";
		System.out.println(jsonSearchActivityMonitorContent);
		return jsonSearchActivityMonitorContent;
		
	}

	public IResponse getResponse(){
		return response;
	}
	public String getResponseFromActivityMonitor(String orderNumber,String endPointName) {
		String responseText ="";
		logger.info("getResponseFromActivityMonitor("+orderNumber+"),("+endPointName+")");
		String json,xmlContent;
		int count;
			json = getJsonSearchActivityMonitorContent(orderNumber, endPointName);
		response = caliber().body(json).contentType("application/json").post(url);
		System.out.println(response.getBody());
		responseText = convertJSONtoXML(response.getBody());
		System.out.println(getNodeValue(response, "//hits/total"));
		return responseText;

	}
	public boolean verifyNeolaneCountInActivityMonitor(String orderNumber,String vpName){
		boolean result = false;
		String responseText = null;
		String neolaneEventCount = null;
		logger.info("verifyActivityMonitorForError("+orderNumber+"),("+vpName+")");
		responseText = getResponseFromActivityMonitor(orderNumber, "NEOLANE_EMAIL_SERVICE");
		neolaneEventCount = getNodeValue(response, "//hits/total");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName,neolaneEventCount).performTest();
		return result;
		
	}
	public boolean verifyActivityMonitorForError(String orderNumber,String vpName) {
		boolean result = false;
		logger.info("verifyActivityMonitorForError("+orderNumber+"),("+vpName+")");
		String json,xmlContent;
		int count;
		try {
			json = Util.readFile(jsonFileSearchActivityMonitor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = caliber().body(getJsonSearchActivityMonitorContent(orderNumber)).contentType("application/json").post(url);
		System.out.println(response.getBody());
		xmlContent = convertJSONtoXML(response.getBody());
		count= getNodeCount(response, "//loglevel");
		System.out.println(getNodeValue(response, "//loglevel"));
		System.out.println(getNodeValue(response, "//MESSAGE"));
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName,count).performTest();
		if(!result){
			logError("Log Level :"+getNodeValue(response, "//loglevel"));
			logError("Message :"+getNodeValue(response, "//MESSAGE"));
		}
		return result;

	}
	public String[] getWIDnTIDForOSDBByPID(String orderNumber){
		logger.info("getWIDnTIDForOSDBByPID("+orderNumber+")");
		String json = "";
		String xmlContent,wid,tid = null;
		String[] wIDnTID= new String[2];
		try {
			json = Util.readFile(jsonFileGetWIDnTID);
			json = json.replaceFirst("111111111", orderNumber);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = caliber().body(json).contentType("application/json").post(url_logstash_atlas_activitymonitor);
		System.out.println(response.getBody());
		xmlContent = convertJSONtoXML(response.getBody());
		tid = getNodeValue(response, "//o/hits/hits/e/_source/TID");
		wid = getNodeValue(response, "//o/hits/hits/e/_source/WID");
		System.out.println("TID is :"+ tid);
		System.out.println("WID is :"+ wid);
		wIDnTID[0] = wid;
		wIDnTID[1] = tid;
		
		return wIDnTID;
	}
	public String getOrderXML(String orderNumber){
		String orderXML="";
		String xmlContent,json= null;
		String []widNTID = null; 
		widNTID= getWIDnTIDForOSDBByPID(orderNumber);
		try {
			json = Util.readFile(jsonFileGetOrderXML);
			json = json.replaceFirst("11111", widNTID[0]);
			json = json.replaceFirst("22222", widNTID[1]);
			
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = caliber().body(json).contentType("application/json").post(url_logstash_atlas_messagearchive);
		System.out.println(response.getBody());
		xmlContent = convertJSONtoXML(response.getBody());
		System.out.println(xmlContent);
		orderXML=getNodeValue(response, "//o/hits/hits/e/_source/BODY");
		return orderXML;
	}
}
