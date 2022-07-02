package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGorupForm(new GroupData("group_1", "group_test 1", "group footer 1"));
        app.submitGroupCreation();
        app.returnToGroupPage();
        app.logout();
    }

}
