package com.atmecs.ToolsninjaAutomation.helpers;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;

/**
 * Class contains methods of different functionalities used in web testing
 */
public class WebUtility {

	/**
	 * method click the Element using the fluent wait concepts ignoring the
	 * ElementClickInterceptedException.
	 * 
	 * @param driver
	 * @param xpath
	 */
	static WebElement element;
	static List<WebElement> elements;
	WebDriver driver;
	Actions action;

	public WebUtility(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
	}

	public WebElement selectSelectorType(String locator) {

		String[] locatorsPart = locator.split(";");
		switch (locatorsPart[0].toUpperCase()) {

		case "ID": {

			element = driver.findElement(By.id(locatorsPart[1]));

			break;
		}

		case "NAME": {

			element = driver.findElement(By.name(locatorsPart[1]));

			break;
		}

		case "CLASS": {

			element = driver.findElement(By.className(locatorsPart[1]));

			break;
		}

		case "LINK_TEXT": {

			element = driver.findElement(By.linkText(locatorsPart[1]));

			break;
		}

		case "PARTIAL_LINK_TEXT": {

			element = driver.findElement(By.partialLinkText(locatorsPart[1]));

			break;
		}

		case "TAG_NAME": {

			element = driver.findElement(By.tagName(locatorsPart[1]));

			break;
		}

		case "CSS": {
			// System.out.println("css");
			element = driver.findElement(By.cssSelector(locatorsPart[1]));

			break;
		}

		case "XPATH": {

			element = driver.findElement(By.xpath(locatorsPart[1]));

			break;
		}

		default: {
			System.out.println("Selector not found");
		}

		}
		return element;
	}

	/**
	 * method click the Element using the fluent wait concepts ignoring the
	 * ElementClickInterceptedException.
	 *
	 * @param locator
	 */

	public void clickElement(String locator) {

		selectSelectorType(locator);
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.ignoring(ElementClickInterceptedException.class).pollingEvery(Duration.ofSeconds(2))
				.withTimeout(Duration.ofSeconds(30));

		fluentWait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement element = selectSelectorType(locator);
				element.click();

				return true;
			}
		});
	}

	/**
	 * method select the dropdown and select the Element by visible text, using the
	 * fluent wait concepts ignoring the ElementClickInterceptedException.
	 * 
	 * @param locator
	 */
	public void selectDropdown(String locator, String text) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.ignoring(ElementClickInterceptedException.class).pollingEvery(Duration.ofSeconds(2))
				.withTimeout(Duration.ofSeconds(30));
		fluentWait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement element = selectSelectorType(locator);
				Select trip = new Select(element);

				trip.selectByVisibleText(text);
				return true;
			}

		});
	}

	/**
	 * method click the blank fields and send the text to be entered
	 * 
	 * @param locator
	 * @param text
	 */
	public void clickAndSendText(String locator, String text) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.ignoring(ElementClickInterceptedException.class).pollingEvery(Duration.ofSeconds(2))
				.withTimeout(Duration.ofSeconds(30));

		fluentWait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement element = selectSelectorType(locator);
				element.sendKeys(text);
				;
				return true;
			}
		});
	}

	public String getText(String locator) {
		WebElement element = selectSelectorType(locator);
		String text = element.getText();
		return text;
	}

	/**
	 * method takes parameters as
	 * 
	 * @param locator
	 * @return element on the web page
	 */
	public WebElement getElement(String locator) {
		WebElement element = selectSelectorType(locator);
		return element;
	}

	/**
	 * method takes parameters as
	 * 
	 * @param locatorIsDisplayed
	 * @return a boolean value for the displayed element on the web page
	 */
	public boolean isDisplayed(String locatorIsDisplayed) {
		boolean isDisplayed = false;
		try {
			isDisplayed = selectSelectorType(locatorIsDisplayed).isDisplayed();
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/**
	 * method takes parameters as
	 * 
	 * @param locatorIsSelected
	 * @return a boolean value for the selected element on the web page
	 */
	public boolean isSelected(String locatorIsSelected) {
		boolean isSelected = false;
		try {
			isSelected = selectSelectorType(locatorIsSelected).isSelected();
		} catch (Exception e) {
			isSelected = false;
		}
		return isSelected;
	}

	/**
	 * method takes parameters as
	 * 
	 * @param locator and uses the explicit wait concept
	 * @return a boolean value after checking the visibility of the.
	 */
	public boolean isElementVisible(String locator) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.visibilityOf(selectSelectorType(locator)));
		} catch (Exception exception) {

			return false;
		}
		return true;
	}

	/**
	 * method takes parameters as
	 * 
	 * @param xpath
	 * @return the list of elements
	 */

	public List<WebElement> getElementsList(String locator) {
		System.out.println(locator);
		String[] locatorsPart = locator.split(";");
		System.out.println(locatorsPart[0]);
		System.out.println(locatorsPart[1]);
		switch (locatorsPart[0]) {

		case "ID": {
			System.out.println("ID");
			elements = driver.findElements(By.id(locatorsPart[1]));

			break;
		}

		case "NAME": {
			System.out.println("name");
			elements = driver.findElements(By.name(locatorsPart[1]));

			break;
		}

		case "CLASS": {
			System.out.println("class");
			elements = driver.findElements(By.className(locatorsPart[1]));

			break;
		}

		case "LINK_TEXT": {
			System.out.println("link");
			elements = driver.findElements(By.linkText(locatorsPart[1]));

			break;
		}

		case "PARTIAL_LINK_TEXT": {
			System.out.println("parftial");
			elements = driver.findElements(By.partialLinkText(locatorsPart[1]));

			break;
		}

		case "TAG_NAME": {
			System.out.println("tag_name");
			elements = driver.findElements(By.tagName(locatorsPart[1]));

			break;
		}

		case "CSS": {
			System.out.println("css");
			elements = driver.findElements(By.cssSelector(locatorsPart[1]));

			break;
		}

		case "XPATH": {
			System.out.println("xpath");
			elements = driver.findElements(By.xpath(locatorsPart[1]));

			break;
		}

		default: {
			System.out.println("Getting elements with this Selector is not available");
		}

		}
		return elements;
	}

	/**
	 * method scroll down the window on the web page
	 * 
	 * @param horizontal index
	 * @param vertical   index
	 */

	public void scrollDownPage(int horizontalIndex, int verticalIndex) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scroll = "window.scrollBy(" + horizontalIndex + "," + verticalIndex + ")";
		js.executeScript(scroll);
	}

	/**
	 * In this method, mouse over operation of the mouse is done
	 * 
	 * @param locator
	 */
	public void action(String locator) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("System interrupted");
		}

		WebElement element = selectSelectorType(locator);
		action.moveToElement(element).build().perform();
	}

	/**
	 * In this method,Input boxes entered texts are cleared
	 * 
	 * @param locator
	 */
	public void clearField(String locator) {
		selectSelectorType(locator).clear();
	}

	/**
	 * Method gets the title of the current page
	 */
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	/**
	 * Method waits for the time until the xpath of the element is clickable
	 * 
	 * @param locator
	 */
	public void explicitWait(String locator) {

		WebDriverWait wait = new WebDriverWait(driver, FilePath.TIMEOUT_INSECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(selectSelectorType(locator)));
	}

	/**
	 * Method scrolls down the window resolution until the view of webelement is not
	 * found
	 * 
	 * @param locator
	 */
	public void scrollIntoview(String locator) {
		WebElement element = selectSelectorType(locator);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Method gets the attribute of the web element
	 * 
	 * @param locator
	 * @param attribute
	 */
	public String getAttribute(String locator, String attribute) {
		WebElement element = selectSelectorType(locator);
		String value = element.getAttribute(attribute);
		return value;

	}

	/**
	 * Method gets the attribute of the web element
	 * 
	 * @param locator
	 */
	public void scrollByTimeout(String locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.ignoring(ElementClickInterceptedException.class).pollingEvery(Duration.ofSeconds(3))
				.withTimeout(Duration.ofSeconds(90));
		fluentWait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				action.sendKeys(Keys.SPACE);
				scrollDownPage(0, -300);
				WebElement element = selectSelectorType(locator);
				element.click();
				return true;
			}

		});
	}

	/**
	 * method select the dropdown and select the Element by index, using the fluent
	 * wait concepts ignoring the ElementClickInterceptedException.
	 * 
	 * @param index
	 * @param locator
	 */
	public void selectDropdownByIndex(String locator, int index) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.ignoring(ElementClickInterceptedException.class).pollingEvery(Duration.ofSeconds(2))
				.withTimeout(Duration.ofSeconds(30));
		fluentWait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement element = selectSelectorType(locator);
				Select trip = new Select(element);

				trip.selectByIndex(index);
				return true;
			}

		});
	}

	/**
	 * Method sets the attribute value of the web element
	 * 
	 * @param locator
	 * @param xDirection offset
	 */
	public void setAttributeValue(String locator, int xOffset) {

		WebElement slider = selectSelectorType(locator);
		Actions move = new Actions(driver);
		// move.moveToElement(slider).clickAndHold().moveByOffset(xOffset,
		// 0).release().build().perform();
		move.clickAndHold(slider).dragAndDropBy(slider, xOffset, 0).build().perform();
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Method perform keyboard action for spacebar button
	 */
	public void scrollBySpacebar() {

		action.sendKeys(Keys.SPACE).build().perform();
	}
}