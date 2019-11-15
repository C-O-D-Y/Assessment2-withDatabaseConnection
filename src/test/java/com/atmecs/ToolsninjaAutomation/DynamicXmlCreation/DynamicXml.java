package com.atmecs.ToolsninjaAutomation.DynamicXmlCreation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;
import com.atmecs.ToolsninjaAutomation.dataProvider.TestDataProvider;
import com.atmecs.ToolsninjaAutomation.utils.ReadPropertiesFile;

public class DynamicXml {

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "classNames", dataProviderClass = TestDataProvider.class)
	public void runTestFile(String className, String status) throws IOException {

		if (status.equalsIgnoreCase("Y")) {
			Properties props = ReadPropertiesFile.loadProperty(FilePath.CONFIG_FILE);

			List<String> browserNames = new ArrayList<String>();
			String[] arr = props.getProperty("browser").split(",");
			for (String name : arr) {
				browserNames.add(name);
			}

			browserNames.forEach(name -> System.out.println(name));
			String mode = props.getProperty("mode");
			XmlSuite xmlSuite = new XmlSuite();
			if (mode.equalsIgnoreCase("parallel")) {
				xmlSuite.setParallel("tests");
			}
			xmlSuite.setThreadCount(arr.length);

			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			for (String name : browserNames) {

				XmlTest xmlTest1 = new XmlTest(xmlSuite);
				Map<String, String> parameter1 = new HashMap<String, String>();
				parameter1.put("browser", name);
				
				xmlTest1.setParameters(parameter1);
				xmlTest1.setName("Test validate " + name);

				// code to read your testNg file

				XmlClass classes = new XmlClass();

				List<XmlClass> xmlClassList1 = new ArrayList<XmlClass>();
				classes.setName(className);
				xmlClassList1.add(classes);
				xmlTest1.setXmlClasses(xmlClassList1);
			}

			suites.add(xmlSuite);

			TestNG testng = new TestNG();
			List<Class<? extends ITestNGListener>> listenerClasses = new ArrayList<Class<? extends ITestNGListener>>();
			// listenerClasses.add((Class<? extends ITestNGListener>) Extent.class);
			testng.setListenerClasses(listenerClasses);

			testng.setXmlSuites(suites);

			testng.run();
		}
	}
}
