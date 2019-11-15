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

/*
 * In this Class i'm reading excel file using maven
 */
public class ExcelFileOperation {
	static FileInputStream fileInputStream = null;
	static XSSFWorkbook workbook = null;
	static XSSFSheet worksheet = null;
	int rowNum;
	int colNum;

	public ExcelFileOperation(String filepath, int sheetIndex) {
		try {
			fileInputStream = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			System.out.println("Sorry! File not Found.");
			e.printStackTrace();
		}
		// Class used to read excel file and read the data
		try {
			workbook = new XSSFWorkbook(fileInputStream);

		} catch (IOException e) {
			System.out.println("File path not found");
			e.printStackTrace();
		}
		worksheet = workbook.getSheetAt(sheetIndex);
		
	}

	/*
	 * In this method i'm reading excel file using dependency of apache-poi
	 */

	public Iterator<Row> getRowsData() throws IOException {

		// iterating through rows and getting row number
		Iterator<Row> rows = worksheet.iterator();
		return rows;
	}

	// getting the no of rows in the sheet
	public int getNoOfRows() {
		int rowIndex = worksheet.getLastRowNum();

		return rowIndex + 1;
	}

	// getting no of columns in the sheet
	public int getNoOfColumns() {
		Iterator<Row> rowIterator = worksheet.rowIterator();
		int columns = 0;
		/**
		 * Escape the header row *
		 */
		if (rowIterator.hasNext()) {
			Row headerRow = rowIterator.next();
			// get the number of cells in the header row
			columns = headerRow.getPhysicalNumberOfCells();
		}
		return columns;
	}

	/**
	 * Method returns the cell data on the basis of sheet index, row and column
	 * numbers
	 * 
	 * @param sheetIndex
	 * @param colNum
	 * @param rowNUm
	 * @return cell data
	 * @throws NullCellValueException
	 */
	public String[][] getRowData(String filepath, int sheetIndex, String uniqueId) throws NullCellValueException {

		try {
			if (sheetIndex < 0)
				throw new NullCellValueException("Sheet index can't be less than zero");
			;
			try {
				fileInputStream = new FileInputStream(new File(filepath));
			} catch (FileNotFoundException e) {
				System.out.println("Sorry! File not Found.");
				e.printStackTrace();
			}
			// Class used to read excel file and read the data
			try {
				workbook = new XSSFWorkbook(fileInputStream);

			} catch (IOException e) {
				System.out.println("File path not found");
				e.printStackTrace();
			}
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

	public String getCellData(String filepath, int sheetIndex, String columnName, String uniqueId)
			throws NullCellValueException {
		try {

			if (sheetIndex < 0)
				throw new NullCellValueException("Sheet index can't be less than zero");
			;
			try {
				fileInputStream = new FileInputStream(new File(filepath));
			} catch (FileNotFoundException e) {
				System.out.println("Sorry! File not Found.");
				e.printStackTrace();
			}
			// Class used to read excel file and read the data
			try {
				workbook = new XSSFWorkbook(fileInputStream);

			} catch (IOException e) {
				System.out.println("File path not found");
				e.printStackTrace();
			}
			worksheet = workbook.getSheetAt(sheetIndex);

			rowNum = getRowNumber(uniqueId, worksheet);
			colNum = getcolumnNumber(columnName, worksheet);
			if (rowNum <= 0)
				throw new NullCellValueException("row no. is less than zero ");
			;
			XSSFRow row = worksheet.getRow(rowNum);
			if (row == null)
				throw new NullCellValueException("Cell value is null");
			if (colNum <= 0)
				throw new NullCellValueException("column no. is less than zero ");
			XSSFCell cell = row.getCell(colNum);

			return cell.toString();
		} catch (Exception e) {

			System.out.println("Cell data not found");
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

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

	public int getcolumnNumber(String columnName, XSSFSheet workSheet) throws IOException {
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

	public int getColumnSize(XSSFSheet worksheet) {

		Iterator<Row> rowIterator = worksheet.rowIterator();
		int columns = 0;
		/**
		 * Escape the header row *
		 */
		if (rowIterator.hasNext()) {
			Row headerRow = rowIterator.next();
			// get the number of cells in the header row
			columns = headerRow.getPhysicalNumberOfCells();
		}

		return columns;

	}

	public Iterator<Row> getDataOfRows(XSSFSheet workSheet) {
		// iterating through rows and getting row number
		Iterator<Row> rows = workSheet.iterator();
		return rows;
	}

}
