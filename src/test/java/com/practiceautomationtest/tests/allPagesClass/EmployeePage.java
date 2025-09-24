package com.practiceautomationtest.tests.allPagesClass;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class EmployeePage extends BasePage {
    Actions actions = new Actions(driver);
    Faker faker = new Faker();
    By clickEmployeeTab = By.xpath("//span[contains(text(),'Employees')]");
    By newBtn = By.xpath("//button[contains(text(),'New')]");
    By visibleCreateEmployeeText = By.xpath("//p[contains(text(),'Create Employee')]");
    By emailInputField = By.xpath("//input[@id='email']");
    By userTypeDropDown = By.xpath("//div[@id='id__userType']");
    By waitForUserTypeDropDown = By.xpath("//li[@role='option']");
    By firstNameInputField = By.id("firstName");
    By middleNameInputField = By.id("middleName");
    By lastNameInputField = By.id("lastName");
    By addressBtn = By.xpath("//button[@variant='contained']");
    By addressLine1 = By.xpath("//input[@id='addrline1']");
    By addressLine2 = By.xpath("//input[@id='addrline2']");
    By citySelectionBtn = By.xpath("//input[@id='city']/following-sibling::div/button");
    By searchInputField = By.id("search");
    By selectCityName = By.xpath("(//div[@data-field='cityName'])[2]");
    By stateSelectionBtn = By.xpath("//input[@id='state']/following-sibling::div/button");
    By stateSelectionName = By.xpath("(//div[@data-field='stateName'])[2]");
    By postalCodeInputField = By.id("postalcode");
    By clickingOkBtn = By.xpath("//button[contains(text(),'OK')]");
    By addressCreatedToastMessage = By.xpath("//div//p[contains(text(),'Address created successfully.')]");
    By genderTypeDropDown = By.xpath("//div[@id='id__gender']");
    By waitForGenderTypeDropDown = By.xpath("//li[@role='option']");
    By mobilePhoneInputField = By.id("mobilePhone");
    By departmentSelectionBtn = By.xpath("//input[@id='departmentName']/following-sibling::div/button");
    By departmentNameSelection = By.xpath("(//div[@data-field='departmentName'])[2]");
    By positionSelectionBtn = By.xpath("//input[@id='positionName']/following-sibling::div/button");
    By positionNameSelection = By.xpath("(//div[contains(@data-field,'positionName')])[2]");
    By saveBtn = By.xpath("//button[contains(text(),'Save')]");
    By employeeCreatedToastMessage = By.xpath("//p[contains(text(),'Employee created successfully.')]");
    By loaderToBeInvisible = By.xpath("//span[contains(@role,'progressbar')]");
    By verifyFirstEmployeeNameBySearch = By.xpath("(//div[@data-field='lastName'])[2]");
    By employeeUpdatedToastMessage = By.xpath("//p[contains(text(),'Employee details updated successfully.')]");

    public EmployeePage(WebDriver driver){
        super(driver);
    }
    public void elementToBeClick(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void loaderToBeInvisible(){
        waitForElementToBeInvisible(loaderToBeInvisible);
    }
    public void addressCreatedToastMessage(){
        String actualAddressCreatedToastMessage = waitForElement(addressCreatedToastMessage).getText();
        String expectedAddressCreatedMessage = "Address created successfully.";
        Assert.assertEquals(actualAddressCreatedToastMessage,expectedAddressCreatedMessage);
    }
    public void enterAddressLines(String line1, String line2) {
        waitForElement(addressLine1).sendKeys(line1);
        waitForElement(addressLine2).sendKeys(line2);
    }
    public void selectCity(String citySelection) {
        elementToBeClick(citySelectionBtn);
        waitForElement(citySelectionBtn).click();
        loaderToBeInvisible();
        waitForElement(searchInputField).sendKeys(citySelection);
        waitForElement(searchInputField).sendKeys(Keys.ENTER);
        loaderToBeInvisible();
        actions.doubleClick(waitForElement(selectCityName)).perform();
    }
    public void selectState(String stateSelection) {
        elementToBeClick(stateSelectionBtn);
        waitForElement(stateSelectionBtn).click();
        loaderToBeInvisible();
        waitForElement(searchInputField).sendKeys(stateSelection);
        loaderToBeInvisible();
        actions.doubleClick(waitForElement(stateSelectionName)).perform();
    }
    public void enterPostalCode(String postalCode) {
        waitForElement(postalCodeInputField).sendKeys(String.valueOf(postalCode));
    }
    public void saveAddress() {
        waitForElement(clickingOkBtn).click();
        addressCreatedToastMessage();
    }
    public void addAddress(String citySelection, String stateSelection) {
        String line1 = faker.address().streetAddress();
        String line2 = faker.address().streetAddress();
        String postalCode = faker.number().digits(6);

        enterAddressLines(line1, line2);
        selectCity(citySelection);
        selectState(stateSelection);
        enterPostalCode(postalCode);
        saveAddress();
    }
    public void clearAndEnterAddressLines(String line1, String line2) {
        waitForElement(addressLine1).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(addressLine1).sendKeys(Keys.DELETE);
        waitForElement(addressLine1).sendKeys(line1);

        waitForElement(addressLine2).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(addressLine2).sendKeys(Keys.DELETE);
        waitForElement(addressLine2).sendKeys(line2);
    }
    public void editAddress(String citySelection, String stateSelection){
        String line1 = faker.address().streetAddress();
        String line2 = faker.address().streetAddress();
        String postalCode = faker.number().digits(6);

        clearAndEnterAddressLines(line1, line2);
        selectCity(citySelection);
        selectState(stateSelection);
        enterPostalCode(postalCode);
        saveAddress();
    }
    public void departmentSelection(String departmentSelection){
        waitForElement(departmentSelectionBtn).click();
        loaderToBeInvisible();
        waitForElement(searchInputField).sendKeys(departmentSelection);
        waitForElement(searchInputField).sendKeys(Keys.ENTER);
        loaderToBeInvisible();
        waitForElement(departmentNameSelection);
        actions.doubleClick(waitForElement(departmentNameSelection)).perform();
    }
    public void positionSelection(String positionSelection){
        waitForElement(positionSelectionBtn).click();
        loaderToBeInvisible();
        waitForElement(searchInputField).sendKeys(positionSelection);
        waitForElement(searchInputField).sendKeys(Keys.ENTER);
        loaderToBeInvisible();
        waitForElement(positionNameSelection);
        actions.doubleClick(waitForElement(positionNameSelection)).perform();
    }
    public void employeeCreatedMessage(){
        String actualEmployeeCreatedMessage = waitForElement(employeeCreatedToastMessage).getText();
        String expectedEmployeeCreateMessage = "Employee created successfully.";
        Assert.assertEquals(actualEmployeeCreatedMessage,expectedEmployeeCreateMessage);
    }
    public void userDropDrownSelection(String userTypeSelection){
        waitForElement(userTypeDropDown).click();
        waitForElement(waitForUserTypeDropDown);

        List<WebElement> userTypeOptions = driver.findElements(By.xpath("//li[@role='option']"));
        for(WebElement option : userTypeOptions){
            if(option.getText().equals(userTypeSelection)){
                option.click();
                break;
            }
        }
    }
    public void genderDropDrownSelection(String genderTypeSelection){
        waitForElement(genderTypeDropDown).click();
        waitForElement(waitForGenderTypeDropDown);

        List<WebElement> genderTypeOptions = driver.findElements(By.xpath("//li[@role='option']"));
        for(WebElement option : genderTypeOptions){
            if(option.getText().equals(genderTypeSelection)){
                option.click();
                break;
            }
        }
    }
    public void verifyEmployeeAddedBySearch(String verifyEmployeeAddedName){
        waitForElement(searchInputField).click();
        waitForElement(searchInputField).sendKeys(verifyEmployeeAddedName);
        waitForElement(searchInputField).sendKeys(Keys.ENTER);
        loaderToBeInvisible();
        String actualEmployeeAddedName = waitForElement(verifyFirstEmployeeNameBySearch).getText();
        Assert.assertEquals(actualEmployeeAddedName,verifyEmployeeAddedName);
    }
    public void verifyEmployeeUpdatedBySearch(String verifyEmployeeUpdatedName){
        waitForElement(searchInputField).click();
        waitForElement(searchInputField).sendKeys(verifyEmployeeUpdatedName);
        waitForElement(searchInputField).sendKeys(Keys.ENTER);
        loaderToBeInvisible();
        String actualEmployeeUpdatedName = waitForElement(verifyFirstEmployeeNameBySearch).getText();
        Assert.assertEquals(actualEmployeeUpdatedName,verifyEmployeeUpdatedName);
    }
    public void navigateToAddEmployeePage() {
        waitForElement(clickEmployeeTab).click();
        waitForElement(newBtn).click();
        waitForElement(visibleCreateEmployeeText).getText();
    }
    public void enterEmployeeBasicDetails(String email, String firstName, String middleName, String lastName) {
        elementToBeClick(emailInputField);
        waitForElement(emailInputField).sendKeys(email);

        userDropDrownSelection("Employee");

        elementToBeClick(firstNameInputField);
        waitForElement(firstNameInputField).sendKeys(firstName);

        elementToBeClick(middleNameInputField);
        waitForElement(middleNameInputField).sendKeys(middleName);

        elementToBeClick(lastNameInputField);
        waitForElement(lastNameInputField).sendKeys(lastName);
    }
    public void addEmployeeAddress(String city, String state) {
        waitForElement(addressBtn).click();
        addAddress(city, state);
    }
    public void selectGender(String gender) {
        genderDropDrownSelection(gender);
    }
    public void enterMobileNumber(String mobileNumber) {
        elementToBeClick(mobilePhoneInputField);
        waitForElement(mobilePhoneInputField).sendKeys(mobileNumber);
    }
    public void selectDepartmentAndPosition(String department, String position) {
        departmentSelection(department);
        positionSelection(position);
    }
    public void saveEmployee() {
        waitForElement(saveBtn).click();
        employeeCreatedMessage();
    }
    public void verifyEmployee(String expectedFullName) {
        verifyEmployeeAddedBySearch(expectedFullName);
    }
    public void executeAddEmployee(){
        String firstName = faker.name().firstName();
        String middleName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String emailAddress = faker.internet().emailAddress();
        String mobileNumber = faker.number().digits(10);
        String expectedFullName = firstName + " " + middleName + " " + lastName;

        navigateToAddEmployeePage();
        enterEmployeeBasicDetails(emailAddress, firstName, middleName, lastName);
        addEmployeeAddress("Ahmedabad", "Gujarat");
        selectGender("Male");
        enterMobileNumber(mobileNumber);
        selectDepartmentAndPosition("EC Department", "Wire Man");
        saveEmployee();
        verifyEmployee(expectedFullName);
    }
    public void employeeUpdatedToastMessage(){
        String actualEmployeeUpdatedToastMessage = waitForElement(employeeUpdatedToastMessage).getText();
        String expectedEmployeeUpdatedToastMessage = "Employee details updated successfully.";
        Assert.assertEquals(actualEmployeeUpdatedToastMessage,expectedEmployeeUpdatedToastMessage);
    }
    public void openFirstEmployeeForEditing() {
        actions.doubleClick(waitForElement(verifyFirstEmployeeNameBySearch)).perform();
    }
    public void updateUserRole(String role) {
        userDropDrownSelection(role);
    }
    public void updateEmployeeName(String firstName, String middleName, String lastName) {
        // First Name
        elementToBeClick(firstNameInputField);
        waitForElement(firstNameInputField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(firstNameInputField).sendKeys(Keys.DELETE);
        waitForElement(firstNameInputField).sendKeys(firstName);

        // Middle Name
        elementToBeClick(middleNameInputField);
        waitForElement(middleNameInputField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(middleNameInputField).sendKeys(Keys.DELETE);
        waitForElement(middleNameInputField).sendKeys(middleName);

        // Last Name
        elementToBeClick(lastNameInputField);
        waitForElement(lastNameInputField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(lastNameInputField).sendKeys(Keys.DELETE);
        waitForElement(lastNameInputField).sendKeys(lastName);
    }
    public void updateEmployeeAddress(String city, String state) {
        waitForElement(addressBtn).click();
        editAddress(city, state);
    }
    public void updateGender(String gender) {
        genderDropDrownSelection(gender);
    }
    public void updateMobileNumber(String mobileNumber) {
        elementToBeClick(mobilePhoneInputField);
        waitForElement(mobilePhoneInputField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(mobilePhoneInputField).sendKeys(Keys.DELETE);
        waitForElement(mobilePhoneInputField).sendKeys(mobileNumber);
    }
    public void saveUpdatedEmployee() {
        waitForElement(saveBtn).click();
        employeeUpdatedToastMessage();
    }
    public void verifyUpdatedEmployee(String expectedFullName) {
        verifyEmployeeUpdatedBySearch(expectedFullName);
    }
    public void executeEditEmployee(){
        String firstNameInEdit = faker.name().firstName();
        String middleNameInEdit = faker.name().firstName();
        String lastNameInEdit = faker.name().lastName();
        String mobileNumberInEdit = faker.number().digits(10);
        String expectedFullNameInEdit = firstNameInEdit + " " + middleNameInEdit + " " + lastNameInEdit;
        executeAddEmployee();

        // First add an employee
        executeAddEmployee();

        // Edit that employee
        openFirstEmployeeForEditing();
        updateUserRole("Admin");
        updateEmployeeName(firstNameInEdit, middleNameInEdit, lastNameInEdit);
        updateEmployeeAddress("Coimbatore", "Tamil Nadu");
        updateGender("Female");
        updateMobileNumber(mobileNumberInEdit);
        saveUpdatedEmployee();
        verifyUpdatedEmployee(expectedFullNameInEdit);
    }
}