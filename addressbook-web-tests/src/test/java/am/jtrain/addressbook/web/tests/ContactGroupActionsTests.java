package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class ContactGroupActionsTests extends TestBase {

    @BeforeMethod
    private void checkPreConditions() {
        if (app.db().contactsFromDb().size() == 0) {
            app.navigate().contacts();
            app.contact().create(new ContactData().withFirstname("Firstname 123").withLastname("Lastname 123"));
        }

        if (app.db().groupsFromDb().size() == 0) {
            app.navigate().groups();
            app.group().create(new GroupData().withName("group_1").withHeader("group_test 1").withFooter("group footer 1"));
        }

        if (app.db().groupsWithContacts().size() == 0) {
            app.navigate().contacts();
            Integer rnd_contact = app.contact().chooseRandomElement();

            Integer rnd_group = app.db().rndGroupIdFromDb();

            app.contact().addToGroup(rnd_contact, rnd_group);
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.navigate().contacts();

        Integer rnd_group = app.db().rndGroupIdFromDb();
        Integer rnd_contact = app.contact().chooseRandomElement();

        while (app.db().groupContactsFromDb(rnd_group).contains(rnd_contact)) {
            rnd_group = app.db().rndGroupIdFromDb();
            rnd_contact = app.contact().chooseRandomElement();
        }

        app.contact().addToGroup(rnd_contact, rnd_group);

        List<Integer> contact_groups_from_db = app.db().contactGroupsFromDb(rnd_contact);
        assertThat(contact_groups_from_db, hasItem(rnd_group));
    }

    @Test
    public void testRemoveContactFromGroup() {
        app.navigate().contacts();
        Integer rnd_group_with_contacts = app.db().rndGroupWithContacts();
        app.navigate().groupWithContacts(rnd_group_with_contacts);
        Integer rnd_contact_in_group = app.contact().chooseRandomElement();

        app.contact().removeFromGroup(rnd_contact_in_group);

        List<Integer> contact_groups_from_db = app.db().contactGroupsFromDb(rnd_contact_in_group);
        assertThat(contact_groups_from_db, not(hasItem(rnd_group_with_contacts)));
    }
}
