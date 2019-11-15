package com.atmecs.ToolsninjaAutomation.logReports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;

/**
 * In this class, log reports is made.
 */
public class LogReport {
	public Logger logger = null;

	/*
	 * constructor used to get the logger to load the configuration file
	 */
	public LogReport() {

		// calling the method, to load the config file
		getlogger();
		logger = Logger.getLogger(LogReport.class.getName());
	}

	/**
	 * method loads the config file from the filepath
	 */
	public static void getlogger() {
		PropertyConfigurator.configure(FilePath.LOG4J_FILE);
	}

	/**
	 * the method takes input as string message
	 * 
	 * @param message is printed on the console
	 */
	public void info(String message) {
		logger.info(message);

	}
}
