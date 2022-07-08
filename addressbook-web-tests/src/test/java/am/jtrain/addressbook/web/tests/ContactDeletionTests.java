package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletionFromMainPage() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isContactsPresented()) {
            app.getContactHelper().createContact(new ContactData("Firstname 123", "Lastname 123",
                    null, null, null, null, null, null, null));
        }
        app.getContactHelper().selectContactForDeletion();
        app.getContactHelper().submitContactDeletion();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionFromModificationForm() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isContactsPresented()) {
            app.getContactHelper().createContact(new ContactData("Firstname 123", "Lastname 123",
                    null, null, null, null, null, null, null));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContactFromModificationForm();
        app.getSessionHelper().logout();
    }
}
