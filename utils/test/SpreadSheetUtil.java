package com.atg_sync_services.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SpreadSheetUtil
{
	public static FileInputStream m_excelInputStream = null;
	public static FileOutputStream m_excelOutputStream = null;
	public static POIFSFileSystem m_fileSystem = null;
	public static HSSFWorkbook m_workBook = null;
	public static HSSFSheet m_sheet = null;
	public static HSSFRow m_row = null;
	public static String m_XlsPath = null;
	public static int currentCellIndex = 0;
	public static int m_rowIndex=0;
	
	public SpreadSheetUtil(String xlsPath, int sheetIndex){
		
		m_XlsPath = xlsPath;
		readXLSFile(xlsPath);
		//setFileSystem();
		setWorkBook(xlsPath);
		setWorkSheet(sheetIndex);
		
	}
	

	public SpreadSheetUtil(String xlsPath, String sheetCaption){
		
		m_XlsPath = xlsPath;
		readXLSFile(xlsPath);
		//setFileSystem();
		setWorkBook(xlsPath);
		setWorkSheet(sheetCaption);
		
	}
	
	private HSSFCellStyle getCellFailStyle(){
		HSSFCellStyle style = m_workBook.createCellStyle();
		style.setFillBackgroundColor(HSSFColor.RED.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = m_workBook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		return style;
	}
	
	public  SpreadSheetUtil getInstance(String xlsPath, int sheetIndex){
		return new SpreadSheetUtil(xlsPath, sheetIndex);
	}
	
	public  void readXLSFile(String xlsPath){
		try{
			m_excelInputStream = new FileInputStream (xlsPath);			
		}
		catch (FileNotFoundException e){
			System.out.println ("File not found in the specified path.");
			e.printStackTrace ();
		}
	}
	
	public  void setFileSystem(){
		try {			
			m_fileSystem = new POIFSFileSystem (m_excelInputStream);			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void setWorkBook(String xlsPath){
		try {
			m_workBook = new HSSFWorkbook(m_excelInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void setWorkSheet(int sheetIndex){
		 m_sheet    = m_workBook.getSheetAt (sheetIndex);
	}
	public  void setWorkSheet(String sheetCaption){
		 m_sheet    = m_workBook.getSheet(sheetCaption);
	}
	
	public void createRow(int rowNumber){
		m_row = m_sheet.createRow(rowNumber);
		m_rowIndex = rowNumber;
	}
	
	public void readRow(int rowIndex){
		boolean found = false;
		m_row = null;
		Iterator<Row> rows = m_sheet.rowIterator();
		for(Row row : m_sheet){
			if(row.getRowNum() == rowIndex){
				m_row = (HSSFRow) row;
				m_rowIndex = m_row.getRowNum();
				found = true;
				break;
			}
		}
		
		if(!found){			
			createRow(rowIndex);
		}
	}
	
	public Row readRow(String rowValue, int colIndex){
		Row tempRow = null;
		Iterator<Row> rows = m_sheet.rowIterator();
		for(Row row : m_sheet){
			String cellValue="";
			Cell cell = row.getCell(colIndex);
			if(cell!=null)
			{
				cellValue = row.getCell(colIndex).toString();
				if(cellValue.contentEquals(rowValue)){
					m_row = (HSSFRow) row;
					tempRow = m_row;
					break;
				}
				
			}

		}

		return tempRow;
	}
	
	public  int getColumnIndex(String columnName){
		
		int colIndex = -1;
		Cell currentCell = null;
		boolean found = false;
		Row headerRow = m_sheet.getRow(0);
		//System.out.println("Header Row NoL " + headerRow.getRowNum());
		for(Cell cell : headerRow){
			//System.out.println(cell.getRichStringCellValue().toString());
			if(cell.getRichStringCellValue().toString().contentEquals(columnName)){
				
				currentCell = cell;
				found = true;
				break;
			}
		}
		if(found)
		{
			colIndex = currentCell.getColumnIndex();
		}
		return colIndex;
	}
	
	public  int getColumnIndex(int rowNum, String columnName){
		
		int colIndex = -1;
		Cell currentCell = null;
		
		Row headerRow = m_sheet.getRow(1);
		for(Cell cell : headerRow){
			if(cell.getRichStringCellValue().toString().contentEquals(columnName)){
				
				currentCell = cell;
				break;
			}
		}
		
		colIndex = currentCell.getColumnIndex();
		return colIndex;
	}
	
	public Cell getCell(int columnIndex){
		Cell currentCell = null;
		for(Cell cell : m_row){
			if(cell.getColumnIndex()==columnIndex){
				
				currentCell = cell;
				break;
			}
		}
		
		return currentCell;
	}
	
	public void removeCell(int rowIndex, String columnName){
		Row row = m_sheet.getRow(rowIndex);
		int colIndex = getColumnIndex(columnName);
		if(row!=null){
			Cell cell = getCell(colIndex);
			row.removeCell(cell);
		}
		
		try {
			m_excelOutputStream = new FileOutputStream(m_XlsPath);
			m_workBook.write(m_excelOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeRow(int rowIndex){
		Row row = m_sheet.getRow(rowIndex);
		
		if(row!=null){
			m_sheet.removeRow(row);
		}
		
		try {
			m_excelOutputStream = new FileOutputStream(m_XlsPath);
			m_workBook.write(m_excelOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCellStringValue(int columnIndex){
		
		Cell cell = getCell(columnIndex);
		return cell.getRichStringCellValue().getString();
	}
	
	public String getCellStringValue(String columnName){
		
		int colIndex = getColumnIndex(columnName);
		//Cell cell = getCell(colIndex);
		Cell cell = m_row.getCell(colIndex);
		if(cell!=null){
			return cell.getRichStringCellValue().getString();
		}
		else
		{
			return "";
		}
		
	}
	
	
	public Double getCellDoubleValue(int columnIndex){
		
		Cell cell = getCell(columnIndex);
		return Double.valueOf(cell.getRichStringCellValue().getString());
	}
	
	public Double getCellDoubleValue(String columnName){
		
		int colIndex = getColumnIndex(columnName);
		Cell cell = m_row.getCell(colIndex);
		if(cell!=null){
			return Double.valueOf(cell.getNumericCellValue());
		}
		else
		{
			return 0.0;
		}
		
	}
	
	public BigDecimal getCellBigDecimalValue(String columnName){
		
		int colIndex = getColumnIndex(columnName);
		Cell cell = m_row.getCell(colIndex);
		
		BigDecimal.valueOf(cell.getNumericCellValue()).toBigIntegerExact();
		return BigDecimal.valueOf(cell.getNumericCellValue());
	}
	
	public void setCellStringValue(String columnName, String value){
		int colIndex = getColumnIndex(columnName);
		Cell cell = null;
		if(m_row!=null){
			cell = m_row.getCell(colIndex);
			if(cell!=null){
				cell.setCellValue(value);
			}
			else
			{
				cell = m_row.createCell(colIndex);
				cell.setCellValue(value);
			}
		}
		
		try {
			m_excelOutputStream = new FileOutputStream(m_XlsPath);
			m_workBook.write(m_excelOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCellStringValue(String columnName, String value, short fontColor){
		int colIndex = getColumnIndex(columnName);
		Cell cell = null;
		if(m_row!=null){
			cell = m_row.getCell(colIndex);
			
			if(cell!=null){
				setCellFontColor(cell, fontColor);
				cell.setCellValue(value);
			}
			else
			{
				cell = m_row.createCell(colIndex);
				setCellFontColor(cell, fontColor);
				cell.setCellValue(value);
			}
		}
		
		try {
			m_excelOutputStream = new FileOutputStream(m_XlsPath);
			m_workBook.write(m_excelOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCellBackColor(Cell cell, short color){
		
		HSSFCellStyle style = m_workBook.createCellStyle();
		style.setFillBackgroundColor(color);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = m_workBook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		if(cell!=null){
			cell.setCellStyle(style);
		}
				
	}
	
	public void setCellFontColor(Cell cell, short color){
				
		HSSFCellStyle style = m_workBook.createCellStyle();	
		HSSFFont font = m_workBook.createFont();
		font.setColor(color);
		style.setFont(font);
		
		if(cell!=null){
			cell.setCellStyle(style);
		}
	}
	
	public int getRowCount()
	{   
		int rowcount = 0;
		
		rowcount = m_sheet.getLastRowNum() + 1;
		
		return rowcount;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
