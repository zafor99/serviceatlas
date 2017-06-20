package framework;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 12, 2013
 */
public  class InvoiceService extends BaseService
{
	private String fileName = "AdvanceShipNotice.xml";
	private String internalPath = null;
	private String externalPath = null;
	
	public InvoiceService(){
		internalPath = "/atlas-web/Logistics/services/rs/Invoice";
		externalPath = "/Logistics/services/rs/Invoice?client_id=gxs";
		loadXmlFile(fileName);
	}
	public void postInternal(){
		post(envUtil.eaiInternal().serverName()+ internalPath);
		
	}
	public void postExternal(){
		post(envUtil.eaiExternal().serverName() + externalPath);
	}
	public void setRandomInvoiceHeaderAlternateDocumentID(){
		xmlUtil.setNodeValueByXPath("//DataArea/Invoice/InvoiceHeader/AlternateDocumentID/ID", generateRandom(11));
	}
	public void setRandomInvoiceHeaderDocumentID(){
		xmlUtil.setNodeValueByXPath("//DataArea/Invoice/InvoiceHeader/DocumentID/ID", generateRandom(6));
	}

	public void setInvoiceHeaderDocumentReferenceDocumentID(String shipmentID){
		xmlUtil.setNodeValueByXPath("//DataArea/Invoice/InvoiceHeader/DocumentReference/DocumentID/ID", shipmentID);
	}
	public void setInvoiceHeaderPONumber(String poNumber){
		xmlUtil.setNodeValueByXPath("//DataArea/Invoice/InvoiceHeader/PurchaseOrderReference/DocumentID/ID", poNumber);
	}
	public void setInvoiceLinePONumber(String poNumber){
		xmlUtil.setNodeValueByXPath("//DataArea/Invoice/InvoiceLine/PurchaseOrderReference/DocumentID/ID", poNumber);
	}
}
