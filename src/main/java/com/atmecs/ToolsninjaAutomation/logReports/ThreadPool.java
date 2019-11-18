package com.atmecs.ToolsninjaAutomation.logReports;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public class ThreadPool {

	private static ThreadPool thisPool = new ThreadPool();

	public final ThreadLocal<WebDriver> userThreadLocal = new ThreadLocal<WebDriver>();
	public final ThreadLocal<ExtentTest> extentThreadLocal = new ThreadLocal<ExtentTest>();

	public static ExtentTest getTest() {
		return thisPool.extentThreadLocal.get();
	}

	public static void setTest(ExtentTest test) {

		thisPool.extentThreadLocal.set(test);
	}

	public static WebDriver get() {
		return thisPool.userThreadLocal.get();
	}

	public static void set(WebDriver dInfo) {

		thisPool.userThreadLocal.set(dInfo);
	}
}