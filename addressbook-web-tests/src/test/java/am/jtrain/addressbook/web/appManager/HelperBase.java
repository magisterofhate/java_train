package am.jtrain.addressbook.web.appManager;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void enterText(By locator, String text) {
        if (text != null) {
            String currentText = wd.findElement(locator).getAttribute("value");
            if (! currentText.equals(text)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }

    }

    protected void clickElement(By locator) {
        wd.findElement(locator).click();
    }

    public Integer chooseRandomElement() {
        List <Integer> element_ids = getListIds();
        Random random_method = new Random();
        int index = random_method.nextInt(element_ids.size());
        return element_ids.get(index);
    }

    public void clickElementInList(Integer e_id) {
        clickElement(By.xpath("//input[@value=" + e_id + "]"));
    }

    public List <Integer> getListIds() {
        List <Integer> element_ids = new ArrayList<>();
        List <WebElement> elements = wd.findElements(By.xpath("//input[@name='selected[]']"));
        for (WebElement element : elements) {
            String el_id = element.getAttribute("value");
            element_ids.add(Integer.parseInt(el_id));
        }
        return element_ids;
    }

    public Integer getMaxIdInList(List <Integer> id_list) {
        return Collections.max(id_list);
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
