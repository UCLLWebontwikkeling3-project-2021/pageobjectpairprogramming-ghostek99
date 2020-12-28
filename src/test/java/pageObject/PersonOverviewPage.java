package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * @author Lukas De Ruysscher
 * @version Szymon Nidecki
 */

public class PersonOverviewPage extends Page {

    public PersonOverviewPage(WebDriver driver) {
        super(driver);
        super.driver.get(getPath() + "/Controller?command=AllContacts");
    }

    public int countUsers() {
        ArrayList<WebElement> users = (ArrayList<WebElement>) driver.findElements(By.id("myUser"));
        return users.size();
    }
}
