package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getGroupHelper().createGroupIfRequired(new GroupData("Test1", null, null));
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1"), true);
    }
}
