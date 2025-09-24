package com.practiceautomationtest.tests.allPagesClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DepartmentPage extends BasePage {
    Faker faker = new Faker();
    Actions actions = new Actions(driver);
    By newButton = By.xpath("//button[@tabindex='0']");
    By enterDepartmentName = By.id("departmentName");
    By enterLocationName = By.id("location");
    By clickSaveButton = By.xpath("//button[contains(text(),'Save')]");
    By visibleCreateDepartmentText = By.xpath("//li[@class='MuiBreadcrumbs-li']");
    By clickOnDepartmentToEdit = By.xpath("(//div[@data-field='departmentName'])[2]");
    By searchDepartment = By.xpath("//input[@placeholder='Search with DepartmentName']");
    By verifyEditDepartmentText = By.xpath("//p[contains(text(),'Edit Department')]");
    By editDepartmentNameField = By.xpath("//input[@id='departmentName']");
    By clickPositionTab = By.xpath("//span[contains(text(),'Position')]");
    By verifyDepartmentNameFromPositionPage = By.xpath("(//div[@data-field='departmentName'])[2]");
    By clickDepartmentsTab = By.xpath("//span[contains(text(),'Departments')]");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By deleteDepartmentBtn = By.xpath("(//button[contains(text(),'Delete')])[1]");
    By verifyConfirmDeleteMessage = By.xpath("//h2[contains(text(),'Confirm')]");
    By confirmDeleteBtn = By.xpath("(//button[contains(text(),'Delete')])[3]");
    By departmentAttachedWithPosition = By.xpath("//p[contains(text(),'This department has positions attached. Please remove positions first.')]");

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
        WebElement searchDepartment = waitForElement(clickOnDepartmentToEdit);
        actions.doubleClick(searchDepartment).perform();
        waitForElement(editDepartmentNameField).clear();
        waitForElement(editDepartmentNameField).sendKeys(newName);
        clickSaveButton();
    }
    public void verifyDeleteDepartmentValidationWithPosition(){
        waitForElement(clickPositionTab).click();
        String actualVerifyDepartmentName = waitForElement(verifyDepartmentNameFromPositionPage).getText();
        waitForElement(clickDepartmentsTab).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
        waitForElement(searchDepartment).click();
        waitForElement(searchDepartment).sendKeys(actualVerifyDepartmentName);
        waitForElement(searchDepartment).sendKeys(Keys.ENTER);
        waitForElementToBeInvisible(loaderToBeInvisible);
        actions.doubleClick(waitForElement(clickOnDepartmentToEdit)).perform();
        String actualEditDepartmentText = waitForElement(verifyEditDepartmentText).getText();
        String expectedEditDepartmentText = "Edit Department";
        Assert.assertEquals(actualEditDepartmentText,expectedEditDepartmentText);
        waitForElement(deleteDepartmentBtn).click();
        String actualDeleteConfirmMessage = waitForElement(verifyConfirmDeleteMessage).getText();
        String expectedDeleteConfirmMessage = "Confirm";
        Assert.assertEquals(actualDeleteConfirmMessage,expectedDeleteConfirmMessage);
        waitForElement(confirmDeleteBtn).click();
        String actualDepartmentValidateMessage = waitForElement(departmentAttachedWithPosition).getText();
        String expectedDepartmentValidateMessage = "This department has positions attached. Please remove positions first.";
        Assert.assertEquals(actualDepartmentValidateMessage,expectedDepartmentValidateMessage);
    }
}
