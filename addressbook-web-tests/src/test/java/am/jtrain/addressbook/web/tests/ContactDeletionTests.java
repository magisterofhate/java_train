package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletionFromMainPage() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isContactsPresented()) {
            app.getContactHelper().createContact(new ContactData(null, "Firstname 123", "Lastname 123",
                    null, null, null, null, null, null, null));
        }

        List<ContactData> before_list = app.getContactHelper().getContactList();
        Integer rnd_contact = app.getContactHelper().chooseRandomElement();
        ContactData removed_contact = app.getContactHelper().getContactDataById(rnd_contact);

        app.getContactHelper().clickElementInList(rnd_contact);
        app.getContactHelper().submitContactDeletion();

        app.getContactHelper().waitForContactList();

        List<ContactData> after_list = app.getContactHelper().getContactList();
        before_list.remove(removed_contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);

    }

    @Test
    public void testContactDeletionFromModificationForm() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isContactsPresented()) {
            app.getContactHelper().createContact(new ContactData(null, "Firstname 123", "Lastname 123",
                    null, null, null, null, null, null, null));
        }

        List<ContactData> before_list = app.getContactHelper().getContactList();
        Integer rnd_contact = app.getContactHelper().chooseRandomElement();
        ContactData removed_contact = app.getContactHelper().getContactDataById(rnd_contact);

        app.getContactHelper().initContactModification(rnd_contact);
        app.getContactHelper().deleteContactFromModificationForm();

        app.getContactHelper().waitForContactList();

        List<ContactData> after_list = app.getContactHelper().getContactList();
        before_list.remove(removed_contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }
}
