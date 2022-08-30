package am.jtrain.mantis.tests;
import am.jtrain.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void resetPasswordByAdmin() {
        app.session().login(app.getProperty("admin_login"),
                app.getProperty("admin_password"), app.getProperty("baseURL"));
        app.admin().goToUserManagementPage();
        app.admin().resetUserPassword(2);
        MailMessage message = app.mail().waitForMail(1, 10000).get(0);
        String link = app.mail().getPasswordResetLink(message);
        app.session().goTo(link);
        String password = "12345";
        app.session().setPassword(password);
        String username = app.db().userDataByIdFromDb(2).getUsername();
        try {
            assertTrue(app.httpSession().login(username, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod (alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
