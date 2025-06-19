package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private ByteArrayOutputStream consoleContent;
    private PrintStream originalOut;
    private long startTime;

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started: {}", context.getName());
        ExtentReportManager.initReport();

    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished: {}", context.getName());
        ExtentReportManager.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: {}", result.getMethod().getDescription());
        startTime = System.currentTimeMillis();
        logger.info("Test Started: {}", result.getMethod().getDescription());
        ExtentReportManager.createTest(result.getMethod().getDescription())
                .assignAuthor("Akash")
                .assignCategory("Regression")
                .assignDevice("API");
        originalOut = System.out;
        consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
    }

    private void logConsoleToExtent() {
        if (consoleContent != null) {
            String logs = consoleContent.toString();
            if (!logs.isEmpty()) {
                ExtentReportManager.getTest().info("Console Output:<br><pre>" + logs + "</pre>");
            }
        }
        // Reset System.out
        if (originalOut != null) {
            System.setOut(originalOut);
        }
    }


    private void logRequestResponse(ITestResult result) {
        Object request = result.getAttribute("request");
        Object response = result.getAttribute("response");
        if (request != null) {
            ExtentReportManager.getTest().info("<details><summary>Request</summary><pre>" + request + "</pre></details>");
        }
        if (response != null) {
            ExtentReportManager.getTest().info("<details><summary>Response</summary><pre>" + response + "</pre></details>");
        }
    }

    private void attachResponseBodyOnFailure(ITestResult result) {
        Object response = result.getAttribute("response");
        if (response != null) {
            ExtentReportManager.getTest().fail("<details><summary>Response Body</summary><pre>" + response + "</pre></details>");
        }
    }

    private void logExecutionTime() {
        long duration = System.currentTimeMillis() - startTime;
        ExtentReportManager.getTest().info("Execution Time: " + duration + " ms");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getMethod().getDescription());
        ExtentReportManager.getTest().pass("Test passed: " + result.getMethod().getDescription());
        logRequestResponse(result);
        logConsoleToExtent();
        logExecutionTime();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getMethod().getDescription());
        ExtentReportManager.getTest().fail(result.getThrowable());
        logRequestResponse(result);
        attachResponseBodyOnFailure(result);
        logConsoleToExtent();
        logExecutionTime();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getMethod().getDescription());
        ExtentReportManager.getTest().skip("Test skipped: " + result.getMethod().getDescription());
        logRequestResponse(result);
        logConsoleToExtent();
        logExecutionTime();
    }

}
