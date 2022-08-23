package am.jtrain.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password, String baseUrl) {
        wd.get(baseUrl);
        enterText(By.name("username"),username);
        clickElement(By.xpath("//input[@type='submit']"));
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        enterText(By.name("password"),password);
        clickElement(By.xpath("//input[@type='submit']"));
    }

    public void logout() {
        wd.get("http://localhost/mantisbt-2.25.4/logout_page.php");
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
    }
}
