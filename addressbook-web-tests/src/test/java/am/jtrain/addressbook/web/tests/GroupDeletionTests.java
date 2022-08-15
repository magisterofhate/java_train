package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        if (app.db().groupsFromDb().size() ==0) {
            app.navigate().groups();
            app.group().create(new GroupData().withName("group_1").withHeader("group_test 1").withFooter("group footer 1"));
        }
    }

    @Test
    public void testGroupDeletion() throws InterruptedException {
        app.navigate().groups();

        Groups before_list = app.db().groupsFromDb();
        GroupData removed_group = app.group().getGroupDataById(app.group().chooseRandomElement());

        app.group().delete(removed_group);

        assertEquals(app.group().count(), before_list.size() - 1);
        Thread.sleep(2000);
        Groups after_list = app.db().groupsFromDb();
        //assertThat(after_list, equalTo(before_list.withOut(removed_group)));
        assertThat(after_list, not(hasItem(removed_group)));
    }


}
