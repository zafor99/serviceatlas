package framework.pfstoolscontroller;

import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import utils.BNTimer;

import com.rational.test.ft.script.RationalTestScript;

import framework.pfstoolsmodel.PFSMainDialogModel;
import utils.XMLUtility;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 09, 2013
 */
public  class PFSMainDialogController extends PFSControllerBase
{
	private static  Logger logger = Logger.getLogger(PFSMainDialogController.class); 
	public PFSMainDialogModel appModel = new PFSMainDialogModel(); 
	public PFSMainDialogController()
	{
		
	}
	@Deprecated
	public void searchOrder(String orderNumber){
		logger.info("searchOrder("+orderNumber+")");
		boolean result = false;
		BNTimer timer = new BNTimer();
		timer.start();
		appModel.orderNumberTextBox().setText(orderNumber);
		//checkInflight();
		do{
			clickGetOpenPO();
			result = isPOAvailable();
			if(timer.getElapsedTime()>=12000){
				RationalTestScript.logError("Search timeout in 6 mins");
				break;
			}
		}while(result==false);
		
		
//		appModel.pfsToolDialog().inputKeys("{Enter}");
	}
	public void searchOrder(String orderNumber,String actionType){
		logger.info("searchOrder("+orderNumber+")");
		boolean result = false;
		BNTimer timer = new BNTimer();
		timer.start();
		appModel.orderNumberTextBox().setText(orderNumber);
		//checkInflight();
		do{
			if(actionType.contains("Shop Confirm")){
				clickGetOpenPO();	
			}
			else if(actionType.contains("Ship Confirm")){
				clickGetShipPO();
			}
			
			result = isPOAvailable();
			if(timer.getElapsedTime()>=12000){
				RationalTestScript.logError("Search timeout in 6 mins");
				break;
			}
		}while(result==false);
		
		
//		appModel.pfsToolDialog().inputKeys("{Enter}");
	}
	public boolean isPOAvailable(){
		boolean result = false;
		String lstPO_SelectedValue = (String)appModel.poListBox().getProperty("SelectedValue");
		if(lstPO_SelectedValue!=null){
			result = true;
		}
		return result;
	}
	public void checkInflight(){
		appModel.shopConfirmTab().chkPOTypeListBox().click(atPath("Inflight->Location(CHECKBOX)"));
	}
	public void comfirmPO(){
		logger.info("comfirmPO()");
		String lstPO_SelectedValue = (String)appModel.poListBox().getProperty("SelectedValue");
		appModel.poListBox().click(RIGHT,atText(lstPO_SelectedValue));
		appModel.poListMenuBar().click(atPath("Confirm PO"));
		
	}
	public void shipPO(){
		logger.info("shipPO()");
		String lstPO_SelectedValue = (String)appModel.poListBox().getProperty("SelectedValue");
		appModel.poListBox().click(RIGHT,atText(lstPO_SelectedValue));
		appModel.poListMenuBar().click(atPath("Ship PO"));
		
	}
	public void clickGetOpenPO(){
		logger.info("clickGetOpenPO()");
		appModel.shopConfirmTab().getOpenPOButton().click();
	}
	public void verifyPOList(String vpName){
		logger.info("verifyPOList("+vpName+")");
		
		String lstPO_SelectedValue = (String)appModel.poListBox().getProperty("SelectedValue");
		getExecutingScript().vpManual(vpName, lstPO_SelectedValue).performTest();
	}
	
	public void verifyMsgBox(String vpName){
		logger.info("verifyMsgBox("+vpName+")");
		String msg = (String)appModel.statusTextMsgBox().getProperty("Text");
		getExecutingScript().vpManual(vpName, msg).performTest();
	}
	public void selectShipConfimTab(){
		logger.info("selectShipConfimTab()");
		appModel.tabList().click(atText("Ship Confirm"));
	}
	public void selectTab(String tabName){
		logger.info("selectShipConfimTab()");
		appModel.tabList().click(atText(tabName));
	}
	public void clickGetShipPO(){
		logger.info("clickGetShipPO()");
		appModel.shipConfirmTab().getShipPOButton().click();
	}
	public void checkXMLOnly(){
		logger.info("clickGetShipPO()");
		appModel.shipConfirmTab().xmlOnlyCheckBox().clickToState(SELECTED);
	}
	public void clickShipPO(){
		logger.info("clickShipPO()");
		appModel.shipConfirmTab().shipPOButton().click();
	}

	public void clickCallWebService(){
		logger.info("clickCallWebService()");
		appModel.webServiceTab().callWebServiceButton().click();
	}
	public void  updateWebServiceXMLWithSerialNo() throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		logger.info("updateWebServiceXML()");
		String randomSerial = "2020"+generateRandom(12);
		logger.info("Device Serial no" + randomSerial);
		System.out.println("Device Serial no" + randomSerial);
		String xml = appModel.webServiceTab().xmlTextBox().getProperty("Text").toString();
		String xmlWithSerial  = addSerialNoNode(xml,randomSerial);
		System.out.println(xmlWithSerial);
		appModel.webServiceTab().xmlTextBox().setText(xmlWithSerial);
		
	}
	public String addSerialNoNode (String xml,String serialNo) throws TransformerFactoryConfigurationError, TransformerException {
		String newXML=null;
		XMLUtility xmlCheckout = new XMLUtility();;
		Document doc = xmlCheckout.convertDocumentFromString(xml);

		NodeList nodes = doc.getElementsByTagName("trxUnitCost");

		org.w3c.dom.Text a = doc.createTextNode(serialNo); 
		Element p = doc.createElement("trxItemData");
		Element x= doc.createElement("serialNo"); 
		p.appendChild(x);
		x.appendChild(a); 
	
		nodes.item(0).getParentNode().insertBefore(p, nodes.item(0));

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);

		newXML = result.getWriter().toString();
		System.out.println(newXML);

		return newXML;
	}
	
}
