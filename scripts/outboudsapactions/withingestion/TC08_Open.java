package scripts.outboudsapactions.withingestion;
import resources.scripts.outboudsapactions.withingestion.TC08_OpenHelper;
import utils.XMLUtility;
import java.util.Properties;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.bn.service.atlas.message.generation.JmsProxy;
import com.bn.service.atlas.message.generation.JmsProxyUsingActiveMq;
import com.bn.service.atlas.message.generation.MessageType;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import framework.DBValidationService;
import javax.jms.Message;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC08_Open extends TC08_OpenHelper
{
	/**
	 * Script Name   : <b>TC08_Open</b>
	 * Generated     : <b>Jun 13, 2014 2:41:44 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/13
	 * @author zsadeque
	 * @throws Exception 
	 */
	public void testMain(Object[] args) throws Exception 
	{
		String orderNumber,customerID,timeStamp,ean = null;
		ean = "2940043922267";
		//atlasManagementTool().suspendRoute("injeaijapp02", "FeOrder-Idoc-SendToSap");
	
		instantPurchase().submitIPOrder("VI", "4313081835209051", ean);
		instantPurchase().verifyInstantPurchase("IPStatus");
		customerID = instantPurchase().getCustomerID();
		orderNumber=instantPurchase().getOrderNumber();
		timeStamp = instantPurchase().getOrderTimeStamp();
		
		  String filePath = "C:\\Users\\zsadeque\\workspace\\Atlas-Messages\\src\\com\\bn\\testdata\\GXS\\open.xml";
		  XMLUtility xmlUtil = null;
		  xmlUtil = new XMLUtility(filePath);
		  
		  xmlUtil.setNodeValueByXPath("//IDOC/E1EDP02/BELNR", orderNumber);
		  xmlUtil.setNodeValueByXPath("//IDOC/E1EDK02/BELNR", orderNumber);
		  final Properties properties = new Properties();
	      properties.put("body", xmlUtil.getXMLString());
	      final Message message = MessageType.xml.create(properties, false);
	      
	      final JmsProxy proxy = new JmsProxyUsingActiveMq("tcp://injeaijapp02:61616");
//	      proxy.send("test111", message);
	      proxy.send("Sap-Idoc-DispatchToWorkflow", message);
	    ///  atlasManagementTool().startRoute("injeaijapp02", "FeOrder-Idoc-SendToSap");
	}
}

