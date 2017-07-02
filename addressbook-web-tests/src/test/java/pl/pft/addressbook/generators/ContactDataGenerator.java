package pl.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import pl.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-cc", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws  IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File (file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstname(),
                    contact.getLastname(),
                    contact.getAddress(),
                    contact.getEmail(),
                    contact.getEmail2(),
                    contact.getEmail3(),
                    contact.getHomePhone(),
                    contact.getMobilePhone(),
                    contact.getWorkPhone(),
                    contact.getGroup()));
        }
        writer.close();
    }
    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i < count; i++) {
            int n = i + 1;
            contacts.add(new ContactData()
                    .withFirstname(String.format("firstname%s", n))
                    .withLastname(String.format("lastname%s", n))
					.withAddress(String.format("address%s", n))
					.withEmail(String.format("email1%s@email.com", n))
					.withEmail2(String.format("email2%s@email.com", n))
					.withEmail3(String.format("email3%s@email.com", n))
					.withHomePhone(String.format("(+11) %s111111", n))
					.withMobilePhone(String.format("%s22 22 22", n))
					.withWorkPhone(String.format("%s33-33-33", n))
					.withGroup(String.format("Test1"))
            );
        }
        return contacts;
    }
}
