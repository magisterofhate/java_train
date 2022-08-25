package am.jtrain.mantis.appmanager;

import am.jtrain.mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private final ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }

    public List<MailMessage> waitForMail (int count, long wait_time) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + wait_time) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map(MailHelper::toModelMessage).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("Error with mail");
    }

    public static MailMessage toModelMessage (WiserMessage message) {
        try {
            MimeMessage mm = message.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException | IOException me) {
            me.printStackTrace();
            return null;
        }
    }

    public String getPasswordResetLink(MailMessage message) {
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.message);
    }

}
