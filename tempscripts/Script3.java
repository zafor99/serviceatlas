package tempscripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.xerces.impl.dv.util.Base64;

//import javax.jms.Message;

import resources.tempscripts.Script3Helper;
import utils.EnvironmentUtility;
import utils.XMLUtil;
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
import com.bn.service.atlas.message.generation.JmsProxy;
import com.bn.service.atlas.message.generation.JmsProxyUsingActiveMq;
import com.bn.service.atlas.message.generation.MessageType;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

import framework.DBValidationService;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class Script3 extends Script3Helper
{
	/**
	 * Script Name   : <b>Script3</b>
	 * Generated     : <b>Dec 11, 2013 2:27:24 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/12/11
	 * @author zsadeque
	 * @throws ClassNotFoundException 
	 */
	public void testMain(Object[] args) throws ClassNotFoundException 
	{
		
		//System.out.println(EnvironmentUtility.caliber().serverName().substring(0, 20)+"8161/admin/xml/queues.jsp");
		
		System.out.println(activeMQService().getRoutePendingMessageCount("saporders"));
		
		/*try {
			URL url = new URL("http://injsvcjapp01:8161/admin/xml/queues.jsp");
			URLConnection uc = url.openConnection();
			String userpass = "testadmin" + ":" + "mq&NspEe";
			String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
			uc.setRequestProperty ("Authorization", basicAuth);
			InputStream in = uc.getInputStream();
			BufferedReader buff = null;
			String line, responseText = "";
			buff = new BufferedReader(new InputStreamReader(in));
			String xml;
			
			while ((line = buff.readLine()) != null) {
				//System.out.println(line);
	
				//if(!line.trim().contentEquals("")&&!line.contains("atom")&&!line.contains("rss")){
					responseText = responseText + line;
				//}
				
				
			}
			System.out.println(responseText);
			XMLUtil xmlUtilyy = new XMLUtil();
			
			xmlUtilyy.readFromString(responseText);
			System.out.println(xmlUtilyy.getXMLString());
			
			int count = xmlUtilyy.getSizeByRouteName("batches");
			System.out.println(count);
			//String sapOrdersCount = xmlUtil.getNodeValueByXPath("//queues/queue[@name='saporders']");
//			String sapOrdersCount = xmlUtil.getNodeValueByXPath("/queues/queue[1]/stats/@size");
			//System.out.println(xmlUtil.getXMLString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*		sap().easyAccessDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().closeApplication();*/

		//sap().createBatchJobToProcessiDocDialog().createBatchtoProcessiDoc("0000000317488430");
		//		sap().createBatchJobToProcessiDocDialog().getiDocStatus();


		//	digitalLocker("000SPRUUU0JYO810").verifyLockerItem("000SPRUUU0JYO810","aaa");
		//sap().appsDownloadPODialog().purchaseOrderHistoryTab().select();
		//sap().displayBNOrderPhaseOverviewDialog().clickScheduleLinesForItem();
		//System.out.println(sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().getFirstPurchaseRequisition());
		//		sap().findVariantDialog().findVariant("DIGITAL_QA");
		//		sap().automaticCreationOfPODialog().createPOfromPurchaseRequisition("5013421449");

		/*sap().displayBNOrderPhaseOverviewDialog().salesTab().openFirstMaterial();
		sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().select();
		System.out.println(sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().getFirstPurchaseRequisition());*/
		/*		DownloadFile downloadFile = new DownloadFile();
		downloadFile.downLoad("sapTransactionIdMonitor.log", "/var/log/jetty/");*/


		/*DigitalLockerService locker = new DigitalLockerService("000oBBJUNYJYO810");
		 locker.showLockerItems();*/
		// DownloadFile.downLoad("20141102.xml");
		/*		String temp="3a.  RQ2  -  ECC  SI2";
		String temp2 = temp.split(" ")[2];
		System.out.println(":"+temp2+":");
		sap().statusMonitorDialog().expandiDocInboundProcessing();*/
		//		dbService().pfsDB().verifyStatusInTRXHEADER("CloseStatus", "8102941090","C");

		/*		if(	sap().statusMonitorDialog().isInboundDesadvVisible()){
			sap().statusMonitorDialog().selectDESADVAndProcess();
			sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Status_53");
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
		}
		else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");
		}*/

	}
}

