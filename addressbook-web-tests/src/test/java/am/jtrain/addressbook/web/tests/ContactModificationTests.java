package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isContactsPresented()) {
            app.getContactHelper().createContact(new ContactData("Firstname 123", "Lastname 123",
                    null, null, null, null, null, null, null));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Firstname 123", "Lastname 123", "Middlename",
                "address number one two three", "+4456789123", "+3378912367",
                "+551428973", "one.email@test.org", "other.mail@test.school"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
}
