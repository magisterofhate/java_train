package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.navigate().contacts();
        Contacts before_list = app.contact().readAll();

        ContactData new_contact = new ContactData().withFirstname("Firstname").withLastname("Lastname").withMiddlename("Middlename")
                .withAddress("address number one").withHome("+4456789123").withMobile("+3378912367")
                .withPhone2("+551428973").withEmail("one.email@test.org").withEmail2("other.mail@test.school");

        app.contact().create(new_contact);

        Contacts after_list = app.contact().readAll();

        assertEquals(before_list.size() + 1, after_list.size());
        assertThat(after_list, equalTo(before_list.withAdded(new_contact.withId(app.contact().getMaxIdInList(app.contact().getListIds())))));
    }

}
