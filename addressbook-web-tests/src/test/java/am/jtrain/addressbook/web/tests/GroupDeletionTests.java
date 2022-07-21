package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isGroupsPresented()) {
            app.getGroupHelper().createGroup(new GroupData(null, "group_1", "group_test 1",
                    "group footer 1"));
        }

        List<GroupData> before_list = app.getGroupHelper().getGroupList();
        Integer rnd_group = app.getGroupHelper().chooseRandomElement();
        GroupData removed_group = app.getGroupHelper().getGroupDataById(rnd_group);

        app.getGroupHelper().clickElementInList(rnd_group);
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after_list = app.getGroupHelper().getGroupList();
        before_list.remove(removed_group);

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }
}
