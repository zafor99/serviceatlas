package framework.sapmodel;

import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiUserAreaTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 17, 2013
 */
public class SAPUserTableModel extends SAPModelBase
{
	private SAPGuiUserAreaTestObject table = null;
	private SAPTopLevelTestObject dialog = null;
	private TestObject[] tableObjects = null;
	private SAPGuiTextTestObject[][] tableGuiObjects = null;
	private String[] tableRowList = null;
	private String rowList;
	private String headerColMap;
	private String rowColMap;
	public SAPUserTableModel(SAPTopLevelTestObject dialog, String rowList, String headerColMap, String rowColMap){
		SAPGuiTextTestObject[][] tempTableObjects = null;
		this.dialog = dialog;
		this.rowList = rowList;
		this.headerColMap = headerColMap;
		this.rowColMap = rowColMap;		
		createTable();		
	}
	
	private void createTable(){
		SAPGuiTextTestObject[][] tempTableObjects = null;
		tableRowList = rowList.split(",");
		tempTableObjects = new SAPGuiTextTestObject[tableRowList.length][headerColMap.split(",").length];
		//tableGuiObjects = new SAPGuiTextTestObject[tableRowList.length][headerColMap.split(",").length];
		dialog.setFocus();
		int rows = tableRowList.length;
		for(int i=0;i<rows;i++){
			int cols = headerColMap.split(",").length;
			for(int j=0; j<headerColMap.split(",").length; j++){
				
				if(i==0){
					String id = "/lbl[" + headerColMap.split(",")[j] + "," + tableRowList[i] + "]";
					TestObject[] tos = dialog.find(atList(atChild("Id","/usr"),atChild("Id",id)), true);
					if(tos.length>0){
						tempTableObjects[i][j] = (SAPGuiTextTestObject)tos[0];
					}					
					
				}
				else
				{
					String id = "/lbl[" + rowColMap.split(",")[j] + "," + tableRowList[i] + "]";
					TestObject[] tos = dialog.find(atList(atChild("Id","/usr"),atChild("Id",id)), true);
					if(tos.length>0){
						tempTableObjects[i][j] = (SAPGuiTextTestObject)tos[0];
					}					
				}
			}
		}
		
		int activeRowCount = getActiveRowCount(tempTableObjects);
		tableGuiObjects = new SAPGuiTextTestObject[activeRowCount][headerColMap.split(",").length];
		
		for(int i=0;i<activeRowCount;i++){
			for(int j=0; j<headerColMap.split(",").length; j++){
				tableGuiObjects[i][j] = tempTableObjects[i][j];
			}
		}
	}
	
	private int getActiveRowCount(SAPGuiTextTestObject[][] tableGuiObjects){
		int count=0;
		for(int i=0;i<tableGuiObjects.length;i++){
			boolean rowActive = false;
			for(int j=0; j<tableGuiObjects[0].length; j++){
				if(tableGuiObjects[i][j]!=null){
					rowActive = true;
				}
			}
			if(rowActive){
				count++;
			}
		}
		return count;
	}
	
	public SAPGuiTextTestObject getCell(int row, int col)
	{
		return tableGuiObjects[row][col];
	}

	public int getRowCount()
	{
		
		return getActiveRowCount(tableGuiObjects);
	}

	public String[][] getTableData(){
		
		String[][] data = null; 
		
		data = new String[tableGuiObjects.length][tableGuiObjects[0].length];
		
		for(int i=0; i<tableGuiObjects.length;i++){
			for(int j=0;j<tableGuiObjects[i].length;j++){
				data[i][j] = tableGuiObjects[i][j].getProperty("Text").toString();
			}			
		}
		
		return data;
		
	}
}
