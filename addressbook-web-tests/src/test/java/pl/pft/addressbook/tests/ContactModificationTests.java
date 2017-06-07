package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getGroupHelper().createGroupIfRequired(new GroupData("Test1", null, null));
        app.getContactHelper().createContactIfRequired(new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1"), true);
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("firstname2", "lastname2", "address2", "home2", "mobile2", "work2", "email2@email.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
