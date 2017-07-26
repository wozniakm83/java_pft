package pl.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws MalformedURLException {
        app.group().createIfRequired();
        app.contact().createIfRequired(true);
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname(app.properties.getProperty("contact.modifiedFirstname"))
                .withLastname(app.properties.getProperty("contact.modifiedLastname"))
                .withAddress(app.properties.getProperty("contact.modifiedAddress"))
                .withHomePhone(app.properties.getProperty("contact.modifiedHomePhone"))
                .withMobilePhone(modifiedContact.getMobilePhone())
                .withWorkPhone("")
                .withEmail(app.properties.getProperty("contact.modifiedEmail"))
                .withEmail2(modifiedContact.getEmail2())
                .withEmail3(app.properties.getProperty("contact.modifiedEmail3"));
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
