package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.group().createIfRequired(new GroupData().withName("Test1"));
        app.contact().createIfRequired(new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1"), true);
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        int index = 0;
        ContactData contact = new ContactData(before.get(index).getId(), "firstname2", "lastname2", "address", "home", null, null, "email2@email.com", "Test1");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
