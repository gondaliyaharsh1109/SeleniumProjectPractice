package com.practiceautomationtest.tests.DepartmentTestPageAdmin;
import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.DepartmentPage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DepartmentTest extends BaseTest {

    @Test
    public void addDepartment(){
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
        DepartmentPage departmentPage = new DepartmentPage(driver);
        departmentPage.redirectToDepartmentByClickingHomeBtn();
    }
}
