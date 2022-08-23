package am.jtrain.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
        WebDriver wd;

    private final Properties properties;
    private SessionHelper sessionHelper;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {

        String config = System.getProperty("config", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", config)));

        String browser = properties.getProperty("defaultBrowser");

        switch (browser) {
            case "CHROME": {
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(false);
                wd = new ChromeDriver(options);
                break;
            }
            case "FIREFOX": {
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                wd = new FirefoxDriver(options);
                break;
            }
            case "EDGE": {
                EdgeOptions options = new EdgeOptions();
                options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
                wd = new EdgeDriver(options);
                break;
            }
            default:
                throw new RuntimeException("Incorrect Browser");
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("login"),
                properties.getProperty("password"), properties.getProperty("baseURL"));

    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public SessionHelper session() {
        return sessionHelper;
    }

}
