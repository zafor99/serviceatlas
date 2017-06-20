package framework.sapcontroller;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.MouseModifiers;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import framework.AtlasScriptbase;
import framework.sapcontroller.SAPDisplayDeviceOrderOverViewDialogController.SalesTab;
import framework.sapmodel.SAPDisplayBNOrdPhaseHeaderDataDialogModel;
import framework.sapmodel.SAPModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 17, 2013
 */
public  class SAPDisplayBNOrdPhaseHeaderDataDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDisplayBNOrdPhaseHeaderDataDialogController.class);
	private static SAPDisplayBNOrdPhaseHeaderDataDialogModel appModel = new SAPDisplayBNOrdPhaseHeaderDataDialogModel();
	public SAPDisplayBNOrdPhaseHeaderDataDialogController (){
		super(appModel.dialog());
	}
	public PaymentCardsTab paymentCardsTab(){
		return new PaymentCardsTab();
	}
	public SalesTab salesTab(){
		return new SalesTab();
	}
	public ConditionsTab conditionTab(){
		return new ConditionsTab();
	}
	public PartnersTab partnersTab(){
		return new PartnersTab();
	}
	public BillingDocumentTab billingDocumentTab(){
		return new BillingDocumentTab();
	}
	public BillingPlanTab BillingPlanTab(){
		return new BillingPlanTab();
	}
	public void openDisplayDocumentFlowDialog(){
		logger.info("openDisplayDocumentFlowDialog()");
		appModel.toolBar().displayDocumentFlowButton().click();
		delayFor(3);
	}
	public class BillingPlanTab{
		public BillingPlanTab(){

		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			appModel.billingPlanTab().tab().click();
			delayFor(2);
		}
		public void updateStartDate(String date){
			logger.info("updateStartDate("+date+")");
			appModel.billingPlanTab().startDateTextBox().setText(date);
			appModel.dialog().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		
		}
	}
	public class BillingDocumentTab{
		public BillingDocumentTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			appModel.billingDocumentTab().tab().click();
			delayFor(2);
		}
		public void updateBillingDate(String date){
			logger.info("updateBillingDate("+date+")");
			appModel.billingDocumentTab().billingDateTextBox().setText(date);
			topLevelToolbar().save();
			
		}
		
	}
	public class PaymentCardsTab{
		
		public PaymentCardsTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			appModel.paymentCardsTab().tab().click();
			delayFor(2);
		}
		/*
		 * @param vpName Name of the verification point 
		 * Verify The Payment Cards Table
		 */
		public boolean  verifyPaymentCardsTable(String vpName){
			logger.info("verifyPaymentCardsTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			java.util.Hashtable testDataTypes = null;
			boolean isActive = (Boolean) appModel.paymentCardsTab().tab().getProperty("Changeable");
			String str; 
			if(isActive){
				testDataTypes = appModel.paymentCardsTab().paymentsCardTable().getTestDataTypes();
				// show all balances in hashtable 
				Set set = testDataTypes.keySet(); 
				Iterator itr = set.iterator(); 
				while(itr.hasNext()){
					str = (String) itr.next(); 
					System.out.println(str + ": " + 
							testDataTypes.get(str)); 
				}
				testData = appModel.paymentCardsTab().paymentsCardTable().getTestData("list");
			}
			else{
				appModel.paymentCardsTab().tab().click();
				testData = appModel.paymentCardsTab().paymentsCardTable().getTestData("list");	
			}
		
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
			return result;
		}
		
	}
	
	public class SalesTab{
		public SalesTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			final int MOUSE_LEFT;
			logger.info("select()");
			appModel.salesTab().tab().click(atPoint(20, 10));
			delayFor(2);
		}
		/*
		 * @param vpName Name of the verification point 
		 * Verify The Sales Data In the Sales Tab ( Top part of the Tab) 
		 */
		public boolean verifySalesData(String vpName){
			logger.info("verifySalesData("+vpName+")");
			boolean result = false;
			String[][] data = new String[12][2];
			
			data[0][0] = appModel.salesTab().orderTypeLabel().getText();
			data[0][1] = appModel.salesTab().orderTypeTextBox().getText() + " " + appModel.salesTab().bnOrdPhase2Label().getText();
			
			data[1][0] = appModel.salesTab().documentDateLabel().getText();
			data[1][1] = appModel.salesTab().documentDateTextBox().getText();

			data[2][0] = appModel.salesTab().saleAreaDataLabel().getText();
			data[2][1] = appModel.salesTab().saleAreaDataTextBox1().getText()+ "/"+ appModel.salesTab().saleAreaDataTextBox2().getText()+ "/"+ appModel.salesTab().saleAreaDataTextBox3().getText() + " "+ appModel.salesTab().retailerInfoLabel().getText();

			data[3][0] = appModel.salesTab().salesOfficeLabel().getText();
			data[3][1] = appModel.salesTab().salesOfficeTextBox().getText() + " " + appModel.salesTab().salesOfficeTextBox2().getText();
			
			data[4][0] = appModel.salesTab().createdByLabel().getText();
			data[4][1] = appModel.salesTab().createdByTextBox().getText();
			
			data[5][0] = appModel.salesTab().salesGroupLabel().getText();
			data[5][1] = appModel.salesTab().salesGroupTextBox().getText() + " " + appModel.salesTab().salesGroupTextBox2().getText();

			data[6][0] = appModel.salesTab().createdOnLabel().getText();
			data[6][1] = appModel.salesTab().createdOnTextBox().getText();

			data[7][0] = appModel.salesTab().versionLabel().getText();
			data[7][1] = appModel.salesTab().versionTextBox().getText();

			data[8][0] = appModel.salesTab().guaranteeLabel().getText();
			data[8][1] = appModel.salesTab().guaranteeTextBox().getText();

			data[9][0] = appModel.salesTab().guaranteeLabel().getText();
			data[9][1] = appModel.salesTab().guaranteeTextBox().getText();
			
			data[10][0] = appModel.salesTab().orderReasonLabel().getText();
			data[10][1] = appModel.salesTab().orderReasonComboBox().getProperty("Value").toString();
			
			data[11][0] = appModel.salesTab().deliveryTimeLabel().getText();
			data[11][1] = appModel.salesTab().deliveryTimeComboBox().getProperty("Value").toString();
			
			ITestData testData = VpUtil.getTestData(data);
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
			
			return result;
		}
		
		/*
		 * @param vpName Name of the verification point 
		 * Verify The Pricing and Statistics 
		 */

		public boolean verifyPricingAndStatisticsData(String vpName){
			logger.info("verifyPricingAndStatisticsData("+vpName+")");
			boolean result = false;
			String[][] data = new String[8][2];
			
			data[0][0] = appModel.salesTab().docCurrencyLabel().getText();
			data[0][1] = appModel.salesTab().docCurrencyTextBox1().getText() + " " + appModel.salesTab().docCurrencyTextBox2().getText();
			
			data[1][0] = appModel.salesTab().pricingDateLabel().getText();
			data[1][1] = appModel.salesTab().pricingDateTextBox().getText();

			data[2][0] = appModel.salesTab().pricProcedureLabel().getText();
			data[2][1] = appModel.salesTab().pricProcedureTextBox().getText()+ "/"+ appModel.salesTab().retailerOrdersLabel().getText();

			data[3][0] = appModel.salesTab().customerGroupLabel().getText();
			data[3][1] = appModel.salesTab().customerGroupComboBox().getProperty("Value").toString();
			
			data[4][0] = appModel.salesTab().priceListLabel().getText();
			data[4][1] = appModel.salesTab().priceListComboBox().getProperty("Value").toString();
			
			data[5][0] = appModel.salesTab().usageLabel().getText();
			data[5][1] = appModel.salesTab().usageComboBox().getProperty("Value").toString();

			data[6][0] = appModel.salesTab().priceGroupLabel().getText();
			data[6][1] = appModel.salesTab().priceGroupComboBox().getProperty("Value").toString();

			data[7][0] = appModel.salesTab().salesDistrictLabel().getText();
			data[7][1] = appModel.salesTab().salesDistrictTextBox().getText();

			ITestData testData = VpUtil.getTestData(data);
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			
			return result;
		}
	}
	public class ConditionsTab{
		public ConditionsTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			appModel.conditionsTab().tab().click(atPoint(20, 10));
			delayFor(2);
		}
		/*
		 * @param vpName Name of the verification point 
		 * Verify The Net Price and Tax  
		 */

		public boolean verifyNetAndTax(String vpName){
			logger.info("verifyPricingAndStatisticsData("+vpName+")");
			boolean result = false;
			String[][] data = new String[2][2];
			
			data[0][0] = appModel.conditionsTab().netLabel().getText();
			data[0][1] = appModel.conditionsTab().netTextBox().getText() + " " + appModel.conditionsTab().currencyTypeTextBox().getText();
			
			data[1][0] = appModel.conditionsTab().taxLabel().getText();
			data[1][1] = appModel.conditionsTab().taxTextBox().getText();

			ITestData testData = VpUtil.getTestData(data);
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			
			return result;
		}
	}
	public class PartnersTab{
		public PartnersTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			appModel.partnersTab().tab().click(atPoint(20, 10));
			delayFor(2);
		}
		/*
		 * @param vpName Name of the verification point 
		 * Verify The Partners Table
		 */
		public boolean  verifyPartnersTable(String vpName){
			logger.info("verifyPartnersTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			boolean isActive = (Boolean) appModel.partnersTab().tab().getProperty("Changeable");

			if(isActive){
				testData = appModel.partnersTab().partnersTable().getTestData("list");	
			}
			else{
				appModel.partnersTab().tab().click();
				testData = appModel.partnersTab().partnersTable().getTestData("list");	
			}
		
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
	}
}
