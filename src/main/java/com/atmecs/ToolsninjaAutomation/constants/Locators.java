package com.atmecs.ToolsninjaAutomation.constants;

import java.io.IOException;
import java.util.Properties;

import com.atmecs.ToolsninjaAutomation.utils.ReadPropertiesFile;

/**
 * In this class, property file is loaded for getting locators.
 */
public class Locators {
	static Properties PageProperty;

	/**
	 * In this constructor, reading of property file is being done.
	 */
	public Locators() {
		try {
			PageProperty = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * In this method, locator data from the property file is taken and returning
	 * the value.
	 * 
	 * @param key
	 */
	public static String getLocators(String key) {

		String value = PageProperty.getProperty(key);
		return value;
	}
}
