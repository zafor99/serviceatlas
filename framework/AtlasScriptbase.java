package framework;

import static com.bn.qa.webservice.restclient.BNRestful.getNewInstance;
import static com.bn.qa.webservice.restclient.BNRestful.given;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.xobject.XOptions;
import com.bn.qa.xobject.script.XObjectTestScript;
import com.rational.test.ft.script.RationalTestScript;

import framework.alchemycontroller.AlchemyController;
import framework.crmcontroller.CRMApplicationController;
import framework.pfstoolscontroller.PFSApplicationController;
import framework.mswallettoolcontroller.MSWalletToolController;
import framework.sapcontroller.SAPApplicationController;
import framework.sapcontroller.SAPControllerBase;
import utils.ActiveMQUtil;
import utils.BNTimer;
import utils.JmxProxy;
import utils.MSWalletIPEnvUtil;
import utils.SpreadSheetUtil;
import framework.webdrivercontroller.DashboardApplicationController;
import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 02, 2013
 */
public abstract class AtlasScriptbase extends XObjectTestScript
{
	private static Logger logger = Logger.getLogger(AtlasScriptbase.class);
	/** The is logger initialize. */
	private boolean isLoggerInitialize = false;
	/** The is cache manager initialize. */
	private boolean isCacheManagerInitialize = false;
	/** The is datapool initialize. */
	private boolean isDatapoolInitialize = false;
	private static AtlasScriptbase executingScript = null;
	private static AtlasController atlas = null;
	private static AtlasManagementToolController atlasManagementTool = null;
	private static SAPApplicationController sap = null;
	private static DBValidationService dbService = null;
	private static ActiveMQUtil activeMQ = null;
	private static PFSApplicationController pfs = null;
	private static CRMApplicationController crm = null;
	private static DashboardApplicationController camel = null;
	private static CheckOutService checkout = null;
	private static InstantPurchase instantPurchase = null;
	private static WebDriver driver = null;
	public static SpreadSheetUtil m_SpreadSheet = null;
	private static MSWalletService msWalletService = null;
	private MSWalletToolController mswalletTool =  null;
	private MSWalletIPEnvUtil msWalletIPEnv = null;
	private static String spreadSheetPathIPOrders = "\\\\bnyfsfile05\\QADept\\QA Automation\\IPOrders.xls";
	private CustomerUserAccount customerUserAccount = null;
	private static TaxRulesAndRatesInstantPurchase taxRulesAndRatesInstantPurchase = null;
	private DigitalLockerService digitalLocker = null;
	private ActivityMonitorService activityMonitor = null;
	private ActivityMonitorOrderStatusDB activityMonitorOrderStatusDB = null;
	private static final int ROUTE_STATE_STARETED = 1;
	private static AlchemyController alchemy = null;

	public static AtlasScriptbase getExecutingScript(){
		
		return executingScript;
		
	}
	

	public static IRequestSpecification caliber(){
		
		return given();
	}
	
	public AlchemyController alchemy(){
		if(alchemy==null){
			alchemy = new AlchemyController() ;
		}
		return alchemy;
	}
	

	public static IRequestSpecification caliberNewInstance(){
		
		return getNewInstance();
	}
	
	
	public void setCRMToNull(){
		crm = null;
	}
	public void setDriverToNull(){
		driver = null;
	}
	public CheckOutService checkout(){
		if(checkout==null){
			checkout = new CheckOutService();
		}
		
		return checkout;
	}
	public InstantPurchase instantPurchase(){
		if(instantPurchase == null){
			instantPurchase = new InstantPurchase();
		}
		return instantPurchase;
	}
	
	public AtlasController atlas(){
		if(atlas == null){
			atlas = new AtlasController();
		}
		
		return atlas;
	}
	public AtlasManagementToolController atlasManagementTool(){
		
		if(atlasManagementTool == null){
			atlasManagementTool = new AtlasManagementToolController();
		}
		
		return atlasManagementTool;
	}
	
	public WebDriver startWebDriver(){
		if(driver==null){
			//driver = new FirefoxDriver();
			//driver = new ChromeDriver(); 
			//driver = new OperaDriver(); 
			driver = new InternetExplorerDriver();
		}
		
		return driver;
	}
	
	public DashboardApplicationController camel(){
		
		if(camel == null){
			startWebDriver();
			camel = new DashboardApplicationController(driver);
		}
		
		return camel;
	}
	
	public SAPApplicationController sap(){
		if(sap==null){
			sap = new SAPApplicationController();
		}
		
		return sap;
	}
	
	public DBValidationService dbService(){
		if(dbService ==null){
			dbService = new  DBValidationService();
		}
		return dbService;
	}
	public ActiveMQUtil activeMQService(){
		if(activeMQ ==null){
			activeMQ = new  ActiveMQUtil();
		}
		return activeMQ;
	}
	public DigitalLockerService digitalLocker(String customerID){
		if(digitalLocker == null){
			digitalLocker = new DigitalLockerService(customerID);
		}
		return digitalLocker;
	}
	public ActivityMonitorService activityMonitor(){
		if(activityMonitor== null){
			activityMonitor = new ActivityMonitorService();
		}
		return activityMonitor;
	}
	 public ActivityMonitorOrderStatusDB activityMonitorOrderStatusDB(String orderNumber){
		 if(activityMonitorOrderStatusDB==null){
			 activityMonitorOrderStatusDB = new ActivityMonitorOrderStatusDB(orderNumber);
		 }
		 return activityMonitorOrderStatusDB;
	 }
	public PFSApplicationController pfsTools(){
		if(pfs ==null){
			pfs = new PFSApplicationController();
		}
		return pfs;
	}
	
	public CRMApplicationController crm(){
		if(crm==null){
			startWebDriver();
			crm = new CRMApplicationController(driver);
		}
		return crm;
	}
	
	public MSWalletIPEnvUtil msWalletIPEnv(){
		if(msWalletIPEnv==null){
			msWalletIPEnv = new MSWalletIPEnvUtil();
		}
		
		return msWalletIPEnv;
	}
	
	public MSWalletService msWalletService(){
		if(msWalletService==null){
			msWalletService = new MSWalletService();
		}
		return msWalletService;
	}
	
	public MSWalletToolController mswalletTool(){
		if(mswalletTool == null){
			mswalletTool = new MSWalletToolController();
		}
		return mswalletTool;
	}
	
public CustomerUserAccount customerUserAccount(){
		
		if(customerUserAccount==null){
			customerUserAccount = new CustomerUserAccount();
		}

		return customerUserAccount;
		
		
	}

public static TaxRulesAndRatesInstantPurchase taxRulesAndRatesInstantPurchase(){
	if(taxRulesAndRatesInstantPurchase==null){
		taxRulesAndRatesInstantPurchase =  new TaxRulesAndRatesInstantPurchase();
	}		
	return taxRulesAndRatesInstantPurchase;
}
	
	@Override
	public Object runMain(Object[] arg0, int arg1) 
	{
		initializeLogger();
		//initializeObjectCache();
		executingScript = this;
		//initializeDatapool();
		return super.runMain(arg0, arg1);
	}

	@Override
	public Object runMain(Object[] arg0)
	{
		initializeLogger();
		//initializeObjectCache();
		executingScript = this;
		//initializeDatapool();
		return super.runMain(arg0);
		
	}
	
	@Override
	public void onTerminate(){
		if(HealthCheckSmokeTest.isRunning()!=null){
			if(HealthCheckSmokeTest.scriptStarted()){
				HealthCheckSmokeTest.endScript();
			}
		}
		unregisterAll();
	}
	

	protected void initializeLogger()
	{
		if(!isLoggerInitialize)
		{
			BasicConfigurator.resetConfiguration();
			BasicConfigurator.configure();
			Logger.getRootLogger().setLevel(Level.ALL);

			logger.info("initializeLogger");
		}
	}
	 public static SpreadSheetUtil createSpredSheetUtilObj(String xlsFilePath){
			
			m_SpreadSheet = new SpreadSheetUtil(xlsFilePath, 0);		
			return m_SpreadSheet;
		}

	protected void initializeObjectCache()
	{
		if(!isCacheManagerInitialize)
		{
			logger.debug("initializing XObject Cache Manager ....");
			XOptions.setUseOptimizeFind(false);
			XOptions.setObjectQueryCash(false);
			XOptions.setXTestObjectCashManager("com.bn.qa.xobject.util.XTestObjectCashManagerHSQLDB");
			//XOptions.setXTestObjectCashManager("com.bn.qa.xobject.util.XTestObjectCashManagerHashMap");
			//XTestObjectCashManager.setXTestObjectCashManager(new IDeskTestObjectCashManager());

		}
	}
	 public static SpreadSheetUtil ipOrdersXLS(){
			
			m_SpreadSheet = new SpreadSheetUtil(spreadSheetPathIPOrders, 0);		
			return m_SpreadSheet;
		}
	 protected String generateRandom(int length) {
		    Random random = new Random();
		    char[] digits = new char[length];
		    digits[0] = (char) (random.nextInt(9) + '1');
		    for (int i = 1; i < length; i++) {
		        digits[i] = (char) (random.nextInt(10) + '0');
		    }
		    return new String(digits);
		}
	public WebDriver getDriver(){
		return crm().getDriver();
	}
	@Deprecated
	public void writeBNOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean);
		
	}
	public void writeBNOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
	}
	public void writeOrdersFromDeviceToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeOrdersFromDeviceToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\OrdersFromDevice.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
	}

	public void writeOutboundBNOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeOutboundBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\bnwallet\\BNWallet.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
	}
	public void writeInvoicedBNOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeOutboundBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\invoiced\\BNWallet_invoiced.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
	}

	public String getOutboundBNOrderNumber(String testCaseName){
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\bnwallet\\BNWallet.xls";
		
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		if(excelSheet.readRow(testCaseName, 0)!=null){
			//Write the Data
			orderNumber = excelSheet.getCellStringValue("Order Number");
		}
		
		return orderNumber;
	}
	public void writeOutboundGCOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeOutboundGCOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\gcorders\\BNWallet.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
	}
	
	public void createIntegrationExcelFile(){
		String timeStamp =BNTimer.getCurrentDate("MMddYYYY");
		String fileName = "Integration_"+timeStamp+".xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil();
		excelSheet.createExcelFileForInegrationTest(fileName);
	}
	
	
	public void createintegrationExcelFile(String fileName){
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil();
		excelSheet.createExcelFileForInegrationTest(fileName);
	}

	public void writeDigitalOrdersForSAPToExcel(String tcName,String dateNTime,String orderNumber, String ean){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\SAP Scripts\\digital\\DigitalOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean);
		
	}
	public void writeOrdersForSAPExcelReporting(String tcName,String ean,String orderNumber, String sapOrderNumber, String poNumber, String invoiceNumber, String IR){
		logger.info("writeOrdersForSAPExcelReporting("+tcName+","+ean+","+orderNumber+","+sapOrderNumber+","+poNumber+","+invoiceNumber+","+IR+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\SAP Scripts\\digital\\DigitalOrders.xls";
		writeOrdersToSAPExcelReport(spreadSheetPath, tcName, ean, orderNumber, sapOrderNumber, poNumber, invoiceNumber, IR);
		
	}
	
	
	public String getEANFromSAPOrdersExcel(String tcName){
		logger.info("getEANFromSAPOrdersExcel()");
		String ean = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\SAP Scripts\\digital\\DigitalOrders.xls";
		ean = getDataFromExcel(spreadSheetPath, "EAN", tcName);
		System.out.println("EAN "+  ean);
		return ean;
	}
	@Deprecated
	public void writeBNOrdersWTokenizationToExcel(String tcName,String dateNTime,String orderNumber, String ean){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\BNOrdersWTokenization.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean);
		
	}
	public void writeBNOrdersWTokenizationToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\BNOrdersWTokenization.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
		
	}
	public String getOrderNumberFromDeviceOrdersExcel(String tcName){
		logger.info("getOrderNumberFromDeviceOrdersExcel()");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\OrdersFromDevice.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "Order Number", tcName);
		System.out.println("orderNumber "+  orderNumber);
		return orderNumber;
	}
	public String getCustomerIDFromDeviceOrdersExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String customerID = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\OrdersFromDevice.xls";
		customerID = getDataFromExcel(spreadSheetPath, "customerID", tcName);
		System.out.println("customer ID "+  customerID);
		return customerID;
	}
	public String getOrderNumberFromBNOrdersExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "Order Number", tcName);
		System.out.println("orderNumber "+  orderNumber);
		return orderNumber;
	}
	public String getCustomerIDFromBNOrdersExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String customerID = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
		customerID = getDataFromExcel(spreadSheetPath, "customerID", tcName);
		System.out.println("customer ID "+  customerID);
		return customerID;
	}
	public String getOrderNumberFromMSWalletOrdersExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAtlasOrders.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "Order Number", tcName);
		System.out.println("orderNumber "+  orderNumber);
		return orderNumber;
	}
	public String getCustomerIDFromMSWalletOrdersExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String customerID = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAtlasOrders.xls";
		customerID = getDataFromExcel(spreadSheetPath, "customerID", tcName);
		System.out.println("customer ID "+  customerID);
		return customerID;
	}
	public String getEANFromMSWalletOrdersExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAtlasOrders.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "EAN", tcName);
		return orderNumber;
	}
	public String getOrderNumberFromBNPhysicalOrdersExcel(String tcName){
		logger.info("getOrderNumberFromBNPhysicalOrdersExcel("+tcName+")");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\PhysicalItemAtlasOrders.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "Order Number", tcName);
		System.out.println("orderNumber "+  orderNumber);
		return orderNumber;
	}
	public String getCustomerIDFromBNPhysicalOrdersExcel(String tcName){
		logger.info("getCustomerIDFromBNPhysicalOrdersExcel("+tcName+")");
		String customerID = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\PhysicalItemAtlasOrders.xls";
		customerID = getDataFromExcel(spreadSheetPath, "customerID", tcName);
		System.out.println("customer ID "+  customerID);
		return customerID;
	}
	public String getEANFromBNPhysicalOrdersExcel(String tcName){
		logger.info("getEANFromBNPhysicalOrdersExcel("+tcName+")");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\PhysicalItemAtlasOrders.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "EAN", tcName);
		return orderNumber;
	}
	public String getEANFromBNOrdersExcel(String tcName){
		logger.info("getEANFromBNOrdersExcel("+tcName+")");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "EAN", tcName);
		return orderNumber;
	}
	public String getOrderNumberFromBNOrdersWTokenizationExcel(String tcName){
		logger.info("getOrderNumberFromBNOrdersWTokenizationExcel("+tcName+")");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\BNOrdersWTokenization.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "Order Number", tcName);
		return orderNumber;
	}
	public String getCustomerIDFromBNOrdersWTokenizationExcel(String tcName){
		logger.info("getOrdernumberFromBNOrdersWTokenizationExcel()");
		String orderNumber = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\BNOrdersWTokenization.xls";
		orderNumber = getDataFromExcel(spreadSheetPath, "customerID", tcName);
		return orderNumber;
	}

	public String getDataFromExcel(String spreadSheetPath, String columnName,String tcName){
		String data = null;
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		if(excelSheet.readRow(tcName, 0)!=null){
			data=  excelSheet.getCellStringValue(columnName);// setCellStringValue("Order Number", orderNumber);
		}
		return data;
		
	}
	public void writeOrdersToSAPExcelReport(String spreadSheetPath,String tcName,String ean,String orderNumber,String sapOrderNumber, String poNumber,String invoiceNumber,String IR){
		logger.info("writeOrdersToSAPExcelReport("+spreadSheetPath+ "),("+tcName+"),("+ean+"),("+orderNumber+"),("+sapOrderNumber+"),("+poNumber+"),("+invoiceNumber+"),("+IR+")");
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		if(excelSheet.readRow(tcName, 0)!=null){
			//Write the Data
			if(ean!=null){
				excelSheet.setCellStringValue("EAN", ean);
			}
			if(orderNumber!=null){
				excelSheet.setCellStringValue("FE ordernum", orderNumber);
			}
			if(sapOrderNumber!=null){
				excelSheet.setCellStringValue("SAP Ordernum", sapOrderNumber);
			}
			if(poNumber!=null){
				excelSheet.setCellStringValue("PO#", poNumber);
			}
			if(invoiceNumber!=null){
				excelSheet.setCellStringValue("Invoice", invoiceNumber);
			}
			if(IR!=null){
				excelSheet.setCellStringValue("IR", IR);
			}
		}
	}

	@Deprecated
	public void writePhysicalOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\PhysicalItemAtlasOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean);
		
	}
	public void writePhysicalOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\PhysicalItemAtlasOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
		
	}
	@Deprecated
	public void writeMSWalletOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean){
		logger.info("writeMSWalletOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAtlasOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean);
		
	}
	public void writeMSWalletOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeMSWalletOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAtlasOrders.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
		
	}
	public void writeOutboundMSWalletOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeMSWalletOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\mswallet\\MSWallet.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
		
	}

	@Deprecated
	public void writeOrdersToExcel(String spreadSheetPath,String tcName,String dateNTime,String orderNumber, String ean){
		logger.info("writeMSWalletOrdersToExcel("+spreadSheetPath+ "),("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		if(excelSheet.readRow(tcName, 0)!=null){
			//Write the Data
			excelSheet.setCellStringValue("Order Number", orderNumber);
			excelSheet.setCellStringValue("EAN", ean);
			excelSheet.setCellStringValue("DateNTime", dateNTime);
		}
	}
	public void writeIntegrationOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID,String orderStatus){
		logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\integration\\";
		String date =BNTimer.getCurrentDate("MMddYYYY");
		String fileName = "Integration_"+date+".xls";
		
		writeOrdersToExcel(spreadSheetPath+fileName, tcName, dateNTime, orderNumber, ean,customerID,orderStatus);
	}

	
	public void writeOrdersToExcel(String spreadSheetPath,String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeMSWalletOrdersToExcel("+spreadSheetPath+ "),("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+"),("+customerID+")");
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		if(excelSheet.readRow(tcName, 0)!=null){
			//Write the Data
			excelSheet.setCellStringValue("Order Number", orderNumber);
			excelSheet.setCellStringValue("EAN", ean);
			excelSheet.setCellStringValue("DateNTime", dateNTime);
			excelSheet.setCellStringValue("customerID", customerID);
		}
	}
	public void writeOrdersToExcel(String spreadSheetPath,String tcName,String dateNTime,String orderNumber, String ean,String customerID,String orderStatus){
		logger.info("writeMSWalletOrdersToExcel("+spreadSheetPath+ "),("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+"),("+customerID+"),("+orderStatus+")");
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		if(excelSheet.readRow(tcName, 0)!=null){
			//Write the Data
			excelSheet.setCellStringValue("Order Number", orderNumber);
			excelSheet.setCellStringValue("EAN", ean);
			excelSheet.setCellStringValue("DateNTime", dateNTime);
			excelSheet.setCellStringValue("customerID", customerID);
			excelSheet.setCellStringValue("Order Status", orderStatus);
		}
	}
	public String getAccessTokenFromMSWalletAccountBindingExcel(){
		logger.info("getAccessTokenFromMSWalletAccountBindingExcel()");
		String accessToken = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAccountBinding.xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		excelSheet.readRow(1);
		accessToken = excelSheet.getCellStringValue("Access Token");
		return accessToken;
	}
	public String getAccessTokenFromMSWalletAccountBindingExcel(String spreadSheetPath){
		logger.info("getAccessTokenFromMSWalletAccountBindingExcel()");
		String accessToken = null;
//		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAccountBinding.xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		excelSheet.readRow(1);
		accessToken = excelSheet.getCellStringValue("Access Token");
		return accessToken;
	}
	public String getcustomerIDFromMSWalletAccountBindingExcel(){
		logger.info("getcustomerIDFromMSWalletAccountBindingExcel()");
		String accessToken = null;
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAccountBinding.xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		excelSheet.readRow(1);
		accessToken = excelSheet.getCellStringValue("Caliber UserID");
		return accessToken;
	}
	public String getcustomerIDFromMSWalletAccountBindingExcel(String spreadSheetPath){
		logger.info("getcustomerIDFromMSWalletAccountBindingExcel()");
		String accessToken = null;
		//String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAccountBinding.xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		excelSheet.readRow(1);
		accessToken = excelSheet.getCellStringValue("Caliber UserID");
		return accessToken;
	}

	public void resetOrderExcelData(String spreadSheetPath){
		logger.info("resetOrderExcelData("+spreadSheetPath+")");
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
	
		for(int i=1;i<19;i++){
			excelSheet.readRow(i);
			excelSheet.setCellStringValue("Order Number", "1");
			excelSheet.setCellStringValue("EAN", "1");
			excelSheet.setCellStringValue("DateNTime", "1");
			excelSheet.setCellStringValue("Order Status", "1");
			excelSheet.setCellStringValue("customerID", "1");
			excelSheet.setCellStringValue("iDoc Number", "1");
			excelSheet.setCellStringValue("iDoc Status", "1");
			excelSheet.setCellStringValue("iDoc Status after Processing", "1");
		}
	}
	public void resetBNOrdersWTokenizationOrderExcelData(){
		logger.info("resetBNOrdersWTokenizationOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\BNOrdersWTokenization.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void resetBNWalletOrderExcelData(){
		logger.info("resetBNWalletOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void resetOutboundBNWalletOrderExcelData(){
		logger.info("resetOutboundBNWalletOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\bnwallet\\BNWallet.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void resetOutboundGCOrderExcelData(){
		logger.info("resetOutboundGCOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\gcorders\\BNWallet.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void resetOutboundMSWalletOrderExcelData(){
		logger.info("resetOutboundMSWalletOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\mswallet\\MSWallet.xls";		
		//resetOrderExcelData(spreadSheetPath);
		resetOrderExcelData(spreadSheetPath);
	}

	public void resetMSWalletOrderExcelData(){
		logger.info("resetMSWalletOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\MSWalletAtlasOrders.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void resetInvoicedOrderExcelData(){
		logger.info("resetInvoicedOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\invoiced\\BNWallet_invoiced.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void resetPhysicalItemAtlasOrdersExcelData(){
		logger.info("resetMSWalletOrderExcelData()");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\PhysicalItemAtlasOrders.xls";
		resetOrderExcelData(spreadSheetPath);
	}
	public void writeBNOrdersToIntegretionExeExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
		logger.info("writeBNOrdersToIntegretionExeExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\Integration_Domestic.xls";
		writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
		
	}
	
	public static void writeResultToExternalSources(String vpName, IResponse response, Boolean result){
		
		String status = "";
		if(response!=null){
			status = "Status Code : " + response.getStatusCode();
		}
		HealthCheckSmokeTest.writeResultToDB(vpName, result, "", status);
	}
	

}
