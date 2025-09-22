package com.practiceautomationtest.tests.DepartmentTestPage;
import com.practiceautomationtest.tests.allPagesClass.DepartmentPage;
import com.practiceautomationtest.tests.allPagesClass.LoginPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DepartmentTest extends BaseTest {

    @Test
    public void addDepartment(){
        LoginPage loginPage = new LoginPage(driver);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        loginPage.visit();
        loginPage.executeLogin("admin@gmail.com","Admin123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        String randomDepartment = "Dept-" + departmentPage.getRandomString(6);
        String randomLocation = "Loc-" + departmentPage.getRandomString(6);
        departmentPage.executeAddDepartment(randomDepartment,randomLocation);
        String expectedMessage = "Department created successfully.";
        Assert.assertEquals(loginPage.toastMessage(),expectedMessage);
    }
}
