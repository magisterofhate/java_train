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
        if (app.db().contactsFromDb().size() == 0) {
            app.navigate().contacts();
            app.contact().create(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }
    }

    @Test
    public void testContactDeletionFromMainPage() {
        app.navigate().contacts();
        Contacts before_list = app.db().contactsFromDb();
        ContactData removed_contact = app.db().contactDataByIdFromDb(app.contact().chooseRandomElement());

        app.contact().deleteFromMainPage(removed_contact);
        assertEquals(app.contact().count(), before_list.size() - 1);
        Contacts after_list = app.db().contactsFromDb();
        assertThat(after_list, equalTo(before_list.withOut(removed_contact)));

    }

    @Test
    public void testContactDeletionFromModificationForm() {
        app.navigate().contacts();
        Contacts before_list = app.db().contactsFromDb();
        ContactData removed_contact = app.db().contactDataByIdFromDb(app.contact().chooseRandomElement());

        app.contact().deleteFromEditForm(removed_contact);
        assertEquals(app.contact().count(), before_list.size() - 1);
        Contacts after_list = app.db().contactsFromDb();
        assertThat(after_list, equalTo(before_list.withOut(removed_contact)));
    }
}
