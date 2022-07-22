package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.appManager.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);

    @BeforeSuite (alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
