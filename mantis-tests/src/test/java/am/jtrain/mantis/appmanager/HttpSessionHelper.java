package am.jtrain.mantis.appmanager;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.persistence.Entity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpSessionHelper {

    private final ApplicationManager app;
    private final CloseableHttpClient httpclient;

    public HttpSessionHelper(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("baseURL") + "/login.php");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = getResponseText(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

    public String getResponseText(CloseableHttpResponse response) throws IOException {
        try (response) {
            return EntityUtils.toString(response.getEntity());
        }
    }
}
