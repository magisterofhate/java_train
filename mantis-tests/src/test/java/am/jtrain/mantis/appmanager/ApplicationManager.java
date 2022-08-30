package am.jtrain.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.xml.rpc.ServiceException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver wd;

    private final Properties properties;
    private UISessionHelper sessionUI;
    private AdminHelper adminHelper;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private HttpSessionHelper httpSession;
    private SoapHelper soapHelper;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {

        String config = System.getProperty("config", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", config)));

    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public UISessionHelper session() {
        if (sessionUI == null) {
            sessionUI = new UISessionHelper(this);
        }
        return sessionUI;
    }

    public AdminHelper admin() {
        if (adminHelper == null) {
            adminHelper = new AdminHelper(this);
        }
        return adminHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public HttpSessionHelper httpSession() {
        if (httpSession == null) {
            httpSession = new HttpSessionHelper(this);
        }
        return httpSession;
    }

    public SoapHelper soap() throws MalformedURLException, ServiceException {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public String getProperty (String key) {
        return properties.getProperty(key);
    }

    public WebDriver getDriver() {
        if (wd == null) {
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
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        return wd;
    }

}
