package com.practiceautomationtest.tests.allPagesClassEmployee;

import com.github.javafaker.Faker;
import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class LeavePage extends BasePage {
    BasePage basePage = new BasePage(driver);
    Faker faker = new Faker();
    By newBtn = By.xpath("//button[contains(text(),'New')]");
    By verifyCreateLeaveText = By.xpath("//p[contains(text(),'Create Leave Status')]");
    By leaveTypeSelection = By.xpath("//div[@id='id__leaveType']");
    By leaveSelectionDropdown = By.xpath("//li[@role='option']");
    By clickOnFromDateBtn = By.xpath("(//button)[7]");
    By selectFromDate = By.xpath("//button[contains(text(),'28')]");
    By clickOnToDateBtn = By.xpath("(//button)[8]");
    By clickBtnForNextMonth = By.xpath("//button[@title='Next month']");
    By selectToDate = By.xpath("//button[contains(text(),'10')]");
    By reasonInputField = By.id("reason");
    By clickSaveButton = By.xpath("//button[contains(text(),'Save')]");
    By verifyLeaveCreatedToastMessage = By.xpath("//p[contains(text(),'Leave sent successfully.')]");
    By clickBtnForLogout = By.xpath("(//button)[1]");
    By clickOnLogoutBtn = By.xpath("//p[contains(text(),'Logout')]");
    By verifyLoginText = By.xpath("//h5[contains(text(),'LOGIN')]");
    By enterEmail = By.xpath("//input[@type='email']");
    By enterPassword = By.id("outlined-adornment-password");
    By loginButton = By.xpath("//button[@type='submit']");
    By verifyLeaveTextAfterLogin = By.xpath("(//span[contains(text(),'Leave')])[2]");

    public LeavePage(WebDriver driver){
        super(driver);
    }
    public void addLeave(){
        String randomReason = faker.chuckNorris().fact();
        waitForElement(newBtn).click();
        String actualCreateLeaveStatusText = waitForElement(verifyCreateLeaveText).getText();
        String expectedCreateLeaveStatusText = "Create Leave Status";
        Assert.assertEquals(actualCreateLeaveStatusText,expectedCreateLeaveStatusText);
        waitForElement(leaveTypeSelection).click();
        waitForElement(leaveSelectionDropdown);
        List<WebElement> leaveTypeDropDown = driver.findElements(By.xpath("//li[@role='option']"));
        for(WebElement leaveType : leaveTypeDropDown){
            if(leaveType.getText().equals("Casual")){
                leaveType.click();
                break;
            }
        }
        waitForElement(clickOnFromDateBtn).click();
        waitForElement(selectFromDate).click();
        waitForElement(clickOnToDateBtn).click();
        waitForElement(clickBtnForNextMonth).click();
        waitForElement(selectToDate).click();
        waitForElement(reasonInputField).sendKeys(randomReason);
        waitForElement(clickSaveButton).click();
        String actualLeaveCreateToastMessage = waitForElement(verifyLeaveCreatedToastMessage).getText();
        String expectedLeaveCreateToastMessage = "Leave sent successfully.";
        Assert.assertEquals(actualLeaveCreateToastMessage,expectedLeaveCreateToastMessage);
        waitForElement(clickBtnForLogout).click();
        waitForElement(clickOnLogoutBtn).click();
        String actualLoginText = waitForElement(verifyLoginText).getText();
        String expectedLoginText = "LOGIN";
        Assert.assertEquals(actualLoginText,expectedLoginText);
    }
}
