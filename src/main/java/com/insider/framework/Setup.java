package com.insider.framework;

import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.testng.TestNG;

import com.insider.common.Constants;

public class Setup {

	public void executeTest() {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(Constants.TESTNG);
		testng.setTestSuites(suites);
		testng.run();
	}
}
