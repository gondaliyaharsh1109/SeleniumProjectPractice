package com.practiceautomationtest.tests.LoginTestPageAdmin;

import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

//    @Test
//    public void sampleTest() {
//        test = extent.createTest("Sample Login Test");
//        driver.get("https://example.com");
//
//        // Sample assertion to demonstrate failure
//        if(!driver.getTitle().contains("Example")) {
//            throw new AssertionError("Title does not contain 'Example'");
//        }
//
//        test.info("Navigated to Example.com successfully");
//    }

    @Test
    @Description("Verify that admin can login successfully")
    public void positiveTestLoginAsAdmin(){
        test = extent.createTest("Positive login test as admin");
        BasePage basePage = new BasePage(driver);
        Allure.step("Open browser and navigate to login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
//        test = extent.createTest("Positive login test");
        Allure.step("Login with valid admin credentials");
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Allure.step("Verify department page is visible");
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        Allure.step("Verify details through profile btn");
        loginPage.verifyAdminUserDetailsThroughProfileBtn();
        Allure.step("Verify all admin tabs are visible");
        loginPage.verifyAdminTabs();
        loginPage.verifySearchResults("De");
    }
    @Test
    public void positiveTestLoginAsEmployee(){
        test = extent.createTest("Positive login test as employee");
        BasePage basePage = new BasePage(driver);
        Allure.step("Open browser and navigate to login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Allure.step("Login with valid admin credentials");
        loginPage.executeLoginEmployee("adena.leannon@yahoo.com","Pytheta123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/leaveEmp";
        Allure.step("Verify leave page is visible");
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        Allure.step("Verify details through profile btn");
        loginPage.verifyEmployeeUserDetailsThroughProfileBtn();
        Allure.step("Verify all employee tabs are visible");
        loginPage.verifyEmployeeTabs();
        Allure.step("Verify search results");
        loginPage.verifySearchResults("e");
    }

    @Test
    public void incorrectUsernameTestAsAdmin(){
        test = extent.createTest("Incorrect username test as admin");
        Allure.step("Open browser and navigate to login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Allure.step("Login with invalid admin credentials");
        loginPage.executeLoginAdmin("admin@yahoo.com","Admin123!",false);
        String expectedErrorMessage = "No account with this email has been registered.";
        Allure.step("Verify invalid username toast message");
        Assert.assertEquals(loginPage.verifyToastMessage(),expectedErrorMessage);
    }

    @Test
    public void incorrectPasswordTestAsAdmin(){
        test = extent.createTest("Incorrect password test as admin");
        Allure.step("Open browser and navigate to login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Allure.step("Login with invalid admin credentials");
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123@",false);
        String expectedMessage = "Invalid credentials.";
        Allure.step("Verify invalid password toast message");
        Assert.assertEquals(loginPage.verifyToastMessage(),expectedMessage);
    }

    @Test
    public void clickingToggleBtnForSideBar(){
        test = extent.createTest("Click toggle btn for sidebar");
        BasePage basePage = new BasePage(driver);
        Allure.step("Open browser and navigate to login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Allure.step("Login with valid admin credentials");
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Allure.step("Verify department page is visible");
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        Allure.step("Click toggle btn");
        loginPage.clickingToggleBtn();
    }
}