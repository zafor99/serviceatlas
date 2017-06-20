package framework.crmmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

public class CRMCustomTableModel {
	
	private WebElement tableElement = null;
	private WebDriver driver = null;
	
	public CRMCustomTableModel(WebDriver driver, WebElement element, By by){
		tableElement = element.findElement(by);
		this.driver = driver;
	}
	
	public WebElement selectAllButton(){
		WebElement button = null;
		try {
			button = tableElement.findElement(By.xpath("thead/tr/th[@title='Selection']/div/a/img"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		return button;
	}
	
	public List <WebElement> getColumnHeaders(){
		List <WebElement> headers = null;
		 
		headers = tableElement.findElements(By.xpath("thead/tr/th"));
		
		return headers;
	}
	
	public WebElement getColHeaderCell(int colIndex){
		WebElement cell = null;
		
		try {
			cell = tableElement.findElement(By.xpath("thead/tr/th[" + colIndex + "]/div/span"));

		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		return cell;
		
	}
	
	public int getRowCount(){
		int rowCount = 0;
		List <WebElement> rows = null;
		try {
			rows = tableElement.findElements(By.xpath("tbody/tr"));
			rowCount = rows.size();
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}

		return rowCount;
	}
	
	public int getColCount(){
		
		return getColumnHeaders().size();
	}
	
	public boolean isRowSelected(int rowIndex){
		
		boolean selected = false;
		
		WebElement cell = null;
		WebElement element = null;
		
		try {
			cell = getCell(rowIndex, 1);
			if(cell!=null){
				element = cell.findElement(By.xpath("div/a"));
				if(element!=null){
					String title = element.getAttribute("title");
					if(title.contains("Deselect")){
						selected = true;
					}
				}
			}
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		
		return selected;
	}
	
	public int getColIndexByColName(String colName){
		int index = 0;
		List <WebElement> headers =  getColumnHeaders();
		int cols = headers.size();
		
		for(int i=1; i<cols;i++){
			WebElement cell = null;
			try {
				cell = headers.get(i).findElement(By.xpath("div/span"));
				if(cell!=null){
					if(cell.getText().contentEquals(colName)){
						index = i+1;
						break;
					}
				}
			} catch (NoSuchElementException ex) {
				//ex.printStackTrace();
			}catch (NullPointerException ex){
			
			}
			
		}
		
		return index;
	}
	
	public int getRowIndexByCellText(int colIndex, String cellText){
		
		int rowIndex = 0;
		List <WebElement> rows =  null;
		try {
			rows = tableElement.findElements(By.xpath("tbody/tr"));
			for(int i=0; i < rows.size();i++){
				WebElement cell = null;
				cell = rows.get(i).findElement(By.xpath("td["+colIndex+"]/span|td["+colIndex+"]/a"));
				if(cell!=null){
					System.out.println(cell.getText());
					if(cell.getText().contentEquals(cellText)){
						rowIndex = i+1;
						break;
					}
				}
				
			}
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		return rowIndex;
	}
	
	public int getRowIndexByCellText(String colName, String cellText){
		int rowIndex = 0;
		int colIndex = 0;
		
		colIndex = getColIndexByColName(colName);
		if(colIndex > 0){
			rowIndex = getRowIndexByCellText(colIndex, cellText);
		}
		else
		{
			RationalTestScript.logError(colName + " not found in table");
		}
		return rowIndex;
	}
	
	
	public List <WebElement> getRowCells(int rowIndex){
		List <WebElement> rowCells = null;
		try {
			rowCells = tableElement.findElements(By.xpath("tbody/tr[" + rowIndex + "]/td"));	
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
			
		return rowCells;
	}
	
	public WebElement getCell(int rowIndex, int colIndex){
		WebElement cell = null;
		try {
			cell = tableElement.findElement(By.xpath("tbody/tr[" + rowIndex + "]/td[" + colIndex + "]"));	
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		
		return cell;
	}
	
	public WebElement getCell(int rowIndex, String colName){
		WebElement cell = null;
		int colIndex = getColIndexByColName(colName);
		if(colIndex > 0)
		{
			cell = tableElement.findElement(By.xpath("//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]"));	
		}
		else
		{
			RationalTestScript.logError(colName + " not found in table");
		}
		return cell;
	}
	
	public WebElement getCellByCellText(int colIndex, String cellText){
		
		WebElement cell = null;
		try {
			cell = tableElement.findElement(By.xpath("//tbody/tr/td[" + colIndex + "]/span[contains(text(),'" + cellText + "')]/..|//tbody/tr/td[" + colIndex + "]/a[contains(text(),'" + cellText + "')]/.."));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		return cell;
	}
	
	public WebElement getRowSelectionCell(int rowIndex){
		
		WebElement cell = null;
		WebElement element = null;
		
		try {
			cell = getCell(rowIndex, 1);
			element = cell.findElement(By.xpath("div/a"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		
		
		
		return element;
	}
	
	public WebElement getCellByCellText(String colName, String cellText){
		
		WebElement cell = null;
		int colIndex = 0;
		colIndex = getColIndexByColName(colName);
		if(colIndex > 0)
		{
			cell = getCellByCellText(colIndex, cellText);
		}
		else
		{
			RationalTestScript.logError(colName + " not found in table");
		}
		
		return cell;
	}
	
	public String getCellText(int rowIndex, int colIndex){
		
		String cellText = "";
		WebElement cell = null;
		WebElement element = null;
		try {
			cell = getCell(rowIndex, colIndex);
			if(cell!=null){
				element = cell.findElement(By.xpath("span|a"));
				if(element!=null){
					cellText = element.getText();
				}
			}
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}

		return cellText;
	}
	
	public String getCellText(int rowIndex, String colName){
		String cellText = "";
		int colIndex = 0;
		
		colIndex = getColIndexByColName(colName);
		if(colIndex > 0)
		{
			cellText = getCellText(rowIndex, colIndex);
		}
		else
		{
			RationalTestScript.logError(colName + " not found in table");
		}
		
		return cellText;
	}
	
	public String[][] getTableCellData(){
		
		String[][] cellData = null;
		List <WebElement> rows = null;
		
		try {
			rows = tableElement.findElements(By.xpath("thead/tr|tbody/tr"));
			cellData = new String[rows.size()][];
			
			for(int i=0; i<rows.size();i++){
				boolean rowSelected = false;
				List <WebElement> cols = null;
				cols = rows.get(i).findElements(By.xpath("th//span|th//a|td/span|td//a"));
				cellData[i] = new String[cols.size()];
				rowSelected = false;
				for(int j=0; j<cols.size();j++){
					if(i>0 && j==0){
						rowSelected = isRowSelected(i);
						cellData[i][j] = rowSelected?"Selected":"";
					}else
					{
						cellData[i][j] = cols.get(j).getText();
					}
					
				}
			}
			
			
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		
		return cellData;
		
	}
}
