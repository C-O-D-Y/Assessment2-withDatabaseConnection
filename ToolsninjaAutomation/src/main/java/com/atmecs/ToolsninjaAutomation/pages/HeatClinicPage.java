package com.atmecs.ToolsninjaAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.constants.ValidatingData;
import com.atmecs.ToolsninjaAutomation.helpers.Waits;
import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;

/**
 * Class validates the different functionality of heat clinic website
 */
public class HeatClinicPage {

	WebDriver driver;
	Locators loc = new Locators();
	ValidatingData data;
	WebUtility WebUtility;
	Waits wait;

	public HeatClinicPage(WebDriver driver) {
		this.driver = driver;
		WebUtility = new WebUtility(driver);
		data = new ValidatingData();
		wait = new Waits(driver);
	}

	/**
	 * Validating every anchor Redirection is correct or not
	 */
	public void isUserRedirectionCorrect(String actualtitle) {
		if (actualtitle == "") {
		} else {
			// WebUtility.explicitWait("//title");
			String title = WebUtility.getTitle();
			System.out.println(title);
			Assert.assertEquals(title, actualtitle);
			System.out.println("Redirection is on the correct page");
		}
	}

	/**
	 * Validating every view mens page redirection message
	 */
	public void validateViewMensDisplayed() {
		String gender = WebUtility.getText(Locators.getLocators("loc.text.viewMens"));
		Assert.assertEquals(gender, data.getValidatingData("ViewMensMessage"));
		System.out.println("message displayed");

	}

	/**
	 * Validating every details of the cart present in the values
	 */
	public void validateCartDetails(String productName, String size, String personalizeName, String color, String price,
			String totalPrice) {
		
		String actualproductName = WebUtility.getText(Locators.getLocators("loc.text.cartProductName"));
		Assert.assertEquals(actualproductName, productName, "Product name doesnt match");

		String actualsize = WebUtility.getText(Locators.getLocators("loc.text.size"));

		Assert.assertEquals(actualsize, size, "size doesnt match");
		String name = WebUtility.getText(Locators.getLocators("loc.text.personalizename"));
		Assert.assertEquals(personalizeName, name, "Personalize name doesnt match");
		String actualcolor = WebUtility.getText(Locators.getLocators("loc.text.color"));
		Assert.assertEquals(actualcolor, color, "color doesnt match");
		String actualprice = WebUtility.getText(Locators.getLocators("loc.text.price"));
		Assert.assertEquals(actualprice, "$" + price, "individual price doesnt match");
		String actualTotalPrice = WebUtility.getText(Locators.getLocators("loc.text.totalPrice"));
		Assert.assertEquals(actualTotalPrice, "$" + totalPrice, "Total price  doesnt match");
	}

	/**
	 * Validating cart details when quantity is updated
	 */
	public void updatedCartAssertion() {
		WebUtility.clearField(Locators.getLocators("loc.inputBox.quantity"));

		WebUtility.clickAndSendText(Locators.getLocators("loc.inputBox.quantity"), data.getValidatingData("quantity"));
		// WebUtility.clickElement(Locators.getLocators("loc.btn.increaseQuantity"));
		WebUtility.explicitWait(Locators.getLocators("loc.btn.updateQuantity"));
		WebUtility.clickElement(Locators.getLocators("loc.btn.updateQuantity"));
		wait.pause(2000);
		String actualTotalPrice = WebUtility.getText(Locators.getLocators("loc.text.totalPrice"));
		Assert.assertEquals(actualTotalPrice, data.getValidatingData("updatedActualPrice"),
				"Total price  doesn't match");

	}

}
