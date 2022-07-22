package am.jtrain.addressbook.web.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groups() {
        clickElement(By.linkText("groups"));
    }

    public void contacts() {
        clickElement(By.linkText("home"));
    }
}
