package tempscripts;
import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.InitialContext;

import org.activemq.broker.jmx.BrokerViewMBean;
import org.activemq.command.ActiveMQMessage;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import resources.tempscripts.Script1Helper;
import utils.BNTimer;
import utils.EnvironmentUtility;
import utils.XMLUtility;

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
import com.sun.corba.se.pept.broker.Broker;
import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

import framework.sapcontroller.SAPDisplaySalesOrderInitialScreenDialogController;
import framework.sapmodel.SAPDisplayDeviceOrderOverViewDialogModel;
import framework.sapmodel.SAPDisplaySalesOrderInitialScreenDialogModel;
import framework.sapmodel.SAPGuiTableControl;
import utils.SpreadSheetUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/*import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

import resources.tempscripts.Script1Helper;
*/
import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;
import static com.bn.qa.webservice.restclient.BNRestful.given;
/**
 * Description   : Functional Test Script
 * @author fahmed1
 */
public class Script1 extends Script1Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>May 3, 2013 3:26:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 147491508
	 * 147491724
	 * @since  2013/05/03
	 * @author fahmed1
	 * @throws Exception 
	 */
	public void testMain(Object[] args) throws Exception 
	{
		
/*		try {
	        //URL url = new URL ("http://ip:port/download_url");
	        URL url = new URL("http://injsvcjapp01:8161/admin/xml/queues.jsp");
	        String authStr = "testadmin" + ":" +  "mq&NspEe";
	        String authEncoded = Base64.encodeBase64String(authStr.getBytes()) ;// encodeBytes(authStr.getBytes());

	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Authorization", "Basic " + authEncoded);

	        
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }*/
	
		
/*		
		URL url = new URL("http://injsvcjapp01:8161/admin/xml/queues.jsp");
		String userPassword = "testadmin" + ":" +  "mq&NspEe";
		String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Authorization", "Basic " + encoding);
		uc.connect();
	//	uc.get
*/				
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("X-BN-Auth-Organization", "Nook");
		headers.put("X-BN-Auth-Role", "Developer");
		headers.put("X-BN-Auth-User", "Tester");
		headers.put("accept", "application/json");


		IResponse response = caliber().headers(headers).content("application/xml").get("http://injsvcjapp01:8161/admin/xml/queues.jsp");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
/*		 Session sess = session.createSession(false, Session.AUTO_ACKNOWLEDGE);
		  
		Queue replyTo = session.createTemporaryQueue();
		MessageConsumer consumer = session.createConsumer(replyTo);
		 
		String queueName = "ActiveMQ.Statistics.Broker";
		Queue testQueue = session.createQueue(queueName);
		MessageProducer producer = session.createProducer(testQueue);
		Message msg = session.createMessage();
		msg.setJMSReplyTo(replyTo);
		producer.send(msg);
		 
		MapMessage reply = (MapMessage) consumer.receive();
		assertNotNull(reply);
		 
		for (Enumeration e = reply.getMapNames();e.hasMoreElements();) {
		  String name = e.nextElement().toString();
		  System.out.println(name + "=" + reply.getObject(name));
		}*/
/*
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://injsvcjapp01:1299/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url);

		MBeanServerConnection conn = jmxc.getMBeanServerConnection();
		
		ObjectName activeMQ = new ObjectName("org.apache.activemq:BrokerName=injsvcjapp01,Type=Broker");
		BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(conn, activeMQ,BrokerViewMBean.class, true);
		*/
		//System.out.println(mbean.getTotalConsumerCount());
		
		  /*// get the initial context
        InitialContext ctx = new InitialContext();

        // lookup the queue object
        Queue queue = (Queue) ctx.lookup("queue/queue0");

        // lookup the queue connection factory
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
            lookup("queue/connectionFactory");

        // create a queue connection
        QueueConnection queueConn = connFactory.createQueueConnection();

        // create a queue session
        QueueSession queueSession = queueConn.createQueueSession(false,
            Session.AUTO_ACKNOWLEDGE);

        // create a queue browser
        QueueBrowser queueBrowser = queueSession.createBrowser(queue);

        // start the connection
        queueConn.start();

        // browse the messages
        Enumeration e = queueBrowser.getEnumeration();
        int numMsgs = 0;

        // count number of messages
        while (e.hasMoreElements()) {
            Message message = (Message) e.nextElement();
            numMsgs++;
        }

        System.out.println(queue + " has " + numMsgs + " messages");

        // close the queue connection
        queueConn.close();
*/		/*sap().dataBrowserTableSelectionScreenDialog().execute("148008359");
		if(sap().dataBrowserTableEDIDCDialog().waitForExistence(10)){
			System.out.print("true");
		}
		else{
			System.out.print("false");
		}*/
//		String status = sap().dataBrowserTableEDIDCDialog().getStatus();
		
	//	digitalLocker("000ta1LVl4JYO810").verifyLockerItem("DigitalLocker");
		//sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
		
	/*	crm().startApplication();
		crm().signInDialog().signIn();
		crm().selectABusinessRolePage().clickZBNT1CSRLink();
		
		crm().logOff();
		crm().closeApplication();*/
		
		
		//sap().ediOrderProcessingDialog().checkSLByCustomerPO("884958");
		/*String orderStatus,orderNumber = null;
		orderNumber = "147859963";
		if(!isShellMode()){
			crm().startApplication();
			crm().signInDialog().signIn();
			crm().selectABusinessRolePage().clickZBNT1CSRLink();
		}
	//	orderNumber = "147531674";
	//	delayFor(30);
		
		crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
		if(crm().identifyAccountPage().accountConfirmPanel().isOrderExist()){
			crm().identifyAccountPage().accountConfirmPanel().confirm();
			crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderClosed");
			orderStatus = crm().identifyAccountPage().ordersTabPanel().getOrderStatus(orderNumber);
			crm().topNavToolBar().end();
			//writeIntegrationOrdersToExcel("TC01_eBookPurchaseBN", timeStamp, orderNumber, ean, customerID,orderStatus);
		}
		else{
			logError("Order Number  not found in CRM");
		}
		if(!isShellMode()){
			crm().logOff();
			crm().closeApplication();
		}*/
		//	sap().bnOrderDataRefreshDialog().paymentCardsTab().verifyPaymentCardsTable("paymmentCard");
		//System.out.println(EnvironmentUtility.atlas().serverName().substring(7, 19));
	/*	String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,timeStamp,orderStatus = null;
		instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 19)+"_","VI", "4313081835209051", "9780553905656");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();

		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().logIn();
		
		
		
		//Process Orders in SAP
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nse16");
		sap().dataBrowserInitialScreenDialog().execute("EDIDC");
		sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);
		status = sap().dataBrowserTableEDIDCDialog().getStatus();
		//If order needs to be process
		if(status.contentEquals("64")){
			iDoc = sap().dataBrowserTableEDIDCDialog().getiDocNumber();
			sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nZRPIDOC");
			sap().createBatchJobToProcessiDocDialog().createBatchtoProcessiDoc(iDoc);
			sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/nzalo1");
		}
		else{
			sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nzalo1");
		}
		
		sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, null);
		sap().iDocReportingToolDialog().expandAllSalesOrder();
		
		sap().iDocReportingToolDialog().expandAllSalesOrder();
		String iDocForZ100 = sap().iDocReportingToolDialog().getIDocForZ100OrderAuthorized();
		System.out.println(iDocForZ100);
		sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(iDocForZ100);
		sap().statusMonitorDialog().expandiDocOutboundProcessing();
		if(	sap().statusMonitorDialog().isOutboundOrdersVisible()){
			sap().statusMonitorDialog().selectORDRSPndProcess();
			sap().informationDialog().clickOkButton();
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");
		}*/
		
/*		String orderStatus,orderNumber = null;
		orderNumber = "147580819";
		crm().startApplication();
		crm().signInDialog().signIn();
		crm().selectABusinessRolePage().clickZBNT1CSRLink();

	//	orderNumber = "147531674";

		crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
		crm().identifyAccountPage().accountConfirmPanel().confirm();
		crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderClosed");
		orderStatus = crm().identifyAccountPage().ordersTabPanel().getOrderStatus(orderNumber);
		*/
		/*String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\";
		String tcName = "TC01_eBookPurchaseBN";
		String dateNTime = "2014-06-10T12:37:14.61";
		String orderNumber = "147577968";
		String ean = "2940043922267";
		String customerID ="000E5DqUr8JYO810";
		String timeStamp =BNTimer.getCurrentDate("MMDDYYYY");
		String fileName = "Integration_"+timeStamp+".xls";
		System.out.println(timeStamp);
		createintegrationExcelFile(fileName);
		writeOrdersToExcel(spreadSheetPath+fileName, tcName, dateNTime, orderNumber, ean, customerID);*/
		
		//String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\Integration_Domestic.xls";
//		SpreadSheetUtil excelSheet = null;
//		excelSheet = new SpreadSheetUtil();
//		excelSheet.createExcelFileForInegrationTest("test555.xls");
	//	String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\test444.xls";
		/*String customerID = "000hAppUB8JYO810";
		String ean = "9780981925509";
		digitalLocker(customerID).verifyLockerItem("DigitalLocker",ean);*/
		
	//	sap().createAndProcessSubscriptioniDocsDialog().waitForStatus53();
//		String currentDate = "05/15/2014";
	//	System.out.println(contact);
		
		//poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
	//	sap().createAndProcessSubscriptioniDocsDialog().verifyContractsProcessed("aaa");;
	//	sap().documentFlowDialog().selectBNSubsRelAndDisplayDoc();
		// Window: Change BN Subscription 3100083968: Header Data
		// PageTab: BillingPlan
/*		text_fplabedat().setText("05/15/2014");
		text_fplabedat().setFocus();
		text_fplabedat().setCaretPosition(5);
		window_changeBNSubscription310().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
*/		//sap().displayBNOrdPhaseHeaderDataDialog().BillingPlanTab().updateStartDate("05/15/2014");
		
//		String orderNumber = "147515564";
//		dbService().datawareHouseDB().verifyOrderExistInOrderHeaderTable("DwDB_OrdExtInOrderHeader", orderNumber);
		
/*		sap().iDocReportingToolDialog().searchSalesOrder("147502905", null);
		sap().iDocReportingToolDialog().verifyIDocReportingToolTable("Minu");
*/		//	sap().iDocProcessingDialog().verifyProcessedIDOCGrid("gridview");

		/*String iDoc = sap().iDocReportingToolDialog().getIDocForZ150ShipmentClosed();
		
		System.out.println(iDoc);*/
	
		
/**/
		// Window: IDoc Reporting Tool
	/*	window_iDocReportingTool().maximize();
		window_iDocReportingTool().maximize();
		window_iDocReportingTool().maximize();
		tree_sapTableTreeControl1().selectItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().selectItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().selectItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().selectItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().ensureVisibleHorizontalItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().ensureVisibleHorizontalItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().ensureVisibleHorizontalItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().ensureVisibleHorizontalItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().doubleClickItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().doubleClickItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().doubleClickItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");
		tree_sapTableTreeControl1().doubleClickItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C6");*/
		
//		sap().iDocReportingToolDialog().verifyIDocReportingToolTable("zsds");
		
		
		/*// Window: IDoc Reporting Tool
		window_iDocReportingTool().maximize();
		tree_sapTableTreeControl1().ensureVisibleHorizontalItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C2");
		tree_sapTableTreeControl1().clickLink(atPath("Sales Order->Sales Order IDoc->ORDERS"), 
                                        "C2");
		
		// Window: IDoc Display: 0000000314909312
		button_back().press();
		
		// Window: IDoc Reporting Tool
		tree_sapTableTreeControl1().doubleClickItem(
                                        atPath("Sales Order->Sales Order IDoc->ORDRSP"), 
                                        "C1");
		tree_sapTableTreeControl1().clickLink(atPath("Sales Order->Sales Order IDoc->ORDRSP"), 
                                        "C2");
		
		
		// Window: IDoc Reporting Tool
		window_iDocReportingTool().maximize();
		window_iDocReportingTool().maximize();
		
		// Window: IDoc Display: 0000000314911949
		button_back2().press();
		button_back2().press();*/
		
	}

	/*private Connection createConnection() {
		// TODO Auto-generated method stub
		return null;
	}*/
}

