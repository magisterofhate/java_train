package am.jtrain.addressbook.web.appManager;

import am.jtrain.addressbook.web.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        clickElement(By.name("new"));
    }

    public void fillGorupForm(GroupData groupData) {
        enterText(By.name("group_name"), groupData.getName());
        enterText(By.name("group_header"), groupData.getHeader());
        enterText(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        clickElement(By.name("submit"));
    }

    public void returnToGroupPage() {
        clickElement(By.linkText("group page"));
    }
}
