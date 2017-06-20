package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  April 30, 2013
 */
public class SpreadSheetUtil {
	
	public static FileInputStream m_excelInputStream = null;
	public static FileOutputStream m_excelOutputStream = null;
	public static POIFSFileSystem m_fileSystem = null;
	public static HSSFWorkbook m_workBook = null;
	public static HSSFSheet m_sheet = null;
	public static HSSFRow m_row = null;
	public static String m_XlsPath = null;
	public static int currentCellIndex = 0;
	public static int m_rowIndex=0;
	public SpreadSheetUtil(){
	}

	public SpreadSheetUtil(String xlsPath, int sheetIndex){
		
		m_XlsPath = xlsPath;
		readXLSFile(xlsPath);
		//setFileSystem();
		setWorkBook(xlsPath);
		setWorkSheet(sheetIndex);
		
	}
	public void createExcelFileForInegrationTest(String fileName){

		String xlspath="C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\integration\\";
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("Sheet1");
       
        HSSFRow row1 = firstSheet.createRow(0);
        HSSFRow row2 = firstSheet.createRow(1);
        HSSFRow row3 = firstSheet.createRow(2);
        HSSFRow row4 = firstSheet.createRow(3);
        
        /*
         * 1st row data
         */
        HSSFCell cellA = row1.createCell(0);
        cellA.setCellValue(new HSSFRichTextString("TC Name"));
        HSSFCell cellB = row1.createCell(1);
        cellB.setCellValue(new HSSFRichTextString("DateNTime"));
        
        HSSFCell cellC = row1.createCell(2);
        cellC.setCellValue(new HSSFRichTextString("Order Number"));
        
        HSSFCell cellD = row1.createCell(3);
        cellD.setCellValue(new HSSFRichTextString("EAN"));
        
        HSSFCell cellE = row1.createCell(4);
        cellE.setCellValue(new HSSFRichTextString("Order Status"));

        HSSFCell cellF = row1.createCell(5);
        cellF.setCellValue(new HSSFRichTextString("customerID"));

        /*
         * creating 2nd row
         */
        HSSFCell cell2A = row2.createCell(0);
        cell2A.setCellValue(new HSSFRichTextString("TC01_eBookPurchaseBN"));
        HSSFCell cell2B = row2.createCell(1);
        cell2B.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell2C = row2.createCell(2);
        cell2C.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell2D = row2.createCell(3);
        cell2D.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell2E = row2.createCell(4);
        cell2E.setCellValue(new HSSFRichTextString("x"));

        HSSFCell cell2F = row2.createCell(5);
        cell2F.setCellValue(new HSSFRichTextString("x"));

        /*
         * creating 3rd row
         */
        HSSFCell cell3A = row3.createCell(0);
        cell3A.setCellValue(new HSSFRichTextString("TC01_eBookPurchaseMS"));
        
        HSSFCell cell3B = row3.createCell(1);
        cell3B.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell3C = row3.createCell(2);
        cell3C.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell3D = row3.createCell(3);
        cell3D.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell3E = row3.createCell(4);
        cell3E.setCellValue(new HSSFRichTextString("x"));

        HSSFCell cell3F = row3.createCell(5);
        cell3F.setCellValue(new HSSFRichTextString("x"));

        /*
         * creating 4th row
         */
        HSSFCell cell4A = row4.createCell(0);
        cell4A.setCellValue(new HSSFRichTextString("TC01_eSubPurchaseUS"));
        
        HSSFCell cell4B = row4.createCell(1);
        cell4B.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell4C = row4.createCell(2);
        cell4C.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell4D = row4.createCell(3);
        cell4D.setCellValue(new HSSFRichTextString("x"));
        
        HSSFCell cell4E = row4.createCell(4);
        cell4E.setCellValue(new HSSFRichTextString("x"));

        HSSFCell cell4F = row4.createCell(5);
        cell4F.setCellValue(new HSSFRichTextString("x"));
        
        
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(xlspath+fileName));
            workbook.write(fos);
            m_XlsPath = xlspath+fileName;
            readXLSFile(m_XlsPath);
    		setWorkBook(m_XlsPath);
    		setWorkSheet(m_XlsPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	public SpreadSheetUtil(String xlsPath, String sheetCaption){
		
		m_XlsPath = xlsPath;
		readXLSFile(xlsPath);
		//setFileSystem();
		setWorkBook(xlsPath);
		setWorkSheet(sheetCaption);
		
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
		/*while (rows.hasNext ())
		{
			HSSFRow row = (HSSFRow)rows.next ();
			if(row.getRowNum() == rowIndex){
				m_row = row;
			}
		}*/
	}
	
	public Row readRow(String rowValue, int colIndex){
		Row tempRow = null;
		Iterator<Row> rows = m_sheet.rowIterator();
		for(Row row : m_sheet){
			if(row.getCell(colIndex).toString().contentEquals(rowValue)){
				m_row = (HSSFRow) row;
				tempRow = m_row;
				break;
			}
		}
		
		return tempRow;
	}
	
	public void createRow(int rowNumber){
		m_row = m_sheet.createRow(rowNumber);
		m_rowIndex = rowNumber;
	}
	
	public  int getColumnIndex(String columnName){
		
		int colIndex = -1;
		Cell currentCell = null;
		
		Row headerRow = m_sheet.getRow(0);
		for(Cell cell : headerRow){
			if(cell.getRichStringCellValue().toString().contentEquals(columnName)){
				
				currentCell = cell;
				break;
			}
		}
		
		colIndex = currentCell.getColumnIndex();
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
	
	
	public String getCellStringValue(int columnIndex){
		
		Cell cell = getCell(columnIndex);
		return cell.getRichStringCellValue().getString();
	}
	
	public String getCellStringValue(String columnName){
		
		int colIndex = getColumnIndex(columnName);
		//Cell cell = getCell(colIndex);
		Cell cell = m_row.getCell(colIndex);
		return cell.getRichStringCellValue().getString();
	}
	
	
	public Double getCellDoubleValue(int columnIndex){
		
		Cell cell = getCell(columnIndex);
		return Double.valueOf(cell.getRichStringCellValue().toString());
	}
	
	public Double getCellDoubleValue(String columnName){
		
		int colIndex = getColumnIndex(columnName);
		Cell cell = m_row.getCell(colIndex);
		return Double.valueOf(cell.getNumericCellValue());
	}
	
	public BigDecimal getCellBigDecimalValue(String columnName){
		
		int colIndex = getColumnIndex(columnName);
		Cell cell = m_row.getCell(colIndex);
		
		BigDecimal.valueOf(cell.getNumericCellValue()).toBigIntegerExact();
		return BigDecimal.valueOf(cell.getNumericCellValue());
	}
	
	public String setCellStringValue(String columnName, String value){
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
		/*else
		{
			m_row = m_sheet.createRow(m_rowIndex);
			cell = m_row.createCell(colIndex);
			cell.setCellValue(value);
			
		}*/
		
		try {
			m_excelOutputStream = new FileOutputStream(m_XlsPath);
			m_workBook.write(m_excelOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getRowCount()
	{   
		int rowcount = 0;
		
		rowcount = m_sheet.getLastRowNum() + 1;
		
		return rowcount;
	}

}
