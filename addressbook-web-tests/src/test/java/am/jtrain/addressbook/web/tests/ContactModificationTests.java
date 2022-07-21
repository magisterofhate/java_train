package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isContactsPresented()) {
            app.getContactHelper().createContact(new ContactData(null, "Firstname 123", "Lastname 123",
                    null, null, null, null, null, null, null));
        }

        List<ContactData> before_list = app.getContactHelper().getContactList();
        Integer rnd_contact = app.getContactHelper().chooseRandomElement();
        ContactData original_contact = app.getContactHelper().getContactDataById(rnd_contact);

        app.getContactHelper().initContactModification(rnd_contact);
        ContactData modified_contact = new ContactData(rnd_contact, "Firstname 123", "Lastname 123", "Middlename",
                "address number one two three", "+4456789123", "+3378912367",
                "+551428973", "one.email@test.org", "other.mail@test.school");
        app.getContactHelper().fillContactForm(modified_contact);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();

        List<ContactData> after_list = app.getContactHelper().getContactList();
        before_list.remove(original_contact);
        before_list.add(modified_contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }
}
