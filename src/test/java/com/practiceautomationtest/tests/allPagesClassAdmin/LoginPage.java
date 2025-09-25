package com.practiceautomationtest.tests.allPagesClassAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
    By enterEmail = By.xpath("//input[@type='email']");
    By enterPassword = By.id("outlined-adornment-password");
    By departmentText = By.xpath("//div[@class='MuiBox-root css-axw7ok']");
    By loginButton = By.xpath("//button[@type='submit']");
    By verifyLeaveTextAfterLogin = By.xpath("(//span[contains(text(),'Leave')])[2]");

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
    }

    public void executeLoginEmployee(String username, String password, boolean verify) {
        visit();
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
        if (verify){
            visibleLeaveText();
        }
    }
    public void executeLoginAdmin(String username, String password) {
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
    }
}
