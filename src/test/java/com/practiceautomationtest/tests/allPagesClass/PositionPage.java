package com.practiceautomationtest.tests.allPagesClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class PositionPage extends BasePage{
    Actions actions = new Actions(driver);
    By newButton = By.xpath("//button[contains(text(),'New')]");
    By clickPositionTab = By.xpath("//span[contains(text(),'Position')]");
    By visibleCreatePositionText = By.xpath("//p[contains(text(),'Create Position')]");
    By visibleEditPositionText = By.xpath("//p[contains(text(),'Edit Position')]");
    By enterPositionName = By.id("positionName");
    By selectDepartmentBtn = By.xpath("//input[@id='departmentName']/following-sibling::div/button");
    By selectDepartmentText = By.xpath("//div[@title='Chris Jones']");
    By editDepartmentText = By.xpath("//div[contains(text(),'Alex smith')]");
    By enterResponsibilities = By.id("responsibilities");
    By enterQualifications = By.id("qualifications");
    By clickSaveButton = By.xpath("//button[contains(text(),'Save')]");
    By verifyPositionCreatedToastMessage = By.xpath("//p[contains(text(),'Position created successfully.')]");
    By searchPosition = By.id("search");
    By verifyFirstPosition = By.xpath("(//div[@data-field='positionName'])[2]");
    By positionUpdateToastMessage = By.xpath("//p[contains(text(),'Updated the position details successfully')]");
    By positionCount = By.xpath("//div/*[contains(normalize-space(text()),'Manager_175800')]");
    By deleteBtn = By.xpath("(//button[text()='Delete'])[1]");
    By confirmDeleteBtn = By.xpath("(//button[text()='Delete'])[3]");
    By deletePositionToastMessage = By.xpath("//p[contains(text(),'Removed the position details successfully')]");
    By noPositionAvailable = By.xpath("//p[contains(text(),'0–0 of 0')]");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");

    public PositionPage(WebDriver driver){
        super(driver);
    }
    public void clickNewBtn(){
        waitForElement(newButton).click();
    }
    public void visitCreatePosition(){
        visit("https://employee-cicd.vercel.app/position/new");
    }
    public void visibleCreatePositionText(){
        waitForElement(visibleCreatePositionText);
    }
    public void visibleEditPositionText(){
        waitForElement(visibleEditPositionText);
    }
    public void executeAddPosition(String positionName, String responsibilities, String qualifications){
        waitForElement(clickPositionTab).click();
        clickNewBtn();
        visitCreatePosition();
        visibleCreatePositionText();
        waitForElement(enterPositionName).sendKeys(positionName);
        waitForElement(selectDepartmentBtn).click();
        WebElement selectDepartmentClick = waitForElement(selectDepartmentText);
        actions.doubleClick(selectDepartmentClick).perform();
        waitForElement(enterResponsibilities).sendKeys(responsibilities);
        waitForElement(enterQualifications).sendKeys(qualifications);
        waitForElement(clickSaveButton).click();
    }
    public String verifyPositionCreateMessage(){
        return waitForElement(verifyPositionCreatedToastMessage).getText();
    }
    public void verifyPositionAddedBySearch(String searchPositionName){
        waitForElement(searchPosition).sendKeys(searchPositionName);
        waitForElement(searchPosition).sendKeys(Keys.ENTER);
    }
    public void verifyPositionEditedAddedBySearch(String searchPositionName){
        waitForElement(searchPosition).sendKeys(searchPositionName);
        waitForElement(searchPosition).sendKeys(Keys.ENTER);
        waitForElementToBeInvisible(loaderToBeInvisible);
    }
    public String verifySearchedPositionName() {
        return waitForElement(verifyFirstPosition).getText();
    }
    public void editPosition(String newName){
        WebElement doubleClickForEditPosition = waitForElement(verifyFirstPosition);
        actions.doubleClick(doubleClickForEditPosition).perform();
        visibleEditPositionText();
        elementToBeClick(enterPositionName);
        waitForElement(enterPositionName).click();
        waitForElement(enterPositionName).sendKeys(Keys.chord(Keys.CONTROL,"a"));
        waitForElement(enterPositionName).sendKeys(Keys.DELETE);
        waitForElement(enterPositionName).sendKeys(newName);
        waitForElement(selectDepartmentBtn).click();
        WebElement selectDepartmentClick = waitForElement(editDepartmentText);
        actions.doubleClick(selectDepartmentClick).perform();
        waitForElement(clickSaveButton).click();
    }
    public String positionUpdateMessage(){
        return waitForElement(positionUpdateToastMessage).getText();
    }
    public void executeSearchPosition(String enterPositionName){
        waitForElement(clickPositionTab).click();
        waitForElement(searchPosition).sendKeys(enterPositionName);
        waitForElement(searchPosition).sendKeys(Keys.ENTER);
        List<WebElement> positions = presenceOfAllElements(positionCount);
        for(WebElement pos : positions){
            System.out.println(pos.getText());
        }
        System.out.println("Total Count : "+positions.size());
    }
    public void elementToBeClick(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void executeDeletePosition(){
        WebElement doubleClickForEditPosition = waitForElement(verifyFirstPosition);
        actions.doubleClick(doubleClickForEditPosition).perform();
        visibleEditPositionText();
        elementToBeClick(deleteBtn);
        waitForElement(deleteBtn).click();
        waitForElement(confirmDeleteBtn).click();
    }
    public String removedPositionMessage(){
        return waitForElement(deletePositionToastMessage).getText();
    }
    public void verifyDeletedPositionBySearch(String deleteEditedPosition){
        waitForElement(searchPosition).sendKeys(deleteEditedPosition);
        waitForElement(searchPosition).sendKeys(Keys.ENTER);
        waitForElement(noPositionAvailable);
    }
//    public void HashMapPractice(){
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://practice.expandtesting.com/tables");
//        WebElement table = driver.findElement(By.id("table1"));
//        List<WebElement> rows = table.findElements(By.xpath("//div//table[(@id='table1')]//tbody//tr"));
//
//        for (int i = 0; i < rows.size(); i++) {
//            WebElement row = rows.get(i);
//
//            List<WebElement> cols = row.findElements(By.tagName("td"));
//
//            HashMap<String, String> rowData = new HashMap<>();
//            rowData.put("Last Name", cols.get(0).getText());
//            rowData.put("First Name", cols.get(1).getText());
//            rowData.put("Email", cols.get(2).getText());
//            rowData.put("Due", cols.get(3).getText());
//            rowData.put("Web Site", cols.get(4).getText());
//            rowData.put("Action", cols.get(5).getText());
//
//            System.out.println("Row " + (i + 1) + ": " + rowData);
//        }
//        driver.quit();
//    }
}
