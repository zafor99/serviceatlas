package framework.pfstoolsmodel;

import com.bn.qa.xobject.XGuiTestObject;
import com.bn.qa.xobject.XTestObject;
import com.bn.qa.xobject.XTopLevelSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;

import framework.AtlasScriptbase;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  August 21, 2013
 */
public abstract class PFSModelBase extends XTestObject
{
	protected PFSModelBase() {
	}
	

	public PFSModelBase(Subitem objectQuery) {
		super(objectQuery);
	}

	public PFSModelBase(Subitem objectQuery, boolean mappableObjectOnly) {
		super(objectQuery, mappableObjectOnly);
	}

	public PFSModelBase(String objectQueryString) {
		super(objectQueryString);
	}

	public PFSModelBase(String objectQueryString, boolean mappableObjectOnly) {
		super(objectQueryString, mappableObjectOnly);
	}

	public PFSModelBase(XTestObject rootTestObject) {
		super(rootTestObject);
	}

	public PFSModelBase(XTestObject rootTestObject, boolean mappableObjectOnly) {
		super(rootTestObject, mappableObjectOnly);
	}

	public PFSModelBase(XTestObject rootTestObject, Subitem p_SubItems) {
		super(rootTestObject, p_SubItems);
	}

	public PFSModelBase(XTestObject rootTestObject, Subitem subItems,
			boolean mappableObjectOnly) {
		super(rootTestObject, subItems, mappableObjectOnly);
	}

	public PFSModelBase(TestObject testObject) {
		super(testObject);
	}

	public PFSModelBase(TestObject rootTestObject, Subitem subItems) {
		super(rootTestObject, subItems);
	}

	public PFSModelBase(TestObject rootTestObject, Subitem subItems,
			boolean mappableObjectOnly) {
		super(rootTestObject, subItems, mappableObjectOnly);
	}
	
	
}
