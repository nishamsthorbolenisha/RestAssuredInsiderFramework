
package com.insider.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.compress.utils.CharsetNames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporter
{

    private static ExtentHtmlReporter reporter;
    private static ExtentReports report;
    private static ExtentTest extentTest;
    private static ExtentTest childTest;
    static ExtentTest test;

    public Reporter()
    {
        reporter = new ExtentHtmlReporter(getReportName());
        reporter.config().setEncoding("utf-8");
        reporter.config().setDocumentTitle("APIReport");
        reporter.config().setReportName(getReportName());
        report = new ExtentReports();
        report.setSystemInfo("OS", System.getProperty("os.name"));
        reporter.config().setTheme(Theme.STANDARD);
        report.attachReporter(reporter);
    }

    public void createReport(String testName, String methodName)
    {
        String temp = testName.replaceAll("([^_])([A-Z])", "$1 $2");
        ;
        if (!Constants.test.isEmpty())
        {
            if (!Constants.test.equals(temp))
            {
                extentTest = report.createTest(testName.replaceAll("([^_])([A-Z])", "$1 $2"));
                Constants.test = temp;
            }
        }
        else
        {
            Constants.test = temp;
            extentTest = report.createTest(testName.replaceAll("([^_])([A-Z])", "$1 $2"));
        }
        childTest = extentTest.createNode(methodName);
    }

    public static void pass(String description)
    {
        childTest.pass(description);
    }

    public static void info(String description)
    {
        childTest.info(description);
    }

    public static void error(String description)
    {
        childTest.error(description);
    }

    public static void skip(String description)
    {
        childTest.skip(description);
    }

    public void export()
    {
        try
        {
            report.flush();
            File testReport = new File(System.getProperty("user.dir") + Constants.REPORTPATH + Constants.ReportFileName);
            Document doc = Jsoup.parse(testReport, CharsetNames.UTF_8);
            PrintWriter writer = new PrintWriter(testReport, "UTF-8");
            writer.write(doc.html());
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void fail(String details)
    {
        childTest.fail(details);

    }

    public String getReportName()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        Constants.ReportFileName = "Insider" + "_" + dateFormat.format(date) + ".html";
        return System.getProperty("user.dir") + Constants.REPORTPATH + Constants.ReportFileName;
    }
}
