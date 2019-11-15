package com.atmecs.ToolsninjaAutomation.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;

public class ReadWebTables {
	WebDriver driver;
	WebUtility utils;
	List<WebElement> tableRow;
	List<WebElement> tableColumn;
	List<Integer> colnum;
	String cellData = null;

	public ReadWebTables(WebDriver driver) {
		this.driver = driver;
		utils = new WebUtility(driver);
	}

	public int getRowCount(String tableLocator) {

		tableRow = utils.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		return tableRow.size();
	}

	public int getColumnCount(String tableLocator, int rowNum) {
		int rowCount = getRowCount(tableLocator);
		System.out.println(rowCount);
		if (rowCount < rowNum)
			return 0;
		else {
			tableColumn = utils.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
					.get(rowNum).findElements(By.tagName("td"));

			return tableColumn.size();
		}
	}

	public String getCellDataByColumnAndRowNumber(String tableLocator, int rowNumber, int columnnumber) {

		for (int index = 0; index < getRowCount(tableLocator); index++) {
			cellData = utils.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
					.get(rowNumber - 1).findElements(By.tagName("td")).get(columnnumber - 1).getText();
		}
		return cellData;
	}

	public String getPositionOfData(String tableLocator, String cellData) {

		int rowIndex = getRowCount(tableLocator);
		for (int index = 0; index < rowIndex; index++) {
			int columnIndex = getColumnCount(tableLocator, index);
			for (int colIndex = 0; colIndex < columnIndex; colIndex++) {
				String ActualcellData = utils.getElement(tableLocator).findElement(By.tagName("tbody"))
						.findElements(By.tagName("tr")).get(index).findElements(By.tagName("td")).get(colIndex)
						.getText();
				if (cellData.equals(ActualcellData)) {
					return "column Number is " + (colIndex + 1) + " and Row number is " + (index + 1);
				}
			}
		}
		return "No data found";
	}

	public void printTableData(String tableLocator) {

		int rowIndex = getRowCount(tableLocator);
		for (int index = 0; index < rowIndex; index++) {
			int columnIndex = getColumnCount(tableLocator, index);
			for (int colIndex = 0; colIndex < columnIndex; colIndex++) {
				cellData = utils.getElement(tableLocator).findElement(By.tagName("tbody"))
						.findElements(By.tagName("tr")).get(index).findElements(By.tagName("td")).get(colIndex)
						.getText();
				System.out.print(cellData + "	 ");
			}
			System.out.println("");
		}
	}

	public void printRowData(String tableLocator, int rowNumber) {
		int columnIndex = getColumnCount(tableLocator, rowNumber - 1);
		for (int colIndex = 0; colIndex < columnIndex; colIndex++) {
			cellData = utils.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
					.get(rowNumber - 1).findElements(By.tagName("td")).get(colIndex).getText();
			System.out.print(cellData + "	 ");
		}
	}
}
