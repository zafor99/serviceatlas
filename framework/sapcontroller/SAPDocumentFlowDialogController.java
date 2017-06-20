package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.services.TestManager.LogInfoSpecifier;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.ft.vp.ITestDataTree;
import com.rational.test.ft.vp.VpUtil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.NodeTest;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDisplayDeviceOrderOverViewDialogModel;
import framework.sapmodel.SAPDocumentFlowDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 22, 2013
 */
public class SAPDocumentFlowDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDocumentFlowDialogController.class);
	private static SAPDocumentFlowDialogModel appModel = new SAPDocumentFlowDialogModel();
	
	public SAPDocumentFlowDialogController(){
		super(appModel.dialog());
	}
	
	public boolean verifyDocumentTable(String vpName){

		logger.info("verifyDocumentTable("+vpName+")");
		boolean result = false;
		String[][] data = null;
		
		data = appModel.documentTreeTable().getTreeTableData();
		ITestData orderTable = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, orderTable).performTest();
		AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
		return result;
	}
	
	public boolean verifyBNSubcriptionRel(String vpName){
		boolean result = false;
		logger.info("verifyBNSubcriptionRel("+vpName+")");
		String[] texts = appModel.documentTreeTable().getTreeNodeTextData();
		String[] keys = appModel.documentTreeTable().getTreeNodeKeyData();
		String[][] data = new String[1][7];
		int index=0;
		for(int i =0; i<keys.length;i++){
			if(appModel.documentTable().getNodeTextByKey(keys[i]).contains("BN Subscription Rel")){
				for(int j =0;j<7;j++){
					String[] rowData = appModel.documentTreeTable().getTableRowData(keys[i]);
					if(j==0){
						data[0][j]=texts[i];
					}
					else{
						data[0][j]=rowData[index];
						index++;
					}
				}
			}
		}
		ITestData orderRow = VpUtil.getTestData(data);
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, orderRow).performTest();
		AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
		return result;
	}
	@Deprecated
	public boolean verifyDocumentTableFfromHeaderData(String vpName){
		logger.info("verifyDocumentTableForInstantPurchase("+vpName+")");
		boolean result = false;
		
		String[][] data = appModel.documentTableFromHeaderData().getTableData();
		ITestData testData = VpUtil.getTestData(data);
		
		AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	
	public void selectPurchaseOrderAndDisplayDoc(){

		logger.info("selectPurchaseOrderAndDisplayDoc()");
		String nodes[] = appModel.documentTable().getSubNodesCol("          1");
		for(int i=0;i<nodes.length;i++){
			String nodesText = appModel.documentTable().getNodeTextByKey(nodes[i]);
			if(nodesText.contains("Purchase")){
				appModel.documentTable().selectNodeByKey(nodes[i]);
				break;
			}
			else{
				String nodes2[] = appModel.documentTable().getSubNodesCol("          2");
				for(int j=0;j<nodes2.length;j++){
					String nodesText2 = appModel.documentTable().getNodeTextByKey(nodes2[j]);
					if(nodesText2.contains("Purchase")){
						appModel.documentTable().selectNodeByKey(nodes2[j]);
						break;
					}
				}
				
			}
		}
		appModel.displauDocumentButton().click();
		delayFor(3);
	}
	public void selectDeviceOrderWhSaleAndDisplayDoc(){
		logger.info("selectDeviceOrderWhSaleAndDisplayDoc()");
		String rootNode = appModel.documentTable().getNodeTextByKey("          1");
		if(rootNode.contains("Device")){
			appModel.documentTable().selectNodeByKey("          1");
		}
		else{
			String nodes[] = appModel.documentTable().getSubNodesCol("          1");
			for(int i=0;i<nodes.length;i++){
				String nodesText = appModel.documentTable().getNodeTextByKey(nodes[i]);
				if(nodesText.contains("Device")){
					appModel.documentTable().selectNodeByKey(nodes[i]);
					break;
				}
			}
			
		}
		appModel.displauDocumentButton().click();
		/*appModel.deviceOrderWhSaleCell().click();
		appModel.displauDocumentButton().click();*/
		delayFor(3);
	}
	public void selectOutbDelDeviceAndDisplayDoc(){
		logger.info("selectPurchaseOrderAndDisplayDoc()");
		//appModel.outbDelDeviceCell().click();
		String nodes[] = appModel.documentTable().getSubNodesCol("          1");
		for(int i=0;i<nodes.length;i++){
			String nodesText = appModel.documentTable().getNodeTextByKey(nodes[i]);
			if(nodesText.contains("Outbd Deliv")||nodesText.contains("Outb Del")){
				appModel.documentTable().selectNodeByKey(nodes[i]);
				break;
			}
		}
		appModel.displauDocumentButton().click();
		delayFor(3);
	}
	public String getDeliveryNumber(){
		logger.info("getDeliveryNumber()");
		String deliveryNumber= null;
		String nodes[] = appModel.documentTable().getSubNodesCol("          1");
		for(int i=0;i<nodes.length;i++){
			String nodesText = appModel.documentTable().getNodeTextByKey(nodes[i]);
			if(nodesText.contains("Outbd Deliv")||nodesText.contains("Outb Del")){
				deliveryNumber = appModel.documentTable().getNodeTextByKey(nodes[i]);
				break;
			}
		}
		deliveryNumber = deliveryNumber.split(" ")[4];
		logger.info("Delivery Number : " + deliveryNumber);
		return deliveryNumber;
	}
	public void selectBNSubsRelAndDisplayDoc(){
		logger.info("selectBNSubsRelAndDisplayDoc()");
		String nodes[] = appModel.documentTable().getSubNodesCol("          1");
		for(int i=0;i<nodes.length;i++){
			String nodesText = appModel.documentTable().getNodeTextByKey(nodes[i]);
			if(nodesText.contains("BN Subscription Rel")){
				appModel.documentTable().selectNodeByKey(nodes[i]);
				break;
			}
		}
		appModel.displauDocumentButton().click();
		delayFor(3);
	}
	public String getPurchaseOrderNumberFromHeaderData(){
		logger.info("getPurchaseOrderNumberFromHeaderData()");
		String nodesText =null;
		String purchaseOrderNumber= "";
		String[] keys = appModel.documentTreeTable().getTreeNodeKeyData();
		for(int i =0;i<keys.length;i++){
			nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
			if(nodesText.contains("Outbd Deliv")||nodesText.contains("Purchase")){
				purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
				break;
			}
		}
		if(purchaseOrderNumber.length()>1){
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[2];	
		}
		else{
			purchaseOrderNumber = "";			
/*			delayFor(120);
			getExecutingScript().sap().documentFlowDialog().topLevelToolbar().navigateBack();
			getExecutingScript().sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
			for(int i =0;i<keys.length;i++){
				nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
				if(nodesText.contains("Outbd Deliv")||nodesText.contains("Purchase")){
					purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
					break;
				}
			}
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[2];
*/		}
			
		logger.info("purchaseOrder Number : " + purchaseOrderNumber);
		return purchaseOrderNumber;
	}
	public String getAccountingDocFromHeaderData(){
		logger.info("getAccountingDocFromHeaderData()");
		String nodesText =null;
		String purchaseOrderNumber= "";
		String[] keys = appModel.documentTreeTable().getTreeNodeKeyData();
		for(int i =0;i<keys.length;i++){
			nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
			if(nodesText.contains("Accounting")){
				purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
				break;
			}
		}
		if(purchaseOrderNumber.length()>1){
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[2];	
		}
		else{
			delayFor(120);
			getExecutingScript().sap().documentFlowDialog().topLevelToolbar().navigateBack();
			getExecutingScript().sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
			for(int i =0;i<keys.length;i++){
				nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
				if(nodesText.contains("Accounting")){
					purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
					break;
				}
			}
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[2];
		}
			
		logger.info("Accounting Doc Number : " + purchaseOrderNumber);
		return purchaseOrderNumber;
	}
	public String getInvoiceFromHeaderData(){
		logger.info("getInvoiceFromHeaderData()");
		String nodesText =null;
		String purchaseOrderNumber= "";
		String[] keys = appModel.documentTreeTable().getTreeNodeKeyData();
		for(int i =0;i<keys.length;i++){
			nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
			if(nodesText.contains("Inv")){
				purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
				break;
			}
		}
		if(purchaseOrderNumber.length()>1){
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[4];	
		}
		else{
			delayFor(120);
			getExecutingScript().sap().documentFlowDialog().topLevelToolbar().navigateBack();
			getExecutingScript().sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
			for(int i =0;i<keys.length;i++){
				nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
				if(nodesText.contains("Inv")){
					purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
					break;
				}
			}
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[4];
		}
			
		logger.info("Invoice Number : " + purchaseOrderNumber);
		return purchaseOrderNumber;
	}
	public String getBNSubscriptionRELNumberFromHeaderData(){
		logger.info("getBNSubscriptionRELNumberFromHeaderData()");
		String nodesText =null;
		String purchaseOrderNumber= "";
		String[] keys = appModel.documentTreeTable().getTreeNodeKeyData();
		for(int i =0;i<keys.length;i++){
			nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
			if(nodesText.contains("BN Subscription Rel")){
				purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
				break;
			}
		}
		if(purchaseOrderNumber.length()>1){
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[3];	
		}
		else{
			delayFor(120);
			getExecutingScript().sap().documentFlowDialog().topLevelToolbar().navigateBack();
			getExecutingScript().sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
			for(int i =0;i<keys.length;i++){
				nodesText = appModel.documentTable().getNodeTextByKey(keys[i]);
				if(nodesText.contains("Outbd Deliv")||nodesText.contains("Purchase")){
					purchaseOrderNumber = appModel.documentTable().getNodeTextByKey(keys[i]);
					break;
				}
			}
			purchaseOrderNumber = purchaseOrderNumber.split(" ")[2];
		}
			
		logger.info("purchaseOrder Number : " + purchaseOrderNumber);
		return purchaseOrderNumber;
	}

	public void waitForAccountingDoc(){
		String nodesText =null;
		int getTime = 0;
		BNTimer timer = new BNTimer();
		timer.start();
		String[] keys = appModel.documentTreeTable().getTreeNodeKeyData();
		do{
			getTime = timer.getElapsedTime();
			for(int i =0;i<keys.length;i++){
				nodesText =  appModel.documentTable().getNodeTextByKey(keys[i]);
				if(nodesText.contains("Accounting")){
					break;
				}
			}
			if(nodesText.contains("Accounting")){
				break;
			}

			else if(getTime>= 240){
				logger.info("Accounting doc is not created in  " + getTime );
				timer.stop();
				break;
			}
			else{
				AtlasScriptbase.getExecutingScript().sap().documentFlowDialog().topLevelToolbar().navigateBack();
				AtlasScriptbase.getExecutingScript().sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			}


		}while(nodesText.contains("Accounting"));


		
	}

}
