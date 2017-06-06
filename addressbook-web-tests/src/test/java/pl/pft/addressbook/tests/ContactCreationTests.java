package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1"), true);
    }
}
