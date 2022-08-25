package am.jtrain.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {

    protected final ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void enterText(By locator, String text) {
        if (text != null) {
            String currentText = wd.findElement(locator).getAttribute("value");
            if (! currentText.equals(text)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void clickElement(By locator) {
        wd.findElement(locator).click();
    }

    public void waitForElement (By locator, Integer duration) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
