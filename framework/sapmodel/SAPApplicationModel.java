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
 * @since  August 01, 2012
 */
public class SAPApplicationModel extends SAPModelBase
{
	private  RootTestObject root = null;
	private TestObject[] sapApps = null;
	private TestObject[] cons = null;
	private  SAPGuiApplicationTestObject theAPP = null;
	private SAPGuiConnectionTestObject con = null;
	private TestObject[] sessions = null;
	private SAPGuiSessionTestObject sess = null;
	public SAPTopLevelTestObject mainWnd = null;
	private TestObject[] sapWindows = null;
	private TestObject activeWnd = null;
	
	public SAPApplicationModel(){
		root = getRootTestObject();
		root.enableForTesting("sapLogon");
		sapApps = getRootTestObject().find(atChild(".domain", "SAP"));		
		theAPP = ((SAPTopLevelTestObject)sapApps[0]).getApplication();
		System.out.println("Application Number:" + theAPP.getProperty("Id"));
		cons = (TestObject[])theAPP.getProperty("Connections");
		con = (SAPGuiConnectionTestObject)cons[0];
		System.out.println("Connection Number:" + con.getProperty("Id"));
		//System.out.println("Connection Number:" + con.getProperty("Id"));

		sessions = (TestObject[])con.getProperty("Sessions");
		sess = (SAPGuiSessionTestObject)sessions[0];
		System.out.println("Session Number:" + sess.getProperty("Id"));
		System.out.println("Session Number:" + sess.getDescriptiveName());
	
		activeWnd = (TestObject) sess.getProperty("ActiveWindow");
		//System.out.println("Connection Number:" + activeWnd.getProperty("Id"));
		mainWnd = (SAPTopLevelTestObject)sess.getProperty("ActiveWindow");
		System.out.println("Main Window :" + mainWnd.getProperty("Id"));
		sapWindows = sess.getChildren();		
		
	}
	
	
	public SAPGuiApplicationTestObject getSAPApplication(){
		return theAPP;
	}
	
	public SAPGuiSessionTestObject getSAPSession(){
		return sess;
	}
	
	public SAPTopLevelTestObject getActiveWindow(){
		//System.out.println("Active Wnd : " + mainWnd.getPropertyFromMap("Id"));
		return new SAPTopLevelTestObject(mainWnd);
	}
	
	public TestObject getSAPWindow(String windowName){
		TestObject wnd = null;
		for(int i=0;i<sapWindows.length;i++){
			
		}
		return wnd;
	}
}
