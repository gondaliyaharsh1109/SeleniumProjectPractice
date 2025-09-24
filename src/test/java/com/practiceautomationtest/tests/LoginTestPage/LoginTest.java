package com.practiceautomationtest.tests.LoginTestPage;

import com.practiceautomationtest.tests.allPagesClass.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void positiveTestLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLogin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        loginPage.visibleDepartmentText();
    }

    @Test
    public void incorrectUserNameTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLogin("admin@yahoo.com","Admin123!",false);
        String expectedErrorMessage = "No account with this email has been registered.";
        Assert.assertEquals(loginPage.verifyToastMessage(),expectedErrorMessage);
    }

    @Test
    public void incorrectPasswordTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLogin("admin@gmail.com","Admin123@");
        String expectedMessage = "Invalid credentials.";
        Assert.assertEquals(loginPage.verifyToastMessage(),expectedMessage);
    }
}
