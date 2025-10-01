package com.practiceautomationtest.tests.allPagesClassEmployee;

import com.github.javafaker.Faker;
import com.practiceautomationtest.tests.allPagesClassAdmin.BasePage;
import com.practiceautomationtest.tests.allPagesClassAdmin.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LeavePage extends BasePage {
    Faker faker = new Faker();
    Actions actions = new Actions(driver);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By newBtn = By.xpath("//button[contains(text(),'New')]");
    By verifyCreateLeaveText = By.xpath("//p[contains(text(),'Create Leave Status')]");
    By leaveTypeSelection = By.xpath("//div[@id='id__leaveType']");
    By dropDownSelection = By.xpath("//li[@role='option']");
    By enterFromDate = By.xpath("(//input[@placeholder='mm-dd-yyyy'])[1]");
    By enterToDate = By.xpath("(//input[@placeholder='mm-dd-yyyy'])[2]");
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
    public void enterLeaveReason() {
        String randomReason = faker.chuckNorris().fact();
        waitForElement(reasonInputField).sendKeys(randomReason);
    }
    public void createLeaveAsEmployee(String leaveType) {
        String fromDate = LocalDate.now().plusDays(2).format(formatter);
        String toDate = LocalDate.now().plusDays(10).format(formatter);

        openLeaveForm();
        selectFromDropdown(leaveTypeSelection, dropDownSelection, leaveType);
        waitForElement(enterFromDate).sendKeys(fromDate);
        waitForElement(enterToDate).sendKeys(toDate);
        enterLeaveReason();
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
    public void updateLeaveStatusAndVerifyAsEmployee(String leaveTypeSelectInEmployee, String statusTypeSelectInAdmin, String updateLeaveStatusInAdmin) {

        LoginPage loginPage = new LoginPage(driver);

        createLeaveAsEmployee(leaveTypeSelectInEmployee);
        logout();
        Assert.assertEquals(waitForElement(verifyLoginText).getText(), "LOGIN");

        loginPage.executeLoginAdmin("admin@gmail.com", "Admin123!", true);
        updateLeaveStatusAsAdmin(statusTypeSelectInAdmin, updateLeaveStatusInAdmin);
        logout();

        loginPage.executeLoginEmployeeAfterUpdatingPerformingActionsAsAdmin("adena.leannon@yahoo.com", "Pytheta123!", true);
        verifyUpdatedStatusAsEmployee(updateLeaveStatusInAdmin);
    }
}
