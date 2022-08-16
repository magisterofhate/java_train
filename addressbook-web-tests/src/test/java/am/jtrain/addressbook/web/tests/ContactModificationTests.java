package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        if (app.db().contactsFromDb().size() == 0) {
            app.navigate().contacts();
            app.contact().create(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }
    }

    @Test
    public void testContactModification() {
        app.navigate().contacts();
        Contacts before_list = app.db().contactsFromDb();
        Integer rnd_contact = app.contact().chooseRandomElement();
        ContactData original_contact = app.db().contactDataByIdFromDb(rnd_contact);

        ContactData modified_contact = new ContactData().withId(rnd_contact).withFirstname("Firstname 123").withLastname("Lastname 123")
                .withMiddlename("Middlename").withAddress("address number one two three").withHome("+4456789123")
                .withMobile("+3378912367").withPhone2("+551428973").withEmail("one.email@test.org").withEmail2("other.mail@test.school");

        app.contact().modify(modified_contact);

        assertEquals(app.contact().count(), before_list.size());
        Contacts after_list = app.db().contactsFromDb();
        assertThat(after_list, equalTo(before_list.withOut(original_contact).withAdded(modified_contact)));
    }
}
