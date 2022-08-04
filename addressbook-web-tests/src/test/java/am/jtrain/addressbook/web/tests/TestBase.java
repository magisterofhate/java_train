package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.appManager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite (alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
