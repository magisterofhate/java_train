package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

    @Test
    public void compareContactDataFromMainPageWithDataFromEditPage() {
        app.navigate().contacts();

        Integer rnd_contact = app.contact().chooseRandomElement();
        ContactData chosen_contact = app.contact().readAllContactDataFromMainPage(rnd_contact);

        ContactData chosen_contact_edit_page = app.contact().readAllContactDataFromEditPage(chosen_contact);

        assertThat(chosen_contact.getAllPhones(), equalTo(app.contact().phones(chosen_contact_edit_page)));
        assertThat(chosen_contact.getAllEmails(), equalTo(app.contact().emails(chosen_contact_edit_page)));
        assertThat(app.contact().cleanAddresses(chosen_contact.getAddress()),
                equalTo(app.contact().cleanAddresses(chosen_contact_edit_page.getAddress())));
    }
}
