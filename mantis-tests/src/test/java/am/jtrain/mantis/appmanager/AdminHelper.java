package am.jtrain.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void goToUserManagementPage() {
        clickElement(By.xpath("//i[@class='fa fa-gears menu-icon']/parent::*"));
        clickElement(By.xpath("//a[@href='/mantisbt-2.25.4/manage_user_page.php']"));
    }

    public void resetUserPassword (Integer user_id) {
        clickElement(By.xpath(String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", user_id)));
        clickElement(By.xpath("//input[@value='Reset Password']"));
        waitForElement(By.xpath("//input[@id='search']"), 15);
    }
}
