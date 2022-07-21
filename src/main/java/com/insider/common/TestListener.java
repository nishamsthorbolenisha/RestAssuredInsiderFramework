
package com.insider.common;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	Reporter extent;

	public void onStart(ITestContext context) {
		System.out.println("onStart");
		extent = new Reporter();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");

		String reportName = result.getInstance().getClass().getSimpleName();
		String methodName = result.getMethod().getMethodName();
		extent.createReport(reportName, methodName);
	}

	public void onFinish(ITestContext context) {

		Set<ITestResult> passedTestCasesCount = context.getPassedTests().getAllResults();
		Constants.passedTestCasesCount = String.valueOf(passedTestCasesCount.size());

		Set<ITestResult> failedTestCasesCount = context.getFailedTests().getAllResults();
		Constants.failedTestCasesCount = String.valueOf(failedTestCasesCount.size());

		Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
		Constants.skippedTestCasesCount = String.valueOf(skippedTests.size());

		Constants.totalTestCasesCount = String
				.valueOf(passedTestCasesCount.size() + failedTestCasesCount.size() + skippedTests.size());
		extent.export();
	}

	public void onTestSuccess(ITestResult result) {
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			String expMsg = Arrays.toString(result.getThrowable().getStackTrace());

			try {

			} catch (Exception ex) {
				Reporter.fail("Test Failed, unable to attach screenshot.");
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.skip("This Test is skiped.");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}

	public String takeScreenshot(String methodName, WebDriver driver) {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir") + "/screenshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		} catch (Exception e) {

		}

		return path;
	}

}
