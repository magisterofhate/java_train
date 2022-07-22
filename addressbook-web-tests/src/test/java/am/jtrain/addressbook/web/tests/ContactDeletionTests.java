package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        app.navigate().contacts();
        if (! app.contact().isContactsPresented()) {
            app.contact().createContact(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }
    }

    @Test
    public void testContactDeletionFromMainPage() {

        List<ContactData> before_list = app.contact().getContactList();
        Integer rnd_contact = app.contact().chooseRandomElement();
        ContactData removed_contact = app.contact().getContactDataById(rnd_contact);

        app.contact().clickElementInList(rnd_contact);
        app.contact().submitContactDeletion();

        app.contact().waitForContactList();

        List<ContactData> after_list = app.contact().getContactList();
        before_list.remove(removed_contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);

    }

    @Test
    public void testContactDeletionFromModificationForm() {

        List<ContactData> before_list = app.contact().getContactList();

        ContactData removed_contact = app.contact().getContactDataById(app.contact().chooseRandomElement());

        app.contact().initContactModification(removed_contact.getId());
        app.contact().deleteContactFromModificationForm();
        app.contact().waitForContactList();

        List<ContactData> after_list = app.contact().getContactList();
        before_list.remove(removed_contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }
}
