package am.jtrain.addressbook.web;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        goToContactPage();
        initContactCreation();
        fillContactForm(new ContactData("Firstname", "Lastname", "Middlename",
                        "address number one", "+4456789123", "+3378912367",
                        "+551428973", "one.email@test.org", "other.mail@test.school"));
        submitContactCreation();
        logout();
    }

}
