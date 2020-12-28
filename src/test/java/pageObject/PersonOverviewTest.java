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
 * @author Lukas De Ruysscher
 * @version Szymon Nidecki
 */

public class PersonOverviewTest {
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
    public void test_User_Not_Logged_In_Shows_Error() {
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.toAllContacts();
        assertEquals("You have not been authorized to access that page.", page.getErrorMessage());
    }

    @Test
    public void test_User_Is_Logged_In_But_Has_No_Rights_To_Access_Page() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        HomePage homePage = loginPage.loginAsUser();
        homePage.toAllContacts();
        assertEquals("You have not been authorized to access that page.", homePage.getErrorMessage());
    }

    @Test
    public void test_User_Is_Admin_And_Shows_Overview() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);

        assertEquals("MetinVirus | All users", personOverviewPage.getTitle());
        assertEquals(3, personOverviewPage.countUsers());
    }
}