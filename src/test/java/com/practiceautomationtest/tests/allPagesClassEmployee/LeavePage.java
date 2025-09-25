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

    public LeavePage(WebDriver driver){
        super(driver);
    }
    public void openLeaveForm() {
        waitForElement(newBtn).click();
        String actualCreateLeaveStatusText = waitForElement(verifyCreateLeaveText).getText();
        Assert.assertEquals(actualCreateLeaveStatusText, "Create Leave Status");
    }
    public void selectLeaveType(String leaveTypeSelect) {
        waitForElement(leaveTypeSelection).click();
        waitForElement(dropDownSelection); // ensures dropdown is visible

        List<WebElement> leaveTypeDropDown = driver.findElements(dropDownSelection);
        for (WebElement leaveType : leaveTypeDropDown) {
            if (leaveType.getText().equals(leaveTypeSelect)) {
                leaveType.click();
                break;
            }
        }
    }
    public void enterLeaveReason() {
        String randomReason = faker.chuckNorris().fact();
        waitForElement(reasonInputField).sendKeys(randomReason);
    }
    public void addLeave(String leaveTypeSelect) {
        openLeaveForm();
        selectLeaveType(leaveTypeSelect);
        enterLeaveReason();
    }
    public void createLeaveAsEmployee(String leaveType) {
        String randomReason = faker.chuckNorris().fact();

        waitForElement(newBtn).click();
        Assert.assertEquals(waitForElement(verifyCreateLeaveText).getText(), "Create Leave Status");

        selectFromDropdown(leaveTypeSelection, dropDownSelection, leaveType);

        waitForElement(clickOnFromDateBtn).click();
        waitForElement(selectFromDate).click();
        waitForElement(clickOnToDateBtn).click();
        waitForElement(clickBtnForNextMonth).click();
        waitForElement(selectToDate).click();

        waitForElement(reasonInputField).sendKeys(randomReason);
        waitForElement(clickSaveButton).click();

        Assert.assertEquals(waitForElement(verifyLeaveCreatedToastMessage).getText(), "Leave sent successfully.");
    }
    public void logout() {
        waitForElement(clickBtnForLogout).click();
        waitForElement(clickOnLogoutBtn).click();
    }
    public void updateLeaveStatusAsAdmin(String statusFilter, String updateStatus) {
        waitForElement(clickOnLeaveTab).click();

        selectFromDropdown(statusSelection, dropDownSelection, statusFilter);

        waitForElementToBeInvisible(loaderToBeInvisible);
        actions.doubleClick(waitForElement(clickOnFirstEmployee)).perform();

        waitForElementToBeInvisible(loaderToBeInvisible);
        Assert.assertEquals(waitForElement(verifyUpdateLeaveStatusText).getText(), "Update Leave Status");

        selectFromDropdown(statusSelection, dropDownSelection, updateStatus);
        waitForElement(clickSaveButton).click();

        waitForElementToBeInvisible(loaderToBeInvisible);
        Assert.assertEquals(waitForElement(leaveStatusUpdatedToastMessage).getText(), "Leave status updated successfully");
    }
    public void verifyUpdatedStatusAsEmployee(String expectedStatus) {
        String actualUpdatedStatus = waitForElement(verifyLeaveStatusAsEmployee).getText();
        Assert.assertEquals(actualUpdatedStatus, expectedStatus);
    }
    public void selectFromDropdown(By dropdownBtn, By dropdownOptions, String optionText) {
        waitForElement(dropdownBtn).click();
        waitForElement(dropdownOptions);

        List<WebElement> options = driver.findElements(dropdownOptions);
        for (WebElement option : options) {
            if (option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }
    public void updateLeaveStatusAndVerifyAsEmployee(String leaveTypeSelectInEmployee, String statusTypeSelectInAdmin, String updateLeaveStatusInAdmin) {

        LoginPage loginPage = new LoginPage(driver);

        createLeaveAsEmployee(leaveTypeSelectInEmployee);
        logout();
        Assert.assertEquals(waitForElement(verifyLoginText).getText(), "LOGIN");

        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        updateLeaveStatusAsAdmin(statusTypeSelectInAdmin, updateLeaveStatusInAdmin);
        logout();

        loginPage.executeLoginEmployeeAfterUpdatingLeaveStatus("adena.leannon@yahoo.com", "Pytheta123!", true);
        verifyUpdatedStatusAsEmployee(updateLeaveStatusInAdmin);
    }
}
