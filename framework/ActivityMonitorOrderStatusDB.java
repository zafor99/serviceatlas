package framework;

import org.apache.log4j.Logger;

import utils.XMLUtil;

import com.bn.qa.webservice.restclient.specification.IResponse;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 22, 2014
 */
public class ActivityMonitorOrderStatusDB extends CaliberService
{
	private static Logger logger = Logger.getLogger(ActivityMonitorOrderStatusDB.class);
	private String orderXML = null;
	private ActivityMonitorService activityMonitor = null;//new ActivityMonitorService();
	public ActivityMonitorOrderStatusDB(){
		
	}
	
	public ActivityMonitorOrderStatusDB(String orderNumber){
		activityMonitor = new ActivityMonitorService();
		orderXML = activityMonitor.getOrderXML(orderNumber);
		
	}
	public void verifyAcertifyServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyAcertifyServiceStatusInInstantPurchase("+vpName+")");
		
		verifyXML(vpName, orderXML,"//order/serviceCallInfo/acertifyStatus");
	}
	public void verifyVertexServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyVertexServiceStatusInInstantPurchase("+vpName+")");
		
		verifyXML(vpName,orderXML,"//order/serviceCallInfo/vertexStatus");
	}

	public void verifyEDSServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyEDSServiceStatusInInstantPurchase("+vpName+")");
		
		verifyXML(vpName,orderXML,
				 "//order/serviceCallInfo/edsInsertStatus",
				 "//order/serviceCallInfo/edsStatus"

		);
	}
	public void verifyCCAuthorizationServiceStatusInInstantPurchase(String vpName){
		logger.info("verifyEDSServiceStatusInInstantPurchase("+vpName+")");
		
		verifyXML(vpName,orderXML,
				 "//order/serviceCallInfo/authStatus",
				 "//order/serviceCallInfo/authType"

		);
	}
	public void verifyInstantPurchase(String vpName){
		logger.info("verifyInstantPurchase("+vpName+")");
		
		verifyXML(vpName,orderXML,
				"//order/serviceCallInfo/acertifyStatus",
				 "//order/serviceCallInfo/authStatus",
				 "//order/serviceCallInfo/authType",
				 "//order/serviceCallInfo/edsInsertStatus",
				 "//order/serviceCallInfo/edsStatus",
				 "//order/serviceCallInfo/vertexStatus"
		);

	}
	
}
