package com.practiceautomationtest.tests.DepartmentTestPageAdmin;
import com.practiceautomationtest.tests.allPagesClassAdmin.DepartmentPage;
import com.practiceautomationtest.tests.allPagesClassAdmin.EmployeePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DepartmentTest extends BaseTest {

    @Test
    public void addDepartment(){
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        departmentPage.executeAddDepartment();
    }
    @Test
    public void deleteDepartmentValidationWithPosition(){
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        departmentPage.verifyDeleteDepartmentValidationWithPosition();
    }
    @Test
    public void searchDepartmentUsingFilterBtn(){
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        departmentPage.searchingDepartmentUsingFilter("Finance Department","Vadodara");
    }
}
