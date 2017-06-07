package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactModificationTests() {
        app.getGroupHelper().createGroupIfRequired(new GroupData("Test1", null, null));
        app.getContactHelper().createContactIfRequired(new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1"), true);
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmContactDeletion();
        app.getContactHelper().returnToHomePage();
    }
}
