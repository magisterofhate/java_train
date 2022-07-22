package am.jtrain.addressbook.web.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private final Browser browser;

    public ApplicationManager (Browser browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            wd = new ChromeDriver(options);
        } else if (browser.equals(Browser.FIREFOX)) {
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            wd = new FirefoxDriver(options);
        } else if ((browser.equals(Browser.EDGE))) {
            EdgeOptions options = new EdgeOptions();
            options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
            wd = new EdgeDriver(options);
        } else {
            throw new RuntimeException("Incorrect Browser");
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
