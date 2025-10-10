package com.practiceautomationtest.tests.TaskTestPageAdmin;

import com.practiceautomationtest.tests.BaseTest;
import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.allPagesClassAdmin.TaskPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskTest extends BaseTest {
    @Test
    public void addTask(){
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        TaskPage taskPage = new TaskPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(basePage.currentUrl(),expectedUrl);
        taskPage.addNewTask();
    }
}
