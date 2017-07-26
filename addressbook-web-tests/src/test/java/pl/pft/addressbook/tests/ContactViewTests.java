package pl.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws MalformedURLException {
        app.group().createIfRequired(new GroupData());
        app.contact().createIfRequired(new ContactData(), true);
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
