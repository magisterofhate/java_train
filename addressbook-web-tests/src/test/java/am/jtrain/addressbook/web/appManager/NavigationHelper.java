package am.jtrain.addressbook.web.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groups() {
        clickElement(By.linkText("groups"));
    }

    public void groupWithContacts(Integer g_id) {
        Select drpCountry = new Select(wd.findElement(By.name("group")));
        drpCountry.selectByValue(String.valueOf(g_id));
    }

    public void contacts() {
        clickElement(By.linkText("home"));
    }
}
