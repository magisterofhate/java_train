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
        app.navigate().contacts();
        if (! app.contact().isContactsPresented()) {
            app.contact().create(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }
    }

    @Test
    public void testContactModification() {

        Contacts before_list = app.contact().readAll();
        Integer rnd_contact = app.contact().chooseRandomElement();
        ContactData original_contact = app.contact().getContactDataById(rnd_contact);

        ContactData modified_contact = new ContactData().withId(rnd_contact).withFirstname("Firstname 123").withLastname("Lastname 123")
                .withMiddlename("Middlename").withAddress("address number one two three").withHome("+4456789123")
                .withMobile("+3378912367").withPhone2("+551428973").withEmail("one.email@test.org").withEmail2("other.mail@test.school");

        app.contact().modify(modified_contact);

        Contacts after_list = app.contact().readAll();

        assertEquals(before_list.size(), after_list.size());
        assertThat(after_list, equalTo(before_list.withOut(original_contact).withAdded(modified_contact)));
    }


}
