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
 * @author Evert
 * @version Szymon Nidecki
 */

public class ContactsOverviewTest {
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
    public void test_Not_Logged_In_User_Navigates_From_Home_To_Contacts_Throws_Exception() {
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.toContacts();
        assertEquals("You have not been authorized to access that page.", page.getErrorMessage());
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Home_To_Contacts() {
        LoginPage page = PageFactory.initElements(driver, LoginPage.class);
        HomePage homePage = page.loginAsUser();
        homePage.toContacts();
        assertEquals(driver.getTitle(), "MetinVirus | Contacts");
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Contacts_To_Home() {
        LoginPage page = PageFactory.initElements(driver, LoginPage.class);
        page.loginAsUser();
        ContactOverviewPage contactsPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactsPage.toHome();
        assertEquals(driver.getTitle(), "MetinVirus | Home");
    }

    @Test
    public void test_User_Without_Contacts_Shows_No_Contacts() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsUserWithoutContacts();
        ContactOverviewPage page = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals(page.countContacts(), 0);
    }

    @Test
    public void test_User_With_Contacts_Shows_Correct_Amount_Of_Contacts() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsUser();
        ContactOverviewPage page = PageFactory.initElements(driver, ContactOverviewPage.class);
        int originalContacts = page.countContacts();
        for (int i = 0; i < 3; i++) {
            page.fillOutContact("Test", "Je", "11122020", "1340", "0498151515", i + "testje@ucll.be");
            page.submit();
        }
        int contactsNow = page.countContacts();
        assertEquals(originalContacts + 3, contactsNow);
    }
}