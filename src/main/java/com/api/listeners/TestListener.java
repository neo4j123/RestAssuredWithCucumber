package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Suite Started: {} ", result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {} ", result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {} ", result.getMethod().getDescription());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {} ", result.getMethod().getDescription());
    }

    // Other methods can be overridden as needed
}
