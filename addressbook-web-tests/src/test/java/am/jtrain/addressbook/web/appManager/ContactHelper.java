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

    public void selectContactForDeletion() {
        clickElement(By.name("selected[]"));
    }

    public void submitContactDeletion() {
        clickElement(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        String c_id = wd.findElement(By.xpath("//input[@name='selected[]']")).getAttribute("value");
        clickElement(By.xpath("//a[@href='edit.php?id=" + c_id + "']"));
    }

    public void submitContactModification() {
        clickElement(By.name("update"));
    }

    public void deleteContactFromModificationForm() {
        clickElement(By.xpath("//input[@value='Delete']"));
    }

    public boolean isContactsPresented (){
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public void createContact(ContactData c_data) {
        initContactCreation();
        fillContactForm(c_data);
        submitContactCreation();
        returnToContactPage();
    }

}
