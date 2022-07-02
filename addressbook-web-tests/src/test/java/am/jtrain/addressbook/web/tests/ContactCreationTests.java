package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goToContactPage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Firstname", "Lastname", "Middlename",
                        "address number one", "+4456789123", "+3378912367",
                        "+551428973", "one.email@test.org", "other.mail@test.school"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
        app.logout();
    }

}
