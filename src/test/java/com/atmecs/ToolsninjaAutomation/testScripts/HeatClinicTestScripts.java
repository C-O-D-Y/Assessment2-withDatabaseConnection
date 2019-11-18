package com.atmecs.ToolsninjaAutomation.testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;
import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.constants.NullCellValueException;
import com.atmecs.ToolsninjaAutomation.constants.ValidatingData;
import com.atmecs.ToolsninjaAutomation.logReports.LogReport;
import com.atmecs.ToolsninjaAutomation.logReports.ThreadPool;
import com.atmecs.ToolsninjaAutomation.pages.HeatClinicPage;
import com.atmecs.ToolsninjaAutomation.testBase.TestBase;
import com.atmecs.ToolsninjaAutomation.testflow.HeatClinicPageFlow;
import com.atmecs.ToolsninjaAutomation.utils.ExcelDataReader;

public class HeatClinicTestScripts extends TestBase {
	LogReport log = new LogReport();

	int rowNumber;
	Locators locators = new Locators();
	HeatClinicPage home;
	ValidatingData data;
	HeatClinicPageFlow heatClinicFlow;
	ExcelDataReader excelData = new ExcelDataReader();

	@BeforeClass
	public void getUrl() {
		String url2 = baseClass.getProperty("URL2");
		driver.get(url2);
		ThreadPool.set(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	// redirection validation

	@Test(priority = 1)
	public void validateTitles() throws NullCellValueException {

		try {
			rowNumber = excelData.getNoOfRows(FilePath.TESTDATA_FILE, "heatClinicPageTitles");
			System.out.println(rowNumber);
		} catch (IOException e) {

			e.printStackTrace();
		}

		while (rowNumber - 1 > 0) {

			String header = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicPageTitles", "HEADERS",
					"TD-" + (rowNumber - 1));
			String title = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicPageTitles", "TITLES",
					"TD-" + (rowNumber - 1));

			// log = Logger.getLogger(HomePagetestscripts.class);
			// WebUtility.explicitWait(Locators.getLocators("loc.btn.services"));
			home = new HeatClinicPage(driver);
			data = new ValidatingData();
			heatClinicFlow = new HeatClinicPageFlow(driver);
			LogReport.getlogger();
			// log = extent.startTest("HomepageRedirection");
			heatClinicFlow.clickHeader(header);
			log.info("Starting Redirection validation");
			home.isUserRedirectionCorrect(title);
			log.info("Validation done, Redirection is on the correct page");
			rowNumber--;
		}
	}

	// selecting gender for the clothing
	@Test(priority = 2)
	public void selectGenderClothing() {
		heatClinicFlow.selectClothingGender(data.getValidatingData("clothing"));
		log.info("selecting clothing gender");
	}

	// choosing shirt
	@Test(priority = 4)
	public void chooseShirt() {
		log.info("select shirt and color with size");
		try {
			heatClinicFlow.selectShirt(data.getValidatingData("personalizeName"));
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		log.info("selected the corresponding size and color");
		log.info("clothing validation starting");
	}

	// validating mens clothing view mens message
	@Test(priority = 3)
	public void validateMensClothing() {
		log.info("validating viewmens page");
		home.validateViewMensDisplayed();
		log.info("Redirected to viewmens page");
	}

	// validating cart details
	@Test(priority = 5)
	public void validateCart() throws NullCellValueException {

		try {
			rowNumber = excelData.getNoOfRows(FilePath.TESTDATA_FILE, "heatClinicShirtConfig");
			System.out.println(rowNumber);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("validating cart details");
		while (rowNumber - 1 > 0) {

			String productName = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicShirtConfig", "PRODUCT NAME",
					"TD-" + (rowNumber - 1));
			String size = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicShirtConfig", "SIZE",
					"TD-" + (rowNumber - 1));
			String personalizeName = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicShirtConfig", "NAME",
					"TD-" + (rowNumber - 1));
			String color = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicShirtConfig", "COLOR",
					"TD-" + (rowNumber - 1));
			String price = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicShirtConfig", "PRICE",
					"TD-" + (rowNumber - 1));
			String TotalPrice = excelData.getCellData(FilePath.TESTDATA_FILE, "heatClinicShirtConfig", "TOTAL PRICE",
					"TD-" + (rowNumber - 1));

			home.validateCartDetails(productName, size, personalizeName, color, price, TotalPrice);
			rowNumber--;
		}
		log.info(" cart details are correct");
	}

	// validating updated cart details
	@Test(priority = 6)
	public void validateUpdatedGrandTotal() {
		log.info("validating updated grand total");
		home.updatedCartAssertion();
		log.info("Updated amount is correct, Functionality is working properly");
	}
}
