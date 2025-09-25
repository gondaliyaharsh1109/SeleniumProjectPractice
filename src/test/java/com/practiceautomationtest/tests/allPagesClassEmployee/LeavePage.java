package com.practiceautomationtest.tests.allPagesClassEmployee;

import com.github.javafaker.Faker;
import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class LeavePage extends BasePage {
    LoginPage loginPage = new LoginPage(driver);
    BasePage basePage = new BasePage(driver);
    Faker faker = new Faker();
    Actions actions = new Actions(driver);
    By enterEmail = By.xpath("//input[@type='email']");
    By enterPassword = By.id("outlined-adornment-password");
    By loginButton = By.xpath("//button[@type='submit']");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By newBtn = By.xpath("//button[contains(text(),'New')]");
    By verifyCreateLeaveText = By.xpath("//p[contains(text(),'Create Leave Status')]");
    By leaveTypeSelection = By.xpath("//div[@id='id__leaveType']");
    By dropDownSelection = By.xpath("//li[@role='option']");
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
    By clickOnLeaveTab = By.xpath("(//span[contains(text(),'Leave')])[1]");
    By statusSelection = By.id("id__leave_status");
    By clickOnFirstEmployee = By.xpath("(//div[@data-field='username'])[2]");
    By verifyUpdateLeaveStatusText = By.xpath("//p[contains(text(),'Update Leave Status')]");
    By leaveStatusUpdatedToastMessage = By.xpath("//p[contains(text(),'Leave status updated successfully')]");
    By verifyLeaveStatusAsEmployee = By.xpath("(//div[(@data-field='leave_status')])[2]");
    By verifyEmailAfterLogin = By.xpath("//h6[contains(text(),'adena.leannon@yahoo.com')]");

    public LeavePage(WebDriver driver){
        super(driver);
    }
    public void addLeave(String leaveTypeSelect){
        String randomReason = faker.chuckNorris().fact();
        waitForElement(newBtn).click();
        String actualCreateLeaveStatusText = waitForElement(verifyCreateLeaveText).getText();
        String expectedCreateLeaveStatusText = "Create Leave Status";
        Assert.assertEquals(actualCreateLeaveStatusText,expectedCreateLeaveStatusText);
        waitForElement(leaveTypeSelection).click();
        waitForElement(dropDownSelection);
        List<WebElement> leaveTypeDropDown = driver.findElements(dropDownSelection);
        for(WebElement leaveType : leaveTypeDropDown){
            if(leaveType.getText().equals(leaveTypeSelect)){
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
    public void updateLeaveStatusAndVerifyAsEmployee(String leaveTypeSelectInEmployee, String statusTypeSelectInAdmin, String updateLeaveStatusInAdmin){
        LoginPage loginPage = new LoginPage(driver);
        String randomReason = faker.chuckNorris().fact();
        waitForElement(newBtn).click();
        String actualCreateLeaveStatusText = waitForElement(verifyCreateLeaveText).getText();
        String expectedCreateLeaveStatusText = "Create Leave Status";
        Assert.assertEquals(actualCreateLeaveStatusText,expectedCreateLeaveStatusText);
        waitForElement(leaveTypeSelection).click();
        waitForElement(dropDownSelection);
        List<WebElement> leaveTypeDropDown = driver.findElements(dropDownSelection);
        for(WebElement leaveType : leaveTypeDropDown){
            if(leaveType.getText().equals(leaveTypeSelectInEmployee)){
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
        loginPage.executeLoginAdmin("admin@gmail.com","Admin123!",true);
        waitForElement(clickOnLeaveTab).click();
        waitForElement(statusSelection).click();
        waitForElement(dropDownSelection);
        List<WebElement> statusSelectionDropdown = driver.findElements(dropDownSelection);
        for(WebElement statusSelect : statusSelectionDropdown){
            if(statusSelect.getText().equals(statusTypeSelectInAdmin)){
                statusSelect.click();
                break;
            }
        }
        waitForElementToBeInvisible(loaderToBeInvisible);
        actions.doubleClick(waitForElement(clickOnFirstEmployee)).perform();
        waitForElementToBeInvisible(loaderToBeInvisible);
        String actualUpdateLeaveStatusText = waitForElement(verifyUpdateLeaveStatusText).getText();
        String expectedUpdateLeaveStatusText = "Update Leave Status";
        Assert.assertEquals(actualUpdateLeaveStatusText,expectedUpdateLeaveStatusText);
        waitForElement(statusSelection).click();
        waitForElement(dropDownSelection);
        List<WebElement> leaveStatusSelection = driver.findElements(dropDownSelection);
        for(WebElement leaveStatusSelect : leaveStatusSelection){
            if(leaveStatusSelect.getText().equals(updateLeaveStatusInAdmin)){
                leaveStatusSelect.click();
                break;
            }
        }
        waitForElement(clickSaveButton).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
        String actualStatusUpdateToastMessage = waitForElement(leaveStatusUpdatedToastMessage).getText();
        String expectedStatusUpdateToastMessage = "Leave status updated successfully";
        Assert.assertEquals(actualStatusUpdateToastMessage,expectedStatusUpdateToastMessage);
        waitForElement(clickBtnForLogout).click();
        waitForElement(clickOnLogoutBtn).click();
        waitForElement(enterEmail).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(enterEmail).sendKeys(Keys.DELETE);
        waitForElement(enterEmail).sendKeys("adena.leannon@yahoo.com");
        waitForElement(enterPassword).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(enterPassword).sendKeys(Keys.DELETE);
        waitForElement(enterPassword).sendKeys("Pytheta123!");
        waitForElement(loginButton).click();
        Assert.assertEquals(actualLoginText,expectedLoginText);
        String actualUpdatedStatusInEmployee = waitForElement(verifyLeaveStatusAsEmployee).getText();
        Assert.assertEquals(actualUpdatedStatusInEmployee,updateLeaveStatusInAdmin);
    }
}
