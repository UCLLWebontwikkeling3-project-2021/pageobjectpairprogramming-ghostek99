package pageObject;

import org.openqa.selenium.WebDriver;

/**
 * @author Szymon Nidecki
 */

public abstract class Page {

    WebDriver driver;
    String path = "http://localhost:8080/mijnechtlabo_war_exploded";

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return driver.getTitle();
    }

}