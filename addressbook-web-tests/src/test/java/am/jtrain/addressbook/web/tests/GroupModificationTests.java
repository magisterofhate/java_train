package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        if (app.db().groupsFromDb().size() ==0) {
            app.navigate().groups();
            app.group().create(new GroupData().withName("group_1").withHeader("group_test 1").withFooter("group footer 1"));
        }
    }

    @Test
    public void testGroupModification() {
        app.navigate().groups();

        Groups before_list = app.db().groupsFromDb();

        Integer rnd_group = app.group().chooseRandomElement();
        GroupData group = new GroupData().withId(rnd_group).withName("group_1_2").withHeader("group_test 1 2")
                .withFooter("group footer 1 2");

        app.group().modify(group);

        assertEquals(app.group().count(), before_list.size());
        Groups after_list = app.db().groupsFromDb();
        assertThat(after_list, hasItem(group));
    }
}
