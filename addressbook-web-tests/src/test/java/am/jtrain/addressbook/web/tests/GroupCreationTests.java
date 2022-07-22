package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.navigate().groups();
        Groups before_list = app.group().readAll();

        GroupData new_group = new GroupData().withName("group_1").withHeader("group_test 1").withFooter("group footer 1");

        app.group().create(new_group);

        Groups after_list = app.group().readAll();

        assertEquals(before_list.size() + 1, after_list.size());
        assertThat(after_list, equalTo(before_list.withAdded(new_group.withId(app.group().getMaxIdInList(app.group().getListIds())))));
    }



}
