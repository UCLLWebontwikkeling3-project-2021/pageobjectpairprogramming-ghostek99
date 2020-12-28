package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

/**
 * @author Evert
 * @version Szymon Nidecki
 */

public class ContactOverviewPage extends Page {
    @FindBy(id = "newContact")
    private WebElement addContactButton;

    @FindBy(linkText = "Home")
    private WebElement toHomeButton;

    public ContactOverviewPage(WebDriver driver) {
        super(driver);
        super.driver.get(getPath() + "/Controller?command=Contacts");
    }

    public void fillOutContact(String firstName, String lastName, String date, String hour, String gsm, String email) {
        fillOutContactForm(firstName, lastName, date, hour, gsm, email);
    }

    private void fillOutContactForm(String firstName, String lastName, String date, String hour, String gsm, String email) {
        fillOutField("voornaam", firstName);
        fillOutField("familienaam", lastName);
        fillOutField("datum", date);
        fillOutField("tijd", hour);
        fillOutField("gsm", gsm);
        fillOutField("email", email);
    }

    private void fillOutField(String idName, String value) {
        WebElement field = driver.findElement(By.id(idName));
        field.clear();
        field.sendKeys(value);
    }

    public void submit() {
        addContactButton.click();
    }

    public int countContacts() {
        ArrayList<WebElement> contacts = (ArrayList<WebElement>) driver.findElements(By.id("myContact"));
        return contacts.size();
    }

    public HomePage toHome() {
        toHomeButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }
}