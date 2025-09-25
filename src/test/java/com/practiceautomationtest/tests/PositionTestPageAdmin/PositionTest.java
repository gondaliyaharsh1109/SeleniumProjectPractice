package com.practiceautomationtest.tests.PositionTestPageAdmin;
import com.github.javafaker.Faker;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import com.practiceautomationtest.tests.allPagesClassAdmin.PositionPage;
import com.practiceautomationtest.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositionTest extends BaseTest {
    Faker faker = new Faker();
    @Test
    public void addPosition(){
        PositionPage positionPage = new PositionPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        String uniqueName = faker.name().firstName();
        positionPage.executeAddPosition(uniqueName, "local operations", "12th Pass");
        String actualPositionAddedMessage = positionPage.verifyPositionCreateMessage();
        String expectedMessage = "Position created successfully.";
        Assert.assertEquals(actualPositionAddedMessage, expectedMessage);
        positionPage.verifyPositionAddedBySearch(uniqueName);
        String actualSearchedPositionName = positionPage.verifySearchedPositionName();
        Assert.assertEquals(actualSearchedPositionName, uniqueName);
    }
    @Test
    public void searchAndCountPositions() {
        PositionPage positionPage = new PositionPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        positionPage.executeSearchPosition("Manager_175800");
    }
    @Test
    public void editAndDeletePosition(){
        PositionPage positionPage = new PositionPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        String uniquePosition = "Manager_" + System.currentTimeMillis();
        positionPage.executeAddPosition(uniquePosition, "local operations", "12th Pass");
        String actualPositionAddedMessage = positionPage.verifyPositionCreateMessage();
        String expectedMessage = "Position created successfully.";
        Assert.assertEquals(actualPositionAddedMessage, expectedMessage);
        positionPage.verifyPositionAddedBySearch(uniquePosition);
        String actualSearchedPositionName = positionPage.verifySearchedPositionName();
        Assert.assertEquals(actualSearchedPositionName, uniquePosition);

        String uniqueEditPosition = "Manager_" + System.currentTimeMillis();
        positionPage.editPosition(uniqueEditPosition);
        String actualPositionUpdateMessage = positionPage.positionUpdateMessage();
        String expectedPositionUpdateMessage = "Updated the position details successfully";
        Assert.assertEquals(actualPositionUpdateMessage,expectedPositionUpdateMessage);
        positionPage.verifyPositionEditedAddedBySearch(uniqueEditPosition);
        String actualSearchedEditedPositionName = positionPage.verifySearchedPositionName();
        Assert.assertEquals(actualSearchedEditedPositionName, uniqueEditPosition);
        positionPage.executeDeletePosition();
        String actualDeleteMessage = positionPage.removedPositionMessage();
        String expectedDeleteMessage = "Removed the position details successfully";
        Assert.assertEquals(actualDeleteMessage,expectedDeleteMessage);
        positionPage.verifyDeletedPositionBySearch(uniqueEditPosition);
    }
    @Test
    public void deletePositionValidationWithEmployee(){
        LoginPage loginPage = new LoginPage(driver);
        PositionPage positionPage = new PositionPage(driver);
        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        String expectedUrl = "https://employee-cicd.vercel.app/department";
        Assert.assertEquals(loginPage.currentUrl(), expectedUrl);
        positionPage.verifyDeletePositionValidationWithEmployee();
    }
//    @Test
//    public void HashMapPractice(){
//        PositionPage positionPage = new PositionPage(driver);
//        positionPage.HashMapPractice();
//    }
}