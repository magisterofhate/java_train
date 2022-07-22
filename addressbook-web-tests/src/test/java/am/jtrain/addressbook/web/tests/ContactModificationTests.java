package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        app.navigate().contacts();
        if (! app.contact().isContactsPresented()) {
            app.contact().createContact(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }
    }

    @Test
    public void testContactModification() {

        List<ContactData> before_list = app.contact().getContactList();
        Integer rnd_contact = app.contact().chooseRandomElement();
        ContactData original_contact = app.contact().getContactDataById(rnd_contact);

        ContactData modified_contact = new ContactData().withId(rnd_contact).withFirstname("Firstname 123").withLastname("Lastname 123")
                .withMiddlename("Middlename").withAddress("address number one two three").withHome("+4456789123")
                .withMobile("+3378912367").withPhone2("+551428973").withEmail("one.email@test.org").withEmail2("other.mail@test.school");

        app.contact().modify(modified_contact);

        List<ContactData> after_list = app.contact().getContactList();
        before_list.remove(original_contact);
        before_list.add(modified_contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }


}
