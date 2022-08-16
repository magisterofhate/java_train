package am.jtrain.addressbook.web.appManager;

import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase {

    private Groups groupCache = null;

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        clickElement(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
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

    public void initGroupModification() {
        clickElement(By.xpath("//input[@name='edit']"));
    }

    public void submitGroupModification() {
        clickElement(By.name("update"));
    }

    public void initGroupDeletion() {
        clickElement(By.xpath("//input[@name='delete']"));
    }

    public boolean isGroupsPresented () {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        clickElementInList(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        clickElementInList(group.getId());
        initGroupDeletion();
        groupCache = null;
        returnToGroupPage();
    }

    public GroupData getGroupDataById(Integer g_id) {
        String g_name = wd.findElement(By.xpath("//input[@value='" + g_id + "']/parent::*")).getText();
        return new GroupData().withId(g_id).withName(g_name);
    }

    public Groups readAll() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String group_name = element.getText();
            Integer group_id = Integer.parseInt(element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("value"));
            GroupData group = new GroupData().withId(group_id).withName(group_name);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }

    public Integer count() {
        return wd.findElements(By.xpath("//input[@name='selected[]']")).size();
    }
}
