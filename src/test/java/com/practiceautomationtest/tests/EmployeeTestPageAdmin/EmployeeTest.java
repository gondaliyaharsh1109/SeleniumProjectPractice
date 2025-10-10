package com.practiceautomationtest.tests.EmployeeTestPageAdmin;

import com.practiceautomationtest.tests.allPagesClassAdmin.EmployeePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import com.practiceautomationtest.tests.allPagesClassAdmin.TaskPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {
    @Test
    public void addEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        employeePage.executeAddEmployee();
    }
    @Test
    public void editEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        employeePage.executeEditEmployee();
    }
    @Test
    public void addNewTaskAndVerifyInEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        employeePage.addNewTaskAndVerifyInEmployee();
    }
}
