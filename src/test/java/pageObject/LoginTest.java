package pageObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @version Szymon Nidecki
 */

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ghostek\\Dropbox\\S3\\Web3\\Tools\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_login_with_valid_info() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        HomePage homePage = loginPage.loginAsAdmin();

        assertEquals("MetinVirus | Home", homePage.getTitle());
        assertEquals(6, homePage.getNumberOfItems());
    }

    @Test
    public void test_login_with_invalid_info() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.logInWithInCorrectCredentials();

        assertEquals("The credentials are invalid.", loginPage.getErrorMessage());
    }

    @Test
    public void test_login_with_invalid_password() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.logInWithInCorrectPassword();

        assertEquals("The credentials are invalid.", loginPage.getErrorMessage());
    }

    @Test
    public void test_login_with_empty_password() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.logInWithoutPassword();

        assertEquals("The credentials are invalid.", loginPage.getErrorMessage());
    }

    @Test
    public void test_login_with_empty_userId() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.logInWithoutUsername();

        assertEquals("The credentials are invalid.", loginPage.getErrorMessage());
    }
}
