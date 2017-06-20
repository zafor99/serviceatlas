package framework.sapmodel;

import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlTreeTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  December 17, 2013
 */
public class SAPCustomTreeTableModel 
{
	private SAPGuiCtrlTreeTestObject tree = null;
	
	
	public SAPCustomTreeTableModel(SAPGuiCtrlTreeTestObject tree){
		this.tree = tree;
	}
	
	private String[] getColumnTitles(){
		
		String[] columnTitles = null;
		
		columnTitles = tree.getColumnTitles();
		
		return columnTitles;
	}
	
	private String[] getColumnNames(){
		
		String[] columnTitles = null;			
		columnTitles = tree.getColumnNames();			
		return columnTitles;
	}
	
	public String[] getTableRowData(String key){
					
		String[] names = getColumnNames();
		String[] data = null;

		data = new String[names.length-1];

		for(int j=1; j<names.length;j++){
			data[j-1] = tree.getItemText(key,names[j]);				
		}
		return data;
	}
	
	private ArrayList<String> treverseTreeNodeKey(String key, ArrayList<String> list){
		String keyList[] = null;
		list.add(key);

		if(tree.getNodeChildrenCount(key)>0){
			keyList = tree.getSubNodesCol(key);
			if(keyList !=null && keyList.length>0){
				for(int i=0; i<keyList.length;i++){
					list = treverseTreeNodeKey(keyList[i], list);
				}
			}
		}

		return list;
	}
	
	private ArrayList<String> treverseTreeNodeText(String key, ArrayList<String> list, boolean child, String tab){
		String keyList[] = null;
		if(child){
			tab = tab + "        ";
			list.add( tab + tree.getNodeTextByKey(key));
		}
		else
		{
			list.add(tree.getNodeTextByKey(key));
		}
		if(tree.getNodeChildrenCount(key)>0){
			keyList = tree.getSubNodesCol(key);
			if(keyList !=null && keyList.length>0){
				for(int i=0; i<keyList.length;i++){
					list = treverseTreeNodeText(keyList[i], list, true, tab);
				}
			}
			
		}

		return list;
	}
	
	public String[] getTreeNodeKeyData(){
		
		ArrayList<String> list = new ArrayList<String>();
		String[] data = null;
		
		String[] temp = tree.getNodesCol();

		System.out.println("item is :"+temp[0]);
		list = treverseTreeNodeKey(temp[0], list);
		data = new String[list.size()];
		
		for(int i=0; i<list.size(); i++)
		{
			data[i] = list.get(i);
		}
		return data;
	}
	
	public String[] getTreeNodeTextData(){
		
		ArrayList<String> list = new ArrayList<String>();
		String[] data = null;
	
		String[] temp = tree.getNodesCol();

		list = treverseTreeNodeText(temp[0], list, false, "");
		data = new String[list.size()];
		
		for(int i=0; i<list.size(); i++)
		{
			data[i] = list.get(i);
		}
		
		return data;
	}
	
	public String[][] getTreeTableData(){
		
		String[] keys = getTreeNodeKeyData();
		String[] texts = getTreeNodeTextData();
		String[][] data = new String[keys.length][];
		for(int i=0; i<keys.length;i++){
			
			String[] rowData = getTableRowData(keys[i]);
			data[i] = new String[rowData.length + 1];
			
			int index=0;
			for(int j=0; j<rowData.length+1;j++)
			{
				if(j==0){
					data[i][j] = texts[i];
				}
				else
				{
					data[i][j] = rowData[index];
					index++;
				}
			}
		}
		
		return data;
	}
}
