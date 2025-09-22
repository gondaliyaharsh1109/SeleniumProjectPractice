package com.practiceautomationtest.tests.allPagesClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class DepartmentPage extends BasePage {
    By newButton = By.xpath("//button[@tabindex='0']");
    By enterDepartmentName = By.id("departmentName");
    By enterLocationName = By.id("location");
    By clickSaveButton = By.xpath("//button[contains(text(),'Save')]");
    By visibleCreateDepartmentText = By.xpath("//li[@class='MuiBreadcrumbs-li']");
    By clickOnDepartmentToEdit = By.xpath("(//div[@data-field='Department Name'])[2]");
    By searchDepartment = By.xpath("//input[@placeholder='Search with DepartmentName']");
    By editDepartmentNameField = By.xpath("//input[@id='departmentName']");

    public DepartmentPage(WebDriver driver){
        super(driver);
    }
    public void newButtonClick(){
        waitForElement(newButton).click();
    }
    public void visitCreateDepartmentPage(){
        visit("https://employee-cicd.vercel.app/department/new");
    }
    public void confirmVisibleCreateDepartmentText(){
        waitForElement(visibleCreateDepartmentText);
    }
    public void clickSaveButton(){
        waitForElement(clickSaveButton).click();
    }
    public String getRandomString(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    public void executeAddDepartment(String departmentName, String locationName){
        newButtonClick();
        visitCreateDepartmentPage();
        confirmVisibleCreateDepartmentText();
        waitForElement(enterDepartmentName).sendKeys(departmentName);
        waitForElement(enterLocationName).sendKeys(locationName);
        clickSaveButton();
    }
    public void editDepartment(String oldName, String newName){
        WebElement searchDptName = waitForElement(searchDepartment);
        searchDptName.sendKeys(oldName);
        searchDptName.sendKeys(Keys.ENTER);
        Actions actions = new Actions(driver);
        WebElement searchDepartment = waitForElement(clickOnDepartmentToEdit);
        actions.doubleClick(searchDepartment).perform();
        waitForElement(editDepartmentNameField).clear();
        waitForElement(editDepartmentNameField).sendKeys(newName);
        clickSaveButton();
    }
}
