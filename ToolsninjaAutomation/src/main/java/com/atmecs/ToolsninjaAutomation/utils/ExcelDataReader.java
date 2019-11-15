package com.atmecs.ToolsninjaAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atmecs.ToolsninjaAutomation.constants.NullCellValueException;

public class ExcelDataReader {
	static FileInputStream fileInputStream = null;
	static XSSFWorkbook workbook = null;
	static XSSFSheet worksheet = null;
	int rowNum;
	int colNum;
	int sheetIndex;

	/**
	 * Method returns the row data on the basis of filepath, sheet index, and
	 * UniqueId
	 * 
	 * @param sheetName
	 * @param filepath
	 * @param uniqueId- It is the id which differentiate the rows among them
	 * @return cell data
	 * @throws NullCellValueException
	 */
	public String[][] getRowData(String filepath, String sheetName, String uniqueId) throws NullCellValueException {

		try {

			try {
				fileInputStream = new FileInputStream(new File(filepath));
			} catch (FileNotFoundException e) {
				System.out.println("Sorry! File not Found.");
				e.printStackTrace();
			}
			// Class used to read excel file and read the data
			try {
				workbook = new XSSFWorkbook(fileInputStream);
				sheetIndex = workbook.getSheetIndex(sheetName);
			} catch (IOException e) {
				System.out.println("File path not found");
				e.printStackTrace();
			}
			if (sheetIndex < 0)
				throw new NullCellValueException("Sheet index can't be less than zero");
			;
			worksheet = workbook.getSheetAt(sheetIndex);
			rowNum = getRowNumber(uniqueId, worksheet);
			XSSFRow row = worksheet.getRow(rowNum);
			if (row == null)
				throw new NullCellValueException("Cell value is null");
			String rowData[][] = new String[1][getColumnSize(worksheet)];
			int j = 0;
			rowNum = getRowNumber(uniqueId, worksheet);
			XSSFRow rowValues = worksheet.getRow(rowNum);
			Iterator<Cell> cellIterator = rowValues.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				rowData[0][j] = cell.toString();
				j++;
			}

			return rowData;

		} catch (Exception e) {

			System.out.println("Cell data not found");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method returns the cell data on the basis of sheet index, row and column
	 * numbers
	 * 
	 * @param filepath
	 * @param sheetName
	 * @param columnName
	 * @param UniqueId-  It is the id which differentiate the rows among them
	 * @return cell data
	 * @throws NullCellValueException
	 */
	public String getCellData(String filepath, String sheetName, String columnName, String uniqueId)
			throws NullCellValueException {
		try {

			try {
				fileInputStream = new FileInputStream(new File(filepath));
			} catch (FileNotFoundException e) {
				System.out.println("Sorry! File not Found.");
				e.printStackTrace();
			}
			// Class used to read excel file and read the data
			try {
				workbook = new XSSFWorkbook(fileInputStream);
				sheetIndex = workbook.getSheetIndex(sheetName);
			} catch (IOException e) {
				System.out.println("File path not found");
				e.printStackTrace();
			}
			if (sheetIndex < 0)
				throw new NullCellValueException("Sheet index can't be less than zero");
			;
			worksheet = workbook.getSheetAt(sheetIndex);

			rowNum = getRowNumber(uniqueId, worksheet);
			colNum = getColumnNumber(columnName, worksheet);
			if (rowNum <= 0)
				throw new NullCellValueException("row no. is less than zero ");
			;
			XSSFRow row = worksheet.getRow(rowNum);
			if (row == null)
				throw new NullCellValueException("Cell value is null");
			if (colNum <= 0)
				throw new NullCellValueException("column no. is less than zero ");
			XSSFCell cell = row.getCell(colNum - 1);

			return cell.toString();
		} catch (Exception e) {

			System.out.println("Cell data not found");
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	/**
	 * Method returns the number of rows on the basis unique Id and worksheet
	 * 
	 * @param workSheet
	 * @param UniqueId- It is the id which differentiate the rows among them
	 * @return RowNumber-specific row number on the basis of uniqueId or returns 0
	 *         when no specific row number is found.
	 * @throws IOException
	 */
	public int getRowNumber(String uniqueId, XSSFSheet workSheet) throws IOException {

		Iterator<Row> rowIterator = getDataOfRows(workSheet);
		rowIterator.next();
		int i = 0;
		while (rowIterator.hasNext()) {
			i++;
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				if (cell.toString().equalsIgnoreCase(uniqueId)) {
					return i;
				}

			}
		}
		return 0;
	}

	/**
	 * Method returns the number of columns on the basis Column name and worksheet
	 * 
	 * @param workSheet
	 * @return ColumnNumber-specific column number on the basis of Column name
	 *         returns 0 when no specific column number is found.
	 * @throws IOException
	 */
	public int getColumnNumber(String columnName, XSSFSheet workSheet) throws IOException {
		Iterator<Row> rowIterator = getDataOfRows(workSheet);
		int i = 0;
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				i++;
				if (cell.toString().equalsIgnoreCase(columnName)) {
					return i;
				}

			}
		}
		return 0;
	}

	/**
	 * Method returns Columns size on the basis worksheet
	 * 
	 * @param worksheet
	 * @return Column Size- Number of columns in the worksheet
	 */
	public int getColumnSize(XSSFSheet worksheet) {

		Iterator<Row> rowIterator = worksheet.rowIterator();
		int columns = 0;

		if (rowIterator.hasNext()) {
			Row headerRow = rowIterator.next();
			// get the number of cells in the header row
			columns = headerRow.getPhysicalNumberOfCells();
		}
		return columns;
	}

	/**
	 * Method returns data of the rows in the worksheet
	 * 
	 * @param workSheet
	 * @return rows- data of the rows
	 */
	public Iterator<Row> getDataOfRows(XSSFSheet workSheet) {
		// iterating through rows and getting row number
		Iterator<Row> rows = workSheet.iterator();
		return rows;
	}

	/**
	 * Method returns number of rows in the worksheet
	 * 
	 * @param sheetName
	 * @param filepath
	 * @return no of rows
	 */
	public int getNoOfRows(String filepath, String sheetName) throws IOException {
		fileInputStream = new FileInputStream(new File(filepath));
		workbook = new XSSFWorkbook(fileInputStream);
		sheetIndex = workbook.getSheetIndex(sheetName);
		worksheet = workbook.getSheetAt(sheetIndex);
		int rowIndex = worksheet.getLastRowNum();

		return rowIndex + 1;
	}

	public String[] excelDataProviderArray(String path, String sheetName, String columnname) throws IOException {
		int sheetIndex = 0;
		fileInputStream = new FileInputStream(new File(path));
		workbook = new XSSFWorkbook(fileInputStream);
		sheetIndex = workbook.getSheetIndex(sheetName);
		worksheet = workbook.getSheetAt(sheetIndex);
		int rowCount = getNoOfRows(path, sheetName);
		String array[] = new String[rowCount - 1];
		int count = 0, matchcolumn = 0;
		for (Row row : worksheet) {
			if (row.getRowNum() == 0) {
				for (Cell cell : row) {
					if (cell.toString().contentEquals(columnname)) {
						matchcolumn = cell.getColumnIndex();
					}
				}
			} else {
				for (Cell cell : row) {
					if (matchcolumn == cell.getColumnIndex()) {
						String Data = cell.toString();
						array[count] = Data;
					}
				}
				count++;
			}

		}
		return array;
	}
}
