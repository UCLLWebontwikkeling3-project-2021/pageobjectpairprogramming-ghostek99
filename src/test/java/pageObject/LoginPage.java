package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Szymon Nidecki
 */

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
        super.driver.get(getPath() + "/Controller?command=LogInPage");
    }

    public void setUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("userid"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage loginAsAdmin() {
        setUsername("admin");
        setPassword("t");
        return confirm();
    }

    public HomePage loginAsUser() {
        setUsername("user");
        setPassword("hehe");
        return confirm();
    }

    public HomePage loginAsUserWithoutContacts() {
        setUsername("userke");
        setPassword("tek");
        return confirm();
    }

    public void logInWithInCorrectCredentials() {
        setUsername("admins");
        setPassword("sdknfgoijshiofsiuo");
        WebElement logInButton = driver.findElement(By.id("logIn"));
        logInButton.click();
    }

    public void logInWithInCorrectPassword() {
        setUsername("sioehnfijoshfiosh");
        setPassword("sdknfgoijshiofsiuo");
        WebElement logInButton = driver.findElement(By.id("logIn"));
        logInButton.click();
    }

    public void logInWithoutPassword() {
        setUsername("sioehnfijoshfiosh");
        setPassword("");
        WebElement logInButton = driver.findElement(By.id("logIn"));
        logInButton.click();
    }

    public void logInWithoutUsername() {
        setUsername("");
        setPassword("t");
        WebElement logInButton = driver.findElement(By.id("logIn"));
        logInButton.click();
    }

    public HomePage confirm() {
        WebElement logInButton = driver.findElement(By.id("logIn"));
        logInButton.click();
        return new HomePage(driver);
    }

    public String getErrorMessage() {
        WebElement error = driver.findElement(By.cssSelector(".alert-danger ul li"));
        return error.getText();
    }
}
