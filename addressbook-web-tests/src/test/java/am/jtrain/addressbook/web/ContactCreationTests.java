package am.jtrain.addressbook.web;

import java.time.Duration;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
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

    @Test
    public void testGroupCreation() {
        goToContactPage();
        initContactCreation();
        fillContactForm(new ContactData("Firstname", "Lastname", "Middlename",
                        "address number one", "+4456789123", "+3378912367",
                        "+551428973", "one.email@test.org", "other.mail@test.school"));
        submitContactCreation();
        logout();
    }

    private void logout() {
        returnToContactPage();
        wd.findElement(By.linkText("Logout")).click();
    }

    private void returnToContactPage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void initContactCreation() {
        wd.findElement(By.xpath("//a[@href='edit.php']")).click();
    }

    private void fillContactForm(ContactData contactData) {
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

    private void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void goToContactPage() {
        wd.findElement(By.linkText("home")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
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
