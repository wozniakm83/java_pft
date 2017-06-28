package pl.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.group().createIfRequired(new GroupData().withName("Test1"));
        app.contact().createIfRequired(new ContactData()
                .withFirstname("firstname").withLastname("lastname").withAddress("somewhere over the rainbow")
                .withEmail("email1@email.com").withEmail2("email2@email.com").withEmail3("email3@email.com")
                .withHomePhone("(+11) 111 1111").withMobilePhone("2-222-222").withWorkPhone("333 33 33")
                .withGroup("Test1"), true);
    }

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
