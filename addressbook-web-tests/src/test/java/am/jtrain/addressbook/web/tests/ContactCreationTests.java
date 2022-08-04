package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/java/am/jtrain/addressbook/web/resources/contacts.json"));
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            json.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "contactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.navigate().contacts();
        Contacts before_list = app.contact().readAll();

        app.contact().create(contact);
        assertEquals(app.contact().count(), before_list.size() + 1);
        Contacts after_list = app.contact().readAll();
        assertThat(after_list, equalTo(before_list.withAdded(contact.withId(app.contact().getMaxIdInList(app.contact().getListIds())))));
    }

}
