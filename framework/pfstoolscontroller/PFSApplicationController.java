package framework.pfstoolscontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.pfstoolsmodel.PFSApplicationModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  August 22, 2013
 */
public class PFSApplicationController extends PFSControllerBase
{
	private static  Logger logger = Logger.getLogger(PFSApplicationController.class); 
	private PFSApplicationModel appModel = new PFSApplicationModel();
	private LoginDialogController logIndialog = null;
	private PFSMainDialogController mainDialog = null;
	
	public PFSApplicationController(){

	}
	
	public void startApplication(){
		RationalTestScript.startApp("PFSTool");
	}
	
	public void closeApplication(){
		appModel.mdiDocument().close();
	}
	
	public LoginDialogController logIndialog(){
		if(logIndialog==null){
			logIndialog = new LoginDialogController();
		}
		
		return logIndialog;
	}
	public PFSMainDialogController mainDialog(){
		if(mainDialog==null){
			mainDialog = new PFSMainDialogController();
		}
		
		return mainDialog;
	}
	

}
