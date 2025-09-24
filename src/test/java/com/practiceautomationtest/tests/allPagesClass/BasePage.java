package com.practiceautomationtest.tests.allPagesClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void visit(String url) {
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
    public List<WebElement> presenceOfAllElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public String verifyToastMessage(){
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-1cp532n']")));
        return errorMessage.getText();
    }
}
