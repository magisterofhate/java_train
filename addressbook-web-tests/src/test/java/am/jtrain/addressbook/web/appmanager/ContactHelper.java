package am.jtrain.addressbook.web.appmanager;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        clickElement(By.linkText("home page"));
    }

    public void initContactCreation() {
        clickElement(By.xpath("//a[@href='edit.php']"));
    }

    public void fillContactFormWithGroup(ContactData contactData, Integer group_id) {
        enterText(By.name("firstname"), contactData.getFirstname());
        enterText(By.name("lastname"), contactData.getLastname());
        enterText(By.name("middlename"), contactData.getMiddlename());
        enterText(By.name("address"), contactData.getAddress());
        enterText(By.name("home"), contactData.getHome());
        enterText(By.name("mobile"), contactData.getMobile());
        enterText(By.name("work"), contactData.getWork());
        enterText(By.name("phone2"), contactData.getPhone2());
        enterText(By.name("email"), contactData.getEmail());
        enterText(By.name("email2"), contactData.getEmail2());
        enterText(By.name("email3"), contactData.getEmail3());

        if (group_id != null) {
            Select groupList = new Select(wd.findElement(By.name("new_group")));
            groupList.selectByValue(String.valueOf(group_id));
        } else {
            Select groupList = new Select(wd.findElement(By.name("new_group")));
            groupList.selectByValue("[none]");
        }
    }

    public void fillContactForm(ContactData contactData) {
        enterText(By.name("firstname"), contactData.getFirstname());
        enterText(By.name("lastname"), contactData.getLastname());
        enterText(By.name("middlename"), contactData.getMiddlename());
        enterText(By.name("address"), contactData.getAddress());
        enterText(By.name("home"), contactData.getHome());
        enterText(By.name("mobile"), contactData.getMobile());
        enterText(By.name("work"), contactData.getWork());
        enterText(By.name("phone2"), contactData.getPhone2());
        enterText(By.name("email"), contactData.getEmail());
        enterText(By.name("email2"), contactData.getEmail2());
        enterText(By.name("email3"), contactData.getEmail3());
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
        fillContactFormWithGroup(c_data, null);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }

    public void deleteFromMainPage(ContactData contact) {
        clickElementInList(contact.getId());
        submitContactDeletion();
        contactCache = null;
        waitForListToLoad();
    }

    public void deleteFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        deleteContactFromModificationForm();
        contactCache = null;
        waitForListToLoad();
    }

    public ContactData getContactDataById(Integer c_id) {
        String c_f_name = wd.findElement(By.xpath("//input[@value='" + c_id + "']/ancestor::tr")).findElement(By.xpath("td[3]")).getText();
        String c_l_name = wd.findElement(By.xpath("//input[@value='" + c_id + "']/ancestor::tr")).findElement(By.xpath("td[2]")).getText();
        return new ContactData().withId(c_id).withFirstname(c_f_name).withLastname(c_l_name);
    }

    public Contacts readAll() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List <WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {

            Integer c_id = Integer.parseInt(element.findElement(By.xpath(".//td/input[@name='selected[]']")).getAttribute("id"));
            String c_f_name = element.findElement(By.xpath("td[3]")).getText();
            String c_l_name = element.findElement(By.xpath("td[2]")).getText();

            ContactData contact = new ContactData().withId(c_id).withFirstname(c_f_name).withLastname(c_l_name);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public void createContactWithGroup(ContactData contact, Integer group_id) {
        initContactCreation();
        fillContactFormWithGroup(contact, group_id);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();
    }

    public ContactData readAllContactDataFromMainPage(Integer c_id) {
        String c_f_name = wd.findElement(By.xpath("//tr[.//input[contains(@value," + c_id + ")]]/td[3]")).getText();
        String c_l_name = wd.findElement(By.xpath("//tr[.//input[contains(@value," + c_id + ")]]/td[2]")).getText();
        String address = wd.findElement(By.xpath("//tr[.//input[contains(@value," + c_id + ")]]/td[4]")).getText();
        String phones = wd.findElement(By.xpath("//tr[.//input[contains(@value," + c_id + ")]]/td[6]")).getText();
        String emails = wd.findElement(By.xpath("//tr[.//input[contains(@value," + c_id + ")]]/td[5]")).getText();

        return new ContactData().withId(c_id).withFirstname(c_f_name).withLastname(c_l_name)
                .withAllEmails(emails).withAllPhones(phones).withAddress(address);
    }

    public ContactData readAllContactDataFromEditPage(ContactData contact) {
        initContactModification(contact.getId());
        String c_f_name = wd.findElement(By.name("firstname")).getAttribute("value");
        String c_l_name = wd.findElement(By.name("lastname")).getAttribute("value");
        String c_m_name = wd.findElement(By.name("middlename")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String h_phone = wd.findElement(By.name("home")).getAttribute("value");
        String m_phone = wd.findElement(By.name("mobile")).getAttribute("value");
        String w_phone = wd.findElement(By.name("work")).getAttribute("value");
        String phone_2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        return new ContactData().withId(contact.getId()).withFirstname(c_f_name).withLastname(c_l_name).withMiddlename(c_m_name)
                .withAddress(address).withHome(h_phone).withMobile(m_phone).withPhone2(phone_2).withWork(w_phone)
                .withEmail(email1).withEmail2(email2).withEmail3(email3);
    }

    public String phones (ContactData contact) {
        return Stream.of(contact.getHome(), contact.getMobile(), contact.getWork(),contact.getPhone2()).filter(s -> !s.equals(""))
                .map(HelperBase::clearPhones)
                .collect(Collectors.joining("\n"));
    }

    public String emails (ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).filter(s -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public void addToGroup (Integer c_id, Integer g_id) {
        clickElementInList(c_id);
        Select groupList = new Select(wd.findElement(By.name("to_group")));
        groupList.selectByValue(String.valueOf(g_id));
        clickElement(By.name("add"));
    }

    public void removeFromGroup (Integer c_id) {
        clickElementInList(c_id);
        clickElement(By.name("remove"));
    }

    public String cleanAddresses (String addresses) {
        return addresses.replaceAll("\n", "");
    }

    public Integer count() {
        return wd.findElements(By.xpath("//input[@name='selected[]']")).size();
    }

    public void waitForListToLoad() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='maintable']")));
    }

}
