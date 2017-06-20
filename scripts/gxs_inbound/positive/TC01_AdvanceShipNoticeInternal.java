package scripts.gxs_inbound.positive;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.gxs_inbound.positive.TC01_AdvanceShipNoticeInternalHelper;
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
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC01_AdvanceShipNoticeInternal extends TC01_AdvanceShipNoticeInternalHelper
{
	/**
	 * Script Name   : <b>TC01_BlankReferenceID</b>
	 * Generated     : <b>May 29, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/29
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		GregorianCalendar calnder3 = new GregorianCalendar();

		atlas().advanceShipNotice().loadXmlFile("gxs_inbound\\Positive\\TC01_AdvanceShipNoticeInternalwithMultipleShipmentUnit.xml");
		atlas().advanceShipNotice().setRandomReferenceID();
		//TODO: DocumentID needs to be changed
		//ShipmentHeader\\DocumentID\\ID
		//TODO: Need to get the PO number from SAP
		//PurchaseOrderReference\\DocumentID\\ID
		
		
		//Same purchase order needs to be entered again on this node
		//PurchaseOrderReference\DocumentID\\ID
		atlas().advanceShipNotice().setHeader();
		atlas().advanceShipNotice().postInternal();
		atlas().advanceShipNotice().verifyResponse("TC01_AdvanceShipNoticeInternal", "201");
		
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();
		sap().userLoginDialog().logIn();
		
		String currentTime = null;
		currentTime ="";
		System.out.println(calnder3.getTime().toString());
		currentTime = calnder3.getTime().toString();

		String hr = currentTime.substring(11, 13);
		String min = currentTime.substring(14, 16);
		System.out.println("Hr : " + hr);
		System.out.println("min : " + min);
		//TODO: Need to Complete verification for SAP
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();

		sap().displaySalesOrderInitailScreenDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		
		sap().statusMonitorDialog().openDESADV();
		//sap().statusMonitorDialog().verifyStatusMonitorGrid("IDOC_Grid");
//    	sap().statusMonitorDialog().openDESADV();
    	

		
	}
}

