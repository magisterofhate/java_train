package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.navigate().groups();
        List<GroupData> before_list = app.group().getGroupList();

        app.group().initGroupCreation();
        GroupData new_group = new GroupData(null, "group_1", "group_test 1", "group footer 1");
        app.group().fillGroupForm(new_group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();

        List<GroupData> after_list = app.group().getGroupList();
        Integer max_g_id = app.group().getMaxIdInList(app.group().getListIds());

        before_list.add(new GroupData(max_g_id, new_group.getName(), null, null));
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }

}
