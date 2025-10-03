package com.practiceautomationtest.tests.allPagesClassAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void visits(String url) {
        driver.get(url);
    }
    public String currentUrl() {
        return driver.getCurrentUrl();
    }
    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementToBeInvisible(By locator){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void waitForElementsToBeInvisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }
    public List<WebElement> presenceOfAllElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public String verifyToastMessage(){
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-1cp532n']")));
        return errorMessage.getText();
    }
}
