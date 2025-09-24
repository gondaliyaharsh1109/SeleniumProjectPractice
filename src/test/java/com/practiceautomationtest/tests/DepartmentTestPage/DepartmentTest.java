package com.practiceautomationtest.tests.DepartmentTestPage;
import com.practiceautomationtest.tests.allPagesClass.DepartmentPage;
import com.practiceautomationtest.tests.allPagesClass.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DepartmentTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(DepartmentTest.class);

    @Test
    public void addDepartment(){
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLogin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        departmentPage.executeAddDepartment();
    }
    @Test
    public void deleteDepartmentValidationWithPosition(){
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.executeLogin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        departmentPage.verifyDeleteDepartmentValidationWithPosition();
    }
}
