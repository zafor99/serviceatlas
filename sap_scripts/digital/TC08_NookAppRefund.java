package sap_scripts.digital;
import resources.sap_scripts.digital.TC08_NookAppRefundHelper;
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
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC08_NookAppRefund extends TC08_NookAppRefundHelper
{
	/**
	 * Script Name   : <b>TC08_NookAppRefund</b>
	 * Generated     : <b>Jan 24, 2014 3:54:02 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/01/24
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		String orderNnumber = "147485481";
		crm().startApplication();
		crm().signInDialog().signIn();
		crm().selectABusinessRolePage().clickZBNT1CSRLink();
		crm().identifyAccountPage().accountSearchPanel().search(orderNnumber, null, null, null, null, "search account");
		crm().identifyAccountPage().accountConfirmPanel().confirm();
		crm().identifyAccountPage().ordersTabPanel().selectOrderTableRowbyOrderNum(orderNnumber);
		crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("orderTable");
		crm().identifyAccountPage().ordersTabPanel().openSAPOrderNo(orderNnumber);
		crm().orderManagementOrderPage().topHeaderPanel().next();
		crm().orderManagementServReqPage().orderDetailsPanel().selectItemByItemNum("000001");
		crm().orderManagementServReqPage().createServiceRequestPanel().clickNew();
		crm().selectSalesServiceTransactionPage().selectFirstServiceRequest();
		crm().orderManagementServReqPage().serviceRequestOverviewPanel().updateCallReason("Downloadable Products", "NOOK Apps", "DRM Policy", null);
		crm().orderManagementServReqPage().createServiceRequestPanel().save();
		crm().orderManagementServReqPage().topHeaderPanel().clickNotificationLink();
		crm().orderManagementServReqPage().topHeaderPanel().verifyNotification("CustServreqCreated");
		crm().orderManagementServReqPage().topHeaderPanel().next();
		crm().orderManagementOptionsPage().availableOrderChangePanel().refund();
		crm().orderManagementServReqPage().topHeaderPanel().next();
		crm().orderManagementIssueRefundPage().issueRefundPanel().selectReasonCodeComboBox("Defective/Damaged)");
		crm().orderManagementIssueRefundPage().issueRefundPanel().updaterefundQty();
		
		crm().orderManagementIssueRefundSummaryPage().selectPaymentType("VISA");
		crm().orderManagementIssueRefundSummaryPage().update();
		crm().orderManagementServReqPage().topHeaderPanel().clickNotificationLink();
		crm().orderManagementServReqPage().topHeaderPanel().verifyNotification("refundCreated");
		String refundID=crm().orderManagementServReqPage().topHeaderPanel().getRefundID();
		System.out.println("refundID");
		crm().orderManagementServReqPage().topHeaderPanel().next();
		crm().orderManagementSummaryPage().verifySummaryTable("Summary");
		crm().orderManagementSummaryPage().topPanel().clickEnd();
		
		//crm().logOff();
		//crm().closeApplication();
	}
}

