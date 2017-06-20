package framework.sapcontroller;

import org.apache.log4j.Logger;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPiDocProcessingDialogModel;
import framework.sapmodel.SAPiDocReportingToolDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 06, 2014
 */
public  class SAPiDocReportingToolDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPiDocProcessingDialogController.class);
	private static SAPiDocReportingToolDialogModel appModel = new SAPiDocReportingToolDialogModel();
	
	public SAPiDocReportingToolDialogController(){
		super(appModel.dialog());
	}
	public void expandSalesOrderIDoc(){
		logger.info("expandSalesOrderIDoc()");
		appModel.iDocReportingToolTree().expandNode(atPath("Sales Order->Sales Order IDoc"));	
	}
	public void expandOutboundDelIDoc(){
		logger.info("expandOutboundDelIDoc()");
		appModel.iDocReportingToolTree().expandNode(atPath("Sales Order->Outbound Del. IDoc"));	
	}

	public void expandInvoiceIDoc(){
		logger.info("expandInvoiceIDoc()");
		appModel.iDocReportingToolTree().expandNode(atPath("Sales Order->Invoice IDoc"));	
	}

	public void expandPOIDoc(){
		logger.info("expandPOIDoc()");
		appModel.iDocReportingToolTree().expandNode(atPath("Sales Order->PO IDoc"));	
	}

	public void expandInboundDelIDoc(){
		logger.info("expandInboundDelIDoc()");
		appModel.iDocReportingToolTree().expandNode(atPath("Sales Order->Inbound Del. IDoc"));	
	}
	public void expandAllSalesOrder(){
		logger.info("expandAllSalesOrder()");
		expandSalesOrderIDoc();
		expandOutboundDelIDoc();
		expandInvoiceIDoc();
		expandPOIDoc();
		expandInboundDelIDoc();
	}
	public void processZ100(){
		logger.info("processZ100()");
		String status,iDoc=null;
		status = getStatusForZ100OrderAuthorized();
		if(status.contains("30")){
			iDoc = getIDocForZ100OrderAuthorized();
			topLevelToolbar().enterCommand("/nbd87");
			AtlasScriptbase.getExecutingScript().sap().selectIDocDialog().searchIDoc(iDoc);
			AtlasScriptbase.getExecutingScript().sap().statusMonitorDialog().expandiDocOutboundProcessing();
			AtlasScriptbase.getExecutingScript().sap().statusMonitorDialog().selectORDRSPndProcess();
		}
		
	}
	public String getIDocForFESAPOrderCreated(){
		String iDoc = null;
		iDoc = getCellText("FE", 1);
		return iDoc;
	}
	public String getStatusForFESAPOrderCreated(){
		String iDoc = null;
		iDoc = getCellText("FE", 2);
		return iDoc;
	}
	public String getIDocForZ100OrderAuthorized(){
		String iDoc = null;
		iDoc = getCellText("Z100", 1);
		return iDoc;
	}
	public String getIDocForZ130OrderShopConfirm(){
		String iDoc = null;
		iDoc = getCellText("Z130", 1);
		return iDoc;
	}
	public String getIDocForZ135OrderChgBilAdrs(){
		String iDoc = null;
		iDoc = getCellText("Z135", 1);
		return iDoc;
	}
	public String getIDocForZRPRRepricingPaymMthd(){
		String iDoc = null;
		iDoc = getCellText("ZRPR", 1);
		return iDoc;
	}

	public String getIDocForZ112OrderAuthorized(){
		String iDoc = null;
		iDoc = getCellText("Z112", 1);
		return iDoc;
	}
	public String getIDocForZ200Refund(){
		String iDoc = null;
		iDoc = getCellText("Z200", 1);
		return iDoc;
	}

	public String getIDocForZ102OrderPending(){
		String iDoc = null;
		iDoc = getCellText("Z102", 1);
		return iDoc;
	}

	public String getIDocForZ105OrderFailed(){
		String iDoc = null;
		iDoc = getCellText("Z105", 1);
		return iDoc;
	}
	public String getIDocForZ104OrderDeclined(){
		String iDoc = null;
		iDoc = getCellText("Z104", 1);
		return iDoc;
	}

	public String getIDocForZ116OrderAuthorized(){
		String iDoc = null;
		iDoc = getCellText("Z116", 1);
		return iDoc;
	}
	@Deprecated
	/*
	 * ZR88 got changed to Z116 02/13/2015
	 */
	public String getIDocForZR88SubscCancelled(){
		String iDoc = null;
		iDoc = getCellText("ZR88", 1);
		return iDoc;
	}
	public String getIDocForZ116SubscCancelled(){
		String iDoc = null;
		iDoc = getCellText("Z116", 1);
		return iDoc;
	}

	public String getStatusForZ100OrderAuthorized(){
		String iDoc = null;
		iDoc = getCellText("Z100", 2);
		return iDoc;
	}
	public String getStatusForZ130OrderShopConfirm(){
		String iDoc = null;
		iDoc = getCellText("Z130", 2);
		return iDoc;
	}

	public String getStatusForZ105OrderFailed(){
		String iDoc = null;
		iDoc = getCellText("Z105", 2);
		return iDoc;
	}
	public String getStatusForZ112OrderAuthorized(){
		String iDoc = null;
		iDoc = getCellText("Z112", 2);
		return iDoc;
	}
	public String getStatusForZ116OrderAuthorized(){
		String iDoc = null;
		iDoc = getCellText("Z116", 2);
		return iDoc;
	}
	@Deprecated
	/*
	 * ZR88 got changed to Z116 02/13/2015
	 */
	public String getStatusForZR88SubscCancelled(){
		String iDoc = null;
		iDoc = getCellText("ZR88", 2);
		return iDoc;
	}
	public String getStatusForZ116SubscCancelled(){
		String iDoc = null;
		iDoc = getCellText("Z116", 2);
		return iDoc;
	}


	public String getIDocForZ108OrderClosed(){
		String iDoc = null;
		iDoc = getCellText("Z108", 1);
		return iDoc;
	}
	public String getIDocForZ600PurchaseOrder(){
		String iDoc = null;
		iDoc = getCellText("Z600", 1);
		return iDoc;
	}

	public String getStatusForZ108OrderClosed(){
		String iDoc = null;
		iDoc = getCellText("Z108", 2);
		return iDoc;
	}
	public String getStatusForZ600PurchaseOrder(){
		String iDoc = null;
		iDoc = getCellText("Z600", 2);
		return iDoc;
	}

	public String getIDocForZ150ShipmentClosed(){
		String iDoc = null;
		iDoc = getCellText("Z150", 1);
		return iDoc;
	}

	public String getStatusForZ150ShipmentClosed(){
		String iDoc = null;
		iDoc = getCellText("Z150", 2);
		return iDoc;
	}
	public String getCellText(String MessageCode,int columnNumber){
		String cellText= null;
		String message = "";
		String[][] tableData = appModel.iDocReportingToolTreeTable().getTreeTableData();
		for(int i=0; i<tableData.length; i++){
			message = tableData[i][5];
			if(message!=null && message.contains(MessageCode)){
				cellText = tableData[i][columnNumber];
				break;
			}
		}
		return cellText;
	}

/*	private String getCellText(String path,String columnTitle){
		String cellText,key,cName = null;
		key = appModel.iDocReportingToolTree().getNodeKeyByPath(atPath(path));
		cName = appModel.iDocReportingToolTree().getColumnNameFromTitle(columnTitle);
		
		cellText = appModel.iDocReportingToolTree().getItemText(key, cName);
		
		return cellText;
	}
*/	public boolean verifyIDocReportingToolTable(String vpName){

		logger.info("verifyIDocReportingToolTable("+vpName+")");
		boolean result = false;
		String[][] data = null;
		
		data = appModel.iDocReportingToolTreeTable().getTreeTableData();
		ITestData orderTable = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, orderTable).performTest();
		return result;
	}
	public void searchSalesOrder(String sapSalesOrder, String orderNumber,String key){
		logger.info("searchSalesOrder("+sapSalesOrder+"),("+orderNumber+"),("+key+")");
		if(sapSalesOrder!=null){
			appModel.sapSalesOrderTextBox().setText(sapSalesOrder);			
		}
		
		if(orderNumber!=null){
			appModel.feOrderNumberTextBox().setText(orderNumber);			
		}
		if(key!=null){
			appModel.salesDocTypeComboBox().setKey(key);//  Value(salesDocType);
		}
		appModel.executeButton().click();
	
		
	}
	public void searchSalesOrder(String orderNumber,String salesDocType){
		if(orderNumber!=null){
			appModel.feOrderNumberTextBox().setText(orderNumber);			
		}
		if(salesDocType!=null){
			appModel.salesDocTypeComboBox().setValue(salesDocType);
		}
		appModel.executeButton().click();

		
	}
}
