package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.group().createIfRequired(new GroupData().withName("Test1"));
        app.contact().createIfRequired(new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("address").withHomePhone("home").withEmail("email1@email.com").withGroup("Test1"), true);
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("firstname2").withLastname("lastname2").withAddress("address").withHomePhone("home").withEmail("email2@email.com").withGroup("Test1");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
