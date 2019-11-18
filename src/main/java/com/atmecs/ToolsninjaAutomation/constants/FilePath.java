package com.atmecs.ToolsninjaAutomation.constants;

import java.io.File;

/**
 * In this Class, All the file paths are saved
 */
public class FilePath {

	public final static String USER_HOME = System.getProperty("user.dir") + File.separator;
	public final static String RESOURCES_HOME = USER_HOME + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator;

	public final static String LIB_HOME = USER_HOME + "lib" + File.separator;

	public final static String CHROME_PATH = LIB_HOME + "chromedriver.exe";
	public final static String FIREFOX_PATH = LIB_HOME + "geckodriver.exe";
	public final static String IE_PATH = LIB_HOME + "IEDriver.exe";

	public final static String CONFIG_FILE = USER_HOME + File.separator + "config.properties";
	public static final String LOCATOR_FILE = RESOURCES_HOME + "locators" + File.separator
			+ "heatClinicAndNinjaStoreLocators.properties";
	public static final String VALIDATION_FILE = RESOURCES_HOME + "testData" + File.separator
			+ "heatClinicAndNinjaStoreValidateData.properties";
	public final static String TESTDATA_FILE = RESOURCES_HOME + "testData" + File.separator
			+ "heatClinicAndNinjaStoreTestData.xlsx";

	public static final String EXTENT_REPORT_FILE = USER_HOME + "test-output" + File.separator + "ExtentReport.html";
	public static final String EXTENT_CONFIG = USER_HOME + "extent-config.xml";
	public static final String FAILED_SCREENSHOT_FILE = USER_HOME + "FailedTestScreenShots" + File.separator;
	public final static String LOG4J_FILE = RESOURCES_HOME + "log4j" + File.separator + "log4j.properties";
	public final static int TIMEOUT_INSECONDS = 30;
	public static final String CLASSNAME = RESOURCES_HOME + "testData" + File.separator
			+ "heatClinicAndNinjaStoreTestData.xlsx";
	public final static String DATABASENAME="saurabh";
}
