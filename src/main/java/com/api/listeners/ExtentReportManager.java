package com.api.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        htmlReporter = new ExtentSparkReporter("Reports/report.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("API Automation Report");
        htmlReporter.config().setReportName("RestAssured API Report");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static ExtentTest createTest(String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports not initialized. Call initReport() before creating tests.");
        }
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        ExtentTest extentTest = test.get();
        if (extentTest == null) {
            throw new IllegalStateException("No ExtentTest found for current thread. Call createTest() first.");
        }
        return extentTest;
    }
}
