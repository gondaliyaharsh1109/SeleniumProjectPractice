package com.practiceautomationtest.tests.LoginTestPageAdmin;

import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void positiveTestLoginAsAdmin(){
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        loginPage.verifyAdminTabs();
        loginPage.verifySearchResults("De");
    }
    @Test
    public void positiveTestLoginAsEmployee(){
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginAdmin("adena.leannon@yahoo.com","Pytheta123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/leaveEmp";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        loginPage.verifyEmployeeTabs();
        loginPage.verifySearchResults("e");
    }

    @Test
    public void incorrectUserNameTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginAdmin("admin@yahoo.com","Admin123!",false);
        String expectedErrorMessage = "No account with this email has been registered.";
        Assert.assertEquals(loginPage.verifyToastMessage(),expectedErrorMessage);
    }

    @Test
    public void incorrectPasswordTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123@",false);
        String expectedMessage = "Invalid credentials.";
        Assert.assertEquals(loginPage.verifyToastMessage(),expectedMessage);
    }
}
