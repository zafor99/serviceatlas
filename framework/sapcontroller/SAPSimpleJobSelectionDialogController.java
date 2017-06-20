package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPHitList1EntryDialogModel;
import framework.sapmodel.SAPSimpleJobSelectionDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 12, 2013
 */
public  class SAPSimpleJobSelectionDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPSimpleJobSelectionDialogController.class);
	private static SAPSimpleJobSelectionDialogModel appModel = new SAPSimpleJobSelectionDialogModel();
	public SAPSimpleJobSelectionDialogController(){
		super(appModel.dialog());
	}
	
	public void executeJob(String jobName,String userName,String jobStatus,String action){
		logger.info("executeJob("+jobName+","+userName+","+jobStatus+")");
		
		if(jobName!=null){
			appModel.jobNameTextBox().setText(jobName);
		}
		if(userName!=null){
			appModel.userNameTextBox().setText(userName);
		}
		if(jobStatus!=null){
			if(jobStatus.contains("Sched")){
				appModel.schedCheckBox().click();
				delayFor(2);
			}
			
		}
		if(action!=null){
			appModel.executeButton().click();
		}
		
		
	}
}
