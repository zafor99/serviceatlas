package framework.sapmodel;

import com.rational.test.ft.object.interfaces.SAP.SAPGuiTabTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPTopLevelToolbarModel extends SAPModelBase
{
	
	private SAPGuiToolbarTestObject toolbar = null;
	
	public SAPTopLevelToolbarModel (){
		toolbar = (SAPGuiToolbarTestObject)SAPSession.getActiveWindow().find(atList(atChild("Id","/tbar[0]")))[0];
	}

	public SAPGuiToggleTestObject refreshButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[0]")))[0];
	}
	
	public SAPGuiTextTestObject commandFieldComboTextBox(){
		return (SAPGuiTextTestObject)toolbar.find(atList(atChild("Id","/okcd")))[0];
	}
	
	public SAPGuiToggleTestObject commandFieldButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[423]")))[0];
	}
	
	public SAPGuiToggleTestObject exitButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[15]")))[0];
	}
	
	public SAPGuiToggleTestObject printButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[86]")))[0];
	}
	
	public SAPGuiToggleTestObject findButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[71]")))[0];
	}
	
	public SAPGuiToggleTestObject findNextButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[84]")))[0];
	}
	
	public SAPGuiToggleTestObject firstPageButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[80]")))[0];
	}
	
	public SAPGuiToggleTestObject previousPageButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[81]")))[0];
	}
	
	public SAPGuiToggleTestObject nextPageButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[82]")))[0];
	}
	
	public SAPGuiToggleTestObject lastPageButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[83]")))[0];
	}
	public SAPGuiToggleTestObject backButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[3]")))[0];
		
	}
	public SAPGuiToggleTestObject saveButton()
	{
		return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[11]")))[0];
		
	}
	
}
