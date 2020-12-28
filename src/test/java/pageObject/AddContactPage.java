package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Adam Gataev (r0717663)
 * @version Szymon Nidecki
 */

public class AddContactPage extends Page {

    @FindBy(id = "voornaam")
    private WebElement firstNameField;

    @FindBy(id = "familienaam")
    private WebElement lastNameField;

    @FindBy(id = "datum")
    private WebElement dateField;

    @FindBy(id = "tijd")
    private WebElement hourField;

    @FindBy(id = "gsm")
    private WebElement gsmField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "newContact")
    private WebElement signUpButton;

    public AddContactPage(WebDriver driver) {
        super(driver);
        super.driver.get(getPath() + "/Controller?command=Contacts");
    }

    public WebElement contactAdded() {
        return driver.findElement(By.cssSelector(".alert-success ul li"));
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void setHour(String hour) {
        hourField.clear();
        hourField.sendKeys(hour);
    }

    public void setGsm(String gsm) {
        gsmField.clear();
        gsmField.sendKeys(gsm);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void pressButton() {
        signUpButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector(".alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyFirstName(String firstname) {
        return firstname.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName(String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyDate(String date) {
        return date.equals(dateField.getAttribute("value"));
    }

    public boolean hasStickyHour(String hour) {
        return hour.equals(hourField.getAttribute("value"));
    }

    public boolean hasStickyGsm(String gsm) {
        return gsm.equals(gsmField.getAttribute("value"));
    }

    public boolean hasStickyEmail(String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName() {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName() {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyDate() {
        return dateField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyHour() {
        return hourField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyGsm() {
        return gsmField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail() {
        return emailField.getAttribute("value").isEmpty();
    }
}