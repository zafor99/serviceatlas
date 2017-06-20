package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDisplayOrdPhaseItemDataDialogModel;
import framework.sapmodel.SAPHitList1EntryDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 12, 2013
 */
public  class SAPDisplayOrdPhaseItemDataDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDisplayOrdPhaseItemDataDialogController.class);
	private static SAPDisplayOrdPhaseItemDataDialogModel appModel = new SAPDisplayOrdPhaseItemDataDialogModel();
	private static ShippingTab shippingTab = null;
	private static ScheduleLinesTab scheduleLinesTab = null;
	public SAPDisplayOrdPhaseItemDataDialogController(){
		super(appModel.dialog());
	
	}
	public ShippingTab shippingTab(){
		shippingTab = new ShippingTab();
		return shippingTab;
			
	}
	public ScheduleLinesTab scheduleLinesTab(){
		scheduleLinesTab = new ScheduleLinesTab();
		return scheduleLinesTab;
	}
	public void selectShippingTab(){
		logger.info("selectShippingTab()");
		appModel.shippingTab().tab().select();
		delayFor(2);
		
	}
	
	public class ShippingTab{
		public ShippingTab(){
			
		}
		public boolean verifySpecProcessing(String vpName){
			logger.info("verifyInfoRecLabel("+vpName+")");
			boolean result = false;
			
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName,appModel.shippingTab().specProcessingTextBox().getProperty("Text").toString()).performTest();
			
			return result;

		}

	}
	public class ScheduleLinesTab{
		public ScheduleLinesTab(){
			
		}
		public void select(){
			appModel.scheduleLinesTab().tab().click();
		}
		public String getFirstPurchaseRequisition(){
			logger.info("getFirstPurchaseRequisition()");
			String purchaseRequisition = null;
			TestObject [] tos = null;
			tos = appModel.scheduleLinesTab().quantitiesDatesTable().findAllByName("VBEP-BANFN", "GuiCTextField");
			if(tos.length>0){
				purchaseRequisition = tos[0].getProperty("Text").toString();
				System.out.println("Purchase Requisition :"+purchaseRequisition );
			}
			return purchaseRequisition;
		}
	}
	public void openFirstArticle(){
		logger.info("openFirstArticle()");
		appModel.firstArticleItem().doubleClick();
		delayFor(2);
	}
}
