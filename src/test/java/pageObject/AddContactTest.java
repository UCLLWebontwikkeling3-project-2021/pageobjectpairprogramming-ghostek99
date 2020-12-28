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
import static org.junit.Assert.assertTrue;

/**
 * @author Adam Gataev (r0717663)
 * @version Szymon Nidecki
 */

public class AddContactTest {

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
    public void test_AddContact_AllFieldsFilledInCorrectly_ContactIsAdded() {
        //Login as admin
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();

        //Create a contact
        int randomId = (int) (Math.random() * 100);
        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10102020");
        addContactPage.setHour("1000a");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail(randomId + "jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Contact successfully added", addContactPage.contactAdded().getText());
    }

    @Test
    public void test_AddContact_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();


        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10102020");
        addContactPage.setHour("1000a");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("MetinVirus | Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("First name is empty"));
        assertTrue(addContactPage.hasEmptyFirstName());
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        addContactPage.setDate("10102020");
        assertTrue(addContactPage.hasStickyDate("2020-10-10"));
        assertTrue(addContactPage.hasStickyHour("10:00"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("");
        addContactPage.setDate("10102020");
        addContactPage.setHour("1000a");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("MetinVirus | Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("Last name is empty"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasEmptyLastName());
        addContactPage.setDate("10102020");
        assertTrue(addContactPage.hasStickyDate("2020-10-10"));
        assertTrue(addContactPage.hasStickyHour("10:00"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_DateNotFilledIn_ErrorMessageGivenForDateAndOtherFieldsValueKept() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("");
        addContactPage.setHour("1000a");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("MetinVirus | Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("Date is empty"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasEmptyDate());
        assertTrue(addContactPage.hasStickyHour("10:00"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_HourNotFilledIn_ErrorMessageGivenForHourAndOtherFieldsValueKept() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10102020");
        addContactPage.setHour("");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("MetinVirus | Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("Time is empty"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        addContactPage.setDate("10102020");
        assertTrue(addContactPage.hasStickyDate("2020-10-10"));
        assertTrue(addContactPage.hasEmptyHour());
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_GSMNotFilledIn_ErrorMessageGivenForGSMAndOtherFieldsValueKept() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10102020");
        addContactPage.setHour("1000a");
        addContactPage.setGsm("");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("MetinVirus | Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("Phone number is empty"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        addContactPage.setDate("10102020");
        assertTrue(addContactPage.hasStickyDate("2020-10-10"));
        assertTrue(addContactPage.hasStickyHour("10:00"));
        assertTrue(addContactPage.hasEmptyGsm());
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAsAdmin();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10102020");
        addContactPage.setHour("1000a");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("");
        addContactPage.pressButton();

        assertEquals("MetinVirus | Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No email given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        addContactPage.setDate("10102020");
        assertTrue(addContactPage.hasStickyDate("2020-10-10"));
        assertTrue(addContactPage.hasStickyHour("10:00"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasEmptyEmail());
    }
}