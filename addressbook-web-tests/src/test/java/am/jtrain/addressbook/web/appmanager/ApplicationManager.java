package am.jtrain.addressbook.web.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
        WebDriver wd;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private final Properties properties;
    private DbHelper dbHelper;

    public ApplicationManager () {
        properties = new Properties();
    }

    public void init() throws IOException {

        String config = System.getProperty("config", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", config)));

        dbHelper = new DbHelper();

        String browser = properties.getProperty("defaultBrowser");

        if ("".equals(properties.getProperty("selenium_server"))) {
            switch (browser) {
                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(false);
                    wd = new ChromeDriver(options);
                    break;
                }
                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                    wd = new FirefoxDriver(options);
                    break;
                }
                case "Edge": {
                    EdgeOptions options = new EdgeOptions();
                    options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
                    wd = new EdgeDriver(options);
                    break;
                }
                default:
                    throw new RuntimeException("Incorrect Browser");
            }
        } else {


            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);

            //capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium_server")), capabilities);
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper.login(properties.getProperty("login"),
                properties.getProperty("password"), properties.getProperty("baseURL"));

    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public SessionHelper session() {
        return sessionHelper;
    }

    public NavigationHelper navigate() {
        return navigationHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }
}
