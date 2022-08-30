package am.jtrain.bugify.appmanager;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;
    private apiHelper apiHelper;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {

        String config = System.getProperty("config", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", config)));

    }

    public void stop() {
    }


    public apiHelper api() throws MalformedURLException {
        if (apiHelper == null) {
            apiHelper = new apiHelper(this);
        }
        return apiHelper;
    }

    public String getProperty (String key) {
        return properties.getProperty(key);
    }

}
