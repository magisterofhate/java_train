package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before_list = app.getGroupHelper().getGroupList();

        app.getGroupHelper().initGroupCreation();
        GroupData new_group = new GroupData(null, "group_1", "group_test 1", "group footer 1");
        app.getGroupHelper().fillGroupForm(new_group);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after_list = app.getGroupHelper().getGroupList();
        Integer max_g_id = app.getGroupHelper().getMaxIdInList(app.getGroupHelper().getListIds());

        before_list.add(new GroupData(max_g_id, new_group.getName(), null, null));
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }

}
