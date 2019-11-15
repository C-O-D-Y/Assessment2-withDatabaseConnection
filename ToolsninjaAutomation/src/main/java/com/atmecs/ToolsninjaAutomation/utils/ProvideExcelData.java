package com.atmecs.ToolsninjaAutomation.utils;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Class provides the Excel cell data to the calling method
 */
public class ProvideExcelData {

	Object[][] sheetData;
	ExcelFileOperation excelOperation;

	// Constructor provide loading of the file
	// parameters filepath
	public ProvideExcelData(String filepath, int sheetIndex) {
		excelOperation = new ExcelFileOperation(filepath, sheetIndex);
	}

	/**
	 * Method returns the object array containing the cell data
	 */
	public Object[][] provideExcelData() {

		Iterator<Row> rowIterator = null;
		try {
			rowIterator = excelOperation.getRowsData();

			int noOfRows = excelOperation.getNoOfRows();
			System.out.println(noOfRows);
			int noOfColumns = excelOperation.getNoOfColumns();
			System.out.println(noOfColumns);
			sheetData = new Object[noOfRows-1][noOfColumns];

		} catch (IOException e) {
			System.out.println("Exception");
		}
		int i = 0;
		rowIterator.next();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			int j = 0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				sheetData[i][j] = cell.toString();
				System.out.println(cell);

				j++;
			}
			i++;
		}
		return sheetData;
	}
}