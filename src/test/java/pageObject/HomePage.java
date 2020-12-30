package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * @author Szymon Nidecki
 */

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        super.driver.get(getPath() + "/Controller?command=Home");
    }

    public String getErrorMessage() {
        WebElement error = driver.findElement(By.cssSelector(".alert-danger ul li"));
        return error.getText();
    }

    public String getWelcomeMessageAdmin() {
        WebElement error = driver.findElement(By.id("welcome"));
        return error.getText();
    }

    public String getIncorrectLogOutMessage() {
        WebElement error = driver.findElement(By.cssSelector(".alert-danger ul li"));
        return error.getText();
    }

    public void toContacts() {
        super.driver.get(getPath() + "/Controller?command=Contacts");
    }

    public void toAllContacts() {
        super.driver.get(getPath() + "/Controller?command=AllContacts");
    }

    public int getNumberOfItems() {
        ArrayList<WebElement> nav = (ArrayList<WebElement>) driver.findElements(By.cssSelector("nav ul li"));
        return nav.size();
    }
}
