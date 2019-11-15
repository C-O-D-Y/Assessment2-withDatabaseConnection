package com.atmecs.ToolsninjaAutomation.testScripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.atmecs.ToolsninjaAutomation.constants.FilePath;
import com.atmecs.ToolsninjaAutomation.utils.ExcelDataReader;

/**
 * This Class used to create Dynamic xml file.
 * 
 * @author arjun.santra
 *
 */
public class DynamicXML {

	ExcelDataReader excelReader;

	/**
	 * This method create and run dynamic xml file.
	 * 
	 * @param classname
	 * @param statusofclass
	 * @param browserName
	 * @throws ClassNotFoundException
	 */

//	public void runTestFile(String classname, String statusofclass ) throws ClassNotFoundException {
//		Properties props = new ReadPropertiesFile().loadProperty(FileConstant.CONFIG_FILE);
//
//		List<String> browsernames = new ArrayList<String>();
//		String[] array = props.getProperty("browser").split(",");
//		for (String browser : array) {
//			browsernames.add(browser);
//		}
//
//		if (statusofclass.equalsIgnoreCase("Y")) {
//			browsernames.forEach(browser -> System.out.println(browser));
//			XmlSuite xmlSuite = new XmlSuite();
//			xmlSuite.setName("suite");
//			xmlSuite.setVerbose(1);
//			xmlSuite.setParallel(ParallelMode.TESTS);
//			xmlSuite.setThreadCount(browsernames.size());
//
//			List<XmlSuite> suites = new ArrayList<XmlSuite>();
//
//			for (String browser : browsernames) {
//
//				XmlTest xmlTest1 = new XmlTest(xmlSuite);
//				Map<String, String> parameter1 = new HashMap<String, String>();
//				parameter1.put("browser", browser);
//				xmlTest1.setParameters(parameter1);
//				xmlTest1.setName("Test validate in " + browser);
//
//				// code to read your testNg file
//
//				XmlClass Class = new XmlClass();
//				Class.setName(classname);
//
//				List<XmlClass> xmlClassList = new ArrayList<XmlClass>();
//				xmlClassList.add(Class);
//
//				xmlTest1.setXmlClasses(xmlClassList);
//
//			}
//
//			suites.add(xmlSuite);
//
//			TestNG testng = new TestNG();
//
//			testng.setXmlSuites(suites);
//
//			testng.run();
//		}
//
//	}

	public List<XmlSuite> suiteXml(String[] className, String[] activityStatus, String[] browserName)
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("mysuite");
		xmlSuite.setParallel(ParallelMode.TESTS);
		xmlSuite.setThreadCount(15);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		for (int classNameIndex = 0; classNameIndex < className.length; classNameIndex++) {
			if (activityStatus[classNameIndex].equals("Y")) {
				String[] browser = browserName[classNameIndex].split(",");

				for (int browserCount = 0; browserCount < browser.length; browserCount++) {
					XmlTest xmlTest1 = new XmlTest(xmlSuite);
					Map<String, String> parameter1 = new HashMap<String, String>();
					parameter1.put("browser", browser[browserCount]);
					xmlTest1.setParameters(parameter1);
					xmlTest1.setName("Test validate " + browser[browserCount] + className[classNameIndex]);
					Class<?> class1 = Class.forName(className[classNameIndex]);
					XmlClass myClass = new XmlClass(class1);
					List<XmlClass> xmlClassList1 = new ArrayList<XmlClass>();
					xmlClassList1.add(myClass);
					xmlTest1.setXmlClasses(xmlClassList1);
				}
			}
		}
		suites.add(xmlSuite);
		return suites;
	}

	@Test
	public void xmlsuite() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		excelReader = new ExcelDataReader();
		String[] classes = excelReader.excelDataProviderArray(FilePath.CLASSNAME, "scriptClassNames", "CLASS-NAME");
		String[] activityStatus = excelReader.excelDataProviderArray(FilePath.CLASSNAME, "scriptClassNames",
				"RUNNING-STATUS");
		String[] browserName = excelReader.excelDataProviderArray(FilePath.CLASSNAME, "scriptClassNames", "BROWSER-NAME");
		System.out.println(classes[0] + classes[1]);
		List<XmlSuite> suites = suiteXml(classes, activityStatus, browserName);
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.run();
	}
}
