package am.jtrain.addressbook.web;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    protected WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        init();
    }

    private void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    protected void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    protected void fillGorupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    protected void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    protected void returnToContactPage() {
        wd.findElement(By.linkText("home page")).click();
    }

    protected void initContactCreation() {
        wd.findElement(By.xpath("//a[@href='edit.php']")).click();
    }

    protected void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).sendKeys(contactData.getHome());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        wd.findElement(By.name("phone2")).sendKeys(contactData.getPhone2());
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
        wd.findElement(By.name("email2")).sendKeys(contactData.getEmail2());

    }

    protected void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    protected void goToContactPage() {
        wd.findElement(By.linkText("home")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        stop();
    }

    private void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
