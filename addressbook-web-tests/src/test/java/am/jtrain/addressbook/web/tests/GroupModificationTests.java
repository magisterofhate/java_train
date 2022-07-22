package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.navigate().groups();
        if (! app.group().isGroupsPresented()) {
            app.group().createGroup(new GroupData(null, "group_1", "group_test 1",
                    "group footer 1"));
        }

        List<GroupData> before_list = app.group().getGroupList();

        Integer rnd_group = app.group().chooseRandomElement();
        app.group().clickElementInList(rnd_group);
        app.group().initGroupModification();
        GroupData group = new GroupData(rnd_group, "group_1_2", "group_test 1 2",
                "group footer 1 2");
        app.group().fillGroupForm(group);
        app.group().submitGroupModification();
        app.group().returnToGroupPage();

        List<GroupData> after_list = app.group().getGroupList();

        Assert.assertEquals(before_list.size(), after_list.size());
        Assert.assertTrue(after_list.contains(group));

    }
}
