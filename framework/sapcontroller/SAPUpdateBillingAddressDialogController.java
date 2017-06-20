package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPUpdateBillingAddressDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 01, 2014
 */
public  class SAPUpdateBillingAddressDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPUpdateBillingAddressDialogController.class);
	private static SAPUpdateBillingAddressDialogModel appModel = new SAPUpdateBillingAddressDialogModel();
	
	public SAPUpdateBillingAddressDialogController(){
		super(appModel.dialog());
	}
	public void updateBillingAddress(String name,String company,String telephone,String street1,String street2,String street3,String city,String zip,String country,String region,String poBox,String action){
		logger.info("updateBillingAddress("+name+"),("+company+"),("+telephone+"),("+street1+"),("+street2+"),("+street3+"),("+city+"),("+zip+"),("+country+"),("+region+"),("+poBox+"),("+action+")");
		if(name!=null){
			appModel.nameTextBox().setText(name);
		}
		if(company!=null){
			appModel.companyTextBox().setText(company);
		}
		if(telephone!=null){
			appModel.telephoneTextBox().setText(telephone);
		}
		if(street1!=null){
			appModel.street1TextBox().setText(street1);
		}
		if(street2!=null){
			appModel.street2TextBox().setText(street2);
		}
		if(street3!=null){
			appModel.street3TextBox().setText(street3);
		}
		if(city!=null){
			appModel.cityTextBox().setText(city);
		}
		if(zip!=null){
			appModel.zipTextBox().setText(zip);
		}
		if(country!=null){
			appModel.countryTextBox().setText(country);
		}
		if(region!=null){
			appModel.regionTextBox().setText(region);
		}
		if(poBox!=null){
			appModel.poBoxCheckBox().select();
		}
		if(action!=null){
			if(action.contains("update")){
				appModel.updateButton().click();	
			}
			else if(action.contains("close")){
				appModel.closeButton().click();
			}
		}
		
	}
}
