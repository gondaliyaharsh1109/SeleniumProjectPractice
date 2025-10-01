package com.practiceautomationtest.tests.allPagesClassAdmin;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskPage extends BasePage{
    BasePage basePage = new BasePage(driver);
    Actions actions = new Actions(driver);
    Faker faker = new Faker();
    LoginPage loginPage = new LoginPage(driver);
    By clickTasksTab = By.xpath("//span[contains(text(),'Tasks')]");
    By newButton = By.xpath("//button[contains(text(),'New')]");
    By visibleCreateTaskText = By.xpath("//p[contains(text(),'Create Task')]");
    By taskNameInputField = By.id("taskName");
    By assigneeSelectBtn = By.xpath("(//button[@type='button'])[7]");
    By assigneeEmailField = By.xpath("(//div[@data-field='firstName'])[3]");
    By taskDateSelectionBtn = By.xpath("(//button[@type='button'])[8]");
    By selectTodayDate = By.xpath("//button[@role='gridcell']");
    By taskDescInputField = By.id("taskDescription");
    By saveBtn = By.xpath("//button[contains(text(),'Save')]");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By searchTaskName = By.id("search");
    By verifyAddedTask = By.xpath("(//div[@data-field='taskName'])[2]");
    By clickBtnForLogout = By.xpath("(//button)[1]");
    By clickOnLogoutBtn = By.xpath("//p[contains(text(),'Logout')]");
    By verifyLoginText = By.xpath("//h5[contains(text(),'LOGIN')]");
    By clickOnTaskTab = By.xpath("//span[contains(text(),'Tasks')]");
    By verifyFirstTaskAsEmployee = By.xpath("(//div[@data-field='taskName'])[2]");
    By clickOnStatusDropdown = By.xpath("//div[@operator='eq']");
    By allListOfStatusDropdown = By.xpath("//li[@role='option']");

    public TaskPage(WebDriver driver){
        super(driver);
    }
    public void navigateToTaskPage() {
        waitForElement(clickTasksTab).click();
        waitForElement(newButton).click();
        String actualVisibleCreateTaskText = waitForElement(visibleCreateTaskText).getText();
        Assert.assertEquals(actualVisibleCreateTaskText, "Create Task");
    }
    public void enterTaskName(String taskName) {
        waitForElement(taskNameInputField).sendKeys(taskName);
    }
    public void selectAssignee() {
        waitForElement(assigneeSelectBtn).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
        actions.doubleClick(waitForElement(assigneeEmailField)).perform();
    }
    public void selectTaskDate() {
        waitForElement(taskDateSelectionBtn).click();
        waitForElement(selectTodayDate).sendKeys(basePage.getTodayDate());
        waitForElement(selectTodayDate).click();
    }
    public void enterTaskDescription(String taskDescription) {
        waitForElement(taskDescInputField).sendKeys(taskDescription);
    }
    public void saveTask() {
        waitForElement(saveBtn).click();
        String actualTaskCreatedMessage = basePage.verifyToastMessage();
        Assert.assertEquals(actualTaskCreatedMessage, "Task created successfully.");
    }
    public void verifyTaskCreated(String taskName) {
        waitForElement(searchTaskName).sendKeys(taskName);
        waitForElement(searchTaskName).sendKeys(Keys.ENTER);
        waitForElementToBeInvisible(loaderToBeInvisible);
        String actualTaskAddedName = waitForElement(verifyAddedTask).getText();
        Assert.assertEquals(actualTaskAddedName, taskName);
    }
    public void executeLogout() {
        waitForElement(clickBtnForLogout).click();
        waitForElement(clickOnLogoutBtn).click();
        waitForElementToBeInvisible(loaderToBeInvisible);
    }
    public void addNewTask(){
        String taskName = "Task: " + faker.company().buzzword();
        String taskDescription = faker.lorem().sentence(10);

        navigateToTaskPage();
        enterTaskName(taskName);
        selectAssignee();
        selectTaskDate();
        enterTaskDescription(taskDescription);
        saveTask();
//        verifyTaskCreated(taskName);
    }
    public void taskStatusDropdown(String expectedStatus){
        List<WebElement> statusList = driver.findElements(By.xpath("//li[@role='option']"));
        for(WebElement listOfStatus : statusList){
            if(listOfStatus.getText().equals(expectedStatus)){
                listOfStatus.click();
            }
        }
    }
    public void addNewTaskAndVerifyInEmployee(){
        String taskName = "Task: " + faker.company().buzzword();
        String taskDescription = faker.lorem().sentence(10);

        navigateToTaskPage();
        enterTaskName(taskName);
        selectAssignee();
        selectTaskDate();
        enterTaskDescription(taskDescription);
        saveTask();
        executeLogout();
        Assert.assertEquals(waitForElement(verifyLoginText).getText(),"LOGIN");
        loginPage.executeLoginEmployeeAfterUpdatingPerformingActionsAsAdmin("adena.leannon@yahoo.com","Pytheta123!",true);
        waitForElement(clickTasksTab).click();
        waitForElement(clickOnStatusDropdown).click();
        taskStatusDropdown("Todo");
        waitForElementToBeInvisible(loaderToBeInvisible);
        String expectedTaskName = waitForElement(verifyFirstTaskAsEmployee).getText();
        Assert.assertEquals(taskName,expectedTaskName);
    }
}
