package com.practiceautomationtest.tests.LeaveTestPageEmployee;

import com.practiceautomationtest.tests.BaseTest;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.allPagesClassEmployee.LeavePageEmployee;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeaveTest extends BaseTest {

    @Test
    public void executeAddLeave(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.executeLoginEmployee("adena.leannon@yahoo.com","Pytheta123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/leaveEmp";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        LeavePageEmployee leavePage = new LeavePageEmployee(driver);
        leavePage.createLeaveAsEmployee("Sick");
    }
    @Test
    public void verifyLeaveStatusAsEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.executeLoginEmployee("adena.leannon@yahoo.com","Pytheta123!",true);
        String expectedUrl = "https://employee-cicd.vercel.app/leaveEmp";
        Assert.assertEquals(loginPage.currentUrl(),expectedUrl);
        LeavePageEmployee leavePage = new LeavePageEmployee(driver);
        leavePage.updateLeaveStatusAndVerifyAsEmployee("Sick","Pending","Rejected");
    }
}
