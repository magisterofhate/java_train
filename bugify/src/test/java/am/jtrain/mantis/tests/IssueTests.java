package am.jtrain.mantis.tests;

import am.jtrain.mantis.model.Issue;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class IssueTests extends TestBase{

    @BeforeClass
    public void initRestAssured() {
        RestAssured.authentication = RestAssured.basic(app.getProperty("apiUser"), app.getProperty("apiPassword"));
    }

    @Test
    public void testIssues() throws MalformedURLException {
        skipIfNotFixed(300);
        Issue new_issue = new Issue().withsubject("Test it now"). withDescription("I said NOW!");
        Set<Issue> old_list = app.api().getAllIssues();
        Integer new_issue_id = app.api().createIssue(new_issue);
        old_list.add(new_issue.withId(new_issue_id));
        Set<Issue> new_list = app.api().getAllIssues();
        assertEquals(old_list, new_list);
    }
}
