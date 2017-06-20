package framework;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import utils.EnvironmentUtility;
import utils.XMLUtility;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 30, 2013
 */
public class DeliveryConfirmationService extends CaliberService
{
	private static Logger logger = Logger.getLogger(DeliveryConfirmationService.class);
	private String fileName = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\atlasDeliveryConfirmation.xml";
	private XMLUtility xmlUtil = null;
	private String url = "";
	private EnvironmentUtility envUtil = null;
	private String clientId = "cstCIwT9TAyMHw9uuvy3Wg";
	private IResponse response = null;
	
	
	public DeliveryConfirmationService(){
		envUtil = new EnvironmentUtility();
		loadXmlFile(fileName);
		setRandomBatchNumber();
		setRandomSerialNumber();
	}
	
	public void loadXmlFile(String fileName){
		logger.info("loadXmlFile("+fileName+")");
		xmlUtil = new XMLUtility(fileName);
	}
	
	public void setClientId(String clientId){
		logger.info("setClientId("+clientId+")");
		this.clientId = clientId;
	}
	
	public void setRandomBatchNumber(){
		logger.info("setRandomBatchNumber");
		xmlUtil.setNodeAttributeByXPath("DeliveryConfirmationBatch", "BatchNumber", "QA-TEAT2_"+ new Date().getTime());
	}
	
	public void setDeliveryNumber(String deliveryNumber){
		logger.info("setDeliveryNumber("+deliveryNumber+")");
		xmlUtil.setNodeValueByXPath("//DeliveryNumber", deliveryNumber);
	}
	
	public void setRandomSerialNumber(){
		logger.info("setRandomSerialNumber");
		xmlUtil.setNodeValueByXPath("//LineItem/ItemData/SerialNo", new Date().getTime()+"");
	}
	
	public void post(){
		logger.info("post");
		//String url = envUtil.atlas().serverName() + "/Logistics/services/rs/WarehouseShippingAdvice/?client_id=" + clientId;
		String url = envUtil.atlas().serverName() + "/atlas-web/Logistics/services/rs/WarehouseShippingAdvice";
		
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("bn_client_id", "cstCIwT9TAyMHw9uuvy3Wg");
	
		System.out.println(xmlUtil.getXMLString());
		response = caliber().content("application/xml").headers(headers).body(xmlUtil.getXMLString()).post(url);
		System.out.println(response.getStatusCode());
		
	}
	
	public boolean verifyResponse(String vpName,String expectedResponse){
		logger.info("verifyResponse("+vpName+"),("+ expectedResponse+")");
		boolean result = false;
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, expectedResponse,response.getStatusCode() ).performTest();
		
		return result;
	}
	
	
}
