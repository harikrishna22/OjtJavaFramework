package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{

	Workbook wb ;
	//constructor for read excel path
	public ExcelFileUtil () throws Throwable
	{
		FileInputStream fi = new FileInputStream("E:\\Selenium_Evining\\ERP_Stock\\TestInput\\InputSheet.xlsx");
		wb = WorkbookFactory.create(fi);
	}
	//count no of rows in a sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//count no of columns in a row
	public int colCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//Reading data from cell
	public String getCellData(String sheetname , int row , int column)
	{
		String data ="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			//converting integer into string
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
	//write results into status column
	public void setCellData(String sheetname , int row ,int column , String status) throws Throwable
	{
		//get sheet from wb(workbook)
		Sheet ws = wb.getSheet(sheetname);
		//get row from sheet
		Row rownum = ws.getRow(row);
		//create column
		Cell cell = rownum.createCell(column);
		//writing status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			//create a cell style
			CellStyle style = wb.createCellStyle();
			//create a font
			Font font = wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.GREEN.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			//create a cell style
			CellStyle style = wb.createCellStyle();
			//create a font
			Font font = wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.RED.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("not Executed"))
		{
			//create a cell style
			CellStyle style = wb.createCellStyle();
			//create a font
			Font font = wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.BLUE.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
		}
		
		FileOutputStream fo = new FileOutputStream("E:\\Selenium_Evining\\ERP_Stock\\TestOutput\\hybrid.xlsx");
		wb.write(fo);
		fo.close();
	}
}
