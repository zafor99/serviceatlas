package framework.sapcontroller;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.rational.test.ft.vp.IFtVerificationPoint;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPiDocProcessingDialogModel;

public class SAPiDocProcessingDialogController extends SAPDialogController{

	private static Logger logger = Logger.getLogger(SAPiDocProcessingDialogController.class);
	private static SAPiDocProcessingDialogModel appModel = new SAPiDocProcessingDialogModel();
	
	public SAPiDocProcessingDialogController(){
		super(appModel.dialog());
	}
	
	public void selectIDoc(String iDocNumber){
		logger.info("selectIDoc("+iDocNumber+")");
		int rows = Integer.valueOf(appModel.processIDocGrid().getProperty("RowCount").toString());
		boolean found = false;
		
		for(int i=0;i<rows;i++){
			if(appModel.processIDocGrid().getCellValue(i, "IDoc number").contentEquals(iDocNumber)){
				found = true;
				appModel.processIDocGrid().click(i, "IDoc number");
			}
		}
	}
	

	public boolean  verifyProcessedIDOCGrid(String vpName){
		logger.info("verifyProcessedIDOCGrid("+vpName+")");
		boolean result = false;
		ITestData testData = appModel.processIDocGrid().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
}
