package com.atmecs.ToolsninjaAutomation.logReports;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport implements ITestListener {

	public ExtentReports extentObject = new ExtentReports(FilePath.EXTENT_REPORT_FILE, true);
	public ExtentTest extentlogger;

	/**
	 * This getScreenshot method takes below parameters:
	 *
	 * @param driver
	 * @param testname and return the screenshot image destination path as a String
	 *                 .
	 * @return
	 * @throws Exception
	 */

	public String getScreenshot(WebDriver driver, String testname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = FilePath.FAILED_SCREENSHOT_FILE + testname + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentlogger = extentObject
				.startTest(result.getMethod().getMethodName() + ":" + Thread.currentThread().getName());
		extentlogger.log(LogStatus.INFO, "Test Starting..." + result.getMethod().getMethodName());
		ThreadPool.setTest(extentlogger);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentlogger.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
		String screenshotPath;
		try {
			screenshotPath = getScreenshot(ThreadPool.get(), result.getName());
			extentlogger.log(LogStatus.PASS, extentlogger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentlogger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName());
		extentlogger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
		String screenshotPath;
		try {
			screenshotPath = getScreenshot(ThreadPool.get(), result.getName());
			extentlogger.log(LogStatus.FAIL, extentlogger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentlogger.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
		extentObject.endTest(extentlogger);
		extentObject.flush();

	}

}