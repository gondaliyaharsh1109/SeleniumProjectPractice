package com.practiceautomationtest.tests.EmployeeTestPageAdmin;

import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.EmployeePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {
    @Test
    public void addEmployee(){
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        employeePage.executeAddEmployee();
    }
    @Test
    public void editEmployee(){
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        employeePage.executeEditEmployee();
    }
    @Test
    public void addNewTaskAndVerifyInEmployee(){
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        employeePage.addNewTaskAndVerifyInEmployee();
    }
}
