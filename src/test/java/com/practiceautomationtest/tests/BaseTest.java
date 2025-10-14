package com.practiceautomationtest.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseTest {
    public WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest test;

    @BeforeClass
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
//    public void tearDown(ITestResult result) throws IOException
    public void tearDown(){
//        if (result.getStatus() == ITestResult.FAILURE) {
//            test.fail(result.getThrowable());
//
//            // Take screenshot
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String path = "target/screenshots/" + result.getName() + ".png";
//            File dest = new File(path);
//            Files.createDirectories(dest.getParentFile().toPath());
//            Files.copy(src.toPath(), dest.toPath());
//
//            test.addScreenCaptureFromPath(path);
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            test.pass("Test Passed");
//        }

        driver.quit();
    }

//    @AfterClass
//    public void tearDownReport() {
//        extent.flush();
//    }
}
