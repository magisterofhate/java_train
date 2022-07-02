package am.jtrain.addressbook.web.appManager;

import am.jtrain.addressbook.web.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper {
    WebDriver wd;

    public GroupHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void fillGorupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }
}
