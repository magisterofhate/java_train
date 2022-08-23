package am.jtrain.addressbook.web.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password, String baseUrl) {
        wd.get(baseUrl);
        enterText(By.name("user"),username);
        enterText(By.name("pass"),password);
        clickElement(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        clickElement(By.linkText("Logout"));
    }
}
