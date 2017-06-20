package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;
import utils.EnvironmentUtility;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;
import com.rational.test.ft.vp.ITestDataTree;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPStatusMonitorDialogModel;

public class SAPStatusMonitorDialogController extends SAPDialogController{
	private static Logger logger = Logger.getLogger(SAPStatusMonitorDialogController.class);
	private static SAPStatusMonitorDialogModel appModel = new SAPStatusMonitorDialogModel();
	private EnvironmentUtility envUtil = new EnvironmentUtility();
	private String sapEnv = envUtil.sap().serverName().split(" ")[2];
	
	public SAPStatusMonitorDialogController(){
		super(appModel.dialog());
	}

	public void expandiDocOutboundProcessing(){
		logger.info("expandiDocOutboundProcessing");
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDocs in outbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing"),"IDoc ready for dispatch (ALE service)")){
				appModel.iDocTree().expandNode(atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)"));
				delayFor(2);
			}
			
		}

	
	}

	public void expandiDocInboundProcessing(){
		logger.info("expandiDocInboundProcessing");
		System.out.println(sapEnv+" Client 010");
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				appModel.iDocTree().expandNode(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"));
				delayFor(2);
			}	
		}
		
	}
	public void selectSHPCONAndProcess(){
		logger.info("selectSHPCONAndProcess");
		appModel.iDocTree().selectItem(
                atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->SHPCON"), "Column1");
		process();
		delayFor(5);
	}
	public boolean isOrdersVisible(Subitem nodePath){
		boolean result = false;
		String key = appModel.iDocTree().getNodeKeyByPath(nodePath);//  KeyByPath(path);
		String nodes[] = appModel.iDocTree().getSubNodesCol(key);
		if(nodes!=null){
			for(int i=0; i<nodes.length;i++){
				String nodesText = appModel.iDocTree().getNodeTextByKey(nodes[i]);
				if(nodesText.contentEquals("ORDERS")){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean isNodesVisible(Subitem nodePath,String nodeNameTofind){
		boolean result = false;
		String key = appModel.iDocTree().getNodeKeyByPath(nodePath);//  KeyByPath(path);
		TestObject tos[]=null;
		int childNode = appModel.iDocTree().getNodeChildrenCount(key);
		if(childNode>=1){
			String nodes[] = appModel.iDocTree().getSubNodesCol(key);
			if(nodes!=null){
				for(int i=0; i<nodes.length;i++){
					String nodesText = appModel.iDocTree().getNodeTextByKey(nodes[i]);
					if(nodesText.contentEquals(nodeNameTofind)){
						result = true;
						break;
					}
				}
			}
			
		}
		return result;
	}
	public boolean isInboundSHPCONAVisible(){
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				result =isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"),"SHPCON");
			}
		}
		
		
		return result;
	}
	public boolean isInboundOrdersVisible(){
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				result = isOrdersVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"));
			}
		}
		return result;
	}
	
	public boolean isOutboundOrdersVisible(){
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDocs in outbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing"),"IDoc ready for dispatch (ALE service)")){
				result = isOrdersVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)"));
			}
		}
		
		return result;
	}
	public boolean isOutboundORDRSPVisible(){
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDocs in outbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing"),"IDoc ready for dispatch (ALE service)")){
				result = isNodesVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)"),"ORDRSP");
			}
		}
		else{
			System.out.println("Node is not visible");
		}
		return result;
	}

	public boolean isInboundDesadvVisible(){
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				result = isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"),"DESADV");
			}
			
		}
		return result;
	}

	public boolean isInboundZWARRANTY_UPDATEVisible(){
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"), "IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"), "IDoc ready to be transferred to application")){
				result =isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"),"ZWARRANTY_UPDATE");
			}
		}
		else{
			RationalTestScript.logError("ZWARRANTY_UPDATE is not visible");
		}
		return result;
	}

	public void selectInboundORDERSndProcess(){
		logger.info("selectORDERSndProcess");
		appModel.iDocTree().selectItem(
               atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->ORDERS"), "Column1");
		process();
		delayFor(5);
	}

	public void selectORDERSndProcess(){
		logger.info("selectORDERSndProcess");
	
		appModel.iDocTree().selectItem(
                atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)->ORDERS"), "Column1");
		process();
		delayFor(3);
	}

	public void selectORDRSPndProcess(){
		logger.info("selectORDERSndProcess");
	
		appModel.iDocTree().selectItem(
                atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)->ORDRSP"), "Column1");
		process();
		delayFor(3);
	}

	public void selectDESADVAndProcess(){
		logger.info("selectDESADVAndProcess");
		if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"), "DESADV")){
			appModel.iDocTree().selectItem(
	                atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->DESADV"), "Column1");
			process();
			delayFor(5);
		}
	}
	public void selectPORDCHAndProcess(){
		logger.info("selectPORDCHAndProcess");
		if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"), "PORDCH")){
			appModel.iDocTree().selectItem(
	                atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->PORDCH"), "Column1");
			process();
			delayFor(5);
		}
	}
	public boolean isPORDCHVisible(){
		logger.info("isPORDCHVisible()");
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				result = isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"),"PORDCH");
			}
		}
		return result;
	}
	public boolean isOutboundInvoiceVisible(){
		logger.info("isInboundInvoiceVisible");
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in outbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in outbound processing"),"IDoc ready for dispatch (ALE service)")){
				result = isOrdersVisible(atPath(sapEnv+" Client 010->IDoc in outbound processing->IDoc ready for dispatch (ALE service)"));
			}
		}
		return result;
	}

	public boolean isInboundInvoiceVisible(){
		logger.info("isInboundInvoiceVisible");
		boolean result = false;
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				result = isOrdersVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"));
			}
		}
		return result;
	}
	public boolean isInboundPORDCHVisible(){
		logger.info("isInboundPORDCHVisible");
		boolean result = false;
		System.out.println(sapEnv+" Client 010");
		if(isNodesVisible(atPath(sapEnv+" Client 010"),"IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"),"IDoc ready to be transferred to application")){
				result = isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"),"PORDCH");
			}
		}
		return result;
	}
	
	public void selectINVOICndProcess(){
		logger.info("selectINVOICndProcess");
		if(isNodesVisible(atPath(sapEnv+" Client 010"), "IDoc in inbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing"), "IDoc ready to be transferred to application")){
				if(isNodesVisible(atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"), "INVOIC")){
					appModel.iDocTree().selectItem(
			                atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->INVOIC"), "Column1");
					process();
					delayFor(5);
				}
			}
		}
	}
	public void selectOutboundINVOICndProcess(){
		logger.info("selectINVOICndProcess");
		if(isNodesVisible(atPath(sapEnv+" Client 010"), "IDocs in outbound processing")){
			if(isNodesVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing"), "IDoc ready for dispatch (ALE service)")){
				if(isNodesVisible(atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)"), "INVOIC")){
					appModel.iDocTree().selectItem(
			                atPath(sapEnv+" Client 010->IDocs in outbound processing->IDoc ready for dispatch (ALE service)->INVOIC"), "Column1");
					process();
					delayFor(5);
				}
			}
		}
	}
	//This function will wait for ZWARRANTY_UPDATE for 6 mins
	public void selectZWARRANTY_UPDATEndProcess(){
		
		boolean result = false;
		BNTimer timer = new BNTimer();
		timer.start();
		if(!isInboundZWARRANTY_UPDATEVisible()){

			do{
				refresh();
				expandiDocInboundProcessing();
				result =isInboundZWARRANTY_UPDATEVisible();
				if(result){
					appModel.iDocTree().selectItem(
			                atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->ZWARRANTY_UPDATE"), "Column1");
					process();
					delayFor(5);
					
				}
				if(timer.getElapsedTime()>=600){
					RationalTestScript.logError("Unable to Find ZWARRANTY_UPDATE in 6 mins");
					break;
				}
			}while(result==false);
		}else{
			appModel.iDocTree().selectItem(
	                atPath(sapEnv+" Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->ZWARRANTY_UPDATE"), "Column1");
			process();
			delayFor(5);
		}
		
		
	}

	public void openDESADV(){
		logger.info("selectDESADVAndProcess");
		appModel.iDocTree().doubleClickItem(
                atPath("RQ2 Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->DESADV"), "Column1");
		delayFor(5);
	}
	public void process(){
		logger.info("process");
		appModel.processButton().click();
		delayFor(5);
	}
	public void displayIDOCs(){
		logger.info("process");
		appModel.displayIDOCsButton().click();
		delayFor(5);
	}
	
	public boolean  verifyStatusMonitorGrid(String vpName){
		logger.info("verifyStatusMonitorGrid("+vpName+")");
		boolean result = false;
		//ITestData testData = appModel.iDocTree().getTestData("contents");
		ITestDataTree orderTable = (ITestDataTree)appModel.iDocTree().getTestData("tree");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, orderTable).performTest();
		
		return result;
	}
	public void clickOK(){
		logger.info("clickOK()");
		appModel.okButton().click();
	}
	public void refresh(){
		logger.info("refresh()");
		appModel.refreshButton().click();
	}
}
