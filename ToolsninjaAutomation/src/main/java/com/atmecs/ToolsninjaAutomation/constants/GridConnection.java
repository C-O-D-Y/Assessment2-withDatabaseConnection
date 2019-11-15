package com.atmecs.ToolsninjaAutomation.constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * In this class, grid connection is being done
 */
public class GridConnection {
	 public URL url;
	String nodeurl;

	/**
	 * In this method, hub is created
	 */
	public WebDriver getConnection(WebDriver driver, String browser, String websiteURL, String nodeURL) {
		nodeurl = nodeURL;

		DesiredCapabilities capability = new DesiredCapabilities();

		try {
			url = new URL(nodeurl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		switch (browser) {
		case "firefox":
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			break;
		case "chrome":
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			break;
		case "ie":
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			break;
		default:
			System.out.println("Browser not found");
		}
		System.out.println("grid");
		capability.setPlatform(Platform.WINDOWS);
		driver = new RemoteWebDriver(url, capability);
		driver.get(websiteURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return driver;
	}
}