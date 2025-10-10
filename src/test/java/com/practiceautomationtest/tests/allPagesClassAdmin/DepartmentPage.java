package com.practiceautomationtest.tests.allPagesClassAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class DepartmentPage extends BasePage {
    BasePage basePage = new BasePage(driver);
    Faker faker = new Faker();
    Actions actions = new Actions(driver);
    By newButton = By.xpath("//button[contains(text(),'New')]");
    By enterDepartmentName = By.id("departmentName");
    By enterLocationName = By.id("location");
    By clickSaveButton = By.xpath("//button[contains(text(),'Save')]");
    By visibleCreateDepartmentText = By.xpath("//li[@class='MuiBreadcrumbs-li']");
    By firstDepartmentNameInList = By.xpath("(//div[@data-field='departmentName'])[2]");
    By searchDepartment = By.id("search");
    By verifyEditDepartmentText = By.xpath("//p[contains(text(),'Edit Department')]");
    By clickPositionTab = By.xpath("//span[contains(text(),'Position')]");
    By verifyDepartmentNameFromPositionPage = By.xpath("(//div[@data-field='departmentName'])[2]");
    By clickDepartmentsTab = By.xpath("//span[contains(text(),'Departments')]");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By deleteDepartmentBtn = By.xpath("(//button[contains(text(),'Delete')])[1]");
    By verifyConfirmDeleteMessage = By.xpath("//h2[contains(text(),'Confirm')]");
    By confirmDeleteBtn = By.xpath("(//button[contains(text(),'Delete')])[3]");
    By clickFilterBtn = By.xpath("(//button[@type='button'])[6]");
    By clickClearBtn = By.xpath("//button[contains(text(),'Clear')]");
    By clickAddBtn = By.xpath("//button[contains(text(),'Add')]");
    By enterValueForFilter = By.id("value_0");
    By clickOKBtn = By.xpath("//button[contains(text(),'OK')]");
    By verifyFirstLocation = By.xpath("(//div[@data-field='location'])[2]");
    By clickColumnDropDown = By.id("id__column_0");
    By clickDeActivateBtn = By.xpath("(//button[contains(text(),'De-Activate')])[1]");
    By verifyStatus = By.xpath("(//div[@data-field='inactive'])[2]");

    public DepartmentPage(WebDriver driver){
        super(driver);
    }
    public void newButtonClick(){
        waitForElement(newButton).click();
    }
    public void visitCreateDepartmentPage(){
        visits("https://employee-cicd.vercel.app/department/new");
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
        String actualMessage = basePage.verifyToastMessage();
        String expectedMessage = "Department created successfully.";
        Assert.assertEquals(actualMessage,expectedMessage);
        waitForElement(searchDepartment).sendKeys(departmentName);
        waitForElement(searchDepartment).sendKeys(Keys.ENTER);
        String actualDepartmentName = waitForElement(firstDepartmentNameInList).getText();
        Assert.assertEquals(actualDepartmentName,departmentName);
    }
    public String getDepartmentNameFromPositionTab(){
        waitForElement(clickPositionTab).click();
        return waitForElement(verifyDepartmentNameFromPositionPage).getText();
    }
    public void searchDepartment(String departmentName) {
        waitForElement(clickDepartmentsTab).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
        waitForElement(searchDepartment).click();
        waitForElement(searchDepartment).sendKeys(departmentName);
        waitForElement(searchDepartment).sendKeys(Keys.ENTER);
        waitForElementToBeInvisible(loaderToBeInvisible);
    }
    public void goToDepartmentDetails(String departmentName) {
        searchDepartment(departmentName);
        actions.doubleClick(waitForElement(firstDepartmentNameInList)).perform();
        String actualEditDepartmentText = waitForElement(verifyEditDepartmentText).getText();
        String expectedEditDepartmentText = "Edit Department";
        Assert.assertEquals(actualEditDepartmentText,expectedEditDepartmentText);
    }
    public void deleteDepartmentAndVerifyConfirm() {
        waitForElement(deleteDepartmentBtn).click();
        String actualDeleteConfirmMessage = waitForElement(verifyConfirmDeleteMessage).getText();
        Assert.assertEquals(actualDeleteConfirmMessage, "Confirm");
        waitForElement(confirmDeleteBtn).click();
    }
    public void verifyDepartmentAttachedValidation() {
        String actualDepartmentValidateMessage = basePage.verifyToastMessage();
        String expectedDepartmentValidateMessage = "This department has positions attached. Please remove positions first.";
        Assert.assertEquals(actualDepartmentValidateMessage,expectedDepartmentValidateMessage);
    }
    public void notAbleToDeleteDepartmentWithPosition(){
        String departmentName = getDepartmentNameFromPositionTab();
        goToDepartmentDetails(departmentName);
        deleteDepartmentAndVerifyConfirm();
        verifyDepartmentAttachedValidation();
    }
    public void searchingDepartmentUsingDepartmentNameAndLocationFilter(String departmentName, String location){
        waitForElement(clickFilterBtn).click();
        waitForElement(clickClearBtn).click();
        waitForElement(clickAddBtn).click();
        waitForElement(enterValueForFilter).sendKeys(departmentName);
        waitForElement(clickOKBtn).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
        String expectedDepartmentName = waitForElement(firstDepartmentNameInList).getText();
        Assert.assertEquals(departmentName, expectedDepartmentName);
        String expectedLocationName = waitForElement(verifyFirstLocation).getText();
        Assert.assertEquals(location,expectedLocationName);
    }
    public void searchingDepartmentUsingLocationFilterOnly(String departmentName, String location, String selectOptionFromDropdown){
        waitForElement(clickFilterBtn).click();
        waitForElement(clickClearBtn).click();
        waitForElement(clickAddBtn).click();
        waitForElement(clickColumnDropDown).click();
        List<WebElement> categoryForFiltering = driver.findElements(By.xpath("//li[@role='option']"));
        for(WebElement category : categoryForFiltering){
            if(category.getText().equals(selectOptionFromDropdown)){
                category.click();
            }
        }
        waitForElement(enterValueForFilter).sendKeys(location);
        waitForElement(clickOKBtn).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
        String expectedLocationName = waitForElement(verifyFirstLocation).getText();
        Assert.assertEquals(location,expectedLocationName);
        String expectedDepartmentName = waitForElement(firstDepartmentNameInList).getText();
        Assert.assertEquals(departmentName, expectedDepartmentName);
    }
    public void updateStatusOfDepartmentFromActiveToInactiveAndVerifyBySearch(String departmentName){
        waitForElementToBeInvisible(loaderToBeInvisible);
        waitForElement(searchDepartment).sendKeys(departmentName);
        actions.sendKeys(Keys.ENTER).perform();
        waitForElementToBeInvisible(loaderToBeInvisible);
        actions.doubleClick(waitForElement(firstDepartmentNameInList)).perform();
        waitForElementToBeInvisible(loaderToBeInvisible);
        waitForElement(clickDeActivateBtn).click();
        String actualStatusUpdateMessage = basePage.verifyToastMessage();
        String expectedStatusUpdateMessage = "Department status updated successfully.";
        Assert.assertEquals(actualStatusUpdateMessage,expectedStatusUpdateMessage);
        waitForElementToBeInvisible(loaderToBeInvisible);
        waitForElement(searchDepartment).sendKeys(departmentName);
        actions.sendKeys(Keys.ENTER).perform();
        String actualStatusAfterUpdate = waitForElement(verifyStatus).getText();
        String expectedStatusAfterUpdate = "Inactive";
        Assert.assertEquals(actualStatusAfterUpdate,expectedStatusAfterUpdate);
    }
}