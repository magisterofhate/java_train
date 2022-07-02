package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goToContactPage();
        app.initContactCreation();
        app.fillContactForm(new ContactData("Firstname", "Lastname", "Middlename",
                        "address number one", "+4456789123", "+3378912367",
                        "+551428973", "one.email@test.org", "other.mail@test.school"));
        app.submitContactCreation();
        app.returnToContactPage();
        app.logout();
    }

}
