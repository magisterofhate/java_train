package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isGroupsPresented()) {
            app.getGroupHelper().createGroup(new GroupData("group_1", "group_test 1",
                    "group footer 1"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
        app.getSessionHelper().logout();
    }
}
