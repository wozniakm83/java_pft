package pl.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(app.properties.getProperty("group.defaultName")));
        }
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname(app.properties.getProperty("contact.defaultFirstname"))
                    .withLastname(app.properties.getProperty("contact.defaultLastname"))
                    .withAddress(app.properties.getProperty("contact.defaultAddress"))
                    .withEmail(app.properties.getProperty("contact.defaultEmail"))
                    .withHomePhone(app.properties.getProperty("contact.defaultHomePhone")),
                    true);
        }
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
