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
                .withAddress("address number one").withHome("+4456 7 89 123").withMobile("+33(789) 12-367")
                .withPhone2("+55-14-289-73").withEmail("one.email@test.org").withEmail2("other.mail@test.school")
                .withEmail3("asdrt@test.ru").withWork("+7(789)2471928");

        app.contact().create(new_contact);
        assertEquals(app.contact().count(), before_list.size() + 1);
        Contacts after_list = app.contact().readAll();
        assertThat(after_list, equalTo(before_list.withAdded(new_contact.withId(app.contact().getMaxIdInList(app.contact().getListIds())))));
    }

}
