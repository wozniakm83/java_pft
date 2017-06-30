package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.group().createIfRequired(new GroupData().withName("Test1"));
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/grumpy_cat.jpg");
        ContactData contact = new ContactData()
                .withFirstname("firstname")
                .withLastname("lastname")
                .withAddress("somewhere over the rainbow")
                .withEmail("email1@email.com")
                .withEmail2("email2@email.com")
                .withHomePhone("(+11) 111 1111")
                .withMobilePhone("2-222-222")
                .withWorkPhone("333 33 33")
                .withGroup("Test1")
                .withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/grumpy_cat.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
