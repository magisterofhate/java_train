package am.jtrain.addressbook.web.appManager;

import am.jtrain.addressbook.web.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        clickElement(By.linkText("home page"));
    }

    public void initContactCreation() {
        clickElement(By.xpath("//a[@href='edit.php']"));
    }

    public void fillContactForm(ContactData contactData) {
        enterText(By.name("firstname"),contactData.getFirstname());
        enterText(By.name("lastname"),contactData.getLastname());
        enterText(By.name("middlename"),contactData.getMiddlename());
        enterText(By.name("address"),contactData.getAddress());
        enterText(By.name("home"),contactData.getHome());
        enterText(By.name("mobile"),contactData.getMobile());
        enterText(By.name("phone2"),contactData.getPhone2());
        enterText(By.name("email"),contactData.getEmail());
        enterText(By.name("email2"),contactData.getEmail2());

    }

    public void submitContactCreation() {
        clickElement(By.name("submit"));
    }

}
