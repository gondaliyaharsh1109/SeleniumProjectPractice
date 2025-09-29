package com.practiceautomationtest.tests.allPagesClassAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends BasePage {
    By enterEmail = By.xpath("//input[@type='email']");
    By enterPassword = By.id("outlined-adornment-password");
    By departmentText = By.xpath("//div[@class='MuiBox-root css-axw7ok']");
    By loginButton = By.xpath("//button[@type='submit']");
    By verifyLeaveTextAfterLogin = By.xpath("(//span[contains(text(),'Leave')])[2]");
    By verifyLoggedInByText = By.xpath("//div[@role='button']");
    By verifyTabsBySearch = By.xpath("//input[@placeholder='Search']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        visits("https://employee-cicd.vercel.app/login");
    }

    public void clickLoginButton() {
        //driver.findElement(loginButton).click();
        waitForElement(loginButton).click();
    }

    public void visibleDepartmentText(){
        waitForElement(departmentText);
    }
    public void visibleLeaveText(){
        String actualLeaveText = waitForElement(verifyLeaveTextAfterLogin).getText();
        String expectedLeaveText = "Leave";
        Assert.assertEquals(actualLeaveText,expectedLeaveText);
    }
    public void executeLoginAdmin(String username, String password, boolean verify) {
        visit();
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
        if (verify){
            visibleDepartmentText();
        }
        verifyRoleAfterLogin("EMS: Admin");
//        verifyEmailAfterLogin("admin@gmail.com");
    }
    public void executeLoginEmployee(String username, String password, boolean verify) {
        visit();
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
        if (verify){
            visibleLeaveText();
        }
        verifyRoleAfterLogin("EMS: Employee");
//        verifyEmailAfterLogin("adena.leannon@yahoo.com");
    }
    public void executeLoginEmployeeAfterUpdatingLeaveStatus(String username, String password, boolean verify) {
        waitForElement(enterEmail).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(enterEmail).sendKeys(Keys.DELETE);
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(enterPassword).sendKeys(Keys.DELETE);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
        if (verify){
            visibleLeaveText();
        }
    }
    public List<String> getSidebarTabs() {
        List<WebElement> tabs = driver.findElements(verifyLoggedInByText);
        return tabs.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    // Verify Admin Tabs
    public void verifyAdminTabs() {
        List<String> actualTabs = getSidebarTabs();
        List<String> expectedTabs = List.of("Departments", "Position", "Employees", "Leave", "Tasks");

        Assert.assertTrue(actualTabs.containsAll(expectedTabs));
    }
    // Verify Employee Tabs
    public void verifyEmployeeTabs() {
        List<String> actualTabs = getSidebarTabs();
        List<String> expectedTabs = List.of("Leave", "Tasks");

        Assert.assertTrue(actualTabs.containsAll(expectedTabs));
    }
    public List<WebElement> searchTabs(String searchText) {
        WebElement searchInput = driver.findElement(verifyTabsBySearch);
        searchInput.clear();
        searchInput.sendKeys(searchText);
        return driver.findElements(verifyLoggedInByText);
    }
    public void verifySearchResults(String searchText) {
        List<WebElement> filteredTabs = searchTabs(searchText);
        for (WebElement tab : filteredTabs) {
            String tabName = tab.getText();
            Assert.assertTrue(tabName.toLowerCase().contains(searchText.toLowerCase()));
        }
    }
    public void verifyRoleAfterLogin(String expectedRole){
        String actualRole = String.format("//h6[contains(@class,'MuiTypography-subtitle2') and text()='%s']",expectedRole);
        Assert.assertEquals(actualRole,expectedRole);
    }
//    public void verifyEmailAfterLogin(String expectedEmail){
//        String actualRole = waitForElement(verifyEmailAfterLogin).getText();
//        Assert.assertEquals(actualRole,expectedEmail);
//    }
}
