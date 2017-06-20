package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPCreateOutboundDeliveryDialogModel;
import framework.sapmodel.SAPOutbDelDevice3PLDialogModel;

public class SAPCreateOutboundDeliveryDialogController  extends SAPDialogController{
	private static Logger logger = Logger.getLogger(SAPCreateOutboundDeliveryDialogController.class);
	private static SAPCreateOutboundDeliveryDialogModel appModel = new SAPCreateOutboundDeliveryDialogModel();
	private String deliveryNumber = "";
	public SAPCreateOutboundDeliveryDialogController(){
		super(appModel.dialog());
	}
	private boolean isNumeric(String number){
		logger.info("isNumeric("+number+")");
		boolean isNumber = true;
		try { 
			if(number!=null){
				Integer.parseInt(number); 
			}
	        
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
		return isNumber;
	}
	public String getDeliveryConfirmationNumber(String message){
		logger.info("getDeliveryConfirmationNumber("+message+")");
		String[] statusList = message.split(" ");
		String number = "";
		for(int i=0; i<statusList.length;i++){
			if(isNumeric(statusList[i])){
				number = statusList[i];
				break;
			}
		}
		deliveryNumber = number;
		RationalTestScript.logInfo("Deliver Number : " +deliveryNumber );
		return number;
	}
	public boolean verifyDeviceDeliveryStatus(String vpName){
		logger.info("verifyDeviceDeliveryStatus("+vpName+")");
		boolean result = false;
		String actual = getStatusBarText();
		String delNumber = actual.split(" ")[4];
		
		String baseline = "Outb Del Device 3PL " + delNumber + " has been saved";
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, actual, baseline).performTest();
		
		
		return result;
	}
	public String getDeliveryNumber(){
		logger.info("getDeliveryNumber");
		String actual = getStatusBarText();
		
		String delNumber = actual.split(" ")[4];
		System.out.println("Delivery Number : " + delNumber);
		return delNumber;
	}
}
