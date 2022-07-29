package am.jtrain.addressbook.web.tests;

import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> groupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/java/am/jtrain/addressbook/web/resources/groups.json"));
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            json.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "groupsFromJson")
    public void testGroupCreation(GroupData group) {
        app.navigate().groups();
        Groups before_list = app.group().readAll();

        app.group().create(group);

        assertEquals(app.group().count() , before_list.size() + 1);
        Groups after_list = app.group().readAll();
        assertThat(after_list, equalTo(before_list.withAdded(group.withId(app.group().getMaxIdInList(app.group().getListIds())))));
    }



}
