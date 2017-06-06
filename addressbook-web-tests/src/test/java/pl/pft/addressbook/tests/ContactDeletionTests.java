package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactModificationTests() {
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "home", null, null, "email1@email.com", "Test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmContactDeletion();
        app.getContactHelper().returnToHomePage();
    }
}
