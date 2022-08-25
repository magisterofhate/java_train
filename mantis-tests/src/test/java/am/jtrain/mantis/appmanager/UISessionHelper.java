package am.jtrain.mantis.appmanager;

import org.openqa.selenium.By;

public class UISessionHelper extends HelperBase{

    public UISessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password, String baseUrl) {
        wd.get(baseUrl);
        enterText(By.name("username"),username);
        clickElement(By.xpath("//input[@type='submit']"));
        waitForElement(By.xpath("//input[@type='password']"), 10);
        enterText(By.name("password"),password);
        clickElement(By.xpath("//input[@type='submit']"));
    }

    public void logout() {
        wd.get("http://localhost/mantisbt-2.25.4/logout_page.php");
        waitForElement(By.xpath("//input[@name='username']"), 10);
    }

    public String getCurrentPageUrl() {
        return wd.getCurrentUrl();
    }

    public void goTo (String link) {
        wd.get(link);
    }

    public void setPassword (String pwd) {
        enterText(By.name("password"), pwd);
        enterText(By.name("password_confirm"), pwd);
    }

}
