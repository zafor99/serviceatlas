package framework.sapmodel;

import static com.rational.test.ft.script.RationalTestScript.getRootTestObject;

import com.rational.test.ft.object.interfaces.RootTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiApplicationTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiConnectionTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiSessionTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 14, 2014
 */
public class SAPSession extends SAPModelBase
{
	private static  RootTestObject root = null;
	private static TestObject[] sapApps = null;
	private static TestObject[] cons = null;
	private static SAPGuiApplicationTestObject theAPP = null;
	private static SAPGuiConnectionTestObject con = null;
	private static TestObject[] sessions = null;
	private static SAPGuiSessionTestObject sess = null;
	private static SAPTopLevelTestObject mainWnd = null;
	private static TestObject[] sapWindows = null;
	private static  TestObject activeWnd = null;
	
	public SAPSession(){
		
	}
	
	public static SAPGuiSessionTestObject getSession(){
		//if(sess==null){
			root = getRootTestObject();
			root.enableForTesting("sapLogon");
			sapApps = getRootTestObject().find(atChild(".domain", "SAP"));		
			theAPP = ((SAPTopLevelTestObject)sapApps[0]).getApplication();
			//System.out.println("Application Number:" + theAPP.getProperty("Id"));
			cons = (TestObject[])theAPP.getProperty("Connections");
			con = (SAPGuiConnectionTestObject)cons[0];
			//System.out.println("Connection Number:" + con.getProperty("Id"));
			sessions = (TestObject[])con.getProperty("Sessions");
			sess = (SAPGuiSessionTestObject)sessions[0];
		//}
		return sess;
	}
	

	
	public static SAPTopLevelTestObject getActiveWindow(){
		//System.out.println("Active Wnd : " + mainWnd.getPropertyFromMap("Id"));

		activeWnd = (TestObject) getSession().getProperty("ActiveWindow");
		//System.out.println("Connection Number:" + activeWnd.getProperty("Id"));
		mainWnd = (SAPTopLevelTestObject)getSession().getProperty("ActiveWindow");
		
		return new SAPTopLevelTestObject(mainWnd);
	}
	
	public TestObject getSAPWindow(String windowName){
		TestObject wnd = null;
		for(int i=0;i<sapWindows.length;i++){
			
		}
		return wnd;
	}
}
