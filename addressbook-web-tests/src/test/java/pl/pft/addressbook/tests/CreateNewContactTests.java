package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class CreateNewContactTests extends TestBase {

    @Test
    public void testCreateNewContact() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("firstname", "lastname", "address", "home", "mobile", "work", "email1@email.com"));
        app.getContactHelper().submitContactCreation();
        app.getGroupHelper().returnToHomePage();
    }
}
