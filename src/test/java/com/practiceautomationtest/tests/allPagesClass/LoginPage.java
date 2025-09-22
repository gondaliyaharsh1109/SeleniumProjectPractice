package com.practiceautomationtest.tests.allPagesClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    By enterEmail = By.xpath("//input[@type='email']");
    By enterPassword = By.id("outlined-adornment-password");
    By departmentText = By.xpath("//div[@class='MuiBox-root css-axw7ok']");
    By loginButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        visit("https://employee-cicd.vercel.app/login");
    }

    public void clickLoginButton() {
        //driver.findElement(loginButton).click();
        waitForElement(loginButton).click();
    }

    public void visibleDepartmentText(){
        waitForElement(departmentText);
    }

    public void executeLogin(String username, String password, boolean verify) {
        visit();
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
        if (verify){
        visibleDepartmentText();
        }
    }
    public void executeLogin(String username, String password) {
        waitForElement(enterEmail).sendKeys(username);
        waitForElement(enterPassword).sendKeys(password);
        clickLoginButton();
    }
}
