package com.atmecs.ToolsninjaAutomation.testScripts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;
import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.constants.NullCellValueException;
import com.atmecs.ToolsninjaAutomation.constants.ValidatingData;
import com.atmecs.ToolsninjaAutomation.databaseUtils.Fetch;
import com.atmecs.ToolsninjaAutomation.logReports.LogReport;
import com.atmecs.ToolsninjaAutomation.pages.NinjaStorePage;
import com.atmecs.ToolsninjaAutomation.testBase.TestBase;
import com.atmecs.ToolsninjaAutomation.testflow.NinjaStorePageFlow;
import com.atmecs.ToolsninjaAutomation.utils.ExcelDataReader;

/*
*Class validates the functionality of homepage functionality
*/
public class NinjaStoreTestScripts extends TestBase {
	LogReport log = new LogReport();
	int i = 1;
	int rowNumber;
	Locators locators = new Locators();
	NinjaStorePage home;
	ValidatingData data;
	NinjaStorePageFlow HomePageFlow;
	ExcelDataReader excelData = new ExcelDataReader();
	Fetch fetch;

	@BeforeClass
	public void getUrl() {
		String url = baseClass.getProperty("URL");
		driver.get(url);

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	/*
	 * Test validates the homepage redirection of the toolsninja.com
	 */
	@Test(priority = 1)
	public void homePageRedirection() {
		home = new NinjaStorePage(driver);
		data = new ValidatingData();
		HomePageFlow = new NinjaStorePageFlow(driver);
		LogReport.getlogger();
		// log = extent.startTest("HomepageRedirection");
		log.info("Starting Redirection validation");
		home.isRedirectionCorrect();
		log.info("Redirection is on the correct page");
		log.info("Starting the homepage testing");
	}

	// test validates the product searching and availability of the products
	@Test(priority = 2)
	public void searchProductAndCheckAvailability() throws InterruptedException, NullCellValueException, SQLException {
		String productName = null;
		String price = null, exTax = null;
		int count = 1;
		fetch = new Fetch("saurabh");
		while (count <= fetch.getRowCount("products")) {
			try {

				productName = fetch.fetchData("products", count, "products_name");
				price = fetch.fetchData("products", count, "price");
				exTax = fetch.fetchData("products", count, "ex_Tax");
				System.out.println("iusyfgctiywdghgvwsyfugedy7gve7yedgfccy7" + productName + price + exTax);
			} catch (SQLException e) {

				e.printStackTrace();
			}

			HomePageFlow.searchProduct(productName);
			home.isProductAvailable(productName);

			log.info("product name is correct");
			if (productName.equalsIgnoreCase("iphone")) {
				home.isDescriptionCorrect("descriptioniphone");

			}

			if (productName.equalsIgnoreCase("macbook air")) {
				home.isDescriptionCorrect("descriptionmac");
			}

			home.isPriceCorrect(price, exTax);
			log.info("product price and taxes is correct");
			log.info("Validation done");
			HomePageFlow.AddProduct();
			count++;
		}
	}

	// test validates grandTotal of the products
	@Test(priority = 3)
	public void validateGrandTotal() throws NullCellValueException, IOException, SQLException {

		int count = 1;
		String quantityOfProduct = null;
		fetch = new Fetch("saurabh");
		while (count <= fetch.getRowCount("products")) {
			quantityOfProduct = fetch.fetchData("products", count, "quantity");

			HomePageFlow.manipulateQuantity(quantityOfProduct, count);
			count++;
		}

	}

	@Test(priority = 4)
	public void checkGrandTotal() {
		home.isGrandTotalCorrect(data.getValidatingData("grandtotal1"));
	}

	// test validates grand total after removing the product
	@Test(priority = 5)
	public void validateGrandTotalAfterRemovingItem() {

		HomePageFlow.removeItem();
		home.isGrandTotalCorrect(data.getValidatingData("grandtotal2"));

	}

	// test validates the error message functionality when wrong product name is
	// searched
	@Test(priority = 6)
	public void validateErrorMessage() throws NullCellValueException, SQLException {
		String productName = null, message = null;
		int count = 1;
		try {
			rowNumber = excelData.getNoOfRows(FilePath.TESTDATA_FILE, "wrongProductsSpecification");
			System.out.println(rowNumber);
		} catch (IOException e) {

			e.printStackTrace();
		}
		fetch = new Fetch("saurabh");
		while (count <= fetch.getRowCount("wrongproducts")) {
			try {

				productName = fetch.fetchData("wrongproducts", count, "PRODUCT_NAME");
				message = fetch.fetchData("wrongproducts", count, "MESSAGE");

			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				HomePageFlow.searchProduct(productName);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			home.isMessageDisplayed(message);
			count++;
		}
	}
}
