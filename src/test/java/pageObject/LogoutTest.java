package pageObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Szymon Nidecki
 */

public class LogoutTest {
    private final String URL = "http://localhost:8080/mijnechtlabo_war_exploded";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ghostek\\Dropbox\\S3\\Web3\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Logged_In_User_Has_Access_To_Log_Out_Page() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        logIn(loginPage);

        LogoutPage logoutPage = PageFactory.initElements(driver, LogoutPage.class);
        Assert.assertEquals("MetinVirus | Log out", logoutPage.getTitle());
    }

    @Test
    public void test_Logged_In_User_Can_Log_Out_Using_Log_Out_Button() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        logIn(loginPage);

        LogoutPage logoutPage = PageFactory.initElements(driver, LogoutPage.class);
        Assert.assertEquals("MetinVirus | Log out", logoutPage.getTitle());

        HomePage homePage = logoutPage.confirm();

        Assert.assertEquals("MetinVirus | Home", homePage.getTitle());
    }

    @Test
    public void test_If_Unknown_User_Attempts_To_Enter_Log_Out_Page_Deny_Access() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals("MetinVirus | Home", homePage.getTitle());

        PageFactory.initElements(driver, LogoutPage.class);
        String error = homePage.getErrorMessage();
        Assert.assertEquals("You have not been authorized to access that page.", error);
    }

    @Test
    public void test_If_Logged_In_User_Tries_To_Log_Out_Using_URL_Only() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.setUsername("admin");
        loginPage.setPassword("t");

        HomePage homePage = loginPage.confirm();
        homePage.driver.get(URL + "/Controller?command=LogOut");
        String error = homePage.getIncorrectLogOutMessage();
        Assert.assertEquals("You must go through the log out page to log out correctly!", error);
    }

    private void logIn(LoginPage loginPage) {
        loginPage.setUsername("admin");
        loginPage.setPassword("t");
        loginPage.confirm();
    }
}
