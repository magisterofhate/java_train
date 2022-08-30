package am.jtrain.bugify.tests;

import am.jtrain.bugify.appmanager.ApplicationManager;
import am.jtrain.bugify.model.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.List;

import static com.google.gson.JsonParser.parseString;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite (alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public boolean isIssueOpen(Integer issueId) {
        String json = RestAssured.get((app.getProperty("apiBaseURL")+ "/issues/" + issueId + ".json")).asString();
        JsonElement parsed = parseString(json);
        List<Issue> issues = new Gson().fromJson(parsed.getAsJsonObject().get("issues"), new TypeToken<List<Issue>>(){}.getType());
        String status = issues.get(0).getState_name();
        return !status.equals("Resolved") && !status.equals("Closed");
    }

    public void skipIfNotFixed(Integer issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Skipped due to issue " + issueId);
        }
    }

}
