package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.EnvironmentUtility;

import framework.sapmodel.SAPLogon720PageModel;

	public class SAPLogon720PageController extends SAPControllerBase {

	private static Logger logger = Logger.getLogger(SAPLogon720PageController.class);
	private SAPLogon720PageModel model = null;
	private EnvironmentUtility envUtil = new EnvironmentUtility();
	public SAPLogon720PageController(){
		model = new SAPLogon720PageModel();
	}
	
	public boolean waitForExistence(){
		boolean result = false;
		model.dialog().waitForExistence(60, 5);
		if(model.dialog().exists()){
			result = true;
		}
		return result;
	}
	
	public void close(){
		model.dialog().close();
		getExecutingScript().delayFor(3);
	}
	
	public void selectSystemToLogin(){
		logger.info("selectSystemToLogin");
		model.dialog().activate();
		model.sytemListView().doubleClick(atCell(atRow(atText(EnvironmentUtility.sap().serverName())), 
                atColumn(atText("Name"))));
		getExecutingScript().delayFor(5);
		//getExecutingScript().sap().userLoginDialog().waitForExistence();
		
	}
	
	public void selectSystemToLogin(String item){
		logger.info("selectSystemToLogin("+item+")");
		model.dialog().activate();
		model.sytemListView().doubleClick(atCell(atRow(atText(item)), 
                atColumn(atText("Name"))));
		getExecutingScript().delayFor(5);
		//getExecutingScript().sap().userLoginDialog().waitForExistence();
		
	}
	
	
	
}
