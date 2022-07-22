package am.jtrain.addressbook.web.appManager;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        enterText(By.name("firstname"), contactData.getFirstname());
        enterText(By.name("lastname"), contactData.getLastname());
        enterText(By.name("middlename"), contactData.getMiddlename());
        enterText(By.name("address"), contactData.getAddress());
        enterText(By.name("home"), contactData.getHome());
        enterText(By.name("mobile"), contactData.getMobile());
        enterText(By.name("phone2"), contactData.getPhone2());
        enterText(By.name("email"), contactData.getEmail());
        enterText(By.name("email2"), contactData.getEmail2());

    }

    public void submitContactCreation() {
        clickElement(By.name("submit"));
    }

    public void submitContactDeletion() {
        clickElement(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification(Integer c_id) {
        clickElement(By.xpath("//a[@href='edit.php?id=" + c_id + "']"));
    }

    public void submitContactModification() {
        clickElement(By.name("update"));
    }

    public void deleteContactFromModificationForm() {
        clickElement(By.xpath("//input[@value='Delete']"));
    }

    public boolean isContactsPresented() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public void create(ContactData c_data) {
        initContactCreation();
        fillContactForm(c_data);
        submitContactCreation();
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToContactPage();
    }

    public void deleteFromMainPage(ContactData contact) {
        clickElementInList(contact.getId());
        submitContactDeletion();
        waitForListToLoad();
    }

    public void deleteFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        deleteContactFromModificationForm();
        waitForListToLoad();
    }

    public ContactData getContactDataById(Integer c_id) {
        String c_f_name = wd.findElement(By.xpath("//input[@value='" + c_id + "']/ancestor::tr")).findElement(By.xpath("td[3]")).getText();
        String c_l_name = wd.findElement(By.xpath("//input[@value='" + c_id + "']/ancestor::tr")).findElement(By.xpath("td[2]")).getText();
        return new ContactData().withId(c_id).withFirstname(c_f_name).withLastname(c_l_name);
    }

    public List<ContactData> getContactList() {
        List <ContactData> contacts = new ArrayList<>();
        List <WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {

            Integer c_id = Integer.parseInt(element.findElement(By.xpath(".//td/input[@name='selected[]']")).getAttribute("id"));
            String c_f_name = element.findElement(By.xpath("td[3]")).getText();
            String c_l_name = element.findElement(By.xpath("td[2]")).getText();

            ContactData contact = new ContactData().withId(c_id).withFirstname(c_f_name).withLastname(c_l_name);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts readAll() {
        Contacts contacts = new Contacts();
        List <WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {

            Integer c_id = Integer.parseInt(element.findElement(By.xpath(".//td/input[@name='selected[]']")).getAttribute("id"));
            String c_f_name = element.findElement(By.xpath("td[3]")).getText();
            String c_l_name = element.findElement(By.xpath("td[2]")).getText();

            ContactData contact = new ContactData().withId(c_id).withFirstname(c_f_name).withLastname(c_l_name);
            contacts.add(contact);
        }
        return contacts;
    }

    public void waitForListToLoad() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='maintable']")));
    }

}
