package am.jtrain.addressbook.web.generators;

import am.jtrain.addressbook.web.model.ContactData;
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
import java.util.Locale;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class ContactGenerator extends GeneratorsBase {

    @Parameter (names = "-c", description = "Contacts count")
    public int count;

    @Parameter (names = "-f", description = "File path")
    public File file;


    public static void main (String[] args) throws IOException {
        ContactGenerator generator = new ContactGenerator();
        JCommander jcommander = new JCommander(generator);
        jcommander.parse(args);
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);

    }

    private List<ContactData> generateContacts (int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withLastname("Lastname_" + generateRndString(15))
                    .withFirstname("Firstname_" + generateRndString(15))
                    .withMiddlename("Middlename_" + generateRndString(15))
                    .withAddress(generateRndString(15) + "\n" + generateRndString(25))
                    .withHome(generateRndPhoneNumber()).withMobile(generateRndPhoneNumber())
                    .withWork(generateRndPhoneNumber()).withPhone2(generateRndPhoneNumber())
                    .withEmail(generateRndEmail()).withEmail2(generateRndEmail()).withEmail3(generateRndEmail()));
        }
        return contacts;
    }

    private String generateRndEmail() {
        return "a" + generateRndString(10) + "@" + generateRndString(8) + "." +
                randomAlphabetic(5).toLowerCase(Locale.ROOT);
    }

    private String generateRndPhoneNumber() {
        return "+" + randomNumeric(11);
    }

    private void save (List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }


}
