package am.jtrain.addressbook.web.generators;

import am.jtrain.addressbook.web.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupGenerator extends GeneratorsBase {

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "File path")
    public File file;


    public static void main (String[] args) throws IOException {
        GroupGenerator generator = new GroupGenerator();
        JCommander jcommander = new JCommander(generator);
        jcommander.parse(args);
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, file);

    }

    private List<GroupData> generateGroups (int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName("name_" + generateRndString(15))
                    .withHeader("header_" + generateRndString(15))
                    .withFooter("footer_" + generateRndString(15)));
        }
        return groups;
    }

    private void save (List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }


}
