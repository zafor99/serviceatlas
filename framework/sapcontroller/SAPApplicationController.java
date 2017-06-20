package framework.sapcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.internal.selenesedriver.GetActiveElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPHitList1EntryDialogModel;
import framework.sapmodel.SAPApplicationModel;
import utils.SpreadSheetUtil;

public class SAPApplicationController extends SAPControllerBase{
	private static  Logger logger = Logger.getLogger(SAPApplicationController.class); 

	/*	private SAPLogon720PageController logon720Dialog = null;
	private SAPUserLoginDialogController userLoginDialog = null;
	private SAPEasyAccessDialogController sapEasyAccessDialog = null;
	private SAPLogOffDialogController logOffDialog = null;
	private SAPDisplaySalesOrderInitialScreenDialogController displaySalesOrderInitailScreenDialog = null;
	private SAPDisplayDeviceOrderOverViewDialogController displayDeviceOrderOverViewDialog = null;
	private SAPDocumentFlowDialogController documentFlowDialog = null;*/
	private SAPApplicationModel appModel = null;
	/*private SAPTopLevelToolbarController toolBar = null;
	private SAPSelectIDocDialogController selectIDocDialog = null;*/
	public SAPApplicationController(){
		//appModel = new SAPApplicationModel();
	}

	public SAPTopLevelToolbarController toolBar(){
		return new SAPTopLevelToolbarController();
		//return new SAPTopLevelToolbarController(appModel.getActiveWindow());
	}

	public void startApplication(){
		logger.info("startApplication");
		try {
			killProcess("saplogon.exe");
			delayFor(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startApp("saplogon");
		logon720Dialog().waitForExistence();

	}
	private  void killProcess(String processName) throws Exception{
		 try {
			 if(isProcessRunging(processName)){
			Runtime.getRuntime().exec("taskkill /F /IM "+processName);
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean isProcessRunging(String serviceName) throws Exception {
		 Process p = Runtime.getRuntime().exec("tasklist");
		 BufferedReader reader = new BufferedReader(new InputStreamReader(
		   p.getInputStream()));
		 String line;
		 while ((line = reader.readLine()) != null) {

		  //System.out.println(line);
		  if (line.contains(serviceName)) {
		   return true;
		  }
		 }
		 return false;
		}

	public void closeApplication(){
		boolean result = false;
		logger.info("closeApplication");
		logon720Dialog().close();

	}


	/*	public void writeOrderStatusToExcel(SpreadSheetUtil spreadSheet){

		boolean result = false;
		int rowIndex;

		SAPHitList1EntryDialogModel hitListEntry1DialogModel = new SAPHitList1EntryDialogModel();

		String[][] dataExp = new String[1][2];
		String[][] dataAct = new String[1][2];

		String ordNum = null;
		String ordStatus = null;

		rowIndex = 1;

		do{
			ordNum = "";
			ordStatus = "";

			spreadSheet.readRow(rowIndex);
			ordNum = String.valueOf(spreadSheet.getCellBigDecimalValue("Order No"));
			if(!ordNum.trim().contentEquals("")&&!ordNum.trim().contentEquals("0.0") ){
				getExecutingScript().sap().customerInteractionCenterDialog().clickOrderSearchFindButton();

				getExecutingScript().sap().restrictValueRangeDialog().standardSearch(ordNum);
				result = getExecutingScript().sap().hitList1EntryDialog().waitForExistence();
				if(result==true){
					hitListEntry1DialogModel.dialog().activate();
					ordStatus = hitListEntry1DialogModel.orderStatusLabel().getText();
					spreadSheet.setCellStringValue("Actual", hitListEntry1DialogModel.orderStatusLabel().getText());
					//hitListEntry1DialogModel.orderStatusLabel().unregister();
					hitListEntry1DialogModel.cancelButton().click();
				} else {
					ordStatus = null;
				}

				rowIndex++;
			}

		}while(!ordNum.trim().contentEquals("")&&!ordNum.trim().contentEquals("0.0"));

	}*/

	public SAPLogon720PageController logon720Dialog(){
		//return logon720Dialog;
		return new SAPLogon720PageController();
	}

	public SAPUserLoginDialogController userLoginDialog(){
		//return userLoginDialog;
		return new SAPUserLoginDialogController();
	}

	public SAPEasyAccessDialogController easyAccessDialog(){
		//return sapEasyAccessDialog;
		return new SAPEasyAccessDialogController();
	}

	public SAPCustomerInteractionCenterDialogController customerInteractionCenterDialog(){
		return new SAPCustomerInteractionCenterDialogController();
	}
	public SAPUpdateBillingAddressDialogController updateBillingAddress(){
		return new SAPUpdateBillingAddressDialogController();
	}
	public SAPAddPaymentDialogController addPaymentDialogController(){
		return new SAPAddPaymentDialogController();
	}
	public SAPRestrictValueRangeDialogController restrictValueRangeDialog(){
		return new SAPRestrictValueRangeDialogController();
	}

	public SAPHitList1EntryDialogController hitList1EntryDialog(){
		return new SAPHitList1EntryDialogController();
	}

	public SAPCICDisplayChangeOrderDialogController cicDisplayChangeOrderDialog(){
		return new SAPCICDisplayChangeOrderDialogController();
	}

	public SAPLogOffDialogController logOffDialog(){
		return new SAPLogOffDialogController();
	}

	public SAPDisplaySalesOrderInitialScreenDialogController displaySalesOrderInitailScreenDialog(){
		return new SAPDisplaySalesOrderInitialScreenDialogController();
	}
	public SAPDisplayContractInitialScreenDialogController displayContractInitialScreenDialog(){
		return new SAPDisplayContractInitialScreenDialogController();
	}

	public SAPDisplayContractInitialScreenDialogController changeContractInitialScreenDialog(){
		return new SAPDisplayContractInitialScreenDialogController();
	}

	public SAPDisplaySalesOrderInitialScreenDialogController changeSalesOrderInitailScreenDialog(){
		return new SAPDisplaySalesOrderInitialScreenDialogController();
	}


	public SAPDisplayDeviceOrderOverViewDialogController displayDeviceOrderOverViewDialog(){
		return new SAPDisplayDeviceOrderOverViewDialogController();
	}
	public SAPDisplayDeviceOrderOverViewDialogController displayBNOrderPhaseOverviewDialog(){
		return new SAPDisplayDeviceOrderOverViewDialogController();
	}

	public SAPDocumentFlowDialogController documentFlowDialog(){
		return new SAPDocumentFlowDialogController();
	}

	public SAPSelectIDocDialogController selectIDocDialog(){
		return new SAPSelectIDocDialogController();
	}
	public SAPOutbDelDevice3PLDialogController outbDelDevice3PLDialog(){
		return new SAPOutbDelDevice3PLDialogController();
	}
	//SAPCreateOutboundDeliveryDialogController
	public SAPCreateOutboundDeliveryDialogController createOutboundDeliveryDialog(){
		return new SAPCreateOutboundDeliveryDialogController();
	}
	public SAPiDocProcessingDialogController iDocProcessingDialog(){
		return new SAPiDocProcessingDialogController();
	}
	public SAPStatusMonitorDialogController statusMonitorDialog(){
		return new SAPStatusMonitorDialogController();
	}
	public SAPCreateBillingDocumentController createBillingDocumentDialog(){
		return new SAPCreateBillingDocumentController();
	}
	public SAPEDIOrderManagementMainMenuDialogController ediOrderManagementMainMenuDialog(){
		return new SAPEDIOrderManagementMainMenuDialogController();
	}
	public SAPEDIOrderProcessingDialogController ediOrderProcessingDialog(){
		return new SAPEDIOrderProcessingDialogController();
	}
	public SAPReleaseCustomerExpectedPriceController releaseCustomerExpectedPriceDialog(){
		return new SAPReleaseCustomerExpectedPriceController();
	}
	public SAPAssignSourceofSupplyDialogController assignSourceo0fSupplyDialog(){
		return new SAPAssignSourceofSupplyDialogController();
	}
	public SAPAssignSourceofSupplyDialogController assignSourceofSupplyDialog(){
		return new SAPAssignSourceofSupplyDialogController();
	}
	public SAPAutomaticCreationOfPurchaseDialogController automaticCreationOfPurchaseDialog(){
		return new SAPAutomaticCreationOfPurchaseDialogController();
	}
	public SAPDeliveryRequestPTNDialogController deliveryRequestPTNDialog(){
		return new SAPDeliveryRequestPTNDialogController();
	}
	public SAPDeliveryRequestPTNDialogController bnDropshipPODialog(){
		return new SAPDeliveryRequestPTNDialogController();
	}
	public SAPDeliveryRequestPTNDialogController appsDownloadPODialog(){
		return new SAPDeliveryRequestPTNDialogController();
	}
	public SAPInformationDialogController informationDialog(){
		return new SAPInformationDialogController();
	}
	public SAPSimpleJobSelectionDialogController simpleJobSelectionDialog(){
		return new SAPSimpleJobSelectionDialogController();
	}
	public SAPJobOverviewDialogController jobOverviewDialog(){
		return new SAPJobOverviewDialogController();
	}
	public SAPStartTimeDialogController startTimeDialog(){
		return new SAPStartTimeDialogController();
	}
	public SAPDisplayOrdPhaseItemDataDialogController displayOrdPhaseItemDataDialog(){
		return new SAPDisplayOrdPhaseItemDataDialogController();
	}
	public SAPBNOrderDataRefreshDialogController bnOrderDataRefreshDialog(){
		return new SAPBNOrderDataRefreshDialogController();
	}

	public SAPCancelSubscriptionDialogController cancelSubscriptionDialog(){
		return new SAPCancelSubscriptionDialogController();
	}
	public SAPDeviceInfoSerialNumberDialogController deviceInfoSerialNumberDialog(){
		return new SAPDeviceInfoSerialNumberDialogController();
	}
	public SAPInitiateReturnForRefundDialogController initiateReturnForRefundDialog(){
		return new SAPInitiateReturnForRefundDialogController();
	}
	public SAPBNEmailSystemDialogController bnEmailSystemDialog(){
		return new SAPBNEmailSystemDialogController();
	}
	public SAPDisplayBNOrdPhaseHeaderDataDialogController displayBNOrdPhaseHeaderDataDialog(){
		return new SAPDisplayBNOrdPhaseHeaderDataDialogController();
	}
	public SAPABAPEditorInitialScreenDialogController aBAPEditorInitialScreenDialog(){
		return new  SAPABAPEditorInitialScreenDialogController();
	}
	public SAPExecuteProgramDialogController executeProgramdialog(){
		return new SAPExecuteProgramDialogController();
	}
	public SAPCreateIncomingInvoiceDialogController createIncomingInvoiceDialog(){
		return new SAPCreateIncomingInvoiceDialogController();
	}
	public SAPCreateAndProcessSubscriptioniDocsDialogController createAndProcessSubscriptioniDocsDialog(){
		return new SAPCreateAndProcessSubscriptioniDocsDialogController();
	}
	public SAPDataBrowserInitialScreenDialogController dataBrowserInitialScreenDialog(){
		return new SAPDataBrowserInitialScreenDialogController();
	}
	public SAPDataBrowserTableSelectionScreenDialogController dataBrowserTableSelectionScreenDialog(){
		return new SAPDataBrowserTableSelectionScreenDialogController();
	}
	public SAPDataBrowserTableEDIDCDialogController dataBrowserTableEDIDCDialog(){
		return new SAPDataBrowserTableEDIDCDialogController();
	}
	public SAPiDocReportingToolDialogController iDocReportingToolDialog(){
		return new SAPiDocReportingToolDialogController();
	}
	public SAPFindVariantDialogController findVariantDialog(){
		return new SAPFindVariantDialogController();
	}
	public SAPAutomaticCreationOfPODialogController automaticCreationOfPODialog(){
		return new SAPAutomaticCreationOfPODialogController();
	}
	public SAPCallingiDocTestToolDialogController callingiDocTestToolDialog(){
		return new SAPCallingiDocTestToolDialogController();
	}
	public SAPTestToolForiDocProcessingDialogController testTooliDocProcessingDialog(){
		return new SAPTestToolForiDocProcessingDialogController();
	}
	public SAPTestInboundIDocUsPartProfDialogController testInboundIDocUsPartProfDialog(){
		return new SAPTestInboundIDocUsPartProfDialogController();
	}
	
	public SAPCreateBatchJobToProcessiDocDialogController createBatchJobToProcessiDocDialog(){
		return new SAPCreateBatchJobToProcessiDocDialogController();
	}
	public SAPChangeDataRecordDialogController changeDataRecordDialog(){
		return new SAPChangeDataRecordDialogController();
	}
	public void writeiDocToExcel(SpreadSheetUtil spreadSheet){
		int rowIndex;
		String ordNum = null;
		String iDoc = null;
		String iDocStatus = null;
		rowIndex = 1;	
		for(int i=0;i<20;i++){
			spreadSheet.readRow(rowIndex);
			ordNum = spreadSheet.getCellStringValue("Order Number");
			if(ordNum.length()>1){
				getExecutingScript().sap().dataBrowserTableSelectionScreenDialog().execute(ordNum);
				iDoc=getExecutingScript().sap().dataBrowserTableEDIDCDialog().getiDocNumber();
				iDocStatus=getExecutingScript().sap().dataBrowserTableEDIDCDialog().getStatus();
				spreadSheet.setCellStringValue("iDoc Number", iDoc);
				spreadSheet.setCellStringValue("iDoc Status", iDocStatus);
				System.out.println("Order No : "+ordNum + " iDoc No :" + iDoc+ " iDoc Status" + iDocStatus);
				getExecutingScript().sap().dataBrowserTableSelectionScreenDialog().topLevelToolbar().navigateBack();
				rowIndex++;
			}
		}

	}
	public void writeiDocProcessStatusToExcel(SpreadSheetUtil spreadSheet){
		int rowIndex;
		String ordNum = null;
		String iDoc = null;
		String iDocStatus = null;
		rowIndex = 1;	
		for(int i=0;i<20;i++){
			spreadSheet.readRow(rowIndex);
			iDoc = spreadSheet.getCellStringValue("iDoc Number");
			iDocStatus = spreadSheet.getCellStringValue("iDoc Status");
			if(iDoc.length()>1){
				if(iDocStatus.contains("64")){
					getExecutingScript().sap().createBatchJobToProcessiDocDialog().createBatchtoProcessiDoc(iDoc);
					iDocStatus =getExecutingScript().sap().createBatchJobToProcessiDocDialog().getiDocStatus(); 
					if(iDocStatus.contains("53")){
						spreadSheet.setCellStringValue("Order Status", " Order found in SAP");
					}
					else if(iDocStatus.contains("51")){
						spreadSheet.setCellStringValue("Order Status", " Order Failed in SAP");
					}
					getExecutingScript().sap().createBatchJobToProcessiDocDialog().topLevelToolbar().navigateBack();
					System.out.println("Order No : "+ordNum + " iDoc No :" + iDoc+  " new iDoc Status" + iDocStatus);
					spreadSheet.setCellStringValue("iDoc Status after Processing", iDocStatus);
					spreadSheet.setCellStringValue("Order Status", " Order found in SAP");
					getExecutingScript().sap().dataBrowserTableSelectionScreenDialog().topLevelToolbar().navigateBack();
					rowIndex++;
				}
				else if(iDocStatus.contains("53")){
					spreadSheet.setCellStringValue("Order Status", " Order found in SAP");
				}
				else if(iDocStatus.contains("51")){
					spreadSheet.setCellStringValue("Order Status", " Order Failed in SAP");
				}
				
			}
		}

	}

	public void writeOrderStatusToExcel(SpreadSheetUtil spreadSheet){

		boolean result = false;
		int rowIndex;
		String ordNum = null;
		String iDocStatus = null;
		String category = null;
		rowIndex = 1;	
		for(int i=0;i<20;i++){

			spreadSheet.readRow(rowIndex);
			ordNum = spreadSheet.getCellStringValue("Order Number");
			iDocStatus= spreadSheet.getCellStringValue("iDoc Status after Processing");
			if(ordNum.length()>1){
				if(iDocStatus.contains("53")){
					if(category.contains("single")){
						getExecutingScript().sap().displaySalesOrderInitailScreenDialog().search(ordNum,false);
						if(getExecutingScript().sap().hitList1EntryDialog().waitForExistence(10)){
							spreadSheet.setCellStringValue("Order Status", "Order reached in SAP");
							getExecutingScript().sap().hitList1EntryDialog().close();
						}
						else{
							spreadSheet.setCellStringValue("Order Status", " Order not found in SAP");
						}	
					}
					else{
						getExecutingScript().sap().displaySalesOrderInitailScreenDialog().topLevelToolbar().enterCommand("/nva43");
						getExecutingScript().sap().displayContractInitialScreenDialog().search(ordNum);
						if(getExecutingScript().sap().hitList1EntryDialog().waitForExistence(10)){
							spreadSheet.setCellStringValue("Order Status", "Order reached in SAP");
							getExecutingScript().sap().hitList1EntryDialog().close();
						}
						else{
							spreadSheet.setCellStringValue("Order Status", " Order not found in SAP");
						}	
						getExecutingScript().sap().displayContractInitialScreenDialog().topLevelToolbar().enterCommand("/nva03");
					}
				}
			}
			else{
				spreadSheet.setCellStringValue("Order Status", " Order not found in SAP");
			}
			rowIndex++;

		}


		/*SAPHitList1EntryDialogModel hitListEntry1DialogModel = new SAPHitList1EntryDialogModel();

		String[][] dataExp = new String[1][2];
		String[][] dataAct = new String[1][2];


		do{
			ordNum = "";
			ordStatus = "";

			spreadSheet.readRow(rowIndex);
			ordNum = String.valueOf(spreadSheet.getCellBigDecimalValue("Order No"));
			if(!ordNum.trim().contentEquals("")&&!ordNum.trim().contentEquals("0.0") ){
				getExecutingScript().sap().customerInteractionCenterDialog().clickOrderSearchFindButton();

				getExecutingScript().sap().restrictValueRangeDialog().searchWebOrder(ordNum);
				result = getExecutingScript().sap().hitList1EntryDialog().waitForExistence();
				if(result==true){
					hitListEntry1DialogModel.dialog().activate();
					ordStatus = hitListEntry1DialogModel.orderStatusLabel().getText();
					spreadSheet.setCellStringValue("Actual", hitListEntry1DialogModel.orderStatusLabel().getText());
					//hitListEntry1DialogModel.orderStatusLabel().unregister();
					hitListEntry1DialogModel.cancelButton().click();
				} else {
					ordStatus = null;
				}

				rowIndex++;
			}

		}while(!ordNum.trim().contentEquals("")&&!ordNum.trim().contentEquals("0.0"));
		 */
	}
}
