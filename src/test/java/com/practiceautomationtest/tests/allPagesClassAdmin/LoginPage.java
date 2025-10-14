package com.practiceautomationtest.tests.allPagesClassAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends BasePage {
    Actions actions = new Actions(driver);
    By enterEmail = By.xpath("//input[@type='email']");
    By enterPassword = By.id("outlined-adornment-password");
    By departmentText = By.xpath("//div[@class='MuiBox-root css-axw7ok']");
    By loginButton = By.xpath("//button[@type='submit']");
    By verifyLeaveTextAfterLogin = By.xpath("(//span[contains(text(),'Leave')])[2]");
    By verifyLoggedInByText = By.xpath("//div[@role='button']");
    By verifyTabsBySearch = By.xpath("//input[@placeholder='Search']");
    By clickProfileBtn = By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1yxmbwk'])[1]");
    By clickToggleBtnToHide = By.cssSelector("svg[data-testid='KeyboardDoubleArrowLeftIcon']");
    By clickToggleBtnToShow = By.cssSelector("svg[data-testid='MenuIcon']");
    By verifySideBar = By.xpath("//span[contains(@class, 'MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-1ulp22j')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        visits("https://employee-cicd.vercel.app/login");
    }

    public void clickLoginButton() {
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
    }
    public void executeLoginEmployee(String username, String password, boolean verify) {
        BasePage basePage = new BasePage(driver);
        visit();
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
        if (verify){
            visibleLeaveText();
        }
    }
    public void executeLoginEmployeeAfterUpdatingPerformingActionsAsAdmin(String username, String password, boolean verify) {
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
    public void verifyEmailAndRoleAfterLogin(String expectedRole, String expectedEmail){
        String xpathForRole = String.format("//h6[contains(@class,'MuiTypography-subtitle2') and text()='%s']",expectedRole);
        WebElement elementForRole = driver.findElement(By.xpath(xpathForRole));
        String actualRole = elementForRole.getText();
        Assert.assertEquals(actualRole,expectedRole);

        String xpathForEmail = String.format("//h6[contains(@class,'MuiTypography-subtitle2') and text()='%s']",expectedEmail);
        WebElement elementForEmail = driver.findElement(By.xpath(xpathForEmail));
        String actualEmail = elementForEmail.getText();
        Assert.assertEquals(actualEmail,expectedEmail);
    }
    public void verifyDetailsThroughProfileBtn(String expectedInitials, String expectedEmail, String expectedName, String expectedRole){
        String xpathForInitials = "(//div[contains(@class,'MuiAvatar-root MuiAvatar-circular MuiAvatar-colorDefault')])[2]";
        WebElement elementForInitials = driver.findElement(By.xpath(xpathForInitials));
        String actualInitials = elementForInitials.getText();
        Assert.assertEquals(actualInitials,expectedInitials);

        String xpathForExpectedName = String.format("//p[contains(@class,'MuiTypography-root MuiTypography-body1') and text()='%s']", expectedName);
        WebElement elementForName = driver.findElement(By.xpath(xpathForExpectedName));
        String actualName = elementForName.getText();
        Assert.assertEquals(actualName,expectedName);

        String xpathForExpectedEmail = "(//span[contains(@class,'MuiTypography-root MuiTypography-caption')])[2]";
        WebElement elementForEmail = driver.findElement(By.xpath(xpathForExpectedEmail));
        String actualEmail = elementForEmail.getText();
        Assert.assertEquals(actualEmail,expectedEmail);

        String xpathForExpectedRole = "(//span[contains(@class,'MuiTypography-root MuiTypography-caption')])[3]";
        WebElement elementForRole = driver.findElement(By.xpath(xpathForExpectedRole));
        String actualRole = elementForRole.getText();
        Assert.assertEquals(actualRole,expectedRole);
    }
    public void verifyAdminUserDetailsThroughProfileBtn(){
        waitForElement(clickProfileBtn).click();
        verifyEmailAndRoleAfterLogin("EMS: Admin","admin@gmail.com");
        verifyDetailsThroughProfileBtn("PA","Email: admin@gmail.com","PyTheta Admin","Role: Admin");
        actions.sendKeys(Keys.ESCAPE).perform();
    }
    public void verifyEmployeeUserDetailsThroughProfileBtn(){
        waitForElement(clickProfileBtn).click();
        verifyEmailAndRoleAfterLogin("EMS: Employee","adena.leannon@yahoo.com");
        verifyDetailsThroughProfileBtn("MG","Email: adena.leannon@yahoo.com","Melodee Gulgowski","Role: Employee");
        actions.sendKeys(Keys.ESCAPE).perform();
    }
    public void clickingToggleBtn(){
        waitForElement(clickToggleBtnToHide).click();
        waitForElementsToBeInvisible(waitForElement(verifySideBar));
        waitForElement(clickToggleBtnToShow).click();
        Assert.assertTrue(waitForElement(verifySideBar).isDisplayed());
    }
}
