package com.atmecs.ToolsninjaAutomation.testflow;

import org.openqa.selenium.WebDriver;

import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.helpers.Waits;
import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;

public class HeatClinicPageFlow {
	WebDriver driver;
	Locators locaters = new Locators();
	WebUtility WebUtility;
	Waits wait;

	public HeatClinicPageFlow(WebDriver driver) {
		this.driver = driver;
		WebUtility = new WebUtility(driver);
		wait = new Waits(driver);

	}

	public void clickHeader(String header) {
		if (header == null) {
		} else {
			WebUtility.clickElement(Locators.getLocators("loc.btn.headers").replace("[dummytext]", header));
		}
	}

	public void selectClothingGender(String shirt) {
		WebUtility.action(Locators.getLocators("loc.btn.headers").replace("[dummytext]", shirt));
		WebUtility.clickElement(Locators.getLocators("loc.btn.mensClothing"));
	}

	public void selectShirt(String name) throws InterruptedException {

		WebUtility.clickElement(Locators.getLocators("loc.link.shirt"));
		WebUtility.explicitWait(Locators.getLocators("loc.btn.color"));
		wait.pause(3000);
		WebUtility.clickElement(Locators.getLocators("loc.btn.color"));
		wait.pause(3000);
		boolean alert = isAlertPresent();
		if (alert == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		WebUtility.clickElement(Locators.getLocators("loc.btn.size"));
		wait.pause(3000);
		alert = isAlertPresent();
		if (alert == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}

		WebUtility.clickAndSendText(Locators.getLocators("loc.inputText.personalize"), name);
		WebUtility.clickElement(Locators.getLocators("loc.btn.buyNow"));
		wait.pause(1500);
		WebUtility.explicitWait(Locators.getLocators("loc.btn.cart"));
		WebUtility.clickElement(Locators.getLocators("loc.btn.cart"));
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (Exception e) {
			return false;
		} // catch
	}

}
