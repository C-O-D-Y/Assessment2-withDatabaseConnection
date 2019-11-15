package com.atmecs.ToolsninjaAutomation.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
	WebDriver driver;

	public Waits(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isElementVisible(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception exception) {

			return false;
		}
		return true;
	}

	public boolean isElementClickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception exception) {

			return false;
		}
		return true;
	}

	public boolean isElementSelected(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception exception) {

			return false;
		}
		return true;
	}

	public boolean isElementRemoved(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception exception) {

			return false;
		}
		return true;
	}

	public void pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
