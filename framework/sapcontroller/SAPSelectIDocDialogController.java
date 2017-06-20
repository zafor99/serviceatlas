package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPSelectIDocDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 23, 2013
 */
public class SAPSelectIDocDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPSelectIDocDialogController.class);
	private static SAPSelectIDocDialogModel appModel = new SAPSelectIDocDialogModel();
	
	public SAPSelectIDocDialogController(){
		super(appModel.dialog());
	}
	
	public void searchIDoc(String timeCreatedFrom, String timeCreatedTo, String changedOnFrom, String changedOnTo, String lastChangedAtFrom, String lastChangedAtTo)
	{
		logger.info("searchIDoc("+timeCreatedFrom+","+timeCreatedTo+","+changedOnFrom+","+changedOnTo+","+lastChangedAtFrom+","+lastChangedAtTo+")");
		if(timeCreatedFrom!=null && !timeCreatedFrom.contentEquals("!")){
			appModel.timeCreatedFromTextBox().setText(timeCreatedFrom);
		}
		
		if(timeCreatedTo!=null && !timeCreatedTo.contentEquals("!")){
			appModel.timeCreatedToTextBox().setText(timeCreatedTo);
		}
		
		if(changedOnFrom!=null && !changedOnFrom.contentEquals("!")){
			appModel.changedOnFromTextBox().setText(changedOnFrom);
		}
		
		if(changedOnTo!=null && !changedOnTo.contentEquals("!")){
			appModel.changedOnToTextBox().setText(changedOnTo);
		}
		
		
		if(lastChangedAtTo!=null && !lastChangedAtTo.contentEquals("!")){
			appModel.lastChangedAtFromTextBox().setText(lastChangedAtTo);
		}
		
		if(lastChangedAtFrom!=null && !lastChangedAtFrom.contentEquals("!")){
			appModel.lastChangedAtFromTextBox().setText(lastChangedAtFrom);
		}
		
		appModel.executeButton().click();
		delayFor(3);
		
	}
	public void searchIDoc(String timeCreatedFrom, String timeCreatedTo, String changedOnFrom, String changedOnTo, String lastChangedAtFrom, String lastChangedAtTo,String iDocStatus)
	{
		logger.info("searchIDoc("+timeCreatedFrom+","+timeCreatedTo+","+changedOnFrom+","+changedOnTo+","+lastChangedAtFrom+","+lastChangedAtTo+","+iDocStatus+")");
		if(timeCreatedFrom!=null && !timeCreatedFrom.contentEquals("!")){
			appModel.timeCreatedFromTextBox().setText(timeCreatedFrom);
		}
		
		if(timeCreatedTo!=null && !timeCreatedTo.contentEquals("!")){
			appModel.timeCreatedToTextBox().setText(timeCreatedTo);
		}
		
		if(changedOnFrom!=null && !changedOnFrom.contentEquals("!")){
			appModel.changedOnFromTextBox().setText(changedOnFrom);
		}
		
		if(changedOnTo!=null && !changedOnTo.contentEquals("!")){
			appModel.changedOnToTextBox().setText(changedOnTo);
		}
	
		if(lastChangedAtFrom!=null && !lastChangedAtFrom.contentEquals("!")){
			appModel.lastChangedAtFromTextBox().setText(lastChangedAtFrom);
		}
		if(lastChangedAtTo!=null && !lastChangedAtTo.contentEquals("!")){
			appModel.lastChangedAtToTextBox().setText(lastChangedAtTo);
		}
		if(iDocStatus!=null && !iDocStatus.contentEquals("!")){
			appModel.iDocStatusFromTextBox().setText(iDocStatus);
		}
		appModel.executeButton().click();
		delayFor(3);
		
	}
	public void searchIDoc(String iDoc){
		if(iDoc!=null){
			appModel.iDocNumberFromTextBox().setText(iDoc);
			appModel.executeButton().click();
			delayFor(3);
		}
	}
	public void searchExistingIDoc(String iDoc){
		logger.info("searchExistingIDoc("+iDoc+")");
		if(iDoc!=null){
			appModel.existingiDocNumberFromTextBox().setText(iDoc);
			appModel.executeButton().click();
			delayFor(3);
		}
	}

	public void execute(){
		appModel.executeButton().click();
		delayFor(3);
	}

	
	
}
