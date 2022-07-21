package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToContactPage();

        List<ContactData> before_list = app.getContactHelper().getContactList();

        app.getContactHelper().initContactCreation();
        ContactData new_contact = new ContactData(null, "Firstname", "Lastname", "Middlename",
                "address number one", "+4456789123", "+3378912367",
                "+551428973", "one.email@test.org", "other.mail@test.school");
        app.getContactHelper().fillContactForm(new_contact);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();

        List<ContactData> after_list = app.getContactHelper().getContactList();
        Integer max_c_id = app.getContactHelper().getMaxIdInList(app.getContactHelper().getListIds());

        before_list.add(new ContactData(max_c_id, new_contact.getFirstname(), new_contact.getLastname()));
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }

}
