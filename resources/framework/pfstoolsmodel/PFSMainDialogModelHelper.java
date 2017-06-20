// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.framework.pfstoolsmodel;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Script Name   : <b>PFSMainDialogModel</b><br>
 * Generated     : <b>2013/09/27 11:23:51 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  September 27, 2013
 * @author zsadeque
 */
public abstract class PFSMainDialogModelHelper extends RationalTestScript
{
	/**
	 * CallWebService: with default state.
	 *		Name : btnWebService
	 * 		Text : Call Web Service
	 * 		TabIndex : 22
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 0
	 */
	protected GuiTestObject callWebServicebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("callWebServicebutton"));
	}
	/**
	 * CallWebService: with specific test context and state.
	 *		Name : btnWebService
	 * 		Text : Call Web Service
	 * 		TabIndex : 22
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 0
	 */
	protected GuiTestObject callWebServicebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("callWebServicebutton"), anchor, flags);
	}
	
	/**
	 * cboEndPoint: with default state.
	 *		Name : cboEndPoint
	 * 		TabIndex : 3
	 * 		.class : System.Windows.Forms.ComboBox
	 * 		.classIndex : 0
	 */
	protected TextSelectGuiSubitemTestObject cboEndPointcomboBox() 
	{
		return new TextSelectGuiSubitemTestObject(
                        getMappedTestObject("cboEndPointcomboBox"));
	}
	/**
	 * cboEndPoint: with specific test context and state.
	 *		Name : cboEndPoint
	 * 		TabIndex : 3
	 * 		.class : System.Windows.Forms.ComboBox
	 * 		.classIndex : 0
	 */
	protected TextSelectGuiSubitemTestObject cboEndPointcomboBox(TestObject anchor, long flags) 
	{
		return new TextSelectGuiSubitemTestObject(
                        getMappedTestObject("cboEndPointcomboBox"), anchor, flags);
	}
	
	/**
	 * chkPoTypes: with default state.
	 *		Name : chkPoTypes
	 * 		TabIndex : 13
	 * 		.class : System.Windows.Forms.CheckedListBox
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject chkPoTypeslist() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("chkPoTypeslist"));
	}
	/**
	 * chkPoTypes: with specific test context and state.
	 *		Name : chkPoTypes
	 * 		TabIndex : 13
	 * 		.class : System.Windows.Forms.CheckedListBox
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject chkPoTypeslist(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("chkPoTypeslist"), anchor, flags);
	}
	
	/**
	 * contextMenuStrip1: with default state.
	 *		Name : contextMenuStrip1
	 * 		TabIndex : 0
	 * 		.class : System.Windows.Forms.ContextMenuStrip
	 * 		ParentHierarchy : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject contextMenuStrip1toolBar() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("contextMenuStrip1toolBar"));
	}
	/**
	 * contextMenuStrip1: with specific test context and state.
	 *		Name : contextMenuStrip1
	 * 		TabIndex : 0
	 * 		.class : System.Windows.Forms.ContextMenuStrip
	 * 		ParentHierarchy : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject contextMenuStrip1toolBar(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("contextMenuStrip1toolBar"), anchor, flags);
	}
	
	/**
	 * GetOpenPO: with default state.
	 *		Name : btnOpenPO
	 * 		TabIndex : 0
	 * 		Text : Get Open PO
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 1
	 */
	protected GuiTestObject getOpenPObutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("getOpenPObutton"));
	}
	/**
	 * GetOpenPO: with specific test context and state.
	 *		Name : btnOpenPO
	 * 		TabIndex : 0
	 * 		Text : Get Open PO
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 1
	 */
	protected GuiTestObject getOpenPObutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("getOpenPObutton"), anchor, flags);
	}
	
	/**
	 * GetShipPO: with default state.
	 *		Name : btnGetOpenShip
	 * 		TabIndex : 13
	 * 		Text : Get Ship PO
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 0
	 */
	protected GuiTestObject getShipPObutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("getShipPObutton"));
	}
	/**
	 * GetShipPO: with specific test context and state.
	 *		Name : btnGetOpenShip
	 * 		TabIndex : 13
	 * 		Text : Get Ship PO
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 0
	 */
	protected GuiTestObject getShipPObutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("getShipPObutton"), anchor, flags);
	}
	
	/**
	 * lstPO: with default state.
	 *		Name : lstPO
	 * 		TabIndex : 1
	 * 		.class : System.Windows.Forms.ListBox
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject lstPOlist() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("lstPOlist"));
	}
	/**
	 * lstPO: with specific test context and state.
	 *		Name : lstPO
	 * 		TabIndex : 1
	 * 		.class : System.Windows.Forms.ListBox
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject lstPOlist(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("lstPOlist"), anchor, flags);
	}
	
	/**
	 * PFSMessageTestTool: with default state.
	 *		Name : frmClient
	 * 		TabIndex : 0
	 * 		Text : PFS Message Test Tool
	 * 		.class : PFSTool.frmClient
	 * 		.processName : PFSTool.exe
	 * 		.classIndex : 0
	 */
	protected TopLevelSubitemTestObject pfsMessageTestToolwindow() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("pfsMessageTestToolwindow"));
	}
	/**
	 * PFSMessageTestTool: with specific test context and state.
	 *		Name : frmClient
	 * 		TabIndex : 0
	 * 		Text : PFS Message Test Tool
	 * 		.class : PFSTool.frmClient
	 * 		.processName : PFSTool.exe
	 * 		.classIndex : 0
	 */
	protected TopLevelSubitemTestObject pfsMessageTestToolwindow(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("pfsMessageTestToolwindow"), anchor, flags);
	}
	
	/**
	 * Ping: with default state.
	 *		Name : btnPing
	 * 		Text : Ping
	 * 		TabIndex : 2
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 1
	 */
	protected GuiTestObject pingbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("pingbutton"));
	}
	/**
	 * Ping: with specific test context and state.
	 *		Name : btnPing
	 * 		Text : Ping
	 * 		TabIndex : 2
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 1
	 */
	protected GuiTestObject pingbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("pingbutton"), anchor, flags);
	}
	
	/**
	 * ShipPO: with default state.
	 *		Name : btnShipPO
	 * 		Text : Ship PO
	 * 		TabIndex : 15
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 1
	 */
	protected GuiTestObject shipPObutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("shipPObutton"));
	}
	/**
	 * ShipPO: with specific test context and state.
	 *		Name : btnShipPO
	 * 		Text : Ship PO
	 * 		TabIndex : 15
	 * 		.class : System.Windows.Forms.Button
	 * 		.classIndex : 1
	 */
	protected GuiTestObject shipPObutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("shipPObutton"), anchor, flags);
	}
	
	/**
	 * tbcActions: with default state.
	 *		Name : tbcActions
	 * 		TabIndex : 0
	 * 		Text : 
	 * 		.class : System.Windows.Forms.TabControl
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tbcActionspageTabList() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tbcActionspageTabList"));
	}
	/**
	 * tbcActions: with specific test context and state.
	 *		Name : tbcActions
	 * 		TabIndex : 0
	 * 		Text : 
	 * 		.class : System.Windows.Forms.TabControl
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tbcActionspageTabList(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tbcActionspageTabList"), anchor, flags);
	}
	
	/**
	 * txtMsg: with default state.
	 *		Name : txtMsg
	 * 		TabIndex : 2
	 * 		.class : System.Windows.Forms.TextBox
	 * 		.classIndex : 0
	 */
	protected TextGuiSubitemTestObject txtMsgtext() 
	{
		return new TextGuiSubitemTestObject(
                        getMappedTestObject("txtMsgtext"));
	}
	/**
	 * txtMsg: with specific test context and state.
	 *		Name : txtMsg
	 * 		TabIndex : 2
	 * 		.class : System.Windows.Forms.TextBox
	 * 		.classIndex : 0
	 */
	protected TextGuiSubitemTestObject txtMsgtext(TestObject anchor, long flags) 
	{
		return new TextGuiSubitemTestObject(
                        getMappedTestObject("txtMsgtext"), anchor, flags);
	}
	
	/**
	 * txtOrder: with default state.
	 *		Name : txtOrder
	 * 		TabIndex : 8
	 * 		.class : System.Windows.Forms.TextBox
	 * 		.classIndex : 0
	 */
	protected TextGuiSubitemTestObject txtOrdertext() 
	{
		return new TextGuiSubitemTestObject(
                        getMappedTestObject("txtOrdertext"));
	}
	/**
	 * txtOrder: with specific test context and state.
	 *		Name : txtOrder
	 * 		TabIndex : 8
	 * 		.class : System.Windows.Forms.TextBox
	 * 		.classIndex : 0
	 */
	protected TextGuiSubitemTestObject txtOrdertext(TestObject anchor, long flags) 
	{
		return new TextGuiSubitemTestObject(
                        getMappedTestObject("txtOrdertext"), anchor, flags);
	}
	
	/**
	 * txtXML: with default state.
	 *		Name : txtXML
	 * 		TabIndex : 23
	 * 		.class : System.Windows.Forms.TextBox
	 * 		.classIndex : 0
	 */
	protected TextGuiSubitemTestObject txtXMLtext() 
	{
		return new TextGuiSubitemTestObject(
                        getMappedTestObject("txtXMLtext"));
	}
	/**
	 * txtXML: with specific test context and state.
	 *		Name : txtXML
	 * 		TabIndex : 23
	 * 		.class : System.Windows.Forms.TextBox
	 * 		.classIndex : 0
	 */
	protected TextGuiSubitemTestObject txtXMLtext(TestObject anchor, long flags) 
	{
		return new TextGuiSubitemTestObject(
                        getMappedTestObject("txtXMLtext"), anchor, flags);
	}
	
	/**
	 * XMLOnlyNoUpdates: with default state.
	 *		Name : chkXMLasn
	 * 		Text : XML Only (No Updates)
	 * 		TabIndex : 22
	 * 		.class : System.Windows.Forms.CheckBox
	 * 		.classIndex : 2
	 */
	protected ToggleGUITestObject xmlOnlyNoUpdatescheckBox() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("xmlOnlyNoUpdatescheckBox"));
	}
	/**
	 * XMLOnlyNoUpdates: with specific test context and state.
	 *		Name : chkXMLasn
	 * 		Text : XML Only (No Updates)
	 * 		TabIndex : 22
	 * 		.class : System.Windows.Forms.CheckBox
	 * 		.classIndex : 2
	 */
	protected ToggleGUITestObject xmlOnlyNoUpdatescheckBox(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("xmlOnlyNoUpdatescheckBox"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point lstPO_list object in the SUT.
	 */
	protected IFtVerificationPoint lstPO_listVP() 
	{
		return vp("lstPO_list");
	}
	protected IFtVerificationPoint lstPO_listVP(TestObject anchor)
	{
		return vp("lstPO_list", anchor);
	}
	
	

	protected PFSMainDialogModelHelper()
	{
		setScriptName("framework.pfstoolsmodel.PFSMainDialogModel");
	}
	
}

