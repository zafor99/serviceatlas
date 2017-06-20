package framework.sapmodel;

import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;

import framework.sapmodel.SAPApplicationModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPGuiTableControl extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	private static Integer cols = 0;
	private static Integer rows = 0;
	public static SAPGuiTableControlTestObject table = null;
	private static String[][] tableData = null;
	
	public SAPGuiTableControl(Subitem subitem){
		appModel = new SAPApplicationModel();
		table = (SAPGuiTableControlTestObject)appModel.getSAPSession().find(subitem)[0];
		setColsAndRows();
		createTableStructure();
		createHeader();
	}
	
	public SAPGuiTableControl(Subitem subitem,String colIndex, String colNames){
		appModel = new SAPApplicationModel();
		table = (SAPGuiTableControlTestObject)appModel.getSAPSession().find(subitem)[0];
		setColsAndRows();
		createTableStructure();
		createHeader();
		setHeaderColumn(colIndex,colNames);
	}
	
	public SAPGuiTableControl(Subitem subitem,int totalCols, int totalRows, String colIndex, String colNames){
		appModel = new SAPApplicationModel();
		table = (SAPGuiTableControlTestObject)appModel.getSAPSession().find(subitem)[0];
		setColsAndRows(totalCols, totalRows);
		createTableStructure();
		createHeader();
		setHeaderColumn(colIndex,colNames);
	}
	
	public SAPGuiTableControlTestObject table(){
		return table;
	}
	
	private void setHeaderColumn(String colIndex, String colNames){
		String[] colIndexArray;
		String[] colNamesArray;
		
		colIndexArray = colIndex.split(",");
		colNamesArray = colNames.split(",");
		
		for(int i=0; i<colIndexArray.length; i++){
			tableData[0][Integer.valueOf(colIndexArray[i])] = colNamesArray[i];
		}
	}
	
	private TestObject[] getTableCellObjects(){
		TestObject[] cellObjects = null;
		
		cellObjects = table.find(atList(atChild(".class",xRegex("Gui.*"))));
		
		return cellObjects;
	}
	
	public void setColsAndRows(){
		TestObject[] cellObjects = null;
		cellObjects = getTableCellObjects();
		String id = "";
		
		int totalRows = 0;
		int totalCols = 0;
		//ArrayList<Integer> rows = new ArrayList<Integer>();
		Integer rowMax = 0;
		Integer colMax = 0;
		for (int i=0; i<cellObjects.length;i++){
			id = cellObjects[i].getProperty("Id").toString();
			System.out.println("Id : " + id);
			System.out.println(id.length());
			System.out.println(id.indexOf("["));
			String colIndex = id.substring(id.indexOf("[")+1,id.indexOf(","));
			System.out.println("Col : " + colIndex);
			String rowIndex = id.substring(id.indexOf(",")+1,id.indexOf("]"));
			System.out.println("Row : " + rowIndex);
			if(Integer.valueOf(colIndex)>totalCols){
				totalCols = Integer.valueOf(colIndex);
			}
			
			if(Integer.valueOf(rowIndex)>totalRows){
				totalRows = Integer.valueOf(rowIndex);
			}
		}
		
		cols = totalCols + 1;
		rows = totalRows + 1;
		
		System.out.println("Cols : " + cols);
		System.out.println("Rows : " + rows);
	}
	
	public void setColsAndRows(int totalCols, int totalRows){
		cols = totalCols;
		rows = totalRows;
	}
	
	public void createTableStructure(){
		
		tableData = new String[rows+1][cols];
		
	}
	
	public void createHeader(){
		TestObject[] cellObjects = null;
		cellObjects = getTableCellObjects();
		String[] header = new String[cols];
		for (int i=0; i<cellObjects.length;i++){
			String id = cellObjects[i].getProperty("Id").toString();
			//System.out.println(id.length());
			//System.out.println(id.indexOf("["));
			String colIndex = id.substring(id.indexOf("[")+1,id.indexOf(","));

			tableData[0][Integer.valueOf(colIndex)]=cellObjects[i].getProperty(".priorLabel").toString();
		}
		
		System.out.println();
	}
	
	private Integer[] getColRowIndex(TestObject to){
		
		String id=null;
		String[] idList;
		int col,row;
		
		col = 0;
		row = 0;
		
		id = to.getProperty("Id").toString();
		id = id.substring(id.indexOf("[")+1,id.indexOf("]"));
		idList = id.split(",");
		col = Integer.valueOf(idList[0]);
		row =  Integer.valueOf(idList[1]);
		
		return new Integer[] {col,row};
	}
	
	private Integer[] getColRowCount(){
/*		TestObject[] cellObjects = null;
		cellObjects = getTableCellObjects();
		int col,row;
		String id=null;
		String[] idList;
		col = 0;
		row = 0;
		for(int i=0;i<cellObjects.length;i++){
			id = cellObjects[i].getProperty("Id").toString();
			id = id.substring(id.indexOf("[")+1,id.indexOf("]"));				
			idList = id.split(",");
			if(Integer.valueOf(idList[0]) > col){
				col = Integer.valueOf(idList[0]);
			}
			
			if(Integer.valueOf(idList[1])>row){
				row = Integer.valueOf(idList[1]);
			}
		}*/
		
		return new Integer[] {cols,rows};
	}
	
	
	public int getColumCount(){
		return cols;
	}
	
	public int getRowCount(){
		return rows;
	}
	
	public GuiTestObject getCellTestObject(int colIndex, int rowIndex){
		TestObject[] tos = null;
		GuiTestObject to = null;
		tos = table().find(atChild("Id",xRegex("/.*\\[" + colIndex + "," + rowIndex + "\\]")));
		if(tos.length>0){
			to = new GuiTestObject(tos[0]);
		}
		return to;
	}
	
	public GuiTestObject getCellTestObject(String colName, int rowIndex){
		return  new GuiTestObject( table().find(atChild(".priorLabel",colName,"Id",xRegex("/.*\\[.*," + rowIndex + "\\]")))[0]);
	}
	
	public TestObject[] getRowTestObjects(int rowIndex){
		TestObject[] tos = new TestObject[cols];
		for(int i=0;i<cols;i++){
			tos[i] = getCellTestObject(i,rowIndex);
		}
		//tos = table().find(atChild("Id",xRegex("/.*\\[.*," + rowIndex + "\\]")));
		return tos;
	}
	
	public TestObject[] getRowTestObjects(int colIndex, String cellText){
		TestObject[] tos = null;
		TestObject cell = null;
		int rowIndex;
		cell = table().find(atChild("Id",xRegex("/.*\\[" + colIndex + ",.*\\]"),"Text",cellText))[0];
		System.out.println(cell.getProperty("Text"));
		if(cell!=null){
			rowIndex = getColRowIndex(cell)[1];
			tos = getRowTestObjects(rowIndex);
		}
		else
		{
			System.out.println(cellText + "not found in collumn " + colIndex);
		}
		return tos;
	}
	
	public TestObject[] getRowTestObjects(String colName, String cellText){
		TestObject[] tos = null;
		TestObject cell = null;
		int rowIndex;
		cell = table().find(atChild(".priorLabel",colName,"Id",xRegex("/.*\\[.*,.*\\]"),"Text",cellText))[0];
		if(cell!=null){
			rowIndex = getColRowIndex(cell)[1];
			tos = getRowTestObjects(rowIndex);
		}
		else
		{
			System.out.println(cellText + "not found in collumn " + colName);
		}
		return tos;
	}
	
	public TestObject[] getColumnTestObjects(String colName){
		TestObject[] tos = null;
		tos = table().find(atChild(".priorLabel",colName,"Id",xRegex("/.*\\[.*,.*\\]")));
		return tos;
	}
	
	public TestObject[] getColumnTestObjects(int colIndex){
		TestObject[] tos = null;
		tos = table().find(atChild("Id",xRegex("/.*\\[" + colIndex + ",.*\\]")));
		return tos;
	}
	
	public String getCellText(int colIndex, int rowIndex){
		TestObject cell = null;
		String cellText = "";
		cell = getCellTestObject(colIndex, rowIndex);
		if(cell!=null){
			if(cell.getProperty(".class").toString().contentEquals("GuiCheckBox")){
				cellText =cell.getProperty("Selected").toString();
			}
			else if(cell.getProperty(".class").toString().contains("TextField")){
				cellText = cell.getProperty("Text").toString();
			}
		}
		
		return cellText;
	}
	
	public String getCellText(String colName, int rowIndex){
		TestObject cell = null;
		String cellText = "";
		cell = getCellTestObject(colName, rowIndex);
		if(cell!=null){
			if(cell.getProperty(".class").toString().contentEquals("GuiCheckBox")){
				cellText =cell.getProperty("Selected").toString();
			}
			else if(cell.getProperty(".class").toString().contains("TextField")){
				cellText = cell.getProperty("Text").toString();
			}
		}
		
		return cellText;
	}
	
	public String[][] getRowTextData(int rowIndex){
		TestObject[] tos = null;
		String[][] rowText = null;
		
		tos = getRowTestObjects(rowIndex);
		rowText = new String[2][tos.length];
		
		for(int i=0; i<tos.length;i++){
			rowText[0][i] = tos[i].getProperty(".priorLabel").toString();
			
			if(tos[i].getProperty(".class").toString().contentEquals("GuiCheckBox")){
				rowText[1][i] =tos[i].getProperty("Selected").toString();
			}
			else if(tos[i].getProperty(".class").toString().contains("TextField")){
				rowText[1][i] = tos[i].getProperty("Text").toString();
			}
		}
		
		return rowText;
	}
	
	public String[][] getRowTextData(int colIndex, String cellText){
		TestObject[] tos = null;
		String[][] rowText = null;
		
		tos = getRowTestObjects(colIndex,cellText);
		rowText = new String[2][tos.length];
		
		for(int i=0; i<tos.length;i++){
			
			rowText[0][i] = tos[i].getProperty(".priorLabel").toString();
			
			if(tos[i].getProperty(".class").toString().contentEquals("GuiCheckBox")){
				rowText[1][i] =tos[i].getProperty("Selected").toString();
			}
			else if(tos[i].getProperty(".class").toString().contains("TextField")){
				rowText[1][i] = tos[i].getProperty("Text").toString();
			}
		}
		
		return rowText;
	}
	
	public String[][] getRowTextData(String colName, String cellText){
		TestObject[] tos = null;
		String[][] rowText = null;
		
		tos = getRowTestObjects(colName,cellText);
		rowText = new String[2][tos.length];
		
		for(int i=0; i<tos.length;i++){
			
			rowText[0][i] = tos[i].getProperty(".priorLabel").toString();
			
			if(tos[i].getProperty(".class").toString().contentEquals("GuiCheckBox")){
				rowText[1][i] =tos[i].getProperty("Selected").toString();
			}
			else if(tos[i].getProperty(".class").toString().contains("TextField")){
				rowText[1][i] = tos[i].getProperty("Text").toString();
			}
		}
		
		return rowText;
	}
	
	public String[] getColumnTextData(int colIndex){
		TestObject[] tos = null;
		String[] rowText = null;
		
		tos = getColumnTestObjects(colIndex);
		rowText = new String[tos.length];
		
		for(int i=0; i<tos.length;i++){
			if(tos[i].getProperty(".class").toString().contentEquals("GuiCheckBox")){
				rowText[i] =tos[i].getProperty("Selected").toString();
			}
			else if(tos[i].getProperty(".class").toString().contains("TextField")){
				rowText[i] = tos[i].getProperty("Text").toString();
			}
		}
		
		return rowText;
	}
	
	public String[] getColumnTextData(String colName){
		TestObject[] tos = null;
		String[] rowText = null;
		
		tos = getColumnTestObjects(colName);
		rowText = new String[tos.length];
		
		for(int i=0; i<tos.length;i++){
			if(tos[i].getProperty(".class").toString().contentEquals("GuiCheckBox")){
				rowText[i] = tos[i].getProperty("Selected").toString();
			}
			else if(tos[i].getProperty(".class").toString().contains("TextField")){
				rowText[i] = tos[i].getProperty("Text").toString();
			}
		}
		
		return rowText;
	}
	
	public String[][] getTableTextData(){
		TestObject[] tos = null;
		//String[][] tableData = null;
		int row = getRowCount();
		int index;
		//tableData = new String[row+1][];
		
		index = 1;
		for(int i=0; i<rows;i++){
			
			
			for(int j=0;j<cols;j++){
				tableData[index][j] = getCellText(j, i);
			}
			index++;
			
		}
		
		return tableData;
	}
}
