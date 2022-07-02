package am.jtrain.addressbook.web.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        clickElement(By.linkText("groups"));
    }

    public void goToContactPage() {
        clickElement(By.linkText("home"));
    }
}
