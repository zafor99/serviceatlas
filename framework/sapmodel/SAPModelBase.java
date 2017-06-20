package framework.sapmodel;

import com.bn.qa.xobject.XTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  August 01, 2012
 */
public abstract class SAPModelBase extends XTestObject
{
	public SAPModelBase() {
		
	}

	public SAPModelBase(Subitem objectQuery) {
		super(objectQuery);		
	}

	public SAPModelBase(Subitem objectQuery, boolean mappableObjectOnly) {
		super(objectQuery, mappableObjectOnly);
	}

	public SAPModelBase(String objectQueryString) {
		super(objectQueryString);
	}

	public SAPModelBase(String objectQueryString, boolean mappableObjectOnly) {
		super(objectQueryString, mappableObjectOnly);
	}

	public SAPModelBase(XTestObject rootTestObject) {
		super(rootTestObject);
	}

	public SAPModelBase(XTestObject rootTestObject, boolean mappableObjectOnly) {
		super(rootTestObject, mappableObjectOnly);
	}

	public SAPModelBase(XTestObject rootTestObject, Subitem p_SubItems) {
		super(rootTestObject, p_SubItems);
	}

	public SAPModelBase(XTestObject rootTestObject, Subitem subItems,
			boolean mappableObjectOnly) {
		super(rootTestObject, subItems, mappableObjectOnly);
	}

	public SAPModelBase(TestObject testObject) {
		super(testObject);
	}

	public SAPModelBase(TestObject rootTestObject, Subitem subItems) {
		super(rootTestObject, subItems);
	}

	public SAPModelBase(TestObject rootTestObject, Subitem subItems,
			boolean mappableObjectOnly) {
		super(rootTestObject, subItems, mappableObjectOnly);
	}
}
