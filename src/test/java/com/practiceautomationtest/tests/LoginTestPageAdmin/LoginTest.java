package com.practiceautomationtest.tests.LoginTestPageAdmin;

import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void positiveTestLoginAsAdmin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        loginPage.verifyAdminUserDetailsThroughProfileBtn();
        loginPage.verifyAdminTabs();
        loginPage.verifySearchResults("De");
    }
    @Test
    public void positiveTestLoginAsEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginEmployee("adena.leannon@yahoo.com","Pytheta123!",true);
        loginPage.verifyEmployeeUserDetailsThroughProfileBtn();
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

    @Test
    public void clickingToggleBtnForSideBar(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        loginPage.clickingToggleBtn();
    }
}