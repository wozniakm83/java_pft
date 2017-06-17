package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.group().createIfRequired(new GroupData("Test1", null, null));
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1");
        app.contact().createContact(contact, true);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.setId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
