package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        app.navigate().contacts();
        if (! app.contact().isContactsPresented()) {
            app.contact().create(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }
    }

    @Test
    public void testContactDeletionFromMainPage() {

        Contacts before_list = app.contact().readAll();
        ContactData removed_contact = app.contact().getContactDataById(app.contact().chooseRandomElement());

        app.contact().deleteFromMainPage(removed_contact);
        assertEquals(app.contact().count(), before_list.size() - 1);
        Contacts after_list = app.contact().readAll();
        assertThat(after_list, equalTo(before_list.withOut(removed_contact)));

    }

    @Test
    public void testContactDeletionFromModificationForm() {

        Contacts before_list = app.contact().readAll();
        ContactData removed_contact = app.contact().getContactDataById(app.contact().chooseRandomElement());

        app.contact().deleteFromEditForm(removed_contact);
        assertEquals(app.contact().count(), before_list.size() - 1);
        Contacts after_list = app.contact().readAll();
        assertThat(after_list, equalTo(before_list.withOut(removed_contact)));
    }
}
