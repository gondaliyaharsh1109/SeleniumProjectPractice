package com.practiceautomationtest.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseTest {
    public WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Extent Report/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }
        driver.quit();
    }

    @AfterSuite
    public void closeReport() {
        extent.flush();
    }
}
