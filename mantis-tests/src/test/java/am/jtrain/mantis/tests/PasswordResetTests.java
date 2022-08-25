package am.jtrain.mantis.tests;
import am.jtrain.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static org.testng.Assert.assertEquals;

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
        app.session().setPassword("12345");
        assertEquals(app.session().getCurrentPageUrl(), "http://localhost/mantisbt-2.25.4/manage_user_page.php");
    }



    @AfterMethod (alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
