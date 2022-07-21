package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isGroupsPresented()) {
            app.getGroupHelper().createGroup(new GroupData(null, "group_1", "group_test 1",
                    "group footer 1"));
        }

        List<GroupData> before_list = app.getGroupHelper().getGroupList();

        Integer rnd_group = app.getGroupHelper().chooseRandomElement();
        app.getGroupHelper().clickElementInList(rnd_group);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(rnd_group, "group_1_2", "group_test 1 2",
                "group footer 1 2");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after_list = app.getGroupHelper().getGroupList();

        Assert.assertEquals(before_list.size(), after_list.size());
        Assert.assertTrue(after_list.contains(group));

    }
}
