package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    private void checkPreConditions() {
        app.navigate().groups();
        if (! app.group().isGroupsPresented()) {
            app.group().create(new GroupData().withName("group_1").withHeader("group_test 1").withFooter("group footer 1"));
        }
    }

    @Test
    public void testGroupDeletion() {

        Groups before_list = app.group().readAll();
        GroupData removed_group = app.group().getGroupDataById(app.group().chooseRandomElement());

        app.group().delete(removed_group);

        Groups after_list = app.group().readAll();

        assertEquals(before_list.size() - 1, after_list.size());
        assertThat(after_list, equalTo(before_list.withOut(removed_group)));
    }


}
