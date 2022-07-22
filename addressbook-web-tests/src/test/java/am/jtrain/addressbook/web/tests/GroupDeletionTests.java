package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.navigate().groups();
        if (! app.group().isGroupsPresented()) {
            app.group().createGroup(new GroupData(null, "group_1", "group_test 1",
                    "group footer 1"));
        }

        List<GroupData> before_list = app.group().getGroupList();
        Integer rnd_group = app.group().chooseRandomElement();
        GroupData removed_group = app.group().getGroupDataById(rnd_group);

        app.group().clickElementInList(rnd_group);
        app.group().initGroupDeletion();
        app.group().returnToGroupPage();

        List<GroupData> after_list = app.group().getGroupList();
        before_list.remove(removed_group);

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before_list.sort(byId);
        after_list.sort(byId);

        Assert.assertEquals(before_list, after_list);
    }
}
