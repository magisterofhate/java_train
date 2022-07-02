package am.jtrain.addressbook.web;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initGroupCreation();
        fillGorupForm(new GroupData("group_1", "group_test 1", "group footer 1"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

}
