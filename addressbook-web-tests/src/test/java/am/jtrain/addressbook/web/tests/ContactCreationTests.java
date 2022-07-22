package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.navigate().contacts();

        List<ContactData> before_list = app.contact().getContactList();

        ContactData new_contact = new ContactData().withFirstname("Firstname").withLastname("Lastname").withMiddlename("Middlename")
                .withAddress("address number one").withHome("+4456789123").withMobile("+3378912367")
                .withPhone2("+551428973").withEmail("one.email@test.org").withEmail2("other.mail@test.school");

        app.contact().createContact(new_contact);

        List<ContactData> after_list = app.contact().getContactList();
        Integer max_c_id = app.contact().getMaxIdInList(app.contact().getListIds());

        before_list.add(new ContactData().withId(max_c_id).withFirstname(new_contact.getFirstname()).withLastname(new_contact.getLastname()));
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }

}
