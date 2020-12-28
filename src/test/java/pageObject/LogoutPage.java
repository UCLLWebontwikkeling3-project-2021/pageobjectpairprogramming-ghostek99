package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Szymon Nidecki
 */

public class LogoutPage extends Page {

    @FindBy(id = "logOut")
    private WebElement logOutButton;

    public LogoutPage(WebDriver driver) {
        super(driver);
        super.driver.get(getPath() + "/Controller?command=LogOutPage");
    }

    public void setURL(String URL) {
        super.driver.get(getPath() + URL);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public HomePage confirm() {
        logOutButton.click();
        return new HomePage(driver);
    }
}
