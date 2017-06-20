package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiComboBoxTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPDisplayDeviceOrderOverViewDialogModel.SalesTab;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 17, 2013
 */
public class SAPDisplayBNOrdPhaseHeaderDataDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel=null;
	public SAPDisplayBNOrdPhaseHeaderDataDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		
		SAPTopLevelTestObject dialog=null;
		try {
			TestObject[] tos = null;
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Display BN.*: Header Data|Change BN.*Header Data")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {

		}
		return dialog;
	}
	public DisplayBNOrderPhaseHeaderDataToolBar toolBar(){
		return new DisplayBNOrderPhaseHeaderDataToolBar();
	}
	public PaymentCardsTab paymentCardsTab(){
		return new PaymentCardsTab();
	}
	public BillingPlanTab billingPlanTab(){
		return new BillingPlanTab();
	}
	public BillingDocumentTab billingDocumentTab(){
		return new BillingDocumentTab();
	}
	public SalesTab salesTab(){
		return new SalesTab();
	}
	public ConditionsTab conditionsTab(){
		return new ConditionsTab();
	}
	public PartnersTab partnersTab(){
		return new PartnersTab();
	}
	
	public class DisplayBNOrderPhaseHeaderDataToolBar{
		
		private SAPGuiToolbarTestObject toolbar = null;
		
		public DisplayBNOrderPhaseHeaderDataToolBar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		
		public SAPGuiToggleTestObject displayDocumentFlowButton()
		{
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[5]")))[0];
		}
	}
	public class BillingPlanTab{
		private SAPGuiTabTestObject tab = null;
		public BillingPlanTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/tabsTAXI_TABSTRIP_HEAD|/tabsTAXI_TABSTRIP")),atChild("Id",xRegex("/tabpT.*06"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTextTestObject startDateTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV60F:4201/ctxtFPLA-BEDAT"))))[0];
		}
		
	}
	public class BillingDocumentTab{
		private SAPGuiTabTestObject tab = null;
		public BillingDocumentTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/tabsTAXI_TABSTRIP_HEAD|/tabsTAXI_TABSTRIP")),atChild("Id",xRegex("/tabpT.*04"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTextTestObject billingDateTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4303/ctxtVBKD-FKDAT"))))[0];
		}
	}
	public class SalesTab{
		private SAPGuiTabTestObject tab = null;
		public SalesTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/tabsTAXI_TABSTRIP_HEAD|/tabsTAXI_TABSTRIP")),atChild("Id",xRegex("/tabpT.*01"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTextTestObject orderTypeLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id","/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtRV45A-TXT_AUART")))[0];
		}
		public SAPGuiTextTestObject orderTypeTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-AUART"))))[0];
		}
		public SAPGuiTextTestObject bnOrdPhase2Label(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtTVAKT-BEZEI"))))[0];
		}
		public SAPGuiTextTestObject documentDateLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-AUDAT"))))[0];
		}
		public SAPGuiTextTestObject documentDateTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-AUDAT"))))[0];
		}
		public SAPGuiTextTestObject saleAreaDataLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblRV45A-TXT_VTRBER"))))[0];
		}
		public SAPGuiTextTestObject  saleAreaDataTextBox1(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-VKORG"))))[0];
		}
		public SAPGuiTextTestObject  saleAreaDataTextBox2(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-VTWEG"))))[0];
		}
		public SAPGuiTextTestObject  saleAreaDataTextBox3(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-SPART"))))[0];
		}
		public SAPGuiTextTestObject  retailerInfoLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtRV45A-TXT_VTRBER"))))[0];
		}
		public SAPGuiTextTestObject  salesOfficeLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-VKBUR"))))[0];
		}
		public SAPGuiTextTestObject  salesOfficeTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-VKBUR"))))[0];		
		}
		public SAPGuiTextTestObject  salesOfficeTextBox2(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtTVKBT-BEZEI"))))[0];		
		}
		public SAPGuiTextTestObject  createdByLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-ERNAM"))))[0];		
		}
		public SAPGuiTextTestObject  createdByTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtVBAK-ERNAM"))))[0];		
		}
		public SAPGuiTextTestObject  salesGroupLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-VKGRP"))))[0];		
		}
		public SAPGuiTextTestObject  salesGroupTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-VKGRP"))))[0];		
		}
		public SAPGuiTextTestObject  salesGroupTextBox2(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtTVGRT-BEZEI"))))[0];		
		}
		public SAPGuiTextTestObject  createdOnLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-ERDAT"))))[0];		
		}
		public SAPGuiTextTestObject  createdOnTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-ERDAT"))))[0];		
		}
		public SAPGuiTextTestObject  versionLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-VSNMR_V"))))[0];		
		}
		public SAPGuiTextTestObject  versionTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtVBAK-VSNMR_V"))))[0];		
		}
		public SAPGuiTextTestObject  guaranteeLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-GWLDT"))))[0];		
		}
		public SAPGuiTextTestObject  guaranteeTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-GWLDT"))))[0];		
		}
		public SAPGuiTextTestObject  orderReasonLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-AUGRU"))))[0];		
		}
		public SAPGuiComboBoxTestObject  orderReasonComboBox(){
			return (SAPGuiComboBoxTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/cmbVBAK-AUGRU"))))[0];		
		}
		public SAPGuiTextTestObject  deliveryTimeLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBKD-DELCO"))))[0];		
		}
		public SAPGuiComboBoxTestObject  deliveryTimeComboBox(){
			return (SAPGuiComboBoxTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/cmbVBKD-DELCO"))))[0];		
		}
		/*
		 * Pricing and Statistics Table
		 */
		public SAPGuiTextTestObject  pricingAndStatisticsLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/boxBLOCK_PREISFINDUNG"))))[0];		
		}
		public SAPGuiTextTestObject  docCurrencyLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-WAERK"))))[0];		
		}
		public SAPGuiTextTestObject  docCurrencyTextBox1(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-WAERK"))))[0];		
		}
		public SAPGuiTextTestObject  docCurrencyTextBox2(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBKD-KURSK"))))[0];		
		}
		public SAPGuiToggleTestObject refreshButton(){
			return (SAPGuiToggleTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/btnBT_WNEU"))))[0];
		}
		public SAPGuiTextTestObject  pricingDateLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBKD-PRSDT"))))[0];		
		}
		public SAPGuiTextTestObject  pricingDateTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBKD-PRSDT"))))[0];		
		}
		public SAPGuiTextTestObject  pricProcedureLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-KALSM"))))[0];		
		}
		public SAPGuiTextTestObject  pricProcedureTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBAK-KALSM"))))[0];		
		}
		public SAPGuiTextTestObject  retailerOrdersLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/txtT683U-VTEXT"))))[0];
		}
		public SAPGuiTextTestObject  customerGroupLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBKD-KDGRP"))))[0];		
		}
		public SAPGuiComboBoxTestObject  customerGroupComboBox(){
			return (SAPGuiComboBoxTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/cmbVBKD-KDGRP"))))[0];		
		}
		public SAPGuiTextTestObject  priceListLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBKD-PLTYP"))))[0];		
		}
		public SAPGuiComboBoxTestObject  priceListComboBox(){
			return (SAPGuiComboBoxTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/cmbVBKD-PLTYP"))))[0];		
		}
		public SAPGuiTextTestObject  usageLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBAK-ABRVW"))))[0];		
		}
		public SAPGuiComboBoxTestObject  usageComboBox(){
			return (SAPGuiComboBoxTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/cmbVBAK-ABRVW"))))[0];		
		}
		public SAPGuiTextTestObject  priceGroupLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBKD-KONDA"))))[0];		
		}
		public SAPGuiComboBoxTestObject  priceGroupComboBox(){
			return (SAPGuiComboBoxTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/cmbVBKD-KONDA"))))[0];		
		}
		public SAPGuiTextTestObject  salesDistrictLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/lblVBKD-BZIRK"))))[0];		
		}
		public SAPGuiTextTestObject  salesDistrictTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4301/ctxtVBKD-BZIRK"))))[0];		
		}
	}
	public class PaymentCardsTab{
		private SAPGuiTabTestObject tab = null;
		public PaymentCardsTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/tabsTAXI_TABSTRIP_HEAD|/tabsTAXI_TABSTRIP")),atChild("Id",xRegex("/tabpT.*04"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTableControlTestObject paymentsCardTable(){
		//	return (SAPGuiTableControlTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLV60F:4204/tblSAPLV60FTCTRL_ZAHLUNGSKARTEN")))[0];
			return (SAPGuiTableControlTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV60F:4204.*tblSAPLV60FTCTRL_ZAHLUNGSKARTEN"))))[0];
		}
	}
	public class ConditionsTab{
		private SAPGuiTabTestObject tab = null;

		public ConditionsTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/tabsTAXI_TABSTRIP_HEAD|/tabsTAXI_TABSTRIP")),atChild("Id",xRegex("/tabpT.*06"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTextTestObject  netLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV69A:6201/lblKOMP-NETWR"))))[0];		
		}
		public SAPGuiTextTestObject  netTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV69A:6201/txtKOMP-NETWR"))))[0];		
		}
		public SAPGuiTextTestObject  currencyTypeTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV69A:6201/ctxtKOMK-WAERK"))))[0];		
		}
		public SAPGuiTextTestObject  taxLabel(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV69A:6201/lblKOMP-MWSBP"))))[0];		
		}
		public SAPGuiTextTestObject  taxTextBox(){
			return (SAPGuiTextTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLV69A:6201/txtKOMP-MWSBP"))))[0];		
		}
	}
	public class PartnersTab{
		private SAPGuiTabTestObject tab = null;

		public PartnersTab(){
			if(tab==null){
				tab = (SAPGuiTabTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id",xRegex("/tabsTAXI_TABSTRIP_HEAD|/tabsTAXI_TABSTRIP")),atChild("Id",xRegex("/tabpT.*08"))))[0];
			}
		}
		public SAPGuiTabTestObject tab(){
			return tab;
		}
		public SAPGuiTableControlTestObject partnersTable(){
			//	return (SAPGuiTableControlTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLV60F:4204/tblSAPLV60FTCTRL_ZAHLUNGSKARTEN")))[0];
				return (SAPGuiTableControlTestObject)tab().find(atList(atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPMV45A:4352/subSUBSCREEN_PARTNER_OVERVIEW:SAPLV09C:1000/tblSAPLV09CGV_TC_PARTNER_OVERVIEW"))))[0];
			}
	}
}
