package am.jtrain.addressbook.web.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletionFromMainPage() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContactForDeletion();
        app.getContactHelper().submitContactDeletion();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionFromModificationForm() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContactFromModificationForm();
        app.getSessionHelper().logout();
    }
}
