package pl.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewTests extends TestBase {

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
    public void testContactView() {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(contact);

        assertThat(contact.getFirstname(), equalTo(contactInfoFromViewForm.getFirstname()));
        assertThat(contact.getLastname(), equalTo(contactInfoFromViewForm.getLastname()));
        assertThat(contact.getAddress(), equalTo(contactInfoFromViewForm.getAddress()));
        assertThat(contact.getHomePhone(), equalTo(contactInfoFromViewForm.getHomePhone()));
        assertThat(contact.getMobilePhone(), equalTo(contactInfoFromViewForm.getMobilePhone()));
        assertThat(contact.getWorkPhone(), equalTo(contactInfoFromViewForm.getWorkPhone()));
        assertThat(contact.getEmail(), equalTo(contactInfoFromViewForm.getEmail()));
        assertThat(contact.getEmail2(), equalTo(contactInfoFromViewForm.getEmail2()));
        assertThat(contact.getEmail3(), equalTo(contactInfoFromViewForm.getEmail3()));
    }
}
