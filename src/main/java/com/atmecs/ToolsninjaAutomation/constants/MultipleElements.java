package com.atmecs.ToolsninjaAutomation.constants;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;
import com.atmecs.ToolsninjaAutomation.testBase.TestBase;

/**
 * Class use to take multiple web elements from the application
 */
public class MultipleElements extends TestBase {

	List<WebElement> webElements = new ArrayList<WebElement>();
	WebUtility WebUtility = new WebUtility(driver);

	/**
	 * Method gives the ELEMENTS of the web application by taking parameter
	 * 
	 * @param locater
	 */
	public void getElements() throws FileNotFoundException {

		webElements = WebUtility.getElementsList("//p[@class='custom-autoTxt']");
		try {
			for (int initial = 0; initial < webElements.size(); initial++) {
				String elements = webElements.get(initial).getText();

				// String links = webElements.get(initial).getAttribute("class");
				System.out.println(elements);
			}
		} catch (NullPointerException e) {
			System.out.println("File contains no data");
		}
	}
}
