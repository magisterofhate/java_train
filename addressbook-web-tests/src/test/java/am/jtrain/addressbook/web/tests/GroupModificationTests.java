package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isGroupsPresented()) {
            app.getGroupHelper().createGroup(new GroupData("group_1", "group_test 1",
                    "group footer 1"));
        }
        Integer rnd_group = app.getGroupHelper().chooseRandomElement();
        app.getGroupHelper().clickElementInList(rnd_group);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("group_1_2", "group_test 1 2",
                "group footer 1 2"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        app.getSessionHelper().logout();
    }
}
