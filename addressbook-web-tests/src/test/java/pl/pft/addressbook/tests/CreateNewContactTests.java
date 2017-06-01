package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class CreateNewContactTests extends TestBase {

    @Test
    public void testCreateNewContact() {
        app.gotoHomePage();
        app.initContactCreation();
        app.fillContactForm(new ContactData("firstname", "lastname", "address", "home", "mobile", "work", "email1@email.com"));
        app.submitContactCreation();
        app.returnToHomePage();
    }
}
