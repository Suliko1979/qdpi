package qdpm.automation.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import qdpm.automation.ui.utils.ConfiReader;
import org.testng.Assert;

//this folder contains page locators and methods (actions) chain
public class PageLocators extends BasePage {
    public PageLocators(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//input[@placeholder='Email']")
    protected WebElement loginEmail;

    @FindBy(css = "input[placeholder='Password']")
    protected WebElement passwordTxt;

    @FindBy(xpath = " //button[@type='submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[8]/a[1]")
    private WebElement clickUserBtn;

    @FindBy(xpath = "//span[@class='title' and text()='Add User']")
    private WebElement addUserBtn;

    @FindBy(css = "#uniform-users_active")
    private WebElement activeUserCheckBox;

    @FindBy(xpath = "//select[@id='users_users_group_id']")
    private WebElement groupBtn;

    @FindBy(css = "#users_name")
    private WebElement userName;

    @FindBy(css="#users_password")
    private WebElement userPassword;

    @FindBy(id = "users_email")
    private WebElement userEmail;

    @FindBy(id="users_photo")
    private WebElement userPhoto;

    @FindBy(id="users_culture")
    private WebElement language;

    @FindBy(id="submit_button")
    private WebElement save;

    @FindBy(xpath = "//div[@class='error' and text()='Email already exists']")
    private WebElement errorMessage;

    @FindBy(xpath = "//i[@class='fa fa-search']")
    private WebElement searchHover;

    @FindBy (id = "search_keywords")
    private WebElement searchBox;

    public void login(String username, String password) throws InterruptedException {
        loginEmail.sendKeys(ConfiReader.getPropertiesValue("adminLogin"));
        passwordTxt.sendKeys(ConfiReader.getPropertiesValue("adminPassword"));
        submitBtn.click();
        Thread.sleep(3000);
    }

    public void createUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        clickUserBtn.click();
        WebElement adduser = wait.until(ExpectedConditions.elementToBeClickable(addUserBtn));
        addUserBtn.click();

        String userEmailValue = "example@gmail.com";
        // Instantiate WebDriverWait


        // Wait for the activeUserCheckBox to be clickable
        WebElement activeUserCheckbox = wait.until(ExpectedConditions.elementToBeClickable(groupBtn));
        Select dropdown = new Select(groupBtn);
        dropdown.selectByValue("3");

        userName.sendKeys("Sultanos");
        userPassword.sendKeys("123$test");
        userEmail.sendKeys(userEmailValue);
        userPhoto.sendKeys("/Users/user1/Desktop/ForU.png");


        Select lang = new Select(language);
        lang.selectByVisibleText("English");

        save.click();

        try {
            WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOf(errorMessage));
            String actualErrorMessage = errorMessageElement.getText();
            String expectedErrorMessage = "Email already exists\n" +
                    "You can't create user with email: \"" + userEmailValue + "\"";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not as expected");
        } catch (TimeoutException e) {
            System.out.println("Error message did not appear within the specified timeout.");
        }


        Actions action= new Actions(driver);
        action.moveToElement(searchHover).perform();

       WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchBox));

        searchBox.sendKeys("Sultanos");

        driver.findElement(By.xpath("//input[@value='Search']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@class='group-checkable']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
        Thread.sleep(2000);

        // Wait until the alert is present
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Accept the alert (press "OK" button)
        alert.accept();
    }
}




