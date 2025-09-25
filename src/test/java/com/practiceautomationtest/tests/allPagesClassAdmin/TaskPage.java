package com.practiceautomationtest.tests.allPagesClassAdmin;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class TaskPage extends BasePage{
    BasePage basePage = new BasePage(driver);
    Actions actions = new Actions(driver);
    Faker faker = new Faker();
    By clickTasksTab = By.xpath("//span[contains(text(),'Tasks')]");
    By newButton = By.xpath("//button[contains(text(),'New')]");
    By visibleCreateTaskText = By.xpath("//p[contains(text(),'Create Task')]");
    By taskNameInputField = By.id("taskName");
    By assigneeSelectBtn = By.xpath("(//button[@type='button'])[7]");
    By assigneeEmailField = By.xpath("(//div[@data-field='firstName'])[3]");
    By taskDateSelectionBtn = By.xpath("(//button[@type='button'])[8]");
    By selectDate = By.xpath("//button[contains(text(),'25')]");
    By taskDescInputField = By.id("taskDescription");
    By saveBtn = By.xpath("//button[contains(text(),'Save')]");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By searchTaskName = By.id("search");
    By verifyAddedTask = By.xpath("(//div[@data-field='taskName'])[2]");

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
        waitForElement(selectDate).click();
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
    public void addNewTask(){
        String taskName = "Task: " + faker.company().buzzword();
        String taskDescription = faker.lorem().sentence(10);

        navigateToTaskPage();
        enterTaskName(taskName);
        selectAssignee();
        selectTaskDate();
        enterTaskDescription(taskDescription);
        saveTask();
        verifyTaskCreated(taskName);
    }
}
