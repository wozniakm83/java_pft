package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.group().createIfRequired(new GroupData().withName("Test1"));
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("firstname").withLastname("lastname").withAddress("address").withHomePhone("home").withEmail("email1@email.com").withGroup("Test1");
        app.contact().create(contact, true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
