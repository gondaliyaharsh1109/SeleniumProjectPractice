package com.practiceautomationtest.tests.allPagesClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class DepartmentPage extends BasePage {
    Faker faker = new Faker();
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
    public void executeAddDepartment(){
        String departmentName = faker.company().industry();
        String locationName = faker.address().city();
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
