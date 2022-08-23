package am.jtrain.mantis.appmanager;

import org.openqa.selenium.*;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
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
}
