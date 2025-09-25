package com.practiceautomationtest.tests.EmployeeTestPage;

import com.practiceautomationtest.tests.allPagesClassAdmin.EmployeePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {
    @Test
    public void addEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLogin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        employeePage.executeAddEmployee();
    }
    @Test
    public void editEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLogin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        employeePage.executeEditEmployee();
    }
}
