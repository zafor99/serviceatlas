package framework;

import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;
import static com.bn.qa.webservice.restclient.BNRestful.given;
import interfaces.IAtlasController;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 02, 2013
 */
public class AtlasController extends ControllerScriptBase implements IAtlasController
{
	private static DeliveryConfirmationService deliveryConf = null;
	private static AdvanceShipmentNoticeService advanceShipNotice = null;
	private static InvoiceService invoiceService = null;
	private static PuchaseOrderService puchaseOrderService = null;
	private static PurchaseOrderAcknowledgementService purchaseOrderAcknowledgementService = null;
	private static WarehousePoackService warehousePoackService = null;
	public IRequestSpecification caliber(){

		return given();
	}

	public IRequestSpecification caliberInstance(){

		return getNewInstance();
	}
	
	public DeliveryConfirmationService deliveryConf(){
		if(deliveryConf==null){
			deliveryConf = new DeliveryConfirmationService();
		}
		return deliveryConf;
	}
	
	public AdvanceShipmentNoticeService advanceShipNotice(){
		if(advanceShipNotice==null){
			advanceShipNotice = new AdvanceShipmentNoticeService();
		}
		return advanceShipNotice;
	}
	public InvoiceService invoiceService(){
		if(invoiceService == null){
			invoiceService = new InvoiceService();
		}
		return invoiceService;
	}
	public PuchaseOrderService puchaseOrderService(){
		if(puchaseOrderService==null){
			puchaseOrderService = new PuchaseOrderService();
		}
		return puchaseOrderService;
	}
	
	public PurchaseOrderAcknowledgementService purchaseOrderAcknowledgementService(){
		if(purchaseOrderAcknowledgementService ==null){
			purchaseOrderAcknowledgementService = new PurchaseOrderAcknowledgementService();
		}
		return purchaseOrderAcknowledgementService;
	}
	public WarehousePoackService warehousePoackService(){
		if(warehousePoackService==null){
			warehousePoackService = new WarehousePoackService();
		}
		return warehousePoackService;
	}
	
}
