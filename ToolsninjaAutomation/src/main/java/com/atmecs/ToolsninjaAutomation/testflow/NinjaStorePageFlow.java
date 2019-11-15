package com.atmecs.ToolsninjaAutomation.testflow;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;

import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.helpers.Waits;
import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;

/**
 * class used to maintain the flow of the homepage of the tutorialsninja.com
 */
public class NinjaStorePageFlow {

	WebDriver driver;
	Locators locaters = new Locators();
	WebUtility WebUtility;

	public NinjaStorePageFlow(WebDriver driver) {
		this.driver = driver;
		WebUtility = new WebUtility(driver);
	}

	/**
	 * Method used to search the products to maintain the flow
	 */
	public void searchProduct(String productName) throws InterruptedException {
		if (productName.equalsIgnoreCase("")) {
		} else {
			BasicConfigurator.configure();
			WebUtility.clearField(Locators.getLocators("loc.text.searchButton"));
			WebUtility.clickAndSendText(Locators.getLocators("loc.text.searchButton"), productName);
			WebUtility.clickElement(Locators.getLocators("loc.btn.searchButton"));
			// WebUtility.explicitWait(Locators.getLocators("loc.text.productNameAfterSearch"));
		}
	}

	/**
	 * Method used to add the products to maintain the flow
	 * 
	 * @throws InterruptedException
	 */
	public void AddProduct() throws InterruptedException {

		WebUtility.scrollDownPage(0, 2000);
		Thread.sleep(2000);
		WebUtility.clickElement(Locators.getLocators("loc.btn.addToCart"));
	}

	/**
	 * Method used to manipulate the products quantity to maintain the flow
	 */
	public void manipulateQuantity(String quantity, int index) {
		new Waits(driver).pause(1500);
		WebUtility.clickElement(Locators.getLocators("loc.btn.goToCart"));
		WebUtility.explicitWait(Locators.getLocators("loc.text.productquantity").replace("dummytext", index + ""));
		WebUtility.clearField(Locators.getLocators("loc.text.productquantity").replace("dummytext", index + ""));
		WebUtility.clickAndSendText(Locators.getLocators("loc.text.productquantity").replace("dummytext", index + ""),
				quantity);
		WebUtility.explicitWait(Locators.getLocators("loc.btn.update").replace("dummytext", index + ""));
		WebUtility.clickElement(Locators.getLocators("loc.btn.update").replace("dummytext", index + ""));
		WebUtility.explicitWait(Locators.getLocators("loc.text.productquantity").replace("dummytext", index + ""));
	}

	/**
	 * Method used to remove the products to maintain the flow
	 */
	public void removeItem() {
		WebUtility.clickElement(Locators.getLocators("loc.btn.remove"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
