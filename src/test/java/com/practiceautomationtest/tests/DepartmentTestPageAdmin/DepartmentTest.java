package com.practiceautomationtest.tests.DepartmentTestPageAdmin;
import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.DepartmentPage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DepartmentTest extends BaseTest {

    @Test
    public void addDepartment(){
        test = extent.createTest("Add department");
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        departmentPage.executeAddDepartment();
    }
    @Test
    public void notAbleToDeleteDepartmentWithPosition(){
        test = extent.createTest("Not able to delete department with position");
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        departmentPage.notAbleToDeleteDepartmentWithPosition();
    }
    @Test
    public void searchingDepartmentUsingDepartmentNameAndLocationFilter(){
        test = extent.createTest("Search department using name and location filter");
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        departmentPage.searchingDepartmentUsingDepartmentNameAndLocationFilter("Finance Department","Vadodara");
    }
    @Test
    public void searchingDepartmentUsingLocationFilterOnly(){
        test = extent.createTest("Search department using location filer");
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        departmentPage.searchingDepartmentUsingLocationFilterOnly("Finance Department", "Vadodara","location");
    }
    @Test
    public void updateStatusOfDepartmentFromActiveToInactiveAndVerifyBySearch(){
        test = extent.createTest("Update status of department from active to inactive and verify by search");
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        departmentPage.updateStatusOfDepartmentFromActiveToInactiveAndVerifyBySearch("Biotechnology");
    }
    @Test
    public void redirectToDepartmentByClickingHomeBtn(){
        test = extent.createTest("Redirect to department by clicking home btn");
        DepartmentPage departmentPage = new DepartmentPage(driver);
        departmentPage.redirectToDepartmentByClickingHomeBtn();
    }
}
