package am.jtrain.mantis.appmanager;

import am.jtrain.mantis.model.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;

import java.util.Set;

import static com.google.gson.JsonParser.parseString;

public class apiHelper {

    private final ApplicationManager app;

    public apiHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getAllIssues() {
        String json = RestAssured.get((app.getProperty("apiBaseURL") + "/issues.json")).asString();
        JsonElement parsed = parseString(json);

        return new Gson().fromJson(parsed.getAsJsonObject().get("issues"), new TypeToken<Set<Issue>>(){}.getType());
    }

    public Integer createIssue(Issue new_issue) {
        String json = RestAssured.given()
                .param("subject", new_issue.getSubject())
                .param("description", new_issue.getDescription())
                .post((app.getProperty("apiBaseURL") + "/issues.json")).asString();
        JsonElement parsed = parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
