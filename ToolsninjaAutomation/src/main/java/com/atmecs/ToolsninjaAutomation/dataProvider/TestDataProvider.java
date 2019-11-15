package com.atmecs.ToolsninjaAutomation.dataProvider;

import org.testng.annotations.DataProvider;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;
import com.atmecs.ToolsninjaAutomation.constants.NullCellValueException;
import com.atmecs.ToolsninjaAutomation.utils.ExcelDataReader;
import com.atmecs.ToolsninjaAutomation.utils.ProvideExcelData;

/**
 * In this class, data is given from the dataprovider
 */
public class TestDataProvider {

	/**
	 * In this method, getting the data of the headers into object array and
	 * returning to the calling method
	 */

	Object[][] sheetData;
	static ExcelDataReader edr = new ExcelDataReader();

	@DataProvider(name = "products")
	public Object[][] getProducts() {
		ProvideExcelData provideData = new ProvideExcelData(FilePath.TESTDATA_FILE, 0);
		sheetData = provideData.provideExcelData();
		return sheetData;
	}

	/**
	 * In this method, getting the data inside the header(SERVICES) into object
	 * array and returning to the calling method
	 */
	@DataProvider(name = "wrongProducts")
	public Object[][] getWrongProducts() {
		ProvideExcelData provideData = new ProvideExcelData(FilePath.TESTDATA_FILE, 1);
		sheetData = provideData.provideExcelData();
		return sheetData;
	}

	@DataProvider(name = "headers")
	public Object[][] getheatClinicHeader() {
		ProvideExcelData provideData = new ProvideExcelData(FilePath.TESTDATA_FILE, 2);
		sheetData = provideData.provideExcelData();
		return sheetData;
	}

	@DataProvider(name = "cartDetails")
	public Object[][] getheatClinicCartDetails() {
		ProvideExcelData provideData = new ProvideExcelData(FilePath.TESTDATA_FILE, 3);
		sheetData = provideData.provideExcelData();
		return sheetData;
	}

	public static void main(String[] args) throws NullCellValueException {
		try {
			String cellData = edr.getCellData(FilePath.TESTDATA_FILE, "cody", "QUANTITY", "TD-3");
			System.out.println(cellData);
		} catch (NullCellValueException e) {

			System.out.println("Cell value is null");
			e.printStackTrace();
		}

		String[][] sa = edr.getRowData(FilePath.TESTDATA_FILE, "cody", "TD-2");
		for (String[] d : sa) {
			for (String s : d) {
				System.out.print(s + " ");
			}
		}

	}
}