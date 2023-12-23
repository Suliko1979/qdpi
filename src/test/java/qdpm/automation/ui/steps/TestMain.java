package qdpm.automation.ui.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import qdpm.automation.ui.utils.ConfiReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qdpm.automation.ui.pages.PageLocators;


public class TestMain {
    private WebDriver driver;
    private PageLocators page;

    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


        page = PageFactory.initElements(driver, PageLocators.class);

        // Navigate to the login page or perform any setup needed
        driver.get(ConfiReader.getPropertiesValue("qdpm.mainPageURl"));

        page.login("administrator@localhost.com", "administrator");
    }

    @Test(priority = 1)
    public void loginAndCreateUserTest() throws InterruptedException {
        // Perform login using the PageObjects class
        page.createUser();
    }

        // Add assertions or verifications as needed to validate the login
        // For example, you might check if the login was successful by verifying elements on the next page.

    @AfterClass
    public void tearDown() {
        // Close the WebDriver after tests are completed
        if (driver != null) {
            driver.quit();
        }
    }
}
